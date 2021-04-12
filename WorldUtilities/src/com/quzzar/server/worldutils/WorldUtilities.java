package com.quzzar.server.worldutils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.quzzar.server.worldutils.commands.CommandRoll;
import com.quzzar.server.worldutils.commands.CommandShowCoords;
import com.quzzar.server.worldutils.commands.CommandSpawn;
import com.quzzar.server.worldutils.commands.CommandTele;
import com.quzzar.server.worldutils.commands.warps.CommandWarp;
import com.quzzar.server.worldutils.enchantments.warding.WardingRecipeManager;
import com.quzzar.server.worldutils.showcoords.CoordsDisplay;
import com.quzzar.server.worldutils.warps.WarpManager;

public class WorldUtilities extends JavaPlugin{
	
	private static WorldUtilities instance;
	
	private static World mainWorld;
	private static World netherWorld;
	private static World aetherWorld;
	private static World endWorld;
	
	@Override
	public void onEnable(){
		
		instance = this;
		
		if (!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
    	}
		
		getCommand("coords").setExecutor(new CommandShowCoords());
		getCommand("roll").setExecutor(new CommandRoll());
		getCommand("spawn").setExecutor(new CommandSpawn());
		
		getCommand("tele").setExecutor(new CommandTele());
		
		getCommand("warp").setExecutor(new CommandWarp());
		getCommand("warp").setTabCompleter(new CommandWarp());
		
		getServer().getPluginManager().registerEvents(new MainListener(), this);
		getServer().getPluginManager().registerEvents(new WorldListener(), this);
		getServer().getPluginManager().registerEvents(new NoExpListener(), this);
		getServer().getPluginManager().registerEvents(new CleanTabListener(), this);
		
		// Initialize Enchantment Recipes
		WardingRecipeManager.init();
		
		// Load Warps
		WarpManager.load();
		
		// Display Clock for /coords
		CoordsDisplay.displayClock();
		
		mainWorld = Bukkit.getWorld("world");
		if(mainWorld == null) {
			Utility.tellConsole(ChatColor.RED+"Failed to find the world 'world'!");
		}
		netherWorld = Bukkit.getWorld("world_nether");
		if(netherWorld == null) {
			Utility.tellConsole(ChatColor.RED+"Failed to find the world 'world_nether'!");
		}
		aetherWorld = Bukkit.getWorld("world_aether");
		if(aetherWorld == null) {
			Utility.tellConsole(ChatColor.RED+"Failed to find the world 'world_aether'!");
		}
		endWorld = Bukkit.getWorld("world_the_end");
		if(endWorld == null) {
			Utility.tellConsole(ChatColor.RED+"Failed to find the world 'world_the_end'!");
		}
		
		Utility.tellConsole("Loaded and Ready!");
		
	}
	
	@Override
	public void onDisable(){
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			
			// To prevent item steal
			p.closeInventory();
			
		}
		
		WarpManager.save();
		
	}
	
	public static WorldUtilities getInstance() {
		return instance;
	}
	
	public static World getMainWorld() {
		return mainWorld;
	}
	
	public static World getNetherWorld() {
		return netherWorld;
	}
	
	public static World getAetherWorld() {
		return aetherWorld;
	}
	
	public static World getEndWorld() {
		return endWorld;
	}
	
	
}
