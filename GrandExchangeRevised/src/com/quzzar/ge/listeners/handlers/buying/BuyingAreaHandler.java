package com.quzzar.ge.listeners.handlers.buying;

import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.quzzar.ge.core.InvType;
import com.quzzar.ge.core.ItemUtils;
import com.quzzar.ge.core.buylist.BuyListManager;
import com.quzzar.ge.core.buylist.GETrade;
import com.quzzar.ge.core.buylist.ItemOnMarket;
import com.quzzar.ge.core.buylist.SimpleItem;
import com.quzzar.ge.core.buylist.TradeStage;
import com.quzzar.ge.presets.GeneralInventoryBuilder;
import com.quzzar.ge.presets.buying.BuyingInventoryBuilder;
import com.quzzar.ge.presets.buying.BuyingItemCreator;
import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;

public class BuyingAreaHandler {

	public static void handleBuyingArea(InvType type, ItemStack item, HumanEntity he, Inventory openedInv, ClickType clickType) {
		
		SimpleItem sItem = BuyingItemCreator.getSimpleItemFromBuyingItem(item);
		
		ItemOnMarket marketItem = BuyListManager.getData().get(sItem);
		if(marketItem == null) {return;}
		GETrade cheapestTrade = marketItem.getCheapestTrade();
		if(cheapestTrade == null) {return;}
		
		// Confirm clicked item price is same
		if(!BuyingItemCreator.isAboutSamePrice(item, cheapestTrade)) {
			// If not, redraw inventory and don't buy
			GeneralInventoryBuilder.updateInventory(openedInv, BuyingInventoryBuilder.generateBuyingInv(type));
			return;
		}
		
		
		// Cancel sale (if middle and has perm)
		if(clickType == ClickType.MIDDLE && he.hasPermission("grandexchange.cancelsale")) {
			cheapestTrade.setTradeStage(TradeStage.EXPIRED);
			return;
		}
		
		double cost;
		int amount;
		if(clickType.isShiftClick()) {
			// Buy stack
			cost = cheapestTrade.getTotalCost();
			amount = cheapestTrade.getItem().getAmount();
		} else {
			// Buy just one
			cost = cheapestTrade.getSingleCost();
			amount = 1;
		}
		
		PlayerCharacter pChar = CharacterManager.getCharacter((Player) he);
		boolean success = pChar.takeMoney(cost);
		
		if(success) {
			
			ItemStack purchasedItem = cheapestTrade.getItem().toItemStack();
			purchasedItem.setAmount(amount);
			
			ItemUtils.addItemToInventory(he.getInventory(), purchasedItem, he.getLocation());
			
			cheapestTrade.setCurrentProfit(cheapestTrade.getCurrentProfit()+amount*cheapestTrade.getSingleCost());
			cheapestTrade.getItem().setAmount(cheapestTrade.getItem().getAmount()-amount);
			
			if(cheapestTrade.getItem().getAmount() <= 0) {
				cheapestTrade.setTradeStage(TradeStage.SOLD);
				cheapestTrade.getItem().setAmount(1);
			}
			
			// Redraw inv
			GeneralInventoryBuilder.updateInventory(openedInv, BuyingInventoryBuilder.generateBuyingInv(type));
			
		} else {
			
			he.closeInventory();
			he.sendMessage(ChatColor.RED+"There is not enough money in your account to do buy this!");
			
		}
		return;
		
	}
	
}
