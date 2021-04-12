package com.quzzar.ge.core;

import java.util.ArrayList;
import java.util.UUID;

import com.quzzar.ge.core.buylist.GETrade;

public class SellingManager {

	public static ArrayList<GETrade> getSellings(UUID pUUID){
		
		ArrayList<GETrade> sellings = new ArrayList<GETrade>();
		
		for(GETrade trade : MarketManager.getAllTrades()) {
			if(trade.getSellerUUID().equals(pUUID)) {
				sellings.add(trade);
			}
		}
		
		return sellings;
		
	}
	
}
