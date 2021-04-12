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

public class EssenceCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(args.length==0 && sender instanceof Player) {
			
			if(sender.hasPermission("souls.commands.essence")) {
				
				Player player = (Player) sender;
				
				player.sendMessage(
						ChatColor.LIGHT_PURPLE+
						"Your Remaining Essence: "+
						ChatColor.BOLD+
						getEssence(player));
				
			} else {
				Utility.needsPermission(sender);
			}
			
		} else if(args.length==1) {
			
			if(sender.hasPermission("souls.commands.essenceother")) {
				
				Player checkedPlayer = Bukkit.getPlayer(args[0]);
				
				if(checkedPlayer != null) {
					
					sender.sendMessage(
							ChatColor.LIGHT_PURPLE+
							args[0]+"'s Remaining Essence: "+
							ChatColor.BOLD+
							getEssence(checkedPlayer));
					
				} else {
					
					sender.sendMessage(ChatColor.RED+"\""+args[0]+"\" could not be found!");
					
				}
				
			} else {
				Utility.needsPermission(sender);
			}
			
		} else {
			
			sender.sendMessage(ChatColor.RED+"/essence");
			
		}
		
		return true;
	}
	
	private String getEssence(Player p) {
		PlayerCharacter pChar = CharacterManager.getCharacter(p);
		double roundedEssence = Math.round(pChar.getData().getEssence().getEssence()*100.0);
		return roundedEssence+"%";
	}
	
}