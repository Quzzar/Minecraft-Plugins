package com.quzzar.ge.core.buylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;

import com.destroystokyo.paper.MaterialTags;
import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;

public enum ItemTag {

	SWORDS(Arrays.asList(Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD,
			Material.DIAMOND_SWORD)),
	
	HELMETS(Arrays.asList(Material.LEATHER_HELMET, Material.CHAINMAIL_HELMET, Material.IRON_HELMET,
			Material.GOLDEN_HELMET, Material.DIAMOND_HELMET)),
	CHESTPLATES(Arrays.asList(Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.IRON_CHESTPLATE,
			Material.GOLDEN_CHESTPLATE, Material.DIAMOND_CHESTPLATE)),
	LEGGINGS(Arrays.asList(Material.LEATHER_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.IRON_LEGGINGS, 
			Material.GOLDEN_LEGGINGS, Material.DIAMOND_LEGGINGS)),
	BOOTS(Arrays.asList(Material.LEATHER_BOOTS, Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS, 
			Material.GOLDEN_BOOTS, Material.DIAMOND_BOOTS)),
	HORSE_ARMOR(Arrays.asList(Material.LEATHER_HORSE_ARMOR, Material.IRON_HORSE_ARMOR, Material.GOLDEN_HORSE_ARMOR, 
			Material.DIAMOND_HORSE_ARMOR)),
	MISC_ARMOR(Arrays.asList(Material.SHIELD, Material.TURTLE_HELMET)),
	
	RANGED_BASIC(Arrays.asList(Material.BOW, Material.CROSSBOW)),
	RANGED_ADVANCED(Arrays.asList(Material.BOW, Material.CROSSBOW, Material.TRIDENT)),
	
	ARROWS(Arrays.asList(Material.ARROW, Material.SPECTRAL_ARROW)),
	
	SHOVELS(Arrays.asList(Material.WOODEN_SHOVEL, Material.STONE_SHOVEL, Material.IRON_SHOVEL,
			Material.GOLDEN_SHOVEL, Material.DIAMOND_SHOVEL)),
	AXES(Arrays.asList(Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE,
			Material.GOLDEN_AXE, Material.DIAMOND_AXE)),
	PICKAXES(Arrays.asList(Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE,
			Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE)),
	HOES(Arrays.asList(Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE,
			Material.GOLDEN_HOE, Material.DIAMOND_HOE)),
	MISC_TOOLS(Arrays.asList(Material.FLINT_AND_STEEL, Material.COMPASS, Material.CLOCK, Material.FISHING_ROD,
			Material.LEAD, Material.SHEARS, Material.BUCKET, Material.MILK_BUCKET, Material.WATER_BUCKET,
			Material.LAVA_BUCKET, Material.MAP, Material.WRITABLE_BOOK, Material.ENDER_PEARL)),
	
	TRANSPORTATION_TOOLS(Arrays.asList(Material.SADDLE, Material.ELYTRA, Material.CARROT_ON_A_STICK)),
	
	ARMOR_LEATHER(Arrays.asList(Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS)),
	ARMOR_CHAINMAIL(Arrays.asList(Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS,
			Material.CHAINMAIL_BOOTS)),
	ARMOR_IRON(Arrays.asList(Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS)),
	ARMOR_GOLDEN(Arrays.asList(Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS)),
	ARMOR_DIAMOND(Arrays.asList(Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS)),
	
	WORK_TABLES_BASIC(Arrays.asList(Material.CRAFTING_TABLE, Material.FURNACE, Material.ENCHANTING_TABLE,
			Material.ANVIL, Material.GRINDSTONE, Material.SMOKER, Material.BLAST_FURNACE, 
			Material.STONECUTTER, Material.LOOM, Material.CARTOGRAPHY_TABLE, Material.LECTERN,
			Material.BREWING_STAND, Material.CAULDRON)),
	WORK_TABLES_ADVANCED(Arrays.asList(Material.CRAFTING_TABLE, Material.FURNACE, Material.ENCHANTING_TABLE,
			Material.ANVIL, Material.GRINDSTONE, Material.SMOKER, Material.BLAST_FURNACE, 
			Material.STONECUTTER, Material.LOOM, Material.CARTOGRAPHY_TABLE, Material.JUKEBOX,
			Material.CAMPFIRE, Material.SCAFFOLDING, Material.LECTERN, Material.BREWING_STAND,
			Material.CAULDRON, Material.COMPOSTER, Material.BEACON, Material.CONDUIT)),
	
