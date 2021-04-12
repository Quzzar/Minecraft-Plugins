package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionManager;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.misc.PlayerRank;
import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;

public class DominionsDestroy {
	
	public static String commandLayout = "/d destroy <name>";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==2){
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				if(player.hasPermission("dominions.commands.destroyother")) {
					
					Dominion dominion = DominionUtils.getDominionByName(args[1]);
					
					if(dominion!=null) {
						
						DominionManager.deleteDominion(dominion);
						sender.sendMessage(ChatColor.GREEN+"Successfully destroyed "+args[1]+"!");
						
					} else {
						sender.sendMessage(ChatColor.RED+"Could not find a Dominion of the name \""+args[1]+"\"!");
					}
					
				} else {
					
					Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
					
					if(dominion!=null) {
						
						if(dominion.getPlayerRank(player.getUniqueId()).equals(PlayerRank.Leader)) {
							
							if(args[1].equals(dominion.getName())) {
								
								double refundedMoney = dominion.getMoney() +
										dominion.getChunkCount()*DominionManager.costToClaim*0.75;
								
								DominionManager.deleteDominion(dominion);
								sender.sendMessage(ChatColor.GREEN+"Successfully destroyed "+args[1]+"!");
								
								PlayerCharacter pChar = CharacterManager.getCharacter(player);
								pChar.giveMoney(refundedMoney);
								
								sender.sendMessage(
										ChatColor.GREEN+"$"+refundedMoney+" has been deposited into your account.");
								
							} else {
								sender.sendMessage(ChatColor.RED+"You must spell the Dominion"
										+ " name exactly correct to destroy it!");
							}
							
						} else {
							sender.sendMessage(ChatColor.RED+"Only a Dominion leader can use this command!");
						}
						
					} else {
						sender.sendMessage(ChatColor.RED+"You have no Dominion!");
					}
					
				}
				
			} else {
				
				Dominion dominion = DominionUtils.getDominionByName(args[1]);
				
				if(dominion!=null) {
					
					DominionManager.deleteDominion(dominion);
					sender.sendMessage(ChatColor.GREEN+"Successfully destroyed "+args[1]+"!");
					
				} else {
					sender.sendMessage(ChatColor.RED+"Could not find a Dominion of the name \""+args[1]+"\"!");
				}
				
			}
			
		} else {
			failedCommand(sender);
		}
		
	}
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
}
