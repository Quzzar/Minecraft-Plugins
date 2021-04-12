package com.quzzar.server.souls.commands.money;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.quzzar.server.dominions.Utility;
import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;
import com.quzzar.server.souls.character.data.CharData;

public class SetMoneyCmd implements CommandExecutor {
	
	public static String commandLayout = "/setmoney <player> <amount>";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(!sender.hasPermission("money.set")) {
			sender.sendMessage(ChatColor.RED+"You don't have permission to use this command!");
			return true;
		}
		
		if(args.length==2){
			
			OfflinePlayer setPlayer = Utility.getOfflinePlayer(args[0]);
			
			if(setPlayer!=null) {
				
				try {
					
					int amount = (int) Double.parseDouble(args[1]);
					
					if(setPlayer.isOnline()) {
						
						PlayerCharacter pChar = CharacterManager.getCharacter(setPlayer.getPlayer());
						pChar.setMoney(amount);
						sender.sendMessage(ChatColor.GREEN+"You have set "+pChar.getName()+"'s funds to "
										+ChatColor.BLUE+"$"+amount+ChatColor.GREEN+".");
						
					} else {
						
						CharData cData = CharacterManager.getCharacterData(setPlayer.getUniqueId());
						cData.setMoney(amount);
						sender.sendMessage(ChatColor.GREEN+"You have set "+cData.getName()+"'s funds to "
								+ChatColor.BLUE+"$"+amount+ChatColor.GREEN+".");
						
					}
					
					
				} catch (Exception e) {
					sender.sendMessage(ChatColor.RED+"\""+args[1]+"\" is not a number!");
				}
				
			} else {
				sender.sendMessage(ChatColor.RED+"Player by the name \""+args[0]+"\" could not be found!");
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