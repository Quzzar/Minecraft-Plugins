package com.quzzar.server.dominions.misc;

import org.bukkit.ChatColor;

public enum PermissionCategory {

	LeadersOnly(7, ChatColor.BLUE),
	GeneralsAndUp(6, ChatColor.GREEN),
	VeteransAndUp(5, ChatColor.GOLD),
	MembersAndUp(4, ChatColor.LIGHT_PURPLE),
	AllMembersAndAllies(3, ChatColor.AQUA),
	AnyoneButEnemies(2, ChatColor.YELLOW),
	Anyone(1, ChatColor.WHITE);
	
	private int power;
	private ChatColor color;
	
	private PermissionCategory(int power, ChatColor color) {
		this.power = power;
		this.color = color;
	}
	
	public int getPower() {
		return power;
	}
	
	public ChatColor getColor() {
		return color;
	}
	
	public static PermissionCategory getFromRank(PlayerRank rank) {
		if(rank.equals(PlayerRank.Member)) {
			return PermissionCategory.MembersAndUp;
		}
		if(rank.equals(PlayerRank.Veteran)) {
			return PermissionCategory.VeteransAndUp;
		}
		if(rank.equals(PlayerRank.General)) {
			return PermissionCategory.GeneralsAndUp;
		}
		if(rank.equals(PlayerRank.Leader)) {
			return PermissionCategory.LeadersOnly;
		}
		return PermissionCategory.Anyone;
	}
	
}
