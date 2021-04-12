package com.quzzar.ge.npcshop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.ge.presets.GeneralInventoryBuilder;
import com.quzzar.ge.presets.GeneralItemCreator;
import com.quzzar.ge.presets.buying.BuyingInventoryBuilder;

public class ShopBuilder {

	private static ItemStack tradingInfo = createBuyingInfo();
	
	public static final int NPC_TRADE_INV_ID = 6743;
	
	public static Inventory buildNPCShop(ShopInstance shop) {
		
		int shopID = ShopManager.getShopID(shop);
		
		Inventory inv = Bukkit.createInventory(null, 54, shop.getShopName());
		inv.setMaxStackSize(NPC_TRADE_INV_ID);
		
		inv.setItem(8, tradingInfo);
		BuyingInventoryBuilder.buildInvBorder(inv, GeneralItemCreator.getInvBorder());
		
		for(NPCTrade trade : shop.getShopInventory()) {
			inv.addItem(getTradeItem(trade, shopID));
		}
		
		GeneralInventoryBuilder.fillInv(inv);
		
		return inv;
		
	}
	
	
	
	///  ///
	
	private static ItemStack createBuyingInfo(){
		
		ItemStack i = new ItemStack(Material.PAPER);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.GOLD+"Info.");
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"Click on an item in this shop window");
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"to buy it and click on an item in your");
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"inventory to sell one. Shift clicking");
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"will buy or sell all possible.");
	    im.setLore(lore);
	    i.setItemMeta(im);
		
	    return i;
	}
	
	public static ItemStack getTradeItem(NPCTrade trade, int shopID) {
		
		ItemStack i = trade.getItem().toItemStack();
		
		ItemMeta im = i.getItemMeta();
		
		List<String> lore = new ArrayList<String>();
		
		if(trade.isOutOfStock()) {
			lore.add(ChatColor.RED+""+ChatColor.ITALIC+"Out of Stock!");
			
			lore.add(ChatColor.BLUE+"Sell ["+ChatColor.GREEN+"$"+trade.getBuyForPrice()+ChatColor.BLUE+"]");
			
		} else {
			lore.add(ChatColor.DARK_AQUA+"Qty: "+ChatColor.GOLD+trade.getQuantity());
			
			lore.add(ChatColor.BLUE+"Buy ["+ChatColor.GREEN+"$"+trade.getSellForPrice()+ChatColor.BLUE
					+"] "+ChatColor.DARK_PURPLE+"|"+ChatColor.BLUE+" Sell ["+ChatColor.GREEN+"$"+trade.getBuyForPrice()+ChatColor.BLUE+"]");
			
		}
		
	    im.setLore(lore);
	    
	    im.setCustomModelData(shopID);
	    
	    i.setItemMeta(im);
		
		return i;
	}
	
}
