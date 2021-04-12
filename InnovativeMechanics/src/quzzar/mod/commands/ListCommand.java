package quzzar.mod.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import quzzar.mod.LangHandler;
import quzzar.mod.Utility;
import quzzar.mod.pluginhelp.items.ItemHelp;
import quzzar.mod.pluginhelp.items.ItemHelpInstance;

public class ListCommand {

	public static String commandLayout = "/im list";
	
	public static String commandDescription(ChatColor color){
		return color+LangHandler.applyCommandDescription("List", "Lists of all Innovative Mechanics items.");
	}
	
	
	private static ChatColor title = ChatColor.DARK_GREEN;
	private static ChatColor border = ChatColor.DARK_GRAY;
	private static ChatColor list = ChatColor.BLUE;
	
	
	public static void run(CommandSender sender, String[] args){
		if(args.length==1){
			
			sender.sendMessage(border+"=========="+title+"["+ChatColor.DARK_AQUA+"I.M. Item List"+ChatColor.DARK_GREEN+"]"+border+"==========");
			
			ArrayList<String> nameList = new ArrayList<String>();
			for(ItemHelpInstance instance : ItemHelp.getItemsList()){
				
				nameList.add(Utility.getSafeItemName(instance.getItemStack()));
			}
			
			sender.sendMessage(list+nameList.toString());
			
		}else{
			failedCommand(sender);
		}
	}
	
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
	
	
	
}
