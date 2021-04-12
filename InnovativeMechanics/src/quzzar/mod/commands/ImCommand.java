package quzzar.mod.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import quzzar.mod.LangHandler;

public class ImCommand implements CommandExecutor{

	public ImCommand instance = this;
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		
		if(args.length>0){
			if(args[0].equalsIgnoreCase("give")||args[0].equalsIgnoreCase("giveitem")||args[0].equalsIgnoreCase("g")){
				if(sender.hasPermission("innomech.commands.give")){
					GiveCommand.run(sender, args);
				}else{
					noPermission(sender);
				}
			}else if(args[0].equalsIgnoreCase("help")||args[0].equalsIgnoreCase("h")||args[0].equalsIgnoreCase("?")){
				if(sender.hasPermission("innomech.commands.help")){
					HelpCommand.run(sender);
				}else{
					noPermission(sender);
				}
			}else if(args[0].equalsIgnoreCase("itemlist")||args[0].equalsIgnoreCase("list")||args[0].equalsIgnoreCase("l")){
				if(sender.hasPermission("innomech.commands.list")){
					ListCommand.run(sender, args);
				}else{
					noPermission(sender);
				}
			}else if(args[0].equalsIgnoreCase("book")||args[0].equalsIgnoreCase("b")){
				if(sender.hasPermission("innomech.commands.book")){
					BookCommand.run(sender, args);
				}else{
					noPermission(sender);
				}
			}else if(args[0].equalsIgnoreCase("openbook")||args[0].equalsIgnoreCase("ob")){
				if(sender.hasPermission("innomech.commands.openbook")){
					BookOpenCommand.run(sender, args);
				}else{
					noPermission(sender);
				}
			}else if(args[0].equalsIgnoreCase("version")||args[0].equalsIgnoreCase("ver")||args[0].equalsIgnoreCase("v")){
				VersionCommand.run(sender);
			}else if(args[0].equalsIgnoreCase("save")||args[0].equalsIgnoreCase("sav")||args[0].equalsIgnoreCase("s")){
				if(sender.hasPermission("innomech.commands.save")){
					SaveCommand.run(sender, args);
				}else{
					noPermission(sender);
				}
			}else if(args[0].equalsIgnoreCase("forcedelete")){
				
				if(sender.hasPermission("innomech.commands.forcedelete")){
					DebugDeleteCommand.run(sender, args);
				}else{
					noPermission(sender);
				}
			}else{
				if(sender.hasPermission("innomech.commands.help")){
					HelpCommand.run(sender);
				}else{
					VersionCommand.run(sender);
				}
			}
		}else{
			if(sender.hasPermission("innomech.commands.help")){
				HelpCommand.run(sender);
			}else{
				VersionCommand.run(sender);
			}
		}
		
		return true;
	}
	
	
	private static void noPermission(CommandSender sender) {
		
		sender.sendMessage(ChatColor.RED+LangHandler.applyMessage("No_Permission", "You don't have permission to use this command!"));
	}
	
}
