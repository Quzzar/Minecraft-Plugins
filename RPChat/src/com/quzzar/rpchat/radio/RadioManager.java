package com.quzzar.rpchat.radio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.bukkit.ChatColor;

import com.quzzar.rpchat.RPChat;
import com.quzzar.rpchat.Utility;

public class RadioManager {
	
	private ArrayList<Radio> radios;
	
	@SuppressWarnings("unchecked")
	public RadioManager() {
		
		try {
	        FileInputStream fis = new FileInputStream(
	        		RPChat.getInstance().getDataFolder()+"/Data/radio_channels.data");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        radios = (ArrayList<Radio>) ois.readObject();
	        ois.close();
		} catch (Exception e) {
			Utility.tellConsoleBullet(ChatColor.RED+"Failed to load Radio Channel files!");
			Utility.tellConsoleBullet(ChatColor.YELLOW+"Creating new ones...");
			
			radios = new ArrayList<Radio>();
		}
		
	}
	
	public void save() {
		
		try {
			FileOutputStream fos = new FileOutputStream(
					RPChat.getInstance().getDataFolder()+"/Data/radio_channels.data");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(radios);
	        oos.close();
		} catch (IOException e) {
			Utility.tellConsole(ChatColor.RED+"Failed to save Radio Channel files!");
		}
		
	}
	
	///
	
	public Radio createRadio(String serializedMatrix) {
		Radio radio = new Radio(serializedMatrix);
		radios.add(radio);
		
		return radio;
	}
	
	public void deleteDominion(Radio radio) {
		radios.remove(radio);
	}
	
	///
	
	public Radio getRadioByMatrix(String serializedMatrix){
		for(Radio radio : radios) {
			if(radio.getSerializedMatrix().equals(serializedMatrix)) {
				return radio;
			}
		}
		return null;
	}
	
	public Radio getRadioByPrefix(String channelPrefix){
		for(Radio radio : radios) {
			if(radio.getChannelPrefix().equals(channelPrefix)) {
				return radio;
			}
		}
		return null;
	}
	
	public ArrayList<Radio> getRadios(){
		return radios;
	}
	
}
