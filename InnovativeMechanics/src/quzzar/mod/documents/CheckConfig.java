package quzzar.mod.documents;

import quzzar.mod.Main;

public class CheckConfig {

	private static ConfigManager configManager = ConfigManager.getInstance();
	
	public static boolean generatingDefaults = false;
	
	public CheckConfig(){
		
		configManager.getConfig().options().header("For a complete breakdown on the config, please visit [web_url]");
		
		
		configChecker("Version",Main.getVersion());
		
		configChecker("Plugin_ClockSpeed",5);
		
		configChecker("Plugin_Autosave_Every_X_Ticks", 36000);
		
		configChecker("Display_Autosave_Message",true);
		configChecker("Autosave_Message","Auto-Saving files...");
		
		configChecker("Enable_The_Dark",false);
		
		configChecker("Max_Large_Machine_Limit",100);
		configChecker("Max_Pipe_Limit",100);
		
		
		
		configChecker("Machine_Upgrades.Speed.Added_Improvement_Multiplier",1);
		configChecker("Machine_Upgrades.Storage.Added_Storage_Slots*9",1);
		
		
		configChecker("Blocks.Simulate_Block_Hardness", true);
		
	}
	
	
	public static Object getValue(String path, Object defaultValue) {
		
		configChecker(path, defaultValue);
		
		configManager.saveConfig();
		
		return configManager.getConfig().get(path);
		
	}
	
	public static void configChecker(String path, Object defaultValue){
		if(configManager.getConfig().get(path)==null){
			configManager.getConfig().set(path, defaultValue);
			configManager.saveConfig();
			generatingDefaults = true;
		}
	}
	
}
