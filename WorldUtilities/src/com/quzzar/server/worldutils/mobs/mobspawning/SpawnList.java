package com.quzzar.server.worldutils.mobs.mobspawning;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.quzzar.server.worldutils.WorldUtilities;

public class SpawnList {

	private static ArrayList<Spawn> spawningList = new ArrayList<Spawn>();
	
	static {
		
		// Empty - Generic
		spawningList.add(new Spawn(EntityType.UNKNOWN).spawnLikeliness(100).tag("Empty"));
		spawningList.add(new Spawn(EntityType.UNKNOWN).spawnLikeliness(100).tag("Empty").requiredWorld(WorldUtilities.getAetherWorld()));
		spawningList.add(new Spawn(EntityType.UNKNOWN).spawnLikeliness(100).tag("Empty").requiredWorld(WorldUtilities.getNetherWorld()));
		spawningList.add(new Spawn(EntityType.UNKNOWN).spawnLikeliness(100).tag("Empty").requiredWorld(WorldUtilities.getEndWorld()));
		
		// Normal
		spawningList.add(new Spawn(EntityType.SLIME).spawnLikeliness(60).yMax(40).maxLightLevel(3));
		
		spawningList.add(new Spawn(EntityType.CAVE_SPIDER).spawnLikeliness(60).yMax(60).maxLightLevel(3));
		
		spawningList.add(new Spawn(EntityType.SPIDER).spawnLikeliness(40).yMin(60));
		
		spawningList.add(new Spawn(EntityType.VEX).spawnLikeliness(8).yMin(60));
		
		spawningList.add(new Spawn(EntityType.ZOMBIE).spawnLikeliness(40).maxLightLevel(7));
		spawningList.add(new Spawn(EntityType.SKELETON).spawnLikeliness(40).yMin(40).maxLightLevel(7));
		
		spawningList.add(new Spawn(EntityType.ENDERMAN).spawnLikeliness(30).yMin(100).maxLightLevel(7));
		spawningList.add(new Spawn(EntityType.ENDERMAN).spawnLikeliness(30).yMax(32).maxLightLevel(5));
		
		spawningList.add(new Spawn(EntityType.DROWNED).spawnLikeliness(90).yMin(60).maxLightLevel(7)
				.requiredWorldState(WorldState.RAINING).inWater(true));
		
		spawningList.add(new Spawn(EntityType.HUSK).spawnLikeliness(70)
				.spawnInBiomes(Arrays.asList(Biome.DESERT, Biome.DESERT_HILLS, Biome.BADLANDS,
						Biome.BADLANDS_PLATEAU, Biome.ERODED_BADLANDS)));
		
		spawningList.add(new Spawn(EntityType.ENDERMITE).spawnLikeliness(55).yMin(60).maxLightLevel(7)
				.spawnInBiomes(Arrays.asList(Biome.GIANT_SPRUCE_TAIGA, Biome.GIANT_SPRUCE_TAIGA_HILLS, Biome.GIANT_TREE_TAIGA, 
						Biome.GIANT_TREE_TAIGA_HILLS, Biome.TAIGA, Biome.TAIGA_HILLS, Biome.TAIGA_MOUNTAINS, Biome.SNOWY_TAIGA,
						Biome.SNOWY_TAIGA_HILLS, Biome.SNOWY_TAIGA_MOUNTAINS)));
		
		spawningList.add(new Spawn(EntityType.CREEPER).spawnLikeliness(55).yMin(60).maxLightLevel(7)
				.spawnInBiomes(Arrays.asList(Biome.FOREST, Biome.DARK_FOREST, Biome.DARK_FOREST_HILLS, Biome.JUNGLE,
						Biome.BAMBOO_JUNGLE, Biome.PLAINS, Biome.JUNGLE_EDGE)));
		
		// Special
		spawningList.add(new Spawn(EntityType.SKELETON_HORSE).spawnLikeliness(10).yMin(60)
				.maxLightLevel(7).tag("Trap_Skeleton_Horse"));
		
		spawningList.add(new Spawn(EntityType.SILVERFISH).spawnLikeliness(14).maxLightLevel(12)
				.yMax(50).tag("Silverfish_Swarm"));
		
		spawningList.add(new Spawn(EntityType.WITCH).spawnLikeliness(18).maxLightLevel(7)
				.yMin(60).tag("Zombie_Legion"));
		
		spawningList.add(new Spawn(EntityType.SKELETON).spawnLikeliness(18).maxLightLevel(7)
				.yMin(60).tag("Skeleton_Legion"));
		
		spawningList.add(new Spawn(EntityType.ILLUSIONER).spawnLikeliness(13)
				.requiredWorldState(WorldState.RAINING).yMin(60).tag("Illusioners"));
		
		spawningList.add(new Spawn(EntityType.PILLAGER).spawnLikeliness(14)
				.yMin(60).tag("Pilliger_Legion_2"));
		
		spawningList.add(new Spawn(EntityType.PILLAGER).spawnLikeliness(12)
				.yMin(60).tag("Pilliger_Legion"));
		
		spawningList.add(new Spawn(EntityType.PHANTOM).spawnLikeliness(4).maxLightLevel(7)
				.requiredWorldState(WorldState.RAINING).tag("Unit_Phantom"));
		
		spawningList.add(new Spawn(EntityType.PHANTOM).spawnLikeliness(8).maxLightLevel(7)
				.requiredWorldState(WorldState.RAINING).yMin(60).tag("Phantom_Rider"));
		
		spawningList.add(new Spawn(EntityType.SLIME).spawnLikeliness(4).maxLightLevel(7).tag("Mega_Slime"));
		
		// Aether
		spawningList.add(new Spawn(EntityType.PHANTOM).spawnLikeliness(20).maxLightLevel(7)
				.requiredWorld(WorldUtilities.getAetherWorld()).tag("Aether_Phantom"));
		spawningList.add(new Spawn(EntityType.ENDERMAN).spawnLikeliness(10).maxLightLevel(7)
				.requiredWorld(WorldUtilities.getAetherWorld()).tag("Enderman_Legion"));
		spawningList.add(new Spawn(EntityType.VEX).spawnLikeliness(10).maxLightLevel(7)
				.requiredWorld(WorldUtilities.getAetherWorld()).tag("Vex_Legion"));
		
		// Nether
		spawningList.add(new Spawn(EntityType.BLAZE).spawnLikeliness(5).requiredWorld(WorldUtilities.getNetherWorld()));
				
		// End
		spawningList.add(new Spawn(EntityType.ENDERMAN).spawnLikeliness(70).requiredWorld(WorldUtilities.getEndWorld()));
		spawningList.add(new Spawn(EntityType.ENDERMITE).spawnLikeliness(50).requiredWorld(WorldUtilities.getEndWorld()));
		spawningList.add(new Spawn(EntityType.SHULKER).spawnLikeliness(10).requiredWorld(WorldUtilities.getEndWorld()));
		spawningList.add(new Spawn(EntityType.PHANTOM).spawnLikeliness(40).requiredWorld(WorldUtilities.getEndWorld()).tag("Aether_Phantom"));
		
		
	}
	
	public static ArrayList<Spawn> getSpawnList(){
		return spawningList;
	}
	
}
