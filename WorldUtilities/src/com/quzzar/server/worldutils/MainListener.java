package com.quzzar.server.worldutils;

import java.util.SplittableRandom;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.destroystokyo.paper.event.entity.PreCreatureSpawnEvent;
import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.InvDataTag;
import com.quzzar.server.worldutils.mobs.mobdrops.DropManager;
import com.quzzar.server.worldutils.showcoords.ShowCoordsManager;

import api.anvilgui.AnvilGUI;

public class MainListener implements Listener {

	private static SplittableRandom splitRand = new SplittableRandom();
	
	//////////////////////////////////////////
	//////// Prevent block abusing ///////////
	//////////////////////////////////////////
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPreventPlace(BlockPlaceEvent e) {

		if (e.isCancelled()) {

			if (!e.getPlayer().isOnGround()) {

				Location pl = e.getPlayer().getLocation();

				if (e.getBlock().getLocation().getBlockX() == pl.getBlockX()
						&& e.getBlock().getLocation().getBlockZ() == pl.getBlockZ()) {

					if (e.getBlock().getLocation().getBlockY() < pl.getBlockY()) {

						e.getPlayer().teleport(new Location(pl.getWorld(), pl.getX(), pl.getY() - 1, pl.getZ(),
								pl.getYaw(), pl.getPitch()));

					}

				}

			}

		}

	}

	/////////////////////////////////////////
	//////// Sand Can Drop Shells ///////////
	/////////////////////////////////////////
	@EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent e) {
        if(!e.isCancelled()) {
        	if(e.getBlock().getType().equals(Material.SAND)) {
        		if(4 > splitRand.nextInt(100)) {
        			e.getBlock().getWorld().dropItemNaturally(
        					e.getBlock().getLocation().add(0.5, 0.5, 0.5), new ItemStack(Material.NAUTILUS_SHELL));
        			e.setDropItems(false);
        		}
        	}
        }
    }

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {

		Player p = e.getPlayer();
		ItemStack mainHand = p.getInventory().getItemInMainHand();
		
		if(mainHand == null) {
			return;
		}
		
		if(!(e.getRightClicked() instanceof LivingEntity)) {
			return;
		}
		
		LivingEntity livEntity = (LivingEntity) e.getRightClicked();
		
		///////////////////////////////////////////////////////////////////////
		//////// Using of a notch apple on witch gets baby villager ///////////
		///////////////////////////////////////////////////////////////////////
		if (mainHand.getType().equals(Material.ENCHANTED_GOLDEN_APPLE) 
				&& livEntity.getType().equals(EntityType.WITCH)) {
			
			mainHand.subtract();
			
			Villager villager = (Villager) livEntity.getWorld().spawnEntity(livEntity.getLocation(), EntityType.VILLAGER);
			villager.setBaby();
			
			e.setCancelled(true);
			return;
			
		}
				
		/////////////////////////////////////////////////////////////////////////
		//////// Using of a nametag allows renaming any living entity ///////////
		/////////////////////////////////////////////////////////////////////////
		if (mainHand.getType().equals(Material.NAME_TAG)) {
			
			if (!livEntity.getType().equals(EntityType.VILLAGER)
					&& !livEntity.getType().equals(EntityType.ZOMBIE_VILLAGER)
					&& !livEntity.getType().equals(EntityType.WANDERING_TRADER)) {
				
				e.setCancelled(true);
				
				new AnvilGUI.Builder()
			    .onComplete((player, name) -> {
			    	
			    	livEntity.setCustomName(name);
			    	livEntity.setCustomNameVisible(true);
			    	mainHand.subtract();
			    	return AnvilGUI.Response.close();
			    	
			    })
			    .text("Name...")
			    .plugin(WorldUtilities.getInstance())
			    .open(e.getPlayer());
				
				return;
				
			}
			
		}
		
		////////////////////////////////////////////////////////
		//////// Using of Leads on any living entity ///////////
		////////////////////////////////////////////////////////
		
		if (mainHand.getType().equals(Material.LEAD) && mainHand.getAmount() == 1) {
			
			if (!livEntity.getType().equals(EntityType.HORSE)
					&& !livEntity.getType().equals(EntityType.MULE)
					&& !livEntity.getType().equals(EntityType.DONKEY)
					&& !livEntity.getType().equals(EntityType.PLAYER)) {
		
				e.setCancelled(true);
				mainHand.setAmount(0);
		
				Bukkit.getScheduler().runTaskLater(WorldUtilities.getInstance(), new Runnable() {
					@Override
					public void run() {
						livEntity.setLeashHolder(p);
					}
				}, 1L);
				
				return;
				
			}
			
		}
		
	}
	
	
	/////////////////////////////////////////////////////////////////////
	//////// Disables Interation with the Disabled Inventory  ///////////
	/////////////////////////////////////////////////////////////////////
	@EventHandler
    public void onInventoryClick(InventoryClickEvent e){
		Inventory inv = e.getInventory();
		if(inv!=null) {
			if(CraftMeta.invHasData(inv, InvDataTag.DISABLED)) {
				e.setCancelled(true);
			}
		}
	}
	
	
	/////////////////////////////////////////
	//////// Determine Mob Drops  ///////////
	/////////////////////////////////////////
	@EventHandler(priority = EventPriority.HIGH)
    public void onEntityDeath(EntityDeathEvent e){
		if(e.isCancelled()) {
			return;
		}
		if(e.getEntityType().equals(EntityType.PLAYER)) {
			return;
		} else {
			DropManager.entityDead(e);
		}
	}
	
	
	///////////////////////////////////////////
	//////// Determine What Spawns  ///////////
	///////////////////////////////////////////
	@EventHandler(priority = EventPriority.HIGH)
	public void onCreatureSpawn(PreCreatureSpawnEvent e){
		if(e.isCancelled()) {
			return;
		}
		if(e.getReason().equals(SpawnReason.SPAWNER)) {
			e.setCancelled(true); // Disable spawners from spawning mobs
			return;
		}
		//SpawningManager.naturalSpawn(e);
	}
	/*
	//////////////////////////////////////////////
	//////// Handling Mobs Targetting  ///////////
	//////////////////////////////////////////////
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityTarget(EntityTargetLivingEntityEvent e){
		if(e.isCancelled()) {
			return;
		}
		if(e.getEntity() instanceof LivingEntity && !(e.getEntity() instanceof Player)) {
			MobTargeting.entityTargetEntity(e);
		}
	}
	*/
	
	///////////////////////////////////////////////
	//////// Remove Player from Coords  ///////////
	///////////////////////////////////////////////
	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent e){
		ShowCoordsManager.setShowing(e.getPlayer(), false);
	}
	
}
