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

public class ExpiredSaleHandler {
	
	public static void handleExpiredSale(ItemStack item, HumanEntity he, Inventory openedInv) {
		
		if(item.getType().equals(SellingItemCreator.remove_material)) {
			
			GETrade trade = SellingInventoryBuilder.getGETrade(openedInv.getItem(SellingInventoryBuilder.EXPIRED_ITEM_SLOT), he.getUniqueId());
			
			if(trade == null) {
				he.closeInventory();
				he.sendMessage(ChatColor.RED+"Error, trade not found!");
				return;
			}
			
			ItemUtils.addItemToInventory(he.getInventory(), trade.getItem().toItemStack(), he.getLocation());
			
			PlayerCharacter pChar = CharacterManager.getCharacter((Player) he);
			pChar.giveMoney(trade.getCurrentProfit());
			
			MarketManager.deleteTradeRecord(trade);
			
			he.closeInventory();
			he.openInventory(SellingInventoryBuilder.buildSellingPage(he.getUniqueId()));
			
			return;
		}
		
	}
	
}
