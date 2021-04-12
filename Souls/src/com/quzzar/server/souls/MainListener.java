package com.quzzar.server.souls;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;
import com.quzzar.server.souls.character.data.CharData;
import com.quzzar.server.souls.character.packets.display.PlayerDisplayProfile;
import com.quzzar.server.souls.death.DeathHandler;
import com.quzzar.server.souls.limbo.LimboInvBuilder;
import com.quzzar.server.souls.limbo.LimboWelcome;

public class MainListener implements Listener {
	
	private static HashMap<UUID, Integer> pvpKills = new HashMap<UUID, Integer>();
	
	// Calculates killValue (called per kill)
	public static int upPvpKillValue(UUID uuid) {
		if(pvpKills.get(uuid) == null) {
			int amt = 60;
			pvpKills.put(uuid, amt);
			return amt/30;
		} else {
			int currentValue = pvpKills.get(uuid);
			int newAmt = (int) (currentValue*2);
			pvpKills.put(uuid, newAmt);
			return newAmt/30;
		}
	}
	
	public static void runClock() {
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Souls.instance, new Runnable() {
			
			public void run() {
				
				// Reduce every player's killValue by 1 every minute
				Iterator<Map.Entry<UUID, Integer>> iterator = pvpKills.entrySet().iterator();
			    while (iterator.hasNext()) {
			        Map.Entry<UUID, Integer> entry = iterator.next();
			        //Bukkit.broadcastMessage("Kill Value - "+entry.getValue());
			        entry.setValue(entry.getValue()-1);
			        if(entry.getValue() < 0) {
			        	iterator.remove();
			        }
			    }
			    
			    // Reduce fame
			    for(CharData data : CharacterManager.getAllData().values()) {
			    	data.getFame().naturallyReduce();
			    }
			    
			    // Refresh all characters
			    for(Player p : Bukkit.getOnlinePlayers()) {
			    	PlayerCharacter pChar = CharacterManager.getCharacter(p);
			    	pChar.refresh();
			    }
				
			}
		
		}, 1200, 1200); // Every 1 minute
		
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Souls.instance, new Runnable() {
			
			public void run() {
				
				// Reduce all recharge hours
				for(CharData data : CharacterManager.getAllData().values()) {
					
					if(data.getSoul().inLimbo()) {
						data.getSoul().setRechargeHours(data.getSoul().getRechargeHours()-1);
						
						// If player is online, update limbo inventory
						Player p = Bukkit.getPlayer(data.getUUID());
						if(p != null) {
							LimboInvBuilder.drawLimboInv(p);
						}
						
					}
					
				}
				
				// Auto-save
				Utility.tellConsole("Autosaving...");
				CharacterManager.save();
				
			}
		
		}, 72000, 72000); // Every hour
		
		
	}
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		if(!e.getPlayer().hasPlayedBefore()) {
			// Give the player a character since it's their first time joining //
			CharacterManager.addCharData(p);
		}
		
		PlayerCharacter pChar = CharacterManager.getCharacter(p);
		
		PlayerDisplayProfile.apply(pChar, false);
		
		if(pChar.getData().getSoul().inLimbo()) {
			
			if(p.getWorld() != Souls.getLimboWorld()) {
				p.teleport(Souls.getLimboWorld().getSpawnLocation());
				Utility.tellConsole(ChatColor.RED+p.getDisplayName()+" is set to be in limbo but wasn't in the limbo world!");
			}
			
			LimboWelcome.welcomePlayer(p);
			LimboWelcome.applyLimboFeatures(p);
			
			e.setJoinMessage("");
			
		} else {
			
			e.setJoinMessage(ChatColor.YELLOW+p.getDisplayName()+" joined the game");
			
		}
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Souls.instance, new Runnable() {
			public void run() {
				// Just in case, they logged while glowing
				p.setGlowing(false);
				
				// Refresh character display
				pChar.refresh();
			}
		}, 1);
		
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		
		e.setQuitMessage("");
		
	}
	
	 @EventHandler(priority = EventPriority.HIGHEST)
	 public void onPlayerDeath(PlayerDeathEvent e) {
		 if(e.isCancelled()) {
			 return;
		 }
		 
		// Core Changes
		e.setDroppedExp(0);
		e.setKeepLevel(true);
		e.setKeepInventory(true);
		e.getDrops().clear();
		
		if(e.getEntity().getWorld().getEnvironment().equals(Environment.NETHER)) {
			 DeathHandler.netherDeath(e);
		 } else if(e.getEntity().getWorld().getEnvironment().equals(Environment.THE_END)) {
			 DeathHandler.endDeath(e);
		 } else {
			 DeathHandler.overworldDeath(e);
		 }
		 
	 }
	 
	 @EventHandler
	 public void onPlayerExpChange(PlayerExpChangeEvent e) {
		 e.setAmount(0);
	 }
	 
}
