package quzzar.mod.pipes;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PipeDesigner {
	
	private Player p;
	
	private HUPipe completedPipe = null;
	
	private Location beginLoc;
	
	private Location endLoc;

	
	public PipeDesigner(Player p){
		this.p = p;
	}

	public Player getPlayer() {
		return p;
	}
	
	
	public HUPipe getCompletedPipe() {
		return completedPipe;
	}
	
	
	public Location getEndLocation() {
		return endLoc;
	}

	public void setEndLocation(Location endLoc) {
		this.endLoc = endLoc;
	}

	public Location getBeginLocation() {
		return beginLoc;
	}

	public void setBeginLocation(Location beginLoc) {
		this.beginLoc = beginLoc;
	}
	
	
	public ArrayList<ArmorStand> complete(){
		
		ArrayList<ArmorStand> returnList = null;
		
		if(beginLoc!=null&&endLoc!=null){
			
			Inventory beginInv = HUPipeManager.getInventoryConnection(beginLoc);
			Inventory endInv = HUPipeManager.getInventoryConnection(endLoc);
			
			if(beginInv!=null&&endInv!=null) {
				
				completedPipe = new HUPipe(beginLoc, endLoc, beginInv, endInv);
				
				returnList = completedPipe.create();
				
			}
			
		}
		
		PipeDesignerManager.removeFromList(this);
		
		return returnList;
	}
	
}
