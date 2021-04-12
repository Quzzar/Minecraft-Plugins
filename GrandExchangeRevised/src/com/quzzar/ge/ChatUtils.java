package com.quzzar.ge;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatUtils {

	public static void tellConsole(String message){
		Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_PURPLE+"["+ChatColor.LIGHT_PURPLE+"Grand Exchange"+ChatColor.DARK_PURPLE+"]"+ChatColor.GREEN+" "+message);
	}
	
	public static void tellSender(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.DARK_PURPLE+"["+ChatColor.LIGHT_PURPLE+"Grand Exchange"+ChatColor.DARK_PURPLE+"] "+message);
	}
	
}
