package com.quzzar.ge.npcshop;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.quzzar.ge.core.InvType;
import com.quzzar.ge.core.buylist.SimpleItem;
import com.quzzar.ge.npcshop.namemachine.NameGenerator;
import com.quzzar.ge.presets.GeneralInventoryBuilder;

public class NPCShopListener implements Listener {
	
	// Open villager shop
	@EventHandler
    public void onVillagerRightClick(PlayerInteractEntityEvent e){
		if(e.getRightClicked() instanceof Villager) {
			Villager villager = (Villager) e.getRightClicked();
			e.setCancelled(true);
			if(!e.getPlayer().isSneaking()) {
				ShopInstance shop = ShopManager.getShop(villager);
				e.getPlayer().openInventory(ShopBuilder.buildNPCShop(shop));
			}
			return;
		}
		
		if(e.getRightClicked() instanceof WanderingTrader) {
			e.setCancelled(true);
			if(!e.getPlayer().isSneaking()) {
				
				if(e.getRightClicked().getCustomName() == null) {
					NameGenerator nameGen = new NameGenerator();
					e.getRightClicked().setCustomName(nameGen.generateName().getFirstName());
					e.getRightClicked().setCustomNameVisible(true);
				}
				e.getPlayer().openInventory(GeneralInventoryBuilder.getInventoryByType(
						InvType.MAIN_MENU, e.getPlayer().getUniqueId()));
				
			}
			return;
		}
		
	}
	
	
	@EventHandler
    public void onInventoryClick(InventoryClickEvent e){
		
		Inventory inv = e.getInventory();
		ItemStack item = e.getCurrentItem();
		Inventory clickedInv = e.getClickedInventory();
		
		if(inv == null || !(e.getWhoClicked() instanceof Player)) {
			return;
		}
		if(inv.getMaxStackSize() != ShopBuilder.NPC_TRADE_INV_ID) {
			return;
		}
		e.setCancelled(true);
		
		Player player = (Player) e.getWhoClicked();
		
		if(inv == clickedInv) {
			attemptingToBuy(player, item, e.getClick(), inv);
		} else {
			attemptingToSell(player, item, e.getClick(), inv);
		}
		
		
	}
	
	private static void attemptingToSell(Player player, ItemStack itemClicked, ClickType type, Inventory inv) {
		
		ItemStack firstItem = inv.getItem(10); // Should always have an item for sale here.
		
		int data = firstItem.getItemMeta().getCustomModelData();
		ShopInstance shop = ShopManager.getShop(data);
		if(shop == null) {return;}
		
		if(!SimpleItem.canBeSimple(itemClicked)) {return;}
		
		NPCTrade trade = getTrade(shop, itemClicked);
		if(trade == null) {return;}
		
		if(type.isShiftClick()) {
			int amt = itemClicked.getAmount();
			for(int i = 0; i < amt; i++) {
				itemClicked.subtract();
				trade.buyOneFrom(player);
			}
		} else {
			itemClicked.subtract();
			trade.buyOneFrom(player);
		}
		
		// Redraw inv
		GeneralInventoryBuilder.updateInventory(inv, ShopBuilder.buildNPCShop(shop));
		
	}
	
	private static void attemptingToBuy(Player player, ItemStack itemClicked, ClickType type, Inventory inv) {
		
		if(!itemClicked.hasItemMeta() || !itemClicked.getItemMeta().hasCustomModelData()) {
			return;
		}
		
		int data = itemClicked.getItemMeta().getCustomModelData();
		ShopInstance shop = ShopManager.getShop(data);
		if(shop == null) {return;}
		
		NPCTrade trade = getTrade(shop, itemClicked);
		if(trade.isOutOfStock()) {return;}
		
		if(type.isShiftClick()) {
			int amt = trade.getQuantity();
			sellToNumberOfTimes(amt, trade, player);
		} else {
			sellToNumberOfTimes(1, trade, player);
		}
		
		// Redraw inv
		GeneralInventoryBuilder.updateInventory(inv, ShopBuilder.buildNPCShop(shop));
		
	}
	
	private static void sellToNumberOfTimes(int num, NPCTrade trade, Player player) {
		for(int i = 0; i < num; i++) {
			boolean success = trade.sellOneTo(player);
			if(success) {
				addItemToInventory(player.getInventory(), trade.getItem().toItemStack(), player.getLocation());
			} else {
				player.sendMessage(ChatColor.RED+"You don't have the funds to purchase this item!");
				return;
			}
		}
	}
	
	private static NPCTrade getTrade(ShopInstance shop, ItemStack item) {
		SimpleItem simpleItem = new SimpleItem(item, 1);
		for(NPCTrade trade : shop.getShopInventory()) {
			if(simpleItem.equals(trade.getItem())) {
				return trade;
			}
		}
		return null;
	}
	
	private static boolean addItemToInventory(Inventory inv, ItemStack i, Location loc){
		if(inventoryFull(inv, i)){
			loc.getWorld().dropItem(loc, i);
			return false;
		}else{
			inv.addItem(i);
			return true;
		}
	}
	
	private static boolean inventoryFull(Inventory inv, ItemStack item){
		boolean full = false;
		if(inv.firstEmpty()==-1){
			for(ItemStack i : inv.getContents()){
				if(i!=null){
					if(i.isSimilar(item) && i.getAmount()<i.getMaxStackSize()){
						return false;
					}else{
						full = true;
					}
				}
			}
		}
		return full;
	}
	
}
