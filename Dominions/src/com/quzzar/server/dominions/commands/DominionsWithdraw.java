package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.logs.LogType;
import com.quzzar.server.dominions.settings.SettingType;
import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;

public class DominionsWithdraw {
	
	public static String commandLayout = "/d withdraw <amount>";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==2){
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
				
				if(dominion!=null) {
					
					// Does the player have the rank to withdraw? Check settings.
					if(DominionUtils.hasPermission(dominion, player.getUniqueId(), SettingType.WITHDRAW)) {
						
						// Is the player in their Dominion territory?
						Dominion currentDominion = DominionUtils.getDominionByChunk(player.getLocation().getChunk());
						if(currentDominion!=null && dominion==currentDominion) {
							
							try {
								
								int amount = (int) Double.parseDouble(args[1]);
								
								if(dominion.withdrawMoney(amount, false)) {
									
									// Make finance log record
									dominion.addLogRecord(LogType.FINANCE, player.getDisplayName()+" withdrew "
											+ChatColor.RED+"$"+amount+ChatColor.RESET+" from the vault.");
									
									PlayerCharacter pChar = CharacterManager.getCharacter(player);
									pChar.giveMoney(amount);
									
									sender.sendMessage(
											ChatColor.GREEN+"$"+amount+" has been "
													+ "withdrew from the Dominion vault into your account.");
									
								} else {
									sender.sendMessage(ChatColor.RED+"There is not enough money in the vault to do that!");
								}
								
							} catch (Exception e) {
								sender.sendMessage(ChatColor.RED+"\""+args[1]+"\" is not a number!");
							}
							
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
