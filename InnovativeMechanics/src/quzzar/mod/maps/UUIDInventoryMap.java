package quzzar.mod.maps;

import java.util.HashMap;
import java.util.UUID;

import quzzar.mod.inventories.mechInv.MechGlobalInv;

public class UUIDInventoryMap {

	private static HashMap<UUID,MechGlobalInv> map = new HashMap<UUID,MechGlobalInv>();
	
	public static void addNew(UUID id,MechGlobalInv mechglobalinv){
		map.put(id, mechglobalinv);
	}
	
	public static MechGlobalInv getMechGlobalInv(UUID id){
		if(isMapped(id)){
			return map.get(id);
		}
		return null;
	}
	
	public static UUID getID(MechGlobalInv mechglobalinv){
		if(isMapped(mechglobalinv)){
			for(UUID id : map.keySet()){
				if(map.get(id).equals(mechglobalinv)){
					return id;
				}
			}
		}
		return null;
	}
	
	
	public static boolean isMapped(UUID id){
		if(map.containsKey(id)){
			return true;
		}
		return false;
	}
	
	public static boolean isMapped(MechGlobalInv mechglobalinv){
		if(map.containsValue(mechglobalinv)){
			return true;
		}
		return false;
	}
	
	public static HashMap<UUID,MechGlobalInv> getMap(){
		return map;
	}
	
	
}
