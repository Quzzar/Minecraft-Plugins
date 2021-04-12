package com.quzzar.ge.npcshop;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.entity.Villager;

import com.quzzar.ge.npcshop.namemachine.NameGenerator;

public class ShopManager {

	private static HashMap<Integer, ShopInstance> npcShop = new HashMap<Integer, ShopInstance>();
	private static NameGenerator nameGen = new NameGenerator();
	
	public static ShopInstance getShop(Villager villager) {
		int shopID = villager.getUniqueId().hashCode();
		ShopInstance shop = npcShop.get(shopID);
		
		if(villager.getCustomName() == null) {
			villager.setCustomName(nameGen.generateName().getFirstName());
			villager.setCustomNameVisible(true);
		}
		
		if(shop == null || !shop.getProfession().equals(villager.getProfession())) {
			npcShop.put(shopID, new ShopInstance(villager.getProfession(), villager.getCustomName()));
			shop = npcShop.get(shopID);
		}
		
		return shop;
	}
	
	public static ShopInstance getShop(int shopID) {
		return npcShop.get(shopID);
	}
	
	public static int getShopID(ShopInstance shop) {
		for (Entry<Integer, ShopInstance> entry : npcShop.entrySet()) {
	        if (shop.equals(entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return -1;
	}
	
}
