package com.quzzar.server.moreitems;

import java.util.Arrays;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import com.quzzar.betterrecipes.BetterRecipesManager;
import com.quzzar.server.moreitems.heads.HeadUtil;

public class MainListener implements Listener {

	// Can't place block if it is a Mechanical
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent e) {
		if (!e.isCancelled()) {
			if (HeadUtil.checkMechanical(e.getItemInHand())) {
				e.setCancelled(true);
			}
		}
	}

	// Can't craft with items that have item meta unless they're in a Better Recipe
	@EventHandler
	public void onItemCraft(PrepareItemCraftEvent e) {
		
		if (BetterRecipesManager.isBetterRecipe(e.getRecipe())) {
			return;
		}
		
		List<ItemStack> ingredients = Arrays.asList(Utility.clean(e.getInventory().getMatrix()));
		for (ItemStack item : ingredients) {
			if (item.hasItemMeta()) {
				e.getInventory().setResult(null);
				return;
			}
		}
	}

	// Can't interact with items that are blocks and are enchanted
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if ((e.isBlockInHand()) && (e.getItem().hasItemMeta()) && (e.getItem().getItemMeta().hasEnchants())) {
			e.setCancelled(true);
		}
	}

}
