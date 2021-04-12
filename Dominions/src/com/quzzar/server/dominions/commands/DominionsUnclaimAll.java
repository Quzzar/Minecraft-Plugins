package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionManager;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.logs.LogType;
import com.quzzar.server.dominions.misc.PlayerRank;

public class DominionsUnclaimAll {
	
	public static String commandLayout = "/d unclaimall";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==1){
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
				
				if(dominion!=null) {
					
					if(dominion.getPlayerRank(player.getUniqueId()).equals(PlayerRank.Leader)) {
						
						double refundedMoney = dominion.getChunkCount()*DominionManager.costToClaim*0.75;
						
						dominion.removeAllChunks();
						dominion.depositMoney(refundedMoney);
						
						// Make finance log record
						dominion.addLogRecord(LogType.FINANCE, player.getDisplayName()+" unclaimed all chunks, refunding "
								+ChatColor.GREEN+"$"+refundedMoney+ChatColor.RESET+" to the vault.");
						
						sender.sendMessage(
								ChatColor.GREEN+"Successfully unclaimed all territory.");
						sender.sendMessage(
								ChatColor.GREEN+"$"+refundedMoney+" has been added back to the Dominion vault.");
						
					} else {
						sender.sendMessage(ChatColor.RED+"Only a leader can use this command!");
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
