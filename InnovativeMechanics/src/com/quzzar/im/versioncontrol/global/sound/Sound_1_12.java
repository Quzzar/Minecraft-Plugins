package com.quzzar.im.versioncontrol.global.sound;

import org.bukkit.Sound;

public enum Sound_1_12 {

	ENTITY_ZOMBIE_BREAK_WOODEN_DOOR(Sound.ENTITY_ZOMBIE_BREAK_DOOR_WOOD),
	BLOCK_METAL_PRESSURE_PLATE_CLICK_ON(Sound.BLOCK_METAL_PRESSUREPLATE_CLICK_ON),
	BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF(Sound.BLOCK_METAL_PRESSUREPLATE_CLICK_OFF),
	BLOCK_ENDER_CHEST_OPEN(Sound.BLOCK_ENDERCHEST_OPEN),
	BLOCK_NOTE_BLOCK_BELL(Sound.BLOCK_NOTE_BELL);
	
	private Sound sound;
	
	private Sound_1_12(Sound sound) {
		this.sound = sound;
	}
	
	public Sound getSound() {
		return sound;
	}
	
	public static Sound_1_12[] getAllValues() {
		return Sound_1_12.values();
	}
	
}
