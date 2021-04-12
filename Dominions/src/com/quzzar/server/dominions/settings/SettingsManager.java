package com.quzzar.server.dominions.settings;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.InvDataTag;
import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.logs.LogManager;
import com.quzzar.server.dominions.misc.PermissionCategory;
import com.quzzar.server.dominions.upgrades.UpgradeType;

public class SettingsManager{
	
	private static final int size = 54;
	
	public static HashMap<SettingType, String> settingsMap;
	
	public static void initialize() {
		
		settingsMap = new HashMap<SettingType, String>();
		
		settingsMap.put(SettingType.INVITE, "Invite>Can invite players to@the Dominion using /d invite");
		settingsMap.put(SettingType.UNINVITE, "Uninvite>Can uninvite players to@the Dominion using /d uninvite");
		
		settingsMap.put(SettingType.CLAIM, "Claim>Can claim Dominion territory@using /d claim");
		settingsMap.put(SettingType.UNCLAIM, "Unclaim>Can unclaim Dominion territory@using /d unclaim");
		
		settingsMap.put(SettingType.SET_HOME, "SetHome>Can set the Dominion home@using /d sethome");
		settingsMap.put(SettingType.HOME, "Home>Can teleport to the Dominion home@using /d home");
		
		settingsMap.put(SettingType.DEPOSIT, "Deposit>Can deposit money into the Dominion@vault using /d deposit");
		settingsMap.put(SettingType.WITHDRAW, "Withdraw>Can withdraw money from the Dominion@vault using /d withdraw");
		
		settingsMap.put(SettingType.UPGRADES, "Upgrades>Can view and buy upgrades@for the Dominion (/d upgrades)");
		settingsMap.put(SettingType.SETTINGS, "Settings>Can view and change settings@for the Dominion (/d settings)");
		settingsMap.put(SettingType.CHUNK_SETTINGS, "ChunkSettings>Can view and change settings for@a specific "
				+ "chunk in the Dominion@(/d chunksettings)");
		
		settingsMap.put(SettingType.JOIN, "Join>Changes how players can join@the Dominion using /d join");
		settingsMap.put(SettingType.DESCRIPTION, "Description>Can set the Dominion description@using /d desc");
		settingsMap.put(SettingType.SET_DIPLOMACY, "SetDiplomacy>Can set diplomacy with other@Dominions using /d setdiplomacy");
		
		settingsMap.put(SettingType.LOG, "Get Log>Can get Dominion logs for@$"+LogManager.costToGetLog+" by using /d log");
		
		settingsMap.put(SettingType.BREAK_AND_PLACE, "Break and Place Blocks>Allows breaking and placing of blocks@anywhere in the Dominion");
		settingsMap.put(SettingType.INTERACT, "Interact with Blocks>Allows usage of doors, levers,@buttons, etc anywhere in the Dominion");
		settingsMap.put(SettingType.OPEN_CONTAINERS, "Open Containers>Allows the opening of chests, furnaces,@hoppers, etc anywhere in the Dominion");
		
	}
	
