package com.quzzar.im.machines.types.industrialfurnace;

import org.bukkit.Material;

public enum Burnables {
	
	COAL(Material.COAL, 96),
	LAVA(Material.LAVA_BUCKET, 1200),
	COAL_BLOCK(Material.COAL_BLOCK, 960),
	CHARCOAL(Material.CHARCOAL, 100);
	
	
	private Material material;
	private int duration;
	
	private int index = -1;
	
	Burnables(Material material, int duration){
		this.material = material;
		this.duration = duration;
	}
	
	public int getDuration(){
		return duration;
	}
	
	public Material getMaterial(){
		return material;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public static Burnables getBurnable(Material material) {
		for(Burnables burnable : Burnables.values()) {
			if(burnable.getMaterial() == material) {
				return burnable;
			}
		}
		return null;
	}
	
}
