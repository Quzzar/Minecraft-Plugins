package com.quzzar.server.moreitems.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import com.quzzar.betterrecipes.BetterRecipesManager;
import com.quzzar.betterrecipes.recipes.BetterShapelessRecipe;
import com.quzzar.server.moreitems.MoreItems;
import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;

public class MiscRecipes {

	public static void loadRecipes(MoreItems main) {
		boolean enabled = true;

		if (enabled) {
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "ChainBoots"),
					new ItemStack(Material.CHAINMAIL_BOOTS));
			shR.shape(new String[] { "F F", "I I" });
			shR.setIngredient('I', Material.IRON_INGOT);
			shR.setIngredient('F', Material.FLINT);
			main.getServer().addRecipe(shR);
		}

		if (enabled) {
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "ChainHelmet"),
					new ItemStack(Material.CHAINMAIL_HELMET));
			shR.shape(new String[] { "iFi", "I I" });
			shR.setIngredient('I', Material.IRON_INGOT);
			shR.setIngredient('i', Material.IRON_NUGGET);
			shR.setIngredient('F', Material.FLINT);
			main.getServer().addRecipe(shR);
		}

		if (enabled) {
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "ChainChest"),
					new ItemStack(Material.CHAINMAIL_CHESTPLATE));
			shR.shape(new String[] { "I I", "FIF", "iFi" });
			shR.setIngredient('I', Material.IRON_INGOT);
			shR.setIngredient('i', Material.IRON_NUGGET);
			shR.setIngredient('F', Material.FLINT);
			main.getServer().addRecipe(shR);
		}

		if (enabled) {
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "ChainLegs"),
					new ItemStack(Material.CHAINMAIL_LEGGINGS));
			shR.shape(new String[] { "IiI", "F F", "I I" });
			shR.setIngredient('I', Material.IRON_INGOT);
			shR.setIngredient('i', Material.IRON_NUGGET);
			shR.setIngredient('F', Material.FLINT);
			main.getServer().addRecipe(shR);
		}

		////

		if (enabled) {
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "NotchApple"),
					new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
			shR.shape(new String[] { "GGG", "GAG", "GGG" });
			shR.setIngredient('A', Material.APPLE);
			shR.setIngredient('G', Material.GOLD_BLOCK);
			main.getServer().addRecipe(shR);
		}

		if (enabled) {
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "Elytra"), new ItemStack(Material.ELYTRA));
			shR.shape(new String[] { "IDI", "PIP", "F F" });
			shR.setIngredient('D', Material.EMERALD_BLOCK);
			shR.setIngredient('I', Material.IRON_BLOCK);
			shR.setIngredient('P', Material.PHANTOM_MEMBRANE);
			shR.setIngredient('F', Material.FEATHER);
			main.getServer().addRecipe(shR);
		}

		if (enabled) {
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "TridentR"), new ItemStack(Material.TRIDENT));
			shR.shape(new String[] { "DGD", "DSD", " S " });
			shR.setIngredient('G', Material.GHAST_TEAR);
			shR.setIngredient('D', Material.DIAMOND);
			shR.setIngredient('S', Material.STICK);
			main.getServer().addRecipe(shR);
		}

		if (enabled) {
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "SaddleR"), new ItemStack(Material.SADDLE));
			shR.shape(new String[] { "LLL", "TLT" });
			shR.setIngredient('L', Material.LEATHER);
			shR.setIngredient('T', Material.TRIPWIRE_HOOK);
			main.getServer().addRecipe(shR);
		}

		if (enabled) {
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(main, "WebBlockk"),
					new ItemStack(Material.COBWEB, 1));
			shR.shape(new String[] { "SSS", "SSS", "SSS" });
			shR.setIngredient('S', Material.STRING);
			main.getServer().addRecipe(shR);
		}

		if (enabled) {
			ShapelessRecipe shR = new ShapelessRecipe(new NamespacedKey(main, "WebToStringg"),
					new ItemStack(Material.STRING, 9));
			shR.addIngredient(Material.COBWEB);
			main.getServer().addRecipe(shR);
		}

		if (enabled) {
			ShapelessRecipe shR = new ShapelessRecipe(new NamespacedKey(main, "ClayBlockk"),
					new ItemStack(Material.CLAY_BALL, 4));
			shR.addIngredient(Material.CLAY);
			main.getServer().addRecipe(shR);
		}

		if (enabled) {
			ShapelessRecipe shR = new ShapelessRecipe(new NamespacedKey(main, "GlowStoneDustt"),
					new ItemStack(Material.GLOWSTONE_DUST, 4));
			shR.addIngredient(Material.GLOWSTONE);
			main.getServer().addRecipe(shR);
		}

		if (enabled) {
			ShapelessRecipe shR = new ShapelessRecipe(new NamespacedKey(main, "MagmaBlockk"),
					new ItemStack(Material.MAGMA_BLOCK, 1));
			shR.addIngredient(Material.LAVA_BUCKET);
			shR.addIngredient(Material.COBBLESTONE);
			main.getServer().addRecipe(shR);
		}

		if (enabled) {
			ShapelessRecipe shR = new ShapelessRecipe(new NamespacedKey(main, "MossCobbleBlockk"),
					new ItemStack(Material.MOSSY_COBBLESTONE, 1));
			shR.addIngredient(Material.WHEAT_SEEDS);
			shR.addIngredient(Material.COBBLESTONE);
			main.getServer().addRecipe(shR);
		}

		if (enabled) {
			BetterShapelessRecipe shR = new BetterShapelessRecipe("TableToPortable",
					new ItemStack(Material.CRAFTING_TABLE));
			shR.addIngredient(ItemManager.getItemClone(ItemType.PORTABLE_CRAFTING_TABLE));
			BetterRecipesManager.addBetterRecipe(shR);
		}

	}
}
