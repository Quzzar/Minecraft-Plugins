package com.quzzar.im.structures;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import com.quzzar.im.InnovativeMechanics;
import com.quzzar.im.machines.Machine;
import com.quzzar.im.machines.MachineType;
import com.quzzar.im.structures.loading.asbuilding.ASManager;
import com.quzzar.im.utils.ChatUtils;

public class StructureManager {
	
	private static Hashtable<String, Set<Structure>> structureMap;
	
	@SuppressWarnings("unchecked")
	public static void load() {
		
		try {
	        FileInputStream fis = new FileInputStream(
	        		InnovativeMechanics.instance.getDataFolder()+"/structures.data");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        structureMap = (Hashtable<String, Set<Structure>>) ois.readObject();
	        ois.close();
		} catch (Exception e) {
			ChatUtils.tellConsole(ChatColor.RED+"Failed to load the structures data file!");
			ChatUtils.tellConsole(ChatColor.RED+"Creating a new one...");
			
			structureMap = new Hashtable<String, Set<Structure>>();
		}
		
	}
	
	public static void save() {
		
		for(Set<Structure> structures : structureMap.values()) {
			for(Structure structure : structures) {
				structure.saveInventories();
			}
		}
		
		try {
			FileOutputStream fos = new FileOutputStream(
					InnovativeMechanics.instance.getDataFolder()+"/structures.data");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(structureMap);
	        oos.close();
		} catch (IOException e) {
			ChatUtils.tellConsole(ChatColor.RED+"Failed to save the structures data file!");
		}
		
	}
	
	public static Structure createStructure(Location loc, BlockFace placingFace, Player player, MachineType machineType) {
		
		Structure structure = new Structure(loc, placingFace, machineType, player);
		
		String packedChunk = packChunk(loc.getChunk());
		Set<Structure> chunkStructures = structureMap.get(packedChunk);
		
		if(chunkStructures == null){
			structureMap.put(packedChunk, new HashSet<Structure>());
			chunkStructures = structureMap.get(packedChunk);
		}
		
		chunkStructures.add(structure);
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(InnovativeMechanics.instance, new Runnable() {
			public void run() {

				structure.load();
				if(machineType != MachineType.PIPE) {
					loc.getBlock().setType(Machine.FILLER_TYPE);
				}

			}
		}, 5L);
		
		return structure;
		
	}
	
	public static void removeStructure(Structure structure) {
		
		structure.unload();
		
		String packedChunk = packChunk(structure.getCenterLocation().toLocation().getChunk());
		Set<Structure> chunkStructures = structureMap.get(packedChunk);
		
		if(chunkStructures != null){
			chunkStructures.remove(structure);
		}
		
		if(chunkStructures.isEmpty()) {
			structureMap.remove(packedChunk);
		}
		
		// Set center block back to air
		Block centerBlock = structure.getCenterLocation().toLocation().getBlock();
		if(centerBlock.getType() == Machine.FILLER_TYPE) {
			centerBlock.setType(Material.AIR);
		}
		
	}
	
	///
	
	public static Structure getStructure(UUID structUUID) {
		for(Set<Structure> structures : structureMap.values()) {
			for(Structure structure : structures) {
				if(structure.getID().equals(structUUID)) {
					return structure;
				}
			}
		}
		return null;
	}
	
	public static Hashtable<String, Set<Structure>> getStructureMap(){
		return structureMap;
	}
	
	///
	
	public static void despawnAll() {
		ASManager.deleteAll();
	}
	
	public static void unloadInChunk(Chunk chunk) {
		Set<Structure> structures = structureMap.get(packChunk(chunk));
		if(structures != null) {
			Bukkit.broadcastMessage(ChatColor.GOLD+"DESPAWNING STRUCTURES");
			for(Structure structure : structures) {
				structure.unload();
			}
		}
	}
	
	public static void loadInChunk(Chunk chunk) {
		Set<Structure> structures = structureMap.get(packChunk(chunk));
		if(structures != null) {
			Bukkit.broadcastMessage(ChatColor.GOLD+"Spawning STRUCTURES");
			for(Structure structure : structures) {
				structure.load();
			}
		}
	}
	
	///
	
	private static String packChunk(Chunk chunk) {
		long packV = (((long)chunk.getX()) << 32) | (chunk.getZ() & 0xffffffffL);
		UUID wUUID = chunk.getWorld().getUID();
		return packV+"~"+wUUID;
	}
	
	
}
