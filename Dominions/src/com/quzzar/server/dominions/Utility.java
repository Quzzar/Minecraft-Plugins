package com.quzzar.server.dominions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.connorlinfoot.bountifulapi.BountifulAPI;
import com.quzzar.server.worldutils.showcoords.ShowCoordsManager;

public class Utility {
	
	public static void tellConsole(String message) {
		Bukkit.getConsoleSender().sendMessage("§5Dominions§5§l >> §a" + message);
	}
	
	public static void tellSender(CommandSender sender, String message) {
		sender.sendMessage("§5Dominions§5§l >> §7" + message);
	}
	
	public static void needsPermission(CommandSender sender) {
		sender.sendMessage("§cYou don't have permission to use this command!");
	}
	
	public static void sendTitle(Player player, String message) {
		BountifulAPI.sendTitle(player, 20, 20, 20, message, "");
	}
	
	public static void sendTitle(Player player, String message, String subMessage) {
		BountifulAPI.sendTitle(player, 20, 20, 20, message, ChatColor.ITALIC+subMessage);
	}
	
	public static void sendActionMessage(Player player, String message) {
		if(ShowCoordsManager.isShowing(player)) {
			player.sendMessage(message);
		} else {
			BountifulAPI.sendActionBar(player, message, 55);
		}
	}
	
	public static void addPotionEffect(Player player, PotionEffectType type, int amplifier, int seconds) {
		if(player.hasPotionEffect(type)) {
			player.removePotionEffect(type);
		}
		player.addPotionEffect(new PotionEffect(type, 20*seconds, amplifier));
	}
	
	public static OfflinePlayer getOfflinePlayer(String name) {
		for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			if(p!=null && p.getName() != null && p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}
		return null;
	}
	
	public static boolean addItemToInventory(Inventory inv, ItemStack i, Location loc){
		if(inventoryFull(inv, i)){
			loc.getWorld().dropItem(loc, i);
			return false;
		}else{
			inv.addItem(i);
			return true;
		}
	}
	
	public static boolean inventoryFull(Inventory inv, ItemStack item){
		boolean full = false;
		if(inv.firstEmpty()==-1){
			for(ItemStack i : inv.getContents()){
				if(i!=null){
					if(i.isSimilar(item) && i.getAmount()<i.getMaxStackSize()){
						return false;
					}else{
						full = true;
					}
				}
			}
		}
		return full;
	}
	
	
	
}
