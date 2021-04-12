package quzzar.mod.dataProcessing;

import java.util.UUID;

import org.bukkit.inventory.Inventory;

public class InvUUIDUnit {

	private Inventory inv;
	private int index;
	private UUID uuid;
	
	public InvUUIDUnit(Inventory inv, int index, UUID uuid){
		this.inv = inv;
		this.index = index;
		this.uuid = uuid;
	}
	
	
	public Inventory getInventory(){
		return inv;
	}
	
	public int getIndex(){
		return index;
	}
	
	public UUID getUUID(){
		return uuid;
	}
	
	
}
