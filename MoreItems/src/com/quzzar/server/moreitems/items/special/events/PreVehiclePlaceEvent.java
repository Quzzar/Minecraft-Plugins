package com.quzzar.server.moreitems.items.special.events;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PreVehiclePlaceEvent extends Event implements Cancellable {

	private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;
    
    private Block blockAgainst;
    private BlockFace placedFace;
    private ItemStack itemPlaced;
    private VehicleType type;
    private Player player;

    public PreVehiclePlaceEvent(Block blockAgainst, BlockFace placedFace, ItemStack itemPlaced, VehicleType type, Player player) {
    	
    	this.blockAgainst = blockAgainst;
    	this.placedFace = placedFace;
    	this.itemPlaced = itemPlaced;
    	this.type = type;
    	this.player = player;
    	this.isCancelled = false;
    	
    }
    
    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

	public Block getBlockAgainst() {
		return blockAgainst;
	}

	public Location getPlacingLocation() {
		return getBlockAgainst().getRelative(getPlacedFace()).getLocation();
	}
	
	public ItemStack getItemPlaced() {
		return itemPlaced;
	}

	public VehicleType getVehicleType() {
		return type;
	}
	
	public BlockFace getPlacedFace() {
		return placedFace;
	}

	public Player getPlayer() {
		return player;
	}
	
}

enum VehicleType {
	
	BOAT,
	MINECART;
	
}
