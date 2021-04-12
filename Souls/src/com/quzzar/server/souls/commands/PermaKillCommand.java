package com.quzzar.server.souls.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;
import com.quzzar.server.souls.death.DeathHandler;

public class PermaKillCommand implements CommandExecutor {

	// > /permakill <player>
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(!sender.hasPermission("souls.commands.permakill")) {
			sender.sendMessage(ChatColor.RED+"You don't have permission to use this command!");
			return true;
		}
		
		if(args.length != 1) {
			sender.sendMessage(ChatColor.RED+"/permakill <player>");
			return true;
		}
		
		Player player = Bukkit.getPlayer(args[0]);
		
		if(player != null) {
			
			player.setHealth(0);
			PlayerCharacter pChar = CharacterManager.getCharacter(player);
			sender.sendMessage(ChatColor.GOLD+pChar.getName()+" has been permanently killed.");
			DeathHandler.permaKill(pChar);
			
		} else {
			sender.sendMessage(ChatColor.RED+"\""+args[0]+"\" could not be found!");
		}
		
		return true;
	}
	
	
}