package com.quzzar.server.moreitems.items;

import java.util.Arrays;
import java.util.HashMap;
import java.util.SplittableRandom;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.betterrecipes.BetterRecipesManager;
import com.quzzar.betterrecipes.recipes.BetterShapedRecipe;
import com.quzzar.betterrecipes.recipes.BetterShapelessRecipe;
import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.ItemDataTag;
import com.quzzar.server.moreitems.MoreItems;
import com.quzzar.server.moreitems.heads.HeadUtil;
import com.quzzar.server.moreitems.heads.TextureDatabase;
import com.quzzar.server.moreitems.items.special.ancientrecipe.RecipeItemListener;
import com.quzzar.server.worldutils.enchantments.warding.WardingRecipe;
import com.quzzar.server.worldutils.enchantments.warding.WardingRecipeManager;

public class ItemManager {
	
	private static HashMap<ItemType, ItemStack> itemMap = new HashMap<ItemType, ItemStack>();

	public static void initialize(MoreItems main) {
		
		itemMap.put(ItemType.PORTABLE_CRAFTING_TABLE, Portable_Crafting_Table(main));

		itemMap.put(ItemType.GRENZOS_ANGUISH, Grenzos_Anguish(main));
		itemMap.put(ItemType.TOLSIMIRIAN_RUNE, Tolsimirian_Rune(main));
		itemMap.put(ItemType.SIVIAN_SCEPTER, Sivian_Scepter(main));

		itemMap.put(ItemType.GRANITE_PICKAXE, Granite_Pickaxe(main));
		itemMap.put(ItemType.GRANITE_SHOVEL, Granite_Shovel(main));
		itemMap.put(ItemType.GRANITE_AXE, Granite_Axe(main));
		itemMap.put(ItemType.GRANITE_SWORD, Granite_Sword(main));
		itemMap.put(ItemType.GRANITE_HOE, Granite_Hoe(main));

		itemMap.put(ItemType.ANDESITE_PICKAXE, Andesite_Pickaxe(main));
		itemMap.put(ItemType.ANDESITE_SHOVEL, Andesite_Shovel(main));
		itemMap.put(ItemType.ANDESITE_AXE, Andesite_Axe(main));
		itemMap.put(ItemType.ANDESITE_SWORD, Andesite_Sword(main));
		itemMap.put(ItemType.ANDESITE_HOE, Andesite_Hoe(main));

		itemMap.put(ItemType.DIORITE_PICKAXE, Diorite_Pickaxe(main));
		itemMap.put(ItemType.DIORITE_SHOVEL, Diorite_Shovel(main));
		itemMap.put(ItemType.DIORITE_AXE, Diorite_Axe(main));
		itemMap.put(ItemType.DIORITE_SWORD, Diorite_Sword(main));
		itemMap.put(ItemType.DIORITE_HOE, Diorite_Hoe(main));

		itemMap.put(ItemType.WAND_OF_TELEPORTATION, Wand_of_Teleportation(main));
		itemMap.put(ItemType.WAND_OF_LAST_DEATH, Wand_of_Last_Death(main));
		itemMap.put(ItemType.WAND_OF_WARPING, Wand_of_Warping(main));
		itemMap.put(ItemType.WAND_OF_LESSER_FIREBALLS, Wand_of_Lesser_Fireballs(main));
		itemMap.put(ItemType.WAND_OF_FIREBALLS, Wand_of_Fireballs(main));
		itemMap.put(ItemType.WAND_OF_GREATER_FIREBALLS, Wand_of_Greater_Fireballs(main));
		itemMap.put(ItemType.WAND_OF_GRAND_FIREBALLS, Wand_of_Grand_Fireballs(main));
		itemMap.put(ItemType.WAND_OF_POLYMORPH, Wand_of_Polymorph(main));
		itemMap.put(ItemType.WAND_OF_TOXIC_FUMES, Wand_of_Toxic_Fumes(main));
		itemMap.put(ItemType.WAND_OF_DEADLY_FUMES, Wand_of_Deadly_Fumes(main));
		itemMap.put(ItemType.WAND_OF_LIGHTNING_BOLTS, Wand_of_Lightning_Bolts(main));
		itemMap.put(ItemType.WAND_OF_GREATER_LIGHTNING_BOLTS, Wand_of_Greater_Lightning_Bolts(main));
		itemMap.put(ItemType.WAND_OF_FEASTING, Wand_of_Feasting(main));
		itemMap.put(ItemType.WAND_OF_HEALING, Wand_of_Healing(main));
		
		itemMap.put(ItemType.SCROLL_OF_RETURNING, Scroll_of_Returning(main));

		itemMap.put(ItemType.SECURITY_BATON, Security_Baton(main));
		
		itemMap.put(ItemType.GRAPPLING_HOOK, Grappling_Hook(main));
		itemMap.put(ItemType.GRAPPLING_HOOK_INFINITE, Grappling_Hook_Infinite(main));
		
		itemMap.put(ItemType.BOTTLE_OF_APEIRON, Bottle_of_Apeiron(main));
		
		itemMap.put(ItemType.DROP_OF_SOUL, Drop_Of_Soul(main));
		itemMap.put(ItemType.MOLTEN_MESH, Molten_Mesh(main));
		itemMap.put(ItemType.MOLTEN_CORE, Molten_Core(main));

		itemMap.put(ItemType.EXPLOSIVES_T1, Explosives_T1(main));
		itemMap.put(ItemType.EXPLOSIVES_T2, Explosives_T2(main));
		itemMap.put(ItemType.EXPLOSIVES_T3, Explosives_T3(main));
		itemMap.put(ItemType.EXPLOSIVES_TX, Explosives_TX(main));
		
		itemMap.put(ItemType.POWERED_ELYTRA, Powered_Elytra(main));
		itemMap.put(ItemType.ADVANCED_ELYTRA, Advanced_Elytra(main));
		
		itemMap.put(ItemType.ADVANCED_BOAT, Advanced_Boat(main));
		
		itemMap.put(ItemType.UNREAD_RECIPE, Unread_Recipe(main));
		
		itemMap.put(ItemType.PLAYER_GUIDE, Player_Guide(main));
		
		
	}

