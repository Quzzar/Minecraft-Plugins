package com.quzzar.server.moreitems.items.special;

import java.util.SplittableRandom;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.quzzar.server.moreitems.ItemUtils;

public class GrenzosAnguish implements Special {
	
	private static SplittableRandom splitRand = new SplittableRandom();

	public void onInteract(PlayerInteractEvent e, ItemStack item) {
		
		if ((e.getAction().equals(Action.RIGHT_CLICK_AIR)) || (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
			Player p = e.getPlayer();

			e.setCancelled(true);
			if (!p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 2));

				p.getWorld().playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 0.5F, 1.0F);

				if (splitRand.nextInt(4) == 1) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 50, 2));
				}
				if (splitRand.nextInt(5) == 1) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
				}
				if (splitRand.nextInt(4) == 1) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 30, 2));
				}
				if (splitRand.nextInt(14) == 1) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 80, 1));
				}
				if (splitRand.nextInt(3) == 1) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 1));
				}
				if (splitRand.nextInt(16) == 1) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 120, 1));
				}
				if (splitRand.nextInt(16) == 1) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 2));
				}
				
				if (splitRand.nextInt(150) == 1) {
					ItemUtils.breakItem(e.getPlayer(), item);
				}
				
			}
		}
	}

	public void onPlayerDamageEntity(EntityDamageByEntityEvent e, Player player, ItemStack item) {
		
		if (splitRand.nextInt(100) == 1) {
			ItemUtils.breakItem(player, item);
		}
		
	}

	public void onItemConsume(PlayerItemConsumeEvent e, ItemStack item) {
	}
}
