package com.quzzar.server.dominions.settings.chunk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

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
import com.quzzar.server.dominions.misc.ChunkSettingState;

public class IntroChunkSettings {
	
	private static final int SIZE = 18;
	
	private static final int LIST_INDEX = 4, ADD_INDEX = 10, CHANGE_INDEX = 13, REMOVE_INDEX = 16;
	
	
	public static void open(HumanEntity player, Chunk chunk, Dominion dominion) {
		
		Inventory introSettingsInv = Bukkit.createInventory(null, SIZE, ChatColor.BOLD+"["+chunk.getX()*16+", "+chunk.getZ()*16+"] Settings");
		
		CraftMeta.invSet(introSettingsInv, InvDataTag.DOM_CHUNK_SETTINGS_INTRO);
		
		draw(dominion, chunk, introSettingsInv);
		
		player.openInventory(introSettingsInv);
		
	}
	
	///
	
	public static void update(int index, HumanEntity he, Inventory inv) {
		
		Player player = (Player) he;
		
		// Get Player's Dominion
		Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
		
		// Get current chunk of player editing settings
		Chunk chunk = player.getLocation().getChunk();
		if(!ChunkSettingsUtils.confirmChunkIsValid(chunk, dominion)) {
			player.sendMessage(ChatColor.RED+"You may only edit chunk settings in a chunk your dominion owns!");
			return;
		}
		
		// If press, 
		if(index == ADD_INDEX) {
			
			AnvilToPlayerHandler.openAddPlayer(player, dominion, chunk);
			
		} else if(index == CHANGE_INDEX) {
			
			AnvilToPlayerHandler.openSearchPlayer(player, dominion, chunk);
			
		} else if(index == REMOVE_INDEX) {
			
			AnvilToPlayerHandler.openRemovePlayer(player, dominion, chunk);
			
		}
		
	}
	
	///
	
	private static void draw(Dominion dominion, Chunk chunk, Inventory inv) {
		
		inv.clear();
		
		HashMap<UUID, HashMap<ChunkSettingType, ChunkSettingState>> chunkSettings = dominion.getChunkSettings(chunk);
		
		///
		
		ItemStack playersSettingsList = new ItemStack(Material.WRITABLE_BOOK);
		ItemMeta pListMeta = playersSettingsList.getItemMeta();
		pListMeta.setDisplayName(ChatColor.GOLD+"Active Player Settings");
		
		ArrayList<String> pslore = new ArrayList<String>();
		for(Entry<UUID, HashMap<ChunkSettingType, ChunkSettingState>> entry : chunkSettings.entrySet()) {
			OfflinePlayer player = Bukkit.getOfflinePlayer(entry.getKey());
			if(player != null) {
				pslore.add(ChatColor.AQUA+" - "+player.getName());
			}
		}
		if(pslore.isEmpty()) {
			pslore.add(ChatColor.AQUA+""+ChatColor.ITALIC+"None currently set!");
		}
		pListMeta.setLore(pslore);
		playersSettingsList.setItemMeta(pListMeta);
		
		inv.setItem(LIST_INDEX, playersSettingsList);
		
		///
		
		ItemStack addPlayer = new ItemStack(Material.GREEN_DYE);
		ItemMeta addPMeta = addPlayer.getItemMeta();
		addPMeta.setDisplayName(ChatColor.GOLD+"Add Player Setting");
		
		ArrayList<String> aplore = new ArrayList<String>();
		aplore.add(ChatColor.AQUA+"Set a new player's settings");
		aplore.add(ChatColor.AQUA+"in this chunk");
		addPMeta.setLore(aplore);
		addPlayer.setItemMeta(addPMeta);
		
		inv.setItem(ADD_INDEX, addPlayer);
		
		///
		
		ItemStack changePlayer = new ItemStack(Material.FIREWORK_STAR);
		ItemMeta changePMeta = changePlayer.getItemMeta();
		changePMeta.setDisplayName(ChatColor.GOLD+"Change Player Setting");
		
		ArrayList<String> cplore = new ArrayList<String>();
		cplore.add(ChatColor.AQUA+"Adjust an existing player's");
		cplore.add(ChatColor.AQUA+"settings");
		changePMeta.setLore(cplore);
		changePlayer.setItemMeta(changePMeta);
		
		inv.setItem(CHANGE_INDEX, changePlayer);
		
		///
		
		ItemStack removePlayer = new ItemStack(Material.RED_DYE);
		ItemMeta removePMeta = removePlayer.getItemMeta();
		removePMeta.setDisplayName(ChatColor.GOLD+"Remove Player Setting");
		
		ArrayList<String> rplore = new ArrayList<String>();
		rplore.add(ChatColor.AQUA+"Remove an existing player's");
		rplore.add(ChatColor.AQUA+"settings from this chunk");
		removePMeta.setLore(rplore);
		removePlayer.setItemMeta(removePMeta);
		
		inv.setItem(REMOVE_INDEX, removePlayer);
		
		
	}
	
	
	
}
