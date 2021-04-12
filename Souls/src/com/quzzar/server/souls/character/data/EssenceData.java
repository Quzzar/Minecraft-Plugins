package com.quzzar.server.souls.character.data;

import java.io.Serializable;

public class EssenceData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private double essence;
	
	public EssenceData() {
		essence = 0.999;
	}
	
	public double getEssence() {
		return essence;
	}
	
	public boolean subtractEssence(double fame) {
		return subtractEssence(fame, 1.0);
	}
	
	public boolean subtractEssence(double fame, double multiplier) {
		
		double fameAdj = fame * multiplier;
		this.essence = essence-fameAdj/1000;
		
		if (essence <= 0.0) {
			return false;
		}
		
		return true;
		
	}
	
	public void setEssence(double essence) {
		this.essence = essence;
	}
	

}
