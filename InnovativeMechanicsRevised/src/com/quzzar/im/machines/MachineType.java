package com.quzzar.im.machines;

public enum MachineType {
	
	LARGE_DIRT_CHEST,
	PIPE,
	ITEM_FILTER,
	INCINERATOR,
	MINER (true, true),
	QUARRY (true, true),
	RECYCLER (true, true),
	MACERATOR (true, true),
	AUTO_CRAFTING_TABLE (true, true),
	INDUSTRIAL_FURNACE (true, false),
	CREMATOR (true, false),
	HEALER;
	
	
	private Boolean powerable;
	
	private Boolean upgradable;
	
	MachineType(){
		powerable = false;
		upgradable = false;
	}
	
	MachineType(Boolean powerable, Boolean upgradable){
		this.powerable = powerable;
		this.upgradable = upgradable;
	}
	
	public Boolean isPowerable(){
		return powerable;
	}
	
	public Boolean isUpgradable(){
		return upgradable;
	}
	
}
