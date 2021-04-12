package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionManager;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.DominionsMain;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.logs.LogType;
import com.quzzar.server.dominions.settings.SettingType;
import com.quzzar.server.dominions.upgrades.UpgradeType;
import com.quzzar.server.dominions.zones.ZoneListener;

public class DominionsClaim {
	
	public static String commandLayout = "/d claim";
	
	public static void run(CommandSender sender, String[] args){
		
		boolean confirmingClaim = false;
		
		if (args.length!=1) {
			if(args.length==2 && args[1].equalsIgnoreCase("confirm")) {
				confirmingClaim = true;
			} else {
				failedCommand(sender);
				return;
			}
		}
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
			
			if(dominion!=null) {
				
				// Does the player have the rank to claim? Check settings.
				if(DominionUtils.hasPermission(dominion, player.getUniqueId(), SettingType.CLAIM)) {
					
					Chunk chunk = player.getLocation().getChunk();
					int claimType = determineClaim(dominion, chunk, player, confirmingClaim);
					
					if(claimType == 0) {
						
						double takenMoney = DominionManager.costToClaim;
						
						if(dominion.withdrawMoney(takenMoney, false)) {
							
							// Make finance log record
							dominion.addLogRecord(LogType.FINANCE, player.getDisplayName()+" claimed a chunk, costing "
									+ChatColor.RED+"$"+takenMoney+ChatColor.RESET+".");
							
							dominion.addChunk(chunk);
							sender.sendMessage(
									ChatColor.GREEN+"Chunk claimed at ["+chunk.getX()*16+", "+chunk.getZ()*16+"]");
							sender.sendMessage(
									ChatColor.GREEN+"$"+takenMoney+" has been removed from the Dominion vault.");
							ZoneListener.displayZone(player, chunk, null);
							
						} else {
							sender.sendMessage(ChatColor.RED+"There is not enough money in the vault to do that!");
						}
						
					} else if(claimType == 1) {
						sender.sendMessage(ChatColor.RED+"Chunk has already been claimed!");
					} else if(claimType == 2) {
						sender.sendMessage(ChatColor.RED+"A nearby Dominion is preventing you from claiming so close!");
					} else if(claimType == 3) {
						sender.sendMessage(ChatColor.RED+"You can only claim chunks next to your Dominion!");
					} else if(claimType == 4) {
						sender.sendMessage(ChatColor.RED+"You can't claim in a Safezone!");
					} else if(claimType == 5) {
						sender.sendMessage(ChatColor.RED+"You can't claim in a Neutral Zone!");
					}
					
				} else {
					sender.sendMessage(ChatColor.RED+"You don't have the rank to use this command!");
				}
				
			} else {
				sender.sendMessage(ChatColor.RED+"You have no Dominion!");
			}
			
		} else {
			sender.sendMessage(ChatColor.RED+"Only players can use this command!");
		}
		
	}
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
	private static int determineClaim(Dominion claimingDominion, Chunk chunk, Player player, boolean confirmingClaim) {
		// 0 = No Claim & next to Your Dom (AKA: All good to claim)
		// 1 = Other claim at Chunk,
		// 2 = Other claim at nearby chunk & they have Personal Space
		// 3 = No claim but Not next to Dominion
		// 4 = Claiming in Safezone
		// 5 = Claiming in Warzone
		// 6 = Imperialism Claim
		
		// Determine Safe/War-Zone claims
		if(DominionsMain.getZoneManager().isSafeClaimed(chunk)) {
			return 4;
		}
		if(DominionsMain.getZoneManager().isNeutralClaimed(chunk)) {
			return 5;
		}
		
		// Determine other Dominion claims
		World world = chunk.getWorld();
		
		Chunk northChunk = world.getChunkAt(chunk.getX()+1, chunk.getZ());
		Chunk southChunk = world.getChunkAt(chunk.getX()-1, chunk.getZ());
		Chunk eastChunk = world.getChunkAt(chunk.getX(), chunk.getZ()+1);
		Chunk westChunk = world.getChunkAt(chunk.getX(), chunk.getZ()-1);
		
		for(Dominion dominion : DominionManager.getDominions()) {
			if(dominion.hasClaimed(chunk)) {
				
				if(claimingDominion.hasUpgrade(UpgradeType.IMPERIALISM) && !dominion.equals(claimingDominion)) { // Go down route of imperial claiming...
					return claimFromOtherDominion(claimingDominion, dominion, chunk, player, confirmingClaim);
				} else {
					return 1;
				}
				
			}
			if(dominion.hasUpgrade(UpgradeType.PERSONAL_SPACE) && claimingDominion!=dominion) {
				if(dominion.hasClaimed(northChunk) 
						|| dominion.hasClaimed(southChunk)
						|| dominion.hasClaimed(eastChunk)
						|| dominion.hasClaimed(westChunk)) {
					
					if(claimingDominion.hasUpgrade(UpgradeType.IMPERIALISM)) {
						// Continue, Because with Imperialism, the others Personal Space is overridden
					} else {
						return 2;
					}
					
				}
			}
		}
		
		return determineAdjacentClaims(claimingDominion, chunk);
		
	}
	
	private static int determineAdjacentClaims(Dominion claimingDominion, Chunk chunk) {
		
		World world = chunk.getWorld();
		
		Chunk northChunk = world.getChunkAt(chunk.getX()+1, chunk.getZ());
		Chunk southChunk = world.getChunkAt(chunk.getX()-1, chunk.getZ());
		Chunk eastChunk = world.getChunkAt(chunk.getX(), chunk.getZ()+1);
		Chunk westChunk = world.getChunkAt(chunk.getX(), chunk.getZ()-1);
		
		// If this is your first claim, you're good
		if(claimingDominion.getChunkCount()>0) {
			// If not, You can only claim next to your existing territory.
			if(claimingDominion.hasClaimed(northChunk) 
					|| claimingDominion.hasClaimed(southChunk)
					|| claimingDominion.hasClaimed(eastChunk)
					|| claimingDominion.hasClaimed(westChunk)) {
				return 0;
			} if (claimingDominion.hasUpgrade(UpgradeType.COLONIZATION)) {
				// Unless you have the Colonization upgrade...
				return 0;
			} else {
				return 3;
			}
		} else {
			return 0;
		}
		
	}
	
	private static int claimFromOtherDominion(Dominion claimingDominion, Dominion otherDominion, Chunk chunk,
			Player player, boolean confirmingClaim) {
		
		// Confirm the adjacency claims check out first.
		int returnType = determineAdjacentClaims(claimingDominion, chunk);
		if (returnType != 0) {
			return returnType;
		}
		// Then continue...
		
		double claimCost = DominionManager.costToClaim + otherDominion.getTaxRate()*60;
		
		if(confirmingClaim) {
			
			if(claimingDominion.withdrawMoney(claimCost, false)) {
				
				// Make finance log record
				claimingDominion.addLogRecord(LogType.FINANCE, player.getDisplayName()+" claimed a chunk from "
						+otherDominion.getName()+", costing "
						+ChatColor.RED+"$"+claimCost+ChatColor.RESET+".");
				
				// Make diplomacy log record
				claimingDominion.addLogRecord(LogType.DIPLOMACY, player.getDisplayName()+" claimed a chunk from "+otherDominion.getName()
					+" at ["+chunk.getX()*16+", "+chunk.getZ()*16+"].");
				
				// Make diplomacy log record
				otherDominion.addLogRecord(LogType.DIPLOMACY, claimingDominion.getName()+" claimed a chunk from us at ["
						+chunk.getX()*16+", "+chunk.getZ()*16+"].");
				
				otherDominion.removeChunk(chunk);
				DominionUtils.sendDominionAnnouncement(otherDominion,
						ChatColor.RED+""+ChatColor.BOLD+claimingDominion.getName()
							+" has stolen territory from us at ["+chunk.getX()*16+", "+chunk.getZ()*16+"]!");
				
				claimingDominion.addChunk(chunk);
				player.sendMessage(
						ChatColor.GREEN+"Chunk claimed at ["+chunk.getX()*16+", "+chunk.getZ()*16+"]");
				player.sendMessage(
						ChatColor.GREEN+"$"+claimCost+" has been removed from the Dominion vault.");
				ZoneListener.displayZone(player, chunk, null);
				
			} else {
				player.sendMessage(ChatColor.RED+"There is not enough money in the vault to do that!");
			}
			
		} else {
			player.sendMessage(ChatColor.GREEN+"Claiming this chunk from \""+otherDominion.getName()
				+"\" will cost "+ChatColor.BLUE+"$"+claimCost+ChatColor.GREEN+". To confirm, please type "
					+ChatColor.YELLOW+"/d claim confirm");
		}
		
		return 6;
		
	}
	
}
