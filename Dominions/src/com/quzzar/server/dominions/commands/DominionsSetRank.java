package com.quzzar.server.dominions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.logs.LogType;
import com.quzzar.server.dominions.misc.PlayerRank;

public class DominionsSetRank {
	
	public static String commandLayout = "/d setrank <name> <leader/general/veteran/member>";
	public static String shortCommandLayout = "/d setrank <name> <leader/general/veteran>";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==3){
			
			Player rankedPlayer = Bukkit.getPlayer(args[1]);
			
			if(rankedPlayer!=null) {
				
				if(sender instanceof Player) {
					
					Player player = (Player) sender;
					
					if(!player.equals(rankedPlayer)) {

						Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
						
						if(dominion!=null) {
							
							Dominion otherDominion = DominionUtils.getDominionByPlayer(rankedPlayer.getUniqueId());
							
							if(otherDominion!=null 
									&& otherDominion == dominion) {
								
								if(!DominionUtils.hasHigherRank(
										dominion, player.getUniqueId(), rankedPlayer.getUniqueId())) {
									
									int rankPower = dominion.getPlayerRank(player.getUniqueId()).getPower();
									
									if(args[2].equalsIgnoreCase("leader")) {
										
										if(rankPower>=PlayerRank.Leader.getPower()) {
											
											// Make members log record
											dominion.addLogRecord(LogType.MEMBERS, player.getDisplayName()+" set "
													+rankedPlayer.getDisplayName()+"'s rank to Leader.");
											
											dominion.setPlayerRank(rankedPlayer.getUniqueId(), PlayerRank.Leader);
											DominionUtils.sendDominionAnnouncement(dominion,
													rankedPlayer.getDisplayName()+"'s rank has been set to Leader.");
											
										} else {
											sender.sendMessage(
													ChatColor.RED+"You cannot set to a rank higher than your own!");
										}
										
									} else if(args[2].equalsIgnoreCase("general")) {
										
										if(rankPower>=PlayerRank.General.getPower()) {
											
											// Make members log record
											dominion.addLogRecord(LogType.MEMBERS, player.getDisplayName()+" set "
													+rankedPlayer.getDisplayName()+"'s rank to General.");
											
											dominion.setPlayerRank(rankedPlayer.getUniqueId(), PlayerRank.General);
											DominionUtils.sendDominionAnnouncement(dominion,
													rankedPlayer.getDisplayName()+"'s rank has been set to General.");
											
										} else {
											sender.sendMessage(
													ChatColor.RED+"You cannot set to a rank higher than your own!");
										}
										
									} else if(args[2].equalsIgnoreCase("veteran")) {
										
										if(rankPower>=PlayerRank.Veteran.getPower()) {
											
											// Make members log record
											dominion.addLogRecord(LogType.MEMBERS, player.getDisplayName()+" set "
													+rankedPlayer.getDisplayName()+"'s rank to Veteran.");
											
											dominion.setPlayerRank(rankedPlayer.getUniqueId(), PlayerRank.Veteran);
											DominionUtils.sendDominionAnnouncement(dominion,
													rankedPlayer.getDisplayName()+"'s rank has been set to Veteran.");
											
										} else {
											sender.sendMessage(
													ChatColor.RED+"You cannot set to a rank higher than your own!");
										}
										
									} else if(args[2].equalsIgnoreCase("member")) {
										
										if(rankPower>=PlayerRank.Member.getPower()) {
											
											// Make members log record
											dominion.addLogRecord(LogType.MEMBERS, player.getDisplayName()+" set "
													+rankedPlayer.getDisplayName()+"'s rank to Member.");
											
											dominion.setPlayerRank(rankedPlayer.getUniqueId(), PlayerRank.Member);
											DominionUtils.sendDominionAnnouncement(dominion,
													rankedPlayer.getDisplayName()+"'s rank has been set to Member.");
											
										} else {
											sender.sendMessage(
													ChatColor.RED+"You cannot set to a rank higher than your own!");
										}
										
									} else {
										sender.sendMessage(ChatColor.RED+"Unknown rank \""+args[2]+"\"!");
									}
									
								} else {
									sender.sendMessage(
											ChatColor.RED+"You can't set the rank of a player"
													+ " with a higher rank than you!");
								}
								
							} else {
								sender.sendMessage(
										ChatColor.RED+rankedPlayer.getDisplayName()+" is not in your Dominion!");
							}
							
						} else {
							sender.sendMessage(ChatColor.RED+"You must be in a Dominion to use this command!");
						}
						
					} else {
						sender.sendMessage(ChatColor.RED+"You cannot set your own rank!");
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
