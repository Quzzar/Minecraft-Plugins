package com.quzzar.server.moreitems.spells;

import org.bukkit.Sound;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Player;

import com.quzzar.server.moreitems.MoreItems;

public class DeadlyFumes
{
  public static String spellName = "DeadlyFumes";
  
  public DeadlyFumes() {}
  
  public static void castSpell(Player player) { player.getWorld().playSound(player.getLocation(), 
      Sound.ENTITY_WITHER_SPAWN, 0.5F, 2.0F);
    
    DragonFireball ball = (DragonFireball)player.getWorld().spawn(player.getEyeLocation(), DragonFireball.class);
    ball.setShooter(player);
    ball.setIsIncendiary(true);
    ball.setVelocity(player.getLocation().getDirection().multiply(2.0D));
    ball.setMetadata(spellName, new org.bukkit.metadata.FixedMetadataValue(MoreItems.getInstance(), Boolean.valueOf(true)));
  }
}