	WARDING_SUPPLIES(Arrays.asList(Material.BOOK, Material.BLAZE_ROD, Material.STICK, Material.BONE,
			Material.LAPIS_BLOCK, Material.GLOWSTONE, Material.EMERALD_BLOCK, Material.QUARTZ_BLOCK, Material.IRON_BLOCK,
			Material.GOLD_BLOCK, Material.DIAMOND_BLOCK, Material.REDSTONE_BLOCK, Material.FIRE_CHARGE, Material.RABBIT_FOOT,
			Material.PRISMARINE_SHARD, Material.PRISMARINE_CRYSTALS, Material.SCUTE, Material.HEART_OF_THE_SEA, Material.ENDER_PEARL)),
	ALCHEMY_SUPPLIES(Arrays.asList(Material.GLASS_BOTTLE, Material.GLOWSTONE_DUST, Material.REDSTONE, Material.BLAZE_POWDER,
			Material.MAGMA_CREAM, Material.FERMENTED_SPIDER_EYE, Material.GHAST_TEAR, Material.NETHER_WART, Material.POISONOUS_POTATO,
			Material.GLISTERING_MELON_SLICE, Material.GOLDEN_CARROT, Material.RABBIT_FOOT, Material.PHANTOM_MEMBRANE)),
	
	FARMING_SUPPLIES(Arrays.asList(Material.BONE_MEAL, Material.WHEAT_SEEDS, Material.PUMPKIN_SEEDS, Material.MELON_SEEDS,
			Material.BEETROOT_SEEDS, Material.CARROT, Material.POTATO, Material.SUGAR_CANE, Material.BAMBOO, Material.KELP,
			Material.COCOA_BEANS, Material.NETHER_WART, Material.EGG, Material.TURTLE_EGG)),
	CRAFTING_SUPPLIES(Arrays.asList(Material.LEATHER, Material.IRON_INGOT, Material.GOLD_INGOT, Material.DIAMOND,
			Material.FLINT, Material.STICK, Material.RABBIT_HIDE, Material.NAUTILUS_SHELL, Material.REDSTONE,
			Material.GUNPOWDER, Material.LAPIS_LAZULI, Material.QUARTZ, Material.STRING, Material.FEATHER,
			Material.BOOK, Material.PAPER, Material.SLIME_BALL, Material.BONE, Material.SCUTE, Material.BLAZE_ROD,
			Material.NETHER_BRICK, Material.BRICK, Material.SNOWBALL)),
	
	BUTTONS(new ArrayList<Material>(Tag.BUTTONS.getValues())),
	DOORS(new ArrayList<Material>(Tag.DOORS.getValues())),
	PRESSURE_PLATES(Arrays.asList(Material.OAK_PRESSURE_PLATE, Material.SPRUCE_PRESSURE_PLATE, Material.BIRCH_PRESSURE_PLATE,
			Material.JUNGLE_PRESSURE_PLATE, Material.ACACIA_PRESSURE_PLATE, Material.DARK_OAK_PRESSURE_PLATE,
			Material.STONE_PRESSURE_PLATE, Material.LIGHT_WEIGHTED_PRESSURE_PLATE, Material.HEAVY_WEIGHTED_PRESSURE_PLATE)),
	RAILS(new ArrayList<Material>(Tag.RAILS.getValues())),
	REDSTONE(Arrays.asList(Material.REDSTONE, Material.REDSTONE_LAMP, Material.REDSTONE_TORCH, Material.REPEATER,
			Material.COMPARATOR, Material.OBSERVER, Material.DROPPER, Material.HOPPER, Material.DAYLIGHT_DETECTOR,
			Material.DISPENSER, Material.NOTE_BLOCK, Material.TRAPPED_CHEST, Material.TRIPWIRE_HOOK, Material.PISTON,
			Material.STICKY_PISTON, Material.LEVER)),
	
