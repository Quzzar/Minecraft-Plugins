package com.quzzar.server.orecreator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Utility {
	
	
	public static void tellConsole(String message){
		Bukkit.getConsoleSender().sendMessage(
				ChatColor.GOLD+"["+ChatColor.GRAY+"OreCreator"+ChatColor.GOLD+"]"+ChatColor.GREEN+" "+message);
	}
	
	public static void tellSender(CommandSender sender, String message) {
		sender.sendMessage(
				ChatColor.GOLD+"["+ChatColor.GRAY+"OreCreator"+ChatColor.GOLD+"]"+ChatColor.GRAY+" "+message);
	}
	
	public static void needsPermission(CommandSender sender) {
		sender.sendMessage(
				ChatColor.RED+"You don't have permission to use this command!");
	}
	
}
