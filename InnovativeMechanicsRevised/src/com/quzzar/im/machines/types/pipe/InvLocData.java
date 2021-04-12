package com.quzzar.im.machines.types.pipe;

import org.bukkit.Location;

public class InvLocData {

	private String tag;
	private String structID;
	private Location invLoc;
	
	public InvLocData(String structID, String tag, Location invLoc) {
		this.structID = structID;
		this.tag = tag;
		this.invLoc = invLoc;
	}
	
	public String getStructID() {
		return structID;
	}
	
	public String getInvTag() {
		return tag;
	}
	
	public Location getInvLoc() {
		return invLoc;
	}
	
}