	MUSIC_DISCS(new ArrayList<Material>(Tag.ITEMS_MUSIC_DISCS.getValues())),
	
	DYES(new ArrayList<Material>(MaterialTags.DYES.getValues())),
	
	BOATS(new ArrayList<Material>(Tag.ITEMS_BOATS.getValues())),
	MINECARTS(Arrays.asList(Material.MINECART, Material.CHEST_MINECART, Material.FURNACE_MINECART, 
			Material.HOPPER_MINECART, Material.TNT_MINECART)),
	
	BURNABLES(new ArrayList<Material>(MaterialTags.COALS.getValues())),
	
	FISHES(new ArrayList<Material>(Tag.ITEMS_FISHES.getValues())),
	
	DECORATIONS(Arrays.asList(Material.PAINTING, Material.ITEM_FRAME, Material.FLOWER_POT, Material.ARMOR_STAND, Material.BELL,
			Material.LANTERN, Material.BOOKSHELF, Material.HAY_BLOCK, Material.SEA_LANTERN, Material.END_ROD)),
	
	STORAGE(Arrays.asList(Material.CHEST, Material.BARREL, Material.ENDER_CHEST, Material.TRAPPED_CHEST)),
	
	PREPARED_FOODS(Arrays.asList(Material.BREAD, Material.BAKED_POTATO, Material.DRIED_KELP, Material.COOKED_PORKCHOP, Material.COOKED_BEEF,
				Material.COOKED_MUTTON, Material.COOKED_CHICKEN, Material.COOKED_RABBIT, Material.COOKED_COD, Material.COOKED_SALMON,
				Material.MUSHROOM_STEW, Material.RABBIT_STEW, Material.BEETROOT_SOUP, Material.SUSPICIOUS_STEW, Material.MELON_SLICE,
				Material.APPLE, Material.GOLDEN_APPLE, Material.CARROT, Material.GOLDEN_CARROT, Material.CHORUS_FRUIT,
				Material.COOKIE, Material.CAKE, Material.PUMPKIN_PIE, Material.SWEET_BERRIES)),
	RAW_FOODS(Arrays.asList(Material.PORKCHOP, Material.BEEF, Material.CHICKEN, Material.RABBIT, Material.MUTTON,
			Material.COD, Material.SALMON, Material.TROPICAL_FISH, Material.PUFFERFISH, Material.KELP, 
			Material.BROWN_MUSHROOM, Material.RED_MUSHROOM, Material.BEETROOT, Material.POTATO)),
	
	LEAVES(new ArrayList<Material>(Tag.LEAVES.getValues())),
	SAPLINGS(new ArrayList<Material>(Tag.SAPLINGS.getValues())),
	FLOWERS(new ArrayList<Material>(Tag.SMALL_FLOWERS.getValues())),
	
	LOGS(new ArrayList<Material>(Tag.LOGS.getValues())),
	PLANKS(new ArrayList<Material>(Tag.PLANKS.getValues())),
	
	SLABS(new ArrayList<Material>(Tag.SLABS.getValues())),
	STONE_BRICKS(new ArrayList<Material>(Tag.STONE_BRICKS.getValues())),
	
	STONE_LIKE(Arrays.asList(Material.COBBLESTONE, Material.MOSSY_COBBLESTONE, Material.STONE, Material.SMOOTH_STONE,
			Material.GRANITE, Material.POLISHED_GRANITE, Material.DIORITE, Material.POLISHED_DIORITE, Material.ANDESITE,
			Material.POLISHED_ANDESITE, Material.SANDSTONE, Material.CHISELED_SANDSTONE, Material.CUT_SANDSTONE, Material.SMOOTH_SANDSTONE,
			Material.RED_SANDSTONE, Material.CHISELED_RED_SANDSTONE, Material.CUT_RED_SANDSTONE, Material.SMOOTH_RED_SANDSTONE,
			Material.END_STONE, Material.END_STONE_BRICKS)),
	
	GLASS(new ArrayList<Material>(MaterialTags.GLASS.getValues())),
	
	TERRACOTTA_ALL(new ArrayList<Material>(MaterialTags.STAINED_TERRACOTTA.add(MaterialTags.GLAZED_TERRACOTTA).getValues())),
	
