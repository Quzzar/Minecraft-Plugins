package com.quzzar.ge.listeners.handlers.selling;

import java.util.ArrayList;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

import com.quzzar.ge.core.SellingManager;
import com.quzzar.ge.core.buylist.GETrade;
import com.quzzar.ge.presets.selling.SellingInventoryBuilder;
import com.quzzar.ge.presets.selling.SellingItemCreator;

public class SellingHandler {

	public static void handleSellingPage(ItemStack item, HumanEntity he, int slotClicked) {
		
		if(item.equals(SellingItemCreator.getAddSale())){
			he.closeInventory();
			he.openInventory(SellingInventoryBuilder.buildSaleEmpty(null, 0, slotClicked));
			return;
		}
		
		ArrayList<GETrade> sellings = SellingManager.getSellings(he.getUniqueId());
		
		for(GETrade trade : sellings) {
			if(item.equals(SellingItemCreator.getExistingSale(trade))){
				he.closeInventory();
				switch(trade.getStage()) {
	    			case ACTIVE: he.openInventory(SellingInventoryBuilder.buildSaleActive(trade)); break;
	    			case SOLD: he.openInventory(SellingInventoryBuilder.buildSaleAccepted(trade)); break;
	    			case EXPIRED: he.openInventory(SellingInventoryBuilder.buildSaleExpired(trade)); break;
	    			default: break;
				}
				return;
			}
		}
		
	}
	
	
}
