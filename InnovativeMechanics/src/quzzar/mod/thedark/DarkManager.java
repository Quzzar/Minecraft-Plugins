package quzzar.mod.thedark;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.plugin.Plugin;

public class DarkManager {

	private static String darkWorldName = "world_the_dark";
	
	public static double sxz = 230;
	public static double sy = 80;
	public static int cutoff = 10;
	public static int caveMin = 2;
	public static int caveMax = 170;
	public static boolean debugMode = true;
	
	
	public static void initialize(Plugin plugin){
		
		if(!Bukkit.getServer().getWorlds().contains(Bukkit.getWorld(darkWorldName))){
			createTheDark();
		}
		
		DarkListener.runTask();
		
	}
	
	public static World getWorld(){
		return Bukkit.getWorld(darkWorldName);
	}
	
	
	
	private static void createTheDark(){
		Bukkit.getServer().createWorld(WorldCreator.name(darkWorldName).type(WorldType.CUSTOMIZED).generatorSettings(
				"{\"useCaves\":true,\"useStrongholds\":true,\"useVillages\":false,\"useMineShafts\":true,\"useTemples\":false,\"useRavines\":true,\"useMonuments\":false,\"useMansions\":false,\"useLavaOceans\":true,\"useWaterLakes\":false,\"useLavaLakes\":true,\"useDungeons\":true,\"fixedBiome\":21,\"biomeSize\":8,\"seaLevel\":255,\"riverSize\":1,\"waterLakeChance\":1,\"lavaLakeChance\":51,\"dungeonChance\":8,\"dirtSize\":6,\"dirtCount\":28,\"dirtMinHeight\":218,\"dirtMaxHeight\":255,\"gravelSize\":8,\"gravelCount\":8,\"gravelMinHeight\":153,\"gravelMaxHeight\":255,\"graniteSize\":20,\"graniteCount\":6,\"graniteMinHeight\":144,\"graniteMaxHeight\":207,\"dioriteSize\":14,\"dioriteCount\":5,\"dioriteMinHeight\":122,\"dioriteMaxHeight\":196,\"andesiteSize\":13,\"andesiteCount\":7,\"andesiteMinHeight\":132,\"andesiteMaxHeight\":202,\"coalSize\":10,\"coalCount\":35,\"coalMinHeight\":0,\"coalMaxHeight\":255,\"ironSize\":7,\"ironCount\":22,\"ironMinHeight\":0,\"ironMaxHeight\":255,\"goldSize\":7,\"goldCount\":7,\"goldMinHeight\":0,\"goldMaxHeight\":92,\"redstoneSize\":5,\"redstoneCount\":14,\"redstoneMinHeight\":0,\"redstoneMaxHeight\":140,\"diamondSize\":8,\"diamondCount\":2,\"diamondMinHeight\":0,\"diamondMaxHeight\":77,\"lapisSize\":9,\"lapisCount\":3,\"lapisMinHeight\":0,\"lapisMaxHeight\":107,\"coordinateScale\":6000,\"heightScale\":6000,\"mainNoiseScaleX\":5000,\"mainNoiseScaleY\":5000,\"mainNoiseScaleZ\":5000,\"depthNoiseScaleX\":2000,\"depthNoiseScaleZ\":2000,\"depthNoiseScaleExponent\":20,\"biomeDepthWeight\":20,\"biomeDepthOffset\":20,\"biomeScaleWeight\":20,\"biomeScaleOffset\":20,\"lowerLimitScale\":5000,\"upperLimitScale\":5000,\"baseSize\":7,\"stretchY\":17.43,\"lapisCenterHeight\":53.5,\"lapisSpread\":53.5}"
				).generateStructures(true));
	}
	
	
	
}
