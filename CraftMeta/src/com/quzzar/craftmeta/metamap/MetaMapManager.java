package com.quzzar.craftmeta.metamap;

import java.util.HashMap;

public class MetaMapManager {

	private HashMap<Integer, HashMap<String, Object>> dataMap;
	
	public MetaMapManager() {
		dataMap = new HashMap<Integer, HashMap<String, Object>>();
	}
	
	public void putData(int itemID, String metaKey, Object data) {
		HashMap<String, Object> metaMap;
		if(hasAnyData(itemID)) {
			metaMap = dataMap.get(itemID);
		} else {
			metaMap = new HashMap<String, Object>();
		}
		metaMap.put(metaKey, data);
		dataMap.put(itemID, metaMap);
	}
	
	public boolean hasAnyData(int itemID) {
		return dataMap.containsKey(itemID);
	}
	
	public HashMap<String, Object> getAllData(int itemID) {
		return dataMap.get(itemID);
	}
	
	public boolean hasData(int itemID, String metaKey) {
		return dataMap.get(itemID).containsKey(metaKey);
	}
	
	public Object getData(int itemID, String metaKey) {
		return dataMap.get(itemID).get(metaKey);
	}
	
}
