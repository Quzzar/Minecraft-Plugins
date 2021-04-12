package com.quzzar.ge.presets.buying;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.ge.core.buylist.GETrade;
import com.quzzar.ge.core.buylist.SimpleItem;

public class BuyingItemCreator {

	private static ItemStack buyingInfo,
		blocksBtn, combatBtn, toolsBtn, foodBtn, redstoneBtn, generalSuppliesBtn, magicSuppliesBtn,
		storageBtn, worktablesBtn, decorationsBtn, organicBtn, buildingBtn,
		buildingWoodBtn, buildingStoneBtn, buildingGlassBtn, buildingMiscBtn,
		meleeWeaponsBtn, rangedWeaponsBtn, arrowsBtn, armorBtn, miscArmorBtn,
		generalToolsBtn, transportToolsBtn, miscToolsBtn,
		preparedFoodBtn, rawFoodBtn,
		generalRedstoneBtn, platesRedstoneBtn, buttonsRedstoneBtn, doorsRedstoneBtn, railsRedstoneBtn,
		craftingGeneralSuppliesBtn, farmingGeneralSuppliesBtn, dyesGeneralSuppliesBtn, discsGeneralSuppliesBtn,
		alchemyMagicSuppliesBtn, wardingMagicSuppliesBtn;
	
