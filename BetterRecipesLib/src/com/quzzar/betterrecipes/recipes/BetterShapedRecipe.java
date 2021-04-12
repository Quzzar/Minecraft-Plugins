package com.quzzar.betterrecipes.recipes;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import com.quzzar.betterrecipes.BetterRecipesLib;

public class BetterShapedRecipe extends BetterRecipe {

	private ShapedRecipe recipe;
	private ItemStack result;
	
	public BetterShapedRecipe(String recipeKey, ItemStack result) {
		super();
		
		this.recipe = new ShapedRecipe(
						new NamespacedKey(BetterRecipesLib.getInstance(), recipeKey),
						new ItemStack(Material.STICK));
		this.result = result;
		
	}
	
	public void setShape(String... shape) {
		recipe.shape(shape);
	}
	
	public void setIngredient(char key, ItemStack ingredient) {
		
		Material material = ingredient.getType();
		
		getItemMap().put(material, ingredient);
		recipe.setIngredient(key, material);
	}
	
	@Override
	public ItemStack getResult() {
		return result;
	}
	
	public void setResult(ItemStack result) {
		this.result = result;
	}
	
	@Override
	public NamespacedKey getKey() {
		return recipe.getKey();
	}
	
	public Map<Character,ItemStack> getIngredientMap() {
		return recipe.getIngredientMap();
	}
	
	public String[] getShape() {
		return recipe.getShape();
	}
	
	@Override
	public ShapedRecipe getRecipe() {
		return recipe;
	}

}
