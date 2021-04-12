package com.quzzar.server.moreitems.spells;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class GreaterLightningBolts
{
  public static String spellName = "GreaterLightningBolts";
  
  private static Set<Material> lookThrough = new HashSet<Material>(Arrays.asList(new Material[] { Material.AIR }));
  
  public GreaterLightningBolts() {}
  
  public static void castSpell(Player player) { player.getWorld().playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0F, 1.0F);
    
    Block block = player.getTargetBlock(lookThrough, 120);
    block.getWorld()
      .createExplosion(block.getX(), block.getY(), block.getZ(), 
      2.0F, false, false);
    block.getWorld().playSound(block.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0F, 1.0F);
    player.getWorld().strikeLightning(block.getLocation());
  }
}