	CONCRETE_POWDERS(new ArrayList<Material>(MaterialTags.CONCRETE_POWDER.getValues())),
	CONCRETES(new ArrayList<Material>(MaterialTags.CONCRETES.getValues())),
	
	DIRTS(new ArrayList<Material>(Tag.DIRT_LIKE.getValues())),
	
	MISC_BLOCKS(Arrays.asList(Material.BRICKS, Material.NETHER_BRICKS, Material.CLAY, Material.MAGMA_BLOCK, Material.OBSIDIAN,
				Material.GLOWSTONE, Material.QUARTZ_BLOCK, Material.ICE, 
				Material.PACKED_ICE, Material.BLUE_ICE, Material.COAL_BLOCK, Material.REDSTONE_BLOCK,
				Material.IRON_BLOCK, Material.GOLD_BLOCK, Material.DIAMOND_BLOCK, Material.EMERALD_BLOCK, Material.LAPIS_BLOCK,
				Material.BONE_BLOCK, Material.SLIME_BLOCK, Material.SAND, Material.RED_SAND, Material.GRAVEL, 
				Material.PRISMARINE, Material.PRISMARINE_BRICKS, Material.DARK_PRISMARINE,
				Material.DRIED_KELP_BLOCK, Material.SPONGE, Material.PURPUR_BLOCK)),
	
	FENCES(new ArrayList<Material>(Tag.FENCES.getValues())),
	WALLS(new ArrayList<Material>(Tag.WALLS.getValues())),
	SIGNS(new ArrayList<Material>(Tag.SIGNS.getValues())),
	
	SAND(new ArrayList<Material>(Tag.SAND.getValues())),
	
	STAIRS(new ArrayList<Material>(Tag.STAIRS.getValues())),
	
	BEDS(new ArrayList<Material>(Tag.BEDS.getValues())),
	CARPETS(new ArrayList<Material>(Tag.CARPETS.getValues())),
	WOOL(new ArrayList<Material>(Tag.WOOL.getValues()));
	
	// Leathergoods?
	
	private List<SimpleItem> list;
	
	private ItemTag(List<Material> mList) {
		this.list = new ArrayList<SimpleItem>();
		for(Material m : mList) {
			this.list.add(new SimpleItem(new ItemStack(m)));
		}
	}
	
	private void addToTag(SimpleItem item) {
		list.add(item);
	}
	
	public List<SimpleItem> getList() {
		return list;
	}
	
	// Static occurs after enums are set
	static {
		
		ItemTag.SWORDS.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.ANDESITE_SWORD)));
		ItemTag.SWORDS.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.DIORITE_SWORD)));
		ItemTag.SWORDS.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.GRANITE_SWORD)));
		
		ItemTag.PICKAXES.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.ANDESITE_PICKAXE)));
		ItemTag.PICKAXES.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.DIORITE_PICKAXE)));
		ItemTag.PICKAXES.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.GRANITE_PICKAXE)));
		
		ItemTag.AXES.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.ANDESITE_AXE)));
		ItemTag.AXES.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.DIORITE_AXE)));
		ItemTag.AXES.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.GRANITE_AXE)));
		
		ItemTag.SHOVELS.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.ANDESITE_SHOVEL)));
		ItemTag.SHOVELS.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.DIORITE_SHOVEL)));
		ItemTag.SHOVELS.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.GRANITE_SHOVEL)));
		
		ItemTag.HOES.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.ANDESITE_HOE)));
		ItemTag.HOES.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.DIORITE_HOE)));
		ItemTag.HOES.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.GRANITE_HOE)));
		
		ItemTag.MISC_TOOLS.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.EXPLOSIVES_T1)));
		ItemTag.MISC_TOOLS.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.EXPLOSIVES_T2)));
		ItemTag.MISC_TOOLS.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.EXPLOSIVES_T3)));
		ItemTag.MISC_TOOLS.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.EXPLOSIVES_TX)));
		
		ItemTag.MISC_TOOLS.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.UNREAD_RECIPE)));
		
		ItemTag.WARDING_SUPPLIES.addToTag(new SimpleItem(ItemManager.getItemClone(ItemType.BOTTLE_OF_APEIRON)));
		
	}
	
}
