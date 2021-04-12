package com.quzzar.server.dominions.settings.chunk;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.InvDataTag;
import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.misc.ChunkSettingState;
import com.quzzar.server.dominions.upgrades.UpgradeType;

public class PlayerChunkSettings {
	
	private static final int SIZE = 18;
	
	public static void open(Dominion dominion, Player player, OfflinePlayer pSettings, HashMap<ChunkSettingType, ChunkSettingState> playerChunkSettings) {
		
		Inventory chunkSettingsInv = Bukkit.createInventory(null, SIZE, ChatColor.BOLD+"Chunk Settings: "+pSettings.getName());
		
		CraftMeta.invSet(chunkSettingsInv, InvDataTag.DOM_CHUNK_SETTINGS_PLAYER);
		
		draw(chunkSettingsInv, playerChunkSettings, dominion);
		
		player.openInventory(chunkSettingsInv);
		
	}
	
	///
	
	public static void update(int index, HumanEntity player, Inventory inv, String inventoryTitle) {
		
		ChunkSettingType setting = ChunkSettingType.getByIndex(index);
		if(setting.equals(ChunkSettingType.NULL) || setting.equals(ChunkSettingType.INFO)) {
			return;
		}
		
		// Get Player's Dominion
		Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
		
		// Get current chunk of player editing settings
		Chunk chunk = player.getLocation().getChunk();
		if(!ChunkSettingsUtils.confirmChunkIsValid(chunk, dominion)) {
			player.sendMessage(ChatColor.RED+"You may only edit chunk settings in a chunk your dominion owns!");
			return;
		}
		
		// Get Player Whose Settings These Are For
		String[] splitOfInvName = inventoryTitle.split(" ");
		OfflinePlayer pSettings = Utility.getOfflinePlayer(splitOfInvName[2]);
		if(pSettings == null) {
			player.sendMessage(ChatColor.RED+"The player \""+splitOfInvName[2]+"\" cannot be found!");
			return;
		}
		
		// Get Player Settings (shouldn't ever be null)
		HashMap<ChunkSettingType, ChunkSettingState> playerChunkSettings = dominion.getChunkSettings(chunk).get(pSettings.getUniqueId());
		
		// If press, 
		if(setting.equals(ChunkSettingType.BACK)) {
			IntroChunkSettings.open(player, chunk, dominion);
			return;
		} else if(setting.equals(ChunkSettingType.OPEN_CONTAINERS) && dominion.hasUpgrade(UpgradeType.PADLOCKS)) {
			toggleSetting(setting, playerChunkSettings);
		} else if(setting.equals(ChunkSettingType.INTERACT)) {
			toggleSetting(setting, playerChunkSettings);
		} else if(setting.equals(ChunkSettingType.BREAK_AND_PLACE)) {
			toggleSetting(setting, playerChunkSettings);
		}
		
		// Save any adjusted settings to map
		dominion.getChunkSettings(chunk).put(pSettings.getUniqueId(), playerChunkSettings);
		// Redraw inventory
		draw(inv, playerChunkSettings, dominion);
		
	}
	
	///
	
	private static void toggleSetting(ChunkSettingType setting, HashMap<ChunkSettingType, ChunkSettingState> playerChunkSettings) {
		ChunkSettingState state = playerChunkSettings.get(setting);
		if(state.equals(ChunkSettingState.DISABLED)) {
			playerChunkSettings.put(setting, ChunkSettingState.ALLOWED);
			return;
		} else if(state.equals(ChunkSettingState.ALLOWED)) {
			playerChunkSettings.put(setting, ChunkSettingState.DISALLOWED);
			return;
		} else if(state.equals(ChunkSettingState.DISALLOWED)) {
			playerChunkSettings.put(setting, ChunkSettingState.DISABLED);
			return;
		}
	}
	
	///
	
