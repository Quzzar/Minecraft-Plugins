package quzzar.mod.Ores;

import org.bukkit.Material;

import quzzar.mod.Textures.TextureDatabase;

public class OreToChunkMap {

	
	private TextureDatabase tex;
	private Material m;
	
	public OreToChunkMap(Material m, TextureDatabase tex){
		
		this.m = m;
		this.tex = tex;
		
	}
	
	public TextureDatabase getTexture() {
		return tex;
	}
	public Material getMaterial() {
		return m;
	}
	
}
