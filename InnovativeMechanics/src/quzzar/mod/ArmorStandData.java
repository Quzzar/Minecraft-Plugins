package quzzar.mod;

import org.bukkit.entity.ArmorStand;

import quzzar.mod.Utilities.MHU_Utilities;

public class ArmorStandData {
	
	
	public static void serializeArmorStand(MultiHeadUnit mhu, ArmorStand as) {
		
		as.setFallDistance((float) MHU_Utilities.getSerializedID(mhu.getItemHU().getID()));
	}
	
	public static void setEnabled(ArmorStand as){
		
		if(!isEnabled(as)) {
			as.setFallDistance(as.getFallDistance()*-1);
		}
		
	}
	
	
	public static void setDisabled(ArmorStand as){
		
		if(isEnabled(as)) {
			as.setFallDistance(as.getFallDistance()*-1);
		}
		
	}
	
	public static boolean isEnabled(ArmorStand as){
		if(as.getFallDistance()>0){
			return true;
		}else{
			return false;
		}
	}
	
	public static double getID(ArmorStand as){
		return Math.abs(as.getFallDistance());
	}
	
	public static boolean isNatural(ArmorStand as){
		if(as.getFallDistance()==0 && !Utility.checkMechanical(as.getHelmet())){
			return true;
		}else{
			return false;
		}
	}
	
}
