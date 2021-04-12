package com.quzzar.craftmeta.metamap;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemMetaManager {

	private MetaMapManager metaManager;
	private int idCount;
	
	public ItemMetaManager() {
		metaManager = new MetaMapManager();
		idCount = 600;
	}
	
	public void setData(ItemStack item, String metaKey, Object data) {
		
		if(!hasData(item)) {
			idCount++;
			ItemMeta im = item.getItemMeta();
			im.setCustomModelData(idCount);
			item.setItemMeta(im);
		}
		
		metaManager.putData(idCount, metaKey, data);
		
	}
	
	public boolean hasData(ItemStack item) {
		if(item.hasItemMeta() && item.getItemMeta().hasCustomModelData()) {
			return metaManager.hasAnyData(item.getItemMeta().getCustomModelData());
		} else {
			return false;
		}
	}
	
	public Object getData(ItemStack item, String metaKey) {
		return metaManager.getData(item.getItemMeta().getCustomModelData(), metaKey);
	}
	
	public Object getAllData(ItemStack item) {
		return metaManager.getAllData(item.getItemMeta().getCustomModelData());
	}
	
}
