package quzzar.mod.thedark.structures;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public abstract class DarkStructure {

	private List<PotentialBlock> blockList = new ArrayList<PotentialBlock>();
	
	public DarkStructure() {
		fillBlockList();
	}
	
	abstract void fillBlockList();
	
	public void createStructure(Location centerLoc){
		
		for(PotentialBlock pb : blockList) {
			
			Location bLoc = centerLoc.clone();
			
			bLoc.add(pb.getX(), pb.getY(), pb.getZ());
			
			bLoc.getBlock().setType(pb.getMaterial());
			
		}
		
	}
	
	public List<PotentialBlock> getBlockList(){
		return blockList;
	}
	
}
