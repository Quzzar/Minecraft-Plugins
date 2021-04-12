package com.quzzar.server.worldutils.commands.warps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CommandWarp implements CommandExecutor, TabCompleter {

	private static List<String> warpSubs = Arrays.asList("tp", "list", "set", "remove");
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(!sender.hasPermission("staff.warp")) {
			sender.sendMessage(ChatColor.RED+"You don't have permission to use this command!");
			return true;
		}
		
		if(args.length>0) {
			
			if(args[0].equalsIgnoreCase("tp")) {
				
				WarpTp.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("list")) {
				
				WarpList.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("set")) {
				
				WarpSet.run(sender, args);
				
			}
			
		}
		
		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(args.length == 1) {
			
			String currentArg = args[args.length-1];
			
			List<String> options = new ArrayList<String>();
			for(String sub : warpSubs) {
				if(sub.toLowerCase().startsWith(currentArg.toLowerCase())) {
					options.add(sub);
				}
			}
			
			return options;
			
		}
		
		return null;
	}
	
}