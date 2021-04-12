package com.quzzar.server.worldutils.commands.warps;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.misc.SimpleLocation;
import com.quzzar.server.dominions.misc.Teleporting;
import com.quzzar.server.worldutils.Utility;
import com.quzzar.server.worldutils.warps.WarpManager;

public class WarpTp {

	public static String commandLayout = "/warp tp <warp_name>";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==2){
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				SimpleLocation loc = WarpManager.getWarp(args[1]);
				if(loc != null) {
					
					Teleporting.teleport(player, loc.toLocation());
					
				} else {
					sender.sendMessage(ChatColor.RED+"The warp '"+args[1].toLowerCase()+"' cannot be found!");
				}
				
			} else {
				sender.sendMessage(ChatColor.RED+"Only players can use this command!");
			}
			
		} else {
			failedCommand(sender);
		}
		
	}
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
}
