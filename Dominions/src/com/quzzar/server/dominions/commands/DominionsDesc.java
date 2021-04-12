package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.logs.LogType;
import com.quzzar.server.dominions.settings.SettingType;

public class DominionsDesc {
	
	public static String commandLayout = "/d desc <description>";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length>1) {
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
				
				if(dominion!=null) {
					
					// Does the player have the rank to set description? Check settings.
					if(DominionUtils.hasPermission(dominion, player.getUniqueId(), SettingType.DESCRIPTION)) {
						
						StringBuilder desc = new StringBuilder();
						for(int i=1; i<args.length; i++){
							desc.append(args[i]);
							desc.append(" ");
						}
						
						String trimmedDesc = desc.toString().substring(0, Math.min(desc.toString().length(), 60));
						
						dominion.setDescription(trimmedDesc);
						sender.sendMessage(ChatColor.GREEN+"Description set!");
						
						// Make diplomacy log record
						dominion.addLogRecord(LogType.DIPLOMACY, player.getDisplayName()+" set the Dominion's description to \""+
								trimmedDesc+"\"");
						
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
			failedCommand(sender);
		}
		
	}
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
}
