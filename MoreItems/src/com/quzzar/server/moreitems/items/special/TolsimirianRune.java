package com.quzzar.server.moreitems.items.special;

import java.util.SplittableRandom;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import com.quzzar.server.moreitems.ItemUtils;
import com.quzzar.server.moreitems.MoreItems;

public class TolsimirianRune implements Special {
	
	private static SplittableRandom splitRand = new SplittableRandom();

	public void onInteract(PlayerInteractEvent e, ItemStack item) {
	}

	public void onPlayerDamageEntity(EntityDamageByEntityEvent e, Player player, ItemStack item) {
		final Entity entity = e.getEntity();

		if (!entity.isGlowing()) {
			entity.setGlowing(true);
			entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_EVOKER_PREPARE_WOLOLO, 1.0F, 1.0F);

			Bukkit.getScheduler().runTaskLater(MoreItems.getInstance(), new Runnable() {
				public void run() {
					entity.setGlowing(false);
				}
			}, 160L);
			
			if (splitRand.nextInt(150) == 1) {
				ItemUtils.breakItem(player, item);
			}
			
		}
	}

	public void onItemConsume(PlayerItemConsumeEvent e, ItemStack item) {
	}
}
