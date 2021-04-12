package com.quzzar.server.moreitems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

public class Utility {

	public static void tellConsole(String message) {
		org.bukkit.Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "MoreItems"
				+ ChatColor.GOLD + "]" + ChatColor.GREEN + " " + message);
	}

	public static void tellSender(CommandSender sender, String message) {
		sender.sendMessage(
				ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "MoreItems" + ChatColor.GOLD + "] " + message);
	}

	public static ItemStack[] clean(ItemStack[] v) {
		List<ItemStack> list = new ArrayList<ItemStack>(Arrays.asList(v));
		list.removeAll(Collections.singleton(null));
		return (ItemStack[]) list.toArray(new ItemStack[list.size()]);
	}
}
