package com.quzzar.server.worldutils.commands.warps;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import com.quzzar.server.worldutils.Utility;
import com.quzzar.server.worldutils.warps.WarpManager;

public class WarpRemove {

	public static String commandLayout = "/warp remove <warp_name>";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==2){
			
			WarpManager.removeWarp(args[1]);
			sender.sendMessage(ChatColor.GREEN+"Warp '"+args[1].toLowerCase()+"' removed if it existed.");
			
		} else {
			failedCommand(sender);
		}
		
	}
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
}