	public static void setDefaults(Dominion dominion) {
		
		dominion.getSettings().put(SettingType.INVITE, PermissionCategory.GeneralsAndUp);
		dominion.getSettings().put(SettingType.UNINVITE, PermissionCategory.GeneralsAndUp);
		
		dominion.getSettings().put(SettingType.CLAIM, PermissionCategory.GeneralsAndUp);
		dominion.getSettings().put(SettingType.UNCLAIM, PermissionCategory.GeneralsAndUp);
		
		dominion.getSettings().put(SettingType.SET_HOME, PermissionCategory.LeadersOnly);
		dominion.getSettings().put(SettingType.HOME, PermissionCategory.MembersAndUp);
		
		dominion.getSettings().put(SettingType.DEPOSIT, PermissionCategory.MembersAndUp);
		dominion.getSettings().put(SettingType.WITHDRAW, PermissionCategory.LeadersOnly);
		
		dominion.getSettings().put(SettingType.UPGRADES, PermissionCategory.GeneralsAndUp);
		dominion.getSettings().put(SettingType.SETTINGS, PermissionCategory.LeadersOnly);
		dominion.getSettings().put(SettingType.CHUNK_SETTINGS, PermissionCategory.LeadersOnly);
		
		dominion.getSettings().put(SettingType.JOIN, PermissionCategory.VeteransAndUp);
		dominion.getSettings().put(SettingType.DESCRIPTION, PermissionCategory.LeadersOnly);
		dominion.getSettings().put(SettingType.SET_DIPLOMACY, PermissionCategory.GeneralsAndUp);
		
		dominion.getSettings().put(SettingType.LOG, PermissionCategory.GeneralsAndUp);
		
		dominion.getSettings().put(SettingType.BREAK_AND_PLACE, PermissionCategory.GeneralsAndUp);
		dominion.getSettings().put(SettingType.INTERACT, PermissionCategory.MembersAndUp);
		
	}
	
	public static void openSettings(HumanEntity player, Dominion dominion) {
		
		Inventory settingsInv = Bukkit.createInventory(null, size, ChatColor.BOLD+dominion.getName()+" Settings");
		
		CraftMeta.invSet(settingsInv, InvDataTag.DOM_SETTINGS);
		
		// Upgrades, Settings, ChunkSettings
		// Join, Desc, SetDiplomacy
		
		// Invite, UnInvite, Claim, UnClaim, SetHome, Home, Deposit, Withdraw
		
		// Update to give padlock setting access if needed
		if(dominion.hasUpgrade(UpgradeType.PADLOCKS) && !dominion.getSettings().containsKey(SettingType.OPEN_CONTAINERS)) {
			dominion.getSettings().put(SettingType.OPEN_CONTAINERS, PermissionCategory.Anyone);
		}
		
		drawInventory(dominion, settingsInv);
		
		player.openInventory(settingsInv);
		
	}
	
	public static void updateSettings(int index, HumanEntity player, Inventory settingsInv) {
		
		SettingType setting = SettingType.getByIndex(index);
		
		Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
		
		if(dominion.getSettings().keySet().contains(setting)) {
			PermissionCategory newCategory;
			if(setting.getIndex()>=45) {
				newCategory = cycleAllCategories(dominion.getSettings().get(setting));
			} else {
				newCategory = cycleMemberCategories(dominion.getSettings().get(setting));
			}
			dominion.getSettings().put(setting, newCategory);
			
			drawInventory(dominion, settingsInv);
		}
		
	}
	
	///
	
	private static void drawInventory(Dominion dominion, Inventory settingsInv) {
		
		settingsInv.clear();
		
		for(SettingType type : dominion.getSettings().keySet()) {
			
			PermissionCategory category = dominion.getSettings().get(type);
			settingsInv.setItem(type.getIndex(), getPane(category, type));
			
		}
		
	}
	
	///
	
