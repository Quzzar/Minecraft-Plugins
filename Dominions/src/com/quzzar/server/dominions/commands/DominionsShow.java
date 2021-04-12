package com.quzzar.server.dominions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.centeredtext.CenterText;
import com.quzzar.server.dominions.misc.DiplomacyState;
import com.quzzar.server.dominions.misc.PlayerRank;

public class DominionsShow {
	
	private static ChatColor border = ChatColor.DARK_GRAY;
	private static ChatColor desc = ChatColor.GREEN;
	private static ChatColor wealth = ChatColor.DARK_PURPLE;
	private static ChatColor taxRate = ChatColor.LIGHT_PURPLE;
	private static ChatColor claimedChunks = ChatColor.GOLD;
	private static ChatColor totalMembers = ChatColor.AQUA;
	private static ChatColor leaders = ChatColor.BLUE;
	private static ChatColor generals = ChatColor.GREEN;
	private static ChatColor veterans = ChatColor.YELLOW;
	private static ChatColor members = ChatColor.LIGHT_PURPLE;
	private static ChatColor allies = ChatColor.DARK_GREEN;
	private static ChatColor enemies = ChatColor.RED;
	
	public static String commandLayout = "/d show [dominion/player]";
	
	private static char endPoint = '\u2B24';
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==1){
			
			if(sender instanceof Player) {
				
				Dominion dominion = DominionUtils.getDominionByPlayer(((Player)sender).getUniqueId());
				
				if(dominion!=null) {
					
					showDominion(sender, dominion);
					
				} else {
					sender.sendMessage(ChatColor.RED+"You have no Dominion!");
				}
				
			} else {
				sender.sendMessage(ChatColor.RED+"Only players can use this command!");
			}
			
		} else if (args.length==2){
			
			Dominion dominion = DominionUtils.getDominionByName(args[1]);
			
			if(dominion!=null) {
				
				showDominion(sender, dominion);
				
			} else {
				
				Player lookupPlayer = Bukkit.getPlayerExact(args[1]);
				
				if(lookupPlayer!=null) {
					
					dominion = DominionUtils.getDominionByPlayer(lookupPlayer.getUniqueId());
					
					if(dominion!=null) {
						
						showDominion(sender, dominion);
						
					} else {
						sender.sendMessage(ChatColor.RED+lookupPlayer.getDisplayName()+" is not in a Dominion!");
					}
					
				} else {
					sender.sendMessage(ChatColor.RED+"Could not be found!");
				}
				
			}
			
		} else {
			failedCommand(sender);
		}
		
	}
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
	private static void showDominion(CommandSender sender, Dominion dominion) {
		
		String topBar = border+"§m"+endPoint+"--------------"+border+"[ §3"
				+dominion.getName()+
				border+" ]§m--------------"+border+endPoint;
		
		String divider = getDivider(topBar);
		String bottomBar = getBottomBar(topBar);
		
		CenterText.sendCenteredMessage(sender,topBar);
		
		CenterText.sendCenteredMessage(sender,desc+" "+ChatColor.ITALIC+dominion.getDescription());
		CenterText.sendCenteredMessage(sender,wealth+"Calculated Wealth: "+ChatColor.BOLD
				+"$"+dominion.getCalculatedWealth());
		CenterText.sendCenteredMessage(sender,taxRate+"Tax Rate: "+ChatColor.BOLD
				+"$"+dominion.getTaxRate()+taxRate+" per hour");
		CenterText.sendCenteredMessage(sender,claimedChunks+"Chunks Claimed: "+ChatColor.BOLD
				+dominion.getChunkCount());
		CenterText.sendCenteredMessage(sender,totalMembers+"Player Size: "+ChatColor.BOLD
				+"["+dominion.getMemberCount()+"/"+dominion.getMaxMembers()+"]");
		
		//CenterText.sendCenteredMessage(sender,border+"§m--------------------------------");
		CenterText.sendCenteredMessage(sender,divider);
		
		StringBuilder leaderNames = new StringBuilder();
		boolean first = true;
		for(String name : DominionUtils.getRankNames(dominion, PlayerRank.Leader)) {
			if(first) {
				leaderNames.append(name);
				first = false;
			} else {
				leaderNames.append(", "+name);
			}
		}
		CenterText.sendCenteredMessage(sender,leaders+"§lLeaders");
		CenterText.sendCenteredMessage(sender,leaders+"§o"+leaderNames.toString());
		///
		StringBuilder generalNames = new StringBuilder();
		first = true;
		for(String name : DominionUtils.getRankNames(dominion, PlayerRank.General)) {
			if(first) {
				generalNames.append(name);
				first = false;
			} else {
				generalNames.append(", "+name);
			}
		}
		CenterText.sendCenteredMessage(sender,generals+"§lGenerals");
		CenterText.sendCenteredMessage(sender,generals+"§o"+generalNames.toString());
		///
		StringBuilder veteranNames = new StringBuilder();
		first = true;
		for(String name : DominionUtils.getRankNames(dominion, PlayerRank.Veteran)) {
			if(first) {
				veteranNames.append(name);
				first = false;
			} else {
				veteranNames.append(", "+name);
			}
		}
		CenterText.sendCenteredMessage(sender,veterans+"§lVeterans");
		CenterText.sendCenteredMessage(sender,veterans+"§o"+veteranNames.toString());
		///
		StringBuilder memberNames = new StringBuilder();
		first = true;
		for(String name : DominionUtils.getRankNames(dominion, PlayerRank.Member)) {
			if(first) {
				memberNames.append(name);
				first = false;
			} else {
				memberNames.append(", "+name);
			}
		}
		CenterText.sendCenteredMessage(sender,members+"§lMembers");
		CenterText.sendCenteredMessage(sender,members+"§o"+memberNames.toString());
		
		CenterText.sendCenteredMessage(sender,divider);
		
		StringBuilder allyNames = new StringBuilder();
		first = true;
		for(String name : DominionUtils.getRelationNames(dominion, DiplomacyState.Ally)) {
			if(first) {
				allyNames.append(name);
				first = false;
			} else {
				allyNames.append(", "+name);
			}
		}
		CenterText.sendCenteredMessage(sender,allies+"§lAllies");
		CenterText.sendCenteredMessage(sender,allies+"§o"+allyNames.toString());
		
		StringBuilder enemyNames = new StringBuilder();
		first = true;
		for(String name : DominionUtils.getRelationNames(dominion, DiplomacyState.Enemy)) {
			if(first) {
				enemyNames.append(name);
				first = false;
			} else {
				enemyNames.append(", "+name);
			}
		}
		CenterText.sendCenteredMessage(sender,enemies+"§lEnemies");
		CenterText.sendCenteredMessage(sender,enemies+"§o"+enemyNames.toString());
		
		CenterText.sendCenteredMessage(sender,bottomBar);
	}
	
	private static String getDivider(String topBar) {
		StringBuilder divider = new StringBuilder();
		divider.append(border+"§m");
		for(int i = 0; i < topBar.length()-18*1.8; i++) {
			divider.append("-");
		}
		return divider.toString();
	}
	
	private static String getBottomBar(String topBar) {
		StringBuilder bottomBar = new StringBuilder();
		bottomBar.append(border+"§m"+endPoint);
		for(int i = 0; i < topBar.length()-18; i++) {
			bottomBar.append("-");
		}
		bottomBar.append(border);
		bottomBar.append(endPoint);
		return bottomBar.toString();
	}
	
}
