package com.quzzar.server.souls.limbo.creation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import com.connorlinfoot.bountifulapi.BountifulAPI;
import com.quzzar.rpchat.announcements.Announcements;
import com.quzzar.server.souls.Souls;
import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;
import com.quzzar.server.souls.character.packets.display.PlayerDisplayProfile;
import com.quzzar.server.souls.character.souls.SoulManager;
import com.quzzar.server.souls.limbo.LimboInvBuilder;
import com.quzzar.server.souls.limbo.creation.charname.NameRecordManager;
import com.quzzar.server.souls.limbo.creation.charname.ValidUsername;

import api.anvilgui.AnvilGUI;

public class CreationManager {
	
	public static void create(Player p) {
		
		PlayerCharacter pChar = CharacterManager.getCharacter(p);
		
		if(!pChar.canCreateCharacter()) {
			p.sendMessage(ChatColor.RED+"Your soul is still recharging its essence!");
			p.sendMessage(ChatColor.RED+"You must wait for it to finish recharging before you can make a new character.");
			return;
		}
		
		if(NameRecordManager.containsName(pChar.getName())) {
			p.sendMessage(ChatColor.RED+"You must choose a new name for your next life!");
			return;
		}
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Souls.instance, new Runnable() {
			public void run() {
				
				pChar.getData().getSoul().revive();
				pChar.refresh();
				NameRecordManager.addName(pChar.getName());
				
				SoulManager.update(pChar.getData().getUUID(), pChar.getSoulCount()-1);
				
				teleportToWorld(pChar);
				
			}
		}, 1);
		
	}
	
	private static void teleportToWorld(PlayerCharacter pChar) {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mv tp "
				+ChatColor.stripColor(pChar.getName())+" world");
		pChar.getPlayer().setGameMode(GameMode.SURVIVAL);
		pChar.setMoney(350);
		
		LimboInvBuilder.setStartingInv(pChar.getPlayer());
		
		Bukkit.broadcastMessage(ChatColor.YELLOW+""+ChatColor.BOLD+"Welcome "+pChar.getName()+" to the world of Vryn!");
		BountifulAPI.sendActionBar(pChar.getPlayer(), "§a§oYou awake into a new world...", 85);
		
		Announcements.sendPrivateAnnouncement(pChar.getPlayer());
		
	}
	
	public static void setSkin(Player p) {
		
		p.openInventory(LimboInvBuilder.getSkinSelectionInvs().get(0));
		
	}
	
	public static void setName(Player p) {
		
		PlayerCharacter pChar = CharacterManager.getCharacter(p);
		
		new AnvilGUI.Builder()
	    .onComplete((player, charName) -> {
	    	
	    	boolean valid = ValidUsername.isValidName(charName);
	    	
	    	
			if(valid) {
				
				String capCharName = charName.substring(0, 1).toUpperCase() + charName.substring(1);
				pChar.getData().setName(capCharName);
				PlayerDisplayProfile.apply(pChar, false);
				
				pChar.getPlayer().sendMessage(ChatColor.GREEN+"Your name has been set to \""+pChar.getName()+"\".");
				
				return AnvilGUI.Response.close();
				
			} else {
				return AnvilGUI.Response.text("Invalid name!");
			}
	    	
	    })
	    .text("Character Name...")
	    .plugin(Souls.instance)
	    .open(pChar.getPlayer());
		
	}
	
	public static void sendPurchaseButton(Player player) {
		
		/*
		TextComponent purchase = new TextComponent("§8§l[§a§l Purchase §8§l] §a-§o Click to open our website");
		purchase.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://soulsofvryn.com/"));
		purchase.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				TextComponent.fromLegacyText("§a§oOpens to the Souls of Vryn website")));
		
		player.spigot().sendMessage(purchase);
		*/
		
		player.sendMessage(ChatColor.YELLOW+"This feature is currently unavailable!");
		
	}
	
}
