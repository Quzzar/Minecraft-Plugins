package com.quzzar.server.moreitems.spells;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.metadata.FixedMetadataValue;

import com.quzzar.server.moreitems.MoreItems;

public class Healing {
	
	public static String spellName = "Healing";

	public static void castSpell(Player player) {
		player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1.0F, 1.7F);
		ShulkerBullet proj = (ShulkerBullet) player.getWorld().spawn(player.getEyeLocation(), ShulkerBullet.class);
		proj.setShooter(player);
		proj.setVelocity(player.getLocation().getDirection().multiply(1.5D));
		proj.setMetadata(spellName, new FixedMetadataValue(MoreItems.getInstance(), Boolean.valueOf(true)));
	}
	
}
