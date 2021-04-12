package com.quzzar.betterrecipes.recipes;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public abstract class BetterRecipe {
	
	private HashMap<Material, ItemStack> itemMap;
	private String permission;
	
	public BetterRecipe() {
		this.itemMap = new HashMap<Material, ItemStack>();
		this.permission = "none";
	}
	
	public HashMap<Material,ItemStack> getItemMap() {
		return itemMap;
	}
	
	public void setRequiredPermission(String permission) {
		this.permission = permission;
	}
	
	public boolean needsPermission() {
		return permission!="none";
	}
	
	public String getRequiredPermission() {
		return permission;
	}
	
	public abstract ItemStack getResult();
	
	public abstract NamespacedKey getKey();
	
	public abstract Recipe getRecipe();
	
}
