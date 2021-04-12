package quzzar.mod.inventories.mechInv;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import quzzar.mod.dataProcessing.InventoryToBase64;
import quzzar.mod.documents.ConfigManager;

public class MechInvASManager {

	private static ConfigManager configManager = ConfigManager.getInstance();
	
	public static ArrayList<MechInvAS> listAS = new ArrayList<MechInvAS>();
	
	private InventoryToBase64 invProcessing = new InventoryToBase64();
	
	public static void createNew(String name, int invSize, ArmorStand as){
		
		MechInvAS mechinv = new MechInvAS(name, invSize, as);
		listAS.add(mechinv);
		
	}
	
	public static MechInvAS createNew(String name, InventoryType type, ArmorStand as){
		
		MechInvAS mechinv = new MechInvAS(name, type, as);
		listAS.add(mechinv);
		
		return mechinv;
		
	}
	
	public static void createNewFromLoad(Inventory inv, ArmorStand as){
		
		MechInvAS mechinv = new MechInvAS(inv, as);
		listAS.add(mechinv);
		
	}
	
	public static MechInvAS getMechInventory(ArmorStand as){
		
		for(MechInvAS mechinv : listAS){
			if(mechinv.getArmorStand().equals(as)){
				return mechinv;
			}
		}
		return null;
	}
	
	
	public void saveInventories(){
		
		configManager.getInventoryData().set("Inventory_Data_AS","");
		configManager.saveInventoryData();
		
		List<String> invStringList = new ArrayList<String>();
		
		for(MechInvAS mechinv : listAS){
			
			invStringList.add(invProcessing.inventoryToBase64(mechinv.getInventory(),listAS.indexOf(mechinv),mechinv.getArmorStand().getUniqueId()));
			
		}
		
		configManager.getInventoryData().set("Inventory_Data_AS",invStringList);
		
		configManager.saveInventoryData();
		
	}
	
	
}
