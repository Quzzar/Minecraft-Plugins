package com.quzzar.server.skills;

import org.bukkit.plugin.java.JavaPlugin;

import com.quzzar.server.skills.commands.SkillsCommand;

public class Skills extends JavaPlugin{

	public static Skills instance;
	
	public SkillManager skillManager;
	
	@Override
	public void onEnable(){
		
		instance = this;
		
		if (!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
    	}
		
		getCommand("skills").setExecutor(new SkillsCommand());
		
		getServer().getPluginManager().registerEvents(new MainListener(), instance);
		
		
		skillManager = new SkillManager();
		
		
		MainListener.runClock();
		
		Utility.tellConsole("Loaded and Ready!");
		
	}
	
	@Override
	public void onDisable(){
		
		skillManager.save();
		
	}
	
	public static SkillManager getSkillManager() {
		return instance.skillManager;
	}
	
}
