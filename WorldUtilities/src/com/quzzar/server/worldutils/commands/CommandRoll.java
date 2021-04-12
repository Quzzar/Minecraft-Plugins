package com.quzzar.server.worldutils.commands;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class CommandRoll implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(!sender.hasPermission("general.roll")) {
			sender.sendMessage(ChatColor.RED+"You don't have permission to use this command!");
			return true;
		}
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			if(args.length==2) {
				
				try {
					
					int amt = Integer.parseInt(args[0]);
					int type = Integer.parseInt(args[1]);
					
					if(amt >= 1 && amt <= 4) {
						
						if(type >= 2 && type <= 100) {
							
							rollDice(amt, type, p, 6);
							return true;
							
						} else {
							p.sendMessage(ChatColor.RED+"Invalid roll! You may only roll dice with between 2 to 100 sides.");
							return true;
						}
						
					} else {
						p.sendMessage(ChatColor.RED+"Invalid roll! You may only roll between 1 to 4 dice at a time.");
						return true;
					}
					
				}catch(Exception e) {
					p.sendMessage(ChatColor.RED+"Invalid roll! Ex: '/roll 2 6' (Rolls two, 6-sided dice)");
					return true;
				}
				
			} else {
				p.sendMessage(ChatColor.RED+"Invalid roll! /roll <amount of dice> <dice type>");
				return true;
			}
			
		} else {
			sender.sendMessage(ChatColor.RED+"Only a player can use this command!");
			return true;
		}
		
	}
	
	private void rollDice(int amt, int type, Player p, int distance) {
		
		Random rand = new Random();
		
		ArrayList<Integer> rolls = new ArrayList<Integer>();
		int totalVal = 0;
		
		for(int i = 0; i<amt; i++) {
			
			int r = rand.nextInt(type)+1;
			
			rolls.add(r);
			totalVal = totalVal+r;
		}
		
		
		for(Entity entity : p.getWorld().getNearbyEntities(p.getLocation(), distance, distance, distance)) {
			if(entity instanceof Player) {
				Player pFound = (Player) entity;
				
				pFound.sendMessage(ChatColor.GRAY+p.getDisplayName()+" begins to roll "+amt+", "+type+"-sided dice ("+amt+"d"+type+")!");
				pFound.sendMessage(ChatColor.AQUA+"...");
				if(rolls.size()>1) {
					for(int i = 0; i<rolls.size(); i++) {
						pFound.sendMessage(ChatColor.GRAY+"Dice "+(i+1)+" is a "+ChatColor.AQUA+rolls.get(i)+ChatColor.GRAY+
								" out of "+ChatColor.AQUA+type);
					}
					pFound.sendMessage(ChatColor.GRAY+"In total, that's "+ChatColor.AQUA+totalVal+ChatColor.GRAY+
							" out of a potential "+ChatColor.AQUA+amt*type+ChatColor.GRAY+"!");
				}else {
					pFound.sendMessage(ChatColor.GRAY+"It's a "+ChatColor.AQUA+rolls.get(0)+ChatColor.GRAY+
							" out of "+ChatColor.AQUA+type+ChatColor.GRAY+"!");
				}
			}
		}
		
	}
	
	
	
}
