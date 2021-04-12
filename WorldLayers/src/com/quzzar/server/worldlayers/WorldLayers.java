package com.quzzar.server.worldlayers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldLayers extends JavaPlugin {

	public static WorldLayers instance;
	
	public static final int OVERWORLD_TP_BOTTOM = 1;
	public static final int OVERWORLD_TP_TOP = 253;
	public static final int NETHER_TP = 252;
	public static final int AETHER_TP_BOTTOM = 1;
	public static final int AETHER_TP_TOP = 253;
	public static final int END_TP = 1;
	
	@Override
	public void onEnable(){
		
		instance = this;
		getServer().getPluginManager().registerEvents(new MainListener(), instance);
		
		// Init Worlds...
		MainListener.init();
		WorldTimeSync.syncAetherToOverworldTime();
		
		tellConsole("Loaded and Ready!");
		
	}
	
	
	public static void tellConsole(String message){
		Bukkit.getConsoleSender().sendMessage(
				ChatColor.DARK_GRAY+"["+ChatColor.GOLD+"WorldLayers"+ChatColor.DARK_GRAY+"]"+ChatColor.GREEN+" "+message);
	}
	
	public static void tellSender(CommandSender sender, String message) {
		sender.sendMessage(
				ChatColor.DARK_GRAY+"["+ChatColor.GOLD+"WorldLayers"+ChatColor.DARK_GRAY+"]"+ChatColor.GRAY+" "+message);
	}
	
	public static void needsPermission(CommandSender sender) {
		sender.sendMessage(
				ChatColor.RED+"You don't have permission to use this command!");
	}
	
}
