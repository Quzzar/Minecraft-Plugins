package com.quzzar.server.dominions;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.InvDataTag;
import com.quzzar.server.dominions.misc.DiplomacyState;
import com.quzzar.server.dominions.misc.PlayerRank;
import com.quzzar.server.dominions.misc.Teleporting;
import com.quzzar.server.dominions.settings.SettingsManager;
import com.quzzar.server.dominions.settings.chunk.IntroChunkSettings;
import com.quzzar.server.dominions.settings.chunk.PlayerChunkSettings;
import com.quzzar.server.dominions.upgrades.UpgradesManager;
import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.data.CharData;

public class MainListener implements Listener {
	
	public static void runClock() {
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(DominionsMain.instance, new Runnable() {
			
			public void run() {
				
				for(Dominion dominion : DominionManager.getDominions()) {
					// Clears all invites
					dominion.getInvites().clear();
					
					// Set fame minimum for leaders
					ArrayList<UUID> leaders = DominionUtils.getRankUUIDs(dominion, PlayerRank.Leader);
					for(UUID leaderUUID : leaders) {
						double minimum = 10+(3.5*dominion.getMemberCount())/leaders.size();
						CharData cData = CharacterManager.getCharacterData(leaderUUID);
						cData.getFame().setMinFame(minimum);
					}
					// Set fame minimum for generals
					ArrayList<UUID> generals = DominionUtils.getRankUUIDs(dominion, PlayerRank.General);
					for(UUID generalUUID : generals) {
						double minimum = 5+(1.5*dominion.getMemberCount())/generals.size();
						CharData cData = CharacterManager.getCharacterData(generalUUID);
						cData.getFame().setMinFame(minimum);
					}
				}
				
			}
		
		}, 6000, 6000); // Every 5 minutes
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(DominionsMain.instance, new Runnable() {
			
			public void run() {
				
				// Auto-save
				Utility.tellConsole("Autosaving...");
				DominionsMain.getDominionManagerInstance().save();
				
				// Clearing the teleporting list
				Teleporting.teleportingList.clear();
				
			}
		
		}, 36000, 36000); // Every 30 minutes
		
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(DominionsMain.instance, new Runnable() {
			
			public void run() {
				
				// Tax all dominions and handle perishing
				Taxing.callTax();
				
			}
		
		}, 72000, 72000); // Every 1 hour
		
	} 
	
	@EventHandler
    public void onInventoryClick(InventoryClickEvent e){
		
		Inventory inv = e.getInventory();
		
		if(inv!=null) {
			if(CraftMeta.invHasData(inv, InvDataTag.DOM_SETTINGS)) {
				
				e.setCancelled(true);
				
				if(e.getClickedInventory() == inv) {
					SettingsManager.updateSettings(
							e.getSlot(), e.getWhoClicked(), inv);
				}
				
			} else if(CraftMeta.invHasData(inv, InvDataTag.DOM_UPGRADES)) {
				
				e.setCancelled(true);
				
				if(e.getClickedInventory() == inv) {
					UpgradesManager.updateUpgrades(
							e.getSlot(), e.getWhoClicked(), inv);
				}
				
			} else if(CraftMeta.invHasData(inv, InvDataTag.DOM_CHUNK_SETTINGS_INTRO)) {
				
				e.setCancelled(true);
				
				if(e.getClickedInventory() == inv) {
					IntroChunkSettings.update(
							e.getSlot(), e.getWhoClicked(), inv);
				}
				
			} else if(CraftMeta.invHasData(inv, InvDataTag.DOM_CHUNK_SETTINGS_PLAYER)) {
				
				e.setCancelled(true);
				
				if(e.getClickedInventory() == inv) {
					PlayerChunkSettings.update(
							e.getSlot(), e.getWhoClicked(), inv, e.getView().getTitle());
				}
				
			}
		}
	}
	
	@EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e){
		
		Player attackerP = null;
		Player defenderP = null;
		
		if(e.getEntity() instanceof Player) {
			
			defenderP = (Player) e.getEntity();
			
		}
		
		if(e.getDamager() instanceof Player) {
			
			attackerP = (Player) e.getDamager();
			
		} else if (e.getDamager() instanceof Projectile) {
            if (((Projectile) e.getDamager()).getShooter() instanceof Player) {
                
            	attackerP = (Player) ((Projectile) e.getDamager()).getShooter();
            	
            }
        }
		
		if(attackerP!=null && defenderP!=null) {
			
			Dominion attackerDom = DominionUtils.getDominionByPlayer(attackerP.getUniqueId());
			
			if(attackerDom!=null) {
				
				DiplomacyState state = attackerDom.getDiplomacy(defenderP.getUniqueId());
				
				// Are they in the same dominion or allies?
				if(state.equals(DiplomacyState.Ally) || state.equals(DiplomacyState.You)) {
					e.setCancelled(true);
					return;
				}
				
			}
			
			
			// If they're in a safe zone, prevent as well.
			boolean attInSafeZone = DominionsMain.getZoneManager().isSafeClaimed(attackerP.getLocation().getChunk());
			boolean defInSafeZone = DominionsMain.getZoneManager().isSafeClaimed(defenderP.getLocation().getChunk());
			
			if(attInSafeZone || defInSafeZone) {
				e.setCancelled(true);
				return;
			}
			
		}
	}
	
}
