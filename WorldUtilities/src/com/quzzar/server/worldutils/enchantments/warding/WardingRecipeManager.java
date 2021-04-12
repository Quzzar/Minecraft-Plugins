package com.quzzar.server.worldutils.enchantments.warding;

import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;

public class WardingRecipeManager {
	
	private static ArrayList<WardingRecipe> enchantRecipes = new ArrayList<WardingRecipe>();
	
	public static void init() {
		
		////////////////////////////////////////////////////
		/////////////////// Basic Recipes //////////////////
		////////////////////////////////////////////////////
		
		buildBookRecipe(Enchantment.WATER_WORKER, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.HEART_OF_THE_SEA),
				new ItemStack(Material.QUARTZ_BLOCK));
		
		buildBookRecipe(Enchantment.PROTECTION_EXPLOSIONS, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.TNT),
				new ItemStack(Material.GLOWSTONE));
		
		buildBookRecipe(Enchantment.DEPTH_STRIDER, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.REDSTONE),
				new ItemStack(Material.SCUTE));
		
		buildBookRecipe(Enchantment.DIG_SPEED, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.EMERALD_BLOCK),
				new ItemStack(Material.QUARTZ));
		
		buildBookRecipe(Enchantment.PROTECTION_FALL, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.GLOWSTONE),
				new ItemStack(Material.PHANTOM_MEMBRANE));
		
		buildBookRecipe(Enchantment.FIRE_ASPECT, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.FIRE_CHARGE),
				new ItemStack(Material.IRON_INGOT));
		
		buildBookRecipe(Enchantment.PROTECTION_FIRE, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.FIRE_CHARGE),
				new ItemStack(Material.GLOWSTONE_DUST));
		
		buildBookRecipe(Enchantment.ARROW_FIRE, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.PRISMARINE_SHARD),
				new ItemStack(Material.FIRE_CHARGE));
		
		buildBookRecipe(Enchantment.LOOT_BONUS_BLOCKS, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.QUARTZ),
				new ItemStack(Material.RABBIT_FOOT));
		
		buildBookRecipe(Enchantment.IMPALING, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.SCUTE),
				new ItemStack(Material.DIAMOND));
		
		buildBookRecipe(Enchantment.KNOCKBACK, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.GOLD_INGOT),
				new ItemStack(Material.IRON_INGOT));
		
		buildBookRecipe(Enchantment.LOOT_BONUS_MOBS, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.RABBIT_FOOT),
				new ItemStack(Material.IRON_INGOT));
		
		buildBookRecipe(Enchantment.LUCK, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.RABBIT_FOOT),
				new ItemStack(Material.SCUTE));
		
		buildBookRecipe(Enchantment.LURE, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.EMERALD),
				new ItemStack(Material.SCUTE));
		
		buildBookRecipe(Enchantment.PIERCING, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.PRISMARINE_SHARD),
				new ItemStack(Material.ENDER_PEARL));
		
		buildBookRecipe(Enchantment.ARROW_DAMAGE, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.PRISMARINE_SHARD),
				new ItemStack(Material.EMERALD));
		
		buildBookRecipe(Enchantment.PROTECTION_PROJECTILE, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.GLOWSTONE_DUST),
				new ItemStack(Material.PRISMARINE_SHARD));
		
		buildBookRecipe(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.GLOWSTONE),
				new ItemStack(Material.GLOWSTONE));
		
		buildBookRecipe(Enchantment.ARROW_KNOCKBACK, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.PRISMARINE_SHARD),
				new ItemStack(Material.GOLD_INGOT));
		
		buildBookRecipe(Enchantment.QUICK_CHARGE, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.PRISMARINE_SHARD),
				new ItemStack(Material.REDSTONE_BLOCK));
		
		buildBookRecipe(Enchantment.OXYGEN, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.PUFFERFISH),
				new ItemStack(Material.SCUTE));
		
		buildBookRecipe(Enchantment.DAMAGE_ALL, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.IRON_BLOCK),
				new ItemStack(Material.DIAMOND));
		
		buildBookRecipe(Enchantment.DAMAGE_UNDEAD, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.BONE_BLOCK),
				new ItemStack(Material.DIAMOND));
		
		buildBookRecipe(Enchantment.DAMAGE_ARTHROPODS, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.COBWEB),
				new ItemStack(Material.DIAMOND));
		
		buildBookRecipe(Enchantment.SWEEPING_EDGE, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.ENDER_PEARL),
				new ItemStack(Material.IRON_INGOT));

		buildBookRecipe(Enchantment.THORNS, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.GLOWSTONE),
				new ItemStack(Material.DIAMOND));
		
		buildBookRecipe(Enchantment.DURABILITY, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.EMERALD_BLOCK));
		
		////// Wands //////
		
		enchantRecipes.add(new WardingRecipe(ItemManager.getItemClone(ItemType.WAND_OF_LESSER_FIREBALLS), false,
				new ItemStack(Material.FIRE_CHARGE),
				new ItemStack(Material.FIRE_CHARGE),
				new ItemStack(Material.TNT),
				new ItemStack(Material.STICK)));
		
		enchantRecipes.add(new WardingRecipe(ItemManager.getItemClone(ItemType.WAND_OF_FIREBALLS), false,
				new ItemStack(Material.FIRE_CHARGE),
				new ItemStack(Material.FIRE_CHARGE),
				ItemManager.getItemClone(ItemType.EXPLOSIVES_T1),
				new ItemStack(Material.STICK)));
		
		enchantRecipes.add(new WardingRecipe(ItemManager.getItemClone(ItemType.WAND_OF_GREATER_FIREBALLS), false,
				new ItemStack(Material.FIRE_CHARGE),
				new ItemStack(Material.FIRE_CHARGE),
				ItemManager.getItemClone(ItemType.EXPLOSIVES_T2),
				new ItemStack(Material.STICK)));
		
		enchantRecipes.add(new WardingRecipe(ItemManager.getItemClone(ItemType.WAND_OF_TOXIC_FUMES), false,
				new ItemStack(Material.PUFFERFISH),
				new ItemStack(Material.DIAMOND),
				new ItemStack(Material.BONE)));
		
		enchantRecipes.add(new WardingRecipe(ItemManager.getItemClone(ItemType.WAND_OF_LIGHTNING_BOLTS), false,
				new ItemStack(Material.PRISMARINE_CRYSTALS),
				new ItemStack(Material.REDSTONE),
				new ItemStack(Material.BLAZE_ROD)));
		
		////// Misc //////
		
		
		
		////////////////////////////////////////////////////
		///////////////// Advanced Recipes /////////////////
		////////////////////////////////////////////////////
		
		////// Enchantment Books //////
		
		buildBookRecipe(Enchantment.THORNS, 1, false, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_LAZULI),
				new ItemStack(Material.GLOWSTONE),
				new ItemStack(Material.DIAMOND));
		
		buildBookRecipe(Enchantment.SILK_TOUCH, 1, true, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_LAZULI),
				new ItemStack(Material.QUARTZ),
				ItemManager.getItemClone(ItemType.BOTTLE_OF_APEIRON));
		
		buildBookRecipe(Enchantment.RIPTIDE, 1, true, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_LAZULI),
				new ItemStack(Material.REDSTONE),
				new ItemStack(Material.HEART_OF_THE_SEA));
		
		buildBookRecipe(Enchantment.ARROW_KNOCKBACK, 1, true, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_LAZULI),
				new ItemStack(Material.PRISMARINE_SHARD),
				new ItemStack(Material.GOLD_INGOT));
		
		buildBookRecipe(Enchantment.PIERCING, 1, true, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_LAZULI),
				new ItemStack(Material.PRISMARINE_SHARD),
				new ItemStack(Material.ENDER_PEARL));
		
		buildBookRecipe(Enchantment.MULTISHOT, 1, true, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_LAZULI),
				new ItemStack(Material.EMERALD),
				ItemManager.getItemClone(ItemType.BOTTLE_OF_APEIRON));
		
		buildBookRecipe(Enchantment.LOYALTY, 1, true, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_BLOCK),
				new ItemStack(Material.HEART_OF_THE_SEA),
				new ItemStack(Material.ENDER_PEARL));
		
		buildBookRecipe(Enchantment.KNOCKBACK, 1, true, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_LAZULI),
				new ItemStack(Material.GOLD_INGOT),
				new ItemStack(Material.IRON_INGOT));
		
		buildBookRecipe(Enchantment.ARROW_INFINITE, 1, true, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_LAZULI),
				new ItemStack(Material.PRISMARINE_SHARD),
				ItemManager.getItemClone(ItemType.BOTTLE_OF_APEIRON));
		
		buildBookRecipe(Enchantment.FROST_WALKER, 1, true, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_LAZULI),
				new ItemStack(Material.ICE),
				new ItemStack(Material.REDSTONE));
		
		buildBookRecipe(Enchantment.ARROW_FIRE, 1, true, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_LAZULI),
				new ItemStack(Material.PRISMARINE_SHARD),
				new ItemStack(Material.FIRE_CHARGE));
		
		buildBookRecipe(Enchantment.FIRE_ASPECT, 1, true, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_LAZULI),
				new ItemStack(Material.FIRE_CHARGE),
				new ItemStack(Material.IRON_INGOT));
		
		buildBookRecipe(Enchantment.DEPTH_STRIDER, 1, true, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_LAZULI),
				new ItemStack(Material.REDSTONE),
				new ItemStack(Material.SCUTE));
		
		buildBookRecipe(Enchantment.WATER_WORKER, 1, true, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_LAZULI),
				new ItemStack(Material.HEART_OF_THE_SEA),
				new ItemStack(Material.QUARTZ));
		
		buildBookRecipe(Enchantment.CHANNELING, 1, true, 
				new ItemStack(Material.BOOK),
				new ItemStack(Material.LAPIS_LAZULI),
				new ItemStack(Material.BLAZE_ROD),
				new ItemStack(Material.HEART_OF_THE_SEA));
		
		////// Wands //////
		
		enchantRecipes.add(new WardingRecipe(ItemManager.getItemClone(ItemType.WAND_OF_TELEPORTATION), true,
				new ItemStack(Material.ENDER_PEARL),
				new ItemStack(Material.ENDER_PEARL),
				new ItemStack(Material.PRISMARINE_CRYSTALS),
				new ItemStack(Material.STICK)));
		
		enchantRecipes.add(new WardingRecipe(ItemManager.getItemClone(ItemType.WAND_OF_GRAND_FIREBALLS), true,
				new ItemStack(Material.FIRE_CHARGE),
				new ItemStack(Material.FIRE_CHARGE),
				ItemManager.getItemClone(ItemType.EXPLOSIVES_T3),
				new ItemStack(Material.STICK)));
		
		enchantRecipes.add(new WardingRecipe(ItemManager.getItemClone(ItemType.WAND_OF_POLYMORPH), true,
				new ItemStack(Material.PRISMARINE_CRYSTALS),
				ItemManager.getItemClone(ItemType.BOTTLE_OF_APEIRON),
				new ItemStack(Material.BAMBOO)));
		
		enchantRecipes.add(new WardingRecipe(ItemManager.getItemClone(ItemType.WAND_OF_DEADLY_FUMES), true,
				new ItemStack(Material.PUFFERFISH),
				new ItemStack(Material.PUFFERFISH),
				new ItemStack(Material.DIAMOND_BLOCK),
				new ItemStack(Material.BONE)));
		
		enchantRecipes.add(new WardingRecipe(ItemManager.getItemClone(ItemType.WAND_OF_GREATER_LIGHTNING_BOLTS), true,
				new ItemStack(Material.PRISMARINE_CRYSTALS),
				new ItemStack(Material.PRISMARINE_CRYSTALS),
				new ItemStack(Material.REDSTONE_BLOCK),
				new ItemStack(Material.BLAZE_ROD)));
		
		enchantRecipes.add(new WardingRecipe(ItemManager.getItemClone(ItemType.WAND_OF_FEASTING), true,
				new ItemStack(Material.CAKE),
				ItemManager.getItemClone(ItemType.BOTTLE_OF_APEIRON),
				new ItemStack(Material.STICK)));
		
		////// Misc //////
		
		enchantRecipes.add(new WardingRecipe(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE), true,
				new ItemStack(Material.GOLD_BLOCK),
				new ItemStack(Material.GOLD_BLOCK),
				new ItemStack(Material.GOLD_BLOCK),
				new ItemStack(Material.APPLE)));
		
		enchantRecipes.add(new WardingRecipe(ItemManager.getItemClone(ItemType.GRENZOS_ANGUISH), true,
				new ItemStack(Material.NETHER_STAR),
				new ItemStack(Material.FIRE_CHARGE),
				new ItemStack(Material.ROTTEN_FLESH),
				new ItemStack(Material.ROTTEN_FLESH)));
		
		enchantRecipes.add(new WardingRecipe(ItemManager.getItemClone(ItemType.TOLSIMIRIAN_RUNE), true,
				new ItemStack(Material.CLAY_BALL),
				new ItemStack(Material.PRISMARINE_CRYSTALS),
				new ItemStack(Material.HEART_OF_THE_SEA),
				new ItemStack(Material.GLOWSTONE)));
		
		enchantRecipes.add(new WardingRecipe(ItemManager.getItemClone(ItemType.SIVIAN_SCEPTER), true,
				ItemManager.getItemClone(ItemType.BOTTLE_OF_APEIRON),
				new ItemStack(Material.BLAZE_ROD),
				new ItemStack(Material.DIAMOND),
				new ItemStack(Material.DIAMOND)));
		
	}
	
	private static void buildBookRecipe(Enchantment enchantment, int lvl, boolean isAdvanced, ItemStack... inputIngredients) {
		ItemStack enchantBook = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta eMeta = (EnchantmentStorageMeta) enchantBook.getItemMeta();
		eMeta.addStoredEnchant(enchantment, lvl, false);
		enchantBook.setItemMeta(eMeta);
		enchantRecipes.add(new WardingRecipe(enchantBook, isAdvanced, inputIngredients));
	}
	
	public static ArrayList<WardingRecipe> getWardingRecipes() {
		return enchantRecipes;
	}
	
	public static WardingRecipe findEnchantmentRecipe(ItemStack... inputIngredients) {
		if(inputIngredients.length <= 1) {
			return null;
		}
		for(WardingRecipe enchantRec : enchantRecipes) {
			if(enchantRec.canForge(inputIngredients)) {
				return enchantRec;
			}
		}
		
		
		
		// Before just returning null, unable to find anything, see if it's an enlargement recipe...
		return EnchantmentEnlargement.getEnlargedRecipe(inputIngredients);
	}
	
	

}
