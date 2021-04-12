package com.quzzar.server.dominions.upgrades;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GenericUpgrade {

	private UpgradeType type;
	private GenericUpgrade nextUpgrade = null;
	private ItemStack itemIcon;
	private int cost;
	private String name;
	
	public GenericUpgrade(UpgradeType type, String name, String description, int cost, ItemStack itemIcon) {
		this.type = type;
		this.cost = cost;
		this.name = name;
		
		ItemMeta im = itemIcon.getItemMeta();
		im.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+name);
		
		String[] descriptionLines = description.split("@");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD+""+ChatColor.BOLD+"  $"+cost
				+ChatColor.LIGHT_PURPLE+""+ChatColor.ITALIC+"  Click to Buy");
		for(String descLine : descriptionLines) {
			lore.add(ChatColor.AQUA+descLine);
		}
		im.setLore(lore);
		
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		im.addItemFlags(ItemFlag.HIDE_DESTROYS);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		im.addItemFlags(ItemFlag.HIDE_PLACED_ON);
		im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		
		itemIcon.setItemMeta(im);
		
		this.itemIcon = itemIcon;
		
	}
	
	public UpgradeType getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public ItemStack getItemIcon() {
		return itemIcon;
	}
	
	public GenericUpgrade getNextUpgrade() {
		return nextUpgrade;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setNextUpgrade(GenericUpgrade nextUpgrade) {
		this.nextUpgrade = nextUpgrade;
	}
	
	
	
}
