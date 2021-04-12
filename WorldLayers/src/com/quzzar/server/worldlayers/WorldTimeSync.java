package com.quzzar.server.worldlayers;

import org.bukkit.Bukkit;

public class WorldTimeSync {

	public static void syncAetherToOverworldTime() {
		
		if(MainListener.aether == null) {
			return;
		}
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(WorldLayers.instance, new Runnable() {
			
			public void run() {
				
				MainListener.aether.setFullTime(MainListener.overworld.getFullTime());
				
			}
		
		}, 200, 200); // Every 10 seconds
		
	}
	
}
