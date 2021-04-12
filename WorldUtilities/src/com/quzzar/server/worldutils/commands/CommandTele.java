package com.quzzar.server.worldutils.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTele implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(!sender.hasPermission("staff.teleport")) {
			sender.sendMessage(ChatColor.RED+"You don't have permission to use this command!");
			return true;
		}
		
		if(sender instanceof Player) {
			
			if(args.length == 1) {
				
				Player toPlayer = Bukkit.getPlayer(args[0]);
				
				if(toPlayer == null) {
					sender.sendMessage(ChatColor.RED+"Player '"+args[0]+"' could not be found!");
					return true;
				}
				
				Player p = (Player) sender;
				
				p.teleport(toPlayer);
				
				return true;
				
			} else {
				sender.sendMessage(ChatColor.RED+"/tele <player>");
				return true;
			}
			
		} else {
			sender.sendMessage(ChatColor.RED+"Only a player can use this command!");
			return true;
		}
		
	}
	
}
