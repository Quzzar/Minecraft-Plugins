package com.quzzar.ge.core.buylist;

import java.io.Serializable;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class SimpleItem implements Serializable {

	private static final long serialVersionUID = 4099653608771915138L;

	private Material material;
	private int amount;
	private String displayName;
	
	public SimpleItem(Material material, int amount, String displayName) {
		this.material = material;
		this.amount = amount;
		this.displayName = displayName;
	}
	
	public SimpleItem(ItemStack item) {
		this.material = item.getType();
		this.amount = item.getAmount();
		if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			this.displayName = item.getItemMeta().getDisplayName();
		}
	}
	
	public SimpleItem(ItemStack item, int amount) {
		this.material = item.getType();
		this.amount = amount;
		if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			this.displayName = item.getItemMeta().getDisplayName();
		}
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public SimpleItem setAmount(int amount) {
		this.amount = amount;
		return this;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public boolean hasDisplayName() {
		return displayName != null;
	}
	
	public ItemStack toItemStack() {
		ItemStack i = new ItemStack(material);
		i.setAmount(amount);
		if(hasDisplayName()) {
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(displayName);
			i.setItemMeta(im);
		}
		return i;
	}
	
	public SimpleItem singleItem() {
		return new SimpleItem(material, 1, displayName);
	}
	
	@Override
	public int hashCode() {
		int displayNameHashCode = (displayName == null) ? 1 : displayName.hashCode();
	    return (int) amount * material.hashCode() * displayNameHashCode;
	}
	
	@Override
	public boolean equals(Object other){
		if(!(other instanceof SimpleItem)) {return false;}
		SimpleItem otherItem = (SimpleItem) other;
		if(otherItem.material == this.material && otherItem.amount == this.amount) {
			if(otherItem.displayName == null || this.displayName == null) {
				return otherItem.displayName == this.displayName;
			} else {
				return otherItem.displayName.equals(this.displayName);
			}
		} else {
			return false;
		}
	}
	
	public static boolean canBeSimple(ItemStack item) {
		
		if(item == null) {return false;}
		
		if(item.hasItemMeta()) {
			
			ItemMeta im = item.getItemMeta();
			
			if(im instanceof Damageable) {
				if(((Damageable) im).hasDamage()){
					return false;
				}
			}
			
			if(im.hasEnchants()) {
				return false;
			}
			
			if(im.getItemFlags() != null) {
				if(!im.getItemFlags().isEmpty()) {
					return false;
				}
			}
			
			if(im.hasCustomModelData()) {
				return false;
			}
			
			if(im.hasLore()) {
				return false;
			}
			
			return true;
			
		} else {
			return true;
		}
		
	}
	
}
