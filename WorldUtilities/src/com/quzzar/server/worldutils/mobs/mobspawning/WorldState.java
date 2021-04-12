package com.quzzar.server.worldutils.mobs.mobspawning;

import org.bukkit.World;

public enum WorldState {

	ANY,
	RAINING;
	
	public static WorldState getWorldState(World world) {
		if(world.hasStorm()) {
			return WorldState.RAINING;
		}
		return WorldState.ANY;
	}
	
}
