package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.logs.LogType;
import com.quzzar.server.dominions.misc.DiplomacyState;
import com.quzzar.server.dominions.settings.SettingType;

public class DominionsSetDiplomacy {
	
	public static String commandLayout = "/d setdiplomacy <dominion/player> <ally/neutral/enemy>";
	
	public static void run(CommandSender sender, String[] args){
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(args.length==3){
				
				Dominion playerDominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
				
				if(playerDominion!=null) {
					
					// Does the player have the rank to set diplomacy? Check settings.
					if(!DominionUtils.hasPermission(playerDominion, player.getUniqueId(), SettingType.SET_DIPLOMACY)) {
						sender.sendMessage(ChatColor.RED+"You don't have the rank to use this command!");
						return;
					}
					
					
					Dominion dominion = DominionUtils.getDominionByName(args[1]);
					OfflinePlayer playerDiplo = Utility.getOfflinePlayer(args[1]);
					
					if(dominion != null) {
						
						if(dominion!=playerDominion) {
							
							setDominionDiplomacy(player, playerDominion, dominion, args[2]);
							
						}else {
							sender.sendMessage(ChatColor.RED+"You cannot set diplomacy with your own Dominion!");
						}
						
					} else if (playerDiplo != null) {
						
						Dominion playerDiploDom = DominionUtils.getDominionByPlayer(playerDiplo.getUniqueId());
						
						if(playerDiploDom != null && playerDiploDom == playerDominion) {
							sender.sendMessage(ChatColor.RED+"You can't set diplomacy with a member of your own dominion!");
						} else {
							
							setPlayerDiplomacy(player, playerDominion, playerDiplo, args[2]);
							
						}
						
					} else {
						sender.sendMessage(ChatColor.RED+"Could not find either a Dominion or player by the name \""+args[1]+"\"!");
					}
					
				} else {
					sender.sendMessage(ChatColor.RED+"You must be in a Dominion to use this command!");
				}
				
			} else {
				failedCommand(sender);
			}
			
		} else {
			sender.sendMessage(ChatColor.RED+"Only players can use this command!");
		}
		
	}
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
	private static void setPlayerDiplomacy(Player player, Dominion playerDominion, OfflinePlayer playerDiplo, String state) {
		
		if(state.equalsIgnoreCase("ally")) {
			
			playerDominion.setPlayerDiplomacy(playerDiplo.getUniqueId(), DiplomacyState.Ally);
			DominionUtils.sendDominionAnnouncement(playerDominion,
					ChatColor.GREEN+"\""+playerDiplo.getName()+"\" has been made an Ally.");
			
			// Make diplomacy log record
			playerDominion.addLogRecord(LogType.DIPLOMACY, player.getDisplayName()+" made "+playerDiplo.getName()
				+" an ally to our Dominion.");
			
			/*
			Player onlinePlayerDiplo = Bukkit.getPlayer(playerDiplo.getUniqueId());
			if(onlinePlayerDiplo != null) {
				onlinePlayerDiplo.sendMessage(playerDominion.getName()+" has made just you their Ally!");
			}*/
			
		} else if(state.equalsIgnoreCase("neutral")) {
			
			playerDominion.setPlayerDiplomacy(playerDiplo.getUniqueId(), DiplomacyState.Neutral);
			DominionUtils.sendDominionAnnouncement(playerDominion,
					"\""+playerDiplo.getName()+"\" has been set to Neutral.");
			
			// Make diplomacy log record
			playerDominion.addLogRecord(LogType.DIPLOMACY, player.getDisplayName()+" made "+playerDiplo.getName()
				+" neutral to our Dominion.");
			
			/*
			Player onlinePlayerDiplo = Bukkit.getPlayer(playerDiplo.getUniqueId());
			if(onlinePlayerDiplo != null) {
				onlinePlayerDiplo.sendMessage(playerDominion.getName()+" has set just you to Neutral!");
			}*/
			
		} else if(state.equalsIgnoreCase("enemy")) {
			
			playerDominion.setPlayerDiplomacy(playerDiplo.getUniqueId(), DiplomacyState.Enemy);
			DominionUtils.sendDominionAnnouncement(playerDominion,
					ChatColor.RED+"\""+playerDiplo.getName()+"\" has been made an Enemy.");
			
			// Make diplomacy log record
			playerDominion.addLogRecord(LogType.DIPLOMACY, player.getDisplayName()+" made "+playerDiplo.getName()
				+" an enemy to our Dominion.");
			
			/*
			Player onlinePlayerDiplo = Bukkit.getPlayer(playerDiplo.getUniqueId());
			if(onlinePlayerDiplo != null) {
				onlinePlayerDiplo.sendMessage(playerDominion.getName()+" has made just you their Enemy!");
			}*/
			
		} else {
			player.sendMessage(ChatColor.RED+"Unknown diplomacy status \""+state+"\"!");
		}
		
	}
	
	private static void setDominionDiplomacy(Player player, Dominion playerDominion, Dominion dominion, String state) {
		
		if(state.equalsIgnoreCase("ally")) {
			
			playerDominion.setDominionDiplomacy(dominion, DiplomacyState.Ally);
			DominionUtils.sendDominionAnnouncement(playerDominion,
					ChatColor.GREEN+"\""+dominion.getName()+"\" has been made an Ally.");
			
			// Make diplomacy log record
			playerDominion.addLogRecord(LogType.DIPLOMACY, player.getDisplayName()+" made "+dominion.getName()
				+" an ally to our Dominion.");
			
			if(dominion.getDominionDiplomacy(playerDominion).equals(DiplomacyState.Ally)) {
				DominionUtils.sendDominionAnnouncement(dominion,
						ChatColor.GREEN+
						"Your ally \""+playerDominion.getName()+"\" has made you their Ally!");
				
				// Make diplomacy log record
				dominion.addLogRecord(LogType.DIPLOMACY, "Our ally, "
						+playerDominion.getName()+", made us their ally as well.");
				
			}
			
		} else if(state.equalsIgnoreCase("neutral")) {
			
			playerDominion.setDominionDiplomacy(dominion, DiplomacyState.Neutral);
			DominionUtils.sendDominionAnnouncement(playerDominion,
					"\""+dominion.getName()+"\" has been set to Neutral.");
			
			// Make diplomacy log record
			playerDominion.addLogRecord(LogType.DIPLOMACY, player.getDisplayName()+" made "+dominion.getName()
				+" neutral to our Dominion.");
			
			if(dominion.getDominionDiplomacy(playerDominion).equals(DiplomacyState.Ally)) {
				DominionUtils.sendDominionAnnouncement(dominion,
						"Your ally \""+playerDominion.getName()+"\" has set you to Neutral!");
				
				// Make diplomacy log record
				dominion.addLogRecord(LogType.DIPLOMACY, "Our ally, "
						+playerDominion.getName()+", set us to neutral.");
				
			}
			
		} else if(state.equalsIgnoreCase("enemy")) {
			
			playerDominion.setDominionDiplomacy(dominion, DiplomacyState.Enemy);
			DominionUtils.sendDominionAnnouncement(playerDominion,
					ChatColor.RED+"\""+dominion.getName()+"\" has been made an Enemy.");
			
			// Make diplomacy log record
			playerDominion.addLogRecord(LogType.DIPLOMACY, player.getDisplayName()+" made "+dominion.getName()
				+" an enemy to our Dominion.");
			
			if(dominion.getDominionDiplomacy(playerDominion).equals(DiplomacyState.Ally)) {
				DominionUtils.sendDominionAnnouncement(dominion,
						ChatColor.RED+
						"Your ally \""+playerDominion.getName()+"\" has made you their Enemy!");
				
				// Make diplomacy log record
				dominion.addLogRecord(LogType.DIPLOMACY, "Our ally, "
						+playerDominion.getName()+", made us their enemy.");
				
			}
			
		} else {
			player.sendMessage(ChatColor.RED+"Unknown diplomacy status \""+state+"\"!");
		}
		
	}
	
}
