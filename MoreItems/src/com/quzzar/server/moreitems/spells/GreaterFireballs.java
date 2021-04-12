package com.quzzar.server.moreitems.spells;

import org.bukkit.Sound;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import com.quzzar.server.moreitems.MoreItems;

public class GreaterFireballs
{
  public static String spellName = "GreaterFireballs";
  
  public GreaterFireballs() {}
  
  public static void castSpell(Player player) { player.getWorld().playSound(player.getLocation(), 
      Sound.ENTITY_GHAST_SHOOT, 1.0F, 1.2F);
    
    LargeFireball ball = (LargeFireball)player.getWorld().spawn(player.getEyeLocation(), LargeFireball.class);
    ball.setShooter(player);
    ball.setIsIncendiary(true);
    ball.setVelocity(player.getLocation().getDirection().multiply(2.2D));
    ball.setMetadata(spellName, new FixedMetadataValue(MoreItems.getInstance(), Boolean.valueOf(true)));
  }
}
