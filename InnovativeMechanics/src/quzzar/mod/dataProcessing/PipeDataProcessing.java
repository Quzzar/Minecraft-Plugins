package quzzar.mod.dataProcessing;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;

import quzzar.mod.HeadUnit;
import quzzar.mod.Main;
import quzzar.mod.MultiHeadUnit;
import quzzar.mod.Utility;
import quzzar.mod.blocks.BreakBlock;
import quzzar.mod.documents.WorldData;
import quzzar.mod.pipes.HUPipe;
import quzzar.mod.pipes.HUPipeManager;

public class PipeDataProcessing {

	
	public ArrayList<String> getEncryption(ArrayList<HUPipe> PipeList, WorldData data){
		
		ArrayList<String> encryptedPipeList = new ArrayList<String>();
		
		for(HUPipe pipe : PipeList){
			
			if(pipe.getBeginLocation().getWorld().equals(data.getWorld()) && pipe.getCompletedMHU()!=null){
				
				HeadUnit mhuItem = pipe.getCompletedMHU().getItemHU();
				Location beginLoc = pipe.getBeginLocation();
				Location endLoc = pipe.getEndLocation();
				
				String str = mhuItem.getTexture().name() + "!" + mhuItem.getID() + "!" + beginLoc.getWorld().getUID();
				
				str = str + "!" + "EmptyValue1" + "!" + beginLoc.getX() + "!" + beginLoc.getY() + "!" + beginLoc.getZ();
				str = str + "!" + "EmptyValue2" + "!" + endLoc.getX() + "!" + endLoc.getY() + "!" + endLoc.getZ();
				
				encryptedPipeList.add(str);
				
			}
			
		}
		
		return encryptedPipeList;
	}
	
	
	
	public List<HUPipe> decrypt(List<String> encryptedPipeList){
		
		ArrayList<HUPipe> PipeList = new ArrayList<HUPipe>();
		
		
		for(String str : encryptedPipeList){
			
			boolean success = true;
			
			String[] sp = str.split("!");
			
			
			UUID mhuItemID = UUID.fromString(sp[1]);
			World world = Bukkit.getWorld(UUID.fromString(sp[2]));
			
			
			String x1Raw = sp[4]; String y1Raw = sp[5]; String z1Raw = sp[6];
			String x2Raw = sp[8]; String y2Raw = sp[9]; String z2Raw = sp[10];
			
			double loc_x1 = 0; double loc_y1 = 0; double loc_z1 = 0;
			double loc_x2 = 0; double loc_y2 = 0; double loc_z2 = 0;
			try {
				loc_x1 = Double.parseDouble(x1Raw);
				loc_y1 = Double.parseDouble(y1Raw);
				loc_z1 = Double.parseDouble(z1Raw);
				
				loc_x2 = Double.parseDouble(x2Raw);
				loc_y2 = Double.parseDouble(y2Raw);
				loc_z2 = Double.parseDouble(z2Raw);
			} catch(NullPointerException e) {
				Utility.tellConsole(ChatColor.RED+"Files were unsuccessfully synchronized [PipesLocs]! Attempting to correct data...");
				success = false;
			}
			
			
			MultiHeadUnit mhuFound = null;
			
			for(MultiHeadUnit mhu : Main.MHUList){
				if(mhu.getItemHU().getID().equals(mhuItemID)){
					mhuFound = mhu;
				}
			}
			
			
				
			if (mhuFound == null) {
				
				Utility.tellConsole(ChatColor.RED+"Files were unsuccessfully synchronized [PipeMHU]! Attempting to correct data...");
				success = false;
				
			}
			
			
			if(success){
				
				Location beginLoc = new Location(world, loc_x1, loc_y1, loc_z1);
				Location endLoc = new Location(world, loc_x2, loc_y2, loc_z2);
				
				Inventory beginInv = HUPipeManager.getInventoryConnection(beginLoc);
				Inventory endInv = HUPipeManager.getInventoryConnection(endLoc);
				
				if(beginLoc!=null&&endLoc!=null&&beginInv!=null&&endInv!=null) {
					
					HUPipe pipe = new HUPipe(beginLoc, endLoc, beginInv, endInv);
					pipe.setCompletedMHU(mhuFound);
					PipeList.add(pipe);
					
				} else {
					Utility.tellConsole(ChatColor.YELLOW+"Pipe ends not found! Breaking pipe to preserve functionality...");
					BreakBlock.MHUForceBreak(mhuFound, mhuFound.getCenterLocation(), true);
				}
				
			}
			
			
		}
		
		
		return PipeList;
	}
	
	
}
