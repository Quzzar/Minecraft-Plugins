package com.quzzar.server.dominions.commands;

import java.util.LinkedHashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.quzzar.server.dominions.DominionManager;
import com.quzzar.server.dominions.centeredtext.CenterText;
import com.quzzar.server.dominions.logs.LogManager;

public class DominionsHelp {
	
	private static LinkedHashMap<String, String> helpMenu1 = new LinkedHashMap<String,String>();
	private static LinkedHashMap<String, String> helpMenu2 = new LinkedHashMap<String,String>();
	private static LinkedHashMap<String, String> helpMenu3 = new LinkedHashMap<String,String>();
	private static LinkedHashMap<String, String> helpMenu4 = new LinkedHashMap<String,String>();
	private static LinkedHashMap<String, String> helpMenu5 = new LinkedHashMap<String,String>();
	
	private static ChatColor border = ChatColor.DARK_GRAY;
	
	public static void setHelpMenu() {
		
		helpMenu1.put(DominionsShow.commandLayout, "Displays useful information about a Dominion.");
		helpMenu1.put(DominionsTop.commandLayout, "Lists the top Dominions with the highest calculated wealth.");
		helpMenu1.put(DominionsList.commandLayout, "Lists all existing Dominions.");
		helpMenu1.put(DominionsMap.commandLayout, "Displays a map of the Dominions in your surrounding area.");
		helpMenu1.put(DominionsJoin.commandLayout, "Used to join a Dominion that has invited you or is open.");
		helpMenu1.put(DominionsLeave.commandLayout, "Used to leave your current Dominion.");
		
		helpMenu2.put(DominionsCreate.commandLayout, "Used to create your own Dominion (costs $"+DominionManager.costToCreate+").");
		helpMenu2.put(DominionsDestroy.commandLayout, "Used to destroy your own Dominion.");
		helpMenu2.put(DominionsDesc.commandLayout, "Sets the description for your Dominion.");
		helpMenu2.put(DominionsSettings.commandLayout, "Opens settings menu where you can customize permissions.");
		helpMenu2.put(DominionsUpgrades.commandLayout, "Opens upgrades menu where you can buy upgrades.");
		helpMenu2.put(DominionsAnnounce.commandLayout, "Sends a message to all players in your Dominion.");
		
		helpMenu3.put(DominionsInvite.commandLayout, "Used to invite a player to your Dominion.");
		helpMenu3.put(DominionsUninvite.commandLayout, "Used to revoke an invitation to your Dominion.");
		helpMenu3.put(DominionsKick.commandLayout, "Kicks a player from your Dominion.");
		helpMenu3.put(DominionsVault.commandLayout, "Displays how much money is in the Dominion vault.");
		helpMenu3.put(DominionsDeposit.commandLayout, "Adds money into the Dominion vault from your balance.");
		helpMenu3.put(DominionsWithdraw.commandLayout, "Takes money from the Dominion vault into your balance.");
		
		helpMenu4.put(DominionsClaim.commandLayout, "Claims the chunk you're standing in (costs $"+DominionManager.costToClaim+").");
		helpMenu4.put(DominionsUnclaim.commandLayout, "Unclaims the chunk you're standing in or all chunks.");
		helpMenu4.put(DominionsSetHome.commandLayout, "Used to set your Dominion's home.");
		helpMenu4.put(DominionsHome.commandLayout, "Teleports you to your Dominion's home.");
		helpMenu4.put(DominionsSetRank.shortCommandLayout, "Sets the rank of a equal or lower ranking player.");
		helpMenu4.put(DominionsSetDiplomacy.commandLayout, "Sets the status between your Dominion and another Dominion or player.");
		
		helpMenu5.put(DominionsLog.commandLayout, "Generates the logbook of recent Dominion events. Costs $"+LogManager.costToGetLog+".");
		helpMenu5.put(DominionsChunkSettings.commandLayout, "Opens chunk settings menu where you can customize permissions"
																+ " for the chunk you're standing in.");
		
	}
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==2){
			
			if(args[1].equals("2")) {
				displayHelpMenu(sender, helpMenu2, args[1]);
			} else if(args[1].equals("3")) {
				displayHelpMenu(sender, helpMenu3, args[1]);
			} else if(args[1].equals("4")) {
				displayHelpMenu(sender, helpMenu4, args[1]);
			} else if(args[1].equals("5")) {
				displayHelpMenu(sender, helpMenu5, args[1]);
			} else {
				displayHelpMenu(sender, helpMenu1, "1");
			}
			
		}else{
			displayHelpMenu(sender, helpMenu1, "1");
		}
		
	}
	
	private static void displayHelpMenu(CommandSender sender, LinkedHashMap<String, String> helpMenu, String numStr) {
		
		//////// Actual message ////////
		CenterText.sendCenteredMessage(sender, 
				border+"§m[---------------"+
				border+"[ §5§lDominions §2Help "+numStr+
				border+" ]§m---------------]");
		
		displayCommands(sender, helpMenu);
		
		CenterText.sendCenteredMessage(sender, 
				border+"§m[------------------------------------------------]");
		///////////////////////////////
		
	}
	
	private static void displayCommands(CommandSender sender, LinkedHashMap<String, String> helpMenu) {
		
		for(String comLayout : helpMenu.keySet()) {
			CenterText.sendCenteredMessage(sender, ChatColor.BLUE+""+ChatColor.BOLD+comLayout);
			CenterText.sendCenteredMessage(sender, ChatColor.GREEN+helpMenu.get(comLayout));
		}
		
	}
	
}
