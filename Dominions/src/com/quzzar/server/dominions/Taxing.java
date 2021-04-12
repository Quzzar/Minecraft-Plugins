package com.quzzar.server.dominions;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.misc.PlayerRank;

public class Taxing {

	private static ArrayList<Dominion> perishingDominions = new ArrayList<Dominion>();
	
	private static final double sellLandPercent = -0.05;
	private static final double perishPercent = -0.10;
	
	public static void callTax() {
		
		Utility.tellConsole("Taxing the Dominions...");
		for(Dominion dominion : DominionManager.getDominions()) {
			
			dominion.withdrawMoney(dominion.getTaxRate(), true);
			
			if(dominion.getMoney() < dominion.getCalculatedWealth()*perishPercent) {
				perishingDominions.add(dominion);
			} else if(dominion.getMoney() < dominion.getCalculatedWealth()*sellLandPercent) {
				sellingLand(dominion);
			} else if(dominion.getMoney() < 0) {
				negativeFunds(dominion);
			}
			
		}
		
		for(Dominion dominion : perishingDominions) {
			Bukkit.broadcastMessage(
					ChatColor.RED+""+ChatColor.ITALIC+dominion.getName()+
					ChatColor.BLUE+""+ChatColor.ITALIC+" has run out of funds and has perished...");
			DominionManager.deleteDominion(dominion);
		}
		perishingDominions.clear();
		
	}
	
	private static void sellingLand(Dominion dominion) {
		
		String message1 = ChatColor.RED+dominion.getName()+" has run out of funds!";
		String message2 = ChatColor.RED+"Any remaining territory is being sold to pay the dominion's upkeep.";
		
		DominionUtils.sendDominionAnnouncement(dominion, message1);
		
		for(Entry<UUID,PlayerRank> entry : dominion.getMembers().entrySet()) {
			Player player = Bukkit.getPlayer(entry.getKey());
			if(player != null) {
				player.sendMessage(message1);
				player.sendMessage(message2);
			}
		}
		
		double refundedMoney = dominion.getChunkCount()*DominionManager.costToClaim*0.75;
		dominion.removeAllChunks();
		dominion.depositMoney(refundedMoney);
		
	}
	
	private static void negativeFunds(Dominion dominion) {
		
		String message1 = ChatColor.RED+dominion.getName()+" has run out of funds!";
		String message2 = ChatColor.RED+"Please deposit funds into the dominion vault or "+dominion.getName()+" will perish!";
		
		DominionUtils.sendDominionAnnouncement(dominion, message1);
		
		for(Entry<UUID,PlayerRank> entry : dominion.getMembers().entrySet()) {
			Player player = Bukkit.getPlayer(entry.getKey());
			if(player != null) {
				player.sendMessage(message1);
				player.sendMessage(message2);
			}
		}
		
	}
	
}
