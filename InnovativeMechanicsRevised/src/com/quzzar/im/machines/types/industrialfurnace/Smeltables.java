package com.quzzar.im.machines.types.industrialfurnace;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.quzzar.im.machines.types.macerator.OreList;

public enum Smeltables {
	
	IRON_ORE(new ItemStack(Material.IRON_ORE), new ItemStack(Material.IRON_INGOT)),
	GOLD_ORE(new ItemStack(Material.GOLD_ORE), new ItemStack(Material.GOLD_INGOT)),
	DIAMOND_ORE(new ItemStack(Material.DIAMOND_ORE), new ItemStack(Material.DIAMOND)),
	COAL_ORE(new ItemStack(Material.COAL_ORE), new ItemStack(Material.COAL)),
	EMERALD_ORE(new ItemStack(Material.EMERALD_ORE), new ItemStack(Material.EMERALD)),
	QUARTZ_ORE(new ItemStack(Material.QUARTZ), new ItemStack(Material.QUARTZ)),
	REDSTONE_ORE(new ItemStack(Material.REDSTONE_ORE), new ItemStack(Material.REDSTONE, 3)),
	LAPIS_ORE(new ItemStack(Material.LAPIS_ORE), new ItemStack(Material.LAPIS_LAZULI, 4)),
	SAND(new ItemStack(Material.SAND), new ItemStack(Material.GLASS)),
	
	COAL_CHUNKS(OreList.COAL.getOreToChunkMap().getItem(), new ItemStack(Material.COAL, 2)),
	IRON_CHUNKS(OreList.IRON.getOreToChunkMap().getItem(), new ItemStack(Material.IRON_INGOT, 2)),
	GOLD_CHUNKS(OreList.GOLD.getOreToChunkMap().getItem(), new ItemStack(Material.GOLD_INGOT, 2)),
	REDSTONE_CHUNKS(OreList.REDSTONE.getOreToChunkMap().getItem(), new ItemStack(Material.REDSTONE, 6)),
	LAPIS_CHUNKS(OreList.LAPIS.getOreToChunkMap().getItem(), new ItemStack(Material.LAPIS_LAZULI, 8)),
	EMERALD_CHUNKS(OreList.EMERALD.getOreToChunkMap().getItem(), new ItemStack(Material.EMERALD, 2)),
	DIAMOND_CHUNKS(OreList.DIAMOND.getOreToChunkMap().getItem(), new ItemStack(Material.DIAMOND, 2));
	
	
	private ItemStack item;
	private ItemStack product;
	private int index = -1;
	
	Smeltables(ItemStack item, ItemStack product){
		this.item = item;
		this.product = product;
	}
	
	public ItemStack getInput(){
		return item;
	}
	
	public ItemStack getProduct(){
		return product;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
