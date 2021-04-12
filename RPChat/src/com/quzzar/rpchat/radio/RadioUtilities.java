package com.quzzar.rpchat.radio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.rpchat.RPChat;
import com.quzzar.rpchat.radio.misc.CodeSet;
import com.quzzar.rpchat.radio.misc.ItemType;

public class RadioUtilities {
	
	private static SplittableRandom splitRand = new SplittableRandom();
	
	public static ArrayList<Radio> getRadios(Player player) {
		
		ArrayList<Radio> radios = null;
		
		for(int i = 0; i <= 8; i++) {
			ItemStack item = player.getInventory().getItem(i);
			if(item!=null) {
				Radio radio = getRadioFromItem(item);
				if(radio!=null) {
					
					if(radios==null) {
						radios = new ArrayList<Radio>();
					}
					radios.add(radio);
					
				}
			}
		}
		
		return radios;
		
	}
	
	public static String getRandomChannelPrefix() {
		
		char c = CodeSet.getCP437().get(splitRand.nextInt(CodeSet.getCP437().size()));
		
		List<ChatColor> colorsList = Arrays.asList(ChatColor.values());
		ChatColor color = colorsList.get(splitRand.nextInt(colorsList.size()));
		
		return "§8§l[§r"+color+""+c+"§8§l]§r";
		
	}
	
	private static String serializeMatrix(ItemStack[] matrix) {
		
		StringBuilder strBuilder = new StringBuilder();
		
		for(ItemStack item : matrix) {
			if(item!=null) {
				strBuilder.append(item.getType().name().substring(0, 1));
			} else {
				strBuilder.append("~");
			}
		}
		
		return strBuilder.toString();
	}
	
	public static ItemStack makeRegisteredRadio(ItemStack[] matrix, ItemStack radioItem) {
		
		String serialization = serializeMatrix(matrix);
		
		Radio radio = RPChat.getRadioManager().getRadioByMatrix(serialization);
		
		if(radio==null) {
			radio = RPChat.getRadioManager().createRadio(serialization);
		}
		
		ItemMeta im = radioItem.getItemMeta();
		
		im.setLore(Arrays.asList("§7§oFrequency: "+radio.getChannelPrefix()));
		
		radioItem.setItemMeta(im);
		
		return radioItem;
	}
	
	public static Radio getRadioFromItem(ItemStack radioItem) {
		
		if(RadioCreator.isFairlySimilar(radioItem, ItemType.SENDING_SHELL)) {
			
			String lore = radioItem.getItemMeta().getLore().get(0);
			String prefix = lore.substring(lore.indexOf(" ") + 1, lore.length());
			
			return RPChat.getRadioManager().getRadioByPrefix(prefix);
			
		}
		
		return null;
	}
	
}