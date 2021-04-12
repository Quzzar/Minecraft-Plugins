package com.quzzar.ge.core;

import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemUtils {

	public static boolean addItemToInventory(Inventory inv, ItemStack i, Location loc){
		if(inventoryFull(inv, i)){
			loc.getWorld().dropItem(loc, i);
			return false;
		}else{
			inv.addItem(i);
			return true;
		}
	}
	
	public static boolean inventoryFull(Inventory inv, ItemStack item){
		boolean full = false;
		if(inv.firstEmpty()==-1){
			for(ItemStack i : inv.getContents()){
				if(i!=null){
					if(i.isSimilar(item)&&i.getAmount()<i.getMaxStackSize()){
						return false;
					}else{
						full = true;
					}
				}
			}
		}
		return full;
	}
	
	public static double roundToTwoDecimals(double d) {
		return Math.round(d * 100.0) / 100.0;
	}
	
}
