package com.quzzar.server.dominions.logs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionManager;
import com.quzzar.server.dominions.Utility;

public class LogManager {

	public static final int costToGetLog = 5;
	
	private static final int recordsPerPage = 4;
	
	public static void cleanLogs() {	
		
		for(Dominion dominion : DominionManager.getDominions()) {
			deleteOldLogs(LogType.FINANCE, dominion);
			deleteOldLogs(LogType.MEMBERS, dominion);
			deleteOldLogs(LogType.DIPLOMACY, dominion);
		}
		
	}
	
	private static void deleteOldLogs(LogType type, Dominion dominion) {
		
		TreeMap<Date, String> log = dominion.getLog(type);
		
		while(log.size() > 100*recordsPerPage) {
			log.remove(log.firstKey());
		}
		
	}
	
	
	public static void createLogItem(Player player, Dominion dominion, LogType type) {
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd");
		
		ItemStack logBook = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta bookMeta = (BookMeta) logBook.getItemMeta();
		
		String title = "Broken Log";
		if(type.equals(LogType.FINANCE)) {
			title = ChatColor.BLUE+""+ChatColor.BOLD+dominion.getName()+": "+ChatColor.GOLD+"Financial Log";
		}
		if(type.equals(LogType.DIPLOMACY)) {
			title = ChatColor.BLUE+""+ChatColor.BOLD+dominion.getName()+": "+ChatColor.GOLD+"Diplomacy Log";
		}
		if(type.equals(LogType.MEMBERS)) {
			title = ChatColor.BLUE+""+ChatColor.BOLD+dominion.getName()+": "+ChatColor.GOLD+"Member Log";
		}
		
		bookMeta.setTitle(title);
		bookMeta.setAuthor(dateFormatter.format(new Date()));
		
		ArrayList<String> pages = new ArrayList<String>();
		
		String page = "";
		int count = 0;
		
		for (Map.Entry<Date, String> record : dominion.getLog(type).descendingMap().entrySet()) {
			
			count++;
			page += ChatColor.BOLD+dateFormatter.format(record.getKey())+ChatColor.RESET+" - "+record.getValue()+"\n";
			
			if(count >= recordsPerPage) {
				pages.add(page);
				page = "";
				count = 0;
			}
	    }
		
		pages.add(page);
		
		bookMeta.setPages(pages);
		logBook.setItemMeta(bookMeta);
		
		Utility.addItemToInventory(player.getInventory(), logBook, player.getLocation());
		
	}
	
	
}
