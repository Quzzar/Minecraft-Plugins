package quzzar.mod.documents;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

public class WorldData {
	
	private World world;
	private YamlConfiguration blockData;
	
	private File blockDataFile;
	
	public WorldData(World world, YamlConfiguration blockData, File blockDataFile) {
		
		this.world = world;
		this.blockData = blockData;
		
		this.blockDataFile = blockDataFile;
		
	}
	
	public World getWorld() {
		return world;
	}
	
	public YamlConfiguration getBlockData() {
		return blockData;
	}
	
    public void saveBlockData() {
        try {
        	blockData.save(blockDataFile);
        }
        catch (IOException e) {
                Bukkit.getServer().getLogger().severe("Could not save "+world.getName()+"'s Block Data File!");
        }
    }
    
    public void reloadBlockData() {
    	blockData = YamlConfiguration.loadConfiguration(blockDataFile);
    }
	
}
