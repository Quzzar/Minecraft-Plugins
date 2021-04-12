package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionManager;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.logs.LogType;
import com.quzzar.server.dominions.settings.SettingType;

public class DominionsUnclaim {
	
	public static String commandLayout = "/d unclaim | unclaimall";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==1){
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
				
				if(dominion!=null) {
					
					// Does the player have the rank to unclaim? Check settings.
					if(DominionUtils.hasPermission(dominion, player.getUniqueId(), SettingType.UNCLAIM)) {
						
						Chunk chunk = player.getLocation().getChunk();
						Dominion chunkDom = DominionUtils.getDominionByChunk(chunk);
						
						if(chunkDom == null) {
							sender.sendMessage(ChatColor.RED+"Chunk is already not claimed!");
							return;
						}
						
						if(!dominion.equals(chunkDom)) {
							sender.sendMessage(ChatColor.RED+"You can only unclaim a chunk that your dominion owns!");
							return;
						}
						
						double refundedMoney = DominionManager.costToClaim*0.75;
						
						dominion.removeChunk(chunk);
						dominion.depositMoney(refundedMoney);
						
						// Make finance log record
						dominion.addLogRecord(LogType.FINANCE, player.getDisplayName()+" unclaimed a chunk, refunding "
								+ChatColor.GREEN+"$"+refundedMoney+ChatColor.RESET+" to the vault.");
						
						sender.sendMessage(
								ChatColor.GREEN+"Chunk unclaimed at ["+chunk.getX()+", "+chunk.getZ()+"]");
						sender.sendMessage(
								ChatColor.GREEN+"$"+refundedMoney+" has been added back to the Dominion vault.");
						
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
