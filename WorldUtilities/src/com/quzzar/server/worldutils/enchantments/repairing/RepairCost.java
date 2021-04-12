package com.quzzar.server.worldutils.enchantments.repairing;

import org.bukkit.Material;

public class RepairCost {

	private Material material;
	private Material itemType;
	private String nameIncludes;
	private double pPerItem;
	private String pluralAdder;
	
	public RepairCost(Material itemType, Material material, double pPerItem) {
		this.material = material;
		this.itemType = itemType;
		this.pPerItem = pPerItem;
		this.nameIncludes = null;
		this.pluralAdder = "s";
	}
	
	public RepairCost(Material itemType, Material material, double pPerItem, String nameIncludes) {
		this.material = material;
		this.itemType = itemType;
		this.pPerItem = pPerItem;
		this.nameIncludes = nameIncludes;
		this.pluralAdder = "s";
	}
	
	public RepairCost(Material itemType, Material material, double pPerItem, String nameIncludes, String pluralAdder) {
		this.material = material;
		this.itemType = itemType;
		this.pPerItem = pPerItem;
		this.nameIncludes = nameIncludes;
		this.pluralAdder = pluralAdder;
	}

	public Material getMaterialCost() {
		return material;
	}

	public Material getItemType() {
		return itemType;
	}
	
	public boolean hasNameIncludes() {
		return nameIncludes != null;
	}

	public String getNameIncludes() {
		return nameIncludes;
	}

	public double getPercentPerItem() {
		return pPerItem;
	}
	
	public String getPluralAdder() {
		return pluralAdder;
	}
	
}
