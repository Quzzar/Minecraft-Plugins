package com.quzzar.server.souls.commands;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.connorlinfoot.bountifulapi.BountifulAPI;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.souls.character.souls.SoulManager;

public class GiveSoulCommand implements CommandExecutor {

	// > /givesoul <player> <amount>
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(!(sender instanceof Player)) {
			
			OfflinePlayer checkedPlayer = Utility.getOfflinePlayer(args[0]);
			
			if(checkedPlayer != null) {
				
				int amount = 0;
				try {
					amount = Integer.parseInt(args[1]);
				} catch (Exception e) {
					sender.sendMessage(ChatColor.RED+"\""+args[1]+"\" is not a number!");
					return true;
				}
				
				SoulManager.update(checkedPlayer.getUniqueId(), SoulManager.getSoulAmount(checkedPlayer.getUniqueId())+amount);
				sender.sendMessage(ChatColor.GOLD+args[0]+" has been given "+amount+" soul(s).");
				
				if(checkedPlayer.isOnline()) {
					if(amount > 1) {
						String message = ChatColor.GOLD+""+ChatColor.BOLD+"You've been given "+amount+" souls.";
						BountifulAPI.sendActionBar(checkedPlayer.getPlayer(), message, 80);
						checkedPlayer.getPlayer().sendMessage(message);
					} else {
						String message = ChatColor.GOLD+""+ChatColor.BOLD+"You've been given a soul.";
						BountifulAPI.sendActionBar(checkedPlayer.getPlayer(), message, 80);
						checkedPlayer.getPlayer().sendMessage(message);
					}
				}
				
			} else {
				sender.sendMessage(ChatColor.RED+"\""+args[0]+"\" could not be found!");
			}
			
		} else {
			sender.sendMessage(ChatColor.RED+"Sorry, this command can only be sent via console.");
		}
		
		return true;
	}
	
	
}