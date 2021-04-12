package com.quzzar.ge;

import org.bukkit.plugin.java.JavaPlugin;

import com.quzzar.ge.commands.CommandGE;
import com.quzzar.ge.core.MarketManager;
import com.quzzar.ge.core.buylist.buyinvmapping.BuyAreaContentsMap;
import com.quzzar.ge.listeners.GeneralListener;
import com.quzzar.ge.npcshop.NPCShopListener;
import com.quzzar.ge.npcshop.NPCTradeManager;
import com.quzzar.ge.presets.buying.BuyingInventoryBuilder;

public class GrandExchangeMain extends JavaPlugin {

	public static GrandExchangeMain instance;
	
	@Override
	public void onEnable(){
		
		instance = this;
		
		if (!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
    	}
		
		getServer().getPluginManager().registerEvents(new NPCShopListener(), instance);
		getServer().getPluginManager().registerEvents(new GeneralListener(), instance);
		
		getCommand("ge").setExecutor(new CommandGE());
		getCommand("grandexchange").setExecutor(new CommandGE());
		
		// Initialize Market Manager
		MarketManager.loadTrades();
		
		// Initialize Map of Inventory ID to Contents
		BuyAreaContentsMap.init();
		
		// Initialize Buying Inventories and Managers
		BuyingInventoryBuilder.init();
		
		// Initialize NPC Shop Inventories
		NPCTradeManager.init();
		
		// Start Internal Clock
		GeneralListener.runClock();
		
		ChatUtils.tellConsole("Loaded and Ready!");
	
	}
	
	@Override
	public void onDisable(){
		
		MarketManager.saveTrades();
		
	}
	
}
