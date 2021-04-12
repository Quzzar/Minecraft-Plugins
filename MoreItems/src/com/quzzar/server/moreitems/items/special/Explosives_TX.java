package com.quzzar.server.moreitems.items.special;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import com.quzzar.server.dominions.DominionsMain;
import com.quzzar.server.moreitems.MoreItems;

public class Explosives_TX implements Special {

	public void onInteract(final PlayerInteractEvent e, ItemStack item) {

		if(e.getClickedBlock() == null) {
			return;
		}
		
		if ((DominionsMain.getZoneManager().isSafeClaimed(e.getClickedBlock().getChunk()))
				|| (DominionsMain.getZoneManager().isNeutralClaimed(e.getClickedBlock().getChunk()))) {
			return;
		}

		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MoreItems.getInstance(),
					new Runnable() {
						public void run() {
							e.getClickedBlock().setType(Material.AIR);
							e.getClickedBlock().getWorld().createExplosion(e.getClickedBlock().getLocation(), 10.0F,
									true);
						}

					}, 40L);
		}
	}

	public void onPlayerDamageEntity(EntityDamageByEntityEvent e, Player player, ItemStack item) {
	}

	public void onItemConsume(PlayerItemConsumeEvent e, ItemStack item) {
	}
}
