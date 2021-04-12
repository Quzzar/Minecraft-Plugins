package com.quzzar.im.versioncontrol.global.material;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

@SuppressWarnings("deprecation")
public enum Material_1_12 {

	WHITE_STAINED_GLASS_PANE(new MaterialData(Material.STAINED_GLASS_PANE)),
	LIGHT_GRAY_STAINED_GLASS_PANE(new MaterialData(Material.STAINED_GLASS_PANE, (byte) 8)),
	PLAYER_HEAD_ITEM(new MaterialData(Material.SKULL_ITEM, (byte) 3)),
	PLAYER_HEAD_BLOCK(new MaterialData(Material.SKULL, (byte) 1)),
	PLAYER_HEAD_BLOCK_WALL(new MaterialData(Material.SKULL, (byte) 1)),
	IRON_BARS(new MaterialData(Material.IRON_FENCE)),
	PISTON(new MaterialData(Material.PISTON_BASE)),
	CRAFTING_TABLE(new MaterialData(Material.WORKBENCH)),
	OAK_SLAB(new MaterialData(Material.WOOD_STEP)),
	REDSTONE_TORCH(new MaterialData(Material.REDSTONE_TORCH_ON)),
	WOODEN_PICKAXE(new MaterialData(Material.WOOD_PICKAXE)),
	WOODEN_AXE(new MaterialData(Material.WOOD_AXE)),
	WOODEN_SHOVEL(new MaterialData(Material.WOOD_SPADE)),
	WOODEN_HOE(new MaterialData(Material.WOOD_HOE)),
	WOODEN_SWORD(new MaterialData(Material.WOOD_SWORD)),
	STONE_SHOVEL(new MaterialData(Material.STONE_SPADE)),
	IRON_SHOVEL(new MaterialData(Material.IRON_SPADE)),
	DIAMOND_SHOVEL(new MaterialData(Material.DIAMOND_SPADE)),
	GOLDEN_PICKAXE( new MaterialData(Material.GOLD_PICKAXE)),
	GOLDEN_AXE(new MaterialData(Material.GOLD_AXE)),
	GOLDEN_SHOVEL(new MaterialData(Material.GOLD_SPADE)),
	GOLDEN_HOE(new MaterialData(Material.GOLD_HOE)),
	GOLDEN_SWORD(new MaterialData(Material.GOLD_SWORD)),
	WOOD(new MaterialData(Material.WOOD)),
	LOG(new MaterialData(Material.LOG)),
	LOG_2(new MaterialData(Material.LOG_2)),
	WOOL(new MaterialData(Material.WOOL)),
	LAPIS_LAZULI(new MaterialData(Material.INK_SACK, (byte) 4)),
	QUARTZ_ORE(new MaterialData(Material.QUARTZ_ORE)),
	BED_BLOCK(new MaterialData(Material.BED_BLOCK)),
	WATER(new MaterialData(Material.STATIONARY_WATER)),
	WOOD_DOOR(new MaterialData(Material.WOOD_DOOR)),
	DIORITE(new MaterialData(Material.STONE, (byte) 3)),
	GRANITE(new MaterialData(Material.STONE, (byte) 1)),
	ANDESITE(new MaterialData(Material.STONE, (byte) 5));
	
private MaterialData mData;
	
	private Material_1_12(MaterialData mData) {
		this.mData = mData;
	}
	
	public MaterialData getMaterialData() {
		return mData;
	}

	public static Material_1_12[] getAllValues() {
		return Material_1_12.values();
	}
	
}
