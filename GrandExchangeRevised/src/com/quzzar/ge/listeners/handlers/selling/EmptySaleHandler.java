package com.quzzar.ge.listeners.handlers.selling;

import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.quzzar.ge.core.MarketManager;
import com.quzzar.ge.core.buylist.BuyListManager;
import com.quzzar.ge.presets.GeneralInventoryBuilder;
import com.quzzar.ge.presets.selling.SellingInventoryBuilder;
import com.quzzar.ge.presets.selling.SellingItemCreator;

public class EmptySaleHandler {

	private static final int MAX_PRICE = 99999;
	private static final int MIN_PRICE = 0;
	
	public static void handleEmptySale(ItemStack item, HumanEntity he, Inventory openedInv,
			Inventory clickedInv, InventoryClickEvent e) {
		
		ItemStack sellingItem = openedInv.getItem(SellingInventoryBuilder.INPUT_ITEM_SLOT);
		int sellSlot = getSellSlot(openedInv);
		
		if(sellSlot == -1) {
			he.closeInventory();
			he.sendMessage(ChatColor.RED+"Error, sell slot not found!");
			return;
		}
		
		if(clickedInv == openedInv) {
			if(item.equals(SellingItemCreator.getIncreaseBtn())) {
				if(e.getClick().equals(ClickType.LEFT)) {
					int price = getCurrentPriceTag(openedInv);
					if(price+S_AMT > MAX_PRICE) {
						price = MAX_PRICE;
					} else {
						price += S_AMT;
					}
					GeneralInventoryBuilder.updateInventory(openedInv, SellingInventoryBuilder.buildSaleEmpty(sellingItem, price, sellSlot));
				} else if(e.getClick().equals(ClickType.RIGHT)) {
					int price = getCurrentPriceTag(openedInv);
					if(price+L_AMT > MAX_PRICE) {
						price = MAX_PRICE;
					} else {
						price += L_AMT;
					}
					GeneralInventoryBuilder.updateInventory(openedInv, SellingInventoryBuilder.buildSaleEmpty(sellingItem, price, sellSlot));
				} else if(e.getClick().isShiftClick()) {
					int price = getCurrentPriceTag(openedInv);
					if(price+G_AMT > MAX_PRICE) {
						price = MAX_PRICE;
					} else {
						price += G_AMT;
					}
					GeneralInventoryBuilder.updateInventory(openedInv, SellingInventoryBuilder.buildSaleEmpty(sellingItem, price, sellSlot));
				}
				return;
			}
			
			if(item.equals(SellingItemCreator.getDecreaseBtn())) {
				if(e.getClick().equals(ClickType.LEFT)) {
					int price = getCurrentPriceTag(openedInv);
					if(price-S_AMT < MIN_PRICE) {
						price = MIN_PRICE;
					} else {
						price -= S_AMT;
					}
					GeneralInventoryBuilder.updateInventory(openedInv, SellingInventoryBuilder.buildSaleEmpty(sellingItem, price, sellSlot));
				} else if(e.getClick().equals(ClickType.RIGHT)) {
					int price = getCurrentPriceTag(openedInv);
					if(price-L_AMT < MIN_PRICE) {
						price = MIN_PRICE;
					} else {
						price -= L_AMT;
					}
					GeneralInventoryBuilder.updateInventory(openedInv, SellingInventoryBuilder.buildSaleEmpty(sellingItem, price, sellSlot));
				} else if(e.getClick().isShiftClick()) {
					int price = getCurrentPriceTag(openedInv);
					if(price-G_AMT < MIN_PRICE) {
						price = MIN_PRICE;
					} else {
						price -= G_AMT;
					}
					GeneralInventoryBuilder.updateInventory(openedInv, SellingInventoryBuilder.buildSaleEmpty(sellingItem, price, sellSlot));
				}
				return;
			}
			
			if(item.equals(SellingItemCreator.getConfirmItem(sellSlot)) && sellingItem != null 
					&& BuyListManager.getBuyList().isOnBuyList(sellingItem)) {
				
				int price = getCurrentPriceTag(openedInv);
				
				MarketManager.createTradeRecord(sellingItem, price, he.getUniqueId(), sellSlot);
				
				he.closeInventory();
				he.openInventory(SellingInventoryBuilder.buildSellingPage(he.getUniqueId()));
				return;
			}
			
		}
		
		// Lastly,
		if(clickedInv != openedInv && BuyListManager.getBuyList().isOnBuyList(item)
				&& (e.getClick().equals(ClickType.LEFT) || e.getClick().equals(ClickType.SHIFT_LEFT)
				|| e.getClick().equals(ClickType.RIGHT) || e.getClick().equals(ClickType.SHIFT_RIGHT))) {
			e.setCancelled(false);
			return;
		}
		if(clickedInv == openedInv && e.getSlot() == SellingInventoryBuilder.INPUT_ITEM_SLOT
				&& (e.getClick().equals(ClickType.LEFT) || e.getClick().equals(ClickType.SHIFT_LEFT) 
						|| e.getClick().equals(ClickType.RIGHT) || e.getClick().equals(ClickType.SHIFT_RIGHT))) {
			e.setCancelled(false);
			return;
		}
		
	}
	
	private static final int G_AMT = 100;
	private static final int L_AMT = 10;
	private static final int S_AMT = 1;
	
	private static int getCurrentPriceTag(Inventory openedInv) {
		for(ItemStack item : openedInv) {
			if(item != null && item.hasItemMeta() && item.getItemMeta().hasCustomModelData()) {
				int data = item.getItemMeta().getCustomModelData();
				if(item.getType().equals(SellingItemCreator.price_tag_material)) {
					return data;
				}
			}
		}
		return 0;
	}
	
	private static int getSellSlot(Inventory openedInv) {
		for(ItemStack item : openedInv) {
			if(item != null && item.hasItemMeta() && item.getItemMeta().hasCustomModelData()) {
				int data = item.getItemMeta().getCustomModelData();
				if(item.getType().equals(SellingItemCreator.confirm_material)) {
					return data;
				}
			}
		}
		return -1;
	}
	
}
