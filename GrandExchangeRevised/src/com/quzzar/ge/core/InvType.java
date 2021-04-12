package com.quzzar.ge.core;

import org.bukkit.inventory.Inventory;

public enum InvType {
	
	NULL(InvCategory.NONE),
	MAIN_MENU(InvCategory.NONE),
	
	// Selling Pages
	SELLING_PAGE(InvCategory.SELLING),
	SALE_EMPTY(InvCategory.SELLING),
	SALE_ACTIVE(InvCategory.SELLING),
	SALE_SOLD(InvCategory.SELLING),
	SALE_FAILED(InvCategory.SELLING),
	
	// Buying Pages
	BUYING_PAGE(InvCategory.BUYING),
	
	BLOCKS_PAGE(InvCategory.BUYING),
	COMBAT_PAGE(InvCategory.BUYING),
	TOOLS_PAGE(InvCategory.BUYING),
	FOOD_PAGE(InvCategory.BUYING),
	REDSTONE_PAGE(InvCategory.BUYING),
	GENERAL_S_PAGE(InvCategory.BUYING),
	MAGIC_S_PAGE(InvCategory.BUYING),
	
	BLOCKS_STORAGE(InvCategory.BUYING_AREA),
	BLOCKS_WORKTABLES(InvCategory.BUYING_AREA),
	BLOCKS_DECORATIONS(InvCategory.BUYING_AREA),
	BLOCKS_ORGANIC(InvCategory.BUYING_AREA),
	BLOCKS_BUILDING_PAGE(InvCategory.BUYING),
	
	BLOCKS_BUILDING_WOOD(InvCategory.BUYING_AREA),
	BLOCKS_BUILDING_STONE(InvCategory.BUYING_AREA),
	BLOCKS_BUILDING_GLASS(InvCategory.BUYING_AREA),
	BLOCKS_BUILDING_MISC(InvCategory.BUYING_AREA),
	
	COMBAT_ARMOR(InvCategory.BUYING_AREA),
	COMBAT_MISC_ARMOR(InvCategory.BUYING_AREA),
	COMBAT_MELEE_WEAPONS(InvCategory.BUYING_AREA),
	COMBAT_RANGED_WEAPONS(InvCategory.BUYING_AREA),
	COMBAT_ARROWS(InvCategory.BUYING_AREA),
	
	TOOLS_GENERAL(InvCategory.BUYING_AREA),
	TOOLS_TRANSPORT(InvCategory.BUYING_AREA),
	TOOLS_MISC(InvCategory.BUYING_AREA),
	
	FOOD_PREPARED(InvCategory.BUYING_AREA),
	FOOD_RAW(InvCategory.BUYING_AREA),
	
	REDSTONE_GENERAL(InvCategory.BUYING_AREA),
	REDSTONE_PLATES(InvCategory.BUYING_AREA),
	REDSTONE_BUTTONS(InvCategory.BUYING_AREA),
	REDSTONE_DOORS(InvCategory.BUYING_AREA),
	REDSTONE_RAILS(InvCategory.BUYING_AREA),
	
	G_SUPPLIES_CRAFTING(InvCategory.BUYING_AREA),
	G_SUPPLIES_FARMING(InvCategory.BUYING_AREA),
	G_SUPPLIES_DYES(InvCategory.BUYING_AREA),
	G_SUPPLIES_DISCS(InvCategory.BUYING_AREA),
	
	M_SUPPLIES_WARDING(InvCategory.BUYING_AREA),
	M_SUPPLIES_ALCHEMY(InvCategory.BUYING_AREA),
	
	;
	
	private int id;
	private InvCategory category;
	
	public int getID() {
		return id;
	}
	
	public InvCategory getCategory() {
		return category;
	}
	
	private InvType(InvCategory category) {
		this.category = category;
	}
	
	static {
		
		int INT_COUNT = 3000;
		
		for(InvType type : InvType.values()) {
			type.id = INT_COUNT;
			INT_COUNT++;
		}
		
	}
	
	public static InvType getFromID(int id) {
		for(InvType type : InvType.values()) {
			if(type.getID() == id) {
				return type;
			}
		}
		return InvType.NULL;
	}
	
	public static InvType getFromInventory(Inventory inv) {
		if(inv == null) {return InvType.NULL;}
		for(InvType type : InvType.values()) {
			if(type.getID() == inv.getMaxStackSize()) {
				return type;
			}
		}
		return InvType.NULL;
	}
	
}
