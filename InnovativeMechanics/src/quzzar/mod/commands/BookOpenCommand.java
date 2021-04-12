package quzzar.mod.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import quzzar.mod.LangHandler;
import quzzar.mod.Utility;
import quzzar.mod.pluginhelp.PluginHelpManager;

public class BookOpenCommand {

	public static String commandLayout = "/im openbook [player]";
	
	public static String commandDescription(ChatColor color){
		return color+LangHandler.applyCommandDescription("Open_Book", "Automatically opens the Book of Innovations.");
	}
	
	
	@SuppressWarnings("deprecation")
	public static void run(CommandSender sender, String[] args){
		if(args.length==1){
			
			if(sender instanceof Player){
				
				Player p = (Player) sender;
				
				p.closeInventory();
				p.openInventory(PluginHelpManager.getHomeInventory());
				
			}else{
				sender.sendMessage(ChatColor.RED+LangHandler.applyMessage("Player_Only", "Only players can perform this command!"));
			}
			
		}else if (args.length==2){
			
			Player p = Bukkit.getPlayer(args[1]);
			
			if(p != null){
				
				p.closeInventory();
				p.openInventory(PluginHelpManager.getHomeInventory());
				
			}else{
				sender.sendMessage(ChatColor.RED+LangHandler.applyMessage("Player_Not_Found", "Could not find player")+" '"+args[1]+"'!");
			}
			
		}else{
			
			failedCommand(sender);
			
		}
	}
	
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
	
	
}
