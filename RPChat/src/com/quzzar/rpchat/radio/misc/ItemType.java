package com.quzzar.rpchat.radio.misc;

public enum ItemType {

	SENDING_SHELL;
	
	public static ItemType getFromName(String name) {
		name = name.replaceAll(" ", "_");
		name = name.replaceAll("'", "");
		for(ItemType item : ItemType.values()) {
			if(item.name().equalsIgnoreCase(name)) {
				return item;
			}
		}
		return null;
	}
	
}
