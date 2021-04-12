package com.quzzar.ge.presets.buying;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.quzzar.ge.core.InvType;
import com.quzzar.ge.core.buylist.BuyListManager;
import com.quzzar.ge.core.buylist.GETrade;
import com.quzzar.ge.core.buylist.SimpleItem;
import com.quzzar.ge.core.buylist.buyinvmapping.BuyAreaInstance;
import com.quzzar.ge.core.buylist.buyinvmapping.BuyAreaContentsMap;
import com.quzzar.ge.presets.GeneralInventoryBuilder;
import com.quzzar.ge.presets.GeneralItemCreator;

public class BuyingInventoryBuilder {
	
	private static Inventory mainSection, blocksSection, blocksBuildingSubSection, combatSection, toolsSection, foodSection,
		redstoneSection, generalSuppliesSection, magicSuppliesSection;
	
	public static void init() {
		
		mainSection = buildMainSection();
		
		combatSection = buildCombatSection();
		toolsSection = buildToolsSection();
		foodSection = buildFoodSection();
		redstoneSection = buildRedstoneSection();
		generalSuppliesSection = buildGeneralSuppliesSection();
		magicSuppliesSection = buildMagicSuppliesSection();
		
		blocksSection = buildBlocksSection();
		blocksBuildingSubSection = buildBlocksBuildingSubSection();
		
		BuyListManager.init();
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Private - Build Inventories /////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static Inventory buildMainSection() {
		
		Inventory inv = Bukkit.createInventory(null, 27, "Buying");
		
		inv.setItem(0, GeneralItemCreator.getBackButton(InvType.MAIN_MENU));
		
		inv.setItem(10, BuyingItemCreator.getBlocksButton());
		inv.setItem(11, BuyingItemCreator.getCombatButton());
		inv.setItem(12, BuyingItemCreator.getToolsButton());
		inv.setItem(13, BuyingItemCreator.getFoodButton());
		inv.setItem(14, BuyingItemCreator.getRedstoneButton());
		inv.setItem(15, BuyingItemCreator.getGeneralSuppliesButton());
		inv.setItem(16, BuyingItemCreator.getMagicSuppliesButton());
		
		GeneralInventoryBuilder.fillInvWithItem(inv, GeneralItemCreator.getInvBorder());
		
		inv.setMaxStackSize(InvType.BUYING_PAGE.getID());
		return inv;
		
	}
	
	private static Inventory buildBlocksSection() {
		
		Inventory inv = Bukkit.createInventory(null, 27, "Buying > Blocks");
		
		inv.setItem(0, GeneralItemCreator.getBackButton(InvType.BUYING_PAGE));
		
		inv.setItem(11, BuyingItemCreator.getBlocksStorageButton());
		inv.setItem(12, BuyingItemCreator.getBlocksWorktablesButton());
		inv.setItem(13, BuyingItemCreator.getBlocksDecorationsButton());
		inv.setItem(14, BuyingItemCreator.getBlocksBuildingButton());
		inv.setItem(15, BuyingItemCreator.getBlocksOrganicButton());
		
		GeneralInventoryBuilder.fillInvWithItem(inv, GeneralItemCreator.getInvBorder());
		
		inv.setMaxStackSize(InvType.BLOCKS_PAGE.getID());
		return inv;
		
	}
	
		private static Inventory buildBlocksBuildingSubSection() {
			
			Inventory inv = Bukkit.createInventory(null, 27, "Buying > Blocks > Building");
			
			inv.setItem(0, GeneralItemCreator.getBackButton(InvType.BLOCKS_PAGE));
			
			inv.setItem(11, BuyingItemCreator.getBlocksBuildingWoodButton());
			inv.setItem(12, BuyingItemCreator.getBlocksBuildingStoneButton());
			inv.setItem(13, BuyingItemCreator.getBlocksBuildingGlassButton());
			inv.setItem(14, BuyingItemCreator.getBlocksBuildingMiscButton());
			
			GeneralInventoryBuilder.fillInvWithItem(inv, GeneralItemCreator.getInvBorder());
			
			inv.setMaxStackSize(InvType.BLOCKS_BUILDING_PAGE.getID());
			return inv;
			
		}
	
	private static Inventory buildCombatSection() {
		
		Inventory inv = Bukkit.createInventory(null, 27, "Buying > Combat");
		
		inv.setItem(0, GeneralItemCreator.getBackButton(InvType.BUYING_PAGE));
		
		inv.setItem(11, BuyingItemCreator.getCombatArmorButton());
		inv.setItem(12, BuyingItemCreator.getCombatMiscArmorButton());
		inv.setItem(13, BuyingItemCreator.getCombatMeleeWeaponsButton());
		inv.setItem(14, BuyingItemCreator.getCombatRangedWeaponsButton());
		inv.setItem(15, BuyingItemCreator.getCombatArrowsButton());
		
		GeneralInventoryBuilder.fillInvWithItem(inv, GeneralItemCreator.getInvBorder());
		
		inv.setMaxStackSize(InvType.COMBAT_PAGE.getID());
		return inv;
		
	}
	
	private static Inventory buildToolsSection() {
		
		Inventory inv = Bukkit.createInventory(null, 27, "Buying > Tools");
		
		inv.setItem(0, GeneralItemCreator.getBackButton(InvType.BUYING_PAGE));
		
		inv.setItem(12, BuyingItemCreator.getToolsGeneralButton());
		inv.setItem(13, BuyingItemCreator.getToolsTransportButton());
		inv.setItem(14, BuyingItemCreator.getToolsMiscButton());
		
		GeneralInventoryBuilder.fillInvWithItem(inv, GeneralItemCreator.getInvBorder());
		
		inv.setMaxStackSize(InvType.TOOLS_PAGE.getID());
		return inv;
		
	}
	
