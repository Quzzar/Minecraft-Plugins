package com.quzzar.im.versioncontrol;

import org.bukkit.Sound;
import org.bukkit.material.MaterialData;

import com.quzzar.im.versioncontrol.global.material.GMaterial;
import com.quzzar.im.versioncontrol.global.material.Material_1_12;
import com.quzzar.im.versioncontrol.global.sound.GSound;
import com.quzzar.im.versioncontrol.global.sound.Sound_1_12;

@SuppressWarnings("deprecation")
public class VersionHandler_1_12 extends VersionHandler{
	
	public VersionHandler_1_12(VersionType version) {
		super(version);
		
		for(Material_1_12 material_1_12 : Material_1_12.getAllValues()) {
			getMaterialMap().put(GMaterial.valueOf(material_1_12.name()), material_1_12.getMaterialData());
		}
		
		for(Sound_1_12 sound_1_12 : Sound_1_12.getAllValues()) {
			getSoundMap().put(GSound.valueOf(sound_1_12.name()), sound_1_12.getSound());
		}
		
	}
	
	@Override
	public MaterialData getMaterialData(GMaterial material) {
		return getMaterialMap().get(material);
	}
	
	@Override
	public Sound getSound(GSound sound) {
		return this.getSoundMap().get(sound);
	}
	
}
