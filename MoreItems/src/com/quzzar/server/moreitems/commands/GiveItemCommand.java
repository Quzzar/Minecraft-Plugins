package com.quzzar.server.moreitems.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.quzzar.server.moreitems.ItemUtils;
import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;

public class GiveItemCommand implements CommandExecutor, TabCompleter {

	private static String commandLayout = "/giveitem <item_name> [amount]";
	
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(!sender.hasPermission("give.item")) {
			sender.sendMessage(ChatColor.RED+"You don't have permission to use this command!");
			return true;
		}
		
		if (sender instanceof Player) {
			Player p = (Player) sender;

			if (args.length == 1) {

				ItemType itemType = ItemType.getFromName(args[0]);
				if(itemType != ItemType.NULL) {
					
					ItemUtils.addItemToInventory(p.getInventory(), ItemManager.getItemClone(itemType), p.getLocation());
					sender.sendMessage(ChatColor.GREEN+"You have been given a "+itemType.name());
					
				} else {
					sender.sendMessage(ChatColor.RED+"The item '"+args[0]+"' does not exist!");
					sender.sendMessage(ChatColor.YELLOW+"Here are all the possible options:");
					sender.sendMessage(ChatColor.YELLOW+Arrays.toString(ItemType.values()));
				}
				
			} else if (args.length == 2) {
				
				try {
					
					int amount = (int) Double.parseDouble(args[1]);
					
					if(amount > 64 || amount <= 0) {
						sender.sendMessage(ChatColor.RED+"You may only get an amount between 1 and 64!");
						return true;
					}
					
					ItemType itemType = ItemType.getFromName(args[0]);
					if(itemType != ItemType.NULL) {
						
						ItemStack item = ItemManager.getItemClone(itemType);
						item.setAmount(amount);
						ItemUtils.addItemToInventory(p.getInventory(), item, p.getLocation());
						sender.sendMessage(ChatColor.GREEN+"You have been given some "+itemType.name());
						
					} else {
						sender.sendMessage(ChatColor.RED+"The item '"+args[0]+"' does not exist!");
						sender.sendMessage(ChatColor.YELLOW+"Here are all the possible options:");
						sender.sendMessage(ChatColor.YELLOW+Arrays.toString(ItemType.values()));
					}
					
				} catch (Exception e) {
					sender.sendMessage(ChatColor.RED+"\""+args[1]+"\" is not a whole number!");
				}
				
			} else {
				sender.sendMessage(ChatColor.RED+commandLayout);
			}
			
		} else {
			sender.sendMessage(ChatColor.RED+"Currently only players can use this command!");
		}

		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		
		if(args.length == 1) {
			
			String currentArg = args[args.length-1];
			
			List<String> options = new ArrayList<String>();
			for(ItemType itemType : ItemType.values()) {
				if(itemType.toString().toLowerCase().startsWith(currentArg.toLowerCase())) {
					options.add(itemType.toString());
				}
			}
			
			return options;
			
		} else if (args.length > 1){
			
			List<String> options = new ArrayList<String>();
			for(int i = 1; i <= 64; i++) {
				options.add(i+"");
			}
			
			return options;
			
		}
		
		return null;
	}
}
