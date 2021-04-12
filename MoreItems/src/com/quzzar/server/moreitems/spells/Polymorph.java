package com.quzzar.server.moreitems.spells;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import com.quzzar.server.moreitems.MoreItems;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;

public class Polymorph
{
  public static String spellName = "Polymorph";
  
  public Polymorph() {}
  
  public static final List<DisguiseType> polymorphTypes = Arrays.asList(
			DisguiseType.CREEPER,
			DisguiseType.BLAZE,
			DisguiseType.CAT,
			DisguiseType.CAVE_SPIDER,
			DisguiseType.CHICKEN,
			DisguiseType.COW,
			DisguiseType.DOLPHIN,
			DisguiseType.DONKEY,
			DisguiseType.DROWNED,
			DisguiseType.ENDERMAN,
			DisguiseType.EVOKER,
			DisguiseType.FOX,
			DisguiseType.GIANT,
			DisguiseType.HORSE,
			DisguiseType.HUSK,
			DisguiseType.ILLUSIONER,
			DisguiseType.IRON_GOLEM,
			DisguiseType.LLAMA,
			DisguiseType.MINECART_TNT,
			DisguiseType.MULE,
			DisguiseType.MUSHROOM_COW,
			DisguiseType.OCELOT,
			DisguiseType.PANDA,
			DisguiseType.PHANTOM,
			DisguiseType.PIG,
			DisguiseType.PIG_ZOMBIE,
			DisguiseType.PILLAGER,
			DisguiseType.POLAR_BEAR,
			DisguiseType.RABBIT,
			DisguiseType.RAVAGER,
			DisguiseType.SHEEP,
			DisguiseType.SKELETON,
			DisguiseType.SLIME,
			DisguiseType.SNOWMAN,
			DisguiseType.SPIDER,
			DisguiseType.SQUID,
			DisguiseType.TRADER_LLAMA,
			DisguiseType.TURTLE,
			DisguiseType.WITCH,
			DisguiseType.WITHER_SKELETON,
			DisguiseType.WOLF,
			DisguiseType.ZOMBIE);
  
  public static void castSpell(Player player) { player.getWorld().playSound(player.getLocation(), 
      Sound.ENTITY_ILLUSIONER_PREPARE_MIRROR, 1.0F, 1.5F);
    
    LlamaSpit spit = (LlamaSpit) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.LLAMA_SPIT);
    spit.setShooter(player);
    spit.setVelocity(player.getLocation().getDirection().multiply(1.0D));
    spit.setMetadata(spellName, new FixedMetadataValue(MoreItems.getInstance(), true));
  }
}
