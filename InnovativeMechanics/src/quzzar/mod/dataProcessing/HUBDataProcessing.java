package quzzar.mod.dataProcessing;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import quzzar.mod.HeadUnit;
import quzzar.mod.Utility;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.blocks.HeadUnitBlock;
import quzzar.mod.documents.WorldData;
import quzzar.mod.inventories.mechInv.MechInvManager;

public class HUBDataProcessing {
	
	public ArrayList<String> getEncryption(ArrayList<HeadUnitBlock> HUBList, WorldData data){
		
		ArrayList<String> encryptedHUBList = new ArrayList<String>();
		
		for(HeadUnitBlock hub : HUBList){
			
			if(hub.getBlock().getLocation().getWorld().equals(data.getWorld())){
				
				String str = hub.getHeadUnit().getTexture().name();
				
				str = str + "!" + hub.getBlock().getLocation().getWorld().getUID() + " " + hub.getBlock().getLocation().getX() + " " + hub.getBlock().getLocation().getY()
						+  " " + hub.getBlock().getLocation().getZ() + "!" + hub.getBlock().getType().name() + "!" + hub.getHeadUnit().getID() + "!" + hub.getHeadUnit().getVar();
				
				encryptedHUBList.add(str);
			}
			
		}
		
		return encryptedHUBList;
		
	}
	
	public List<HeadUnitBlock> decrypt(List<String> encryptedHUBList){
		
		ArrayList<HeadUnitBlock> HUBList = new ArrayList<HeadUnitBlock>();
		
		
		for(String str : encryptedHUBList){
			
			boolean success = true;
			
			String[] sp = str.split("!");
			
			UUID id = UUID.fromString(sp[3]);
			
			HeadUnit headunit = new HeadUnit(sp[0],id);
			
			String[] split = sp[1].split(" ");
			
			World world = Bukkit.getWorld(UUID.fromString(split[0]));
			
			if(world!=null){
				
				Location location = new Location(world, Double.parseDouble(split[1]), Double.parseDouble(split[2]),Double.parseDouble(split[3]));
				Block theBlock = location.getBlock();
				
				Material mat = Material.getMaterial(sp[2]);
				
				int var = -1;
				try {
					var = Integer.parseInt(sp[4]);
					
				} catch(NullPointerException e) {
					Utility.tellConsole(ChatColor.RED+"Files were unsuccessfully synchronized! [ParseInt] Attempting to correct data...");
					success = false;
				}
				
				headunit.setVar(var);
				
				if(headunit.getType().equals(BlockType.STORAGE)){
					
					for(InvUUIDUnit unit : ProcessingManager.mapUUIDInv){
						
						if(unit.getUUID().equals(id)){
							MechInvManager.createNewFromLoad(unit.getInventory(),headunit,unit.getIndex());
						}
						
					}
					
				}
				
				
				if(theBlock.getType().equals(mat)&&success){
					HUBList.add(new HeadUnitBlock(headunit,theBlock));
				}else{
					Utility.tellConsole(ChatColor.RED+"Files were unsuccessfully synchronized! [HU Final Check] Attempting to correct data...");
				}
				
				
			}else{
				Utility.tellConsole(ChatColor.RED+"Failed to retrieve data from old world files!");
			}
			
			
		}
		
		return HUBList;
	}
	
}
