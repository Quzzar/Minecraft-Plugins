package com.quzzar.server.worldutils.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.worldutils.showcoords.ShowCoordsManager;

public class CommandShowCoords implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(!sender.hasPermission("general.coords")) {
			sender.sendMessage(ChatColor.RED+"You don't have permission to use this command!");
			return true;
		}
		
		if(sender instanceof Player) {
			
			ShowCoordsManager.toggleShowing((Player) sender);
			
		} else {
			sender.sendMessage(ChatColor.RED+"Only a player can use this command!");
		}
		
		return true;
		
	}
	
}
