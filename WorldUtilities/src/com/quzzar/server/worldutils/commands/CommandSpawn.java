package com.quzzar.server.worldutils.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.misc.Teleporting;
import com.quzzar.server.worldutils.WorldUtilities;

public class CommandSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(!sender.hasPermission("general.spawn")) {
			sender.sendMessage(ChatColor.RED+"You don't have permission to use this command!");
			return true;
		}
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			Teleporting.teleport(p, WorldUtilities.getMainWorld().getSpawnLocation());
			return true;
			
		} else {
			sender.sendMessage(ChatColor.RED+"Only a player can use this command!");
			return true;
		}
		
	}
	
}
