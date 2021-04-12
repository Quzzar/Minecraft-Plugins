package com.quzzar.server.souls.character.data;

import java.io.Serializable;

public class FameData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Players can have 1 - 1000 total value
	private final double reduceRate = -0.005; // Which is -1.5 fame and -1.5 infamy per hour
	
	private double fame;
	private double minimumFame;
	private double infamy;
	private double minimumInfamy;
	
	public FameData() {
		
		this.minimumFame = 1.0;
		this.fame = minimumFame;
		
		this.minimumInfamy = 0.0;
		this.infamy = minimumInfamy;
		
	}

	public double getValue() {
		return fame+infamy;
	}
	
	public void adjustFame(double rate) {
		if(fame + rate >= minimumFame) {
			fame += rate;
		} else {
			fame = minimumFame;
		}
	}
	
	public void setFame(double amt) {
		if(amt >= minimumFame) {
			fame = amt;
		} else {
			fame = minimumFame;
		}
	}
	
	public double getFame() {
		return fame;
	}

	public void adjustInfamy(double rate) {
		if(infamy + rate >= minimumInfamy) {
			infamy += rate;
		} else {
			infamy = minimumInfamy;
		}
	}
	
	public void setInfamy(double infamy) {
		this.infamy = infamy;
	}
	
	
	public double getInfamy() {
		return infamy;
	}
	
	public void naturallyReduce() {
		adjustFame(reduceRate);
		adjustInfamy(reduceRate);
	}

	public void setMinFame(double minimumFame) {
		this.minimumFame = minimumFame;
	}
	
}
