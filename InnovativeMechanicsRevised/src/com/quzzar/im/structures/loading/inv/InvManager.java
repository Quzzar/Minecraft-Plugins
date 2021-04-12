package com.quzzar.im.structures.loading.inv;

import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.inventory.Inventory;

import com.quzzar.im.structures.Structure;

public class InvManager {

	private static Hashtable<UUID, Hashtable<InvTag, Inventory>> inventories = new Hashtable<UUID, Hashtable<InvTag, Inventory>>();
	
	public static Inventory getInventory(Structure structure, InvTag tag) {
		
		if(tag == null) {
			return null;
		}
		
		Hashtable<InvTag, Inventory> invs = inventories.get(structure.getID());
		if(invs == null) {
			inventories.put(structure.getID(), new Hashtable<InvTag, Inventory>());
			invs = inventories.get(structure.getID());
		}
		
		Inventory inventory = invs.get(tag);
		if(inventory == null) {
			invs.put(tag, structure.getStructInventory(tag).getInventory(structure, tag));
			inventory = invs.get(tag);
		}
		
		return inventory;
		
	}
	
	public static void saveInventories(Structure structure) {
		
		Hashtable<InvTag, Inventory> invs = inventories.get(structure.getID());
		if(invs != null) {
			for(Entry<InvTag, Inventory> entry : invs.entrySet()) {
				structure.getStructInventory(entry.getKey()).setInventory(entry.getValue());
			}
		}
		
	}
	
}
