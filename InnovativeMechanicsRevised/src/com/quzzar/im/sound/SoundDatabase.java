package com.quzzar.im.sound;

import org.bukkit.Sound;

public enum SoundDatabase {
	
	BLOCK_BREAK (Sound.BLOCK_STONE_BREAK, 2, 2),
	
	MINICHEST_OPEN (Sound.BLOCK_CHEST_OPEN, 0.3f, 1),
	
	QUIET_BURNING (Sound.ENTITY_GENERIC_BURN, 0.01f, 0.05f),
	
	FURNACE_BURNING (Sound.BLOCK_FIRE_AMBIENT, 0.2f, 0.2f),
	FURNACE_COMPLETE (Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.3f, 0.1f),
	
	MACHINE_RUNNING (Sound.BLOCK_PISTON_CONTRACT, 0.07f, 0.55f),
	MACHINE_HIT (Sound.BLOCK_METAL_BREAK, 0.4f, 0.1f),
	MACHINE_BREAK (Sound.BLOCK_METAL_BREAK, 2, 2),
	MACHINE_SUCCESS (Sound.BLOCK_NOTE_BLOCK_BELL, 0.05f, 0.45f);
	
	
	private final Sound sound;
	private final float volume;
	private final float pitch;
	
	SoundDatabase(Sound sound, float volume, float pitch){
		this.sound = sound;
		this.volume = volume;
		this.pitch = pitch;
	}
	
	public Sound getSound(){
		return sound;
	}
	
	public float getVolume(){
		return volume;
	}
	
	public float getPitch(){
		return pitch;
	}
	
}
