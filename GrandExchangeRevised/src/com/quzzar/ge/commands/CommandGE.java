package com.quzzar.ge.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.ge.ChatUtils;
import com.quzzar.ge.core.InvType;
import com.quzzar.ge.presets.GeneralInventoryBuilder;

public class CommandGE implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender.hasPermission("grandexchange.commands.ge")) {
			
			
			if(args.length==0) {
				
				if(sender instanceof Player) {
					Player p = (Player) sender;
					
					p.openInventory(GeneralInventoryBuilder.getInventoryByType(InvType.MAIN_MENU, p.getUniqueId()));
					
				}else {
					ChatUtils.tellSender(sender, ChatColor.RED+"Only players can use this command!");
				}
				
			} else if (args.length==1) {
				
				Player p = Bukkit.getPlayer(args[0]);
				
				if(p!=null) {
					
					p.openInventory(GeneralInventoryBuilder.getInventoryByType(InvType.MAIN_MENU, p.getUniqueId()));
					
				}else {
					ChatUtils.tellSender(sender, ChatColor.RED+"Could not find a player by the name of '"+args[0]+"'!");
				}
				
			} else {
				
				failedCommand(sender);
				
			}
			
			
		} else {
			ChatUtils.tellSender(sender, ChatColor.RED+"You don't have the permission to use this command!");
		}
		
		return true;
	}
	
	
	private void failedCommand(CommandSender sender) {
		ChatUtils.tellSender(sender, ChatColor.YELLOW+"/grandexchange <player>");
	}
	
	
	
}
