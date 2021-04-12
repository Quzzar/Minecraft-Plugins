package com.quzzar.betterrecipes;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BetterRecipesLib extends JavaPlugin {

	private static BetterRecipesLib instance;
	
	@Override
	public void onEnable(){
		
		instance = this;
		
		getServer().getPluginManager().registerEvents(new CraftingListener(), this);
		
		Bukkit.getConsoleSender().sendMessage("§9BetterRecipesLib §8>> §aLoaded!");
		
	}
	
	@Override
	public void onDisable(){
		
	}
	
	public static BetterRecipesLib getInstance() {
		return instance;
	}
	
}
