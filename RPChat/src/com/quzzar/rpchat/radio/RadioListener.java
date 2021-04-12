package com.quzzar.rpchat.radio;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import com.quzzar.rpchat.radio.misc.ItemType;

public class RadioListener implements Listener{

	// Prevents crafting with Sending Shells
	@EventHandler
	public void onPrepareItemCraft(PrepareItemCraftEvent e) {
		
		if(e.getInventory()!=null && e.getInventory().getResult()!=null) {
			
			for(ItemStack item : e.getInventory().getMatrix()) {
				if(item!=null && RadioCreator.isFairlySimilar(item, ItemType.SENDING_SHELL)) {
					e.getInventory().setResult(null);
					return;
				}
			}
			
		}
		
	}
	
	// Creates radio instance
	@EventHandler
	public void onItemCraft(CraftItemEvent e) {
		
		if(e.getInventory()!=null && e.getInventory().getResult()!=null) {
			
			if(RadioCreator.isFairlySimilar(e.getInventory().getResult(), ItemType.SENDING_SHELL)) {
				if(e.getAction().equals(InventoryAction.PICKUP_ALL)) {
					ItemStack registeredRadio = RadioUtilities.makeRegisteredRadio(
							e.getInventory().getMatrix(), e.getInventory().getResult());
					e.getInventory().setResult(registeredRadio);
					return;
				} else {
					e.setCancelled(true);
					return;
				}
			}
			
		}
		
	}
	
}
