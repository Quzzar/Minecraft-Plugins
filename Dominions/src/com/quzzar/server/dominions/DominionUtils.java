package com.quzzar.server.dominions;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.misc.DiplomacyState;
import com.quzzar.server.dominions.misc.PermissionCategory;
import com.quzzar.server.dominions.misc.PlayerRank;
import com.quzzar.server.dominions.settings.SettingType;
import com.quzzar.server.souls.character.CharacterManager;

public class DominionUtils {
	
	public static String packChunk(Chunk chunk) {
		long packV = (((long)chunk.getX()) << 32) | (chunk.getZ() & 0xffffffffL);
		UUID wUUID = chunk.getWorld().getUID();
		return packV+"~"+wUUID;
	}
	
	public static Chunk unpackChunk(String val) {
		String[] parts = val.split("~");
		
		long packV = Long.parseLong(parts[0]);
		int x = (int)(packV >> 32);
		int z = (int)packV;
		
		UUID wUUID = UUID.fromString(parts[1]);
		World world = Bukkit.getWorld(wUUID);
		
		return world.getChunkAt(x, z);
	}
	
	
	public static Dominion getDominionByChunk(Chunk chunk) {
		if(chunk!=null) {
			for(Dominion dominion : DominionManager.getDominions()) {
				if(dominion.hasClaimed(chunk)) {
					return dominion;
				}
			}
		}
		return null;
	}
	
	public static Dominion getDominionByPlayer(UUID playerUUID) {
		for(Dominion dominion : DominionManager.getDominions()) {
			if(dominion.getMembers().containsKey(playerUUID)) {
				return dominion;
			}
		}
		return null;
	}
	
	public static Dominion getDominionByUUID(UUID domUUID) {
		for(Dominion dominion : DominionManager.getDominions()) {
			if(dominion.getUUID().equals(domUUID)) {
				return dominion;
			}
		}
		return null;
	}
	
	public static Dominion getDominionByName(String domName) {
		for(Dominion dominion : DominionManager.getDominions()) {
			if(dominion.getName().equalsIgnoreCase(domName)) {
				return dominion;
			}
		}
		return null;
	}
	
	public static ArrayList<String> getRankNames(Dominion dominion, PlayerRank rank){
		ArrayList<String> rankNames = new ArrayList<String>();
		
		for(UUID uuid : dominion.getMembers().keySet()) {
			if(dominion.getMembers().get(uuid).equals(rank)) {
				rankNames.add(CharacterManager.getCharacterData(uuid).getName());
			}
		}
		
		return rankNames;
	}
	
	public static ArrayList<UUID> getRankUUIDs(Dominion dominion, PlayerRank rank){
		ArrayList<UUID> rankUUIDs = new ArrayList<UUID>();
		
		for(UUID uuid : dominion.getMembers().keySet()) {
			if(dominion.getMembers().get(uuid).equals(rank)) {
				rankUUIDs.add(uuid);
			}
		}
		
		return rankUUIDs;
	}
	
	public static ArrayList<String> getRelationNames(Dominion dominion, DiplomacyState state){
		ArrayList<String> relationNames = new ArrayList<String>();
		
		// Dominion relations
		for(UUID uuid : dominion.getDominionDiplomacy().keySet()) {
			if(dominion.getDominionDiplomacy().get(uuid).equals(state)) {
				Dominion otherDom = getDominionByUUID(uuid);
				if(otherDom!=null) {
					relationNames.add(otherDom.getName());
				}
			}
		}
		
		// Player relations
		for(UUID uuid : dominion.getPlayerDiplomacy().keySet()) {
			if(dominion.getPlayerDiplomacy().get(uuid).equals(state)) {
				OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
				if(player!=null) {
					relationNames.add(player.getName());
				}
			}
		}
		
		return relationNames;
	}
	
	public static void sendDominionAnnouncement(Dominion dominion, String message) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(dominion.getMembers().keySet().contains(player.getUniqueId())) {
				Utility.sendActionMessage(player, message);
				//player.sendMessage(ChatColor.DARK_AQUA+" "+dominion.getName()
				//+ChatColor.DARK_PURPLE+" - "+ChatColor.GREEN+message);
			}
		}
	}
	
	public static boolean hasHigherRank(Dominion dominion, UUID yourUUID, UUID checkingUUID) {
		return dominion.getPlayerRank(checkingUUID).getPower() > dominion.getPlayerRank(yourUUID).getPower();
	}
	
	public static boolean hasPermission(Dominion dominion, UUID uuid, SettingType type) {
		
		PermissionCategory pCategory = dominion.getPermissionCategory(uuid);
		PermissionCategory requiredCategory = dominion.getSettings().get(type);
		
		if(pCategory == null || requiredCategory == null)
			return true;
		
		return pCategory.getPower() >= requiredCategory.getPower();
	}

	
	public static boolean forceRemoveDominionMember(Dominion dominion, OfflinePlayer p) {
		
		if(dominion == null) {return false;}
		
		dominion.getMembers().remove(p.getUniqueId());
		
		boolean hasLeader = false;
		for(PlayerRank rank : dominion.getMembers().values()) {
			if(rank == PlayerRank.Leader) {
				hasLeader = true;
			}
		}
		
		if(!hasLeader) {
			Bukkit.broadcastMessage(
					ChatColor.RED+""+ChatColor.ITALIC+dominion.getName()+
					ChatColor.BLUE+""+ChatColor.ITALIC+" has lost its only leader and has perished...");
			DominionManager.deleteDominion(dominion);
			return true;
		}
		return false;
	}
	
	
}
