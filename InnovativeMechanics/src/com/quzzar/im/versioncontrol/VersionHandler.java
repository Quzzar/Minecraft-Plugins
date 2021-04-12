package com.quzzar.im.versioncontrol;

import java.util.HashMap;

import org.bukkit.Sound;
import org.bukkit.material.MaterialData;

import com.quzzar.im.versioncontrol.global.material.GMaterial;
import com.quzzar.im.versioncontrol.global.sound.GSound;

@SuppressWarnings("deprecation")
public abstract class VersionHandler {
	
	private HashMap<GMaterial, MaterialData> materialMap;
	private HashMap<GSound, Sound> soundMap;
	
	private VersionType version;
	
	public VersionHandler(VersionType version) {
		this.version = version;
		
		materialMap = new HashMap<GMaterial, MaterialData>();
		soundMap = new HashMap<GSound, Sound>();
		
	}
	
	public abstract MaterialData getMaterialData(GMaterial material);
	public abstract Sound getSound(GSound sound);

	public HashMap<GMaterial, MaterialData> getMaterialMap() {
		return materialMap;
	}
	
	public HashMap<GSound, Sound> getSoundMap() {
		return soundMap;
	}
	
	public VersionType getVersion() {
		return version;
	}

}
