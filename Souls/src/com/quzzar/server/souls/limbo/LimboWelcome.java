package com.quzzar.server.souls.limbo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.connorlinfoot.bountifulapi.BountifulAPI;
import com.quzzar.server.souls.Souls;
import com.quzzar.server.souls.centeredtext.CenterText;

public class LimboWelcome {

	public static void welcomePlayer(Player player) {
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Souls.instance, new Runnable() {
			public void run() {
				
				BountifulAPI.sendTitle(player, 25, 55, 30, "§aWelcome", "§e§oto");
				
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Souls.instance, new Runnable() {
					public void run() {
						
						BountifulAPI.sendTitle(player, 20, 60, 40, "§b§l§oSouls §3§l§oof §b§l§oVryn", "");
						
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Souls.instance, new Runnable() {
							public void run() {
								
								giveInfo(player);
								
							}
						}, 100);
						
					}
				}, 125);
				
			}
		}, 10);
		
	}
	
	private static void giveInfo(Player player) {
		CenterText.sendCenteredMessage(player,
				"§8§m-------§8[ "+ChatColor.GOLD+""+ChatColor.BOLD+
				"Welcome to "+
				"§b§l§oSouls §3§l§oof §b§l§oVryn"+
				ChatColor.GOLD+""+ChatColor.BOLD+
				"! §8]§m-------");
		CenterText.sendCenteredMessage(player,"§eYou're currently in Limbo, here you can run around and");
		CenterText.sendCenteredMessage(player,"§eexplore as much as you'd like.");
		CenterText.sendCenteredMessage(player,"§eTo leave and enter into the real world, you must use a");
		CenterText.sendCenteredMessage(player,"§esoul to create your new character.");
	}
	
	public static void welcomeAfterDeath(Player player) {
		
		player.playSound(player.getLocation(), Sound.MUSIC_CREDITS, 1f, 1f);
		LimboInvBuilder.drawLimboInv(player);
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Souls.instance, new Runnable() {
			public void run() {
				
				BountifulAPI.sendTitle(player, 25, 45, 15, "§aYour Soul has", "");
				
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Souls.instance, new Runnable() {
					public void run() {
						
						BountifulAPI.sendTitle(player, 20, 50, 20, "§d§lPerished", "");
						
					}
				}, 90);
				
			}
		}, 10);
		
	}
	
	public static void applyLimboFeatures(Player p) {
		
		LimboInvBuilder.drawLimboInv(p);
		p.setGameMode(GameMode.ADVENTURE);
		
	}
	
}
