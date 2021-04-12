package com.quzzar.server.souls;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Utility {
	
	
	public static void tellConsole(String message){
		Bukkit.getConsoleSender().sendMessage(
				ChatColor.LIGHT_PURPLE+"["+ChatColor.DARK_PURPLE+"Souls"+ChatColor.LIGHT_PURPLE+"]"+ChatColor.GREEN+" "+message);
	}
	
	public static void tellSender(CommandSender sender, String message) {
		sender.sendMessage(
				ChatColor.LIGHT_PURPLE+"["+ChatColor.DARK_PURPLE+"Souls"+ChatColor.LIGHT_PURPLE+"]"+ChatColor.GRAY+" "+message);
	}
	
	public static void needsPermission(CommandSender sender) {
		sender.sendMessage(
				ChatColor.RED+"You don't have permission to use this command!");
	}
	
	public static double roundToTwoDecimals(double d) {
		return Math.round(d * 100.0) / 100.0;
	}
	
}
