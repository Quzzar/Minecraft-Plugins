package com.quzzar.rpchat.announcements;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Announcements {

	private static List<List<TextComponent>> announcements = new ArrayList<List<TextComponent>>();
	private static SplittableRandom splitRand = new SplittableRandom();
	
	static {
		
		List<TextComponent> discord = new ArrayList<TextComponent>();
		TextComponent discord_1 = new TextComponent(ChatColor.GREEN+""+ChatColor.BOLD+"Did you know we have a Discord server?");
		discord_1.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "discord.soulsofvryn.com"));
		discord_1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				TextComponent.fromLegacyText("§a§oOpens the Souls of Vryn Discord")));
		TextComponent discord_2 = new TextComponent(ChatColor.BLUE+""+ChatColor.BOLD+"Click Here to Join!");
		discord_2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "discord.soulsofvryn.com"));
		discord_2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				TextComponent.fromLegacyText("§a§oOpens the Souls of Vryn Discord")));
		announcements.add(discord);
		
		List<TextComponent> commands = new ArrayList<TextComponent>();
		commands.add(new TextComponent(ChatColor.GREEN+""+ChatColor.BOLD+"Wanna know what commands you can type?"));
		commands.add(new TextComponent(ChatColor.BLUE+""+ChatColor.BOLD+"Type '/' and use tab complete!"));
		commands.add(new TextComponent(ChatColor.BLUE+""+ChatColor.BOLD+"That's everything you need!"));
		announcements.add(commands);
		
		List<TextComponent> dominions_1 = new ArrayList<TextComponent>();
		dominions_1.add(new TextComponent(ChatColor.GREEN+""+ChatColor.BOLD+"Want to join a dominion?"));
		dominions_1.add(new TextComponent(ChatColor.BLUE+""+ChatColor.BOLD+"Use /d join <name>."));
		dominions_1.add(new TextComponent(ChatColor.BLUE+""+ChatColor.BOLD+""+ChatColor.ITALIC+"Vyre's always accepting new members!"));
		announcements.add(dominions_1);
		
		List<TextComponent> local_chat = new ArrayList<TextComponent>();
		local_chat.add(new TextComponent(ChatColor.GREEN+""+ChatColor.BOLD+"Did you know that the server has proximity chat?"));
		local_chat.add(new TextComponent(ChatColor.BLUE+""+ChatColor.BOLD+"You can use /shout to talk globally"));
		local_chat.add(new TextComponent(ChatColor.BLUE+""+ChatColor.BOLD+"and /whisper to speak nearby!"));
		announcements.add(local_chat);
		
		List<TextComponent> dominions_2 = new ArrayList<TextComponent>();
		dominions_2.add(new TextComponent(ChatColor.GREEN+""+ChatColor.BOLD+"Not in a dominion?"));
		dominions_2.add(new TextComponent(ChatColor.GREEN+""+ChatColor.BOLD+"Ask around to see if anyone is recruiting!"));
		announcements.add(dominions_2);
		
		List<TextComponent> third_drop = new ArrayList<TextComponent>();
		third_drop.add(new TextComponent(ChatColor.GREEN+""+ChatColor.BOLD+"FYI, you only drop 1/3rd of your items on death in"));
		third_drop.add(new TextComponent(ChatColor.GREEN+""+ChatColor.BOLD+"the overworld and aether!"));
		announcements.add(third_drop);
		
	}
	
	public static void sendGlobalAnnouncement() {
		for(TextComponent text : announcements.get(splitRand.nextInt(announcements.size()))) {
			Bukkit.broadcast(text);
		}
	}
	
	public static void sendPrivateAnnouncement(Player p) {
		for(TextComponent text : announcements.get(splitRand.nextInt(announcements.size()))) {
			p.spigot().sendMessage(text);
		}
	}
	
}
