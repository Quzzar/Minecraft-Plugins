package com.quzzar.server.worldutils.enchantments.warding;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class WardingRecipe {
	
	public static final int MAX_INGREDIENTS = 4;

	private ArrayList<ItemStack> ingredients;
	
	private ItemStack result;
	
	private boolean isAdvanced;
	
	public WardingRecipe(ItemStack result, boolean isAdvanced, ItemStack... inputIngredients) {
		
		this.ingredients = new ArrayList<ItemStack>();
		
		for(int i = 0; (i <= MAX_INGREDIENTS && i < inputIngredients.length); i++) {
			ItemStack inputIn = inputIngredients[i];
			inputIn.setAmount(1);
			ingredients.add(inputIn);
		}
		
		this.result = result;
		
		this.isAdvanced = isAdvanced;
		
	}

	public ArrayList<ItemStack> getIngredients() {
		return ingredients;
	}
	
	public ItemStack getResult() {
		return result;
	}
	
	public boolean isAdvanced() {
		return isAdvanced;
	}
	
	public boolean canForge(ItemStack... inputIngredients) {
		
		ArrayList<ItemStack> adjustedInputIngredients = new ArrayList<ItemStack>();
		
		for(ItemStack inputIn : inputIngredients) {
			ItemStack adjustedInputIn = inputIn.clone();
			adjustedInputIn.setAmount(1);
			adjustedInputIngredients.add(adjustedInputIn);
		}
		
		return ingredients.containsAll(adjustedInputIngredients) 
				&& adjustedInputIngredients.containsAll(ingredients)
				&& ingredients.size() == adjustedInputIngredients.size();
	}
	
}
