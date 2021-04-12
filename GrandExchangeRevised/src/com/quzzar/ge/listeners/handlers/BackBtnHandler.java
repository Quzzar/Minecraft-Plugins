package com.quzzar.ge.listeners.handlers;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.ge.core.InvType;
import com.quzzar.ge.core.ItemUtils;
import com.quzzar.ge.presets.GeneralInventoryBuilder;
import com.quzzar.ge.presets.GeneralItemCreator;
import com.quzzar.ge.presets.selling.SellingInventoryBuilder;

public class BackBtnHandler {
	
	public static boolean isBackBtn(ItemStack item, HumanEntity he, InvType type, Inventory openedInv) {
		
		if(item.hasItemMeta() && item.getItemMeta().hasCustomModelData()) {
			
			ItemStack backBtnGeneric = GeneralItemCreator.getBackButton(InvType.NULL);
			int dataID = item.getItemMeta().getCustomModelData();
			
			ItemMeta genericMeta = backBtnGeneric.getItemMeta();
			genericMeta.setCustomModelData(dataID);
			backBtnGeneric.setItemMeta(genericMeta);
			
			if(item.equals(backBtnGeneric)) {
				
				Inventory inv = GeneralInventoryBuilder.getInventoryByType(InvType.getFromID(dataID), he.getUniqueId());
				
				if(inv == null) {
					return false;
				}
				
				handleIfEmptySale(he, type, openedInv); // < If empty sale, drop any item in item slot
				
				he.closeInventory();
				he.openInventory(inv);
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	private static void handleIfEmptySale(HumanEntity he, InvType type, Inventory openedInv) {
		
		if(!type.equals(InvType.SALE_EMPTY)) {
			return;
		}
		
		ItemStack inputItem = openedInv.getItem(SellingInventoryBuilder.INPUT_ITEM_SLOT);
		
		if(inputItem == null) {
			return;
		}
		
		ItemUtils.addItemToInventory(he.getInventory(), inputItem.clone(), he.getLocation());
		
		inputItem.setAmount(0); // < To confirm its destruction
		
	}
	
}
