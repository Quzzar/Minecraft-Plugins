package com.quzzar.server.worldutils.mobs.mobdrops.drop;

public class Drop {
	
	private double chance; // from 0 - 100 %
	
	public Drop(double chance) {
		this.chance = chance;
	}
	
	public double getChance() {
		return chance;
	}
	
}
