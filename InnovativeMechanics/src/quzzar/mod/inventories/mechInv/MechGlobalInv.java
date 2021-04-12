package quzzar.mod.inventories.mechInv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import quzzar.mod.dataProcessing.InventoryToBase64;
import quzzar.mod.documents.ConfigManager;
import quzzar.mod.maps.UUIDInventoryMap;

public class MechGlobalInv {

	private Inventory inv;
	
	private String name;
	
	private static ConfigManager configManager = ConfigManager.getInstance();
	
	public static List<MechGlobalInv> listInvs = new ArrayList<MechGlobalInv>();
	
	public static List<String> listIDs = new ArrayList<String>();
	
	private static InventoryToBase64 invProcessing = new InventoryToBase64();
	
	public MechGlobalInv(int invSize, String name){
		this.inv = Bukkit.createInventory(null, invSize, name);
		this.name = name;
	}
	
	public MechGlobalInv(Inventory inv){
		this.inv = inv;
		this.name = inv.getName();
	}
	
	public Inventory getInventory(){
		return inv;
	}
	
	public int getSize(){
		return inv.getSize();
	}
	
	public String getName(){
		return name;
	}
	
	public UUID getUUID(){
		return UUIDInventoryMap.getID(this);
	}
	
	public void delete(){
		inv.clear();
		listInvs.remove(this);
	}
	
	public static void createNew(String name, int invSize, UUID uuid){
		MechGlobalInv mechinv = new MechGlobalInv(invSize, name);
		listInvs.add(mechinv);
		if(!listIDs.contains(uuid+"")){
			listIDs.add(uuid+"");
		}
		UUIDInventoryMap.addNew(uuid, mechinv);
	}
	
	public static MechGlobalInv createNewFromLoad(Inventory inv, UUID uuid){
		MechGlobalInv mechinv = new MechGlobalInv(inv);
		listInvs.add(mechinv);
		if(!listIDs.contains(uuid+"")){
			listIDs.add(uuid+"");
		}
		UUIDInventoryMap.addNew(uuid, mechinv);
		
		return mechinv;
	}
	
	public static MechGlobalInv getMechGlobalInv(UUID uuid){
		return UUIDInventoryMap.getMechGlobalInv(uuid);
	}
	
	public static void loadInventories(){
		listInvs.clear();
		listIDs.clear();
		
		List<String> oldIDList = configManager.getInventoryData().getStringList("Global_Inventory_UUIDs");
		listIDs.addAll((List<String>) oldIDList);
		
		for(String str : listIDs){
			
			if(configManager.getInventoryData().getString("Global_Inv_Data."+str)!=null){
				try {
					MechGlobalInv.listInvs.add(MechGlobalInv.createNewFromLoad(invProcessing.inventoryFromBase64NoMap(
							configManager.getInventoryData().getString("Global_Inv_Data."+str)),UUID.fromString(str)));
				} catch (IOException e) {
					Bukkit.getServer().getLogger().severe("Could not decode inventory from inventoryData.yml!");
				}
			}
			
		}
		
		
		
		
	}
	
	
	public static void saveInventories(){
		
		configManager.getInventoryData().set("Global_Inv_Data","");
		configManager.getInventoryData().set("Global_Inventory_UUIDs", "");
		configManager.saveInventoryData();
		
		configManager.getInventoryData().set("Global_Inventory_UUIDs", listIDs);
		
		for(MechGlobalInv mechinv : listInvs){
			
			String str = mechinv.getUUID()+"";
			
			configManager.getInventoryData().set("Global_Inv_Data."+str, invProcessing.inventoryToBase64NoMap(mechinv.getInventory()));
			
		}
		
		configManager.saveInventoryData();
		
	}
	
	
	
}
