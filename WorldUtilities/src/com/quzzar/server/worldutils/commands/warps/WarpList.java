package com.quzzar.server.worldutils.commands.warps;

import java.util.Collections;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.quzzar.server.worldutils.Utility;
import com.quzzar.server.worldutils.warps.WarpManager;

public class WarpList {

	public static String commandLayout = "/warp list";
	
	public static void run(CommandSender sender, String[] args){
		
		sender.sendMessage(ChatColor.GOLD+"Warp List:");
		sender.sendMessage(ChatColor.DARK_GREEN+Collections.list(WarpManager.getAllWarps().keys()).toString());
		
	}
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
}
