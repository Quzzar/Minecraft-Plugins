package com.quzzar.server.moreitems.items.special;

import java.util.SplittableRandom;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.EntityDataTag;
import com.quzzar.craftmeta.tags.ItemDataTag;
import com.quzzar.server.moreitems.ItemUtils;
import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;
import com.quzzar.server.moreitems.items.special.baton.SearchHandler;
import com.quzzar.server.moreitems.items.special.baton.SearchingObject;
import com.quzzar.server.moreitems.items.special.elytras.ElytraListener;

public class SpecialListener implements Listener {
	
	/////////////////////////////////////////
	//////// Using Security Baton ///////////
	/////////////////////////////////////////
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		ItemStack mainHand = p.getInventory().getItemInMainHand();

		if (ItemManager.isSimilar(mainHand, ItemType.SECURITY_BATON) && e.getRightClicked() instanceof Player) {
			Player otherP = (Player) e.getRightClicked();

			SearchHandler.addSearchingPlayer(p, otherP);

			return;
		}
	}

	///////////////////////////////////////////////////////////
	//////// Cancel the search if the player moves ////////////
	///////////////////////////////////////////////////////////
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		
		if (!SearchHandler.searchingPlayers.isEmpty() 
				&& CraftMeta.entityHasData(e.getPlayer(), EntityDataTag.SEARCHING) 
				&& e.getFrom().distance(e.getTo()) > 0.1) {
			
			boolean searching = (boolean) CraftMeta.entityGet(e.getPlayer(), EntityDataTag.SEARCHING);
			if(searching) {
				for (SearchingObject search : SearchHandler.searchingPlayers) {
					if (!search.isMoved() && search.getPlayer().equals(e.getPlayer())) {
						search.setMoved(true);
						search.getPlayer().sendMessage(ChatColor.RED + "You moved! Searching cancelled!");
						search.getOtherPlayer().sendMessage(
								ChatColor.RED + search.getPlayer().getDisplayName() + " attempted to search you!");
						return;
					}
				}
			}
		}
		
	}

	/////////////////////////////////////////
	//////// Using Grappling Hook ///////////
	/////////////////////////////////////////
	@EventHandler
	public void onPlayerGrapple(PlayerFishEvent e) {
		ItemStack item = getGrapplingHook(e.getPlayer());
		if(item == null) {
			return;
		}
		if (e.getState() == PlayerFishEvent.State.IN_GROUND 
				|| e.getState() == PlayerFishEvent.State.CAUGHT_ENTITY) {
			
			e.getPlayer().setVelocity(e.getHook().getLocation().toVector()
					.subtract(e.getPlayer().getLocation().toVector()).multiply(0.2));
			
			// If it is an infinite grappling hook, remove any damage applied
			if(ItemUtils.areSimilarNoDamage(item, ItemManager.getItemClone(ItemType.GRAPPLING_HOOK_INFINITE))) {
				Damageable damage = (Damageable) item.getItemMeta();
				damage.setDamage(0);
				item.setItemMeta((ItemMeta)damage);
			}
			
		}
	}
	
	private ItemStack getGrapplingHook(Player player) {
		
		ItemStack mainHand = player.getInventory().getItemInMainHand();
		ItemStack offHand = player.getInventory().getItemInOffHand();
		
		if(mainHand != null && CraftMeta.itemHasData(mainHand, ItemDataTag.GRAPPLING_HOOK)) {
			return mainHand;
		} else if(offHand != null && CraftMeta.itemHasData(offHand, ItemDataTag.GRAPPLING_HOOK)) {
			return offHand;
		} else {
			return null;
		}
		
	}
	
	////////////////////////////////////////////
	//////// Using Bottle of Apeiron ///////////
	////////////////////////////////////////////
	@EventHandler
	public void onExpBottleBreak(ExpBottleEvent e) {
		
		Location loc = getLocation(e);
		if(loc == null) {return;}
		
		
		SplittableRandom splitRand = new SplittableRandom();
		
		if(splitRand.nextDouble() < 0.02) {
			
			if(splitRand.nextDouble() < 0.5) {
				
				loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.BEDROCK));
				
			} else {
				
				loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.VILLAGER_SPAWN_EGG));
				
			}
			
		} else {
			
			try {
				loc.getWorld().spawnEntity(loc, EntityType.values()[splitRand.nextInt(EntityType.values().length)]);
			} catch (Exception exception) {}
			
		}
		
	}
	
	private Location getLocation(ExpBottleEvent e) {
		if(e.getHitBlock() != null) {
			return e.getHitBlock().getRelative(e.getHitBlockFace()).getLocation();
		} else {
			if(e.getEntity() != null) {
				return e.getEntity().getLocation();
			} else {
				return null;
			}
		}
	}
	
	
	///
	
	
	@EventHandler
	public void onPlayerSneak(PlayerToggleSneakEvent e) {
		
		ElytraListener.onPlayerSneak(e);

	}
	
	@EventHandler
	public void onPlayerJump(PlayerJumpEvent e) {
		
		ElytraListener.onPlayerJump(e);
		
	}
	
}
