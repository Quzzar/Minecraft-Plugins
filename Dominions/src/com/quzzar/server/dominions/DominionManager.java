package com.quzzar.server.dominions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.misc.PersistentEntities;

public class DominionManager {
	
	private static LinkedList<Dominion> dominions;
	
	public static final double costToCreate = 500.0;
	public static final double costToClaim = 50.0;
	
	@SuppressWarnings("unchecked")
	public DominionManager() {
		
		try {
	        FileInputStream fis = new FileInputStream(
	        		DominionsMain.instance.getDataFolder()+"/dominions.data");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        dominions = (LinkedList<Dominion>) ois.readObject();
	        ois.close();
		} catch (Exception e) {
			Utility.tellConsole(ChatColor.RED+"Failed to load Dominion files!");
			Utility.tellConsole(ChatColor.RED+"Creating new ones...");
			
			dominions = new LinkedList<Dominion>();
		}
		
		// Spawn All P Entities
		for(Dominion d : dominions) {
			PersistentEntities.spawnEntities(d);
		}
		
	}
	
	public void save() {
		
		// Delete All P Entities
		for(Dominion d : dominions) {
			PersistentEntities.deleteEntities(d);
		}
		
		try {
			FileOutputStream fos = new FileOutputStream(
					DominionsMain.instance.getDataFolder()+"/dominions.data");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(dominions);
	        oos.close();
		} catch (IOException e) {
			Utility.tellConsole(ChatColor.RED+"Failed to save Dominion files!");
		}
		
	}
	
	///
	
	public static void createDominion(Player pLeader, String name, String description) {
		dominions.add(new Dominion(pLeader, name, description));
	}
	
	public static void deleteDominion(Dominion dominion) {
		dominions.remove(dominion);
	}
	
	///
	
	public static LinkedList<Dominion> getDominions(){
		return dominions;
	}
	
}


