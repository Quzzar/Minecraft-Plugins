package com.quzzar.im.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatUtils {

	public static void tellConsole(String message){
		Bukkit.getConsoleSender().sendMessage(
				ChatColor.LIGHT_PURPLE+"["+ChatColor.DARK_AQUA+"IM"+ChatColor.LIGHT_PURPLE+"]"+ChatColor.GREEN+" "+message);
	}
	
	public static void tellSender(CommandSender sender, String message) {
		sender.sendMessage(
				ChatColor.LIGHT_PURPLE+"["+ChatColor.DARK_AQUA+"IM"+ChatColor.LIGHT_PURPLE+"]"+ChatColor.GRAY+" "+message);
	}
	
	public static void needsPermission(CommandSender sender) {
		sender.sendMessage(
				ChatColor.RED+"You don't have permission to use this command!");
	}
	
}
