package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.logs.LogManager;
import com.quzzar.server.dominions.logs.LogType;
import com.quzzar.server.dominions.settings.SettingType;

public class DominionsLog {
	
	public static String commandLayout = "/d log <finance/members/diplomacy>";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==2){
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
				
				if(dominion!=null) {
					
					// Does the player have the rank to get the log? Check settings.
					if(DominionUtils.hasPermission(dominion, player.getUniqueId(), SettingType.LOG)) {
						
						LogType type;
						
						try {
							type = LogType.valueOf(args[1].toUpperCase());
						} catch (Exception e) {
							sender.sendMessage(ChatColor.RED+"\""+args[1]+"\" is not a log type!");
							return;
						}
						
						if(dominion.withdrawMoney(LogManager.costToGetLog, false)) {
							
							sender.sendMessage(
									ChatColor.GREEN+"Log acquired. Creating it cost $"+LogManager.costToGetLog
										+" which has been withdrew from the Dominion vault.");
							
							LogManager.createLogItem(player, dominion, type);
							
							// Make finance log record
							dominion.addLogRecord(LogType.FINANCE, 
									player.getDisplayName()+" acquired a "+type+" log, costing "
											+ChatColor.RED+"$"+LogManager.costToGetLog+ChatColor.RESET+".");
							
							
						} else {
							sender.sendMessage(ChatColor.RED+"There is not enough money in the vault to do this command!");
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
