package com.quzzar.ge.core.buylist;

import java.util.HashMap;

public class BuyListManager {

	private static BuyList buyList;
	
	public static void init() {
		
		buyList = new BuyList();
		
		buyList.updateEntireBuyList();
		
	}
	
	public static BuyList getBuyList() {
		return buyList;
	}
	
	public static HashMap<SimpleItem, ItemOnMarket> getData() {
		return buyList.getBuyList();
	}
	
}
