package com.quzzar.server.moreitems.wands;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.quzzar.server.moreitems.ItemUtils;

public class WandListener implements Listener {
	public WandListener() {
	}

	@org.bukkit.event.EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		ItemStack item = e.getItem();

		if (item != null) {
			if (e.getPlayer().isSneaking()) {
				if ((e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
						|| (e.getAction().equals(Action.RIGHT_CLICK_AIR))) {

					for (Wand wand : WandManager.getWands()) {
						if (ItemUtils.areSimilarNoLore(item, wand.getWand())) {
							wand.shiftRightClick(e.getPlayer(), item);
							return;
						}
					}
					return;
				}
				if ((e.getAction().equals(Action.LEFT_CLICK_BLOCK)) || (e.getAction().equals(Action.LEFT_CLICK_AIR))) {

					for (Wand wand : WandManager.getWands()) {
						if (ItemUtils.areSimilarNoLore(item, wand.getWand())) {
							wand.shiftLeftClick(e.getPlayer(), item);
							return;
						}

					}

				}
			} else {
				if ((e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
						|| (e.getAction().equals(Action.RIGHT_CLICK_AIR))) {

					for (Wand wand : WandManager.getWands()) {
						if (ItemUtils.areSimilarNoLore(item, wand.getWand())) {
							wand.rightClick(e.getPlayer(), item);
							return;
						}
					}
					return;
				}
				if ((e.getAction().equals(Action.LEFT_CLICK_BLOCK)) || (e.getAction().equals(Action.LEFT_CLICK_AIR))) {

					for (Wand wand : WandManager.getWands()) {
						if (ItemUtils.areSimilarNoLore(item, wand.getWand())) {
							wand.leftClick(e.getPlayer(), item);
							return;
						}
					}
					return;
				}
			}
		}
	}
}
