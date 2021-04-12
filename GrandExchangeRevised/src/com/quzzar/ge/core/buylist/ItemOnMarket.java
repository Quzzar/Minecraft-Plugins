package com.quzzar.ge.core.buylist;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ItemOnMarket {

	private TreeMap<Double, ArrayList<GETrade>> allTrades = new TreeMap<Double, ArrayList<GETrade>>();
	
	public void addTrade(GETrade trade) {
		ArrayList<GETrade> tradesOfPrice = allTrades.get(trade.getSingleCost());
		if (tradesOfPrice == null) {
			tradesOfPrice = new ArrayList<GETrade>();
		}
		tradesOfPrice.add(trade);
		allTrades.put(trade.getSingleCost(), tradesOfPrice);
	}
	
	public void removeTrade(GETrade trade) {
		ArrayList<GETrade> tradesOfPrice = allTrades.get(trade.getSingleCost());
		if(tradesOfPrice != null) {
			
			tradesOfPrice.remove(trade);
			
			if(tradesOfPrice.isEmpty()) {
				allTrades.remove(trade.getSingleCost());
			}
			
		}
	}
	
	public GETrade getCheapestTrade() {
		if(allTrades.isEmpty()) {return null;}
		Entry<Double, ArrayList<GETrade>> cheapestTrades = allTrades.firstEntry();
		return cheapestTrades.getValue().get(0); // Get first trade of cheapest trades
	}
	
	public int getAmountOfTrades() {
		return allTrades.size();
	}
	
	
}
