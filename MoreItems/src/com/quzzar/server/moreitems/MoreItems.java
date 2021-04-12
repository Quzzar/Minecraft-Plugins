package com.quzzar.server.moreitems;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.quzzar.server.moreitems.commands.GiveItemCommand;
import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.special.SpecialListener;
import com.quzzar.server.moreitems.items.special.SpecialManager;
import com.quzzar.server.moreitems.items.special.ancientrecipe.RecipeItemListener;
import com.quzzar.server.moreitems.items.special.baton.SearchHandler;
import com.quzzar.server.moreitems.items.special.events.EventListener;
import com.quzzar.server.moreitems.recipes.MiscRecipes;
import com.quzzar.server.moreitems.spells.SpellListener;
import com.quzzar.server.moreitems.wands.WandListener;
import com.quzzar.server.moreitems.wands.WandManager;

public class MoreItems extends JavaPlugin {
	private static MoreItems instance;

	public void onEnable() {

		instance = this;

		getCommand("giveitem").setExecutor(new GiveItemCommand());
		getCommand("giveitem").setTabCompleter(new GiveItemCommand());
		
		getServer().getPluginManager().registerEvents(new MainListener(), this);
		getServer().getPluginManager().registerEvents(new SpecialListener(), this);
		getServer().getPluginManager().registerEvents(new SpecialManager(), this);
		
		getServer().getPluginManager().registerEvents(new RecipeItemListener(), this);

		getServer().getPluginManager().registerEvents(new WandListener(), this);
		getServer().getPluginManager().registerEvents(new SpellListener(), this);
		
		getServer().getPluginManager().registerEvents(new EventListener(), this);

		ItemManager.initialize(this);


		WandManager.initialize();

		MiscRecipes.loadRecipes(this);


		SpecialManager.initialize();
		SearchHandler.runTask();


		Utility.tellConsole("Loaded and Ready!");
	}



	public void onDisable() {
		for (Player p : org.bukkit.Bukkit.getServer().getOnlinePlayers()) {
			p.closeInventory();
		}
	}


	public static MoreItems getInstance()
	{
		return instance;
	}


}
