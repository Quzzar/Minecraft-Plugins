package com.quzzar.server.dominions.zones;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.DominionsMain;
import com.quzzar.server.dominions.Utility;

public class ZoneManager {
	
	private Set<String> safeChunks;
	private Set<String> neutralChunks;

	@SuppressWarnings("unchecked")
	public ZoneManager() {
		try {
			FileInputStream fis = new FileInputStream(DominionsMain.instance.getDataFolder() + "/safezone.data");
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.safeChunks = (Set<String>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			Utility.tellConsole(ChatColor.RED + "Failed to load safezone files!");
			Utility.tellConsole(ChatColor.RED + "Creating new ones...");

			this.safeChunks = new HashSet<String>();
		}

		try {
			FileInputStream fis = new FileInputStream(DominionsMain.instance.getDataFolder() + "/neutralzone.data");
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.neutralChunks = (Set<String>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			Utility.tellConsole(ChatColor.RED + "Failed to load neutral zone files!");
			Utility.tellConsole(ChatColor.RED + "Creating new ones...");

			this.neutralChunks = new HashSet<String>();
		}
	}

	public void save() {
		
		try {
			FileOutputStream fos = new FileOutputStream(DominionsMain.instance.getDataFolder() + "/safezone.data");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(safeChunks);
			oos.close();
		} catch (IOException e) {
			Utility.tellConsole(ChatColor.RED + "Failed to save safezone files!");
		}

		try {
			FileOutputStream fos = new FileOutputStream(DominionsMain.instance.getDataFolder() + "/neutralzone.data");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(neutralChunks);
			oos.close();
		} catch (IOException e) {
			Utility.tellConsole(ChatColor.RED + "Failed to save neutral zone files!");
		}
	}
	
	public void addSafeChunk(Chunk chunk) {
		safeChunks.add(DominionUtils.packChunk(chunk));
	}
	
	public void removeSafeChunk(Chunk chunk) {
		safeChunks.remove(DominionUtils.packChunk(chunk));
	}

	public void removeAllSafeChunks() {
		safeChunks.clear();
	}

	public boolean isSafeClaimed(Chunk chunk) {
		return safeChunks.contains(DominionUtils.packChunk(chunk));
	}
	
	///
	
	public void addNeutralChunk(Chunk chunk) {
		neutralChunks.add(DominionUtils.packChunk(chunk));
	}
	
	public void removeNeutralChunk(Chunk chunk) {
		neutralChunks.remove(DominionUtils.packChunk(chunk));
	}

	public void removeAllNeutralChunks() {
		neutralChunks.clear();
	}

	public boolean isNeutralClaimed(Chunk chunk) {
		return neutralChunks.contains(DominionUtils.packChunk(chunk));
	}
	
	///

	public ZoneType getZoneType(Chunk chunk, Dominion dominion) {
		if (chunk == null) {
			return ZoneType.Wilderness;
		}
		if (isSafeClaimed(chunk))
			return ZoneType.SafeZone;
		if (isNeutralClaimed(chunk))
			return ZoneType.NeutralZone;
		if (dominion != null) {
			return ZoneType.Dominion;
		}
		return ZoneType.Wilderness;
	}
}