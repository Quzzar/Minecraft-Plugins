package com.quzzar.im.machines.types;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.quzzar.im.InnovativeMechanics;
import com.quzzar.im.machines.BreakMachine;
import com.quzzar.im.machines.Machine;
import com.quzzar.im.machines.MachineType;
import com.quzzar.im.machines.types.pipe.InvLocData;
import com.quzzar.im.sound.SoundDatabase;
import com.quzzar.im.sound.SoundManager;
import com.quzzar.im.structures.Structure;
import com.quzzar.im.structures.StructureManager;
import com.quzzar.im.structures.data.StructDataTag;
import com.quzzar.im.structures.loading.inv.InvTag;
import com.quzzar.im.textures.TextureCollection;
import com.quzzar.im.textures.TextureDatabase;
import com.quzzar.im.utils.ItemUtils;

public class Pipe extends Machine {
	
	public static final String BLOCK_INV_ID = "BLOCK_INV";
	public final ItemStack firstPipe;
	
	public Pipe(MachineType machineType, TextureDatabase itemTexture, TextureCollection texCollection,
			TextureCollection poweredTexCollection) {
		super(machineType, itemTexture, texCollection, poweredTexCollection);
		firstPipe = TextureDatabase.PIPE_END.toRawItemStack(1);
	}

	@Override
	protected ItemStack initialize(ItemStack rawItem) {

		ItemMeta im = rawItem.getItemMeta();

		im.setDisplayName(ChatColor.YELLOW + "Pipe");

		rawItem.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(InnovativeMechanics.instance, "Pipe"), rawItem);
		shR.shape(new String[] { "III" });
		shR.setIngredient('I', Material.IRON_BLOCK);
		InnovativeMechanics.instance.getServer().addRecipe(shR);

		return rawItem;

	}

	@Override
	public void create(Block placedBlock, BlockFace direction, Player p) {}
	
	public boolean create(InvLocData startingData, InvLocData endingData, Player p) {
		
		if(!isGoodLength(startingData.getInvLoc(), endingData.getInvLoc())) {
			return false;
		}
		
		Structure structure = StructureManager.createStructure(startingData.getInvLoc(), BlockFace.NORTH, p,
				MachineType.PIPE);
		
		structure.setMetaStr(StructDataTag.STARTING_STRUCT_ID, startingData.getStructID());
		structure.setMetaStr(StructDataTag.STARTING_STRUCT_TAG, startingData.getInvTag());
		structure.setMetaInt(StructDataTag.STARTING_LOC_X, startingData.getInvLoc().getBlockX());
		structure.setMetaInt(StructDataTag.STARTING_LOC_Y, startingData.getInvLoc().getBlockY());
		structure.setMetaInt(StructDataTag.STARTING_LOC_Z, startingData.getInvLoc().getBlockZ());
		
		structure.setMetaStr(StructDataTag.ENDING_STRUCT_ID, endingData.getStructID());
		structure.setMetaStr(StructDataTag.ENDING_STRUCT_TAG, endingData.getInvTag());
		structure.setMetaInt(StructDataTag.ENDING_LOC_X, endingData.getInvLoc().getBlockX());
		structure.setMetaInt(StructDataTag.ENDING_LOC_Y, endingData.getInvLoc().getBlockY());
		structure.setMetaInt(StructDataTag.ENDING_LOC_Z, endingData.getInvLoc().getBlockZ());
		
		return true;
		
	}

	@Override
	public void interact(Structure structure, ArmorStand as, Player p) {}

	@Override
	public void updateTask(Structure structure) {
		
		Inventory startingInv = getStartingInv(structure);
		Inventory endingInv = getEndingInv(structure);
		
		if(startingInv == null || endingInv == null) {
			BreakMachine.forceBreak(structure, structure.getCenterLocation().toLocation(), true);
			return;
		}
		
		transferItem(startingInv, endingInv);
		
	}

	@Override
	public void delete(Structure structure) {

		StructureManager.removeStructure(structure);
		SoundManager.playSound(structure.getCenterLocation().toLocation(), SoundDatabase.MACHINE_BREAK);

	}
	
	private void transferItem(Inventory startingInv, Inventory endingInv) {
		for(ItemStack i : startingInv.getStorageContents()){
			if(i!=null) {
				if(!ItemUtils.inventoryFull(endingInv, i)) {
					endingInv.addItem(i);
					i.setAmount(0);
					return;
				}
			}
		}
	}
	
	private Inventory getStartingInv(Structure structure) {
		
		String strStructID = structure.getMetaStr(StructDataTag.STARTING_STRUCT_ID);
		if(strStructID.equals(Pipe.BLOCK_INV_ID)){
			
			World world = structure.getCenterLocation().toLocation().getWorld();
			Location startingLoc = new Location(world, structure.getMetaInt(StructDataTag.STARTING_LOC_X),
					structure.getMetaInt(StructDataTag.STARTING_LOC_Y), structure.getMetaInt(StructDataTag.STARTING_LOC_Z));
			
			if(startingLoc.getBlock().getState() instanceof BlockInventoryHolder) {
				BlockInventoryHolder blockInv = (BlockInventoryHolder) startingLoc.getBlock().getState();
				return blockInv.getInventory();
			} else {
				return null;
			}
			
		} else {
			
			Structure struct = StructureManager.getStructure(UUID.fromString(strStructID));
			return struct.getInventory(InvTag.valueOf(structure.getMetaStr(StructDataTag.STARTING_STRUCT_TAG)));
			
		}
		
	}
	
	private Inventory getEndingInv(Structure structure) {
		
		String strStructID = structure.getMetaStr(StructDataTag.ENDING_STRUCT_ID);
		if(strStructID.equals(Pipe.BLOCK_INV_ID)){
			
			World world = structure.getCenterLocation().toLocation().getWorld();
			Location endingLoc = new Location(world, structure.getMetaInt(StructDataTag.ENDING_LOC_X),
					structure.getMetaInt(StructDataTag.ENDING_LOC_Y), structure.getMetaInt(StructDataTag.ENDING_LOC_Z));
			
			if(endingLoc.getBlock().getState() instanceof BlockInventoryHolder) {
				BlockInventoryHolder blockInv = (BlockInventoryHolder) endingLoc.getBlock().getState();
				return blockInv.getInventory();
			} else {
				return null;
			}
			
		} else {
			
			Structure struct = StructureManager.getStructure(UUID.fromString(strStructID));
			return struct.getInventory(InvTag.valueOf(structure.getMetaStr(StructDataTag.ENDING_STRUCT_TAG)));
			
		}
		
	}
	
	private boolean isGoodLength(Location startLoc, Location endLoc) {
		Vector vec = startLoc.toVector().subtract(endLoc.toVector());
		return vec.length()<=8;
	}
	
	
}
