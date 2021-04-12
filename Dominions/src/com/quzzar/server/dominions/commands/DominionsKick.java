package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.logs.LogType;

public class DominionsKick {
	
	public static String commandLayout = "/d kick <name>";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==2){
			
			OfflinePlayer kickedPlayer = Utility.getOfflinePlayer(args[1]);
			
			if(kickedPlayer!=null) {
				
				if(sender instanceof Player) {
					
					Player player = (Player) sender;
					
					if(!player.equals(kickedPlayer)) {

						Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
						
						if(dominion!=null) {
							
							Dominion otherDominion = DominionUtils.getDominionByPlayer(kickedPlayer.getUniqueId());
							
							if(otherDominion!=null 
									&& otherDominion.equals(dominion)) {
								
								if(!DominionUtils.hasHigherRank(
										dominion, player.getUniqueId(), kickedPlayer.getUniqueId())) {
									
									// Make members log record
									dominion.addLogRecord(LogType.MEMBERS, player.getDisplayName()+" kicked "
											+kickedPlayer.getName()+" from the Dominion.");
									
									dominion.removeMember(kickedPlayer.getUniqueId());
									DominionUtils.sendDominionAnnouncement(dominion,
											kickedPlayer.getName()+" has been kicked from the Dominion.");
									if(kickedPlayer.isOnline()) {
										kickedPlayer.getPlayer().sendMessage(
												ChatColor.BLUE+"You've been kicked from "+dominion.getName()+"!");
									}
									
								} else {
									sender.sendMessage(
											ChatColor.RED+"You can't kick a player with a higher rank than you!");
								}
								
							} else {
								sender.sendMessage(
										ChatColor.RED+kickedPlayer.getName()+" is not in your Dominion!");
							}
							
						} else {
							sender.sendMessage(ChatColor.RED+"You must be in a Dominion to use this command!");
						}
						
					} else {
						sender.sendMessage(ChatColor.RED+"You can not kick yourself!");
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
