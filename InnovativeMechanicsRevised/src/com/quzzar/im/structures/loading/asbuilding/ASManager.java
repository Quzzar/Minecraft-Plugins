package com.quzzar.im.structures.loading.asbuilding;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

import org.bukkit.entity.ArmorStand;

import com.quzzar.im.machines.MachineType;
import com.quzzar.im.structures.Structure;

public class ASManager {

	private static Hashtable<UUID, ArrayList<ArmorStand>> armorStands = new Hashtable<UUID, ArrayList<ArmorStand>>();
	
	public static void spawnArmorStands(Structure structure) {
		
		if(isSpawned(structure)) {
			despawnArmorStands(structure);
		}
		
		ArrayList<ArmorStand> stands;
		if(structure.getMachineType() == MachineType.PIPE) {
			stands = Loading.createPipe(structure);
		} else {
			stands = Loading.createStructure(structure);
		}
		
		armorStands.put(structure.getID(), stands);
		
	}
	
	public static void despawnArmorStands(Structure structure) {
		
		ArrayList<ArmorStand> stands = armorStands.get(structure.getID());
		if(stands != null) {
			
			for(ArmorStand stand : stands) {
				stand.remove();
			}
			
			armorStands.remove(structure.getID());
			
		}
		
	}
	
	public static void refreshArmorStands(Structure structure) {
		
		Loading.updateHeads(armorStands.get(structure.getID()), structure);
		
	}
	
	public static boolean isSpawned(Structure structure) {
		return armorStands.get(structure.getID()) != null;
	}
	
	public static void deleteAll() {
		
		for(ArrayList<ArmorStand> stands : armorStands.values()) {
			for(ArmorStand stand : stands) {
				stand.remove();
			}
		}
		
		armorStands.clear();
		
	}
	
}
