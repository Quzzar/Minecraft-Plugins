package quzzar.mod.maps;

import org.bukkit.inventory.ItemStack;

import quzzar.mod.Textures.TextureDatabase;

public class TextureItemStackMap {
	
	private ItemStack i;
	private TextureDatabase tex;
	
	public TextureItemStackMap(TextureDatabase tex, ItemStack i){
		this.tex = tex;
		this.i = i;
	}
	
	
	public ItemStack getItemStack(){
		return i;
	}
	
	public TextureDatabase getTexture(){
		return tex;
	}
	
	
}
