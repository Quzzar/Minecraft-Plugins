package com.quzzar.server.souls.character.souls;

import java.util.Hashtable;
import java.util.UUID;

import com.quzzar.server.souls.Souls;

public class SoulManager {
	
	private static Hashtable<UUID, Integer> currentSouls = new Hashtable<UUID, Integer>();
	
	public static void syncToDatabase() {
		Souls.instance.getSoulsConnection().fillTableWithInfo();
	}
	
	public static int getSoulAmount(UUID pUUID) {
		Integer amt = currentSouls.get(pUUID);
		if(amt == null) {
			addNew(pUUID);
			amt = currentSouls.get(pUUID);
		}
		return amt;
	}
	
	public static void addNew(UUID pUUID) {
		Souls.instance.getSoulsConnection().addNewEntry(pUUID);
		currentSouls.put(pUUID, SoulsConnection.defaultSoulCount);
	}
	
	public static void update(UUID pUUID, int soulAmt) {
		if(!currentSouls.containsKey(pUUID)){addNew(pUUID);}
		Souls.instance.getSoulsConnection().updateEntry(pUUID, soulAmt);
		currentSouls.put(pUUID, soulAmt);
	}
	
	public static Hashtable<UUID, Integer> getSoulTable() {
		return currentSouls;
	}
	
}
