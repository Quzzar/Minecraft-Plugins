package com.quzzar.server.skills;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.SplittableRandom;
import java.util.UUID;

import org.bukkit.ChatColor;

public class SkillManager {

	private HashMap<UUID,SkillSet> skillSetTable;
	private SplittableRandom splitRand;
	
	@SuppressWarnings("unchecked")
	public SkillManager() {
		
		splitRand = new SplittableRandom();
		
		// fill hashMap from data file here

		try {
	        FileInputStream fis = new FileInputStream(
	        		Skills.instance.getDataFolder()+"/skillset_tables.data");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        skillSetTable = (HashMap<UUID, SkillSet>) ois.readObject();
	        ois.close();
		} catch (Exception e) {
			Utility.tellConsole(ChatColor.RED+"Failed to load skill set table files!");
			Utility.tellConsole(ChatColor.RED+"Creating new ones...");
			
			skillSetTable = new HashMap<UUID,SkillSet>();
		}
		
	}
	
	public void save() {
		
		try {
			FileOutputStream fos = new FileOutputStream(
					Skills.instance.getDataFolder()+"/skillset_tables.data");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(skillSetTable);
	        oos.close();
		} catch (IOException e) {
			Utility.tellConsole(ChatColor.RED+"Failed to save skill set table files!");
		}
		
	}
	
	
	public void addPlayer(UUID uuid) {
		skillSetTable.put(uuid, new SkillSet(uuid));
	}
	
	public void removePlayer(UUID uuid) {
		skillSetTable.remove(uuid);
	}
	
	public HashMap<UUID,SkillSet> getSkillTables(){
		return skillSetTable;
	}
	
	public SkillSet getSkillSet(UUID uuid) {
		SkillSet skillSet = skillSetTable.get(uuid);
		if(skillSet == null) {
			this.addPlayer(uuid);
			skillSet = skillSetTable.get(uuid);
		}
		return skillSet;
	}
	
	public void adjustSkills(UUID uuid, int index, int amount) {
		
		SkillSet skillSet = this.getSkillSet(uuid);
		int takeFromIndex = splitRand.nextInt(5);
		
		if(takeFromIndex != index) {
			
			skillSet.decrement(takeFromIndex, amount);
			skillSet.increment(index, amount);
			
		}
		
	}
	
	
	
	
	
}
