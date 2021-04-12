package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.logs.LogType;
import com.quzzar.server.dominions.settings.SettingType;

public class DominionsSetHome {
	
	public static String commandLayout = "/d sethome";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==1){
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
				
				if(dominion!=null) {
					
					// Does the player have the rank to set home? Check settings.
					if(DominionUtils.hasPermission(dominion, player.getUniqueId(), SettingType.SET_HOME)) {
						
						// Is the player in their Dominion territory?
						Dominion currentDominion = DominionUtils.getDominionByChunk(player.getLocation().getChunk());
						if(currentDominion!=null && dominion==currentDominion) {
							
							Location newHomeLoc = player.getLocation();
							
							// Make members log record
							dominion.addLogRecord(LogType.MEMBERS, player.getDisplayName()+" set the Dominion's home to ["
									+newHomeLoc.getBlockX()+", "+newHomeLoc.getBlockY()+", "+newHomeLoc.getBlockZ()+"].");
							
							dominion.setHomeLocation(newHomeLoc);
							sender.sendMessage(ChatColor.GREEN+"Home set! "+ChatColor.ITALIC+"(you can now use /d home)");
							
						} else {
							sender.sendMessage(
									ChatColor.RED+"You can only set home inside your own territory!");
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
