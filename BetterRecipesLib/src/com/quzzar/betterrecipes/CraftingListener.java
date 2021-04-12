package com.quzzar.betterrecipes;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import com.quzzar.betterrecipes.recipes.BetterRecipe;

public class CraftingListener implements Listener {
	
	@EventHandler
	public void onItemCraft(PrepareItemCraftEvent e) {
		
		BetterRecipe betterRecipe = BetterRecipesManager.getBetterRecipe(e.getRecipe());
		
		if(betterRecipe!=null) {
			
			// If permission is required to craft, make sure all viewers have the permission.
			if(betterRecipe.needsPermission()) {
				String permission = betterRecipe.getRequiredPermission();
				for(HumanEntity he : e.getViewers()) {
					// If any viewers don't, set result to null and return
					if(!he.hasPermission(permission)) {
						e.getInventory().setResult(null);
						return;
					}
				}
			}
			
			boolean notSame = false;
			
			for(ItemStack item : e.getInventory().getMatrix()) {
				if(item!=null) { // Checks to make sure the item given is the same as the one required
					if(!betterRecipe.getItemMap().get(item.getType()).isSimilar(item)) {
						notSame = true;
					}
				}
			}
			
			if(notSame) {
				e.getInventory().setResult(null);
				return;
			} else {
				e.getInventory().setResult(betterRecipe.getResult());
				return;
			}
			
		}
		
	}
	
}
