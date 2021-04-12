package quzzar.mod.pipes;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import quzzar.mod.ItemsList;
import quzzar.mod.MultiHeadUnit;
import quzzar.mod.Utility;

public class HUPipe {

	
	private final Vector vec;
	private final Location loc2;
	
	private final Location beginLoc;
	private final Location endLoc;
	
	
	private final Inventory beginInv;
	private final Inventory endInv;
	
	
	private MultiHeadUnit completedMHU = null;
	
	
	
	public HUPipe (Location beginLoc, Location endLoc, Inventory beginInv, Inventory endInv){
		
		Location loc1 = beginLoc.clone();
		loc1.setY(loc1.getY()-0.83);
		
		Location loc2 = endLoc.clone();
		loc2.setY(loc2.getY()-0.83);
		
		
		this.vec = loc1.toVector().subtract(loc2.toVector());
		this.loc2 = loc2;
		
		this.beginLoc = beginLoc;
		this.endLoc = endLoc;
		
		this.beginInv = beginInv;
		this.endInv = endInv;
		
		
	}
	
	
	
	
	public void setCompletedMHU(MultiHeadUnit completedMHU){
		this.completedMHU = completedMHU;
	}
	
	public MultiHeadUnit getCompletedMHU(){
		return completedMHU;
	}
	
	public Inventory getBeginInventory(){
		return beginInv;
	}
	
	public Inventory getEndInventory(){
		return endInv;
	}
	
	
	public Location getBeginLocation(){
		return beginLoc;
	}
	
	public Location getEndLocation(){
		return endLoc;
	}
	
	
	public ArrayList<ArmorStand> create(){
		
		ArrayList<ArmorStand> asList = new ArrayList<ArmorStand>();
		
		if(vec.length()<=8){
			
			
			Vector v = vec.clone().multiply(0.19*(1/vec.length())); // Approx distance between each small Armor Stand
			
			
			for(double n=0; n<vec.length()*10; n++){
				
				if(v.clone().length()*n*2.1>=vec.length()){ // When to cut off loop (before reaching max). Gives a fairly good approximation.
					return asList; //2.6
				}
				
				ArmorStand as;
				if(n==0){
					as = Utility.createSmallArmorStand(loc2.add(v.clone().add(v.clone())), ItemsList.PIPE_BLOCK_END(1));
				}else{
					as = Utility.createSmallArmorStand(loc2.add(v.clone().add(v.clone())), ItemsList.PIPE_BLOCK(1));
				}
				
				as.setHeadPose(convertCoordsToEulerAngle(vec.getX(),vec.getY(),vec.getZ()));
				
				
				asList.add(as);
				
	        }
			
			
			
			return asList; // Basically shouldn't ever return here
		}
		
		return null;
		
	}
	
	
	
	public static EulerAngle convertCoordsToEulerAngle(double x, double y, double z) {
		
		double pitch = Math.atan(y/z)+Math.PI*0.5;
		
		double yaw = Math.atan(z/x)+Math.PI*0.5;
		
		return new EulerAngle(pitch, yaw, 0);
		
	}
	
	
}
