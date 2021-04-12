package com.quzzar.server.skills;

import java.io.Serializable;
import java.util.UUID;

public class SkillSet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static int defaultAmt = 6000;
	
	private UUID uuid;
	
	// 0=Cutting, 1=Farming, 2=Mining, 3=Fighting, 4=Digging
	private int[] skills;
	
	public SkillSet(UUID uuid) {
		
		skills = new int[5];
		
		skills[0] = defaultAmt;
		skills[1] = defaultAmt;
		skills[2] = defaultAmt;
		skills[3] = defaultAmt;
		skills[4] = defaultAmt;
		
		this.uuid = uuid;
		
	}
	
	public UUID getUUID() {
		return uuid;
	}
	
	///
	
	public int get(int index) {
		return skills[index];
	}
	
	public void set(int index, int value) {
		skills[index] = value;
	}
	
	///
	
	public void increment(int index, int amount) {
		skills[index] += amount;
	}
	
	public void decrement(int index, int amount) {
		skills[index] -= amount;
	}
	
}
