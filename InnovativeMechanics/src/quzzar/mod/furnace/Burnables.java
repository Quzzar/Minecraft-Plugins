package quzzar.mod.furnace;

import org.bukkit.Material;

import com.quzzar.im.versioncontrol.VersionControl;
import com.quzzar.im.versioncontrol.global.material.GMaterial;

public enum Burnables {

	COAL(Material.COAL, 96),
	LAVA(Material.LAVA_BUCKET, 1200),
	COAL_BLOCK(Material.COAL_BLOCK, 960),
	LOG_1(VersionControl.getMaterial(GMaterial.LOG), 18),
	LOG_2(VersionControl.getMaterial(GMaterial.LOG_2), 20),
	WOOD(VersionControl.getMaterial(GMaterial.WOOD), 16),
	WOOL(VersionControl.getMaterial(GMaterial.WOOL), 10);
	
	
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
	
}
