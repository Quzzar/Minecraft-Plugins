package quzzar.mod.blocks;

import org.bukkit.Location;
import com.quzzar.im.versioncontrol.VersionControl;
import com.quzzar.im.versioncontrol.global.sound.GSound;

import quzzar.mod.MultiHeadUnit;
import quzzar.mod.Textures.TextureCollection;
import quzzar.mod.Utilities.MHU_Utilities;

public class PoweredMHUs {

	
	
	public static boolean isOn(MultiHeadUnit mhu){
		if(mhu.getVariable2().equalsIgnoreCase("on")){
			return true;
		}else{
			return false;
		}
	}
	
	public static void turnOn(MultiHeadUnit mhu, Location loc, TextureCollection texColOn){
		mhu.setVariable2("on");
		loc.getWorld().playSound(loc, VersionControl.getSound(GSound.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON), 2.0f, 1);
		MHU_Utilities.changeLook(mhu, texColOn);
	}
	
	public static void turnOff(MultiHeadUnit mhu, Location loc, TextureCollection texColOff){
		mhu.setVariable2("off");
		loc.getWorld().playSound(loc, VersionControl.getSound(GSound.BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF), 2.0f, 1);
		MHU_Utilities.changeLook(mhu, texColOff);
	}
	
	public static void toggle(MultiHeadUnit mhu, Location loc, TextureCollection texColOn, TextureCollection texColOff){
		if(isOn(mhu)){
			turnOff(mhu, loc, texColOff);
		}else{
			turnOn(mhu, loc, texColOn);
		}
	}
	
	
	public static boolean isMHUPowerable(MultiHeadUnit mhu){
		if(mhu.getItemHU().getType().isPowerable()){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	public static void turnOnSoundless(MultiHeadUnit mhu, TextureCollection texColOn){
		mhu.setVariable2("on");
		MHU_Utilities.changeLook(mhu, texColOn);
	}
	
	public static void turnOffSoundless(MultiHeadUnit mhu, TextureCollection texColOff){
		mhu.setVariable2("off");
		MHU_Utilities.changeLook(mhu, texColOff);
	}
	
	
}