	static {
		
		buyingInfo = createBuyingInfo();
		
		blocksBtn = createBlocksButton();
		combatBtn = createCombatButton();
		toolsBtn = createToolsButton();
		foodBtn = createFoodButton();
		redstoneBtn = createRedstoneButton();
		generalSuppliesBtn = createGeneralSuppliesButton();
		magicSuppliesBtn = createMagicSuppliesButton();
		
		storageBtn = createStorageButton();
		worktablesBtn = createWorktablesButton();
		decorationsBtn = createDecorationsButton();
		buildingBtn = createBuildingButton();
		organicBtn = createOrganicButton();
		
		buildingWoodBtn = createBuildingWoodButton();
		buildingStoneBtn = createBuildingStoneButton();
		buildingGlassBtn = createBuildingGlassButton();
		buildingMiscBtn = createBuildingMiscButton();
		
		meleeWeaponsBtn = createMeleeWeaponsButton();
		rangedWeaponsBtn = createRangedWeaponsButton();
		arrowsBtn = createArrowsButton();
		armorBtn = createArmorButton();
		miscArmorBtn = createMiscArmorButton();
		
		generalToolsBtn = createGeneralToolsButton();
		transportToolsBtn = createTransportToolsButton();
		miscToolsBtn = createMiscToolsButton();
		
		preparedFoodBtn = createPreparedFoodButton();
		rawFoodBtn = createRawFoodButton();
		
		generalRedstoneBtn = createGeneralRedstoneButton();
		platesRedstoneBtn = createPlatesRedstoneButton();
		buttonsRedstoneBtn = createButtonsRedstoneButton();
		doorsRedstoneBtn = createDoorsRedstoneButton();
		railsRedstoneBtn = createRailsRedstoneButton();
		
		craftingGeneralSuppliesBtn = createCraftingGeneralSuppliesButton();
		farmingGeneralSuppliesBtn = createFarmingGeneralSuppliesButton();
		dyesGeneralSuppliesBtn = createDyesGeneralSuppliesButton();
		discsGeneralSuppliesBtn = createDiscsGeneralSuppliesButton();
		
		alchemyMagicSuppliesBtn = createAlchemyMagicSuppliesButton();
		wardingMagicSuppliesBtn = createWardingMagicSuppliesButton();
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Private - Create Items /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	
	private static ItemStack createBuyingInfo(){
		
		ItemStack i = new ItemStack(Material.PAPER);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.GOLD+"Info.");
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"Click to purchase a single item,");
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"shift click to purchase the entire");
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"remaining stack.");
	    im.setLore(lore);
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createBlocksButton(){
		
		ItemStack i = new ItemStack(Material.BRICKS);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Blocks");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createCombatButton(){
		
		ItemStack i = new ItemStack(Material.GOLDEN_SWORD);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Combat");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createToolsButton(){
		
		ItemStack i = new ItemStack(Material.IRON_AXE);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Tools");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createFoodButton(){
		
		ItemStack i = new ItemStack(Material.APPLE);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Food");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createRedstoneButton(){
		
		ItemStack i = new ItemStack(Material.REDSTONE);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Redstone");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createGeneralSuppliesButton(){
		
		ItemStack i = new ItemStack(Material.LEATHER);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"General Supplies");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createMagicSuppliesButton(){
		
		ItemStack i = new ItemStack(Material.GLISTERING_MELON_SLICE);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Magic Supplies");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	///////
	
	private static ItemStack createStorageButton(){
		
		ItemStack i = new ItemStack(Material.CHEST);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Storage");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createWorktablesButton(){
		
		ItemStack i = new ItemStack(Material.CRAFTING_TABLE);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Worktables");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createDecorationsButton(){
		
		ItemStack i = new ItemStack(Material.PAINTING);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Decorations");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createOrganicButton(){
		
		ItemStack i = new ItemStack(Material.GRASS_BLOCK);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Organic");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createBuildingButton(){
		
		ItemStack i = new ItemStack(Material.BRICKS);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Building");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	///
	
	private static ItemStack createBuildingWoodButton(){
		
		ItemStack i = new ItemStack(Material.OAK_LOG);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Wood");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createBuildingStoneButton(){
		
		ItemStack i = new ItemStack(Material.POLISHED_ANDESITE);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Stone");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createBuildingGlassButton(){
		
		ItemStack i = new ItemStack(Material.GLASS);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Glass");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createBuildingMiscButton(){
		
		ItemStack i = new ItemStack(Material.BRICKS);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Miscellaneous");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	///////
	
	private static ItemStack createMeleeWeaponsButton(){
		
		ItemStack i = new ItemStack(Material.GOLDEN_SWORD);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Melee Weapons");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createRangedWeaponsButton(){
		
		ItemStack i = new ItemStack(Material.BOW);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Ranged Weapons");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createArrowsButton(){
		
		ItemStack i = new ItemStack(Material.ARROW);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Arrows");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createArmorButton(){
		
		ItemStack i = new ItemStack(Material.IRON_HELMET);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Armor");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createMiscArmorButton(){
		
		ItemStack i = new ItemStack(Material.LEATHER_HORSE_ARMOR);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Misc. Armor");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	///////
	
	private static ItemStack createGeneralToolsButton(){
		
		ItemStack i = new ItemStack(Material.IRON_AXE);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"General");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createTransportToolsButton(){
			
		ItemStack i = new ItemStack(Material.SADDLE);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Transportation");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createMiscToolsButton(){
		
		ItemStack i = new ItemStack(Material.FLINT_AND_STEEL);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Miscellaneous");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	///////
	
	private static ItemStack createPreparedFoodButton(){
		
		ItemStack i = new ItemStack(Material.COOKED_BEEF);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Prepared");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createRawFoodButton(){
		
		ItemStack i = new ItemStack(Material.BEEF);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Raw");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	///////
	
	private static ItemStack createGeneralRedstoneButton(){
		
		ItemStack i = new ItemStack(Material.COMPARATOR);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"General");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createPlatesRedstoneButton(){
		
		ItemStack i = new ItemStack(Material.OAK_PRESSURE_PLATE);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Pressure Plates");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createButtonsRedstoneButton(){
		
		ItemStack i = new ItemStack(Material.STONE_BUTTON);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Buttons");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createDoorsRedstoneButton(){
		
		ItemStack i = new ItemStack(Material.DARK_OAK_DOOR);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Doors");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createRailsRedstoneButton(){
		
		ItemStack i = new ItemStack(Material.POWERED_RAIL);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Rails");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	///////
	
	private static ItemStack createCraftingGeneralSuppliesButton(){
		
		ItemStack i = new ItemStack(Material.LEATHER);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Crafting");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createFarmingGeneralSuppliesButton(){
		
		ItemStack i = new ItemStack(Material.WHEAT_SEEDS);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Farming");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createDyesGeneralSuppliesButton(){
		
		ItemStack i = new ItemStack(Material.CYAN_DYE);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Dyes");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createDiscsGeneralSuppliesButton(){
		
		ItemStack i = new ItemStack(Material.MUSIC_DISC_13);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Music Discs");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	///////
	
	private static ItemStack createAlchemyMagicSuppliesButton(){
		
		ItemStack i = new ItemStack(Material.NETHER_WART);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Alchemy");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createWardingMagicSuppliesButton(){
		
		ItemStack i = new ItemStack(Material.LAPIS_LAZULI);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BOLD+"Warding");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Public - Get Items /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	
	public static ItemStack getBuyingInfo() {
		return buyingInfo;
	}
	
	public static ItemStack getBuyingItem(GETrade trade) {
		
		ItemStack i = trade.getItem().toItemStack();
		
		ItemMeta im = i.getItemMeta();
		
		List<String> lore = new ArrayList<String>();
		
		if(i.getAmount() > 1) {
			lore.add(ChatColor.BLUE+"Price ["+ChatColor.YELLOW+"Single: "+ChatColor.GREEN+"$"+trade.getSingleCost()
				+ChatColor.DARK_PURPLE+" | "+ChatColor.YELLOW+"Stack: "+ChatColor.GREEN+"$"+trade.getTotalCost()+ChatColor.BLUE+"]");
		} else {
			lore.add(ChatColor.BLUE+"Price ["+ChatColor.GREEN+"$"+trade.getTotalCost()+ChatColor.BLUE+"]");
		}
		
	    lore.add(ChatColor.GOLD+""+ChatColor.ITALIC+"Sold by: "
	    		+ChatColor.DARK_AQUA+""+ChatColor.ITALIC+Bukkit.getOfflinePlayer(trade.getSellerUUID()).getName());
	    im.setLore(lore);
		
	    // Set price of single unit to item data
	    im.setCustomModelData((int) (trade.getSingleCost()*100));
	    
	    i.setItemMeta(im);
		
		return i;
	}
	
	public static ItemStack getEmptyBuyingItem(SimpleItem item) {
		
		ItemStack i = item.toItemStack();
		
		ItemMeta im = i.getItemMeta();
		
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.RED+""+ChatColor.ITALIC+"Out of Stock!");
	    im.setLore(lore);
		
	    i.setItemMeta(im);
		
		return i;
	}
	
	public static SimpleItem getSimpleItemFromBuyingItem(ItemStack buyingItem) {
		ItemStack bItem = buyingItem.clone();
		bItem.setLore(null);
		bItem.setAmount(1);
		return new SimpleItem(bItem);
	}
	
	public static boolean isAboutSamePrice(ItemStack buyingItem, GETrade cheapestTrade) {
		int displayedPrice = buyingItem.getItemMeta().getCustomModelData();
		int tradePrice = (int) (cheapestTrade.getSingleCost()*100);
		return displayedPrice == tradePrice;
	}
	
	///////
	
	public static ItemStack getBlocksButton() {
		return blocksBtn;
	}
	
	public static ItemStack getCombatButton() {
		return combatBtn;
	}
	
	public static ItemStack getToolsButton() {
		return toolsBtn;
	}
	
	public static ItemStack getFoodButton() {
		return foodBtn;
	}
	
	public static ItemStack getRedstoneButton() {
		return redstoneBtn;
	}
	
	public static ItemStack getGeneralSuppliesButton() {
		return generalSuppliesBtn;
	}
	
	public static ItemStack getMagicSuppliesButton() {
		return magicSuppliesBtn;
	}
	
	/////// Blocks //////
	
	public static ItemStack getBlocksStorageButton() {
		return storageBtn;
	}
	
	public static ItemStack getBlocksWorktablesButton() {
		return worktablesBtn;
	}
	
	public static ItemStack getBlocksDecorationsButton() {
		return decorationsBtn;
	}
	
	public static ItemStack getBlocksOrganicButton() {
		return organicBtn;
	}
	
	public static ItemStack getBlocksBuildingButton() {
		return buildingBtn;
	}
	
		/// Building Blocks ///
	
		public static ItemStack getBlocksBuildingWoodButton() {
			return buildingWoodBtn;
		}
		
		public static ItemStack getBlocksBuildingStoneButton() {
			return buildingStoneBtn;
		}
		
		public static ItemStack getBlocksBuildingGlassButton() {
			return buildingGlassBtn;
		}
		
		public static ItemStack getBlocksBuildingMiscButton() {
			return buildingMiscBtn;
		}
	
	/////// Combat //////
	
	public static ItemStack getCombatMeleeWeaponsButton() {
		return meleeWeaponsBtn;
	}
	
	public static ItemStack getCombatRangedWeaponsButton() {
		return rangedWeaponsBtn;
	}
	
	public static ItemStack getCombatArrowsButton() {
		return arrowsBtn;
	}
	
	public static ItemStack getCombatArmorButton() {
		return armorBtn;
	}
	
	public static ItemStack getCombatMiscArmorButton() {
		return miscArmorBtn;
	}
	
	/////// Tools //////
	
	public static ItemStack getToolsGeneralButton() {
		return generalToolsBtn;
	}
	
	public static ItemStack getToolsTransportButton() {
		return transportToolsBtn;
	}
	
	public static ItemStack getToolsMiscButton() {
		return miscToolsBtn;
	}
	
	/////// Food ////////
	
	public static ItemStack getFoodPreparedButton() {
		return preparedFoodBtn;
	}
	
	public static ItemStack getFoodRawButton() {
		return rawFoodBtn;
	}
	
	/////// Redstone ////////
	
	public static ItemStack getRedstoneGeneralButton() {
		return generalRedstoneBtn;
	}
	
	public static ItemStack getRedstonePlatesButton() {
		return platesRedstoneBtn;
	}
	
	public static ItemStack getRedstoneButtonsButton() {
		return buttonsRedstoneBtn;
	}
	
	public static ItemStack getRedstoneDoorsButton() {
		return doorsRedstoneBtn;
	}
	
	public static ItemStack getRedstoneRailsButton() {
		return railsRedstoneBtn;
	}
	
	/////// General Supplies ////////
	
	public static ItemStack getGeneralSuppliesCraftingButton() {
		return craftingGeneralSuppliesBtn;
	}
	
	public static ItemStack getGeneralSuppliesFarmingButton() {
		return farmingGeneralSuppliesBtn;
	}
	
	public static ItemStack getGeneralSuppliesDyesButton() {
		return dyesGeneralSuppliesBtn;
	}
	
	public static ItemStack getGeneralSuppliesDiscsButton() {
		return discsGeneralSuppliesBtn;
	}
	
	/////// Magic Supplies ////////
	
	public static ItemStack getMagicSuppliesWardingButton() {
		return wardingMagicSuppliesBtn;
	}
	
	public static ItemStack getMagicSuppliesAlchemyButton() {
		return alchemyMagicSuppliesBtn;
	}
	
}
