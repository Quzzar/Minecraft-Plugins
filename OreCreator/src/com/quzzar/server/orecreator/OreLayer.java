package com.quzzar.server.orecreator;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;


public class OreLayer {
	
	private int minY;
	private int maxY;
	
	private int maxVeinSize;
	
	private double chance;
	/* 
	 	Chance determines how likely that ore will spawn.
	 	- From 0.0 - 1.0, percent chance
	 */
	
	private Material blockMaterial;
	
	private Biome restrictedBiome; // If null, there is no restriction
	private World restrictedWorld; // If null, there is no restriction
	
	public OreLayer(Material blockMaterial, int minY, int maxY, double chance, int maxVeinSize, 
			Biome restrictedBiome, World restrictedWorld) {
		this.blockMaterial = blockMaterial;
		this.minY = minY;
		this.maxY = maxY;
		this.chance = chance;
		this.maxVeinSize = maxVeinSize;
		this.restrictedBiome = restrictedBiome;
		this.restrictedWorld = restrictedWorld;
	}

	public Material getBlockMaterial() {
		return blockMaterial;
	}
	
	public int getMinY() {
		return minY;
	}

	public int getMaxY() {
		return maxY;
	}
	
	public double getChance() {
		return chance;
	}
	
	public Biome getRestrictedBiome() {
		return restrictedBiome;
	}
	
	public World getRestrictedWorld() {
		return restrictedWorld;
	}

	public int getMaxVeinSize() {
		return maxVeinSize;
	}

}