	public static HashMap<ItemType, ItemStack> getItemMap() {
		return itemMap;
	}

	public static ItemStack getItemClone(ItemType itemType) {
		return ((ItemStack) itemMap.get(itemType)).clone();
	}

	public static boolean isSimilar(ItemStack checkingItem, ItemType itemType) {
		return ((ItemStack) itemMap.get(itemType)).isSimilar(checkingItem);
	}
	
	///////////////////////////////////////////////////////////////////////////////
	// All the item functions (also adds recipe to craft them). Called only once //
	///////////////////////////////////////////////////////////////////////////////
	
	private static ItemStack Security_Baton(MoreItems main){
		
		ItemStack i = new ItemStack(Material.STICK);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+"Security Baton");
	    
	    im.addEnchant(Enchantment.KNOCKBACK, 1, false);
	    
	    im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    
	    i.setItemMeta(im);
	    
		ShapelessRecipe shR = new ShapelessRecipe(new NamespacedKey(main,"SecurityBaton"),i);
		shR.addIngredient(Material.STICK);
		shR.addIngredient(Material.LAPIS_LAZULI);
		shR.addIngredient(Material.REDSTONE);
		shR.addIngredient(Material.GOLD_INGOT);
	    main.getServer().addRecipe(shR);
	    
	    return i;
	}

	private static ItemStack Drop_Of_Soul(MoreItems main) {
		ItemStack i = new ItemStack(Material.GHAST_TEAR);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.DARK_PURPLE + "Drop of Soul");

		im.addEnchant(Enchantment.OXYGEN, 1, false);
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		
		i.setItemMeta(im);

		return i;
	}

	private static ItemStack Molten_Core(MoreItems main) {
		ItemStack i = HeadUtil.makeRawItemStack(TextureDatabase.MOLTEN_CORE, 1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Molten Core");

		im.addEnchant(Enchantment.FIRE_ASPECT, 1, false);
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		BetterShapedRecipe shR = new BetterShapedRecipe("MoltenCore", i);
		shR.setShape(new String[] { "OMO", "MSM", "OMO" });
		shR.setIngredient('M', getItemClone(ItemType.MOLTEN_MESH));
		shR.setIngredient('O', new ItemStack(Material.OBSIDIAN));
		shR.setIngredient('S', new ItemStack(Material.NETHER_STAR));
		BetterRecipesManager.addBetterRecipe(shR);

		return i;
	}

	private static ItemStack Molten_Mesh(MoreItems main) {
		ItemStack i = new ItemStack(Material.ORANGE_DYE, 1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.YELLOW + "Molten Mesh");

		im.addEnchant(Enchantment.DURABILITY, 1, false);
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "MoltenMesh"), i);
		shR.shape(new String[] { "RLR", "LBL", "RLR" });
		shR.setIngredient('L', Material.LAVA_BUCKET);
		shR.setIngredient('B', Material.BLAZE_POWDER);
		shR.setIngredient('R', Material.REDSTONE);
		main.getServer().addRecipe(shR);

		return i;
	}

	private static ItemStack Portable_Crafting_Table(MoreItems main) {
		ItemStack i = HeadUtil.makeRawItemStack(TextureDatabase.CRAFTING_TABLE, 1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Portable Crafting Table");
		i.setItemMeta(im);

		BetterShapelessRecipe shR = new BetterShapelessRecipe("PortableCraftingTablee", i);
		shR.addIngredient(new ItemStack(Material.CRAFTING_TABLE));
		BetterRecipesManager.addBetterRecipe(shR);

		return i;
	}

	private static ItemStack Grenzos_Anguish(MoreItems main) {
		ItemStack i = new ItemStack(Material.FIRE_CHARGE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.MAGIC + "Grenzo's Anguish");

		im.addEnchant(Enchantment.FIRE_ASPECT, 1, false);
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}

	private static ItemStack Tolsimirian_Rune(MoreItems main) {
		ItemStack i = new ItemStack(Material.FIREWORK_STAR);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.STRIKETHROUGH + "Tolsimirian Rune");

		im.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 4, false);
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}

	private static ItemStack Sivian_Scepter(MoreItems main) {
		ItemStack i = new ItemStack(Material.BLAZE_ROD);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.AQUA + "" + ChatColor.ITALIC + "Sivian Scepter");

		im.addEnchant(Enchantment.SILK_TOUCH, 1, false);
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}

	private static ItemStack Explosives_T1(MoreItems main) {
		ItemStack i = new ItemStack(Material.FIRE_CHARGE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Explosives (T1)");

		i.setItemMeta(im);

		BetterShapedRecipe shR = new BetterShapedRecipe("ExplosivesT1", i);
		shR.setShape(new String[] { "TIT", "IMI", "TIT" });
		shR.setIngredient('M', getItemClone(ItemType.MOLTEN_MESH));
		shR.setIngredient('I', new ItemStack(Material.IRON_INGOT));
		shR.setIngredient('T', new ItemStack(Material.TNT));
		BetterRecipesManager.addBetterRecipe(shR);

		return i;
	}

	private static ItemStack Explosives_T2(MoreItems main) {
		ItemStack i = new ItemStack(Material.FIRE_CHARGE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Explosives " + ChatColor.BOLD + "(T2)");

		i.setItemMeta(im);

		BetterShapedRecipe shR = new BetterShapedRecipe("ExplosivesT2", i);
		shR.setShape(new String[] { "TMT", "MIM", "TMT" });
		shR.setIngredient('M', getItemClone(ItemType.MOLTEN_MESH));
		shR.setIngredient('I', new ItemStack(Material.IRON_BLOCK));
		shR.setIngredient('T', new ItemStack(Material.TNT));
		BetterRecipesManager.addBetterRecipe(shR);

		return i;
	}

	private static ItemStack Explosives_T3(MoreItems main) {
		ItemStack i = new ItemStack(Material.FIRE_CHARGE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Explosives (T3)");

		i.setItemMeta(im);

		BetterShapedRecipe shR = new BetterShapedRecipe("ExplosivesT3", i);
		shR.setShape(new String[] { "TMT", "MmM", "TMT" });
		shR.setIngredient('M', getItemClone(ItemType.MOLTEN_MESH));
		shR.setIngredient('m', getItemClone(ItemType.MOLTEN_CORE));
		shR.setIngredient('T', new ItemStack(Material.TNT));
		BetterRecipesManager.addBetterRecipe(shR);

		return i;
	}

	private static ItemStack Explosives_TX(MoreItems main) {
		ItemStack i = new ItemStack(Material.FIRE_CHARGE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Explosives (TX)");

		i.setItemMeta(im);

		BetterShapedRecipe shR = new BetterShapedRecipe("ExplosivesTX", i);
		shR.setShape(new String[] { "TmT", "mCm", "TmT" });
		shR.setIngredient('C', new ItemStack(Material.END_CRYSTAL));
		shR.setIngredient('m', getItemClone(ItemType.MOLTEN_CORE));
		shR.setIngredient('T', new ItemStack(Material.TNT));
		BetterRecipesManager.addBetterRecipe(shR);

		return i;
	}

	private static ItemStack Granite_Pickaxe(MoreItems main) {
		ItemStack i = new ItemStack(Material.STONE_PICKAXE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Granite Pickaxe");

		i.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "Pickk1"), i);
		shR.shape(new String[] { "RRR", " S ", " S " });
		shR.setIngredient('R', Material.GRANITE);
		shR.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR);

		return i;
	}

	private static ItemStack Granite_Shovel(MoreItems main) {
		ItemStack i = new ItemStack(Material.STONE_SHOVEL);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Granite Shovel");

		i.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "Shovel1"), i);
		shR.shape(new String[] { "R", "S", "S" });
		shR.setIngredient('R', Material.GRANITE);
		shR.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR);

		return i;
	}

	private static ItemStack Granite_Axe(MoreItems main) {
		ItemStack i = new ItemStack(Material.STONE_AXE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Granite Axe");

		i.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "Axe1"), i);
		shR.shape(new String[] { "RR", "RS", " S" });
		shR.setIngredient('R', Material.GRANITE);
		shR.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR);

		ShapedRecipe shR2 = new ShapedRecipe(new NamespacedKey(main, "Axxe1"), i);
		shR2.shape(new String[] { "RR", "SR", "S " });
		shR2.setIngredient('R', Material.GRANITE);
		shR2.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR2);

		return i;
	}

	private static ItemStack Granite_Hoe(MoreItems main) {
		ItemStack i = new ItemStack(Material.STONE_HOE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Granite Hoe");

		i.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "Hoe1"), i);
		shR.shape(new String[] { "RR", " S", " S" });
		shR.setIngredient('R', Material.GRANITE);
		shR.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR);

		ShapedRecipe shR2 = new ShapedRecipe(new NamespacedKey(main, "Hooe1"), i);
		shR2.shape(new String[] { "RR", "S ", "S " });
		shR2.setIngredient('R', Material.GRANITE);
		shR2.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR2);

		return i;
	}

	private static ItemStack Granite_Sword(MoreItems main) {
		ItemStack i = new ItemStack(Material.STONE_SWORD);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Granite Sword");

		i.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "Sword1"), i);
		shR.shape(new String[] { "R", "R", "S" });
		shR.setIngredient('R', Material.GRANITE);
		shR.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR);

		return i;
	}

	private static ItemStack Andesite_Pickaxe(MoreItems main) {
		ItemStack i = new ItemStack(Material.STONE_PICKAXE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Andesite Pickaxe");

		i.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "Pickk2"), i);
		shR.shape(new String[] { "RRR", " S ", " S " });
		shR.setIngredient('R', Material.ANDESITE);
		shR.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR);

		return i;
	}

	private static ItemStack Andesite_Shovel(MoreItems main) {
		ItemStack i = new ItemStack(Material.STONE_SHOVEL);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Andesite Shovel");

		i.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "Shovel2"), i);
		shR.shape(new String[] { "R", "S", "S" });
		shR.setIngredient('R', Material.ANDESITE);
		shR.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR);

		return i;
	}

	private static ItemStack Andesite_Axe(MoreItems main) {
		ItemStack i = new ItemStack(Material.STONE_AXE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Andesite Axe");

		i.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "Axe2"), i);
		shR.shape(new String[] { "RR", "RS", " S" });
		shR.setIngredient('R', Material.ANDESITE);
		shR.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR);

		ShapedRecipe shR2 = new ShapedRecipe(new NamespacedKey(main, "Axxe2"), i);
		shR2.shape(new String[] { "RR", "SR", "S " });
		shR2.setIngredient('R', Material.ANDESITE);
		shR2.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR2);

		return i;
	}

	private static ItemStack Andesite_Hoe(MoreItems main) {
		ItemStack i = new ItemStack(Material.STONE_HOE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Andesite Hoe");

		i.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "Hoe2"), i);
		shR.shape(new String[] { "RR", " S", " S" });
		shR.setIngredient('R', Material.ANDESITE);
		shR.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR);

		ShapedRecipe shR2 = new ShapedRecipe(new NamespacedKey(main, "Hooe2"), i);
		shR2.shape(new String[] { "RR", "S ", "S " });
		shR2.setIngredient('R', Material.ANDESITE);
		shR2.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR2);

		return i;
	}

	private static ItemStack Andesite_Sword(MoreItems main) {
		ItemStack i = new ItemStack(Material.STONE_SWORD);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Andesite Sword");

		i.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "Sword2"), i);
		shR.shape(new String[] { "R", "R", "S" });
		shR.setIngredient('R', Material.ANDESITE);
		shR.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR);

		return i;
	}

	private static ItemStack Diorite_Pickaxe(MoreItems main) {
		ItemStack i = new ItemStack(Material.STONE_PICKAXE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Diorite Pickaxe");

		i.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "Pickk3"), i);
		shR.shape(new String[] { "RRR", " S ", " S " });
		shR.setIngredient('R', Material.DIORITE);
		shR.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR);

		return i;
	}

	private static ItemStack Diorite_Shovel(MoreItems main) {
		ItemStack i = new ItemStack(Material.STONE_SHOVEL);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Diorite Shovel");

		i.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "Shovel3"), i);
		shR.shape(new String[] { "R", "S", "S" });
		shR.setIngredient('R', Material.DIORITE);
		shR.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR);

		return i;
	}

	private static ItemStack Diorite_Axe(MoreItems main) {
		ItemStack i = new ItemStack(Material.STONE_AXE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Diorite Axe");

		i.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "Axe3"), i);
		shR.shape(new String[] { "RR", "RS", " S" });
		shR.setIngredient('R', Material.DIORITE);
		shR.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR);

		ShapedRecipe shR2 = new ShapedRecipe(new NamespacedKey(main, "Axxe3"), i);
		shR2.shape(new String[] { "RR", "SR", "S " });
		shR2.setIngredient('R', Material.DIORITE);
		shR2.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR2);

		return i;
	}

	private static ItemStack Diorite_Hoe(MoreItems main) {
		ItemStack i = new ItemStack(Material.STONE_HOE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Diorite Hoe");

		i.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "Hoe3"), i);
		shR.shape(new String[] { "RR", " S", " S" });
		shR.setIngredient('R', Material.DIORITE);
		shR.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR);

		ShapedRecipe shR2 = new ShapedRecipe(new NamespacedKey(main, "Hooe3"), i);
		shR2.shape(new String[] { "RR", "S ", "S " });
		shR2.setIngredient('R', Material.DIORITE);
		shR2.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR2);

		return i;
	}

	private static ItemStack Diorite_Sword(MoreItems main) {
		ItemStack i = new ItemStack(Material.STONE_SWORD);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Diorite Sword");

		i.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "Sword3"), i);
		shR.shape(new String[] { "R", "R", "S" });
		shR.setIngredient('R', Material.DIORITE);
		shR.setIngredient('S', Material.STICK);
		main.getServer().addRecipe(shR);

		return i;
	}

	///////////////////////////////////////////////
	//////////////////// Wands ////////////////////
	///////////////////////////////////////////////
	
	private static ItemStack Wand_of_Teleportation(MoreItems main) {
		ItemStack i = new ItemStack(Material.STICK);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.DARK_AQUA + "Wand of Teleportation");

		im.addEnchant(Enchantment.DURABILITY, 1, false);

		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}

	private static ItemStack Wand_of_Last_Death(MoreItems main) {
		ItemStack i = new ItemStack(Material.BONE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Wand of Last Death");

		im.addEnchant(Enchantment.DURABILITY, 1, false);

		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}

	private static ItemStack Wand_of_Warping(MoreItems main) {
		ItemStack i = new ItemStack(Material.BLAZE_ROD);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.WHITE + "Wand of Warping");

		im.addEnchant(Enchantment.DURABILITY, 1, false);

		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}

	@SuppressWarnings("deprecation")
	private static ItemStack Wand_of_Lesser_Fireballs(MoreItems main) {
		ItemStack i = new ItemStack(Material.LEGACY_REDSTONE_TORCH_ON);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Wand of Lesser Fireballs");

		im.addEnchant(Enchantment.DURABILITY, 1, false);

		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}

	@SuppressWarnings("deprecation")
	private static ItemStack Wand_of_Fireballs(MoreItems main) {
		ItemStack i = new ItemStack(Material.LEGACY_REDSTONE_TORCH_ON);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Wand of Fireballs");

		im.addEnchant(Enchantment.FIRE_ASPECT, 1, false);

		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}

	@SuppressWarnings("deprecation")
	private static ItemStack Wand_of_Greater_Fireballs(MoreItems main) {
		ItemStack i = new ItemStack(Material.LEGACY_REDSTONE_TORCH_ON);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Wand of Greater Fireballs");

		im.addEnchant(Enchantment.FIRE_ASPECT, 1, false);

		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}

	@SuppressWarnings("deprecation")
	private static ItemStack Wand_of_Grand_Fireballs(MoreItems main) {
		ItemStack i = new ItemStack(Material.LEGACY_REDSTONE_TORCH_ON);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Wand of Grand Fireballs");

		im.addEnchant(Enchantment.FIRE_ASPECT, 2, false);

		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}

	private static ItemStack Wand_of_Polymorph(MoreItems main) {
		ItemStack i = new ItemStack(Material.BAMBOO);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.AQUA + "Wand of Polymorph");

		im.addEnchant(Enchantment.DURABILITY, 1, false);

		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}

	private static ItemStack Wand_of_Toxic_Fumes(MoreItems main) {
		ItemStack i = new ItemStack(Material.BONE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.LIGHT_PURPLE + "Wand of Toxic Fumes");

		im.addEnchant(Enchantment.DURABILITY, 1, false);

		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}

	private static ItemStack Wand_of_Deadly_Fumes(MoreItems main) {
		ItemStack i = new ItemStack(Material.BONE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.DARK_PURPLE + "Wand of Deadly Fumes");

		im.addEnchant(Enchantment.THORNS, 1, false);

		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}

	private static ItemStack Wand_of_Lightning_Bolts(MoreItems main) {
		ItemStack i = new ItemStack(Material.BLAZE_ROD);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.DARK_AQUA + "Wand of Lightning Bolts");

		im.addEnchant(Enchantment.DURABILITY, 1, false);

		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}

	private static ItemStack Wand_of_Greater_Lightning_Bolts(MoreItems main) {
		ItemStack i = new ItemStack(Material.BLAZE_ROD);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.DARK_AQUA + "Wand of Greater Lightning Bolts");

		im.addEnchant(Enchantment.DURABILITY, 1, false);

		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}
	
	private static ItemStack Wand_of_Feasting(MoreItems main) {
		ItemStack i = new ItemStack(Material.STICK);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.YELLOW + "Wand of Feasting");

		im.addEnchant(Enchantment.DURABILITY, 1, false);

		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}
	
	private static ItemStack Wand_of_Healing(MoreItems main) {
		ItemStack i = new ItemStack(Material.STICK);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.GOLD + "Wand of Healing");

		im.addEnchant(Enchantment.DURABILITY, 1, false);

		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

		i.setItemMeta(im);

		return i;
	}
	
	///////////////////////
	
	private static ItemStack Scroll_of_Returning(MoreItems main) {
		ItemStack i = new ItemStack(Material.FLOWER_BANNER_PATTERN);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.DARK_AQUA + "Scroll of Returning");

		im.addEnchant(Enchantment.DURABILITY, 1, false);

		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS });

		i.setItemMeta(im);

		return i;
	}
	
	///////////////////////////////////
	
	private static ItemStack Bottle_of_Apeiron(MoreItems main) {
		ItemStack i = new ItemStack(Material.EXPERIENCE_BOTTLE);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Bottle of Apeiron");
		i.setItemMeta(im);
		
		return i;
	}
	
	private static ItemStack Grappling_Hook_Infinite(MoreItems main) {
		ItemStack i = new ItemStack(Material.FISHING_ROD);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.BOLD+"Grappling Hook");
		i.setItemMeta(im);
		CraftMeta.itemSet(i, ItemDataTag.GRAPPLING_HOOK);
		CraftMeta.itemSet(i, ItemDataTag.NOT_ENCHANTABLE);
		
		return i;
	}
	
	private static ItemStack Grappling_Hook(MoreItems main) {
		ItemStack i = new ItemStack(Material.FISHING_ROD);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("Grappling Hook");
		i.setItemMeta(im);
		CraftMeta.itemSet(i, ItemDataTag.GRAPPLING_HOOK);
		CraftMeta.itemSet(i, ItemDataTag.NOT_ENCHANTABLE);
		
		BetterShapedRecipe shR = new BetterShapedRecipe("GrapplingHook", i);
		shR.setShape(new String[] { " PP", "ILP", "FI " });
		shR.setIngredient('P', new ItemStack(Material.EMERALD));
		shR.setIngredient('L', new ItemStack(Material.LEAD));
		shR.setIngredient('I', new ItemStack(Material.IRON_INGOT));
		shR.setIngredient('F', new ItemStack(Material.FISHING_ROD));
		BetterRecipesManager.addBetterRecipe(shR);
		
		return i;
	}
	
	private static ItemStack Powered_Elytra(MoreItems main) {
		ItemStack i = new ItemStack(Material.ELYTRA);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.GOLD+"Powered Elytra");
		i.setItemMeta(im);
		CraftMeta.itemSet(i, ItemDataTag.SPECIAL_ELYTRA);
		CraftMeta.itemSet(i, ItemDataTag.NOT_ENCHANTABLE);
		
		BetterShapedRecipe shR = new BetterShapedRecipe("PoweredElytra", i);
		shR.setShape(new String[] { "RER", "F F" });
		shR.setIngredient('F', getItemClone(ItemType.EXPLOSIVES_T1));
		shR.setIngredient('E', new ItemStack(Material.ELYTRA));
		shR.setIngredient('R', new ItemStack(Material.REDSTONE));
		BetterRecipesManager.addBetterRecipe(shR);
		
		return i;
	}
	
	private static ItemStack Advanced_Elytra(MoreItems main) {
		ItemStack i = new ItemStack(Material.ELYTRA);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"Advanced Elytra");
		im.addEnchant(Enchantment.DURABILITY, 4, true);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		i.setItemMeta(im);
		CraftMeta.itemSet(i, ItemDataTag.SPECIAL_ELYTRA);
		CraftMeta.itemSet(i, ItemDataTag.NOT_ENCHANTABLE);
		
		BetterShapedRecipe shR = new BetterShapedRecipe("AdvancedElytra", i);
		shR.setShape(new String[] { " D ", "CPC", "DED" });
		shR.setIngredient('E', getItemClone(ItemType.EXPLOSIVES_T2));
		shR.setIngredient('P', getItemClone(ItemType.POWERED_ELYTRA));
		shR.setIngredient('D', new ItemStack(Material.DIAMOND));
		shR.setIngredient('C', new ItemStack(Material.COMPARATOR));
		BetterRecipesManager.addBetterRecipe(shR);
		
		return i;
	}
	
	////
	
	private static ItemStack Advanced_Boat(MoreItems main) {
		ItemStack i = new ItemStack(Material.SPRUCE_BOAT);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RESET+""+ChatColor.BOLD+"Advanced Boat");
		im.addEnchant(Enchantment.DURABILITY, 1, true);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		i.setItemMeta(im);
		CraftMeta.itemSet(i, ItemDataTag.ADVANCED_BOAT);
		
		BetterShapedRecipe shR = new BetterShapedRecipe("AdvancedBoat", i);
		shR.setShape(new String[] { " D ", "CPC", "D D" });
		shR.setIngredient('P', getItemClone(ItemType.POWERED_ELYTRA));
		shR.setIngredient('D', new ItemStack(Material.DIAMOND));
		shR.setIngredient('C', new ItemStack(Material.COMPARATOR));
		BetterRecipesManager.addBetterRecipe(shR);
		
		return i;
	}
	
	////
	
	private static ItemStack Player_Guide(MoreItems main) {
		
		ItemStack i = new ItemStack(Material.KNOWLEDGE_BOOK);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"Guide to Vryn");
		im.setLore(Arrays.asList(ChatColor.GREEN+""+ChatColor.ITALIC+"Click to teleport to the tutorial"));
		i.setItemMeta(im);
		
		return i;
	}
	
	////
	
	private static ItemStack Unread_Recipe(MoreItems main) {
		
		ItemStack i = new ItemStack(Material.BOOK);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.GOLD+""+ChatColor.ITALIC+"Ancient Recipe");
		i.setItemMeta(im);
		
		return i;
	}
	
	public static ItemStack Warding_Recipe(MoreItems main) {
		
		ItemStack i = new ItemStack(Material.BOOK);
		ItemMeta im = i.getItemMeta();
		
		int index = new SplittableRandom().nextInt(WardingRecipeManager.getWardingRecipes().size());
		WardingRecipe recipe = WardingRecipeManager.getWardingRecipes().get(index);
		
		im.setDisplayName(RecipeItemListener.getWardingRecipeName(recipe));
		i.setItemMeta(im);
		
		CraftMeta.itemSet(i, ItemDataTag.ANCIENT_RECIPE_WARDING, index);
		
		return i;
	}
	
}
