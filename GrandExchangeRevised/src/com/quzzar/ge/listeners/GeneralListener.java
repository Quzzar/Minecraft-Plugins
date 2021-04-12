package com.quzzar.ge.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryCloseEvent.Reason;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import com.quzzar.ge.ChatUtils;
import com.quzzar.ge.GrandExchangeMain;
import com.quzzar.ge.core.InvCategory;
import com.quzzar.ge.core.InvType;
import com.quzzar.ge.core.ItemUtils;
import com.quzzar.ge.core.MarketManager;
import com.quzzar.ge.core.buylist.GETrade;
import com.quzzar.ge.core.buylist.TradeStage;
import com.quzzar.ge.listeners.handlers.BackBtnHandler;
import com.quzzar.ge.listeners.handlers.MainMenuHandler;
import com.quzzar.ge.listeners.handlers.buying.BuyingAreaHandler;
import com.quzzar.ge.listeners.handlers.buying.BuyingHandler;
import com.quzzar.ge.listeners.handlers.selling.ActiveSaleHandler;
import com.quzzar.ge.listeners.handlers.selling.EmptySaleHandler;
import com.quzzar.ge.listeners.handlers.selling.ExpiredSaleHandler;
import com.quzzar.ge.listeners.handlers.selling.SellingHandler;
import com.quzzar.ge.listeners.handlers.selling.SoldSaleHandler;
import com.quzzar.ge.presets.selling.SellingInventoryBuilder;

public class GeneralListener implements Listener {
	
	private static final int UPDATE_FREQ = 30; // In minutes
	
	public static void runClock() {
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(GrandExchangeMain.instance, new Runnable() {
			
			public void run() {
				
				// Set old trades to expired
				for(GETrade trade : MarketManager.getAllTrades()) {
					trade.setTimeOnMarket(trade.getTimeOnMarket()+UPDATE_FREQ);
					if(trade.getTimeOnMarket() > MarketManager.EXPIRE_TIME) {
						trade.setTradeStage(TradeStage.EXPIRED);
					}
				}
				
				// Auto-save
				ChatUtils.tellConsole("Autosaving...");
				MarketManager.saveTrades();
				
			}
		
		}, UPDATE_FREQ*1200, UPDATE_FREQ*1200); // Every UPDATE_FREQ minutes
		
	}
	
	@EventHandler
    public void onInventoryClick(InventoryClickEvent e){
		
		Inventory inv = e.getInventory();
		ItemStack item = e.getCurrentItem();
		InventoryView invView = e.getView();
		HumanEntity he = e.getWhoClicked();
		Inventory clickedInv = e.getClickedInventory();
		
		if(inv == null) {
			return;
		}
		
		InvType type = InvType.getFromInventory(inv);
		
		if(type.equals(InvType.NULL)) {
			return;
		}
		
		e.setCancelled(true); // <- Cancel any click event, in some situations it will be un-cancelled
		
		if(item == null || invView == null || he == null) {
			return;
		}
		
		// Handle if it is a back button
		if(inv == clickedInv && BackBtnHandler.isBackBtn(item, he, type, inv)) {
			return;
		}
		
		
		if(type.equals(InvType.MAIN_MENU)) {
			if(inv == clickedInv) {
				MainMenuHandler.handleMainMenu(item, he);
			}
			return;
		}
		
		if(type.getCategory().equals(InvCategory.BUYING)) {
			if(inv == clickedInv) {
				BuyingHandler.handleBuying(type, item, he);
			}
			return;
		}
		
		if(type.getCategory().equals(InvCategory.BUYING_AREA)) {
			if(inv == clickedInv) {
				BuyingAreaHandler.handleBuyingArea(type, item, he, inv, e.getClick());
			}
			return;
		}
		
		if(type.getCategory().equals(InvCategory.SELLING)) {
			switch(type) {
			case SELLING_PAGE:
				if(inv == clickedInv) {SellingHandler.handleSellingPage(item, he, e.getSlot());}
				return;
			case SALE_EMPTY:
				EmptySaleHandler.handleEmptySale(item, he, inv, clickedInv, e);
				return;
			case SALE_ACTIVE:
				if(inv == clickedInv) {ActiveSaleHandler.handleActiveSale(item, he, inv);}
				return;
			case SALE_FAILED:
				if(inv == clickedInv) {ExpiredSaleHandler.handleExpiredSale(item, he, inv);}
				return;
			case SALE_SOLD:
				if(inv == clickedInv) {SoldSaleHandler.handleSoldSale(item, he, inv);}
				return;
			default:
				return;
			}
		}
		
	}
	
	
	
	
	// If player closes the inventory while selling an item, give item back to them
	@EventHandler
    public void onInventoryClose(InventoryCloseEvent e){
		
		// If inv is being closed by plugin, it's fine // 
		if(e.getReason().equals(Reason.PLUGIN)) {
			return;
		}
		
		Inventory inv = e.getInventory();
		InvType type = InvType.getFromInventory(inv);
		
		// Only if InvType is Sale_Empty //
		if(!type.equals(InvType.SALE_EMPTY)) {
			return;
		}
		
		ItemStack sellingItem = inv.getItem(SellingInventoryBuilder.INPUT_ITEM_SLOT);
		
		// Only if there's an item in slot //
		if(sellingItem == null) {
			return;
		}
		
		ItemUtils.addItemToInventory(e.getPlayer().getInventory(), sellingItem.clone(), e.getPlayer().getLocation());
		
		sellingItem.setAmount(0); // < To confirm its destruction
		
	}

}
