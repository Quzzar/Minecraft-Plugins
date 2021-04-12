package quzzar.mod.thedark.structures;

import org.bukkit.Material;

public class PotentialBlock {
	
	private Material material;
	
	private int x;
	private int y;
	private int z;
	
	public PotentialBlock(int x, int y, int z, Material material) {
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.material = material;
		
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getZ(){
		return z;
	}
	
	public Material getMaterial(){
		return material;
	}
	
	
}
