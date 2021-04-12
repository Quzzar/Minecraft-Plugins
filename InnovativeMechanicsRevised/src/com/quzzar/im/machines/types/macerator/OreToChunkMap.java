package com.quzzar.im.machines.types.macerator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.im.textures.TextureDatabase;

public class OreToChunkMap {
	
	private TextureDatabase tex;
	private Material m;
	private String name;
	
	public OreToChunkMap(Material m, TextureDatabase tex, String name){
		
		this.m = m;
		this.tex = tex;
		this.name = name;
		
	}
	
	public TextureDatabase getTexture() {
		return tex;
	}
	
	public ItemStack getItem() {
		
		ItemStack rawItem = tex.toRawItemStack(1);
		ItemMeta im = rawItem.getItemMeta();
		im.setDisplayName(ChatColor.RESET+name);
		rawItem.setItemMeta(im);
		
		return rawItem;
	}
	
	public Material getMaterial() {
		return m;
	}
	
}
