package com.quzzar.server.worldutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

public class Utility {
	
	public static void tellConsole(String message){
		Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"["+ChatColor.AQUA+"World Utils"+ChatColor.BLUE+"]"+ChatColor.GREEN+" "+message);
	}
	
	public static void tellSender(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.BLUE+"["+ChatColor.AQUA+"World Utils"+ChatColor.BLUE+"] "+message);
	}
	
	public static ItemStack[] clean(final ItemStack[] v) {
	    List<ItemStack> list = new ArrayList<ItemStack>(Arrays.asList(v));
	    list.removeAll(Collections.singleton(null));
	    return list.toArray(new ItemStack[list.size()]);
	}
	
}
