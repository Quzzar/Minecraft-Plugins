package com.quzzar.server.souls.commands.money;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;

public class PayCmd implements CommandExecutor {
	
	public static String commandLayout = "/pay <player> <amount>";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(!sender.hasPermission("general.pay")) {
			sender.sendMessage(ChatColor.RED+"You don't have permission to use this command!");
			return true;
		}
		
		if(args.length==2){
			
			if(sender instanceof Player) {
				
				Player givingPlayer = (Player) sender;
				Player paidPlayer = Bukkit.getPlayer(args[0]);
				
				if(paidPlayer!=null) {
					
					PlayerCharacter givingChar = CharacterManager.getCharacter(givingPlayer);
					PlayerCharacter paidChar = CharacterManager.getCharacter(paidPlayer);
					
					try {
						
						int amount = (int) Double.parseDouble(args[1]);
						
						if(givingChar.takeMoney(amount)) {
							
							paidChar.giveMoney(amount);
							
							givingPlayer.sendMessage(
									ChatColor.GREEN+paidPlayer.getDisplayName()+" has been paid "+
											ChatColor.BLUE+"$"+amount+ChatColor.GREEN+".");
							paidPlayer.sendMessage(
									ChatColor.GREEN+"You have been given "+ChatColor.BLUE+"$"+amount+ChatColor.GREEN+" by "+
											givingPlayer.getDisplayName()+".");
							
						} else {
							sender.sendMessage(ChatColor.RED+"You do not have enough money to do that!");
						}
						
					} catch (Exception e) {
						sender.sendMessage(ChatColor.RED+"\""+args[1]+"\" is not a number!");
					}
					
				} else {
					sender.sendMessage(ChatColor.RED+"Player by the name \""+args[0]+"\" could not be found!");
				}
				
			} else {
				sender.sendMessage(ChatColor.RED+"Only a player can send this command! Use /setmoney instead!");
			}
			
		}else{
			failedCommand(sender);
		}
		
		return true;
		
	}
	
	public static void failedCommand(CommandSender sender){
		sender.sendMessage(ChatColor.RED+commandLayout);
	}
	
}