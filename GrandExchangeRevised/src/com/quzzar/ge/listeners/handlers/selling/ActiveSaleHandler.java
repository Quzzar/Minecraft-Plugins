package com.quzzar.ge.listeners.handlers.selling;

import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.quzzar.ge.core.ItemUtils;
import com.quzzar.ge.core.MarketManager;
import com.quzzar.ge.core.buylist.GETrade;
import com.quzzar.ge.presets.selling.SellingInventoryBuilder;
import com.quzzar.ge.presets.selling.SellingItemCreator;
import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;

public class ActiveSaleHandler {
	
	public static void handleActiveSale(ItemStack item, HumanEntity he, Inventory openedInv) {
		
		PlayerCharacter pChar = CharacterManager.getCharacter((Player) he);
		
		if(item.getType().equals(SellingItemCreator.remove_material)) {
			
			GETrade trade = SellingInventoryBuilder.getGETrade(openedInv.getItem(SellingInventoryBuilder.ACTIVE_ITEM_SLOT), he.getUniqueId());
			
			if(trade == null) {
				he.closeInventory();
				he.sendMessage(ChatColor.RED+"Error, trade not found!");
				return;
			}
			
			boolean success = pChar.takeMoney(SellingInventoryBuilder.calculateCancelFee(trade.getTotalCost()));
			
			if(success) {
				
				ItemUtils.addItemToInventory(he.getInventory(), trade.getItem().toItemStack(), he.getLocation());
				
				if(trade.hasProfit()) {
					pChar.giveMoney(trade.getCurrentProfit());
				}
				
				MarketManager.deleteTradeRecord(trade);
				
				he.closeInventory();
				he.openInventory(SellingInventoryBuilder.buildSellingPage(he.getUniqueId()));
				
			} else {
				
				he.closeInventory();
				he.sendMessage(ChatColor.RED+"There is not enough money in your account to do that!");
				
			}
			return;
		}
		
		if(item.getType().equals(SellingItemCreator.claim_profit_material)) {
			
			GETrade trade = SellingInventoryBuilder.getGETrade(openedInv.getItem(SellingInventoryBuilder.ACTIVE_ITEM_SLOT), he.getUniqueId());
			
			if(trade == null) {
				he.closeInventory();
				he.sendMessage(ChatColor.RED+"Error, trade not found!");
				return;
			}
			
			pChar.giveMoney(trade.getCurrentProfit());
			trade.setCurrentProfit(0);
			
			he.closeInventory();
			he.openInventory(SellingInventoryBuilder.buildSaleActive(trade));
			
			return;
		}
		
	}
	
}
