package com.quzzar.server.dominions.misc;

public enum PlayerRank {

	Leader(4),
	General(3),
	Veteran(2),
	Member(1);
	
	private int power;
	
	private PlayerRank(int power) {
		this.power = power;
	}
	
	public int getPower() {
		return power;
	}
	
}
