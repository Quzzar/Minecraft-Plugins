package com.quzzar.server.moreitems.heads;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class HeadUtil {

	public static ItemStack makeRawItemStack(TextureDatabase tex, int amt) {
		ItemStack head = new ItemStack(Material.PLAYER_HEAD, amt);
		if (tex.getURL().isEmpty()) {
			return head;
		}
		SkullMeta headMeta = (SkullMeta) head.getItemMeta();
		GameProfile profile = new GameProfile(tex.getUUID(), null);
		byte[] encodedData = Base64.encodeBase64(
				String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { tex.getURL() }).getBytes());
		profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
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

	public static TextureDatabase getTexture(ItemStack i) {
		return TextureDatabase.getTexture(getUUID(i));
	}

	public static boolean checkMechanical(ItemStack i) {
		if ((i != null) && (i.getType().equals(Material.PLAYER_HEAD)) && (i.hasItemMeta())
				&& (TextureDatabase.getTexture(getUUID(i)) != null)) {
			return true;
		}

		return false;
	}

	public static UUID getUUID(ItemStack i) {
		SkullMeta headMeta = (SkullMeta) i.getItemMeta();

		Field profileField = null;
		try {
			profileField = headMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);

			GameProfile gp = (GameProfile) profileField.get(headMeta);

			return gp.getId();
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
