package com.quzzar.server.worldutils.mobs.mobdrops.drop;

import org.bukkit.inventory.ItemStack;

public class ItemDrop extends Drop {

	private ItemStack drop;
	private int minCount, maxCount;
	
	public ItemDrop(double chance, ItemStack drop, int minCount, int maxCount) {
		super(chance);
		this.drop = drop;
		this.minCount = minCount;
		this.maxCount = maxCount;
	}
	
	public ItemStack getDrop() {
		return drop;
	}
	
	public int getMin() {
		return minCount;
	}
	
	public int getMax() {
		return maxCount;
	}
	
}
