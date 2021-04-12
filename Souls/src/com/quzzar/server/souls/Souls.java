package com.quzzar.server.souls;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.packets.display.PlayerDisplayHandler;
import com.quzzar.server.souls.character.packets.skin.SkinManager;
import com.quzzar.server.souls.character.souls.SoulManager;
import com.quzzar.server.souls.character.souls.SoulsConnection;
import com.quzzar.server.souls.commands.EssenceCommand;
import com.quzzar.server.souls.commands.FameCommand;
import com.quzzar.server.souls.commands.GiveSoulCommand;
import com.quzzar.server.souls.commands.PermaKillCommand;
import com.quzzar.server.souls.commands.SetEssenceCommand;
import com.quzzar.server.souls.commands.SetFameCommand;
import com.quzzar.server.souls.commands.money.BalanceCmd;
import com.quzzar.server.souls.commands.money.PayCmd;
import com.quzzar.server.souls.commands.money.SetMoneyCmd;
import com.quzzar.server.souls.limbo.LimboListener;
import com.quzzar.server.souls.limbo.creation.charname.NameRecordManager;
import com.quzzar.server.souls.limbo.creation.skin.SkinSelectListener;

public class Souls extends JavaPlugin {

	public static Souls instance;
	
	private static World limboWorld;
	
	private SoulsConnection soulsDB;
	
	@Override
	public void onEnable(){
		
		soulsDB = new SoulsConnection();
		
		instance = this;
		
		if (!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
    	}
		
		//DataManager.setup(instance);
		
		getCommand("fame").setExecutor(new FameCommand());
		getCommand("essence").setExecutor(new EssenceCommand());
		
		getCommand("setfame").setExecutor(new SetFameCommand());
		getCommand("setessence").setExecutor(new SetEssenceCommand());
		
		getCommand("givesoul").setExecutor(new GiveSoulCommand());
		getCommand("permakill").setExecutor(new PermaKillCommand());
		
		getCommand("balance").setExecutor(new BalanceCmd());
		getCommand("bal").setExecutor(new BalanceCmd());
		getCommand("money").setExecutor(new BalanceCmd());
		getCommand("pay").setExecutor(new PayCmd());
		getCommand("setmoney").setExecutor(new SetMoneyCmd());
		
		getServer().getPluginManager().registerEvents(new MainListener(), instance);
		getServer().getPluginManager().registerEvents(new LimboListener(), instance);
		getServer().getPluginManager().registerEvents(new SkinSelectListener(), instance);
		
		CharacterManager.load();
		NameRecordManager.load();
		SkinManager.load();
		new PlayerDisplayHandler();
		
		MainListener.runClock();
		
		SoulManager.syncToDatabase();
		
		limboWorld = Bukkit.getWorld("world_limbo");
		if(limboWorld == null) {
			Utility.tellConsole(ChatColor.RED+"Failed to find the world 'world_limbo'!");
		}
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Souls.instance, new Runnable() {
			public void run() {
				Utility.tellConsole("Updating gamerules...");
				
				for(World world : Bukkit.getWorlds()) {
					
					world.setGameRule(GameRule.KEEP_INVENTORY, true);
					world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
					world.setGameRule(GameRule.DISABLE_RAIDS, true);
					//world.setGameRule(GameRule.REDUCED_DEBUG_INFO, true);
					
				}
				
			}
		}, 1);
		
		Utility.tellConsole("Loaded and Ready!");
		
		
	}
	
	@Override
	public void onDisable(){
		
		CharacterManager.save();
		NameRecordManager.save();
		
	}
	
	public static World getLimboWorld() {
		return limboWorld;
	}
	
	public SoulsConnection getSoulsConnection() {
		return soulsDB;
	}
	
}
