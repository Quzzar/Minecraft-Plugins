package com.quzzar.server.souls.character;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.quzzar.server.souls.Souls;
import com.quzzar.server.souls.Utility;
import com.quzzar.server.souls.character.data.CharData;

public class CharacterManager {

	private static Hashtable<UUID, CharData> charMap;
	
	@SuppressWarnings("unchecked")
	public static void load() {
		
		try {
	        FileInputStream fis = new FileInputStream(
	        		Souls.instance.getDataFolder()+"/player_characters.data");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        charMap = (Hashtable<UUID, CharData>) ois.readObject();
	        ois.close();
		} catch (Exception e) {
			Utility.tellConsole(ChatColor.RED+"Failed to load character files!");
			Utility.tellConsole(ChatColor.RED+"Creating new ones...");
			
			charMap = new Hashtable<UUID, CharData>();
		}
		
	}
	
	public static void save() {
		
		try {
			FileOutputStream fos = new FileOutputStream(
					Souls.instance.getDataFolder()+"/player_characters.data");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(charMap);
	        oos.close();
		} catch (IOException e) {
			Utility.tellConsole(ChatColor.RED+"Failed to save character files!");
		}
		
	}
	
	public static PlayerCharacter getCharacter(Player p) {
		CharData cData = charMap.get(p.getUniqueId());
		if(cData == null) {
			throw new NullPointerException();
		}
		return new PlayerCharacter(cData, p);
	}
	
	public static PlayerCharacter addCharData(Player p) {
		charMap.put(p.getUniqueId(), new CharData(p));
		PlayerCharacter pChar = getCharacter(p);
		pChar.getData().getSoul().setRechargeHours(0);
		return pChar;
	}
	
	public static CharData getCharacterData(UUID pUUID) {
		return charMap.get(pUUID);
	}
	
	public static Hashtable<UUID, CharData> getAllData() {
		return charMap;
	}
	
}
