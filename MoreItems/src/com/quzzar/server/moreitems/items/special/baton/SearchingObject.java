package com.quzzar.server.moreitems.items.special.baton;

import org.bukkit.entity.Player;

public class SearchingObject {
	
	private Player p;
	private Player otherP;
	
	private boolean moved;
	
	public SearchingObject(Player p, Player otherP, boolean moved) {
		
		this.p = p;
		this.otherP = otherP;
		this.moved = moved;
		
	}

	public Player getPlayer() {
		return p;
	}

	public Player getOtherPlayer() {
		return otherP;
	}

	public boolean isMoved() {
		return moved;
	}
	
	public void setMoved(boolean moved){
		this.moved = moved;
	}
	
	
}
