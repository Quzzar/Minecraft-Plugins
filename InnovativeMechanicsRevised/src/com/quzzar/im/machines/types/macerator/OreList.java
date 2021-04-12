package com.quzzar.im.machines.types.macerator;

import org.bukkit.Material;
import com.quzzar.im.textures.TextureDatabase;

public enum OreList {
	
	
	COAL (Material.COAL_ORE, TextureDatabase.COAL_CHUNKS, "Coal Chunks"),
	IRON (Material.IRON_ORE, TextureDatabase.IRON_CHUNKS, "Iron Chunks"),
	GOLD (Material.GOLD_ORE, TextureDatabase.GOLD_CHUNKS, "Gold Chunks"),
	LAPIS (Material.LAPIS_ORE, TextureDatabase.LAPIS_CHUNKS, "Lapis Chunks"),
	REDSTONE (Material.REDSTONE_ORE, TextureDatabase.REDSTONE_CHUNKS, "Redstone Chunks"),
	EMERALD (Material.EMERALD_ORE, TextureDatabase.EMERALD_CHUNKS, "Emerald Chunks"),
	DIAMOND (Material.DIAMOND_ORE, TextureDatabase.DIAMOND_CHUNKS, "Diamond Chunks");
	
	
	private OreToChunkMap oc;
	
	OreList(Material m, TextureDatabase tex, String name){
		
		this.oc = new OreToChunkMap(m, tex, name);
		
	}
	
	public OreToChunkMap getOreToChunkMap(){
		return oc;
	}
	
	
}
