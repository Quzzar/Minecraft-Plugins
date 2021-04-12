package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.logs.LogType;
import com.quzzar.server.dominions.misc.PlayerRank;

public class DominionsLeave {
	
	public static String commandLayout = "/d leave";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==1){
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
				
				if(dominion!=null) {
					
					if(!dominion.getPlayerRank(player.getUniqueId()).equals(PlayerRank.Leader)) {
						
						// Make members log record
						dominion.addLogRecord(LogType.MEMBERS, player.getDisplayName()+" left the Dominion.");
						
						dominion.removeMember(player.getUniqueId());
						DominionUtils.sendDominionAnnouncement(dominion,
								player.getDisplayName()+" has left the Dominion.");
						player.sendMessage(
								ChatColor.GREEN+"You've left "+dominion.getName()+".");
						
					} else {
						sender.sendMessage(ChatColor.RED+"A leader can't leave their own Dominion!");
						sender.sendMessage(ChatColor.RED+""+ChatColor.ITALIC+"Either destroy the Dominion "
								+ "or ask another leader to de-rank you.");
					}
					
				} else {
					sender.sendMessage(ChatColor.RED+"You must be in a Dominion to use this command!");
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
