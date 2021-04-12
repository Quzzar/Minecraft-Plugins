package quzzar.mod.Utilities;

import org.bukkit.block.Block;
import org.bukkit.event.inventory.InventoryType;

import quzzar.mod.HeadUnit;
import quzzar.mod.Utility;
import quzzar.mod.inventories.mechInv.MechInvManager;

public class HU_Utilities {

	
	
	public static void createHB_Blank(Block b, HeadUnit headunit){
		
		Utility.snapToGrid(b);
		
		headunit.addToHeadUnitBlockList(b);
		
	}
	
	public static void createHB_Storage(Block b, HeadUnit headunit, String invName, int invSize){
		
		Utility.snapToGrid(b);
		
		
		MechInvManager.createNew(invName,invSize,headunit);
		
		headunit.addToHeadUnitBlockList(b);
		
	}
	
	public static void createHB_Storage_By_Type(Block b, HeadUnit headunit, String invName, InventoryType type){
		
		Utility.snapToGrid(b);
		
		
		MechInvManager.createNew(invName,type,headunit);
		
		headunit.addToHeadUnitBlockList(b);
		
	}
	
	public static void createHB_Global_Storage(Block b, HeadUnit headunit){
		
		Utility.snapToGrid(b);
		
		headunit.addToHeadUnitBlockList(b);
		
	}
	
	
	
}
