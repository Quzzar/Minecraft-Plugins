package com.quzzar.server.souls.character.packets.skin;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class SkinManager {

	private static Set<GameProfile> skinProfiles;
	
	public static void load() {
		
		SkinDatabase database = new SkinDatabase();
		skinProfiles = database.loadSkins();
		
	}
	
	public static Set<GameProfile> getAllSkins() {
		return skinProfiles;
	}
	
	public static GameProfile getSkinProfile(UUID skinUUID) {
		for(GameProfile profile : skinProfiles) {
			if(profile.getId().equals(skinUUID)){
				return profile;
			}
		}
		return null;
	}
	
	public static Property getSkinTexture(UUID skinUUID) {
		GameProfile profile = getSkinProfile(skinUUID);
		if(profile != null) {
			return Iterables.get(profile.getProperties().get("textures"), 0);
		} else {
			return null;
		}
	}
	
	public static ItemStack getHead(GameProfile profile) {
		
		if(profile == null) {return null;}
		
		ItemStack head = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta headMeta = (SkullMeta) head.getItemMeta();
		
		Field profileField = null;
		try {
			profileField = headMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(headMeta, profile);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		head.setItemMeta(headMeta);
		
		return head;
		
	}
	
}
