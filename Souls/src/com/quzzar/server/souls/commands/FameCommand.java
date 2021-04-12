package com.quzzar.server.souls.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.souls.Utility;
import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;

public class FameCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(args.length==0 && sender instanceof Player) {
			
			if(sender.hasPermission("souls.commands.fame")) {
				
				Player player = (Player) sender;
				PlayerCharacter pChar = CharacterManager.getCharacter(player);
				
				double roundedValue = Math.round(pChar.getData().getFame().getValue() * 100.0) / 100.0;
				
				player.sendMessage(
						ChatColor.GOLD+
						"Your Fame: "+
						ChatColor.BOLD+
						roundedValue);
				
			} else {
				Utility.needsPermission(sender);
			}
			
		} else if(args.length==1) {
			
			if(sender.hasPermission("souls.commands.fameother")) {
				
				Player checkedPlayer = Bukkit.getPlayer(args[0]);
				
				if(checkedPlayer != null) {
					
					PlayerCharacter pChar = CharacterManager.getCharacter(checkedPlayer);
					
					double roundedFame = Math.round(pChar.getData().getFame().getFame() * 100.0) / 100.0;
					double roundedInfamy = Math.round(pChar.getData().getFame().getInfamy() * 100.0) / 100.0;
					
					sender.sendMessage(
							ChatColor.GOLD+
							pChar.getName()+"'s Fame: "+
							ChatColor.BOLD+
							roundedFame);
					sender.sendMessage(
							ChatColor.GOLD+
							pChar.getName()+"'s Infamy: "+
							ChatColor.BOLD+
							roundedInfamy);
					
				} else {
					
					sender.sendMessage(ChatColor.RED+"\""+args[0]+"\" could not be found!");
					
				}
				
			} else {
				Utility.needsPermission(sender);
			}
			
		} else {
			
			sender.sendMessage(ChatColor.RED+"/fame");
			
		}
		
		return true;
	}
	
	
}