	private static ItemStack getPane(PermissionCategory category, SettingType type) {
		
		String text = settingsMap.get(type);
		
		String[] parts = text.split(">");
		String title = parts[0];
		String description = parts[1];
		
		ItemStack pane;
		ItemMeta im;
		
		if(category.equals(PermissionCategory.LeadersOnly)) {
			
			pane = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
			im = pane.getItemMeta();
			im.setDisplayName(ChatColor.GOLD+title+": "+category.getColor()+""+ChatColor.BOLD+"Leaders Only");
			
		} else if(category.equals(PermissionCategory.GeneralsAndUp)) {
			
			pane = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
			im = pane.getItemMeta();
			im.setDisplayName(ChatColor.GOLD+title+": "+category.getColor()+""+ChatColor.BOLD+"Generals & Leaders");
			
		} else if(category.equals(PermissionCategory.VeteransAndUp)) {
			
			pane = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
			im = pane.getItemMeta();
			im.setDisplayName(ChatColor.GOLD+title+": "+category.getColor()+""+ChatColor.BOLD+"Veterans, Generals & Leaders");
			
		} else if(category.equals(PermissionCategory.MembersAndUp)) {
			
			pane = new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE);
			im = pane.getItemMeta();
			im.setDisplayName(ChatColor.GOLD+title+": "+category.getColor()+""+ChatColor.BOLD+"All Members");
			
		} else if(category.equals(PermissionCategory.AllMembersAndAllies)) {
			
			pane = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
			im = pane.getItemMeta();
			im.setDisplayName(ChatColor.GOLD+title+": "+category.getColor()+""+ChatColor.BOLD+"All Members and Allies");
			
		} else if(category.equals(PermissionCategory.AnyoneButEnemies)) {
			
			pane = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
			im = pane.getItemMeta();
			im.setDisplayName(ChatColor.GOLD+title+": "+category.getColor()+""+ChatColor.BOLD+"Anyone but Enemies");
			
		} else {
			
			pane = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
			im = pane.getItemMeta();
			im.setDisplayName(ChatColor.GOLD+title+": "+category.getColor()+""+ChatColor.BOLD+"Anyone");
			
		}
		
		// Exception for Join
		if(type.equals(SettingType.JOIN)) {
			if(category.equals(PermissionCategory.MembersAndUp)) {
				
				pane = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				im = pane.getItemMeta();
				im.setDisplayName(ChatColor.GOLD+title+": "+ChatColor.GREEN+""+ChatColor.BOLD+"Open");
				
			} else if(category.equals(PermissionCategory.VeteransAndUp)) {
				
				pane = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
				im = pane.getItemMeta();
				im.setDisplayName(ChatColor.GOLD+title+": "+ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"Invite Only");
				
			} else {
				
				pane = new ItemStack(Material.RED_STAINED_GLASS_PANE);
				im = pane.getItemMeta();
				im.setDisplayName(ChatColor.GOLD+title+": "+ChatColor.RED+""+ChatColor.BOLD+"Closed");
				
			}
		}
		//
		
		String[] descriptionLines = description.split("@");
		
		ArrayList<String> lore = new ArrayList<String>();
		for(String descLine : descriptionLines) {
			lore.add(ChatColor.AQUA+descLine);
		}
		im.setLore(lore);
		
		pane.setItemMeta(im);
		
		return pane;
	}
	
	private static PermissionCategory cycleMemberCategories(PermissionCategory category) {
		if(category.equals(PermissionCategory.MembersAndUp)) {
			return PermissionCategory.VeteransAndUp;
		} else if(category.equals(PermissionCategory.VeteransAndUp)) {
			return PermissionCategory.GeneralsAndUp;
		} else if(category.equals(PermissionCategory.GeneralsAndUp)) {
			return PermissionCategory.LeadersOnly;
		} else if(category.equals(PermissionCategory.LeadersOnly)) {
			return PermissionCategory.MembersAndUp;
		}
		return PermissionCategory.MembersAndUp;
	}
	
	private static PermissionCategory cycleAllCategories(PermissionCategory category) {
		if(category.equals(PermissionCategory.Anyone)) {
			return PermissionCategory.AnyoneButEnemies;
		} else if(category.equals(PermissionCategory.AnyoneButEnemies)) {
			return PermissionCategory.AllMembersAndAllies;
		} else if(category.equals(PermissionCategory.AllMembersAndAllies)) {
			return PermissionCategory.MembersAndUp;
		} else if(category.equals(PermissionCategory.MembersAndUp)) {
			return PermissionCategory.VeteransAndUp;
		} else if(category.equals(PermissionCategory.VeteransAndUp)) {
			return PermissionCategory.GeneralsAndUp;
		} else if(category.equals(PermissionCategory.GeneralsAndUp)) {
			return PermissionCategory.LeadersOnly;
		} else if(category.equals(PermissionCategory.LeadersOnly)) {
			return PermissionCategory.Anyone;
		}
		return PermissionCategory.Anyone;
	}
	
}
