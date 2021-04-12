package com.quzzar.rpchat;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.quzzar.rpchat.announcements.AnnouncingClock;
import com.quzzar.rpchat.commands.CommandShout;
import com.quzzar.rpchat.commands.CommandStaff;
import com.quzzar.rpchat.commands.CommandTell;
import com.quzzar.rpchat.commands.CommandWhisper;
import com.quzzar.rpchat.localchat.LocalChatManager;
import com.quzzar.rpchat.radio.RadioCreator;
import com.quzzar.rpchat.radio.RadioListener;
import com.quzzar.rpchat.radio.RadioManager;
import com.quzzar.rpchat.radio.misc.CodeSet;
import com.quzzar.rpchat.rankprefix.RankPrefixManager;

public class RPChat extends JavaPlugin{
	
	private static RPChat instance;
	
	private static RadioManager radioManager;
	private static LocalChatManager localChatManager;
	private static RankPrefixManager rankPrefixManager;
	
	public static boolean debugMode;
	
	@Override
	public void onEnable(){
		
		printPluginBorder();
		
		printPluginLogo();
		
		instance = this;
		
    	if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
            File dataFolder = new File(getDataFolder()+"/Data");
            dataFolder.mkdir();
    	}
		
		saveDefaultConfig();
		
		getCommand("staff").setExecutor(new CommandStaff());
		getCommand("tell").setExecutor(new CommandTell());
		
		getCommand("whisper").setExecutor(new CommandWhisper());
		getCommand("shout").setExecutor(new CommandShout());
		getCommand("w").setExecutor(new CommandWhisper());
		getCommand("s").setExecutor(new CommandShout());
		
		
		getServer().getPluginManager().registerEvents(new ChatListener(), this);
		getServer().getPluginManager().registerEvents(new RadioListener(), this);
		
		radioManager = new RadioManager();
		localChatManager = new LocalChatManager();
		rankPrefixManager = new RankPrefixManager();
		
		AnnouncingClock.runClock();
		
		// Initializing...
		CodeSet.initialize();
		RadioCreator.initialize(this);
		
		// Set if in debugMode...
		debugMode = getMainConfig().getBoolean("DebugMode");
		
		Utility.tellConsoleBullet("§aLoaded and Ready to Chat!");
		
		printPluginBorder();
		Bukkit.getConsoleSender().sendMessage("");
		
	}
	
	@Override
	public void onDisable(){
		
		radioManager.save();
		
	}
	
	public void printPluginLogo() {
		Bukkit.getConsoleSender().sendMessage("§d  _____ ______   §b_____  _             _      ");
		Bukkit.getConsoleSender().sendMessage("§d  | ___ \\| ___ \\§b /  __ \\| |           | |    ");
		Bukkit.getConsoleSender().sendMessage("§d  | |_/ /| |_/ / §b| /  \\/| |__    __ _ | |_   ");
		Bukkit.getConsoleSender().sendMessage("§d  |    / |  __/  §b| |    | '_ \\  / _` || __|  ");
		Bukkit.getConsoleSender().sendMessage("§d  | |\\ \\ | |    §b | \\__/\\| | | || (_| || |_   ");
		Bukkit.getConsoleSender().sendMessage("§d  \\_| \\_|\\_|   §b   \\____/|_| |_| \\__,_| \\__|  ");
		Bukkit.getConsoleSender().sendMessage("§d                  §b                           ");
	}
	
	public void printPluginBorder() {
		Bukkit.getConsoleSender().sendMessage("§5_____________________________________________");
	}
	
	public static RPChat getInstance() {
		return instance;
	}
	
	public static FileConfiguration getMainConfig() {
		return instance.getConfig();
	}
	
	public static RadioManager getRadioManager() {
		return radioManager;
	}
	
	public static LocalChatManager getLocalChatManager() {
		return localChatManager;
	}
	
	public static RankPrefixManager getRankPrefixManager() {
		return rankPrefixManager;
	}
	
}
