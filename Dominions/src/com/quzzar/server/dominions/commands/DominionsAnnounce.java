package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.misc.PlayerRank;

public class DominionsAnnounce {
	
	public static String commandLayout = "/d announce <message>";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length>1) {
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
				
				if(dominion!=null) {
					
					if(dominion.getPlayerRank(player.getUniqueId()).equals(PlayerRank.Leader)) {
						
						StringBuilder message = new StringBuilder();
						for(int i=1; i<args.length; i++){
							message.append(args[i]);
							message.append(" ");
						}
						
						String trimmedMessage = 
								message.toString().substring(0, Math.min(message.toString().length(), 40));
						
						DominionUtils.sendDominionAnnouncement(dominion, trimmedMessage);
						
					} else {
						sender.sendMessage(ChatColor.RED+"Only a leader can use this command!");
					}
					
				} else {
					sender.sendMessage(ChatColor.RED+"You must be in a Dominion to use this command!");
				}
				
			} else {
				sender.sendMessage(ChatColor.RED+"Only players can use this command!");
			}
			
		} else {
			sender.sendMessage("§c" + commandLayout);
		}	
	}
}
