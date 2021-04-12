package com.quzzar.server.moreitems.spells;

import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

import com.quzzar.server.moreitems.MoreItems;

public class Fireballs
{
  public static String spellName = "Fireballs";
  
  public Fireballs() {}
  
  public static void castSpell(Player player) { player.getWorld().playSound(player.getLocation(), 
      Sound.ENTITY_GHAST_SHOOT, 1.0F, 1.0F);
    Fireball ball = (Fireball)player.getWorld().spawn(player.getEyeLocation(), Fireball.class);
    ball.setShooter(player);
    ball.setVelocity(player.getLocation().getDirection().multiply(1.5D));
    ball.setMetadata(spellName, new org.bukkit.metadata.FixedMetadataValue(MoreItems.getInstance(), Boolean.valueOf(true)));
  }
}
