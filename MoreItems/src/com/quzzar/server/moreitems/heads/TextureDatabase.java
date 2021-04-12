package com.quzzar.server.moreitems.heads;

import java.util.UUID;

public enum TextureDatabase {
	
	// Upload at > https://mineskin.org/ 
	
	CRAFTING_TABLE("23c2263b-d5c6-425b-b11a-09d662ca1706",
			"http://textures.minecraft.net/texture/7ead389269c04feba791de17c5a7953c41c2cb3663b1346819b3c2ced31b2"),
	MOLTEN_CORE("0649810d-5146-4617-88c9-3cb448359f9e",
			"http://textures.minecraft.net/texture/1b9af7d2b4eba279ec6beb8af62b124fcbcb9fa165b4c0b9032199d1c9fa13c9");
	

	private String url;
	private UUID uuid;

	private TextureDatabase(String rawUUID, String url) {
		this.url = url;
		uuid = UUID.fromString(rawUUID);
	}

	public String getURL() {
		return url;
	}

	public UUID getUUID() {
		return uuid;
	}

	public static TextureDatabase getTexture(UUID inputUUID) {
		for (TextureDatabase tex : TextureDatabase.values()) {
			if (tex.getUUID().equals(inputUUID)) {
				return tex;
			}
		}
		return null;
	}

	public static TextureDatabase getTexture(String inputName) {
		for (TextureDatabase tex : TextureDatabase.values()) {
			if (tex.name().equalsIgnoreCase(inputName)) {
				return tex;
			}
		}
		return null;
	}
}
