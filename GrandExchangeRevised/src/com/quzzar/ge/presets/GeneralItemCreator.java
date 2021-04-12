package com.quzzar.ge.presets;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.ge.core.InvType;

public class GeneralItemCreator {

	private static ItemStack buyButton, sellButton, invFiller, invBorder;
	
	static {
		
		buyButton = createBuyButton();
		sellButton = createSellButton();
		
		invFiller = createInvFiller();
		invBorder = createInvBorder();
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Private - Create Items /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	
	private static ItemStack createBuyButton(){
		
		ItemStack i = new ItemStack(Material.NETHER_STAR);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+""+ChatColor.BLUE+"Buy");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createSellButton(){
		
		ItemStack i = new ItemStack(Material.NETHER_STAR);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+""+ChatColor.AQUA+"Sell");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createInvFiller(){
		
		ItemStack i = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+"");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createInvBorder(){
		
		ItemStack i = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+"");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Public - Get Items /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	
	public static ItemStack getBuyButton() {
		return buyButton;
	}
	
	public static ItemStack getSellButton() {
		return sellButton;
	}
	
	public static ItemStack getInvFiller() {
		return invFiller;
	}
	
	public static ItemStack getInvBorder() {
		return invBorder;
	}
	
	public static ItemStack getBackButton(InvType type){
		
		ItemStack i = new ItemStack(Material.SPRUCE_DOOR);
		
		ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"<- "+ChatColor.RESET+ChatColor.RED+"Back");
	    im.setCustomModelData(type.getID());
	    i.setItemMeta(im);
		
	    return i;
	}
	
}
