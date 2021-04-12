package com.quzzar.server.dominions.upgrades;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.InvDataTag;
import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.logs.LogType;
import com.quzzar.server.dominions.misc.PersistentEntities;

public class UpgradesManager {
	
	private static final int size = 27;
	
	public static HashMap<Integer, GenericUpgrade> upgradesMap;
	
	public static void initialize() {
		
		upgradesMap = new HashMap<Integer, GenericUpgrade>();
		
		GenericUpgrade prot_T1 = new GenericUpgrade(UpgradeType.PROT_T1, 
				"Protection Tier I", 
				"Prevents T1 Explosives within@your territory.",
				5000,
				new ItemStack(Material.LEATHER_CHESTPLATE));
		GenericUpgrade prot_T2 = new GenericUpgrade(UpgradeType.PROT_T2, 
				"Protection Tier II", 
				"Prevents T1 and T2 Explosives@within your territory.",
				10000,
				new ItemStack(Material.IRON_CHESTPLATE));
		GenericUpgrade prot_T3 = new GenericUpgrade(UpgradeType.PROT_T3, 
				"Protection Tier III", 
				"Prevents T1, T2, and T3 Explosives@within your territory.",
				20000,
				new ItemStack(Material.DIAMOND_CHESTPLATE));
		
		prot_T1.setNextUpgrade(prot_T2);
		prot_T2.setNextUpgrade(prot_T3);
		
		GenericUpgrade courage_T1 = new GenericUpgrade(UpgradeType.COURAGE_T1, 
				"Courage Tier I", 
				"Gives members Strength I,@when entering your territory.",
				2500,
				new ItemStack(Material.STONE_SWORD));
		GenericUpgrade courage_T2 = new GenericUpgrade(UpgradeType.COURAGE_T2, 
				"Courage Tier II", 
				"Gives members Strength II,@when entering your territory.",
				5000,
				new ItemStack(Material.IRON_SWORD));
		
		courage_T1.setNextUpgrade(courage_T2);
		
		GenericUpgrade intimidation_T1 = new GenericUpgrade(UpgradeType.INTIMIDATION_T1, 
				"Intimidation Tier I", 
				"Gives enemies Weakness I,@when entering your territory.",
				2500,
				new ItemStack(Material.ZOMBIE_HEAD, 1));
		GenericUpgrade intimidation_T2 = new GenericUpgrade(UpgradeType.INTIMIDATION_T2, 
				"Intimidation Tier II", 
				"Gives enemies Weakness II,@when entering your territory.",
				5000,
				new ItemStack(Material.SKELETON_SKULL, 1));
		
		intimidation_T1.setNextUpgrade(intimidation_T2);
		
		GenericUpgrade space = new GenericUpgrade(UpgradeType.PERSONAL_SPACE, 
				"Personal Space", 
				"Other Dominions can't claim@chunks directly next to yours.",
				10000,
				new ItemStack(Material.CACTUS));
		
		GenericUpgrade padlocks = new GenericUpgrade(UpgradeType.PADLOCKS, 
				"Padlocks", 
				"Unlocks setting (in /d settings)@to set who can and can't open@containers (chests) in the Dominion.",
				12000,
				new ItemStack(Material.CHEST));
		
		GenericUpgrade gift_from_gods = new GenericUpgrade(UpgradeType.GIFT_FROM_GODS, 
				"Gift From The Gods", 
				"Gives members Regeneration I,@when entering your territory.",
				18000,
				new ItemStack(Material.GOLDEN_APPLE));
		
		GenericUpgrade poison_gas = new GenericUpgrade(UpgradeType.POISON_GAS, 
				"Poison Gas", 
				"Gives enemies Poison I,@when entering your territory.",
				16000,
				new ItemStack(Material.POISONOUS_POTATO));
		
		
		GenericUpgrade ge_trader = new GenericUpgrade(UpgradeType.GE_TRADER, 
				"GE Trader", 
				"A Grand Exchange Trader stays@around the Dominion home for@easy Grand Exchange access.",
				2000,
				new ItemStack(Material.EMERALD));
		
		GenericUpgrade syndicate = new GenericUpgrade(UpgradeType.SYNDICATE, 
				"Syndicate", 
				"All upgrades that benefit@your members will also@benefit your allies as well.",
				5000,
				new ItemStack(Material.WRITABLE_BOOK));
		
		GenericUpgrade colonization = new GenericUpgrade(UpgradeType.COLONIZATION, 
				"Colonization", 
				"You may claim chunks anywhere,@not just directly next to@your existing territory.@"
						+ChatColor.ITALIC+"<You cannot purchase Imperialism>",
				18000,
				new ItemStack(Material.COMPASS));
		
		GenericUpgrade imperialism = new GenericUpgrade(UpgradeType.IMPERIALISM, 
				"Imperialism", 
				"You may claim over chunks from@other Dominions but at a costly price.@"
						+ChatColor.ITALIC+"<You cannot purchase Colonization>",
				25000,
				new ItemStack(Material.NAME_TAG));
		
		upgradesMap.put(10, prot_T1);
		upgradesMap.put(11, courage_T1);
		upgradesMap.put(12, intimidation_T1);
		upgradesMap.put(13, space);
		upgradesMap.put(14, padlocks);
		upgradesMap.put(15, gift_from_gods);
		upgradesMap.put(16, poison_gas);
		
		upgradesMap.put(20, ge_trader);
		upgradesMap.put(21, syndicate);
		upgradesMap.put(22, colonization);
		upgradesMap.put(23, imperialism);
		
	}
	
