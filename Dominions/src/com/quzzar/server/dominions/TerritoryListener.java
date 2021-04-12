package com.quzzar.server.dominions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.quzzar.server.dominions.misc.ChunkSettingState;
import com.quzzar.server.dominions.settings.SettingType;
import com.quzzar.server.dominions.settings.chunk.ChunkSettingType;

public class TerritoryListener implements Listener{
	
	public static ArrayList<Material> interactMaterials = new ArrayList<Material>();
	public static List<Material> containerMaterials = 
			Arrays.asList(Material.CHEST, Material.TRAPPED_CHEST, Material.BEACON, Material.DROPPER, 
					Material.DISPENSER, Material.FURNACE, Material.HOPPER);
	
	public static void init() {
		
		interactMaterials.addAll(Tag.BUTTONS.getValues());
		interactMaterials.addAll(Tag.DOORS.getValues());
		interactMaterials.addAll(Tag.TRAPDOORS.getValues());
		
	}
	
	
	@EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockBreakEvent e){
		
		Chunk chunk = e.getBlock().getChunk();
		UUID pUUID = e.getPlayer().getUniqueId();
		Dominion currentDominion = DominionUtils.getDominionByChunk(chunk);
		
		if(currentDominion != null) {
			
			// Does player have permission to break blocks in this specific chunk?
        	ChunkSettingState state = getChunkPermission(
        			currentDominion, chunk, pUUID, ChunkSettingType.BREAK_AND_PLACE);
			if(state.equals(ChunkSettingState.ALLOWED)) {
				return;
			} else if (state.equals(ChunkSettingState.DISALLOWED)) {
				e.setCancelled(true);
				return;
			}
			
			// Does the player have the permission to break blocks in dominion?
			if (!DominionUtils.hasPermission(currentDominion, pUUID, SettingType.BREAK_AND_PLACE)) {
				e.setCancelled(true);
				return;
			}
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPlace(BlockPlaceEvent e){
		
		Chunk chunk = e.getBlock().getChunk();
		UUID pUUID = e.getPlayer().getUniqueId();
		Dominion currentDominion = DominionUtils.getDominionByChunk(chunk);
		
		if(currentDominion != null && !e.getBlockPlaced().getType().equals(Material.FIRE)) {
			
			// Does player have permission to place blocks in this specific chunk?
        	ChunkSettingState state = getChunkPermission(
        			currentDominion, chunk, pUUID, ChunkSettingType.BREAK_AND_PLACE);
			if(state.equals(ChunkSettingState.ALLOWED)) {
				return;
			} else if (state.equals(ChunkSettingState.DISALLOWED)) {
				e.setCancelled(true);
				return;
			}
			
			// Does the player have the permission to place blocks in dominion?
			if (!DominionUtils.hasPermission(currentDominion, pUUID, SettingType.BREAK_AND_PLACE)) {
				e.setCancelled(true);
				return;
			}
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
    public void onHangingBreak(HangingBreakByEntityEvent e){
		
		if(e.getRemover() instanceof Player) {
			
			Chunk chunk = e.getEntity().getLocation().getChunk();
			Dominion currentDominion = DominionUtils.getDominionByChunk(chunk);
			Player player = (Player) e.getRemover();
			
			if(currentDominion != null) {
				
				// Does player have permission to break hanging in this specific chunk?
	        	ChunkSettingState state = getChunkPermission(
	        			currentDominion, chunk, player.getUniqueId(), ChunkSettingType.BREAK_AND_PLACE);
				if(state.equals(ChunkSettingState.ALLOWED)) {
					return;
				} else if (state.equals(ChunkSettingState.DISALLOWED)) {
					e.setCancelled(true);
					return;
				}
				
				// Does the player have the permission to break hanging in dominion?
				if (!DominionUtils.hasPermission(currentDominion, player.getUniqueId(), SettingType.BREAK_AND_PLACE)) {
					e.setCancelled(true);
					return;
				}
				
			}
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
    public void onHangingPlace(HangingPlaceEvent e){
		
		Chunk chunk = e.getBlock().getChunk();
		UUID pUUID = e.getPlayer().getUniqueId();
		Dominion currentDominion = DominionUtils.getDominionByChunk(chunk);
		
		if(currentDominion != null) {
			
			// Does player have permission to place hanging in this specific chunk?
        	ChunkSettingState state = getChunkPermission(
        			currentDominion, chunk, pUUID, ChunkSettingType.BREAK_AND_PLACE);
			if(state.equals(ChunkSettingState.ALLOWED)) {
				return;
			} else if (state.equals(ChunkSettingState.DISALLOWED)) {
				e.setCancelled(true);
				return;
			}
			
			// Does the player have the permission to place hanging in dominion?
			if (!DominionUtils.hasPermission(currentDominion, pUUID, SettingType.BREAK_AND_PLACE)) {
				e.setCancelled(true);
				return;
			}
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
    public void onInteractEntity(PlayerInteractEntityEvent e){
		
		if (e.getRightClicked() instanceof Hanging) {
            
			Chunk chunk = e.getRightClicked().getLocation().getChunk();
			UUID pUUID = e.getPlayer().getUniqueId();
			Dominion currentDominion = DominionUtils.getDominionByChunk(chunk);
			
			if(currentDominion != null) {
				
				// Does player have permission to interact in this specific chunk?
            	ChunkSettingState state = getChunkPermission(
            			currentDominion, chunk, pUUID, ChunkSettingType.INTERACT);
				if(state.equals(ChunkSettingState.ALLOWED)) {
					return;
				} else if (state.equals(ChunkSettingState.DISALLOWED)) {
					e.setCancelled(true);
					return;
				}
				
				// Does the player have the permission to interact in dominion?
				if (!DominionUtils.hasPermission(currentDominion, pUUID, SettingType.INTERACT)) {
					e.setCancelled(true);
					return;
				}
				
			}
			
        }
		
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
    public void onDamageEntity(EntityDamageByEntityEvent e){
		
		if (e.getEntity() instanceof Hanging) {
			
			Chunk chunk = e.getEntity().getLocation().getChunk();
			Dominion currentDominion = DominionUtils.getDominionByChunk(chunk);
			
			if(currentDominion != null) {
				
				if (e.getDamager() instanceof Player) {
	                
					Player player = (Player) e.getDamager();
					
					// Does player have permission to damage hanging entity in this specific chunk?
                	ChunkSettingState state = getChunkPermission(
                			currentDominion, chunk, player.getUniqueId(), ChunkSettingType.INTERACT);
					if(state.equals(ChunkSettingState.ALLOWED)) {
						return;
					} else if (state.equals(ChunkSettingState.DISALLOWED)) {
						e.setCancelled(true);
						return;
					}
					
					// Does player have permission to damage hanging entity in dominion?
					if (!DominionUtils.hasPermission(currentDominion, player.getUniqueId(), SettingType.INTERACT)) {
						e.setCancelled(true);
						return;
					}
	            	
	            } else if (e.getDamager() instanceof Projectile) {
	                if (((Projectile) e.getDamager()).getShooter() instanceof Player) {
	                    
	                	Player player = (Player) ((Projectile) e.getDamager()).getShooter();
	                	
	                	// Does player have permission to damage hanging entity in this specific chunk?
	                	ChunkSettingState state = getChunkPermission(
	                			currentDominion, chunk, player.getUniqueId(), ChunkSettingType.INTERACT);
						if(state.equals(ChunkSettingState.ALLOWED)) {
							return;
						} else if (state.equals(ChunkSettingState.DISALLOWED)) {
							e.getDamager().remove();
							e.setCancelled(true);
							return;
						}
	                	
						// Does player have permission to damage hanging entity in dominion?
	                	if (!DominionUtils.hasPermission(currentDominion, player.getUniqueId(), SettingType.INTERACT)) {
	                		e.getDamager().remove();
							e.setCancelled(true);
							return;
						}
	                	
	                }
	            }
				
			}
			
        }
		
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
    public void onInteract(PlayerInteractEvent e){
		
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			
			if(interactMaterials.contains(e.getClickedBlock().getType())) {
				
				Chunk chunk = e.getClickedBlock().getChunk();
				UUID pUUID = e.getPlayer().getUniqueId();
				Dominion currentDominion = DominionUtils.getDominionByChunk(chunk);
				
				if(currentDominion != null) {
					
					// Does the player have the permission to interact in this specific chunk?
					ChunkSettingState state = getChunkPermission(currentDominion, chunk, pUUID, ChunkSettingType.INTERACT);
					if(state.equals(ChunkSettingState.ALLOWED)) {
						return;
					} else if (state.equals(ChunkSettingState.DISALLOWED)) {
						e.setCancelled(true);
						return;
					}
					
					// Does the player have the permission to interact in dominion?
					if (!DominionUtils.hasPermission(currentDominion, pUUID, SettingType.INTERACT)) {
						e.setCancelled(true);
						return;
					}
					
				}
				
			} else if(containerMaterials.contains(e.getClickedBlock().getType())) {
				
				Chunk chunk = e.getClickedBlock().getChunk();
				UUID pUUID = e.getPlayer().getUniqueId();
				Dominion currentDominion = DominionUtils.getDominionByChunk(chunk);
				
				if(currentDominion != null) {
					
					// Does the player have the permission to open containers in this specific chunk?
					ChunkSettingState state = getChunkPermission(currentDominion, chunk, pUUID, ChunkSettingType.OPEN_CONTAINERS);
					if(state.equals(ChunkSettingState.ALLOWED)) {
						return;
					} else if (state.equals(ChunkSettingState.DISALLOWED)) {
						e.setCancelled(true);
						return;
					}
					
					// Does the player have the permission to open containers in dominion?
					if (!DominionUtils.hasPermission(currentDominion, pUUID, SettingType.OPEN_CONTAINERS)) {
						e.setCancelled(true);
						return;
					}
					
				}
				
			}
			
		}
		
	}
	
	private ChunkSettingState getChunkPermission(Dominion dominion, Chunk chunk, UUID pUUID, ChunkSettingType type) {
		try {
			return dominion.getChunkSettings(chunk).get(pUUID).get(type);
		} catch (Exception exception) {
			return ChunkSettingState.DISABLED;
		}
	}
	
	
}
