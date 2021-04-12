package com.quzzar.im.machines.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.im.InnovativeMechanics;
import com.quzzar.im.machines.Machine;
import com.quzzar.im.machines.MachineType;
import com.quzzar.im.machines.types.autocrafttable.ItemStackData;
import com.quzzar.im.sound.SoundDatabase;
import com.quzzar.im.sound.SoundManager;
import com.quzzar.im.structures.Structure;
import com.quzzar.im.structures.StructureManager;
import com.quzzar.im.structures.loading.asbuilding.ASDirection;
import com.quzzar.im.structures.loading.asbuilding.ASPlacement;
import com.quzzar.im.structures.loading.inv.InvTag;
import com.quzzar.im.textures.TextureCollection;
import com.quzzar.im.textures.TextureDatabase;
import com.quzzar.im.utils.ItemUtils;

public class AutoCraftingTable extends Machine {

	public AutoCraftingTable(MachineType machineType, TextureDatabase itemTexture, TextureCollection texCollection,
			TextureCollection poweredTexCollection) {
		super(machineType, itemTexture, texCollection, poweredTexCollection);
	}

	@Override
	protected ItemStack initialize(ItemStack rawItem) {

		ArrayList<ASPlacement> placements = ASPlacement.getMachineBaseList();
		placements.add(new ASPlacement(0, 1, -1.425, ASDirection.FORWARD));
		placements.add(new ASPlacement(0, -1, -1.425, ASDirection.OPPOSITE));
		placements.add(new ASPlacement(0, 0, -0.40, ASDirection.LEFT));

		setASPlacements(placements);

		setSpeed(10);
		
		addToIndexInvTagMap(8, InvTag.OUTPUT);
		addToIndexInvTagMap(9, InvTag.INPUT);
		addToIndexInvTagMap(10, InvTag.CRAFTING_LAYOUT);

		///

		ItemMeta im = rawItem.getItemMeta();

		im.setDisplayName(ChatColor.YELLOW + "Automatic Crafting Table");

		rawItem.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(InnovativeMechanics.instance, "AutoCraftTable"), rawItem);
		shR.shape(new String[] { "III", "ICI", "RRR" });
		shR.setIngredient('I', Material.IRON_BLOCK);
		shR.setIngredient('C', Material.CRAFTING_TABLE);
		shR.setIngredient('R', Material.REDSTONE);
		InnovativeMechanics.instance.getServer().addRecipe(shR);

