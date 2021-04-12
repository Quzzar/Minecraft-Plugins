package com.quzzar.server.worldutils.mobs.mobspawning;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class MobTargeting {

	///////////////////////////////////////////////////////////
	// If rider is targeting something, mount target as well //
	///////////////////////////////////////////////////////////
	public static void entityTargetEntity(EntityTargetLivingEntityEvent e) {
		
		LivingEntity entity = (LivingEntity) e.getEntity();
		
		if(entity.isInsideVehicle()) {
			
			if(entity.getVehicle() instanceof Mob) {
				Mob ridingOn = (Mob) entity.getVehicle();
				
				ridingOn.setTarget(e.getTarget());
				
			}
			
		}
		
	}
	
}
