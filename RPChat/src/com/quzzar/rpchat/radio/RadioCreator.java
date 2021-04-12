package com.quzzar.rpchat.radio;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.rpchat.RPChat;
import com.quzzar.rpchat.radio.misc.ItemType;

public class RadioCreator {

	private static HashMap<ItemType, ItemStack> itemMap = new HashMap<ItemType, ItemStack>();
	
	public static void initialize(RPChat main) {
		
		itemMap.put(ItemType.SENDING_SHELL, Radio(main));
		
	}
	
	public static HashMap<ItemType, ItemStack> getItemMap(){
		return itemMap;
	}
	
	public static ItemStack getItemClone(ItemType itemType){
		return itemMap.get(itemType).clone();
	}
	
	public static boolean isSimilar(ItemStack checkingItem, ItemType itemType){
		return itemMap.get(itemType).isSimilar(checkingItem);
	}
	
	public static boolean isFairlySimilar(ItemStack checkingItem, ItemType itemType) {
		
		ItemStack item1 = checkingItem;
		ItemStack item2 = itemMap.get(itemType);
		
		// If they have lore, clear it
		if(item1.hasItemMeta() && item1.getItemMeta().hasLore()) {
			item1 = item1.clone();
			ItemMeta im = item1.getItemMeta();
			im.setLore(null);
			item1.setItemMeta(im);
		}
		
		if(item2.hasItemMeta() && item2.getItemMeta().hasLore()) {
			item2 = item2.clone();
			ItemMeta im = item2.getItemMeta();
			im.setLore(null);
			item2.setItemMeta(im);
		}
		
		return item1.isSimilar(item2);
	}
	
	
	///////////////////////////////////////////////////////////////////////////////
	// All the item functions (also adds recipe to craft them). Called only once //
	///////////////////////////////////////////////////////////////////////////////
	
	private static ItemStack Radio(RPChat main){
		
		ItemStack i = new ItemStack(Material.NAUTILUS_SHELL);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.LIGHT_PURPLE+"Sending Shell");
	    
	    im.addEnchant(Enchantment.DURABILITY, 1, false);
	    
	    im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	    
	    i.setItemMeta(im);
	    
	    ShapelessRecipe shless = new ShapelessRecipe(new NamespacedKey(main,"SendingShell"),i);
	    shless.addIngredient(Material.IRON_INGOT);
	    shless.addIngredient(Material.IRON_INGOT);
	    shless.addIngredient(Material.REDSTONE);
	    shless.addIngredient(Material.NOTE_BLOCK);
	    shless.addIngredient(Material.NAUTILUS_SHELL);
	    main.getServer().addRecipe(shless);
	    
	    return i;
	}
	
}
