package com.quzzar.ge.listeners.handlers;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

import com.quzzar.ge.presets.GeneralItemCreator;
import com.quzzar.ge.presets.buying.BuyingInventoryBuilder;
import com.quzzar.ge.presets.selling.SellingInventoryBuilder;

public class MainMenuHandler {

	public static void handleMainMenu(ItemStack item, HumanEntity he) {
		
		if(item.equals(GeneralItemCreator.getBuyButton())){
			he.closeInventory();
			he.openInventory(BuyingInventoryBuilder.getMainSection());
			return;
		}
		
		if(item.equals(GeneralItemCreator.getSellButton())){
			he.closeInventory();
			he.openInventory(SellingInventoryBuilder.buildSellingPage(he.getUniqueId()));
			return;
		}
		
	}
	
}
