package api.anvilgui;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AnvilMain extends JavaPlugin {
	
	public static AnvilMain instance;
	
	@Override
	public void onEnable(){
		
		instance = this;
		
		Bukkit.getConsoleSender().sendMessage("AnvilGUI-API >> §aLoaded!");
		
	}
	
	@Override
	public void onDisable(){
		
	}

}
