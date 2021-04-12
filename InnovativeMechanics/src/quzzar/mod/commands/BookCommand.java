package quzzar.mod.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import quzzar.mod.ItemsList;
import quzzar.mod.LangHandler;
import quzzar.mod.Utility;

public class BookCommand {

	public static String commandLayout = "/im book [player]";
	
	public static String commandDescription(ChatColor color){
		return color+LangHandler.applyCommandDescription("Book", "Gives the Book of Innovations.");
	}
	
	
	@SuppressWarnings("deprecation")
	public static void run(CommandSender sender, String[] args){
		if(args.length==1){
			
			if(sender instanceof Player){
				
				Player p = (Player) sender;
				
				Utility.addItemToInventory(p.getInventory(), ItemsList.Book_of_Innovations(1), p.getLocation());
				
			}else{
				sender.sendMessage(ChatColor.RED+LangHandler.applyMessage("Player_Only", "Only players can perform this command!"));
			}
			
		}else if (args.length==2){
			
			Player p = Bukkit.getPlayer(args[1]);
			
			if(p != null){
				
				Utility.addItemToInventory(p.getInventory(), ItemsList.Book_of_Innovations(1), p.getLocation());
				
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
