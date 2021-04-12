package com.quzzar.craftmeta.metamap;

import org.bukkit.inventory.Inventory;

public class InventoryMetaManager {

	private MetaMapManager metaManager;
	private int idCount;
	
	public InventoryMetaManager() {
		metaManager = new MetaMapManager();
		idCount = 100;
	}
	
	public void setData(Inventory inv, String metaKey, Object data) {
		
		if(!hasData(inv)) {
			idCount++;
			inv.setMaxStackSize(idCount);
		}
		
		metaManager.putData(idCount, metaKey, data);
		
	}
	
	public boolean hasData(Inventory inv) {
		if(inv.getMaxStackSize() > 64) {
			return metaManager.hasAnyData(inv.getMaxStackSize());
		} else {
			return false;
		}
	}
	
	public Object getData(Inventory inv, String metaKey) {
		return metaManager.getData(inv.getMaxStackSize(), metaKey);
	}
	
	public Object getAllData(Inventory inv) {
		return metaManager.getAllData(inv.getMaxStackSize());
	}
	
}
