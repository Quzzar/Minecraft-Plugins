package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.settings.SettingType;
import com.quzzar.server.dominions.upgrades.UpgradesManager;

public class DominionsUpgrades {
	
	public static String commandLayout = "/d upgrades";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==1){
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
				
				if(dominion!=null) {
					
					// Does the player have the rank to view/buy upgrades? Check settings.
					if(DominionUtils.hasPermission(dominion, player.getUniqueId(), SettingType.UPGRADES)) {
						
						// Is the player in their Dominion territory?
						Dominion currentDominion = DominionUtils.getDominionByChunk(player.getLocation().getChunk());
						if(currentDominion!=null && dominion==currentDominion) {
							
							UpgradesManager.openUpgrades(player, dominion);
							
						} else {
							sender.sendMessage(ChatColor.RED+"You can only use this command while inside your own territory!");
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
