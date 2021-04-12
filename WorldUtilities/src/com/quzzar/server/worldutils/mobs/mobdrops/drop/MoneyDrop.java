package com.quzzar.server.worldutils.mobs.mobdrops.drop;

public class MoneyDrop extends Drop {

	private double minCount, maxCount;
	
	public MoneyDrop(double chance, double minMoney, double maxMoney) {
		super(chance);
		this.minCount = minMoney;
		this.maxCount = maxMoney;
	}
	
	public double getMin() {
		return minCount;
	}
	
	public double getMax() {
		return maxCount;
	}
	
}
