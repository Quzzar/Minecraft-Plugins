package quzzar.mod.dataProcessing;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import quzzar.mod.HeadUnit;
import quzzar.mod.MultiHeadUnit;
import quzzar.mod.Utility;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.Utilities.MHU_Utilities;
import quzzar.mod.documents.WorldData;
import quzzar.mod.inventories.mechInv.MechInvASManager;
import quzzar.mod.inventories.mechInv.MechInvManager;
import quzzar.mod.mhu.LargeMachineUnit;
import quzzar.mod.mhu.ArmorStandGeneration.ArmorStandDesign;

public class MHUDataProcessing {
	
	
	public ArrayList<String> getEncryption(ArrayList<MultiHeadUnit> MHUList, WorldData data){
		
		ArrayList<String> encryptedMHUList = new ArrayList<String>();
		
		for(MultiHeadUnit mhu : MHUList){
			
			if(mhu.getCenterLocation().getWorld().equals(data.getWorld())) {
				
				String str = mhu.getCenterLocation().getWorld().getUID() // 0
						+ "!" + mhu.getCenterLocation().getX()+","+mhu.getCenterLocation().getY()+","+mhu.getCenterLocation().getZ() // 1
						+ "!" + mhu.getItemHU().getTexture().name() // 2
						+ "!" + mhu.getItemHU().getID() // 3
						+ "!" + mhu.getBlockFace().name() // 4
						+ "!" + mhu.getVariable1() // 5
						+ "!" + mhu.getVariable2() // 6
						+ "!" + mhu.getVariable3() // 7
						+ "!" + mhu.getCreatorPlayer().getUniqueId() // 8
				        + "!" + "ExtraDataHere"; // 9
				
				encryptedMHUList.add(str);
				
			}
			
		}
		
		
		return encryptedMHUList;
	}
	
	
	
	public List<MultiHeadUnit> decrypt(List<String> encryptedMHUList){
		
		ArrayList<MultiHeadUnit> MHUList = new ArrayList<MultiHeadUnit>();
		
		
		for(String str : encryptedMHUList){
			
			retrieving:
			{
				
				String[] split = str.split("!");
				
				
				World world = Bukkit.getWorld(UUID.fromString(split[0])); // 0
				
				
				
				String[] cLocS = split[1].split(","); // 1
				String xCRaw = cLocS[0]; String yCRaw = cLocS[1]; String zCRaw = cLocS[2];
				double loc_xC = 0; double loc_yC = 0; double loc_zC = 0;
				try {
					loc_xC = Double.parseDouble(xCRaw);
					loc_yC = Double.parseDouble(yCRaw);
					loc_zC = Double.parseDouble(zCRaw);
				} catch(NullPointerException e) {
					Utility.tellConsole(ChatColor.RED+"Files were unsuccessfully synchronized [ParseDouble Failure on Center Location]!");
					break retrieving;
				}
				
				final Location centerLoc = new Location(world, loc_xC, loc_yC, loc_zC);
				
				
				
				UUID id = UUID.fromString(split[3]); // 3
				
				HeadUnit headUnit = new HeadUnit(split[2],id); // 2
				
				
				
				BlockFace blockFace = BlockFace.NORTH;  // 4
				
				for(BlockFace face : BlockFace.values()) {
					if(face.name().equals(split[4])) {
						blockFace = face;
					}
				}
				
				
				int var1 = -1; int var3 = -1; // 5 & 7
				try {
					var1 = Integer.parseInt(split[5]);
					var3 = Integer.parseInt(split[7]);
				} catch(NullPointerException e) {
					Utility.tellConsole(ChatColor.RED+"Files were unsuccessfully synchronized [ParseInt Failure]!");
					break retrieving;
				}
				
				String var2 = split[6]; // 6
				
				
				
				LargeMachineUnit machine = MHU_Utilities.getMachineType(headUnit);
				
				
				ArrayList<ArmorStand> asList;
				
				if(machine.getMachineType().equals(BlockType.MHU_Pipe)) {
					asList = ArmorStandDesign.confirmPipeStructure(centerLoc, id, var1, 0);
				}else {
					asList = ArmorStandDesign.confirmASStructure(centerLoc, machine.getASPlacements(), blockFace, id, 0);
				}
				
				if(asList==null) {
					
					centerLoc.getBlock().setType(Material.AIR);
					
				}else {
					
					// Get ArmorStand Inventories
					for(InvUUIDUnit unit : ProcessingManager.mapUUIDInvAS){
						
						for(ArmorStand as : asList){
							if(as.getUniqueId().equals(unit.getUUID())){
								
								MechInvASManager.createNewFromLoad(unit.getInventory(),as);
								
							}
						}
					}
					//
					
					// Get HeadUnit Inventories
					for(InvUUIDUnit unit : ProcessingManager.mapUUIDInv){
						
						if(unit.getUUID().equals(id)){
							
							MechInvManager.createNewFromLoad(unit.getInventory(),headUnit,unit.getIndex());
							
						}
						
					}
					//
					
					MultiHeadUnit mhu = MultiHeadUnit.createNewLoad(centerLoc, headUnit, asList, centerLoc.getBlock(), blockFace, var1, var2, var3);
					
					OfflinePlayer p = Bukkit.getOfflinePlayer(UUID.fromString(split[8]));
					if(p!=null){
						mhu.setCreatorPlayer(p);
					}else {
						Utility.tellConsole(ChatColor.RED+"Failed to find machine owner ("+split[8]+")!");
					}
					
				}
				
			}
			
		}
		return MHUList;
	}
	
	
	
	
	
}
