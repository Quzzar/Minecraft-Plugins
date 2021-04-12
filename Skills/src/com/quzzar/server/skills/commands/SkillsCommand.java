package com.quzzar.server.skills.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.skills.SkillSet;
import com.quzzar.server.skills.Skills;
import com.quzzar.server.skills.Utility;

public class SkillsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(sender.hasPermission("skills.commands.skills")) {
			
			if(args.length==0 && sender instanceof Player) {
				
				Player player = (Player) sender;
				
				player.sendMessage(ChatColor.DARK_AQUA+""+ChatColor.BOLD+
						"Your Skills");
				displaySkills(sender, player.getUniqueId());
				
			} else if(args.length==1) {
				
				Player checkedPlayer = Bukkit.getPlayer(args[0]);
				
				if(checkedPlayer != null) {
					
					sender.sendMessage(ChatColor.DARK_AQUA+""+ChatColor.BOLD+
							args[0]+"'s Skills");
					displaySkills(sender, checkedPlayer.getUniqueId());
					
				} else {
					
					sender.sendMessage(ChatColor.RED+"\""+args[0]+"\" could not be found!");
					
				}
				
			} else {
				
				sender.sendMessage(ChatColor.RED+"/skills [player]");
				
			}
			
		} else {
			
			Utility.needsPermission(sender);
			
		}
		
		return true;
	}
	
	private void displaySkills(CommandSender sender, UUID uuid) {
		
		SkillSet skillSet = Skills.getSkillManager().getSkillSet(uuid);
		
		sender.sendMessage(
				ChatColor.GOLD+""+ChatColor.BOLD+
				"Cutting: "+
				ChatColor.RESET+ChatColor.AQUA+
				skillSet.get(0));
		
		sender.sendMessage(
				ChatColor.GOLD+""+ChatColor.BOLD+
				"Farming: "+
				ChatColor.RESET+ChatColor.AQUA+
				skillSet.get(1));
		
		sender.sendMessage(
				ChatColor.GOLD+""+ChatColor.BOLD+
				"Mining: "+
				ChatColor.RESET+ChatColor.AQUA+
				skillSet.get(2));
		
		sender.sendMessage(
				ChatColor.GOLD+""+ChatColor.BOLD+
				"Fighting: "+
				ChatColor.RESET+ChatColor.AQUA+
				skillSet.get(3));
		
		sender.sendMessage(
				ChatColor.GOLD+""+ChatColor.BOLD+
				"Digging: "+
				ChatColor.RESET+ChatColor.AQUA+
				skillSet.get(4));
		
	}
	
	
}