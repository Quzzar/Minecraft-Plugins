package com.quzzar.betterrecipes;

import java.util.HashMap;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import com.quzzar.betterrecipes.recipes.BetterRecipe;

public class BetterRecipesManager {

	private static HashMap<NamespacedKey,BetterRecipe> betterRecipes = new HashMap<NamespacedKey,BetterRecipe>();
	
	public static void addBetterRecipe(BetterRecipe recipe) {
		BetterRecipesLib.getInstance().getServer().addRecipe(recipe.getRecipe());
		betterRecipes.put(recipe.getKey(), recipe);
	}
	
	public static BetterRecipe getBetterRecipe(NamespacedKey key) {
		return betterRecipes.get(key);
	}
	
	public static BetterRecipe getBetterRecipe(Recipe recipe) {
		if(recipe instanceof ShapedRecipe) {
			BetterRecipe betterRecipe = getBetterRecipe(((ShapedRecipe) recipe).getKey());
			return betterRecipe;
		} else if(recipe instanceof ShapelessRecipe) {
			BetterRecipe betterRecipe = getBetterRecipe(((ShapelessRecipe) recipe).getKey());
			return betterRecipe;
		}
		return null;
	}
	
	public static boolean isBetterRecipe(Recipe recipe) {
		return getBetterRecipe(recipe)!=null;
	}
	
	public static HashMap<NamespacedKey,BetterRecipe> getBetterRecipes() {
		return betterRecipes;
	}
	
}
