package com.quzzar.rpchat.announcements;

import org.bukkit.Bukkit;

import com.quzzar.rpchat.RPChat;

public class AnnouncingClock {

	public static void runClock() {
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(RPChat.getInstance(), new Runnable() {
			
			public void run() {
				
				Announcements.sendGlobalAnnouncement();
				
			}
		
		}, 18000, 18000); // Every 15 min
		
	}
	
}
