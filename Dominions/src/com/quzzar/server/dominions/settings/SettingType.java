package com.quzzar.server.dominions.settings;

public enum SettingType {
	
	NULL(-1),
	INVITE(1),
	UNINVITE(10),
	CLAIM(3),
	UNCLAIM(12),
	SET_HOME(5),
	HOME(14),
	DEPOSIT(7),
	WITHDRAW(16),
	UPGRADES(28),
	SETTINGS(29),
	CHUNK_SETTINGS(30),
	JOIN(32),
	DESCRIPTION(33),
	SET_DIPLOMACY(34),
	LOG(42),
	BREAK_AND_PLACE(46),
	INTERACT(47),
	OPEN_CONTAINERS(48);
	
	private int index;
	
	private SettingType(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public static SettingType getByIndex(int index) {
		for(SettingType setting : SettingType.values()) {
			if(setting.getIndex() == index) {
				return setting;
			}
		}
		return SettingType.NULL;
	}

}
