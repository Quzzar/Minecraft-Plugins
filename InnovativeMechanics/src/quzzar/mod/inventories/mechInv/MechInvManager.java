package quzzar.mod.inventories.mechInv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import quzzar.mod.HeadUnit;
import quzzar.mod.dataProcessing.InventoryToBase64;
import quzzar.mod.documents.ConfigManager;

public class MechInvManager {

	private static ConfigManager configManager = ConfigManager.getInstance();
	
	public static HashMap<UUID,List<MechInv>> mapInvs = new HashMap<UUID,List<MechInv>>();
	
	private InventoryToBase64 invProcessing = new InventoryToBase64();
	
	public static void createNew(String name,int invSize, HeadUnit headunit){
		
		MechInv mechinv = new MechInv(name, invSize, headunit);
		mapAdd(mechinv);
		
	}
	
	public static void createNew(String name, InventoryType type, HeadUnit headunit){
		
		MechInv mechinv = new MechInv(name, type, headunit);
		mapAdd(mechinv);
		
	}
	
	public static void createNewFromLoad(Inventory inv, HeadUnit headunit, int index){
		
		MechInv mechinv = new MechInv(inv, headunit);
		mapAddFromLoad(mechinv, index);
		
	}
	
	public static List<MechInv> getMechInventories(HeadUnit headunit){
		
		for(UUID uuid : mapInvs.keySet()){
			if(uuid.equals(headunit.getID())){
				return mapInvs.get(uuid);
			}
		}
		return null;
	}
	
	
	public void saveInventories(){
		
		configManager.getInventoryData().set("Inventory_Data","");
		configManager.saveInventoryData();
		
		List<String> invStringList = new ArrayList<String>();
		
		for(UUID uuid : mapInvs.keySet()){
			
			for(MechInv mechinv : mapInvs.get(uuid)){
				if(mechinv!=null){
					
					invStringList.add(invProcessing.inventoryToBase64(mechinv.getInventory(),mapInvs.get(uuid).indexOf(mechinv),uuid));
					
				}
			}
			
			
		}
		
		configManager.getInventoryData().set("Inventory_Data",invStringList);
		
		configManager.saveInventoryData();
		
	}
	
	
	private static void mapAdd(MechInv mechinv){
		if(mapInvs.containsKey(mechinv.getHeadUnit().getID())){
			for(UUID uuid : mapInvs.keySet()){
				if(uuid.equals(mechinv.getHeadUnit().getID())){
					mapInvs.get(uuid).add(mechinv);
					return;
				}
			}
		}else{
			List<MechInv> list = new ArrayList<MechInv>();
			list.add(mechinv);
			mapInvs.put(mechinv.getHeadUnit().getID(), list);
		}
	}
	
	private static void mapAddFromLoad(MechInv mechinv, int index){
		if(mapInvs.containsKey(mechinv.getHeadUnit().getID())){
			for(UUID uuid : mapInvs.keySet()){
				if(uuid.equals(mechinv.getHeadUnit().getID())){
					mapInvs.get(uuid).add(index, mechinv);
					return;
				}
			}
		}else{
			List<MechInv> list = new ArrayList<MechInv>();
			list.add(index, mechinv);
			mapInvs.put(mechinv.getHeadUnit().getID(), list);
		}
	}
	
	
}
