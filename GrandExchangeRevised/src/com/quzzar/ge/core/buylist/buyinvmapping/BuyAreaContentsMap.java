package com.quzzar.ge.core.buylist.buyinvmapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.quzzar.ge.core.InvType;
import com.quzzar.ge.core.buylist.ItemTag;
import com.quzzar.ge.core.buylist.SimpleItem;

public class BuyAreaContentsMap {

	private static HashMap<InvType, BuyAreaInstance> invContentsMap;
	
	public static void init() {
		
		invContentsMap = new HashMap<InvType, BuyAreaInstance>();
		
		
		List<SimpleItem> building_wood_list = new ArrayList<SimpleItem>();
		building_wood_list.addAll(ItemTag.LOGS.getList());
		invContentsMap.put(InvType.BLOCKS_BUILDING_WOOD, 
				new BuyAreaInstance(building_wood_list, "Buying > Blocks > Building > Wood", InvType.BLOCKS_BUILDING_PAGE));
		
		List<SimpleItem> building_stone_list = new ArrayList<SimpleItem>();
		building_stone_list.addAll(ItemTag.STONE_BRICKS.getList()); building_stone_list.addAll(ItemTag.STONE_LIKE.getList());
		invContentsMap.put(InvType.BLOCKS_BUILDING_STONE, 
				new BuyAreaInstance(building_stone_list, "Buying > Blocks > Building > Stone", InvType.BLOCKS_BUILDING_PAGE));
		
		invContentsMap.put(InvType.BLOCKS_BUILDING_GLASS, 
				new BuyAreaInstance(ItemTag.GLASS.getList(), "Buying > Blocks > Building > Glass", InvType.BLOCKS_BUILDING_PAGE));
		
		invContentsMap.put(InvType.BLOCKS_BUILDING_MISC, 
				new BuyAreaInstance(ItemTag.MISC_BLOCKS.getList(), "Buying > Blocks > Building > Misc", InvType.BLOCKS_BUILDING_PAGE));
		
		///
		
		List<SimpleItem> organic_list = new ArrayList<SimpleItem>();
		organic_list.addAll(ItemTag.DIRTS.getList());
		organic_list.addAll(ItemTag.SAPLINGS.getList()); organic_list.addAll(ItemTag.FLOWERS.getList());
		invContentsMap.put(InvType.BLOCKS_ORGANIC, 
				new BuyAreaInstance(organic_list, "Buying > Blocks > Organic", InvType.BLOCKS_PAGE));
		
		invContentsMap.put(InvType.BLOCKS_DECORATIONS, 
				new BuyAreaInstance(ItemTag.DECORATIONS.getList(), "Buying > Blocks > Decorations", InvType.BLOCKS_PAGE));
		
		invContentsMap.put(InvType.BLOCKS_WORKTABLES, 
				new BuyAreaInstance(ItemTag.WORK_TABLES_ADVANCED.getList(), "Buying > Blocks > Worktables", InvType.BLOCKS_PAGE));
		
		invContentsMap.put(InvType.BLOCKS_STORAGE,
				new BuyAreaInstance(ItemTag.STORAGE.getList(), "Buying > Blocks > Storage", InvType.BLOCKS_PAGE));
		
		///
		
		List<SimpleItem> armor_list = new ArrayList<SimpleItem>();
		armor_list.addAll(ItemTag.ARMOR_LEATHER.getList()); armor_list.addAll(ItemTag.ARMOR_CHAINMAIL.getList());
		armor_list.addAll(ItemTag.ARMOR_IRON.getList()); armor_list.addAll(ItemTag.ARMOR_GOLDEN.getList());
		armor_list.addAll(ItemTag.ARMOR_DIAMOND.getList());
		invContentsMap.put(InvType.COMBAT_ARMOR,
				new BuyAreaInstance(armor_list, "Buying > Combat > Armor", InvType.COMBAT_PAGE));
		
		List<SimpleItem> misc_armor_list = new ArrayList<SimpleItem>();
		misc_armor_list.addAll(ItemTag.MISC_ARMOR.getList()); misc_armor_list.addAll(ItemTag.HORSE_ARMOR.getList());
		invContentsMap.put(InvType.COMBAT_MISC_ARMOR,
				new BuyAreaInstance(misc_armor_list, "Buying > Combat > Misc. Armor", InvType.COMBAT_PAGE));
		
		invContentsMap.put(InvType.COMBAT_MELEE_WEAPONS,
				new BuyAreaInstance(ItemTag.SWORDS.getList(), "Buying > Combat > Melee", InvType.COMBAT_PAGE));
		
		invContentsMap.put(InvType.COMBAT_RANGED_WEAPONS,
				new BuyAreaInstance(ItemTag.RANGED_ADVANCED.getList(), "Buying > Combat > Ranged", InvType.COMBAT_PAGE));
		
		invContentsMap.put(InvType.COMBAT_ARROWS,
				new BuyAreaInstance(ItemTag.ARROWS.getList(), "Buying > Combat > Arrows", InvType.COMBAT_PAGE));
		
		///
		
		List<SimpleItem> general_tools_list = new ArrayList<SimpleItem>();
		general_tools_list.addAll(ItemTag.SHOVELS.getList()); general_tools_list.addAll(ItemTag.AXES.getList());
		general_tools_list.addAll(ItemTag.PICKAXES.getList());
		invContentsMap.put(InvType.TOOLS_GENERAL,
				new BuyAreaInstance(general_tools_list, "Buying > Tools > General", InvType.TOOLS_PAGE));
		
		invContentsMap.put(InvType.TOOLS_TRANSPORT,
				new BuyAreaInstance(ItemTag.TRANSPORTATION_TOOLS.getList(), "Buying > Tools > Transport", InvType.TOOLS_PAGE));
		
		invContentsMap.put(InvType.TOOLS_MISC,
				new BuyAreaInstance(ItemTag.MISC_TOOLS.getList(), "Buying > Tools > Miscellaneous", InvType.TOOLS_PAGE));
		
		///
		
		invContentsMap.put(InvType.FOOD_PREPARED,
				new BuyAreaInstance(ItemTag.PREPARED_FOODS.getList(), "Buying > Food > Prepared", InvType.FOOD_PAGE));
		
		invContentsMap.put(InvType.FOOD_RAW,
				new BuyAreaInstance(ItemTag.RAW_FOODS.getList(), "Buying > Food > Raw", InvType.FOOD_PAGE));
		
		///
		
		invContentsMap.put(InvType.REDSTONE_GENERAL,
				new BuyAreaInstance(ItemTag.REDSTONE.getList(), "Buying > Redstone > General", InvType.REDSTONE_PAGE));
		
		invContentsMap.put(InvType.REDSTONE_PLATES,
				new BuyAreaInstance(ItemTag.PRESSURE_PLATES.getList(), "Buying > Redstone > Plates", InvType.REDSTONE_PAGE));
		
		invContentsMap.put(InvType.REDSTONE_BUTTONS,
				new BuyAreaInstance(ItemTag.BUTTONS.getList(), "Buying > Redstone > Buttons", InvType.REDSTONE_PAGE));
		
		invContentsMap.put(InvType.REDSTONE_DOORS,
				new BuyAreaInstance(ItemTag.DOORS.getList(), "Buying > Redstone > Doors", InvType.REDSTONE_PAGE));
		
		invContentsMap.put(InvType.REDSTONE_RAILS,
				new BuyAreaInstance(ItemTag.RAILS.getList(), "Buying > Redstone > Rails", InvType.REDSTONE_PAGE));
		
		///
		
		invContentsMap.put(InvType.G_SUPPLIES_CRAFTING,
				new BuyAreaInstance(ItemTag.CRAFTING_SUPPLIES.getList(), "Buying > General > Crafting", InvType.GENERAL_S_PAGE));
		
		List<SimpleItem> general_s_farming_list = new ArrayList<SimpleItem>();
		general_s_farming_list.addAll(ItemTag.FARMING_SUPPLIES.getList()); general_s_farming_list.addAll(ItemTag.HOES.getList());
		invContentsMap.put(InvType.G_SUPPLIES_FARMING,
				new BuyAreaInstance(general_s_farming_list, "Buying > General > Farming", InvType.GENERAL_S_PAGE));
		
		invContentsMap.put(InvType.G_SUPPLIES_DYES,
				new BuyAreaInstance(ItemTag.DYES.getList(), "Buying > General > Dyes", InvType.GENERAL_S_PAGE));
		
		invContentsMap.put(InvType.G_SUPPLIES_DISCS,
				new BuyAreaInstance(ItemTag.MUSIC_DISCS.getList(), "Buying > General > Discs", InvType.GENERAL_S_PAGE));
		
		///
		
		invContentsMap.put(InvType.M_SUPPLIES_WARDING,
				new BuyAreaInstance(ItemTag.WARDING_SUPPLIES.getList(), "Buying > Magic > Warding", InvType.MAGIC_S_PAGE));
		
		invContentsMap.put(InvType.M_SUPPLIES_ALCHEMY,
				new BuyAreaInstance(ItemTag.ALCHEMY_SUPPLIES.getList(), "Buying > Magic > Alchemy", InvType.MAGIC_S_PAGE));
		
	}
	
	public static BuyAreaInstance getInvInstance(InvType type){
		for(Entry<InvType, BuyAreaInstance> entry : invContentsMap.entrySet()) {
			if(entry.getKey().equals(type)) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	public static HashMap<InvType, BuyAreaInstance> getInvContentsMap(){
		return invContentsMap;
	}
	
}
