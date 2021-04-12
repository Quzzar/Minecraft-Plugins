package com.quzzar.server.moreitems.spells;

import org.bukkit.Sound;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;

import com.quzzar.server.moreitems.MoreItems;

public class GrandFireballs
{
  public static String spellName = "GrandFireballs";
  
  public GrandFireballs() {}
  
  public static void castSpell(Player player) { player.getWorld().playSound(player.getLocation(), 
      Sound.ENTITY_GHAST_SHOOT, 1.0F, 1.4F);
    
    LargeFireball ball = (LargeFireball)player.getWorld().spawn(player.getEyeLocation(), LargeFireball.class);
    ball.setShooter(player);
    ball.setIsIncendiary(true);
    ball.setVelocity(player.getLocation().getDirection().multiply(3.0D));
    ball.setMetadata(spellName, new org.bukkit.metadata.FixedMetadataValue(MoreItems.getInstance(), Boolean.valueOf(true)));
  }
}
