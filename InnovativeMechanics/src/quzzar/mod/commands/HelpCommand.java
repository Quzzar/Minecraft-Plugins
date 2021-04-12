package quzzar.mod.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import quzzar.mod.LangHandler;

public class HelpCommand {

	public static String commandLayout = "/im help";
	
	public static String commandDescription(ChatColor color){
		return color+LangHandler.applyCommandDescription("Help", "Shows all commands.");
	}
	
	private static ChatColor title = ChatColor.DARK_GREEN;
	private static ChatColor border = ChatColor.DARK_GRAY;
	private static ChatColor cmd = ChatColor.GREEN;
	private static ChatColor desc = ChatColor.BLUE;
	
	public static void run(CommandSender sender){
		sender.sendMessage(border+"=========="+title+"["+ChatColor.DARK_AQUA+"Innovative Mechanics"+ChatColor.DARK_GREEN+"]"+border+"==========");
		sender.sendMessage(cmd+HelpCommand.commandLayout+" :");
		sender.sendMessage("-> "+HelpCommand.commandDescription(desc));
		sender.sendMessage(cmd+GiveCommand.commandLayout+" :");
		sender.sendMessage("-> "+GiveCommand.commandDescription(desc));
		sender.sendMessage(cmd+ListCommand.commandLayout+" :");
		sender.sendMessage("-> "+ListCommand.commandDescription(desc));
		sender.sendMessage(cmd+BookCommand.commandLayout+" :");
		sender.sendMessage("-> "+BookCommand.commandDescription(desc));
		sender.sendMessage(cmd+BookOpenCommand.commandLayout+" :");
		sender.sendMessage("-> "+BookOpenCommand.commandDescription(desc));
		sender.sendMessage(cmd+SaveCommand.commandLayout+" :");
		sender.sendMessage("-> "+SaveCommand.commandDescription(desc));
		sender.sendMessage(cmd+DebugDeleteCommand.commandLayout+" :");
		sender.sendMessage("-> "+DebugDeleteCommand.commandDescription(desc));
	}
	
	
	
}
