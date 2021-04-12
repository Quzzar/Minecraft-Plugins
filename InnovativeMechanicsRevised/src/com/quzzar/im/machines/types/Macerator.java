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
import com.quzzar.im.machines.types.macerator.OreList;
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

public class Macerator extends Machine {
	
	
	public Macerator(MachineType machineType, TextureDatabase itemTexture, TextureCollection texCollection,
			TextureCollection poweredTexCollection) {
		super(machineType, itemTexture, texCollection, poweredTexCollection);
	}

	@Override
	protected ItemStack initialize(ItemStack rawItem) {

		ArrayList<ASPlacement> placements = ASPlacement.getMachineBaseList();
		placements.add(new ASPlacement(-1, 0, -1.425, ASDirection.RIGHT));
		placements.add(new ASPlacement(1, 0, -1.425, ASDirection.LEFT));

		setASPlacements(placements);
		
		setSpeed(16);
		
		addToIndexInvTagMap(8, InvTag.INPUT);
		addToIndexInvTagMap(9, InvTag.OUTPUT);

		///

		ItemMeta im = rawItem.getItemMeta();

		im.setDisplayName(ChatColor.GOLD + "Macerator");

		rawItem.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(InnovativeMechanics.instance, "Macerator"), rawItem);
		shR.shape(new String[] { "III", "IRI", "III" });
		shR.setIngredient('I', Material.IRON_BLOCK);
		shR.setIngredient('R', Material.FLINT);
		InnovativeMechanics.instance.getServer().addRecipe(shR);

		return rawItem;

	}

	@Override
	public void create(Block placedBlock, BlockFace direction, Player p) {

		/// Set Variables
		
		String invName1 = "[Input] Ores to Process";
		String invName2 = "[Output] Processed Ores";
		int invSize = 18;
		
		///

		Location centerLocation = placedBlock.getLocation();
		centerLocation.add(0.5, 0, 0.5);

		Structure structure = StructureManager.createStructure(centerLocation, direction, p,
				MachineType.MACERATOR);
		
		structure.addStructInventory(InvTag.INPUT, Bukkit.createInventory(null, invSize, invName1), invName1);
		structure.addStructInventory(InvTag.OUTPUT, Bukkit.createInventory(null, invSize, invName2), invName2);

	}

	@Override
	public void interact(Structure structure, ArmorStand as, Player p) {

		InvTag tag = structure.getInvTag(as);
		Inventory inv = structure.getInventory(tag);

		if (inv != null) {
			
			SoundManager.playSound(as.getEyeLocation(), SoundDatabase.MINICHEST_OPEN);
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
		
		boolean found = attemptToMacerate(structure, centerLocation);
		if(!found) {
			structure.setRunning(false);
		}
		
	}

	@Override
	public void delete(Structure structure) {

		StructureManager.removeStructure(structure);
		SoundManager.playSound(structure.getCenterLocation().toLocation(), SoundDatabase.MACHINE_BREAK);

	}
	
	private boolean attemptToMacerate(Structure structure, Location centerLocation) {
		
		Inventory inputInv = structure.getInventory(InvTag.INPUT);
		Inventory outputInv = structure.getInventory(InvTag.OUTPUT);
		
		for(ItemStack i : inputInv.getContents()){
			
			if(i!=null){
				
				for(OreList ore : OreList.values()){
					
					if (i.getType().equals(ore.getOreToChunkMap().getMaterial())){
						
						SoundManager.playSound(centerLocation, SoundDatabase.MACHINE_SUCCESS);
						
						ItemStack iNew = ore.getOreToChunkMap().getItem();
						
						ItemUtils.addItemToInventory(outputInv, iNew, centerLocation);
						
						i.subtract();
						
						return true;
					}
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	
}
