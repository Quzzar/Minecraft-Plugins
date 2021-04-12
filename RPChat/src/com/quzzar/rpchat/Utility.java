package com.quzzar.rpchat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.misc.DiplomacyState;

public class Utility {
	
	
	public static void tellConsole(String message){
		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE+"["+ChatColor.AQUA+"RP Chat"+ChatColor.LIGHT_PURPLE+"] "+message);
	}
	
	public static void tellConsoleBullet(String message){
		Bukkit.getConsoleSender().sendMessage("§a§l > §r"+message);
	}
	
	public static void tellSender(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.LIGHT_PURPLE+"["+ChatColor.AQUA+"RP Chat"+ChatColor.LIGHT_PURPLE+"] "+message);
	}
	
	public static void needsPermission(CommandSender sender) {
		sender.sendMessage("§cYou don't have permission to use this command!");
	}
	
	public static void sendPlayerMessage(String channelPrefix, Player sender, Player receiver, String message) {
		StringBuilder strBuilder = new StringBuilder();
		
		if(channelPrefix.length()!=0) {
			strBuilder.append(channelPrefix);
			strBuilder.append(" ");
		}
		
		String rankPrefix = RPChat.getRankPrefixManager().getRankPrefix(sender);
		if(rankPrefix.length()!=0) {
			strBuilder.append(rankPrefix);
			strBuilder.append(" ");
		}
		
		strBuilder.append(getDiplomacyColor(sender, receiver)+sender.getDisplayName());
		strBuilder.append(" §8-§r ");
		strBuilder.append(message);
		
		receiver.sendMessage(strBuilder.toString());
	}
	
	private static ChatColor getDiplomacyColor(Player sender, Player receiver) {
		
		Dominion receiverDom = DominionUtils.getDominionByPlayer(receiver.getUniqueId());
		
		// If self, return gray
		if(sender.equals(receiver)) {
			return ChatColor.GRAY;
		}
		
		if(receiverDom != null) {
			DiplomacyState state = receiverDom.getDiplomacy(sender.getUniqueId());
			
			if(state.equals(DiplomacyState.Neutral)) { // Make neutral white instead
				return ChatColor.WHITE;
			} else {
				return state.getPrimaryColor();
			}
			
		} else {
			// If reciever has no dominion, give them white
			return ChatColor.WHITE;
		}
		
	}
	
	public static ItemStack[] clean(final ItemStack[] v) {
	    List<ItemStack> list = new ArrayList<ItemStack>(Arrays.asList(v));
	    list.removeAll(Collections.singleton(null));
	    return list.toArray(new ItemStack[list.size()]);
	}
	
}
