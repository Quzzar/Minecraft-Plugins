package com.quzzar.ge.core.buylist;

import java.io.Serializable;
import java.util.UUID;

import org.bukkit.inventory.ItemStack;

import com.quzzar.ge.core.ItemUtils;

public class GETrade implements Serializable {

	private static final long serialVersionUID = 4099653608771915138L;
	
	private SimpleItem item;
	private UUID sellerUUID;
	
	private int timeOnMarket; // In minutes
	private TradeStage stage;
	
	private double currentProfit;
	private double singleCost;
	
	private int sellSlot;
	
	public GETrade(SimpleItem item, int totalCost, UUID sellerUUID, int sellSlot) {
		
		this.item = item;
		this.singleCost = totalCost*1.0/item.getAmount();
		this.currentProfit = 0.0;
		this.sellerUUID = sellerUUID;
		this.timeOnMarket = 0;
		this.stage = TradeStage.ACTIVE;
		this.sellSlot = sellSlot;
		
	}
	
	public GETrade(ItemStack item, int totalCost, UUID sellerUUID, int sellSlot) {
		
		this.item = new SimpleItem(item);
		this.singleCost = totalCost*1.0/item.getAmount();
		this.currentProfit = 0.0;
		this.sellerUUID = sellerUUID;
		this.timeOnMarket = 0;
		this.stage = TradeStage.ACTIVE;
		this.sellSlot = sellSlot;
		
	}
	
	public SimpleItem getItem() {
		return item;
	}

	public double getSingleCost() {
		return ItemUtils.roundToTwoDecimals(singleCost);
	}
	
	public double getTotalCost() {
		return ItemUtils.roundToTwoDecimals(singleCost*item.getAmount());
	}
	
	public double getCurrentProfit() {
		return ItemUtils.roundToTwoDecimals(currentProfit);
	}
	
	public void setCurrentProfit(double newProfit) {
		this.currentProfit = ItemUtils.roundToTwoDecimals(newProfit);
	}
	
	public boolean hasProfit() {
		return currentProfit > 0.0;
	}

	public UUID getSellerUUID() {
		return sellerUUID;
	}

	public int getTimeOnMarket() {
		return timeOnMarket;
	}

	public TradeStage getStage() {
		return stage;
	}

	public int getSellSlot() {
		return sellSlot;
	}
	
	///
	
	public void setTimeOnMarket(int timeOnMarket) {
		this.timeOnMarket = timeOnMarket;
	}
	
	public void setTradeStage(TradeStage stage) {
		this.stage = stage;
		if(!stage.equals(TradeStage.ACTIVE)) {
			BuyListManager.getBuyList().removeBuyListEntry(this);
		}
	}
	
}
