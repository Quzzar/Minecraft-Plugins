package quzzar.mod.Ores;

import org.bukkit.Material;

import quzzar.mod.Textures.TextureDatabase;

public enum OreList {
	
	
	COAL (Material.COAL_ORE, TextureDatabase.COAL_CHUNKS),
	IRON (Material.IRON_ORE, TextureDatabase.IRON_CHUNKS),
	GOLD (Material.GOLD_ORE, TextureDatabase.GOLD_CHUNKS),
	LAPIS (Material.LAPIS_ORE, TextureDatabase.LAPIS_CHUNKS),
	REDSTONE (Material.REDSTONE_ORE, TextureDatabase.REDSTONE_CHUNKS),
	EMERALD (Material.EMERALD_ORE, TextureDatabase.EMERALD_CHUNKS),
	DIAMOND (Material.DIAMOND_ORE, TextureDatabase.DIAMOND_CHUNKS);
	
	
	private OreToChunkMap oc;
	
	OreList(Material m, TextureDatabase tex){
		
		this.oc = new OreToChunkMap(m, tex);
		
	}
	
	public OreToChunkMap getOreToChunkMap(){
		return oc;
	}
	
	
}
