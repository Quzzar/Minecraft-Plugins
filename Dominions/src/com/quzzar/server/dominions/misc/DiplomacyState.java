package com.quzzar.server.dominions.misc;

import org.bukkit.ChatColor;

public enum DiplomacyState {
	
	You(ChatColor.BLUE, ChatColor.DARK_BLUE),
	Ally(ChatColor.GREEN, ChatColor.DARK_GREEN),
	Neutral(ChatColor.GRAY, ChatColor.DARK_GRAY),
	Enemy(ChatColor.RED, ChatColor.DARK_RED),
	Null(ChatColor.GRAY, ChatColor.GRAY);
	
	
	private ChatColor primaryColor;
	private ChatColor secondaryColor;
	
	private DiplomacyState(ChatColor primaryColor, ChatColor secondaryColor) {
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
	}
	
	public ChatColor getPrimaryColor() {
		return primaryColor;
	}
	
	public ChatColor getSecondaryColor() {
		return secondaryColor;
	}
	
}
