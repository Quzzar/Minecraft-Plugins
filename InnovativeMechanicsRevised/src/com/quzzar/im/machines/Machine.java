package com.quzzar.im.machines;

import java.util.ArrayList;
import java.util.Hashtable;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.ItemDataTag;
import com.quzzar.im.structures.Structure;
import com.quzzar.im.structures.loading.asbuilding.ASPlacement;
import com.quzzar.im.structures.loading.inv.InvTag;
import com.quzzar.im.textures.TextureCollection;
import com.quzzar.im.textures.TextureDatabase;

public abstract class Machine{
	
	public static final Material FILLER_TYPE = Material.BARRIER;
	
	
	private MachineType machineType;
	
	private ItemStack item;
	
	private ArrayList<ItemStack> texCollection;
	private ArrayList<ItemStack> poweredTexCollection;
	
	private ArrayList<ASPlacement> placements = new ArrayList<ASPlacement>();
	private Hashtable<Integer, InvTag> indexInvTagMap = new Hashtable<Integer, InvTag>();
	
	private int speed;
	
	public Machine(MachineType machineType, TextureDatabase itemTexture, TextureCollection texCollection, TextureCollection poweredTexCollection){
		
		this.machineType = machineType;
		
		this.texCollection = texCollection.toRawItemStackCollection(1);
		this.poweredTexCollection = poweredTexCollection.toRawItemStackCollection(1);
		
		ItemStack rawItem = itemTexture.toRawItemStack(1);
		CraftMeta.itemSet(rawItem, ItemDataTag.MACHINE, machineType);
		this.item = initialize(rawItem);
		
	}
	
	protected abstract ItemStack initialize(ItemStack rawItem);
	
	public abstract void create(Block placedBlock, BlockFace direction, Player p);
	
	public abstract void interact(Structure structure, ArmorStand as, Player p);
	
	public abstract void updateTask(Structure structure);
	
	public abstract void delete(Structure structure);
	
	public ArrayList<ASPlacement> getASPlacements() {
		return placements;
	}
	
	public void setASPlacements(ArrayList<ASPlacement> placements) {
		this.placements = placements;
	}
	
	public MachineType getMachineType() {
		return machineType;
	}

	public ItemStack getItem() {
		return item;
	}
	
	public ArrayList<ItemStack> getTexCollection() {
		return texCollection;
	}

	public ArrayList<ItemStack> getPoweredTexCollection() {
		return poweredTexCollection;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public Hashtable<Integer, InvTag> getIndexInvTagMap(){
		return indexInvTagMap;
	}
	
	public void addToIndexInvTagMap(int index, InvTag tag) {
		indexInvTagMap.put(index, tag);
	}
	
}
