package com.quzzar.im.machines.types;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.im.InnovativeMechanics;
import com.quzzar.im.machines.Machine;
import com.quzzar.im.machines.MachineType;
import com.quzzar.im.machines.types.industrialfurnace.Burnables;
import com.quzzar.im.machines.types.industrialfurnace.Smeltables;
import com.quzzar.im.sound.SoundDatabase;
import com.quzzar.im.sound.SoundManager;
import com.quzzar.im.structures.Structure;
import com.quzzar.im.structures.StructureManager;
import com.quzzar.im.structures.data.StructDataTag;
import com.quzzar.im.structures.loading.asbuilding.ASDirection;
import com.quzzar.im.structures.loading.asbuilding.ASPlacement;
import com.quzzar.im.structures.loading.inv.InvTag;
import com.quzzar.im.textures.TextureCollection;
import com.quzzar.im.textures.TextureDatabase;
import com.quzzar.im.utils.ItemUtils;

public class IndustrialFurnace extends Machine {
	
	
	public IndustrialFurnace(MachineType machineType, TextureDatabase itemTexture, TextureCollection texCollection,
			TextureCollection poweredTexCollection) {
		super(machineType, itemTexture, texCollection, poweredTexCollection);
	}

	@Override
	protected ItemStack initialize(ItemStack rawItem) {

		ArrayList<ASPlacement> placements = ASPlacement.getMachineBaseList();
		placements.add(new ASPlacement(0, 1, -1.425, ASDirection.FORWARD));

		setASPlacements(placements);
		
		setSpeed(16);
		
		addToIndexInvTagMap(0, InvTag.INPUT);
		addToIndexInvTagMap(1, InvTag.INPUT_FUEL);
		addToIndexInvTagMap(8, InvTag.OUTPUT);
		
		///

		ItemMeta im = rawItem.getItemMeta();

		im.setDisplayName(ChatColor.GOLD + "Industrial Furnace");

		rawItem.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(InnovativeMechanics.instance, "IndustrialFurnace"), rawItem);
		shR.shape(new String[] { "III", "ICI", "III" });
		shR.setIngredient('I', Material.IRON_BLOCK);
		shR.setIngredient('C', Material.COAL);
		InnovativeMechanics.instance.getServer().addRecipe(shR);

		return rawItem;

	}

	@Override
	public void create(Block placedBlock, BlockFace direction, Player p) {

		/// Set Variables
		
		String invName1 = "[Input] Ores";
		String invName2 = "[Input] Fuel";
		String invName3 = "[Output] Refined Materials";
		int invSize = 18;
		
		///

		Location centerLocation = placedBlock.getLocation();
		centerLocation.add(0.5, 0, 0.5);

		Structure structure = StructureManager.createStructure(centerLocation, direction, p,
				MachineType.INDUSTRIAL_FURNACE);
		
		structure.addStructInventory(InvTag.INPUT, Bukkit.createInventory(null, invSize, invName1), invName1);
		structure.addStructInventory(InvTag.INPUT_FUEL, Bukkit.createInventory(null, invSize, invName2), invName2);
		structure.addStructInventory(InvTag.OUTPUT, Bukkit.createInventory(null, invSize, invName3), invName3);

		structure.setMetaInt(StructDataTag.REMAINING_BURN_TIME, 0);

	}

	@Override
	public void interact(Structure structure, ArmorStand as, Player p) {

		InvTag tag = structure.getInvTag(as);
		Inventory inv = structure.getInventory(tag);

		if (inv != null) {
			
			SoundManager.playSound(as.getEyeLocation(), SoundDatabase.MINICHEST_OPEN);
			p.openInventory(inv);
			
		}
		
	}

	@Override
	public void updateTask(Structure structure) {
		
		Location centerLocation = structure.getCenterLocation().toLocation();
		
		SoundManager.playSound(centerLocation, SoundDatabase.MACHINE_RUNNING);
		
		boolean found = attemptToSmelt(structure, centerLocation);
		structure.setRunning(found);
		
	}

	@Override
	public void delete(Structure structure) {

		StructureManager.removeStructure(structure);
		SoundManager.playSound(structure.getCenterLocation().toLocation(), SoundDatabase.MACHINE_BREAK);

	}
	
	private boolean attemptToSmelt(Structure structure, Location centerLocation) {
		
		if(!canRun(structure, centerLocation)) {
			return false;
		}
		
		Inventory inputInv = structure.getInventory(InvTag.INPUT);
		Inventory outputInv = structure.getInventory(InvTag.OUTPUT);
		
		for(ItemStack i : inputInv.getContents()){
			
			if(i!=null){
				
				for(Smeltables smeltable : Smeltables.values()){
					
					if(smeltable.getInput().isSimilar(i)) {
						
						i.subtract();
						
						ItemUtils.addItemToInventory(outputInv, smeltable.getProduct(), centerLocation);
						
						SoundManager.playSound(centerLocation, SoundDatabase.FURNACE_COMPLETE);
						
						return true;
						
					}
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	private boolean canRun(Structure structure, Location centerLocation) {
		
		int burnTime = structure.getMetaInt(StructDataTag.REMAINING_BURN_TIME);
		
		if(burnTime > 0) {
			structure.setMetaInt(StructDataTag.REMAINING_BURN_TIME, burnTime-5);
			return true;
		}
		
		Inventory inputFuelInv = structure.getInventory(InvTag.INPUT_FUEL);
		
		for(ItemStack i : inputFuelInv.getContents()){
			if(i != null) {
				Burnables burnable = Burnables.getBurnable(i.getType());
				if(burnable != null) {
					
					i.subtract();
					structure.setMetaInt(StructDataTag.REMAINING_BURN_TIME, burnable.getDuration());
					
					if(burnable.getMaterial() == Material.LAVA_BUCKET) {
						ItemUtils.addItemToInventory(inputFuelInv, new ItemStack(Material.BUCKET), centerLocation);
					}
					
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	
}