	public static void setDefaults(Dominion dominion) {
		
		for(UpgradeType type : UpgradeType.values()) {
			dominion.getUpgrades().put(type, UpgradeState.AVAILABLE);
		}
		
	}
	
	public static void openUpgrades(HumanEntity player, Dominion dominion) {
		
		Inventory upgradesInv = Bukkit.createInventory(null, size, ChatColor.BOLD+dominion.getName()+" Upgrades");
		
		CraftMeta.invSet(upgradesInv, InvDataTag.DOM_UPGRADES);
		
		drawInventory(dominion, upgradesInv);
		
		player.openInventory(upgradesInv);
		
	}
	
	public static void updateUpgrades(int index, HumanEntity player, Inventory upgradesInv) {
		
		Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
		
		if(upgradesMap.keySet().contains(index)) {
			
			// Get the latest upgrade.
			GenericUpgrade upgrade = getLatestUpgrade(dominion, upgradesMap.get(index));
			
			// If the latest upgrade is available, attempt to buy it
			if(dominion.getUpgrades().get(upgrade.getType()).equals(UpgradeState.AVAILABLE)) {
				
				// If success, buy it. If not, tell them.
				if(dominion.withdrawMoney(upgrade.getCost(), false)) {
					dominion.getUpgrades().put(upgrade.getType(), UpgradeState.PURCHASED);
					
					// Disable respective collonization / imperialism, if need be
					if(upgrade.getType().equals(UpgradeType.IMPERIALISM)) {
						dominion.getUpgrades().put(UpgradeType.COLONIZATION, UpgradeState.UNAVAILABLE);
					}
					if(upgrade.getType().equals(UpgradeType.COLONIZATION)) {
						dominion.getUpgrades().put(UpgradeType.IMPERIALISM, UpgradeState.UNAVAILABLE);
					}
					
					// If GE Trader is purchased, spawn trader & llama
					if(upgrade.getType().equals(UpgradeType.GE_TRADER)) {
						PersistentEntities.giveGETrader(dominion);
					}
					
					// Make finance log record
					dominion.addLogRecord(LogType.FINANCE, player.getName()+" purchased the "+upgrade.getName()+" upgrade, costing "
							+ChatColor.RED+"$"+upgrade.getCost()+ChatColor.RESET+".");
					
					// Then draw the inventory again.
					drawInventory(dominion, upgradesInv);
					DominionUtils.sendDominionAnnouncement(dominion, 
							ChatColor.GOLD+""+ChatColor.BOLD+upgrade.getName()+" upgrade has been purchased.");
				} else {
					player.sendMessage(
							ChatColor.RED+"Your Dominion doesn't have enough money to buy this upgrade!");
					if(player instanceof Player) {
						((Player)player).playSound(player.getLocation(),
								Sound.BLOCK_ANVIL_LAND, 1f, 0.5f);
					}
				}
				
			}
			
		}
		
	}
	
	///
	
	private static void drawInventory(Dominion dominion, Inventory upgradesInv) {
		
		upgradesInv.clear();
		
		for(int index : upgradesMap.keySet()) {
			
			// Get the latest upgrade.
			GenericUpgrade upgrade = getLatestUpgrade(dominion, upgradesMap.get(index));
			
			// If you own the latest, show that it's purchased. If not display the latest.
			UpgradeState upState = dominion.getUpgrades().get(upgrade.getType());
			if(upState.equals(UpgradeState.PURCHASED)) {
				upgradesInv.setItem(index, getPurchasedVersion(upgrade.getItemIcon()));
			} if (upState.equals(UpgradeState.UNAVAILABLE)) {
				upgradesInv.setItem(index, getUnavailableVersion(upgrade.getItemIcon()));
			} else {
				upgradesInv.setItem(index, upgrade.getItemIcon());
			}
			
		}
		
	}
	
	///
	
	private static GenericUpgrade getLatestUpgrade(Dominion dominion, GenericUpgrade startingUpgrade) {
		GenericUpgrade upgrade = startingUpgrade;
		while(dominion.getUpgrades().get(upgrade.getType()).equals(UpgradeState.PURCHASED) && upgrade.getNextUpgrade()!=null) {
			upgrade = upgrade.getNextUpgrade();
		}
		return upgrade;
	}
	
	private static ItemStack getPurchasedVersion(ItemStack item) {
		ItemMeta im = item.getItemMeta();
		
		List<String> newLore = im.getLore();
		newLore.set(0, "  "+ChatColor.GOLD+""+ChatColor.ITALIC+""+ChatColor.BOLD+"Purchased!");
		im.setLore(newLore);
		
		item.setItemMeta(im);
		return item;
	}
	
	private static ItemStack getUnavailableVersion(ItemStack item) {
		ItemMeta im = item.getItemMeta();
		
		List<String> newLore = im.getLore();
		newLore.set(0, "  "+ChatColor.GRAY+""+ChatColor.BOLD+"Unavailable");
		im.setLore(newLore);
		
		item.setItemMeta(im);
		return item;
	}
	
}
