package com.quzzar.server.moreitems.spells;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class LightningBolts
{
  public static String spellName = "LightningBolts";
  
  private static Set<Material> lookThrough = new HashSet<Material>(Arrays.asList(new Material[] { Material.AIR }));
  
  public LightningBolts() {}
  
  public static void castSpell(Player player) { player.getWorld().playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.8F, 0.8F);
    
    Block block = player.getTargetBlock(lookThrough, 60);
    block.getWorld().playSound(block.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1.0F, 1.0F);
    player.getWorld().strikeLightning(block.getLocation());
  }
}
