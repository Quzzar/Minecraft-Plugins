package com.quzzar.ge.presets;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.quzzar.ge.core.InvType;
import com.quzzar.ge.presets.buying.BuyingInventoryBuilder;
import com.quzzar.ge.presets.selling.SellingInventoryBuilder;

public class GeneralInventoryBuilder {

	// Used for back button //
	public static Inventory getInventoryByType(InvType type, UUID pUUID) {
		switch(type) {
			case MAIN_MENU: return buildMainMenu();
			case SELLING_PAGE: return SellingInventoryBuilder.buildSellingPage(pUUID);
			case BUYING_PAGE: return BuyingInventoryBuilder.getMainSection();
			case BLOCKS_PAGE: return BuyingInventoryBuilder.getBlocksSection();
			case BLOCKS_BUILDING_PAGE: return BuyingInventoryBuilder.getBlocksBuildingSubSection();
			case COMBAT_PAGE: return BuyingInventoryBuilder.getCombatSection();
			case TOOLS_PAGE: return BuyingInventoryBuilder.getToolsSection();
			case FOOD_PAGE: return BuyingInventoryBuilder.getFoodSection();
			case REDSTONE_PAGE: return BuyingInventoryBuilder.getRedstoneSection();
			case GENERAL_S_PAGE: return BuyingInventoryBuilder.getGeneralSuppliesSection();
			case MAGIC_S_PAGE: return BuyingInventoryBuilder.getMagicSuppliesSection();
			default: return null;
		}
	}
	
	//////////
	
	public static Inventory buildMainMenu() {
		
		Inventory inv = Bukkit.createInventory(null, InventoryType.DROPPER, ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"Grand Exchange");
		
		inv.setItem(3, GeneralItemCreator.getBuyButton());
		inv.setItem(5, GeneralItemCreator.getSellButton());
		
		fillInv(inv);
		
		inv.setMaxStackSize(InvType.MAIN_MENU.getID());
		return inv;
		
	}
	
	// Util Functions //
	
	public static void fillInv(Inventory inv) {
		for(int i=0; i<inv.getSize(); i++){
			
			if(inv.getItem(i)==null) {
				inv.setItem(i, GeneralItemCreator.getInvFiller());
			}
			
		}
	}
	
	public static void fillInvWithItem(Inventory inv, ItemStack item) {
		for(int i=0; i<inv.getSize(); i++){
			
			if(inv.getItem(i)==null) {
				inv.setItem(i, item);
			}
			
		}
	}
	
	public static void updateInventory(Inventory currentInv, Inventory newInv) {
		currentInv.clear();
		currentInv.setContents(newInv.getContents());
	}
	
}
