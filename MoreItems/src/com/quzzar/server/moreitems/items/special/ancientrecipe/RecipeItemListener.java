package com.quzzar.server.moreitems.items.special.ancientrecipe;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.ItemDataTag;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.moreitems.MoreItems;
import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;
import com.quzzar.server.worldutils.enchantments.warding.WardingRecipe;
import com.quzzar.server.worldutils.enchantments.warding.WardingRecipeManager;

public class RecipeItemListener implements Listener {
	
	@EventHandler
	public void onBlockInteract(PlayerInteractEvent e) {
		
		if(!e.hasItem()) {
			return;
		}
		
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			
			ItemStack item = e.getItem();
			
			if(item.isSimilar(ItemManager.getItemClone(ItemType.UNREAD_RECIPE))) {
				handleUnreadRecipe(e.getPlayer(), item);
				return;
			}
			
			if(CraftMeta.itemHasData(item, ItemDataTag.ANCIENT_RECIPE_WARDING)) {
				
				int wardingIndex = (int) CraftMeta.itemGet(item, ItemDataTag.ANCIENT_RECIPE_WARDING);
				handleWardingRecipe(e.getPlayer(), wardingIndex);
				
			}
			
		}
		
	}
	
	
	private void handleUnreadRecipe(Player player, ItemStack item) {
		item.subtract();
		ItemStack wardingRecipeItem = ItemManager.Warding_Recipe(MoreItems.getInstance());
		Utility.addItemToInventory(player.getInventory(), wardingRecipeItem, player.getLocation());
		player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.5f, 1.5f);
	}
	
	private void handleWardingRecipe(Player player, int index) {
		WardingRecipe recipe = WardingRecipeManager.getWardingRecipes().get(index);
		ShowRecipe.showWardingRecipe(player, recipe);
	}
	
	////
	
	public static String getWardingRecipeName(WardingRecipe recipe) {
		
		String name = ChatColor.GOLD+""+ChatColor.ITALIC+"Ancient Recipe: "+ChatColor.GREEN;
		
		if(recipe.getResult().getItemMeta() instanceof EnchantmentStorageMeta) {
			
			EnchantmentStorageMeta eIm = (EnchantmentStorageMeta) recipe.getResult().getItemMeta();
			if(!eIm.getStoredEnchants().isEmpty()) {
				Enchantment enchantment = eIm.getStoredEnchants().keySet().iterator().next();
				return name+fixKey(enchantment.getKey().getKey());
			}
			
		}
		
		return name+recipe.getResult().getItemMeta().getDisplayName();
		
	}
	
	private static String fixKey(String keyText) {
		keyText = keyText.replaceAll("_", " ");
		keyText = WordUtils.capitalizeFully(keyText);
		return keyText;
	}
	
	
	
	
}
