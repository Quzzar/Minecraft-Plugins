package com.quzzar.server.moreitems.spells;

import org.bukkit.Sound;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import com.quzzar.server.moreitems.MoreItems;

public class ToxicFumes
{
  public static String spellName = "ToxicFumes";
  
  public ToxicFumes() {}
  
  public static void castSpell(Player player) { player.getWorld().playSound(player.getLocation(), 
      Sound.ENTITY_WITHER_SPAWN, 0.5F, 2.0F);
    
    DragonFireball ball = (DragonFireball)player.getWorld().spawn(player.getEyeLocation(), DragonFireball.class);
    ball.setShooter(player);
    ball.setIsIncendiary(true);
    ball.setVelocity(player.getLocation().getDirection().multiply(0.1D));
    ball.setMetadata(spellName, new FixedMetadataValue(MoreItems.getInstance(), true));
  }
}
