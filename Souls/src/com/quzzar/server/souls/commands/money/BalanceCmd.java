package com.quzzar.server.souls.commands.money;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;

public class BalanceCmd implements CommandExecutor {

	public static String commandLayout = "/balance";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(!sender.hasPermission("general.balance")) {
			sender.sendMessage(ChatColor.RED+"You don't have permission to use this command!");
			return true;
		}
		
		if(args.length==0){
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				PlayerCharacter pChar = CharacterManager.getCharacter(player);
				
				sender.sendMessage(ChatColor.BLUE+""+ChatColor.BOLD+"Balance: "+ChatColor.GREEN+"$"+pChar.getMoney());
				
			} else {
				sender.sendMessage(ChatColor.RED+"Only players can use this command!");
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
