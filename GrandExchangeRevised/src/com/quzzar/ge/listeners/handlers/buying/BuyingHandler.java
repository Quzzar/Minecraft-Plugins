package com.quzzar.ge.listeners.handlers.buying;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.quzzar.ge.core.InvType;
import com.quzzar.ge.presets.buying.BuyingInventoryBuilder;
import com.quzzar.ge.presets.buying.BuyingItemCreator;

public class BuyingHandler {

	public static void handleBuying(InvType type, ItemStack item, HumanEntity he) {
		
		if(type.equals(InvType.BUYING_PAGE)) {
			handleBuyingPage(item, he);
			return;
		}
		
		if(type.equals(InvType.BLOCKS_PAGE)) {
			handleBlocksPage(item, he);
			return;
		}
		
		if(type.equals(InvType.BLOCKS_BUILDING_PAGE)) {
			handleBlocksBuildingPage(item, he);
			return;
		}
		
		if(type.equals(InvType.COMBAT_PAGE)) {
			handleCombatPage(item, he);
			return;
		}
		
		if(type.equals(InvType.TOOLS_PAGE)) {
			handleToolsPage(item, he);
			return;
		}
		
		if(type.equals(InvType.FOOD_PAGE)) {
			handleFoodPage(item, he);
			return;
		}
		
		if(type.equals(InvType.REDSTONE_PAGE)) {
			handleRedstonePage(item, he);
			return;
		}
		
		if(type.equals(InvType.GENERAL_S_PAGE)) {
			handleGeneralSuppliesPage(item, he);
			return;
		}
		
		if(type.equals(InvType.MAGIC_S_PAGE)) {
			handleMagicSuppliesPage(item, he);
			return;
		}
		
	}
	
	private static void handleBuyingPage(ItemStack item, HumanEntity he) {
		
		if(item.equals(BuyingItemCreator.getBlocksButton())) {
			he.closeInventory();
			he.openInventory(BuyingInventoryBuilder.getBlocksSection());
			return;
		}
		
		if(item.equals(BuyingItemCreator.getCombatButton())) {
			he.closeInventory();
			he.openInventory(BuyingInventoryBuilder.getCombatSection());
			return;		
		}
		
		if(item.equals(BuyingItemCreator.getToolsButton())) {
			he.closeInventory();
			he.openInventory(BuyingInventoryBuilder.getToolsSection());
			return;	
		}
		
		if(item.equals(BuyingItemCreator.getFoodButton())) {
			he.closeInventory();
			he.openInventory(BuyingInventoryBuilder.getFoodSection());
			return;	
		}
		
		if(item.equals(BuyingItemCreator.getRedstoneButton())) {
			he.closeInventory();
			he.openInventory(BuyingInventoryBuilder.getRedstoneSection());
			return;	
		}
		
		if(item.equals(BuyingItemCreator.getGeneralSuppliesButton())) {
			he.closeInventory();
			he.openInventory(BuyingInventoryBuilder.getGeneralSuppliesSection());
			return;	
		}
		
		if(item.equals(BuyingItemCreator.getMagicSuppliesButton())) {
			he.closeInventory();
			he.openInventory(BuyingInventoryBuilder.getMagicSuppliesSection());
			return;
		}
		
	}
	
	private static void handleBlocksPage(ItemStack item, HumanEntity he) {
		
		if(item.equals(BuyingItemCreator.getBlocksStorageButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.BLOCKS_STORAGE);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getBlocksWorktablesButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.BLOCKS_WORKTABLES);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getBlocksDecorationsButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.BLOCKS_DECORATIONS);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getBlocksOrganicButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.BLOCKS_ORGANIC);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getBlocksBuildingButton())) {
			Inventory inv = BuyingInventoryBuilder.getBlocksBuildingSubSection();
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
	}
	
	private static void handleBlocksBuildingPage(ItemStack item, HumanEntity he) {
		
		if(item.equals(BuyingItemCreator.getBlocksBuildingWoodButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.BLOCKS_BUILDING_WOOD);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getBlocksBuildingStoneButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.BLOCKS_BUILDING_STONE);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getBlocksBuildingGlassButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.BLOCKS_BUILDING_GLASS);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getBlocksBuildingMiscButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.BLOCKS_BUILDING_MISC);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
	}
	
	private static void handleCombatPage(ItemStack item, HumanEntity he) {
		
		if(item.equals(BuyingItemCreator.getCombatArmorButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.COMBAT_ARMOR);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getCombatMiscArmorButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.COMBAT_MISC_ARMOR);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getCombatMeleeWeaponsButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.COMBAT_MELEE_WEAPONS);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getCombatRangedWeaponsButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.COMBAT_RANGED_WEAPONS);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getCombatArrowsButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.COMBAT_ARROWS);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
	}
	
	private static void handleToolsPage(ItemStack item, HumanEntity he) {
		
		if(item.equals(BuyingItemCreator.getToolsGeneralButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.TOOLS_GENERAL);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getToolsTransportButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.TOOLS_TRANSPORT);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getToolsMiscButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.TOOLS_MISC);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
	}
	
	private static void handleFoodPage(ItemStack item, HumanEntity he) {
		
		if(item.equals(BuyingItemCreator.getFoodPreparedButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.FOOD_PREPARED);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getFoodRawButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.FOOD_RAW);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
	}
	
	private static void handleRedstonePage(ItemStack item, HumanEntity he) {
		
		if(item.equals(BuyingItemCreator.getRedstoneGeneralButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.REDSTONE_GENERAL);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getRedstonePlatesButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.REDSTONE_PLATES);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getRedstoneButtonsButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.REDSTONE_BUTTONS);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getRedstoneDoorsButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.REDSTONE_DOORS);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getRedstoneRailsButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.REDSTONE_RAILS);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
	}
	
	private static void handleGeneralSuppliesPage(ItemStack item, HumanEntity he) {
		
		if(item.equals(BuyingItemCreator.getGeneralSuppliesCraftingButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.G_SUPPLIES_CRAFTING);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getGeneralSuppliesFarmingButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.G_SUPPLIES_FARMING);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getGeneralSuppliesDyesButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.G_SUPPLIES_DYES);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getGeneralSuppliesDiscsButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.G_SUPPLIES_DISCS);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
	}
	
	private static void handleMagicSuppliesPage(ItemStack item, HumanEntity he) {
		
		if(item.equals(BuyingItemCreator.getMagicSuppliesWardingButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.M_SUPPLIES_WARDING);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
		if(item.equals(BuyingItemCreator.getMagicSuppliesAlchemyButton())) {
			Inventory inv = BuyingInventoryBuilder.generateBuyingInv(InvType.M_SUPPLIES_ALCHEMY);
			he.closeInventory();
			he.openInventory(inv);
			return;
		}
		
	}
	
}
