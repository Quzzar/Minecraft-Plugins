package com.quzzar.server.worldutils.mobs.mobspawning;

import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.metadata.FixedMetadataValue;

import com.destroystokyo.paper.event.entity.PreCreatureSpawnEvent;
import com.quzzar.server.dominions.DominionsMain;
import com.quzzar.server.worldutils.WorldUtilities;

public class SpawningManager {

	private static SplittableRandom splitRand = new SplittableRandom();
	
	private static boolean DEBUG = false;
	private static final double cancelChance = 0.95;
	
	private static List<EntityType> controlledEntities = Arrays.asList(
			EntityType.BLAZE, EntityType.CAVE_SPIDER, EntityType.CREEPER, EntityType.DROWNED, EntityType.ENDERMAN, EntityType.ENDERMITE, 
			EntityType.EVOKER, EntityType.GIANT, EntityType.HUSK, EntityType.PILLAGER, EntityType.ILLUSIONER,
			EntityType.RAVAGER, EntityType.SKELETON, EntityType.STRAY, EntityType.ENDER_DRAGON, EntityType.SPIDER, 
			EntityType.VEX, EntityType.VINDICATOR, EntityType.WITCH, EntityType.WITHER_SKELETON, EntityType.ZOMBIE_HORSE, EntityType.SHULKER, 
			EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.SLIME, EntityType.SILVERFISH);
	
	// MAGMA_CUBE, PIG_ZOMBIE, GHAST
	
	private static List<EntityType> adjacentEntities = Arrays.asList(
			EntityType.COW, EntityType.SHEEP, EntityType.PIG, EntityType.CHICKEN, EntityType.OCELOT, EntityType.WOLF, 
			EntityType.FOX, EntityType.LLAMA, EntityType.HORSE);

	// Manages what spawns trigger
	private static boolean cancelSpawn(EntityType type) {
		if(controlledEntities.contains(type)) {
			return true;
		}
		if(adjacentEntities.contains(type)) {
			return false;
		}
		return true;
	}
	
	// When a mob spawns naturally...
	public static void naturalSpawn(PreCreatureSpawnEvent e) {
		
		if(cancelSpawn(e.getType())) {
			e.setShouldAbortSpawn(true); // < Don't try to spawn again!
			e.setCancelled(true); // < Remove normal spawning
		}
		
		// A chance to just cancel the spawn and not spawn anything
		if(splitRand.nextDouble() < cancelChance) {
			return;
		}
		
		
		boolean isSafeZone = DominionsMain.getZoneManager().isSafeClaimed(e.getSpawnLocation().getChunk());
		if (isSafeZone) {return;}
		
		
		double highestChance = 0.0;
		Spawn bestSpawn = null;
		
		if(DEBUG) {Bukkit.broadcastMessage(ChatColor.BLUE+"DEBUG: Beginning spawn...");}
		for(Spawn spawn : SpawnList.getSpawnList()) {
			if(checksAllConditions(e, spawn)) {
				double chance = splitRand.nextDouble(spawn.getSpawningLikeliness());
				if(DEBUG) {Bukkit.broadcastMessage(
						ChatColor.BLUE+" - "+spawn.getEntityType()+" got "+chance+"/"+spawn.getSpawningLikeliness());}
				if(chance > highestChance) {
					highestChance = chance;
					bestSpawn = spawn;
				}
			}
		}
		
		if(bestSpawn != null) {
			if(DEBUG) {Bukkit.broadcastMessage(ChatColor.BLUE+"Concluded on spawning: "+bestSpawn.getEntityType());}
			boolean didSpawn = SpecialSpawning.determineSpecialSpawn(e.getSpawnLocation(), bestSpawn);
			if(!didSpawn) {
				Entity entity = e.getSpawnLocation().getWorld().spawnEntity(e.getSpawnLocation(), bestSpawn.getEntityType());
				entity.setMetadata(bestSpawn.getData(), new FixedMetadataValue(WorldUtilities.getInstance(), true));
			}
		}
		
	}
	
	private static boolean checksAllConditions(PreCreatureSpawnEvent e, Spawn spawn) {
		
		int spawnY = e.getSpawnLocation().getBlockY();
		byte lightLevel = e.getSpawnLocation().getBlock().getLightLevel();
		Block blockOn = e.getSpawnLocation().getBlock().getRelative(BlockFace.DOWN);
		Block block = e.getSpawnLocation().getBlock();
		Biome biome = e.getSpawnLocation().getBlock().getBiome();
		World world = e.getSpawnLocation().getWorld();
		WorldState worldState = WorldState.getWorldState(world);
		
		// Is spawning in correct world?
		if(!world.equals(spawn.getRequiredWorld())) {
			return false;
		}
		
		// Is spawning in water?
		if(block.getType().equals(Material.WATER)) {
			if(!spawn.isInWater()) {
				return false;
			}
		}
		
		// Is spawning higher than max Y?
		if(spawnY > spawn.getYMax()) {
			return false;
		}
		
		// Is spawning lower than min Y?
		if(spawnY < spawn.getYMin()) {
			return false;
		}
		
		// Is it too bright?
		if(lightLevel > spawn.getMaxLightLevel()) {
			return false;
		}
		
		// Is required world state?
		if(spawn.getRequiredWorldState() != WorldState.ANY && spawn.getRequiredWorldState() != worldState) {
			return false;
		}
		
		// Is spawning on correct block?
		if(!spawn.getSpawnOnMaterials().isEmpty()) {
			if(!spawn.getSpawnOnMaterials().contains(blockOn.getType())) {
				return false;
			}
		}
		
		// Is spawning in correct biome?
		if(!spawn.getSpawningBiomes().isEmpty()) {
			if(!spawn.getSpawningBiomes().contains(biome)) {
				return false;
			}
		}
		
		return true;
		
	}
	
	
	
}
