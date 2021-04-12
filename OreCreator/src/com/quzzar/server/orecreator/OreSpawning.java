package com.quzzar.server.orecreator;

import java.util.ArrayList;
import java.util.SplittableRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

public class OreSpawning {

	private static SplittableRandom splitRand = new SplittableRandom();
	
	public static ArrayList<OreLayer> oreLayers = new ArrayList<OreLayer>();
	
	public static void init() {
		
		World mainWorld = Bukkit.getWorld("world");
		if(mainWorld == null) {
			Utility.tellConsole(ChatColor.RED+"Failed to find the world 'world'!");
		}
		World netherWorld = Bukkit.getWorld("world_nether");
		if(netherWorld == null) {
			Utility.tellConsole(ChatColor.RED+"Failed to find the world 'world_nether'!");
		}
		World aetherWorld = Bukkit.getWorld("world_aether");
		if(aetherWorld == null) {
			Utility.tellConsole(ChatColor.RED+"Failed to find the world 'world_aether'!");
		}
		
		// Mainworld Ores
		oreLayers.add(new OreLayer(Material.IRON_ORE, 30, 180, 0.03, 6, null, mainWorld));
		oreLayers.add(new OreLayer(Material.GOLD_ORE, 10, 180, 0.01, 3, null, mainWorld));
		oreLayers.add(new OreLayer(Material.EMERALD_ORE, 50, 180, 0.005, 16, null, mainWorld));
		oreLayers.add(new OreLayer(Material.LAPIS_ORE, 1, 40, 0.02, 3, null, mainWorld));
		oreLayers.add(new OreLayer(Material.DIAMOND_ORE, 1, 21, 0.01, 4, null, mainWorld));
		oreLayers.add(new OreLayer(Material.REDSTONE_ORE, 1, 26, 0.02, 6, null, mainWorld));
		oreLayers.add(new OreLayer(Material.COAL_ORE, 30, 180, 0.035, 10, null, mainWorld));
		
			// Biomes Specific
			oreLayers.add(new OreLayer(Material.EMERALD_ORE, 1, 80, 0.02, 8, Biome.DESERT, mainWorld));
			oreLayers.add(new OreLayer(Material.GOLD_ORE, 20, 180, 0.02, 6, Biome.BADLANDS, mainWorld));
			oreLayers.add(new OreLayer(Material.REDSTONE_ORE, 20, 180, 0.02, 6, Biome.BADLANDS, mainWorld));
			oreLayers.add(new OreLayer(Material.COAL_ORE, 40, 70, 0.015, 4, Biome.BEACH, mainWorld));
			oreLayers.add(new OreLayer(Material.COAL_ORE, 40, 70, 0.01, 8, Biome.JUNGLE, mainWorld));
			oreLayers.add(new OreLayer(Material.DIAMOND_ORE, 1, 10, 0.005, 2, Biome.JUNGLE, mainWorld));
			oreLayers.add(new OreLayer(Material.DIAMOND_ORE, 1, 10, 0.005, 2, Biome.SAVANNA, mainWorld));
			oreLayers.add(new OreLayer(Material.LAPIS_ORE, 30, 40, 0.02, 4, Biome.BIRCH_FOREST, mainWorld));
			oreLayers.add(new OreLayer(Material.IRON_ORE, 80, 140, 0.01, 10, Biome.PLAINS, mainWorld));
		
		
		// Aether Ores
		oreLayers.add(new OreLayer(Material.IRON_ORE, 60, 256, 0.035, 3, null, aetherWorld));
		oreLayers.add(new OreLayer(Material.GOLD_ORE, 60, 256, 0.005, 5, null, aetherWorld));
		oreLayers.add(new OreLayer(Material.EMERALD_ORE, 60, 256, 0.04, 2, null, aetherWorld));
		oreLayers.add(new OreLayer(Material.LAPIS_ORE, 60, 256, 0.03, 5, null, aetherWorld));
		oreLayers.add(new OreLayer(Material.DIAMOND_ORE, 60, 100, 0.01, 6, null, aetherWorld));
		
	}
	
	
	public static OreLayer getOreVein(Block block) {
		for(OreLayer oreLayer : oreLayers) {
			if(doesSpawnOre(oreLayer, block)) {
				return oreLayer;
			}
		}
		return null;
	}
	
	private static boolean doesSpawnOre(OreLayer oreLayer, Block block) {
	    
		if(oreLayer.getRestrictedBiome() != null) {
			if(oreLayer.getRestrictedBiome() != block.getBiome()) {
				return false;
			}
		}
		
		if(oreLayer.getRestrictedWorld() != null) {
			if(oreLayer.getRestrictedWorld() != block.getWorld()) {
				return false;
			}
		}
		
		if(oreLayer.getMaxY() < block.getY() || block.getY() < oreLayer.getMinY()) {
			return false;
		}
		
		if(splitRand.nextDouble() <= oreLayer.getChance()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
}
