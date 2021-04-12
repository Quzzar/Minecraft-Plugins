package com.quzzar.server.worldutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.DominionsMain;
import com.quzzar.server.dominions.zones.ZoneType;
import com.quzzar.server.souls.Souls;

public class WorldListener implements Listener{
	
	private static List<Material> replaceWithSapling = Arrays.asList(Material.AIR, Material.GRASS);
	private static List<Material> placeSaplingOn = Arrays.asList(Material.GRASS_BLOCK, Material.DIRT,
			Material.COARSE_DIRT, Material.PODZOL);
	
	@EventHandler
	public void onItemDespawn(ItemDespawnEvent e) {

		// If de-spawning item is sapling, the item is located in an air block, 
		// and the block below it is grass, set block to sapling.
		
		Dominion dominion = DominionUtils.getDominionByChunk(e.getLocation().getChunk());
		ZoneType zoneType = DominionsMain.getZoneManager().getZoneType(e.getLocation().getChunk(), dominion);
		
		if(zoneType == ZoneType.Wilderness) {
			
			if(Tag.SAPLINGS.isTagged(e.getEntity().getItemStack().getType())
					&& replaceWithSapling.contains(e.getLocation().getBlock().getType())
					&& placeSaplingOn.contains(e.getLocation().getBlock().getRelative(BlockFace.DOWN).getType())) {
				
				ItemStack saplingItem = e.getEntity().getItemStack();
				e.getLocation().getBlock().setBlockData(Bukkit.createBlockData(saplingItem.getType()));
				
			}
			
		}

	}
	
	// Players respawn at mainworld respawn
	@EventHandler(priority = EventPriority.MONITOR)
	public void onRespawn(PlayerRespawnEvent e) {
		if(!e.isBedSpawn()) {
			e.setRespawnLocation(WorldUtilities.getMainWorld().getSpawnLocation());
		} else {
			e.setRespawnLocation(e.getPlayer().getBedSpawnLocation());
		}
	}
	
	// Disable normal explosions from breaking blocks
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent e) {
		e.blockList().clear();
	}
	
	// Allow sleeping in nether / end
	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent e) {
		if(e.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.NOT_POSSIBLE_HERE) {
			e.setUseBed(Event.Result.ALLOW);
		}
		if(e.getPlayer().getWorld().equals(Souls.getLimboWorld())) {
			e.setCancelled(true);
			e.setUseBed(Event.Result.DENY);
		}
	}
	
	// Disable fire spread, chorus plant grow in not end
	@EventHandler
    public void onBlockSpread(BlockSpreadEvent e) {
        if(e.getSource().getType().equals(Material.FIRE)) {
        	e.setCancelled(true);
        	return;
        }
        if(!e.getSource().getWorld().equals(WorldUtilities.getEndWorld()) 
        		&& (e.getSource().getType().equals(Material.CHORUS_FLOWER) || e.getSource().getType().equals(Material.CHORUS_PLANT))) {
        	e.setCancelled(true);
        	return;
        }
    }
	
	@EventHandler
    public void onBlockBurn(BlockBurnEvent e) {
		e.setCancelled(true);
    }
	
	// Disable totem of undying
	@EventHandler
    public void onEntityResurrect(EntityResurrectEvent e) {
        e.setCancelled(true);
    }
	
	// Drinking milk only removes one effect at random, instead of all
	@EventHandler
    public void onConsume(PlayerItemConsumeEvent e){
		
		if(!e.isCancelled()) {
			
			if(e.getItem().getType().equals(Material.MILK_BUCKET)) {
				
				Player player = e.getPlayer();
				SplittableRandom splitRand = new SplittableRandom();
				
				ArrayList<PotionEffect> potionEffects = new ArrayList<>(player.getActivePotionEffects());
				PotionEffect randomEffect = potionEffects.get(splitRand.nextInt(potionEffects.size()));
				
				potionEffects.remove(randomEffect);
				
				 Bukkit.getScheduler().scheduleSyncDelayedTask(WorldUtilities.getInstance(), new Runnable() {
					 @Override
					 public void run() {
						 player.addPotionEffects(potionEffects);
					 }
				 }, 1);
				
			}
			
		}
		
	}
	
	//private static SplittableRandom splitRand = new SplittableRandom();
	
	// ADD GRAVITY TO PHYSICS BLOCKS (ONLY IN MAIN WORLD)
	/*
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockUpdate(BlockPhysicsEvent e) {
		if(e.isCancelled() || !e.getBlock().getWorld().equals(WorldUtilities.getMainWorld())) {
			return;
		}
		if(0.85 > splitRand.nextDouble()) {
			PhysicsBlocks.update(e.getBlock());
		}
	}
	  
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockPlace(BlockPlaceEvent e) {
		if(!e.isCancelled() && e.getBlock().getWorld().equals(WorldUtilities.getMainWorld())) {
			PhysicsBlocks.update(e.getBlock());
		}
	}
	*/
	////
	
	
}
