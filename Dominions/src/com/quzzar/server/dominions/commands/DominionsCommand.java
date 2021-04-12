package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DominionsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(!sender.hasPermission("dominions.commands.general")) {
			sender.sendMessage(ChatColor.RED+"You don't have permission to use this command!");
			return true;
		}
		
		if(args.length>0) {
			
			if(args[0].equalsIgnoreCase("create")) {
				
				// Requires fine of..... ?
				// Anyone
				DominionsCreate.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("destroy")) {
				
				// Only the leader rank can use command.
				DominionsDestroy.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("join") || args[0].equalsIgnoreCase("j")) {
				
				// Permission from settings
				DominionsJoin.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("kick")) {
				
				// Can only kick members of a rank equal to or lower than you
				// Can not kick yourself.
				DominionsKick.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("leave")) {
				
				// Can not leave if leader.
				// Anyone
				DominionsLeave.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("i")) {
				
				// Permission from settings
				DominionsInvite.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("uninvite")) {
				
				// Permission from settings
				DominionsUninvite.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("claim")) {
				
				// Requires fine of..... ?
				// Permission from settings
				DominionsClaim.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("unclaim")) {
				
				// Adds 75% of cost to claim back
				// Permission from settings
				DominionsUnclaim.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("unclaimall")) {
				
				// Adds 75% of cost to claim back
				// Only the leader rank can use command.
				DominionsUnclaimAll.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("announce")) {
				
				// Only the leader rank can use command.
				DominionsAnnounce.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("chunksettings")) {
				
				// Obviously needs to be in territory.
				// Permission from settings
				DominionsChunkSettings.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("settings")) {
				
				// Permission from settings
				DominionsSettings.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("upgrades")) {
				
				// Must be in Dominion territory to use
				// Permission from settings
				DominionsUpgrades.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("setdiplomacy") || args[0].equalsIgnoreCase("setdip")) {
				
				// Permission from settings
				DominionsSetDiplomacy.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("deposit") || args[0].equalsIgnoreCase("d")) {
				
				// Permission from settings
				DominionsDeposit.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("withdraw") || args[0].equalsIgnoreCase("w")) {
				
				// Permission from settings
				DominionsWithdraw.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("vault") || args[0].equalsIgnoreCase("bank") || args[0].equalsIgnoreCase("balance")
					|| args[0].equalsIgnoreCase("bal") || args[0].equalsIgnoreCase("money") || args[0].equalsIgnoreCase("v")) {
				
				// Anyone in the Dominion
				DominionsVault.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("setrank")) {
				
				// Can only set rank to a rank equal to or lower than you
				// Can not set your own rank.
				DominionsSetRank.run(sender, args);
				
				
			} else if(args[0].equalsIgnoreCase("description") || args[0].equalsIgnoreCase("desc")) {
				
				// Permission from settings
				DominionsDesc.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("sethome")) {
				
				// Can only set in territory.
				// Permission from settings
				DominionsSetHome.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("home") || args[0].equalsIgnoreCase("h")) {
				
				// Can't teleport if home location is no longer claimed territory.
				// Permission from settings
				DominionsHome.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("show") || args[0].equalsIgnoreCase("s")) {
				
				// Anyone
				DominionsShow.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("top")) {
				
				// Anyone
				DominionsTop.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("list")) {
				
				// Anyone
				DominionsList.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("map") || args[0].equalsIgnoreCase("m")) {
				
				// Anyone
				DominionsMap.run(sender, args);
				
			} else if(args[0].equalsIgnoreCase("log")) {
				
				// Permission from settings
				DominionsLog.run(sender, args);
				
			} else {
				
				// Anyone
				DominionsHelp.run(sender, args);
				
			}
			
			
		} else {
			
			// Display help command
			DominionsHelp.run(sender, args);
			
		}
		
		return true;
	}
	
}