		return rawItem;

	}

	@Override
	public void create(Block placedBlock, BlockFace direction, Player p) {

		/// Set Variables

		String invName1 = "[Output] Crafted Items";
		String invName2 = "[Input] Raw Materials";
		String craftInvName3 = "Crafting Layout";
		int invSize = 18;

		///

		Location centerLocation = placedBlock.getLocation();
		centerLocation.add(0.5, 0, 0.5);

		Structure structure = StructureManager.createStructure(centerLocation, direction, p,
				MachineType.AUTO_CRAFTING_TABLE);

		structure.addStructInventory(InvTag.OUTPUT, Bukkit.createInventory(null, invSize, invName1), invName1);
		structure.addStructInventory(InvTag.INPUT, Bukkit.createInventory(null, invSize, invName2), invName2);

		structure.addStructInventory(InvTag.CRAFTING_LAYOUT,
				Bukkit.createInventory(null, InventoryType.DROPPER, craftInvName3), craftInvName3);

	}

	@Override
	public void interact(Structure structure, ArmorStand as, Player p) {
		
		InvTag tag = structure.getInvTag(as);
		Inventory inv = structure.getInventory(tag);

		if (inv != null) {
			if (tag != InvTag.CRAFTING_LAYOUT) {
				SoundManager.playSound(as.getEyeLocation(), SoundDatabase.MINICHEST_OPEN);
			}
			
			p.openInventory(inv);
			
		} else {
			
			if(structure.getMachineType().isPowerable()) {
				structure.toggleRunning();
			}
			
		}

	}

	@Override
	public void updateTask(Structure structure) {
		
		if(!structure.isRunning()) {
			return;
		}
		
		Location centerLocation = structure.getCenterLocation().toLocation();
		
		SoundManager.playSound(centerLocation, SoundDatabase.MACHINE_RUNNING);
		
		Inventory inputInv = structure.getInventory(InvTag.INPUT);
		Inventory outputInv = structure.getInventory(InvTag.OUTPUT);
		Inventory layoutInv = structure.getInventory(InvTag.CRAFTING_LAYOUT);
		
		
		
		
		ArrayList<ItemStack> craftInstance = new ArrayList<ItemStack>();
		
		for(int i=0; i<layoutInv.getSize(); i++){
			
			if(layoutInv.getItem(i)==null){
				craftInstance.add(layoutInv.getItem(i));
			}else{
				ItemStack iNew = layoutInv.getItem(i).clone();
				iNew.setAmount(1);
				
				craftInstance.add(iNew);
			}
			
        }
		
		reduceCraftInstance(craftInstance);
		
		
		Map<Character,ItemStack> ingredientsMap = new HashMap<Character,ItemStack>();
		
		for(ItemStack instance : craftInstance){
			if(instance==null){
				ingredientsMap.put(intToCharacter(craftInstance.indexOf(instance)), null);
			}else{
				ingredientsMap.put(intToCharacter(craftInstance.indexOf(instance)), instance);
			}
		}
		
		
		
		Iterator<Recipe> itr = Bukkit.recipeIterator();
		
		while(itr.hasNext()){
			Recipe r = itr.next();
			
			if(r instanceof ShapedRecipe){
				
				ShapedRecipe shR = (ShapedRecipe) r;
				
				boolean found = attemptToCraft(shR, ingredientsMap, craftInstance, inputInv, outputInv, structure, centerLocation);
				if(found) {
					return;
				}
				
			} else if(r instanceof ShapelessRecipe){
				
				//ShapelessRecipe shless = (ShapelessRecipe) r;
				
			}
			
		}
		
		
	}

	@Override
	public void delete(Structure structure) {

		StructureManager.removeStructure(structure);
		SoundManager.playSound(structure.getCenterLocation().toLocation(), SoundDatabase.MACHINE_BREAK);

	}
	
	
	private boolean attemptToCraft(ShapedRecipe shR, Map<Character,ItemStack> ingredientsMap,
			ArrayList<ItemStack> craftInstance, Inventory inputInv, Inventory outputInv,
			Structure structure, Location centerLocation) {
		
		if(shR.getIngredientMap().equals(ingredientsMap)){
			

			ArrayList<ItemStackData> itemSlots = new ArrayList<ItemStackData>();
			
			int size_count = 0;
			
			for(ItemStack item : craftInstance){
				if(item!=null){
					size_count++;
				}
			}
			
			
			for(ItemStack item : inputInv.getContents()){
				if(item!=null){
					ItemStack itemNew = item.clone();
					
					
					int amt = 0;
					
					ArrayList<ItemStack> removeList = new ArrayList<ItemStack>();
					
					for(ItemStack craftItem : craftInstance){
						if(craftItem!=null){
							
							if(craftItem.isSimilar(itemNew)&&itemNew.getAmount()>amt){
								
								itemSlots.add(new ItemStackData(inputInv.getItem(inputInv.first(item)), inputInv.first(item)));
								amt++;
								removeList.add(craftItem);
							}
							
							
						}
					}
					
					craftInstance.removeAll(removeList);
					
				}
				
			}
			
			
			if(itemSlots.size()==size_count){
				
				for(ItemStackData n : itemSlots){
					ItemStack i = n.getItemStack();
					i.subtract();
					inputInv.setItem(n.getIndex(), i);
				}
				
				SoundManager.playSound(centerLocation, SoundDatabase.MACHINE_SUCCESS);
				
				ItemUtils.addItemToInventory(outputInv, shR.getResult(), centerLocation);
				
				return true;
				
			}
			
		}
		
		return false;
		
	}

	
	private void reduceCraftInstance(ArrayList<ItemStack> craftInstance){
		ArrayList<ItemStack> removeList = new ArrayList<ItemStack>();
		
		checkRemoveIndex(craftInstance, removeList, 0, 1, 2);
		checkRemoveIndex(craftInstance, removeList, 3, 4, 5);
		checkRemoveIndex(craftInstance, removeList, 6, 7, 8);
		
		checkRemoveIndex(craftInstance, removeList, 0, 3, 6);
		checkRemoveIndex(craftInstance, removeList, 1, 4, 7);
		checkRemoveIndex(craftInstance, removeList, 2, 5, 8);
		
		
		craftInstance.removeAll(removeList);
		
	}
	
	private void checkRemoveIndex(ArrayList<ItemStack> craftInstance, ArrayList<ItemStack> list, int x, int y, int z){
		if(craftInstance.get(x)==null&&craftInstance.get(y)==null&&craftInstance.get(z)==null){
			if(!list.contains(craftInstance.get(x))){
				list.add(craftInstance.get(x));
			}
			if(!list.contains(craftInstance.get(y))){
				list.add(craftInstance.get(y));
			}
			if(!list.contains(craftInstance.get(z))){
				list.add(craftInstance.get(z));
			}
		}
	}
	
	
	private Character intToCharacter(int i){
		return String.valueOf((char)(i + 97)).charAt(0);
	}
	
	
}
