package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.misc.Teleporting;
import com.quzzar.server.dominions.settings.SettingType;

public class DominionsHome {
	
	public static String commandLayout = "/d home";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==1){
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
				
				if(dominion!=null) {
					
					// Does the player have the rank to teleport home? Check settings.
					if(DominionUtils.hasPermission(dominion, player.getUniqueId(), SettingType.HOME)) {
						
						Location homeLoc = dominion.getHomeLocation();
						
						// Is home set?
						if(homeLoc != null) {
							
							// Is the home location in their Dominion territory?
							Dominion checkDominion = DominionUtils.getDominionByChunk(homeLoc.getChunk());
							if(checkDominion!=null && dominion==checkDominion) {
								
								Teleporting.teleport(player, homeLoc);
								
							} else {
								sender.sendMessage(
										ChatColor.RED+"Teleportation failed, Home location is no longer in claimed territory!");
							}
							
						} else {
							sender.sendMessage(ChatColor.RED+"Your Dominion doesn't have a home set yet!");
						}
						
					} else {
						sender.sendMessage(ChatColor.RED+"You don't have the rank to use this command!");
					}
					
				} else {
					sender.sendMessage(ChatColor.RED+"You have no Dominion!");
				}
				
			} else {
				sender.sendMessage(ChatColor.RED+"Only players can use this command!");
			}
			
		} else {
			failedCommand(sender);
		}
		
	}
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
}
