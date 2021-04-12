package com.quzzar.server.worldutils.warps;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import org.bukkit.ChatColor;
import com.quzzar.server.dominions.misc.SimpleLocation;
import com.quzzar.server.worldutils.Utility;
import com.quzzar.server.worldutils.WorldUtilities;

public class WarpManager {

	private static Hashtable<String, SimpleLocation> warpMap;
	
	@SuppressWarnings("unchecked")
	public static void load() {
		
		try {
	        FileInputStream fis = new FileInputStream(
	        		WorldUtilities.getInstance().getDataFolder()+"/warps.data");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        warpMap = (Hashtable<String, SimpleLocation>) ois.readObject();
	        ois.close();
		} catch (Exception e) {
			Utility.tellConsole(ChatColor.RED+"Failed to warps files!");
			Utility.tellConsole(ChatColor.RED+"Creating new ones...");
			
			warpMap = new Hashtable<String, SimpleLocation>();
		}
		
	}
	
	public static void save() {
		
		try {
			FileOutputStream fos = new FileOutputStream(
					WorldUtilities.getInstance().getDataFolder()+"/warps.data");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(warpMap);
	        oos.close();
		} catch (IOException e) {
			Utility.tellConsole(ChatColor.RED+"Failed to save warps files!");
		}
		
	}
	
	public static Hashtable<String, SimpleLocation> getAllWarps() {
		return warpMap;
	}
	
	public static void addWarp(String name, SimpleLocation loc) {
		warpMap.put(name.toLowerCase(), loc);
	}
	
	public static void removeWarp(String name) {
		warpMap.remove(name.toLowerCase());
	}
	
	public static SimpleLocation getWarp(String name) {
		return warpMap.get(name.toLowerCase());
	}
	
}
