package com.quzzar.server.dominions;

import org.bukkit.plugin.java.JavaPlugin;

import com.quzzar.server.dominions.chat.ChatListener;
import com.quzzar.server.dominions.commands.DominionsCommand;
import com.quzzar.server.dominions.commands.DominionsHelp;
import com.quzzar.server.dominions.commands.tabcompleter.DominionTabCompleter;
import com.quzzar.server.dominions.commands.tabcompleter.TabCompleteManager;
import com.quzzar.server.dominions.logs.LogManager;
import com.quzzar.server.dominions.misc.Teleporting;
import com.quzzar.server.dominions.settings.SettingsManager;
import com.quzzar.server.dominions.upgrades.UpgradesManager;
import com.quzzar.server.dominions.zones.ZoneListener;
import com.quzzar.server.dominions.zones.ZoneManager;
import com.quzzar.server.dominions.zones.commands.neutralzone.NeutralZoneCommand;
import com.quzzar.server.dominions.zones.commands.neutralzone.NeutralZoneHelp;
import com.quzzar.server.dominions.zones.commands.safezone.SafezoneCommand;
import com.quzzar.server.dominions.zones.commands.safezone.SafezoneHelp;

public class DominionsMain extends JavaPlugin{

	public static DominionsMain instance;
	
	private DominionManager dominionManager;
	private ZoneManager zoneManager;
	
	@Override
	public void onEnable(){
		
		instance = this;
		
		if (!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
    	}
		
		getCommand("dominions").setExecutor(new DominionsCommand());
		getCommand("dominion").setExecutor(new DominionsCommand());
		getCommand("d").setExecutor(new DominionsCommand());
		
			getCommand("dominions").setTabCompleter(new DominionTabCompleter());
			getCommand("dominion").setTabCompleter(new DominionTabCompleter());
			getCommand("d").setTabCompleter(new DominionTabCompleter());
		
		getCommand("safezone").setExecutor(new SafezoneCommand());
		getCommand("neutralzone").setExecutor(new NeutralZoneCommand());
		
		getServer().getPluginManager().registerEvents(new MainListener(), instance);
		getServer().getPluginManager().registerEvents(new ZoneListener(), instance);
		getServer().getPluginManager().registerEvents(new TerritoryListener(), instance);
		getServer().getPluginManager().registerEvents(new ChatListener(), instance);
		
		getServer().getPluginManager().registerEvents(new Teleporting(), instance);
		
		dominionManager = new DominionManager();
		zoneManager = new ZoneManager();
		
		
		SettingsManager.initialize();
		UpgradesManager.initialize();
		
		// Sets up help command info...
		DominionsHelp.setHelpMenu();
		SafezoneHelp.setHelpMenu();
		NeutralZoneHelp.setHelpMenu();
		
		MainListener.runClock();
		
		// Initialize Materials Lists
		TerritoryListener.init();
		
		// Initialize Tab Complete
		TabCompleteManager.init();
		
		// Clean Dominion Logs of old records
		LogManager.cleanLogs();
		
		Utility.tellConsole("Loaded and Ready!");
		
	}
	
	@Override
	public void onDisable(){
		
		dominionManager.save();
		zoneManager.save();
		
	}
	
	public static DominionManager getDominionManagerInstance() {
		return instance.dominionManager;
	}
	
	public static ZoneManager getZoneManager() {
		return instance.zoneManager;
	}
	
}
