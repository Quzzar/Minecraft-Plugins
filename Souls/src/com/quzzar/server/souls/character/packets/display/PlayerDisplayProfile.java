package com.quzzar.server.souls.character.packets.display;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.mojang.authlib.properties.Property;
import com.quzzar.server.souls.character.PlayerCharacter;
import com.quzzar.server.souls.character.packets.skin.SkinManager;
import com.quzzar.server.souls.limbo.LimboWelcome;

public class PlayerDisplayProfile {
	
	public static void apply(PlayerCharacter pChar, boolean teleport) {
		
		PlayerProfile pPro = pChar.getPlayer().getPlayerProfile();
		
		pPro.removeProperty("textures");
		Property property = SkinManager.getSkinTexture(pChar.getSkin());
		pPro.setProperty(new ProfileProperty(property.getName(), property.getValue(), property.getSignature()));
		
		pPro.setName(pChar.getName());
		
		pChar.getPlayer().setPlayerProfile(pPro);
		
		pChar.getPlayer().setDisplayName(pChar.getName());
		pChar.getPlayer().setCustomName(pChar.getName());
		
		if(teleport) {
			Location currentLoc = pChar.getPlayer().getLocation().clone();
			
			World worldTo = Bukkit.getWorlds().get(0);
			if(currentLoc.getWorld().equals(worldTo)) {
				worldTo = Bukkit.getWorlds().get(1);
			}
			
			pChar.getPlayer().teleport(worldTo.getSpawnLocation());
			pChar.getPlayer().teleport(currentLoc);
			LimboWelcome.applyLimboFeatures(pChar.getPlayer());
		}
		
	}
	
}