	private static void draw(Inventory inv, HashMap<ChunkSettingType, ChunkSettingState> playerChunkSettings, Dominion dominion) {
		
		inv.clear();
		
		// Back Button - ItemStack
		ItemStack backBtn = new ItemStack(Material.SPRUCE_DOOR);
		ItemMeta backMeta = backBtn.getItemMeta();
		backMeta.setDisplayName(ChatColor.GOLD+"<- Back");
		
		backBtn.setItemMeta(backMeta);
		
		inv.setItem(ChunkSettingType.BACK.getIndex(), backBtn);
		
		// Info Item - ItemStack
		ItemStack infoItem = new ItemStack(Material.PAPER);
		ItemMeta infoMeta = backBtn.getItemMeta();
		infoMeta.setDisplayName(ChatColor.BLUE+""+ChatColor.BOLD+" Note.");
		
		ArrayList<String> infoLore = new ArrayList<String>();
		infoLore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"If a setting is disabled, that");
		infoLore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"means the setting will have no");
		infoLore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"effect. But Dominion settings");
		infoLore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"will still have an impact on a");
		infoLore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"player's permissions throughout");
		infoLore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"the Dominion (based off rank).");
		infoMeta.setLore(infoLore);
		
		infoItem.setItemMeta(infoMeta);
		
		inv.setItem(ChunkSettingType.INFO.getIndex(), infoItem);
		
		// Settings ItemStacks
		inv.setItem(ChunkSettingType.BREAK_AND_PLACE.getIndex(), generateSettingsItem(
				playerChunkSettings.get(ChunkSettingType.BREAK_AND_PLACE),
				"Break and Place Blocks: ",
				"Allows player to break and place blocks"));
		
		inv.setItem(ChunkSettingType.INTERACT.getIndex(), generateSettingsItem(
				playerChunkSettings.get(ChunkSettingType.INTERACT),
				"Interact with Blocks: ",
				"Allows player to use of doors, levers,@buttons, etc"));
		
		if(dominion.hasUpgrade(UpgradeType.PADLOCKS)) {
			inv.setItem(ChunkSettingType.OPEN_CONTAINERS.getIndex(), generateSettingsItem(
					playerChunkSettings.get(ChunkSettingType.OPEN_CONTAINERS),
					"Open Containers: ",
					"Allows player to open chests, furnaces,@hoppers, etc"));
		}
		
	}
	
	
	private static ItemStack generateSettingsItem(ChunkSettingState settingState, String name, String description) {
		
		ItemStack item = new ItemStack(getSettingStateMaterial(settingState));
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.GOLD+name+getSettingStateTag(settingState));
		
		String[] descriptionLines = description.split("@");
		
		ArrayList<String> lore = new ArrayList<String>();
		for(String descLine : descriptionLines) {
			lore.add(ChatColor.AQUA+descLine);
		}
		itemMeta.setLore(lore);
		
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	private static String getSettingStateTag(ChunkSettingState state) {
		if(state.equals(ChunkSettingState.ALLOWED)) {
			return ChatColor.GREEN+""+ChatColor.BOLD+"Allowed";
		} else if (state.equals(ChunkSettingState.DISALLOWED)) {
			return ChatColor.RED+""+ChatColor.BOLD+"Disallowed";
		} else {
			return ChatColor.GRAY+""+ChatColor.BOLD+"Disabled";
		}
	}
	
	private static Material getSettingStateMaterial(ChunkSettingState state) {
		if(state.equals(ChunkSettingState.ALLOWED)) {
			return Material.LIME_STAINED_GLASS_PANE;
		} else if (state.equals(ChunkSettingState.DISALLOWED)) {
			return Material.RED_STAINED_GLASS_PANE;
		} else {
			return Material.BLACK_STAINED_GLASS_PANE;
		}
	}
	
	public static HashMap<ChunkSettingType, ChunkSettingState> setSettingsDefaults() {
		HashMap<ChunkSettingType, ChunkSettingState> playerChunkSettings = new HashMap<ChunkSettingType, ChunkSettingState>();
		
		playerChunkSettings.put(ChunkSettingType.BREAK_AND_PLACE, ChunkSettingState.DISABLED);
		playerChunkSettings.put(ChunkSettingType.INTERACT, ChunkSettingState.DISABLED);
		playerChunkSettings.put(ChunkSettingType.OPEN_CONTAINERS, ChunkSettingState.DISABLED);
		
		return playerChunkSettings;
	}
	
	
}
