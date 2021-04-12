package com.quzzar.server.worldutils.mobs.mobspawning;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.quzzar.server.worldutils.WorldUtilities;

public class Spawn {

	private EntityType type;
	private List<Material> canSpawnOn;
	private List<Biome> canSpawnIn;
	private double spawnLikeliness;
	private int yMax;
	private int yMin;
	private int maxLightLevel;
	private WorldState requiredState;
	private boolean inWater;
	private String tag;
	private String data;
	private World requiredWorld;
	
	public Spawn(EntityType type) {
		this.type = type;
		this.canSpawnOn = new ArrayList<Material>();
		this.canSpawnIn = new ArrayList<Biome>();
		this.spawnLikeliness = 50;
		this.yMax = 256;
		this.yMin = 0;
		this.requiredState = WorldState.ANY;
		this.maxLightLevel = 15;
		this.inWater = false;
		this.tag = "";
		this.data = "";
		this.requiredWorld = WorldUtilities.getMainWorld();
	}
	
	///
	
	public Spawn spawnOnMaterials(List<Material> canSpawnOn) {
		this.canSpawnOn.addAll(canSpawnOn);
		return this;
	}
	
	public Spawn spawnOnMaterials(Set<Material> canSpawnOn) {
		this.canSpawnOn.addAll(canSpawnOn);
		return this;
	}
	
	public Spawn spawnInBiomes(List<Biome> canSpawnIn) {
		this.canSpawnIn = canSpawnIn;
		return this;
	}
	
	public Spawn spawnLikeliness(double spawnLikeliness) {
		this.spawnLikeliness = spawnLikeliness;
		return this;
	}
	
	public Spawn yMax(int yMax) {
		this.yMax = yMax;
		return this;
	}
	
	public Spawn yMin(int yMin) {
		this.yMin = yMin;
		return this;
	}
	
	public Spawn requiredWorldState(WorldState requiredState) {
		this.requiredState = requiredState;
		return this;
	}
	
	public Spawn maxLightLevel(int maxLightLevel) {
		this.maxLightLevel = maxLightLevel;
		return this;
	}
	
	public Spawn inWater(boolean inWater) {
		this.inWater = inWater;
		return this;
	}
	
	public Spawn tag(String tag) {
		this.tag = tag;
		return this;
	}
	
	public Spawn data(String data) {
		this.data = data;
		return this;
	}
	
	public Spawn requiredWorld(World requiredWorld) {
		this.requiredWorld = requiredWorld;
		return this;
	}
	
	///
	
	public EntityType getEntityType() {
		return type;
	}

	public List<Material> getSpawnOnMaterials() {
		return canSpawnOn;
	}
	
	public List<Biome> getSpawningBiomes() {
		return canSpawnIn;
	}
	
	public double getSpawningLikeliness() {
		return spawnLikeliness;
	}
	
	public int getYMax() {
		return yMax;
	}
	
	public int getYMin() {
		return yMin;
	}
	
	public WorldState getRequiredWorldState() {
		return requiredState;
	}
	
	public int getMaxLightLevel() {
		return maxLightLevel;
	}
	
	public boolean isInWater() {
		return inWater;
	}
	
	public String getTag() {
		return tag;
	}
	
	public String getData() {
		return data;
	}
	
	public World getRequiredWorld() {
		return requiredWorld;
	}
	
}
