package com.quzzar.server.moreitems.items.special;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import com.quzzar.server.dominions.misc.SimpleLocation;
import com.quzzar.server.dominions.misc.Teleporting;
import com.quzzar.server.worldutils.warps.WarpManager;

public class PlayerGuide implements Special {
	public PlayerGuide() {
	}

	public void onInteract(final PlayerInteractEvent e, ItemStack item) {

		e.setCancelled(true);
		SimpleLocation loc = WarpManager.getWarp("tutorial_room");
		if(loc != null) {
			Teleporting.teleport(e.getPlayer(), loc.toLocation());
		} else {
			e.getPlayer().sendMessage(ChatColor.RED+"Failed to find the tutorial! Please contact an admin about this.");
		}
		
	}

	public void onPlayerDamageEntity(EntityDamageByEntityEvent e, Player player, ItemStack item) {
	}

	public void onItemConsume(PlayerItemConsumeEvent e, ItemStack item) {
	}
}
