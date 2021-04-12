package com.quzzar.server.dominions.zones;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.TraderLlama;
import org.bukkit.entity.Villager;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.DominionsMain;
import com.quzzar.server.dominions.TerritoryListener;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.misc.DiplomacyState;
import com.quzzar.server.dominions.upgrades.UpgradeType;
import com.quzzar.server.moreitems.items.special.events.PreVehiclePlaceEvent;

public class ZoneListener implements Listener {
	
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		boolean isSafeZone = DominionsMain.getZoneManager().isSafeClaimed(e.getBlock().getChunk());
		boolean isWarZone = DominionsMain.getZoneManager().isNeutralClaimed(e.getBlock().getChunk());

		if ((isSafeZone || isWarZone) && 
				!e.getPlayer().hasPermission("dominions.zone.bypass")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cYou can't break blocks here!");
		} 
	}




	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		boolean isSafeZone = DominionsMain.getZoneManager().isSafeClaimed(e.getBlock().getChunk());
		boolean isWarZone = DominionsMain.getZoneManager().isNeutralClaimed(e.getBlock().getChunk());

		if ((isSafeZone || isWarZone) && 
				!e.getPlayer().hasPermission("dominions.zone.bypass")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cYou can't place blocks here!");
		} 
	}




	@EventHandler
	public void onHangingBreak(HangingBreakByEntityEvent e) {
		if (e.getRemover() instanceof Player) {

			Player player = (Player)e.getRemover();

			boolean isSafeZone = DominionsMain.getZoneManager().isSafeClaimed(e.getEntity().getLocation().getChunk());
			boolean isWarZone = DominionsMain.getZoneManager().isNeutralClaimed(e.getEntity().getLocation().getChunk());

			if ((isSafeZone || isWarZone) && 
					!player.hasPermission("dominions.zone.bypass")) {
				e.setCancelled(true);
				player.sendMessage("§cYou can't break blocks here!");
			} 
		} 
	}






	@EventHandler
	public void onHangingPlace(HangingPlaceEvent e) {
		boolean isSafeZone = DominionsMain.getZoneManager().isSafeClaimed(e.getEntity().getLocation().getChunk());
		boolean isWarZone = DominionsMain.getZoneManager().isNeutralClaimed(e.getEntity().getLocation().getChunk());

		if ((isSafeZone || isWarZone) && 
				!e.getPlayer().hasPermission("dominions.zone.bypass")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cYou can't place blocks here!");
		} 
	}





	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Hanging) {

			boolean isSafeZone = DominionsMain.getZoneManager().isSafeClaimed(e.getRightClicked().getLocation().getChunk());
			boolean isWarZone = DominionsMain.getZoneManager().isNeutralClaimed(e.getRightClicked().getLocation().getChunk());

			if (isSafeZone || isWarZone)
			{
				if (!e.getPlayer().hasPermission("dominions.zone.bypass")) {
					e.setCancelled(true);
				}
			}
		} 
	}


	@EventHandler
	public void onBlockExplode(BlockExplodeEvent e) {
		boolean isSafeZone = DominionsMain.getZoneManager().isSafeClaimed(e.getBlock().getChunk());
		boolean isWarZone = DominionsMain.getZoneManager().isNeutralClaimed(e.getBlock().getChunk());

		if (isSafeZone || isWarZone) {
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void onVehiclePlace(PreVehiclePlaceEvent e) {
		boolean isSafeZone = DominionsMain.getZoneManager().isSafeClaimed(e.getBlockAgainst().getChunk());
		boolean isWarZone = DominionsMain.getZoneManager().isNeutralClaimed(e.getBlockAgainst().getChunk());

		if (isSafeZone || isWarZone) {
			e.setCancelled(true);
		}
	}
	
	

	@EventHandler
	public void onDamageEntity(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Hanging 
				|| e.getEntity() instanceof Villager
				|| e.getEntity() instanceof IronGolem
				|| e.getEntity() instanceof WanderingTrader
				|| e.getEntity() instanceof TraderLlama) {

			boolean isSafeZone = DominionsMain.getZoneManager().isSafeClaimed(e.getEntity().getLocation().getChunk());
			boolean isWarZone = DominionsMain.getZoneManager().isNeutralClaimed(e.getEntity().getLocation().getChunk());

			if (isSafeZone || isWarZone) {

				boolean cancel = true;

				if (e.getDamager() instanceof Player) {

					Player player = (Player)e.getDamager();

					if (player.hasPermission("dominions.zone.bypass")) {
						cancel = false;
					}
				} 


				e.setCancelled(cancel);
			} 
		} 
	}




	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			if (TerritoryListener.interactMaterials.contains(e.getClickedBlock().getType())) {

				boolean isSafeZone = DominionsMain.getZoneManager().isSafeClaimed(e.getClickedBlock().getChunk());
				boolean isWarZone = DominionsMain.getZoneManager().isNeutralClaimed(e.getClickedBlock().getChunk());

				if (isSafeZone || isWarZone) {
					if (!e.getPlayer().hasPermission("dominions.zone.bypass")) {
						e.setCancelled(true);
					}
				}
			}
			else if (TerritoryListener.containerMaterials.contains(e.getClickedBlock().getType())) {

				boolean isSafeZone = DominionsMain.getZoneManager().isSafeClaimed(e.getClickedBlock().getChunk());
				boolean isWarZone = DominionsMain.getZoneManager().isNeutralClaimed(e.getClickedBlock().getChunk());

				if (isSafeZone || isWarZone) {
					if (!e.getPlayer().hasPermission("dominions.zone.bypass")) {
						e.setCancelled(true);
					}
				}
			} 
		}
	}


	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerMove(PlayerMoveEvent e) {
		Chunk chunkFrom = e.getFrom().getChunk();
		Chunk chunkTo = e.getTo().getChunk();


		if (chunkFrom != chunkTo) {
			displayZone(e.getPlayer(), chunkTo, chunkFrom);
		}
	}




	public static void displayZone(Player player, Chunk chunkTo, Chunk chunkFrom) {
		Dominion dominionTo = DominionUtils.getDominionByChunk(chunkTo);
		Dominion dominionFrom = DominionUtils.getDominionByChunk(chunkFrom);

		ZoneType zoneTypeTo = DominionsMain.getZoneManager().getZoneType(chunkTo, dominionTo);
		ZoneType zoneTypeFrom = DominionsMain.getZoneManager().getZoneType(chunkFrom, dominionFrom);

		if (zoneTypeTo != zoneTypeFrom) {
			if (zoneTypeTo.equals(ZoneType.SafeZone)) {
				Utility.sendTitle(player, ChatColor.GREEN + "SafeZone");

				return;
			} 
			if (zoneTypeTo.equals(ZoneType.NeutralZone)) {
				Utility.sendTitle(player, ChatColor.DARK_GRAY + "Neutral Zone");

				return;
			} 
			if (zoneTypeTo.equals(ZoneType.Wilderness)) {
				Utility.sendTitle(player, ChatColor.DARK_AQUA + "Wilderness");

				return;
			}
		}
		
		if (dominionFrom != dominionTo) {
			dominionChange(player, dominionTo);
			return;
		} 
		
	}






	private static void dominionChange(Player player, Dominion newDominion) {
		Dominion playerDominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
		

		if (playerDominion != null) {

			DiplomacyState state = playerDominion.getDominionDiplomacy(newDominion);

			Utility.sendTitle(player, 
					state.getPrimaryColor() + newDominion.getName(), 
					state.getSecondaryColor() + newDominion.getDescription());
		} else {

			Utility.sendTitle(player, 
					DiplomacyState.Neutral.getPrimaryColor() + newDominion.getName(), 
					DiplomacyState.Neutral.getSecondaryColor() + newDominion.getDescription());
		} 




		DiplomacyState state = newDominion.getDiplomacy(player.getUniqueId());

		if (state.equals(DiplomacyState.You) || (
				newDominion.hasUpgrade(UpgradeType.SYNDICATE) && state.equals(DiplomacyState.Ally))) {

			if (newDominion.hasUpgrade(UpgradeType.COURAGE_T2)) {
				Utility.addPotionEffect(player, PotionEffectType.INCREASE_DAMAGE, 1, 90);
			} else if (newDominion.hasUpgrade(UpgradeType.COURAGE_T1)) {
				Utility.addPotionEffect(player, PotionEffectType.INCREASE_DAMAGE, 0, 60);
			} 

			if (newDominion.hasUpgrade(UpgradeType.GIFT_FROM_GODS)) {
				Utility.addPotionEffect(player, PotionEffectType.REGENERATION, 0, 120);
			}
		}
		else if (state.equals(DiplomacyState.Enemy)) {

			if (newDominion.hasUpgrade(UpgradeType.INTIMIDATION_T2)) {
				Utility.addPotionEffect(player, PotionEffectType.WEAKNESS, 1, 90);
			} else if (newDominion.hasUpgrade(UpgradeType.INTIMIDATION_T1)) {
				Utility.addPotionEffect(player, PotionEffectType.WEAKNESS, 0, 60);
			} 

			if (newDominion.hasUpgrade(UpgradeType.POISON_GAS))
				Utility.addPotionEffect(player, PotionEffectType.POISON, 0, 60); 
		} 
	}
}