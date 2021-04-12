package com.quzzar.server.moreitems.items.special.boats;

import org.bukkit.Bukkit;
import org.bukkit.TreeSpecies;
import org.bukkit.entity.Boat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.util.Vector;

import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.EntityDataTag;
import com.quzzar.craftmeta.tags.ItemDataTag;
import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;
import com.quzzar.server.moreitems.items.special.events.PreVehiclePlaceEvent;

public class BoatListener {

	public static void onPlace(PreVehiclePlaceEvent e) {
		
		if(CraftMeta.itemHasData(e.getItemPlaced(), ItemDataTag.ADVANCED_BOAT)) {
			
			e.setCancelled(true);
			
			Boat boat = (Boat) e.getBlockAgainst().getWorld().spawnEntity(e.getPlacingLocation(), EntityType.BOAT);
			boat.setWoodType(TreeSpecies.REDWOOD);
			CraftMeta.entitySet(boat, EntityDataTag.ADVANCED_BOAT);
			
			e.getItemPlaced().subtract();
			
		}
		
	}
	
	public static void onDestroy(VehicleDestroyEvent e) {
		
		if(CraftMeta.entityHasData(e.getVehicle(), EntityDataTag.ADVANCED_BOAT)) {
			e.setCancelled(true);
			e.getVehicle().getWorld().dropItemNaturally(
					e.getVehicle().getLocation(), ItemManager.getItemClone(ItemType.ADVANCED_BOAT));
			e.getVehicle().remove();
		}
		
	}
	
	public static void onMove(VehicleMoveEvent e) {
		
		/*
		if(!CraftMeta.entityHasData(e.getVehicle(), EntityDataTag.ADVANCED_BOAT)) {
			return;
		}*/
		
		if(!e.getVehicle().getPassengers().isEmpty()) {
			if(e.getVehicle().getPassengers().get(0) instanceof Player) {
				
				Bukkit.broadcastMessage("Got here");
				
				e.getVehicle().setVelocity(e.getVehicle().getVelocity().multiply(1.3));
				
			}
		}
		
	}
	
	public static void onBlockCollide(VehicleBlockCollisionEvent e) {
		
		/*
		if(!CraftMeta.entityHasData(e.getVehicle(), EntityDataTag.ADVANCED_BOAT)) {
			return;
		}*/
		
		if(!e.getVehicle().getPassengers().isEmpty()) {
			if(e.getVehicle().getPassengers().get(0) instanceof Player) {
				
				Bukkit.broadcastMessage("Got here collide");
				
				e.getVehicle().setVelocity(e.getVehicle().getVelocity().add(new Vector(0, 22, 0)));
				
			}
		}
		
	}
	
}
