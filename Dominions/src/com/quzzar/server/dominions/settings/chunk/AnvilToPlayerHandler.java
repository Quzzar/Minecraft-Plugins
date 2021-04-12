package com.quzzar.server.dominions.settings.chunk;

import java.util.HashMap;

import org.bukkit.Chunk;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionsMain;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.logs.LogType;
import com.quzzar.server.dominions.misc.ChunkSettingState;

import api.anvilgui.AnvilGUI;

public class AnvilToPlayerHandler {
	
	public static void openAddPlayer(Player p, Dominion dominion, Chunk chunk) {
		
		new AnvilGUI.Builder()
	    .onComplete((player, text) -> {
	    	
			OfflinePlayer pSettings = Utility.getOfflinePlayer(text);
	    	
	    	if(pSettings != null) {
	    		
	    		if(dominion.getChunkSettings(chunk).get(pSettings.getUniqueId()) == null) {
	    			
	    			HashMap<ChunkSettingType, ChunkSettingState> playerChunkSettings = PlayerChunkSettings.setSettingsDefaults();
					dominion.getChunkSettings(chunk).put(pSettings.getUniqueId(), playerChunkSettings);
	    			
					// Make members log record
					dominion.addLogRecord(LogType.MEMBERS, player.getDisplayName()+" added a Player Setting for "
							+pSettings.getName()+" at ["+chunk.getX()*16+", "+chunk.getZ()*16+"].");
					
	    			DominionsMain.instance.getServer().getScheduler().scheduleSyncDelayedTask(DominionsMain.instance, new Runnable() {
		    			public void run() {
							
							PlayerChunkSettings.open(dominion, player, pSettings, playerChunkSettings);
							
		    			}
		    		}, 1L);
		    		return AnvilGUI.Response.close();
	    			
	    		} else {
	    			return AnvilGUI.Response.text("Already added!");
	    		}
	    		
	    	} else {
	    		return AnvilGUI.Response.text("Player not found!");
	    	}
	    	
	    })
	    .text("Name...")
	    .plugin(DominionsMain.instance)
	    .open(p);
		
	}
	
	public static void openRemovePlayer(Player p, Dominion dominion, Chunk chunk) {
		
		new AnvilGUI.Builder()
	    .onComplete((player, text) -> {
	    	
			OfflinePlayer pSettings = Utility.getOfflinePlayer(text);
	    	
	    	if(pSettings != null) {
	    		
	    		HashMap<ChunkSettingType, ChunkSettingState> playerChunkSettings = dominion.getChunkSettings(chunk).get(pSettings.getUniqueId());
	    		if(playerChunkSettings != null) {
	    			
					dominion.getChunkSettings(chunk).remove(pSettings.getUniqueId());
	    			
					// Make members log record
					dominion.addLogRecord(LogType.MEMBERS, player.getDisplayName()+" removed "+pSettings.getName()
						+"'s Player Setting at ["+chunk.getX()*16+", "+chunk.getZ()*16+"].");
					
	    			DominionsMain.instance.getServer().getScheduler().scheduleSyncDelayedTask(DominionsMain.instance, new Runnable() {
		    			public void run() {
							
		    				IntroChunkSettings.open(player, chunk, dominion);
							
		    			}
		    		}, 1L);
		    		return AnvilGUI.Response.close();
	    			
	    		} else {
	    			return AnvilGUI.Response.text("Already not added!");
	    		}
	    		
	    	} else {
	    		return AnvilGUI.Response.text("Player not found!");
	    	}
	    	
	    })
	    .text("Name...")
	    .plugin(DominionsMain.instance)
	    .open(p);
		
	}

	public static void openSearchPlayer(Player p, Dominion dominion, Chunk chunk) {
		
		new AnvilGUI.Builder()
	    .onComplete((player, text) -> {
	    	
			OfflinePlayer pSettings = Utility.getOfflinePlayer(text);
	    	
	    	if(pSettings != null) {
	    		
	    		HashMap<ChunkSettingType, ChunkSettingState> playerChunkSettings = dominion.getChunkSettings(chunk).get(pSettings.getUniqueId());
	    		if(playerChunkSettings != null) {
	    			
	    			// Make members log record
					dominion.addLogRecord(LogType.MEMBERS, player.getDisplayName()+" accessed "+pSettings.getName()
						+"'s Player Setting at ["+chunk.getX()*16+", "+chunk.getZ()*16+"].");
	    			
	    			DominionsMain.instance.getServer().getScheduler().scheduleSyncDelayedTask(DominionsMain.instance, new Runnable() {
		    			public void run() {
							
							PlayerChunkSettings.open(dominion, player, pSettings, playerChunkSettings);
							
		    			}
		    		}, 1L);
		    		return AnvilGUI.Response.close();
	    			
	    		} else {
	    			return AnvilGUI.Response.text("No record found!");
	    		}
	    		
	    	} else {
	    		return AnvilGUI.Response.text("Player not found!");
	    	}
	    	
	    })
	    .text("Name...")
	    .plugin(DominionsMain.instance)
	    .open(p);
		
	}
	
}
