package quzzar.mod.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import quzzar.mod.LangHandler;
import quzzar.mod.Main;

public class VersionCommand {

	
	public static String commandLayout = "/im version";
	
	public static String commandDescription(ChatColor color){
		return color+LangHandler.applyCommandDescription("Version", "States plugin version.");
	}
	
	private static ChatColor title = ChatColor.DARK_GREEN;
	private static ChatColor version = ChatColor.GREEN;
	private static ChatColor colon = ChatColor.DARK_GRAY;
	
	public static void run(CommandSender sender){
		sender.sendMessage(title+"["+ChatColor.DARK_AQUA+"Innovative Mechanics"+ChatColor.DARK_GREEN+"]"+colon+">"+version+" Version "+Main.getVersion());
	}
	
	
}
