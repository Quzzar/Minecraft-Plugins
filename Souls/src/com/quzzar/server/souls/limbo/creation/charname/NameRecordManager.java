package com.quzzar.server.souls.limbo.creation.charname;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.ChatColor;
import com.quzzar.server.souls.Souls;
import com.quzzar.server.souls.Utility;

public class NameRecordManager {

	private static Set<String> nameList;
	
	@SuppressWarnings("unchecked")
	public static void load() {
		
		try {
	        FileInputStream fis = new FileInputStream(
	        		Souls.instance.getDataFolder()+"/name_record.data");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        nameList = (Set<String>) ois.readObject();
	        ois.close();
		} catch (Exception e) {
			Utility.tellConsole(ChatColor.RED+"Failed to load the name record file!");
			Utility.tellConsole(ChatColor.RED+"Creating a new one...");
			
			nameList = new HashSet<String>();
		}
		
	}
	
	public static void save() {
		
		try {
			FileOutputStream fos = new FileOutputStream(
					Souls.instance.getDataFolder()+"/name_record.data");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(nameList);
	        oos.close();
		} catch (IOException e) {
			Utility.tellConsole(ChatColor.RED+"Failed to save the name record file!");
		}
		
	}
	
	public static boolean containsName(String name) {
		return nameList.contains(name.toLowerCase());
	}
	
	public static void addName(String name) {
		nameList.add(name.toLowerCase());
	}
	
}
