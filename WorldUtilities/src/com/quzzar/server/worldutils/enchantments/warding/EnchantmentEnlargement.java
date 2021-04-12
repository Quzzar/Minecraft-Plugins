package com.quzzar.server.worldutils.enchantments.warding;

import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class EnchantmentEnlargement {
	
	public static WardingRecipe getEnlargedRecipe(ItemStack... inputIngredients) {
		
		/*
		 * Will attempt to make an enlarged recipe is applicable, if not will return null.
		 */
		
		if(inputIngredients.length != 2) {
			return null;
		}
		
		if(!areAllSameItem(inputIngredients)) {
			return null;
		}
		
		ItemStack book = inputIngredients[0];
		
		if(!book.getType().equals(Material.ENCHANTED_BOOK)) {
			return null;
		}
		
		EnchantmentStorageMeta eMeta = (EnchantmentStorageMeta) book.getItemMeta();
		
		if(eMeta.getStoredEnchants().size() <= 0) {
			return null;
		}
		
		boolean isAdvanced = false;
		
		if(eMeta.getStoredEnchants().size() > 1) {
			isAdvanced = true;
		}
		
		
		// Creating result ItemStack
		ItemStack result = new ItemStack(Material.ENCHANTED_BOOK);
		EnchantmentStorageMeta resultMeta = (EnchantmentStorageMeta) result.getItemMeta();
		
		for(Entry<Enchantment, Integer> entry : eMeta.getStoredEnchants().entrySet()) {
			
			if(entry.getValue() > 1) {
				isAdvanced = true;
			}
			
			int newEnchantmentLvl = entry.getValue()+1;
			
			if(newEnchantmentLvl > 12) {
				return null; // If it would craft an enchantment that is too powerful, just return null.
			}
			
			resultMeta.addStoredEnchant(entry.getKey(), newEnchantmentLvl, true);
			
		}
		
		result.setItemMeta(resultMeta);
		
		
		return new WardingRecipe(result, isAdvanced, inputIngredients);
		
	}
	
	private static boolean areAllSameItem(ItemStack... inputIngredients) {
		
		if(inputIngredients.length <= 0) {
			return false;
		}
		
		ItemStack firstItem = inputIngredients[0];
		
		for(ItemStack item : inputIngredients) {
			if(!item.isSimilar(firstItem)) {
				return false;
			}
		}
		
		return true;
		
	}

}
