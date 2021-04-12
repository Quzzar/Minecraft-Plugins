package com.quzzar.ge.presets.selling;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.quzzar.ge.core.InvType;
import com.quzzar.ge.core.ItemUtils;
import com.quzzar.ge.core.MarketManager;
import com.quzzar.ge.core.SellingManager;
import com.quzzar.ge.core.buylist.GETrade;
import com.quzzar.ge.presets.GeneralInventoryBuilder;
import com.quzzar.ge.presets.GeneralItemCreator;

public class SellingInventoryBuilder {
	
	/////////
	
	public static final int INPUT_ITEM_SLOT = 4;
	public static Inventory buildSaleEmpty(ItemStack item, int totalPrice, int sellSlot) {
		
		Inventory inv = Bukkit.createInventory(null, InventoryType.DROPPER, "Sell Item");
		
		inv.setItem(0, GeneralItemCreator.getBackButton(InvType.SELLING_PAGE));
		
		inv.setItem(1, SellingItemCreator.getIncreaseBtn());
		inv.setItem(7, SellingItemCreator.getDecreaseBtn());
		
		inv.setItem(3, SellingItemCreator.getPriceTag(totalPrice));
		
		inv.setItem(5, SellingItemCreator.getConfirmItem(sellSlot));
		
		GeneralInventoryBuilder.fillInv(inv);
		
		if(item == null) {
			inv.getItem(INPUT_ITEM_SLOT).setAmount(0);
		} else {
			inv.setItem(INPUT_ITEM_SLOT, item);
		}
		
		inv.setMaxStackSize(InvType.SALE_EMPTY.getID());
		return inv;
		
	}
	
	public static final int ACTIVE_ITEM_SLOT = 4;
	public static Inventory buildSaleActive(GETrade trade) {
		
		Inventory inv = Bukkit.createInventory(null, InventoryType.DROPPER, "Active Sale");
		
		inv.setItem(0, GeneralItemCreator.getBackButton(InvType.SELLING_PAGE));
		
		inv.setItem(3, SellingItemCreator.getPriceTag(trade.getTotalCost()));
		
		if(trade.hasProfit()) {
			inv.setItem(5, SellingItemCreator.getClaimProfitItem(trade.getCurrentProfit()));
		}
		
		inv.setItem(7, SellingItemCreator.getRemoveItem(calculateCancelFee(trade.getTotalCost())));
		
		inv.setItem(ACTIVE_ITEM_SLOT, SellingItemCreator.getSaleItem(trade));
		
		GeneralInventoryBuilder.fillInv(inv);
		
		inv.setMaxStackSize(InvType.SALE_ACTIVE.getID());
		return inv;
		
	}
	
	public static final int ACCEPTED_ITEM_SLOT = 7;
	public static Inventory buildSaleAccepted(GETrade trade) {
		
		Inventory inv = Bukkit.createInventory(null, InventoryType.DROPPER, "Sold Item");
		
		inv.setItem(0, GeneralItemCreator.getBackButton(InvType.SELLING_PAGE));
		
		inv.setItem(4, SellingItemCreator.getClaimProfitItem(trade.getCurrentProfit()));
		
		inv.setItem(ACCEPTED_ITEM_SLOT, SellingItemCreator.getSaleItem(trade));
		
		GeneralInventoryBuilder.fillInv(inv);
		
		inv.setMaxStackSize(InvType.SALE_SOLD.getID());
		return inv;
		
	}
	
	public static final int EXPIRED_ITEM_SLOT = 7;
	public static Inventory buildSaleExpired(GETrade trade) {
		
		Inventory inv = Bukkit.createInventory(null, InventoryType.DROPPER, "Expired Sale");
		
		inv.setItem(0, GeneralItemCreator.getBackButton(InvType.SELLING_PAGE));
		
		inv.setItem(2, SellingItemCreator.getExpireInfo());
		
		inv.setItem(4, SellingItemCreator.getRemoveItemNoFee());
		
		inv.setItem(EXPIRED_ITEM_SLOT, SellingItemCreator.getSaleItem(trade));
		
		GeneralInventoryBuilder.fillInv(inv);
		
		inv.setMaxStackSize(InvType.SALE_FAILED.getID());
		return inv;
		
	}
	
	/////////
	
	public static Inventory buildSellingPage(UUID pUUID) {
		
		Inventory inv = Bukkit.createInventory(null, 36, "Selling");
		
		inv.setItem(0, GeneralItemCreator.getBackButton(InvType.MAIN_MENU));
		
		inv.setItem(1, GeneralItemCreator.getInvBorder());
		inv.setItem(2, GeneralItemCreator.getInvBorder());
		inv.setItem(3, GeneralItemCreator.getInvBorder());
		inv.setItem(4, GeneralItemCreator.getInvBorder());
		inv.setItem(5, GeneralItemCreator.getInvBorder());
		inv.setItem(6, GeneralItemCreator.getInvBorder());
		inv.setItem(7, GeneralItemCreator.getInvBorder());
		
		inv.setItem(8, SellingItemCreator.getSellingInfo());
		
		inv.setItem(9, GeneralItemCreator.getInvBorder());
		inv.setItem(17, GeneralItemCreator.getInvBorder());
		
		inv.setItem(18, GeneralItemCreator.getInvBorder());
		inv.setItem(26, GeneralItemCreator.getInvBorder());
		
		inv.setItem(27, GeneralItemCreator.getInvBorder());
		inv.setItem(28, GeneralItemCreator.getInvBorder());
		inv.setItem(29, GeneralItemCreator.getInvBorder());
		inv.setItem(30, GeneralItemCreator.getInvBorder());
		inv.setItem(31, GeneralItemCreator.getInvBorder());
		inv.setItem(32, GeneralItemCreator.getInvBorder());
		inv.setItem(33, GeneralItemCreator.getInvBorder());
		inv.setItem(34, GeneralItemCreator.getInvBorder());
		inv.setItem(35, GeneralItemCreator.getInvBorder());
		
		ArrayList<GETrade> sellings = SellingManager.getSellings(pUUID);
		for(GETrade trade : sellings) {
			inv.setItem(trade.getSellSlot(), SellingItemCreator.getExistingSale(trade));
		}
		GeneralInventoryBuilder.fillInvWithItem(inv, SellingItemCreator.getAddSale());
		
		inv.setMaxStackSize(InvType.SELLING_PAGE.getID());
		return inv;
		
	}
	
	// Util Functions //
	
	public static double calculateCancelFee(double price) {
		return ItemUtils.roundToTwoDecimals(price*0.1);
	}
	
	public static GETrade getGETrade(ItemStack saleItem, UUID pUUID) {
		if(saleItem != null && saleItem.hasItemMeta() && saleItem.getItemMeta().hasCustomModelData()) {
			int data = saleItem.getItemMeta().getCustomModelData();
			return MarketManager.getTradeRecord(pUUID, data);
		}
		return null;
	}
	
}
