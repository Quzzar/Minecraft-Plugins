package com.quzzar.ge.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import com.quzzar.ge.ChatUtils;
import com.quzzar.ge.GrandExchangeMain;
import com.quzzar.ge.core.buylist.BuyListManager;
import com.quzzar.ge.core.buylist.GETrade;

public class MarketManager {
	
	private static LinkedList<GETrade> trades;
	
	// In minutes,
	public static final int REMOVAL_TIME = 19000; // ~ 16 days
	public static final int EXPIRE_TIME = 3600; // ~ 3 days
	
	@SuppressWarnings("unchecked")
	public static void loadTrades() {
		
		try {
	        FileInputStream fis = new FileInputStream(
	        		GrandExchangeMain.instance.getDataFolder()+"/market.data");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        trades = (LinkedList<GETrade>) ois.readObject();
	        ois.close();
		} catch (Exception e) {
			ChatUtils.tellConsole(ChatColor.RED+"Failed to load GE Market files!");
			ChatUtils.tellConsole(ChatColor.RED+"Creating new ones...");
			
			trades = new LinkedList<GETrade>();
		}
		
		// Remove all Very Old trades //
		ArrayList<GETrade> deletingTrades = new ArrayList<GETrade>();
		for(GETrade trade : trades) {
			if(trade.getTimeOnMarket() > REMOVAL_TIME) {
				deletingTrades.add(trade);
			}
		}
		trades.removeAll(deletingTrades);
		
	}
	
	public static void saveTrades() {
		
		try {
			FileOutputStream fos = new FileOutputStream(
					GrandExchangeMain.instance.getDataFolder()+"/market.data");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(trades);
	        oos.close();
		} catch (IOException e) {
			ChatUtils.tellConsole(ChatColor.RED+"Failed to save GE Market files!");
		}
		
	}
	
	///
	
	public static void createTradeRecord(ItemStack item, int cost, UUID pUUID, int sellSlot) {
		GETrade trade = new GETrade(item, cost, pUUID, sellSlot);
		trades.add(trade);
		BuyListManager.getBuyList().addBuyListEntry(trade);
	}
	
	public static void deleteTradeRecord(GETrade trade) {
		GETrade foundTrade = getTradeRecord(trade.getSellerUUID(), trade.getSellSlot());
		trades.remove(foundTrade);
		BuyListManager.getBuyList().removeBuyListEntry(trade);
	}
	
	///
	
	public static LinkedList<GETrade> getAllTrades(){
		return trades;
	}
	
	///
	
	public static GETrade getTradeRecord(UUID pUUID, int sellSlot) {
		for(GETrade trade : trades) {
			if(trade.getSellerUUID().equals(pUUID) && trade.getSellSlot()==sellSlot) {
				return trade;
			}
		}
		return null;
	}
	
}
