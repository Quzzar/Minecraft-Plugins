package quzzar.mod.dataProcessing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import quzzar.mod.Main;
import quzzar.mod.MultiHeadUnit;
import quzzar.mod.Utility;
import quzzar.mod.blocks.HeadUnitBlock;
import quzzar.mod.documents.ConfigManager;
import quzzar.mod.documents.WorldData;
import quzzar.mod.pipes.HUPipe;

public class DataProcessingManager {
	
	
	private static List<WorldData> worldsData = new ArrayList<WorldData>();
	
	public static WorldData getWorldData(World world) {
		for(WorldData data : worldsData) {
			if(data.getWorld().equals(world)) {
				return data;
			}
		}
		return null;
	}
	
	
	public static void loadData() {
		
		retrieveWorldsData();
		
		tempSwitchVersions();
		
		
		
		Main.MHUList.clear();
		Main.HUBList.clear();
		Main.PipeList.clear();
		
		for(World world : Bukkit.getWorlds()) {
			
			processWorldData(getWorldData(world));
			
		}
		
	}
	
	
	private static void retrieveWorldsData() {
		
		for(World world : Bukkit.getWorlds()) {
			
			File dataFile = new File(world.getWorldFolder() + File.separator + "InnMech" + File.separator + "BlockData", "data.yml");
			
            if (!dataFile.exists()) {
                try {
                	dataFile.createNewFile();
                }
                catch (IOException e) {
                        Bukkit.getServer().getLogger().severe("Could not create world data file!");
                }
            }
            
            worldsData.add(new WorldData(world, YamlConfiguration.loadConfiguration(dataFile), dataFile));
            
		}
		
	}
	
	
	private static void processWorldData(WorldData data) {
		
		List<String> oldEncryptedMHUList = data.getBlockData().getStringList("MHU_Data");
		Main.MHUList.addAll((List<MultiHeadUnit>) Main.mhuDataProcInstance.decrypt(oldEncryptedMHUList));
		
		List<String> oldEncryptedHUBList = data.getBlockData().getStringList("HUB_Data");
		Main.HUBList.addAll((List<HeadUnitBlock>) Main.hubDataProcInstance.decrypt(oldEncryptedHUBList));
		
		List<String> oldEncryptedPipeList = data.getBlockData().getStringList("Pipe_Data");
		Main.PipeList.addAll((List<HUPipe>) Main.pipeDataProcInstance.decrypt(oldEncryptedPipeList));
		
	}
	
	public static void saveWorldsData() {
		
		for(WorldData data : worldsData) {
			
			data.getBlockData().set("MHU_Data", Main.mhuDataProcInstance.getEncryption(Main.MHUList, data));
			data.getBlockData().set("HUB_Data", Main.hubDataProcInstance.getEncryption(Main.HUBList, data));
			data.getBlockData().set("Pipe_Data", Main.pipeDataProcInstance.getEncryption(Main.PipeList, data));
			
			data.saveBlockData();
		}
		
	}
	
	
	private static void tempSwitchVersions() {
		
		// Different Version?
		if(!ConfigManager.getInstance().getConfig().getString("Version").equalsIgnoreCase(Main.getVersion())) {
			
			File bfile = new File(Main.instance.getDataFolder() + File.separator + "Data", "blockData.yml");
			
			if(bfile.isFile()){
				
				Utility.tellConsole(ChatColor.YELLOW+"Using old data versions, converting!");
				
				FileConfiguration blockData = YamlConfiguration.loadConfiguration(bfile);
				
				List<String> oldEncryptedMHUList = blockData.getStringList("MHU_Data");
				Main.MHUList.addAll((List<MultiHeadUnit>) Main.mhuDataProcInstance.decrypt(oldEncryptedMHUList));
				
				List<String> oldEncryptedHUBList = blockData.getStringList("HUB_Data");
				Main.HUBList.addAll((List<HeadUnitBlock>) Main.hubDataProcInstance.decrypt(oldEncryptedHUBList));
				
				List<String> oldEncryptedPipeList = blockData.getStringList("Pipe_Data");
				Main.PipeList.addAll((List<HUPipe>) Main.pipeDataProcInstance.decrypt(oldEncryptedPipeList));
				
				saveWorldsData();
				
				bfile.delete();
				
				
			}
			
			
			
			
		}
		
	}
	
	
	
	
}