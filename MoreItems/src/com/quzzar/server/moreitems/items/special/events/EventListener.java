package com.quzzar.server.moreitems.items.special.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		if(e.getClickedBlock() != null && e.getItem() != null) {
			
			if(Tag.ITEMS_BOATS.isTagged(e.getItem().getType())) {
				PreVehiclePlaceEvent event = new PreVehiclePlaceEvent(e.getClickedBlock(), e.getBlockFace(),
						e.getItem(), VehicleType.BOAT, e.getPlayer());
				Bukkit.getPluginManager().callEvent(event);
				if(event.isCancelled()) {e.setCancelled(true);}
			} else if (e.getItem().getType() == Material.MINECART) {
				PreVehiclePlaceEvent event = new PreVehiclePlaceEvent(e.getClickedBlock(), e.getBlockFace(), 
						e.getItem(), VehicleType.MINECART, e.getPlayer());
				Bukkit.getPluginManager().callEvent(event);
				if(event.isCancelled()) {e.setCancelled(true);}
			}
			
		}
		
	}
	
	
}
