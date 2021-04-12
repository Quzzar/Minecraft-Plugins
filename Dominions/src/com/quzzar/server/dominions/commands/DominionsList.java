package com.quzzar.server.dominions.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionManager;
import com.quzzar.server.dominions.Utility;

public class DominionsList {
	
	public static String commandLayout = "/d list";
	
	private static ChatColor list = ChatColor.BLUE;
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==1){
			
			sender.sendMessage("§8§m---------§8[ §5§lDominions §8]§m---------");
			
			ArrayList<String> nameList = new ArrayList<String>();
			for(Dominion dominion : DominionManager.getDominions()){
				nameList.add(dominion.getName());
			}
			
			sender.sendMessage(list+nameList.toString());
			
			sender.sendMessage("§8§m------------------------------");
			
		}else{
			failedCommand(sender);
		}
		
	}
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
}
