package com.quzzar.im.machines.types.pipe;

import java.util.Hashtable;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PipePlacingManager {

	private static Hashtable<UUID, InvLocData> placingData = new Hashtable<UUID, InvLocData>();
	
	public static InvLocData addEnd(Player p, InvLocData data) {
		// Retuns any other end that was already added, therefore allowing the pipe to be created
		if(placingData.containsKey(p.getUniqueId())) {
			return placingData.get(p.getUniqueId());
		} else {
			placingData.put(p.getUniqueId(), data);
			return null;
		}
	}
	
	public static void remove(Player p) {
		placingData.remove(p.getUniqueId());
	}
	
}