	private static Inventory buildFoodSection() {
		
		Inventory inv = Bukkit.createInventory(null, 27, "Buying > Food");
		
		inv.setItem(0, GeneralItemCreator.getBackButton(InvType.BUYING_PAGE));
		
		inv.setItem(12, BuyingItemCreator.getFoodPreparedButton());
		
		inv.setItem(14, BuyingItemCreator.getFoodRawButton());
		
		GeneralInventoryBuilder.fillInvWithItem(inv, GeneralItemCreator.getInvBorder());
		
		inv.setMaxStackSize(InvType.FOOD_PAGE.getID());
		return inv;
		
	}
	
	private static Inventory buildRedstoneSection() {
		
		Inventory inv = Bukkit.createInventory(null, 27, "Buying > Redstone");
		
		inv.setItem(0, GeneralItemCreator.getBackButton(InvType.BUYING_PAGE));
		
		inv.setItem(11, BuyingItemCreator.getRedstoneGeneralButton());
		inv.setItem(12, BuyingItemCreator.getRedstonePlatesButton());
		inv.setItem(13, BuyingItemCreator.getRedstoneButtonsButton());
		inv.setItem(14, BuyingItemCreator.getRedstoneDoorsButton());
		inv.setItem(15, BuyingItemCreator.getRedstoneRailsButton());
		
		GeneralInventoryBuilder.fillInvWithItem(inv, GeneralItemCreator.getInvBorder());
		
		inv.setMaxStackSize(InvType.REDSTONE_PAGE.getID());
		return inv;
		
	}
	
	private static Inventory buildGeneralSuppliesSection() {
		
		Inventory inv = Bukkit.createInventory(null, 27, "Buying > General Supplies");
		
		inv.setItem(0, GeneralItemCreator.getBackButton(InvType.BUYING_PAGE));
		
		inv.setItem(11, BuyingItemCreator.getGeneralSuppliesCraftingButton());
		inv.setItem(12, BuyingItemCreator.getGeneralSuppliesFarmingButton());
		
		inv.setItem(14, BuyingItemCreator.getGeneralSuppliesDyesButton());
		inv.setItem(15, BuyingItemCreator.getGeneralSuppliesDiscsButton());
		
		GeneralInventoryBuilder.fillInvWithItem(inv, GeneralItemCreator.getInvBorder());
		
		inv.setMaxStackSize(InvType.GENERAL_S_PAGE.getID());
		return inv;
		
	}
	
	private static Inventory buildMagicSuppliesSection() {
		
		Inventory inv = Bukkit.createInventory(null, 27, "Buying > Magic Supplies");
		
		inv.setItem(0, GeneralItemCreator.getBackButton(InvType.BUYING_PAGE));
		
		inv.setItem(12, BuyingItemCreator.getMagicSuppliesWardingButton());
		
		inv.setItem(14, BuyingItemCreator.getMagicSuppliesAlchemyButton());
		
		GeneralInventoryBuilder.fillInvWithItem(inv, GeneralItemCreator.getInvBorder());
		
		inv.setMaxStackSize(InvType.MAGIC_S_PAGE.getID());
		return inv;
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Public - Get Inventories /////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	public static Inventory getMainSection() {
		return mainSection;
	}

	public static Inventory getCombatSection() {
		return combatSection;
	}

	public static Inventory getToolsSection() {
		return toolsSection;
	}

	public static Inventory getFoodSection() {
		return foodSection;
	}

	public static Inventory getRedstoneSection() {
		return redstoneSection;
	}

	public static Inventory getGeneralSuppliesSection() {
		return generalSuppliesSection;
	}

	public static Inventory getMagicSuppliesSection() {
		return magicSuppliesSection;
	}
	
	public static Inventory getBlocksSection() {
		return blocksSection;
	}
	
	public static Inventory getBlocksBuildingSubSection() {
		return blocksBuildingSubSection;
	}
	
	
	/// Utils ///

	public static Inventory generateBuyingInv(InvType type) {
		
		BuyAreaInstance instance = BuyAreaContentsMap.getInvInstance(type);
		
		int rowCount = (int) Math.ceil(instance.getContents().size()/7.0)+2;
		Inventory inv = Bukkit.createInventory(null, rowCount*9, instance.getTitle());
		
		inv.setItem(0, GeneralItemCreator.getBackButton(instance.getBackInv()));
		inv.setItem(8, BuyingItemCreator.getBuyingInfo());
		
		buildInvBorder(inv, GeneralItemCreator.getInvBorder());
		
		for(SimpleItem item : instance.getContents()) {
			GETrade cheapestTrade = BuyListManager.getData().get(item).getCheapestTrade();
			if(cheapestTrade != null) {
				inv.addItem(BuyingItemCreator.getBuyingItem(cheapestTrade));
			} else {
				inv.addItem(BuyingItemCreator.getEmptyBuyingItem(item));
			}
		}
		
		GeneralInventoryBuilder.fillInv(inv);
		
		inv.setMaxStackSize(type.getID());
		return inv;
		
	}
	
	public static void buildInvBorder(Inventory inv, ItemStack borderItem) {
		for(int i=0; i<inv.getSize(); i++){
			if(inv.getItem(i)==null) {
				if((i >= 0 && i <= 8) || (i >= inv.getSize()-9 && i <= inv.getSize()-1) || i % 9 == 0 || i % 9 == 8) {
					inv.setItem(i, borderItem);
				}
			}
		}
	}
	
}
