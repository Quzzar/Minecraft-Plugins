package com.quzzar.ge.npcshop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Villager;

public class ShopInstance {
	
	private Villager.Profession profession;
	private String shopName;
	private List<NPCTrade> shopInv;
	
	public ShopInstance(Villager.Profession profession, String villagerName) {
		
		this.profession = profession;
		this.shopName = villagerName+"'s Shop";
		this.shopInv = new ArrayList<NPCTrade>();
		
		double pAdj = getPAdj(villagerName);
		for(NPCTrade trade : NPCTradeManager.getNPCTrades().get(profession)) {
			shopInv.add(trade.clone(pAdj));
		}
		
	}
	
	public List<NPCTrade> getShopInventory(){
		return shopInv;
	}
	
	public String getShopName() {
		return shopName;
	}
	
	public Villager.Profession getProfession(){
		return profession;
	}
	
	//// Calculated a Variation in Prices Based Off Name ////
	private static double getPAdj(String villagerName) {
		
		int i = 0;
		for(char c : villagerName.toCharArray()) {
			i += c;
		}
		
		int maxSize = villagerName.length()*125;
		int minSize = villagerName.length()*97;
		
		double pAdj = (0.8)/(maxSize-minSize)*(i-maxSize)+0.7;
		
		return pAdj;
	}
	
}
