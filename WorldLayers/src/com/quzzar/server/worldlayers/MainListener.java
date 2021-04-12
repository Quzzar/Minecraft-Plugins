package com.quzzar.server.worldlayers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.world.PortalCreateEvent;

public class MainListener implements Listener {

	public static World overworld;
	public static World nether;
	public static World aether;
	public static World end;
	
	public static final double TP_OFFSET = 0.5;
	private static final double FLYING_OFFSET = 6;
	
	public static void init() {
		
		overworld = Bukkit.getWorld("world");
		nether = Bukkit.getWorld("world_nether");
		aether = Bukkit.getWorld("world_aether");
		end = Bukkit.getWorld("world_the_end");
		
		if(overworld == null) {
			WorldLayers.tellConsole(ChatColor.RED+"Failed to find the world 'world'!");
		}
		if(nether == null) {
			WorldLayers.tellConsole(ChatColor.RED+"Failed to find the world 'world_nether'!");
		}
		if(aether == null) {
			WorldLayers.tellConsole(ChatColor.RED+"Failed to find the world 'world_aether'!");
		}
		if(end == null) {
			WorldLayers.tellConsole(ChatColor.RED+"Failed to find the world 'world_end'!");
		}
		
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if(e.getTo().getY() > WorldLayers.NETHER_TP+TP_OFFSET
				&& e.getPlayer().getWorld().equals(nether)) {
			
			tpUp(e.getPlayer(), overworld, WorldLayers.OVERWORLD_TP_BOTTOM);
			return;
			
		}
		if(e.getTo().getY() < WorldLayers.OVERWORLD_TP_BOTTOM-TP_OFFSET
				&& e.getPlayer().getWorld().equals(overworld)) {
			
			tpDown(e.getPlayer(), nether, WorldLayers.NETHER_TP);
			return;
			
		}
		if(e.getTo().getY() > WorldLayers.OVERWORLD_TP_TOP+TP_OFFSET
				&& e.getPlayer().getWorld().equals(overworld)) {
			
			tpUp(e.getPlayer(), aether, WorldLayers.AETHER_TP_BOTTOM);
			return;
			
		}
		if(e.getTo().getY() < WorldLayers.AETHER_TP_BOTTOM-TP_OFFSET
				&& e.getPlayer().getWorld().equals(aether)) {
			
			tpDown(e.getPlayer(), overworld, WorldLayers.OVERWORLD_TP_TOP);
			return;
			
		}
		if(e.getTo().getY() > WorldLayers.AETHER_TP_TOP+TP_OFFSET
				&& e.getPlayer().getWorld().equals(aether)) {
			
			tpUp(e.getPlayer(), end, WorldLayers.END_TP);
			return;
			
		}
		if(e.getTo().getY() < WorldLayers.END_TP-TP_OFFSET
				&& e.getPlayer().getWorld().equals(end)) {
			
			tpDown(e.getPlayer(), aether, WorldLayers.AETHER_TP_TOP);
			return;
			
		}
	}
	
	@EventHandler
	public void onPlayerSneak(PlayerToggleSneakEvent e) {
		if(e.getPlayer().getLocation().getBlockY() == WorldLayers.OVERWORLD_TP_BOTTOM 
				&& e.getPlayer().getWorld().equals(overworld)) {
			
			tpDown(e.getPlayer(), nether, WorldLayers.NETHER_TP);
			return;
			
		}
		if(e.getPlayer().getLocation().getBlockY() == WorldLayers.AETHER_TP_BOTTOM 
				&& e.getPlayer().getWorld().equals(aether)) {
			
			tpDown(e.getPlayer(), overworld, WorldLayers.OVERWORLD_TP_TOP);
			return;
			
		}
		if(e.getPlayer().getLocation().getBlockY() == WorldLayers.END_TP 
				&& e.getPlayer().getWorld().equals(aether)) {
			
			tpDown(e.getPlayer(), aether, WorldLayers.AETHER_TP_TOP);
			return;
			
		}
	}
	
	private static void tpDown(Player p, World worldTo, int worldTpY) {
		
		Location pLoc = p.getLocation();
		Location worldLoc = new Location(worldTo, pLoc.getX(), 
				worldTpY, pLoc.getZ(), pLoc.getYaw(), pLoc.getPitch());
		
		Block tpLoc = worldLoc.getBlock();
		Block tpAbove = worldLoc.getBlock().getRelative(BlockFace.UP);
		
		if(tpLoc.getType().isSolid()) {
			tpLoc.setType(Material.AIR);
		}
		if(tpAbove.getType().isSolid()) {
			tpAbove.setType(Material.AIR);
		}
		
		
		if(p.getVehicle() != null) {
			
			Entity vehicle = p.getVehicle();
			vehicle.eject();
			vehicle.teleport(worldLoc);
			p.teleport(worldLoc);
			vehicle.addPassenger(p);
			
		} else {
			
			p.teleport(worldLoc);
			
		}
		
	}
	
	private static void tpUp(Player p, World worldTo, int worldTpY) {
		
		Location pLoc = p.getLocation();
		Location worldLoc = new Location(worldTo, pLoc.getX(), 
				worldTpY, pLoc.getZ(), pLoc.getYaw(), pLoc.getPitch());
		
		FlyingState state = getFlying(p);
		if(state != FlyingState.NONE) {
			worldLoc.add(0, FLYING_OFFSET, 0);
			if(state == FlyingState.ELYTRA) {
				p.setGliding(true);
			}
		} else {
			
			Block tpLoc = worldLoc.getBlock();
			Block tpAbove = worldLoc.getBlock().getRelative(BlockFace.UP);
			Block tpBelow = worldLoc.getBlock().getRelative(BlockFace.DOWN);
			
			if(tpLoc.getType().isSolid()) {
				tpLoc.setType(Material.AIR);
			}
			if(tpAbove.getType().isSolid()) {
				tpAbove.setType(Material.AIR);
			}
			
			if(!tpBelow.getType().isSolid()) {
				tpBelow.setType(Material.STONE);
			}
			
		}
		
		
		if(p.getVehicle() != null) {
			
			Entity vehicle = p.getVehicle();
			vehicle.eject();
			vehicle.teleport(worldLoc);
			p.teleport(worldLoc);
			vehicle.addPassenger(p);
			
		} else {
			
			p.teleport(worldLoc);
			
		}
		
	}
	
	
	
	////////////////////////////////////////
	//////// Disable Making Portals ////////
	////////////////////////////////////////
	@EventHandler
    public void onPortalContruction(PortalCreateEvent e) {
		e.setCancelled(true);
    }
	
	
	private static FlyingState getFlying(Player p) {
		if(p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
			return FlyingState.CREATIVE;
		}
		if(p.getInventory().getChestplate() != null) {
			if(p.getInventory().getChestplate().getType() == Material.ELYTRA) {
				return FlyingState.ELYTRA;
			} else {
				return FlyingState.NONE;
			}
		} else {
			return FlyingState.NONE;
		}
	}
	
	
	
	
	
	
}
