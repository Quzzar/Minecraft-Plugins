package com.quzzar.server.souls.character.data;

import java.io.Serializable;

public class SoulData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int HOURS_TO_RECHARGE = 48;
	
	private boolean inLimbo;
	private int hoursLeft;
	
	public SoulData() {
		this.hoursLeft = HOURS_TO_RECHARGE;
		this.inLimbo = true;
	}
	
	public int getRechargeHours() {
		return hoursLeft;
	}
	
	public void setRechargeHours(int hoursLeft) {
		if(hoursLeft < 0) {hoursLeft = 0;}
		this.hoursLeft = hoursLeft;
	}
	
	public boolean inLimbo() {
		return inLimbo;
	}
	
	public void revive() {
		inLimbo = false;
		hoursLeft = 0;
	}
	
}
