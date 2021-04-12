package com.quzzar.im.structures.loading.inv;

import java.io.Serializable;
import java.util.UUID;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import com.quzzar.im.structures.Structure;

public class StructInv implements Serializable {

	private static final long serialVersionUID = 3384462276927066635L;
	
	private String invTitle;
	private String encodedItems;
	private InventoryType invType;
	private UUID uuid;
	
	public StructInv(Inventory inv, String invTitle) {
		
		this.invTitle = invTitle;
		setInventory(inv);
		this.invType = inv.getType();
		this.uuid = UUID.randomUUID();
		
	}

	public String getInvTitle() {
		return invTitle;
	}

	public InventoryType getInvType() {
		return invType;
	}
	
	public String getEncodedItems() {
		return encodedItems;
	}

	public UUID getID() {
		return uuid;
	}
	
	public Inventory getInventory(Structure thisStructure, InvTag thisTag) {
		return InventoryToBase64.inventoryFromBase64(this, thisStructure, thisTag);
	}
	
	public void setInventory(Inventory inv) {
		this.encodedItems = InventoryToBase64.inventoryToBase64(inv);
	}
	
}
