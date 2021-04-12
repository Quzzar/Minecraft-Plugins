package com.quzzar.ge.presets.selling;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.ge.core.buylist.GETrade;

public class SellingItemCreator {
	
	private static ItemStack addSale, increaseBtn, decreaseBtn, sellingInfo, expireInfo;
	
	static {
		
		addSale = createAddSale();
		
		increaseBtn = createIncreaseBtn();
		decreaseBtn = createDecreaseBtn();
		
		sellingInfo = createSellingInfo();
		expireInfo = createExpireInfo();
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Private - Create Items /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	
	private static ItemStack createAddSale() {
		
		ItemStack i = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(
	    		ChatColor.GREEN+""+ChatColor.BOLD+">"+ChatColor.ITALIC
	    		+" Click to Sell Item "+ChatColor.RESET+ChatColor.GREEN+""+ChatColor.BOLD+"<");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createIncreaseBtn(){
		
		ItemStack i = new ItemStack(Material.SPRUCE_BUTTON);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+"Increase Price");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createDecreaseBtn(){
		
		ItemStack i = new ItemStack(Material.SPRUCE_BUTTON);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+"Decrease Price");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createSellingInfo(){
		
		ItemStack i = new ItemStack(Material.PAPER);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.GOLD+"Info.");
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"Welcome to your selling page!");
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"Here you can put items up for");
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"sale at prices you set. You");
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"only have a limited number of");
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"selling slots, so sell wisely!");
	    im.setLore(lore);
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createExpireInfo(){
		
		ItemStack i = new ItemStack(Material.PAPER);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.GOLD+"Info.");
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"Make sure you collect your items");
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"from expired sales! After a while,");
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"any expired sales will be removed.");
	    im.setLore(lore);
	    i.setItemMeta(im);
		
	    return i;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Public - Get Items /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	
	public static ItemStack getAddSale() {
		return addSale;
	}
	
	public static ItemStack getExistingSale(GETrade trade){
		
		ItemStack i = trade.getItem().toItemStack();
	    ItemMeta im = i.getItemMeta();
	    List<String> lore = new ArrayList<String>();
	    
	    switch(trade.getStage()) {
			case ACTIVE:
				if(trade.hasProfit()) {
			    	lore.add(ChatColor.DARK_AQUA+"Status: "+ChatColor.GREEN+"Partially Sold");
			    } else {
			    	lore.add(ChatColor.DARK_AQUA+"Status: "+ChatColor.GRAY+"Listed");
			    }
				break;
			case SOLD: lore.add(ChatColor.DARK_AQUA+"Status: "+ChatColor.GREEN+"Sold"); break;
			case EXPIRED: lore.add(ChatColor.DARK_AQUA+"Status: "+ChatColor.RED+"Expired"); break;
			default: lore.add(ChatColor.DARK_AQUA+"Status: "+ChatColor.RED+"NULL"); break;
	    }
	    
	    if(i.getAmount() > 1) {
			lore.add(ChatColor.BLUE+"Price ["+ChatColor.YELLOW+"Single: "+ChatColor.GREEN+"$"+trade.getSingleCost()
				+ChatColor.DARK_PURPLE+" | "+ChatColor.YELLOW+"Stack: "+ChatColor.GREEN+"$"+trade.getTotalCost()+ChatColor.BLUE+"]");
		} else {
			lore.add(ChatColor.BLUE+"Price ["+ChatColor.GREEN+"$"+trade.getTotalCost()+ChatColor.BLUE+"]");
		}
	    lore.add(ChatColor.GREEN+""+ChatColor.BOLD+">"+ChatColor.ITALIC
	    		+" Click to View / Cancel "+ChatColor.RESET+ChatColor.GREEN+""+ChatColor.BOLD+"<");
	    im.setLore(lore);
	    i.setItemMeta(im);
		
	    return i;
	}
	
	public static ItemStack getSaleItem(GETrade trade){
		
		ItemStack i = trade.getItem().toItemStack();
		
		ItemMeta im = i.getItemMeta();
	    im.setCustomModelData(trade.getSellSlot());
	    i.setItemMeta(im);
		
	    return i;
	}
	
	public static final Material confirm_material = Material.LIME_DYE;
	public static ItemStack getConfirmItem(int sellSlot){
		
		ItemStack i = new ItemStack(confirm_material);
		
		ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.GREEN+"Confirm");
	    im.setCustomModelData(sellSlot);
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.YELLOW+""+ChatColor.ITALIC+"Note: Make sure you don't price");
	    lore.add(ChatColor.YELLOW+""+ChatColor.ITALIC+"an item for more than you think");
	    lore.add(ChatColor.YELLOW+""+ChatColor.ITALIC+"it will sell for. There is a 10%");
	    lore.add(ChatColor.YELLOW+""+ChatColor.ITALIC+"fee to cancel an active sale!");
	    im.setLore(lore);
	    i.setItemMeta(im);
		
	    return i;
	}
	
	public static final Material price_tag_material = Material.NAME_TAG;
	public static ItemStack getPriceTag(double totalPrice){
		
		ItemStack i = new ItemStack(price_tag_material);
		
		ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.GREEN+"$"+totalPrice);
	    im.setCustomModelData((int)totalPrice);
	    i.setItemMeta(im);
		
	    return i;
	}
	
	public static ItemStack getIncreaseBtn() {
		return increaseBtn;
	}
	
	public static ItemStack getDecreaseBtn() {
		return decreaseBtn;
	}
	
	public static final Material claim_profit_material = Material.EMERALD;
	public static ItemStack getClaimProfitItem(double profit){
		
		ItemStack i = new ItemStack(claim_profit_material);
		
		ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"Claim "+ChatColor.RESET+ChatColor.GREEN+"$"+profit);
	    i.setItemMeta(im);
		
	    return i;
	}
	
	public static final Material remove_material = Material.BARRIER;
	public static ItemStack getRemoveItem(double fee){
		
		ItemStack i = new ItemStack(remove_material);
		
		ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Remove");
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.YELLOW+""+ChatColor.ITALIC+"Cancellation Fee: "+ChatColor.RED+"$"+fee);
	    im.setLore(lore);
	    i.setItemMeta(im);
		
	    return i;
	}
	public static ItemStack getRemoveItemNoFee(){
		
		ItemStack i = new ItemStack(remove_material);
		
		ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.RED+"Remove");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	public static ItemStack getSellingInfo() {
		return sellingInfo;
	}
	
	public static ItemStack getExpireInfo() {
		return expireInfo;
	}
	
	
}
