package com.quzzar.server.dominions.misc;

import java.io.Serializable;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SimpleLocation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private double x, y, z;
	private float yaw, pitch;
	private UUID worldUUID;
	
	public SimpleLocation(double x, double y, double z, float yaw, float pitch, UUID worldUUID) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
		this.worldUUID = worldUUID;
	}
	
	public SimpleLocation(Location loc) {
		this.x = loc.getX();
		this.y = loc.getY();
		this.z = loc.getZ();
		this.yaw = loc.getYaw();
		this.pitch = loc.getPitch();
		this.worldUUID = loc.getWorld().getUID();
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public float getYaw() {
		return yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public UUID getWorldUUID() {
		return worldUUID;
	}
	
	public Location toLocation() {
		return new Location(Bukkit.getWorld(worldUUID), x, y, z, yaw, pitch);
	}

}
