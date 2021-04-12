package quzzar.mod.blocks;

import quzzar.mod.HeadUnit;

public class PoweredHUs {
	
	public static boolean isOn(HeadUnit hu){
		if(hu.getVar()==0){
			return true;
		}else{
			return false;
		}
	}
	
	public static void saveCurrent(HeadUnit hu, int oldCurrent){
		hu.setVar(oldCurrent);
	}
	
	
}
