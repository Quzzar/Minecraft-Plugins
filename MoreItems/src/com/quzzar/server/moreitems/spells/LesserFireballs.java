package com.quzzar.server.moreitems.spells;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;

import com.quzzar.server.moreitems.MoreItems;

public class LesserFireballs
{
  public static String spellName = "LesserFireballs";
  
  public LesserFireballs() {}
  
  public static void castSpell(Player player) { player.getWorld().playSound(player.getLocation(), 
      Sound.ENTITY_GHAST_SHOOT, 1.0F, 1.0F);
    SmallFireball ball = (SmallFireball)player.getWorld().spawn(player.getEyeLocation(), SmallFireball.class);
    ball.setShooter(player);
    ball.setVelocity(player.getLocation().getDirection().multiply(0.5D));
    ball.setMetadata(spellName, new org.bukkit.metadata.FixedMetadataValue(MoreItems.getInstance(), Boolean.valueOf(true)));
  }
}
