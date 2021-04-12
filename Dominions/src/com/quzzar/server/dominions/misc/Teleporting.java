package com.quzzar.server.dominions.misc;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.quzzar.server.dominions.DominionsMain;
import com.quzzar.server.dominions.zones.ZoneListener;

public class Teleporting implements Listener{

	public static ArrayList<UUID> teleportingList = new ArrayList<UUID>();
	public static int delay = 10; // Delay in seconds
	
	public static boolean teleport(Player player, Location location) {
		
		if(!teleportingList.contains(player.getUniqueId())) {
			
			// Store player UUID and whether they will teleport
			teleportingList.add(player.getUniqueId());
	        player.sendMessage(
	        		ChatColor.GREEN+"Teleportation will commence in "+
	        		ChatColor.GOLD+delay+" seconds"+ChatColor.GREEN+", don't move...");
	        
	        
	        Bukkit.getScheduler().scheduleSyncDelayedTask(DominionsMain.instance, new Runnable() {
	            @Override
	            public void run() {
	                if (teleportingList.contains(player.getUniqueId())) {
	                	// Play sound for where they're leaving from
	            		player.getWorld().playSound(player.getLocation(),
	            				Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1f, 1f);
	                	player.teleport(location);
	                	// Play sound for where they're coming to
	            		player.getWorld().playSound(player.getLocation(),
	            				Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1f, 1f);
	                	teleportingList.remove(player.getUniqueId());
	                	ZoneListener.displayZone(player, player.getLocation().getChunk(), null);
	                }
	            }
	        }, 20 * delay);
			
	        return true;
	        
		} else {
			player.sendMessage(ChatColor.RED+"You're already being teleported!");
			return false;
		}
		
	}
	
	@EventHandler (priority = EventPriority.LOW)
    public void onMove(PlayerMoveEvent e) {
		
        if(teleportingList.contains(e.getPlayer().getUniqueId()) && e.getFrom().distance(e.getTo())>0.1) {
			
        	teleportingList.remove(e.getPlayer().getUniqueId());
        	e.getPlayer().sendMessage(ChatColor.RED+"You moved! Teleportation cancelled.");
			
		}
        
    }
	
}
