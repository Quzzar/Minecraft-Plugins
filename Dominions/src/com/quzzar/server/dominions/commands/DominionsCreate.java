package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.DominionManager;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;

public class DominionsCreate {
	
	public static String commandLayout = "/d create <name> [description]";
	
	public static void run(CommandSender sender, String[] args){
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(DominionUtils.getDominionByPlayer(player.getUniqueId())==null) {
				
				if(args.length==2){
					
					String domName = args[1].substring(0, Math.min(args[1].length(), 20));
					
					if(DominionUtils.getDominionByName(domName)==null) {
						
						PlayerCharacter pChar = CharacterManager.getCharacter(player);
						
						if(pChar.takeMoney(DominionManager.costToCreate)) {
							
							DominionManager.createDominion(player, domName, "A newly founded Dominion.");
							sender.sendMessage(ChatColor.GREEN+"Successfully created "+domName+"!");
							sender.sendMessage(
									ChatColor.BLUE+""+ChatColor.ITALIC+
									" - To change your description, use: "+ChatColor.AQUA+"/d desc <description>");
							sender.sendMessage(
									ChatColor.BLUE+""+ChatColor.ITALIC+
									" - To see an overview of your Dominion, use: "+ChatColor.AQUA+"/d show");
							sender.sendMessage(
									ChatColor.BLUE+""+ChatColor.ITALIC+
									" - To change member permissions, use: "+ChatColor.AQUA+"/d settings");
							sender.sendMessage(
									ChatColor.BLUE+""+ChatColor.ITALIC+
									" - To buy upgrades, use: "+ChatColor.AQUA+"/d upgrades");
							
						} else {
							sender.sendMessage(ChatColor.RED+"There is not enough money in your account to do that!");
						}
						
					}else {
						sender.sendMessage(ChatColor.RED+"A Dominion of that name already exists!");
					}
					
				} else if (args.length>2){
					
					String domName = args[1].substring(0, Math.min(args[1].length(), 20));
					
					if(DominionUtils.getDominionByName(domName)==null) {
						
						PlayerCharacter pChar = CharacterManager.getCharacter(player);
						
						if(pChar.takeMoney(DominionManager.costToCreate)) {
							
							StringBuilder desc = new StringBuilder();
							for(int i=2; i<args.length; i++){
								desc.append(args[i]);
								desc.append(" ");
							}
							
							String trimmedDesc = desc.toString().substring(0, Math.min(desc.toString().length(), 60));
							
							DominionManager.createDominion(player, domName, trimmedDesc);
							sender.sendMessage(ChatColor.GREEN+"Successfully created "+domName+"!");
							sender.sendMessage(
									ChatColor.BLUE+""+ChatColor.ITALIC+
									" - To see an overview of your Dominion, use: "+ChatColor.AQUA+"/d show");
							sender.sendMessage(
									ChatColor.BLUE+""+ChatColor.ITALIC+
									" - To change member permissions, use: "+ChatColor.AQUA+"/d settings");
							sender.sendMessage(
									ChatColor.BLUE+""+ChatColor.ITALIC+
									" - To buy upgrades, use: "+ChatColor.AQUA+"/d upgrades");
							
						} else {
							sender.sendMessage(ChatColor.RED+"There is not enough money in your account to do that!");
						}
						
					}else {
						sender.sendMessage(ChatColor.RED+"A Dominion of that name already exists!");
					}
					
				} else {
					failedCommand(sender);
				}
				
			} else {
				sender.sendMessage(ChatColor.RED+"You must leave your current Dominion first!");
			}
			
		} else {
			sender.sendMessage(ChatColor.RED+"Only players can use this command!");
		}
		
	}
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
}
