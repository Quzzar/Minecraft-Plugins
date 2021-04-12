package com.quzzar.rpchat.rankprefix;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class RankPrefix {
	
	private String prefix;
	private String permission;
	
	public RankPrefix(String name, String prefix) {
		
		this.permission = "rpchat.prefix."+name;
		this.prefix = ChatColor.translateAlternateColorCodes('&', prefix);
		
		Bukkit.getPluginManager().addPermission(new Permission(permission, PermissionDefault.FALSE));
		
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public String getPermission() {
		return permission;
	}
	
}
