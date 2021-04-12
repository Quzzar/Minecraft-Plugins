package com.quzzar.server.dominions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.logs.LogType;
import com.quzzar.server.dominions.settings.SettingType;

public class DominionsUninvite {
	
	public static String commandLayout = "/d uninvite <name>";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==2){
			
			Player invitedPlayer = Bukkit.getPlayer(args[1]);
			
			if(invitedPlayer!=null) {
				
				if(sender instanceof Player) {
					
					Player player = (Player) sender;
					
					Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
					
					if(dominion!=null) {
						
						// Does the player have the rank to uninvite? Check settings.
						if(DominionUtils.hasPermission(dominion, player.getUniqueId(), SettingType.UNINVITE)) {
							
							// Make members log record
							dominion.addLogRecord(LogType.MEMBERS, player.getDisplayName()+" revoked "
									+invitedPlayer.getDisplayName()+"'s invitation to the Dominion.");
							
							DominionUtils.sendDominionAnnouncement(dominion,
									invitedPlayer.getDisplayName()+"'s invitation has been revoked.");
							dominion.removeInvite(invitedPlayer.getUniqueId());
							
						} else {
							sender.sendMessage(ChatColor.RED+"You don't have the rank to use this command!");
						}
						
					} else {
						sender.sendMessage(ChatColor.RED+"You must be in a Dominion to use this command!");
					}
					
				} else {
					sender.sendMessage(ChatColor.RED+"Only players can use this command!");
				}
				
			} else {
				sender.sendMessage(ChatColor.RED+"Player by the name \""+args[1]+"\" could not be found!");
			}
			
		} else {
			failedCommand(sender);
		}
		
	}
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
}
