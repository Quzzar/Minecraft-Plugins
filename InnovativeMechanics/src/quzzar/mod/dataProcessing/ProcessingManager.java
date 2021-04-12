package quzzar.mod.dataProcessing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import quzzar.mod.documents.ConfigManager;

public class ProcessingManager {
	
	private static ConfigManager configManager = ConfigManager.getInstance();
	
	public static ArrayList<InvUUIDUnit> mapUUIDInv = new ArrayList<InvUUIDUnit>();
	public static ArrayList<InvUUIDUnit> mapUUIDInvAS = new ArrayList<InvUUIDUnit>();
	
	private static InventoryToBase64 invProcessing = new InventoryToBase64();
	
	public static void fillUUIDInvMap(){
		
		List<String> invData = configManager.getInventoryData().getStringList("Inventory_Data");
		
		if(invData!=null){
			
			for(String s : invData){
				try {
					mapUUIDInv.add(invProcessing.inventoryFromBase64(s));
				} catch (IOException e) {
					Bukkit.getServer().getLogger().severe("Could not decode inventory from inventoryData.yml (HU)!");
				}
			}
			
		}
		
		
		
		List<String> invDataAS = configManager.getInventoryData().getStringList("Inventory_Data_AS");
		
		if(invDataAS!=null){
			
			for(String s : invDataAS){
				try {
					mapUUIDInvAS.add(invProcessing.inventoryFromBase64(s));
				} catch (IOException e) {
					Bukkit.getServer().getLogger().severe("Could not decode inventory from inventoryData.yml (from AS)!");
				}
			}
			
		}
		
	}
	
	
	
	
	
}
