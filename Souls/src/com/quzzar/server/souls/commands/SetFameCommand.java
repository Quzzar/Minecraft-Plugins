package com.quzzar.server.souls.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;

public class SetFameCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(!(sender instanceof Player)) {
			
			Player checkedPlayer = Bukkit.getPlayer(args[0]);
			
			if(checkedPlayer != null) {
				
				int amount = 1;
				try {
					amount = Integer.parseInt(args[1]);
				} catch (Exception e) {
					sender.sendMessage(ChatColor.RED+"\""+args[1]+"\" is not a number!");
					return true;
				}
				
				PlayerCharacter pChar = CharacterManager.getCharacter(checkedPlayer);
				pChar.getData().getFame().setInfamy(amount);
				
				sender.sendMessage(ChatColor.GOLD+args[0]+"'s Fame has been set to "+amount);
				
			} else {
				sender.sendMessage(ChatColor.RED+"\""+args[0]+"\" could not be found!");
			}
			
		} else {
			sender.sendMessage(ChatColor.RED+"Sorry, this command can only be sent via console.");
		}
		
		return true;
	}
	
	
}