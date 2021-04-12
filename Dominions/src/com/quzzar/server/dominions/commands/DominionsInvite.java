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

public class DominionsInvite {
	
	public static String commandLayout = "/d invite <name>";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==2){
			
			Player invitedPlayer = Bukkit.getPlayer(args[1]);
			
			if(invitedPlayer!=null) {
				
				if(sender instanceof Player) {
					
					Player player = (Player) sender;
					
					Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
					
					if(dominion!=null) {
						
						if(!dominion.getInvites().contains(invitedPlayer.getUniqueId())) {
							
							// Does the player have the rank to invite? Check settings.
							if(DominionUtils.hasPermission(dominion, player.getUniqueId(), SettingType.INVITE)) {
								
								player.sendMessage(ChatColor.GREEN+
										invitedPlayer.getDisplayName()+" has been invited to the Dominion.");
								dominion.addInvite(invitedPlayer.getUniqueId());
								
								// Make members log record
								dominion.addLogRecord(LogType.MEMBERS, player.getDisplayName()+" invited "
										+invitedPlayer.getDisplayName()+" to the Dominion.");
								
								invitedPlayer.sendMessage(
										ChatColor.GREEN+"You've been invited to "+dominion.getName()+"!");
								invitedPlayer.sendMessage(
										ChatColor.GREEN+""+ChatColor.ITALIC+"To join, do: /d join "+dominion.getName());
								
							} else {
								sender.sendMessage(ChatColor.RED+"You don't have the rank to use this command!");
							}
							
						} else {
							sender.sendMessage(
									ChatColor.RED+invitedPlayer.getDisplayName()+" has already been invited to "
									+dominion.getName()+"!");
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
