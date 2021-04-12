package com.quzzar.server.moreitems.items.special.baton;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.EntityDataTag;
import com.quzzar.craftmeta.tags.InvDataTag;
import com.quzzar.server.moreitems.MoreItems;

public class SearchHandler {
	
	public static ArrayList<SearchingObject> searchingPlayers = new ArrayList<SearchingObject>();
	
	public static Inventory getPlayerInventory(Player p) {
		
		Inventory pInv = Bukkit.createInventory(null, 36, p.getDisplayName()+"'s Inventory");
		
		CraftMeta.invSet(pInv, InvDataTag.DISABLED);
		
		for(int i = 0; i<p.getInventory().getSize(); i++) {
			if(p.getInventory().getItem(i)!=null) {
				
				if(i<9) {
					
					pInv.setItem(i+27, p.getInventory().getItem(i));
					
				} else {
					
					pInv.setItem(i-9, p.getInventory().getItem(i));
					
				}
				
			}
		}
		
		return pInv;
		
	}
	
	
	public static void addSearchingPlayer(Player p, Player otherP) {
		
		boolean contains = false;
		
		for(SearchingObject search : SearchHandler.searchingPlayers) {
			if(search.getPlayer().equals(p)) {
				contains = true;
			}
		}
		
		if(!contains) {
			
			SearchHandler.searchingPlayers.add(new SearchingObject(p, otherP, false));
			
			CraftMeta.entitySet(p, EntityDataTag.SEARCHING, true);
			p.sendMessage(ChatColor.GRAY+"Searching "+otherP.getDisplayName()+", don't move...");
			
		}
		
	}
	
	
	public static void runTask() {
		
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(MoreItems.getInstance(), new Runnable() {
			
			public void run() {
				
				ArrayList<SearchingObject> removePlayers = new ArrayList<SearchingObject>();
				
				for(SearchingObject search : searchingPlayers) {
					
					if(!search.isMoved()) {
						
						search.getPlayer().openInventory(SearchHandler.getPlayerInventory(search.getOtherPlayer()));
						CraftMeta.entitySet(search.getPlayer(), EntityDataTag.SEARCHING, false);
						
					}
					
					removePlayers.add(search);
					
				}
				
				searchingPlayers.removeAll(removePlayers);
				
			}
			
		}, 140, 140);// 100 L == 7 sec, 20 ticks == 1 sec
		
		
	}
	
	
	
	
}
