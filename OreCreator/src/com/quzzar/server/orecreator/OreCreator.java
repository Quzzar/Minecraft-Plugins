package com.quzzar.server.orecreator;

import org.bukkit.plugin.java.JavaPlugin;

public class OreCreator extends JavaPlugin{

	public static OreCreator instance;
	
	@Override
	public void onEnable(){
		
		instance = this;
		
		if (!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
    	}
		
		getServer().getPluginManager().registerEvents(new OreListener(), instance);
		
		OreSpawning.init();
		
		Utility.tellConsole("Loaded and Ready!");
		
	}
	
	@Override
	public void onDisable(){
		
	}
	
}
