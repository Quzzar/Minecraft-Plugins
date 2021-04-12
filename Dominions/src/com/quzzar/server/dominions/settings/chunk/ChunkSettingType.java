package com.quzzar.server.dominions.settings.chunk;

public enum ChunkSettingType {

	NULL(-1),
	BACK(0),
	INFO(8),
	BREAK_AND_PLACE(3),
	INTERACT(4),
	OPEN_CONTAINERS(5);
	
	
	private int index;
	
	private ChunkSettingType(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public static ChunkSettingType getByIndex(int index) {
		for(ChunkSettingType setting : ChunkSettingType.values()) {
			if(setting.getIndex() == index) {
				return setting;
			}
		}
		return ChunkSettingType.NULL;
	}
	
}
