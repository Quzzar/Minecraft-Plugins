package com.quzzar.craftmeta.metamap;

import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;

import com.quzzar.craftmeta.CraftMeta;

public class EntityMetaManager {

	private MetaMapManager metaManager;
	private int idCount;
	private CraftMeta plugin;
	private String craftMetaEntityKey;
	
	public EntityMetaManager(CraftMeta plugin) {
		this.plugin = plugin;
		this.craftMetaEntityKey = "CraftMetaEntityMetaKey";
		metaManager = new MetaMapManager();
		idCount = 0;
	}
	
	public void setData(Entity entity, String metaKey, Object data) {
		
		if(!hasData(entity)) {
			idCount++;
			entity.setMetadata(craftMetaEntityKey, new FixedMetadataValue(plugin, idCount));
		}
		
		metaManager.putData(idCount, metaKey, data);
		
	}
	
	public boolean hasData(Entity entity) {
		if(entity.hasMetadata(craftMetaEntityKey)) {
			try {
				return metaManager.hasAnyData(entity.getMetadata(craftMetaEntityKey).get(0).asInt());
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public Object getData(Entity entity, String metaKey) {
		try {
			return metaManager.getData(entity.getMetadata(craftMetaEntityKey).get(0).asInt(), metaKey);
		} catch (Exception e) {
			return null;
		}
	}
	
	public Object getAllData(Entity entity) {
		try {
			return metaManager.getAllData(entity.getMetadata(craftMetaEntityKey).get(0).asInt());
		} catch (Exception e) {
			return null;
		}
	}
	
}
