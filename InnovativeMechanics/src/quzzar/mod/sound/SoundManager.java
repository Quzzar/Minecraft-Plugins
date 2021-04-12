package quzzar.mod.sound;

import org.bukkit.Location;

public class SoundManager {
	
	public static void playSound(Location playingLocation, SoundDatabase sound) {
		playingLocation.getWorld().playSound(playingLocation, sound.getSound(), sound.getVolume(), sound.getPitch());
		
	}
	
	
}
