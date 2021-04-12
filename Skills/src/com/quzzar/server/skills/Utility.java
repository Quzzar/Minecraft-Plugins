package com.quzzar.server.skills;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Utility {
	
	
	public static void tellConsole(String message){
		Bukkit.getConsoleSender().sendMessage(
				ChatColor.BLUE+"["+ChatColor.GOLD+"Skills"+ChatColor.BLUE+"]"+ChatColor.GREEN+" "+message);
	}
	
	public static void tellSender(CommandSender sender, String message) {
		sender.sendMessage(
				ChatColor.BLUE+"["+ChatColor.GOLD+"Skills"+ChatColor.BLUE+"]"+ChatColor.GRAY+" "+message);
	}
	
	public static void needsPermission(CommandSender sender) {
		sender.sendMessage(
				ChatColor.RED+"You don't have permission to use this command!");
	}
	
}
