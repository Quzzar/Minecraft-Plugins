package com.quzzar.server.moreitems.items.special.ancientrecipe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.InvDataTag;
import com.quzzar.server.worldutils.enchantments.warding.WardingRecipe;

public class ShowRecipe {

	public static void showWardingRecipe(Player player, WardingRecipe recipe) {
		
		Inventory inv = Bukkit.createInventory(null, InventoryType.DROPPER, 
				ChatColor.stripColor(RecipeItemListener.getWardingRecipeName(recipe)));
		CraftMeta.invSet(inv, InvDataTag.DISABLED);
		
		ItemStack bookSelf = new ItemStack(Material.BOOKSHELF);
		inv.setItem(0, bookSelf);
		inv.setItem(2, bookSelf);
		inv.setItem(6, bookSelf);
		inv.setItem(8, bookSelf);
		
		ItemStack enchantTable = new ItemStack(Material.ENCHANTING_TABLE);
		if(recipe.isAdvanced()) {
			enchantTable.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
			enchantTable.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		}
		inv.setItem(4, enchantTable);
		
		if(recipe.getIngredients().size() >= 1) {
			inv.setItem(1, recipe.getIngredients().get(0));
			if(recipe.getIngredients().size() >= 2) {
				inv.setItem(3, recipe.getIngredients().get(1));
				if(recipe.getIngredients().size() >= 3) {
					inv.setItem(5, recipe.getIngredients().get(2));
					if(recipe.getIngredients().size() >= 4) {
						inv.setItem(7, recipe.getIngredients().get(3));
					}
				}
			}
		}
		
		player.openInventory(inv);
		
	}
	
}
