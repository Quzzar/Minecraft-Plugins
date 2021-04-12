package com.quzzar.server.worldutils;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class CleanTabListener implements Listener {

	List<String> allowedCommands = Arrays.asList("d", "dominion", "dominions", "balance", "bal", "money", "pay",
			"shout", "s", "whisper", "w", "coords", "fame", "essence", "roll", "spawn");

	//////////////////////////////////////////////////////////
	//////// Block Most Commands From Tab Complete ///////////
	//////////////////////////////////////////////////////////
	@EventHandler
	public void onPlayerTab(PlayerCommandSendEvent e) {
		if (!e.getPlayer().hasPermission("tab.viewallcommands")) {
			e.getCommands().clear();
			e.getCommands().addAll(allowedCommands);
		}
	}

	/////////////////////////////////////////////////////////
	//////// Prevent /plugins and /? without perm ///////////
	/////////////////////////////////////////////////////////
	@EventHandler
	public void onCommandExecuted(PlayerCommandPreprocessEvent e) {
		
		/////////////////////////////////////////////////////////
		//////// Prevent /plugins and /? without perm ///////////
		/////////////////////////////////////////////////////////
		if (!e.getPlayer().hasPermission("helpcommands.override")) {

			if (e.getMessage().equalsIgnoreCase("/plugins")
					|| e.getMessage().equalsIgnoreCase("/?")
					|| e.getMessage().equalsIgnoreCase("/pl")) {
				e.setCancelled(true);
				e.getPlayer().sendMessage("Unknown command.");
				return;
			}
			
		}
		
		///////////////////////////////////////////////////////
		//////// Prevent using non-allowed commands ///////////
		///////////////////////////////////////////////////////
		if (!e.getPlayer().hasPermission("tab.viewallcommands")) {
			
			for(String cmdStr : allowedCommands) {
				if (e.getMessage().toLowerCase().contains(cmdStr)) {
					return;
				}
			}
			
			e.setCancelled(true);
			e.getPlayer().sendMessage("Unknown command.");
			return;
		
		}
		
		////////////////////////////////////////////////////////////
		//////// Prevent /gamemode creative without perm ///////////
		////////////////////////////////////////////////////////////
		if (!e.getPlayer().hasPermission("give.item")) {

			if (e.getMessage().toLowerCase().contains("/gamemode creative")) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.RED+"You do not have permission to change to this gamemode!");
				return;
			}

		}
		
	}

}
