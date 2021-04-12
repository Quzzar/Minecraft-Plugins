package com.quzzar.ge.npcshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.inventory.ItemStack;

import com.quzzar.ge.core.buylist.SimpleItem;
import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;

public class NPCTradeManager {

	private static HashMap<Villager.Profession, List<NPCTrade>> npcTrades;
	
	public static void init() {
		
		npcTrades = new HashMap<Villager.Profession, List<NPCTrade>>();
		
		///////////////// Mason ///////////////////
		
		addNPCTrade(Profession.MASON, new NPCTrade(1, 128, new SimpleItem(new ItemStack(Material.STONE_BRICKS))));
		addNPCTrade(Profession.MASON, new NPCTrade(1, 128, new SimpleItem(new ItemStack(Material.MOSSY_STONE_BRICKS))));
		addNPCTrade(Profession.MASON, new NPCTrade(1, 128, new SimpleItem(new ItemStack(Material.CHISELED_STONE_BRICKS))));
		addNPCTrade(Profession.MASON, new NPCTrade(1, 128, new SimpleItem(new ItemStack(Material.CRACKED_STONE_BRICKS))));
		
		addNPCTrade(Profession.MASON, new NPCTrade(0.1, 128, new SimpleItem(new ItemStack(Material.COBBLESTONE))));
		addNPCTrade(Profession.MASON, new NPCTrade(0.5, 84, new SimpleItem(new ItemStack(Material.MOSSY_COBBLESTONE))));
		
		addNPCTrade(Profession.MASON, new NPCTrade(0.5, 128, new SimpleItem(new ItemStack(Material.STONE))));
		addNPCTrade(Profession.MASON, new NPCTrade(1, 84, new SimpleItem(new ItemStack(Material.SMOOTH_STONE))));
		
		addNPCTrade(Profession.MASON, new NPCTrade(0.15, 84, new SimpleItem(new ItemStack(Material.GRANITE))));
		addNPCTrade(Profession.MASON, new NPCTrade(0.3, 84, new SimpleItem(new ItemStack(Material.POLISHED_GRANITE))));
		
		addNPCTrade(Profession.MASON, new NPCTrade(0.15, 84, new SimpleItem(new ItemStack(Material.ANDESITE))));
		addNPCTrade(Profession.MASON, new NPCTrade(0.3, 84, new SimpleItem(new ItemStack(Material.POLISHED_ANDESITE))));
		
		addNPCTrade(Profession.MASON, new NPCTrade(0.15, 84, new SimpleItem(new ItemStack(Material.DIORITE))));
		addNPCTrade(Profession.MASON, new NPCTrade(0.3, 84, new SimpleItem(new ItemStack(Material.POLISHED_DIORITE))));
		
		addNPCTrade(Profession.MASON, new NPCTrade(0.15, 128, new SimpleItem(new ItemStack(Material.SANDSTONE))));
		addNPCTrade(Profession.MASON, new NPCTrade(0.5, 128, new SimpleItem(new ItemStack(Material.SMOOTH_SANDSTONE))));
		
		addNPCTrade(Profession.MASON, new NPCTrade(0.2, 128, new SimpleItem(new ItemStack(Material.RED_SANDSTONE))));
		addNPCTrade(Profession.MASON, new NPCTrade(0.6, 128, new SimpleItem(new ItemStack(Material.SMOOTH_RED_SANDSTONE))));
		
		addNPCTrade(Profession.MASON, new NPCTrade(1, 128, new SimpleItem(new ItemStack(Material.BRICKS))));
		addNPCTrade(Profession.MASON, new NPCTrade(3, 64, new SimpleItem(new ItemStack(Material.NETHER_BRICKS))));
		
		addNPCTrade(Profession.MASON, new NPCTrade(24, 84, new SimpleItem(new ItemStack(Material.SMOOTH_QUARTZ))));
		
		addNPCTrade(Profession.MASON, new NPCTrade(8, 84, new SimpleItem(new ItemStack(Material.PRISMARINE))));
		addNPCTrade(Profession.MASON, new NPCTrade(10, 84, new SimpleItem(new ItemStack(Material.PRISMARINE_BRICKS))));
		addNPCTrade(Profession.MASON, new NPCTrade(10, 84, new SimpleItem(new ItemStack(Material.DARK_PRISMARINE))));
		
		addNPCTrade(Profession.MASON, new NPCTrade(6, 48, new SimpleItem(new ItemStack(Material.PURPUR_BLOCK))));
		
		///////////////// Leatherworker ///////////////////
		
		addNPCTrade(Profession.LEATHERWORKER, new NPCTrade(2, 128, new SimpleItem(new ItemStack(Material.LEATHER))));
		addNPCTrade(Profession.LEATHERWORKER, new NPCTrade(0.5, 128, new SimpleItem(new ItemStack(Material.RABBIT_HIDE))));
		
		addNPCTrade(Profession.LEATHERWORKER, new NPCTrade(12, 64, new SimpleItem(new ItemStack(Material.SADDLE))));
		
		addNPCTrade(Profession.LEATHERWORKER, new NPCTrade(18, 64, new SimpleItem(new ItemStack(Material.LEATHER_HORSE_ARMOR))));
		
		addNPCTrade(Profession.LEATHERWORKER, new NPCTrade(12, 32, new SimpleItem(new ItemStack(Material.LEATHER_HELMET))));
		addNPCTrade(Profession.LEATHERWORKER, new NPCTrade(18, 32, new SimpleItem(new ItemStack(Material.LEATHER_CHESTPLATE))));
		addNPCTrade(Profession.LEATHERWORKER, new NPCTrade(16, 32, new SimpleItem(new ItemStack(Material.LEATHER_LEGGINGS))));
		addNPCTrade(Profession.LEATHERWORKER, new NPCTrade(10, 32, new SimpleItem(new ItemStack(Material.LEATHER_BOOTS))));
		
		///////////////// Armorer ///////////////////
		
		addNPCTrade(Profession.ARMORER, new NPCTrade(14, 48, new SimpleItem(new ItemStack(Material.LEATHER_HELMET))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(20, 48, new SimpleItem(new ItemStack(Material.LEATHER_CHESTPLATE))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(18, 48, new SimpleItem(new ItemStack(Material.LEATHER_LEGGINGS))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(12, 48, new SimpleItem(new ItemStack(Material.LEATHER_BOOTS))));
		
		addNPCTrade(Profession.ARMORER, new NPCTrade(16, 48, new SimpleItem(new ItemStack(Material.CHAINMAIL_HELMET))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(24, 48, new SimpleItem(new ItemStack(Material.CHAINMAIL_CHESTPLATE))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(20, 48, new SimpleItem(new ItemStack(Material.CHAINMAIL_LEGGINGS))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(14, 48, new SimpleItem(new ItemStack(Material.CHAINMAIL_BOOTS))));
		
		addNPCTrade(Profession.ARMORER, new NPCTrade(30, 48, new SimpleItem(new ItemStack(Material.IRON_HELMET))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(48, 48, new SimpleItem(new ItemStack(Material.IRON_CHESTPLATE))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(42, 48, new SimpleItem(new ItemStack(Material.IRON_LEGGINGS))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(24, 48, new SimpleItem(new ItemStack(Material.IRON_BOOTS))));
		
		addNPCTrade(Profession.ARMORER, new NPCTrade(40, 48, new SimpleItem(new ItemStack(Material.GOLDEN_HELMET))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(64, 48, new SimpleItem(new ItemStack(Material.GOLDEN_CHESTPLATE))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(56, 48, new SimpleItem(new ItemStack(Material.GOLDEN_LEGGINGS))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(32, 48, new SimpleItem(new ItemStack(Material.GOLDEN_BOOTS))));
		
		addNPCTrade(Profession.ARMORER, new NPCTrade(90, 32, new SimpleItem(new ItemStack(Material.DIAMOND_HELMET))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(144, 32, new SimpleItem(new ItemStack(Material.DIAMOND_CHESTPLATE))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(126, 32, new SimpleItem(new ItemStack(Material.DIAMOND_LEGGINGS))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(72, 32, new SimpleItem(new ItemStack(Material.DIAMOND_BOOTS))));
		
		addNPCTrade(Profession.ARMORER, new NPCTrade(20, 16, new SimpleItem(new ItemStack(Material.LEATHER_HORSE_ARMOR))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(48, 16, new SimpleItem(new ItemStack(Material.IRON_HORSE_ARMOR))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(64, 16, new SimpleItem(new ItemStack(Material.GOLDEN_HORSE_ARMOR))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(144, 8, new SimpleItem(new ItemStack(Material.DIAMOND_HORSE_ARMOR))));
		
		addNPCTrade(Profession.ARMORER, new NPCTrade(12, 32, new SimpleItem(new ItemStack(Material.SHIELD))));
		addNPCTrade(Profession.ARMORER, new NPCTrade(100, 8, new SimpleItem(new ItemStack(Material.TURTLE_HELMET))));
		
		///////////////// Butcher ///////////////////
		
		addNPCTrade(Profession.BUTCHER, new NPCTrade(8, 128, new SimpleItem(new ItemStack(Material.COOKED_BEEF))));
		addNPCTrade(Profession.BUTCHER, new NPCTrade(8, 128, new SimpleItem(new ItemStack(Material.COOKED_PORKCHOP))));
		addNPCTrade(Profession.BUTCHER, new NPCTrade(6.5, 128, new SimpleItem(new ItemStack(Material.COOKED_MUTTON))));
		addNPCTrade(Profession.BUTCHER, new NPCTrade(6, 128, new SimpleItem(new ItemStack(Material.COOKED_CHICKEN))));
		addNPCTrade(Profession.BUTCHER, new NPCTrade(5, 128, new SimpleItem(new ItemStack(Material.COOKED_RABBIT))));
		addNPCTrade(Profession.BUTCHER, new NPCTrade(14, 64, new SimpleItem(new ItemStack(Material.RABBIT_STEW))));
		
		addNPCTrade(Profession.BUTCHER, new NPCTrade(4, 256, new SimpleItem(new ItemStack(Material.BEEF))));
		addNPCTrade(Profession.BUTCHER, new NPCTrade(4, 256, new SimpleItem(new ItemStack(Material.PORKCHOP))));
		addNPCTrade(Profession.BUTCHER, new NPCTrade(3, 256, new SimpleItem(new ItemStack(Material.MUTTON))));
		addNPCTrade(Profession.BUTCHER, new NPCTrade(3, 256, new SimpleItem(new ItemStack(Material.CHICKEN))));
		addNPCTrade(Profession.BUTCHER, new NPCTrade(2, 256, new SimpleItem(new ItemStack(Material.RABBIT))));
		
		///////////////// Cartographer ///////////////////
				
		addNPCTrade(Profession.CARTOGRAPHER, new NPCTrade(35, 64, new SimpleItem(new ItemStack(Material.MAP))));
		addNPCTrade(Profession.CARTOGRAPHER, new NPCTrade(2, 32, new SimpleItem(new ItemStack(Material.CARTOGRAPHY_TABLE))));
		addNPCTrade(Profession.CARTOGRAPHER, new NPCTrade(15, 16, new SimpleItem(new ItemStack(Material.COMPASS))));
		addNPCTrade(Profession.CARTOGRAPHER, new NPCTrade(15, 16, new SimpleItem(new ItemStack(Material.CLOCK))));
		addNPCTrade(Profession.CARTOGRAPHER, new NPCTrade(20, 32, new SimpleItem(new ItemStack(Material.LEAD))));
		addNPCTrade(Profession.CARTOGRAPHER, new NPCTrade(1, 128, new SimpleItem(new ItemStack(Material.PAPER))));
		addNPCTrade(Profession.CARTOGRAPHER, new NPCTrade(3, 32, new SimpleItem(new ItemStack(Material.ITEM_FRAME))));
		addNPCTrade(Profession.CARTOGRAPHER, new NPCTrade(3, 32, new SimpleItem(new ItemStack(Material.PAINTING))));
		addNPCTrade(Profession.CARTOGRAPHER, new NPCTrade(3, 32, new SimpleItem(new ItemStack(Material.ARMOR_STAND))));
		addNPCTrade(Profession.CARTOGRAPHER, new NPCTrade(45, 32, new SimpleItem(new ItemStack(Material.BELL))));
		
		addNPCTrade(Profession.CARTOGRAPHER, new NPCTrade(14, 32, new SimpleItem(new ItemStack(Material.WHITE_BANNER))));
		addNPCTrade(Profession.CARTOGRAPHER, new NPCTrade(30, 32, new SimpleItem(new ItemStack(Material.GLOBE_BANNER_PATTERN))));
		addNPCTrade(Profession.CARTOGRAPHER, new NPCTrade(30, 32, new SimpleItem(new ItemStack(Material.CREEPER_BANNER_PATTERN))));
		addNPCTrade(Profession.CARTOGRAPHER, new NPCTrade(30, 32, new SimpleItem(new ItemStack(Material.FLOWER_BANNER_PATTERN))));
		addNPCTrade(Profession.CARTOGRAPHER, new NPCTrade(30, 32, new SimpleItem(new ItemStack(Material.MOJANG_BANNER_PATTERN))));
		addNPCTrade(Profession.CARTOGRAPHER, new NPCTrade(30, 32, new SimpleItem(new ItemStack(Material.SKULL_BANNER_PATTERN))));
		
		///////////////// Cleric ///////////////////
				
		addNPCTrade(Profession.CLERIC, new NPCTrade(65, 16, new SimpleItem(new ItemStack(Material.ENCHANTING_TABLE))));
		addNPCTrade(Profession.CLERIC, new NPCTrade(45, 16, new SimpleItem(new ItemStack(Material.BREWING_STAND))));
		
		addNPCTrade(Profession.CLERIC, new NPCTrade(8, 32, new SimpleItem(new ItemStack(Material.BOOK))));
		
		///////////////// Farmer ///////////////////
		
		addNPCTrade(Profession.FARMER, new NPCTrade(0.5, 32, new SimpleItem(new ItemStack(Material.WOODEN_HOE))));
		addNPCTrade(Profession.FARMER, new NPCTrade(1, 32, new SimpleItem(new ItemStack(Material.STONE_HOE))));
		addNPCTrade(Profession.FARMER, new NPCTrade(1.2, 16, new SimpleItem(ItemManager.getItemClone(ItemType.GRANITE_HOE))));
		addNPCTrade(Profession.FARMER, new NPCTrade(1.4, 16, new SimpleItem(ItemManager.getItemClone(ItemType.DIORITE_HOE))));
		addNPCTrade(Profession.FARMER, new NPCTrade(1.6, 16, new SimpleItem(ItemManager.getItemClone(ItemType.ANDESITE_HOE))));
		addNPCTrade(Profession.FARMER, new NPCTrade(12, 32, new SimpleItem(new ItemStack(Material.IRON_HOE))));
		addNPCTrade(Profession.FARMER, new NPCTrade(14, 32, new SimpleItem(new ItemStack(Material.GOLDEN_HOE))));
		addNPCTrade(Profession.FARMER, new NPCTrade(36, 32, new SimpleItem(new ItemStack(Material.DIAMOND_HOE))));
		
		addNPCTrade(Profession.FARMER, new NPCTrade(0.3, 256, new SimpleItem(new ItemStack(Material.BONE_MEAL))));
		
		addNPCTrade(Profession.FARMER, new NPCTrade(0.3, 84, new SimpleItem(new ItemStack(Material.WHEAT_SEEDS))));
		addNPCTrade(Profession.FARMER, new NPCTrade(2, 64, new SimpleItem(new ItemStack(Material.PUMPKIN_SEEDS))));
		addNPCTrade(Profession.FARMER, new NPCTrade(1.85, 64, new SimpleItem(new ItemStack(Material.MELON_SEEDS))));
		addNPCTrade(Profession.FARMER, new NPCTrade(2, 64, new SimpleItem(new ItemStack(Material.BEETROOT_SEEDS))));
		addNPCTrade(Profession.FARMER, new NPCTrade(2.3, 64, new SimpleItem(new ItemStack(Material.CARROT))));
		addNPCTrade(Profession.FARMER, new NPCTrade(1, 64, new SimpleItem(new ItemStack(Material.POTATO))));
		addNPCTrade(Profession.FARMER, new NPCTrade(1, 64, new SimpleItem(new ItemStack(Material.BEETROOT))));
		
		addNPCTrade(Profession.FARMER, new NPCTrade(0.5, 16, new SimpleItem(new ItemStack(Material.SUGAR_CANE))));
		addNPCTrade(Profession.FARMER, new NPCTrade(0.65, 16, new SimpleItem(new ItemStack(Material.BAMBOO))));
		addNPCTrade(Profession.FARMER, new NPCTrade(0.4, 16, new SimpleItem(new ItemStack(Material.KELP))));
		
		addNPCTrade(Profession.FARMER, new NPCTrade(2, 16, new SimpleItem(new ItemStack(Material.COCOA_BEANS))));
		
		addNPCTrade(Profession.FARMER, new NPCTrade(1, 84, new SimpleItem(new ItemStack(Material.EGG))));
		
		///////////////// Fisherman ///////////////////
				
		addNPCTrade(Profession.FISHERMAN, new NPCTrade(2, 48, new SimpleItem(new ItemStack(Material.FISHING_ROD))));
		addNPCTrade(Profession.FISHERMAN, new NPCTrade(2, 128, new SimpleItem(new ItemStack(Material.SPRUCE_BOAT))));
		addNPCTrade(Profession.FISHERMAN, new NPCTrade(12, 32, new SimpleItem(new ItemStack(Material.BUCKET))));
		
		addNPCTrade(Profession.FISHERMAN, new NPCTrade(4, 128, new SimpleItem(new ItemStack(Material.COOKED_COD))));
		addNPCTrade(Profession.FISHERMAN, new NPCTrade(6, 128, new SimpleItem(new ItemStack(Material.COOKED_SALMON))));
		addNPCTrade(Profession.FISHERMAN, new NPCTrade(3, 48, new SimpleItem(new ItemStack(Material.TROPICAL_FISH))));
		addNPCTrade(Profession.FISHERMAN, new NPCTrade(12, 48, new SimpleItem(new ItemStack(Material.PUFFERFISH))));
		
		addNPCTrade(Profession.FISHERMAN, new NPCTrade(0.2, 128, new SimpleItem(new ItemStack(Material.DRIED_KELP))));
		
		addNPCTrade(Profession.FISHERMAN, new NPCTrade(32, 1, new SimpleItem(new ItemStack(Material.TURTLE_EGG))));
		addNPCTrade(Profession.FISHERMAN, new NPCTrade(20, 8, new SimpleItem(new ItemStack(Material.SCUTE))));
		
		///////////////// Fletcher ///////////////////
				
		addNPCTrade(Profession.FLETCHER, new NPCTrade(1, 128, new SimpleItem(new ItemStack(Material.ARROW))));
		addNPCTrade(Profession.FLETCHER, new NPCTrade(3, 32, new SimpleItem(new ItemStack(Material.SPECTRAL_ARROW))));
		
		///////////////// Librarian ///////////////////
				
		addNPCTrade(Profession.LIBRARIAN, new NPCTrade(6, 256, new SimpleItem(new ItemStack(Material.BOOK))));
		addNPCTrade(Profession.LIBRARIAN, new NPCTrade(14, 8, new SimpleItem(new ItemStack(Material.LECTERN))));
		
		///////////////// Nitwit ///////////////////
				
		addNPCTrade(Profession.NITWIT, new NPCTrade(380, 2, new SimpleItem(new ItemStack(Material.GRASS_BLOCK))));
		addNPCTrade(Profession.NITWIT, new NPCTrade(680, 2, new SimpleItem(new ItemStack(Material.ELYTRA))));
		addNPCTrade(Profession.NITWIT, new NPCTrade(450, 2, new SimpleItem(new ItemStack(Material.TRIDENT))));
		
		///////////////// Shepherd ///////////////////
				
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(8, 16, new SimpleItem(new ItemStack(Material.SHEARS))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.LOOM))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(0.5, 48, new SimpleItem(new ItemStack(Material.WHITE_WOOL))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(2, 16, new SimpleItem(new ItemStack(Material.WHITE_BED))));
		
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.BLACK_DYE))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.BLUE_DYE))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.BROWN_DYE))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.CYAN_DYE))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.GRAY_DYE))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.GREEN_DYE))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.LIGHT_BLUE_DYE))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.LIGHT_GRAY_DYE))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.LIME_DYE))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.MAGENTA_DYE))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.ORANGE_DYE))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.PINK_DYE))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.PURPLE_DYE))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.RED_DYE))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.WHITE_DYE))));
		addNPCTrade(Profession.SHEPHERD, new NPCTrade(1, 8, new SimpleItem(new ItemStack(Material.YELLOW_DYE))));
		
		///////////////// Toolsmith ///////////////////
				
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(1, 16, new SimpleItem(new ItemStack(Material.WOODEN_AXE))));
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(1, 16, new SimpleItem(new ItemStack(Material.WOODEN_PICKAXE))));
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(0.3, 16, new SimpleItem(new ItemStack(Material.WOODEN_SHOVEL))));
		
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(1.2, 16, new SimpleItem(new ItemStack(Material.STONE_AXE))));
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(1.2, 16, new SimpleItem(new ItemStack(Material.STONE_PICKAXE))));
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(0.4, 16, new SimpleItem(new ItemStack(Material.STONE_SHOVEL))));
		
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(1.5, 8, new SimpleItem(ItemManager.getItemClone(ItemType.GRANITE_AXE))));
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(1.5, 8, new SimpleItem(ItemManager.getItemClone(ItemType.GRANITE_PICKAXE))));
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(0.5, 8, new SimpleItem(ItemManager.getItemClone(ItemType.GRANITE_SHOVEL))));
		
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(1.6, 8, new SimpleItem(ItemManager.getItemClone(ItemType.DIORITE_AXE))));
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(1.6, 8, new SimpleItem(ItemManager.getItemClone(ItemType.DIORITE_PICKAXE))));
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(0.55, 8, new SimpleItem(ItemManager.getItemClone(ItemType.DIORITE_SHOVEL))));
		
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(1.7, 8, new SimpleItem(ItemManager.getItemClone(ItemType.ANDESITE_AXE))));
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(1.7, 8, new SimpleItem(ItemManager.getItemClone(ItemType.ANDESITE_PICKAXE))));
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(0.6, 8, new SimpleItem(ItemManager.getItemClone(ItemType.ANDESITE_SHOVEL))));
		
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(16, 16, new SimpleItem(new ItemStack(Material.IRON_AXE))));
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(16, 16, new SimpleItem(new ItemStack(Material.IRON_PICKAXE))));
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(7, 16, new SimpleItem(new ItemStack(Material.IRON_SHOVEL))));
		
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(22, 16, new SimpleItem(new ItemStack(Material.GOLDEN_AXE))));
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(22, 16, new SimpleItem(new ItemStack(Material.GOLDEN_PICKAXE))));
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(8, 16, new SimpleItem(new ItemStack(Material.GOLDEN_SHOVEL))));
		
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(56, 16, new SimpleItem(new ItemStack(Material.DIAMOND_AXE))));
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(58, 16, new SimpleItem(new ItemStack(Material.DIAMOND_PICKAXE))));
		addNPCTrade(Profession.TOOLSMITH, new NPCTrade(20, 16, new SimpleItem(new ItemStack(Material.DIAMOND_SHOVEL))));
		
		///////////////// Weaponsmith ///////////////////
				
		addNPCTrade(Profession.WEAPONSMITH, new NPCTrade(0.5, 16, new SimpleItem(new ItemStack(Material.WOODEN_SWORD))));
		addNPCTrade(Profession.WEAPONSMITH, new NPCTrade(1, 16, new SimpleItem(new ItemStack(Material.STONE_SWORD))));
		addNPCTrade(Profession.WEAPONSMITH, new NPCTrade(1.2, 8, new SimpleItem(ItemManager.getItemClone(ItemType.GRANITE_SWORD))));
		addNPCTrade(Profession.WEAPONSMITH, new NPCTrade(1.4, 8, new SimpleItem(ItemManager.getItemClone(ItemType.DIORITE_SWORD))));
		addNPCTrade(Profession.WEAPONSMITH, new NPCTrade(1.6, 8, new SimpleItem(ItemManager.getItemClone(ItemType.ANDESITE_SWORD))));
		addNPCTrade(Profession.WEAPONSMITH, new NPCTrade(14, 16, new SimpleItem(new ItemStack(Material.IRON_SWORD))));
		addNPCTrade(Profession.WEAPONSMITH, new NPCTrade(16, 16, new SimpleItem(new ItemStack(Material.GOLDEN_SWORD))));
		addNPCTrade(Profession.WEAPONSMITH, new NPCTrade(40, 16, new SimpleItem(new ItemStack(Material.DIAMOND_SWORD))));
		
		///////////////// General ///////////////////
		
		addNPCTrade(Profession.NONE, new NPCTrade(0.5, 0, new SimpleItem(new ItemStack(Material.OAK_LOG))));
		addNPCTrade(Profession.NONE, new NPCTrade(0.5, 0, new SimpleItem(new ItemStack(Material.ACACIA_LOG))));
		addNPCTrade(Profession.NONE, new NPCTrade(0.5, 0, new SimpleItem(new ItemStack(Material.BIRCH_LOG))));
		addNPCTrade(Profession.NONE, new NPCTrade(0.5, 0, new SimpleItem(new ItemStack(Material.DARK_OAK_LOG))));
		addNPCTrade(Profession.NONE, new NPCTrade(0.5, 0, new SimpleItem(new ItemStack(Material.JUNGLE_LOG))));
		addNPCTrade(Profession.NONE, new NPCTrade(0.5, 0, new SimpleItem(new ItemStack(Material.SPRUCE_LOG))));
		addNPCTrade(Profession.NONE, new NPCTrade(0.25, 0, new SimpleItem(new ItemStack(Material.TORCH))));
		addNPCTrade(Profession.NONE, new NPCTrade(1, 0, new SimpleItem(new ItemStack(Material.FLINT))));
		addNPCTrade(Profession.NONE, new NPCTrade(1, 0, new SimpleItem(new ItemStack(Material.NAUTILUS_SHELL))));
		addNPCTrade(Profession.NONE, new NPCTrade(180, 0, new SimpleItem(new ItemStack(Material.SHULKER_SHELL))));
		addNPCTrade(Profession.NONE, new NPCTrade(350, 0, new SimpleItem(new ItemStack(Material.VILLAGER_SPAWN_EGG))));
		addNPCTrade(Profession.NONE, new NPCTrade(500, 0, new SimpleItem(new ItemStack(Material.BEDROCK))));
		addNPCTrade(Profession.NONE, new NPCTrade(100, 0, new SimpleItem(new ItemStack(Material.BEACON))));
		addNPCTrade(Profession.NONE, new NPCTrade(100, 0, new SimpleItem(new ItemStack(Material.CONDUIT))));
		
	}
	
	private static void addNPCTrade(Villager.Profession profession, NPCTrade trade) {
		List<NPCTrade> inventory = npcTrades.get(profession);
		if(inventory == null) {
			npcTrades.put(profession, new ArrayList<NPCTrade>());
			inventory = npcTrades.get(profession);
		}
		inventory.add(trade);
	}
	
	public static HashMap<Villager.Profession, List<NPCTrade>> getNPCTrades() {
		return npcTrades;
	}
	
}
