package com.quzzar.ge.core.buylist;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.quzzar.ge.core.MarketManager;
import com.quzzar.ge.core.buylist.buyinvmapping.BuyAreaContentsMap;
import com.quzzar.ge.core.buylist.buyinvmapping.BuyAreaInstance;

public class BuyList {

	private HashMap<SimpleItem, ItemOnMarket> buyList;
	
	public BuyList() {
		buyList = new HashMap<SimpleItem, ItemOnMarket>();
	}
	
	public HashMap<SimpleItem, ItemOnMarket> getBuyList() {
		return buyList;
	}
	
	public void updateEntireBuyList() {
		
		buyList.clear();
		
		// Compile all items that are in BuyArea inventories into buyList
		for(BuyAreaInstance instance : BuyAreaContentsMap.getInvContentsMap().values()) {
			for(SimpleItem item : instance.getContents()) {
				buyList.put(item, new ItemOnMarket());
			}
		}
		
		for(GETrade trade : MarketManager.getAllTrades()) {
			if(trade.getStage().equals(TradeStage.ACTIVE)) {
				addBuyListEntry(trade);
			}
		}
		
	}
	
	public void addBuyListEntry(GETrade trade) {
		buyList.get(trade.getItem().singleItem()).addTrade(trade);
	}
	
	public void removeBuyListEntry(GETrade trade) {
		buyList.get(trade.getItem().singleItem()).removeTrade(trade);
	}
	
	public boolean isOnBuyList(ItemStack item) {
		
		// Allow Air
		if(item.getType().equals(Material.AIR)) {return true;}
		
		boolean canBeSimple = SimpleItem.canBeSimple(item);
		if(canBeSimple) {
			SimpleItem simpleVersion = new SimpleItem(item, 1);
			return buyList.containsKey(simpleVersion);
		}
		return false;
	}
	
}
