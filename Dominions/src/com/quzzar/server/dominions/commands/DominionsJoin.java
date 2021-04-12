package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.logs.LogType;
import com.quzzar.server.dominions.misc.PermissionCategory;
import com.quzzar.server.dominions.settings.SettingType;

public class DominionsJoin {
	
	public static String commandLayout = "/d join <dominion>";
	
	public static void run(CommandSender sender, String[] args){
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(args.length==2){
				
				if(DominionUtils.getDominionByPlayer(player.getUniqueId())==null) {
					
					Dominion dominion = DominionUtils.getDominionByName(args[1]);
					
					if(dominion!=null) {
						
						// Now check if anyone can join or just invites from settings.
						if(dominion.getSettings().get(SettingType.JOIN).equals(PermissionCategory.VeteransAndUp)) {
							// Invite Only
							
							if(dominion.getInvites().contains(player.getUniqueId())) {
								
								joinDominion(dominion, player, sender);
								
							} else {
								sender.sendMessage(ChatColor.RED+"You must be invited to join "+dominion.getName()+"!");
							}
							
						} else if(dominion.getSettings().get(SettingType.JOIN).equals(PermissionCategory.MembersAndUp)) {
							// Open
							
							joinDominion(dominion, player, sender);
							
						} else {
							sender.sendMessage(ChatColor.RED+dominion.getName()+" is a closed Dominion, you can't join!");
						}
						
						
					}else {
						sender.sendMessage(ChatColor.RED+"A Dominion of the name \""+args[1]+"\" does not exist!");
					}
					
				} else {
					sender.sendMessage(ChatColor.RED+"You must leave your current Dominion first!");
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
	
	private static void joinDominion(Dominion dominion, Player player, CommandSender sender) {
		
		boolean added = dominion.addMember(player.getUniqueId());
		
		if(added) {
			
			// Make members log record
			dominion.addLogRecord(LogType.MEMBERS, player.getDisplayName()+" joined the Dominion.");
			
			sender.sendMessage(ChatColor.BLUE+"Joined "+dominion.getName()+"!");
			
			DominionUtils.sendDominionAnnouncement(dominion,
					player.getDisplayName()+" joined the Dominion.");
			
			dominion.removeInvite(player.getUniqueId());
			
		} else {
			sender.sendMessage(ChatColor.RED+dominion.getName()+" doesn't have enough space for you to join!");
		}
		
	}
	
}
