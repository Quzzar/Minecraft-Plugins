package com.quzzar.server.moreitems.items.special;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import com.quzzar.server.moreitems.ItemUtils;
import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;

public class SpecialManager implements Listener {
	private static HashMap<ItemStack, Special> specialItems = new HashMap<ItemStack, Special>();

	public SpecialManager() {
	}

	public static void initialize() {
		specialItems.put(ItemManager.getItemClone(ItemType.EXPLOSIVES_T1), new Explosives_T1());
		specialItems.put(ItemManager.getItemClone(ItemType.EXPLOSIVES_T2), new Explosives_T2());
		specialItems.put(ItemManager.getItemClone(ItemType.EXPLOSIVES_T3), new Explosives_T3());
		specialItems.put(ItemManager.getItemClone(ItemType.EXPLOSIVES_TX), new Explosives_TX());
		specialItems.put(ItemManager.getItemClone(ItemType.GRENZOS_ANGUISH), new GrenzosAnguish());
		specialItems.put(ItemManager.getItemClone(ItemType.SIVIAN_SCEPTER), new SivianScepter());
		specialItems.put(ItemManager.getItemClone(ItemType.TOLSIMIRIAN_RUNE), new TolsimirianRune());
		specialItems.put(ItemManager.getItemClone(ItemType.PORTABLE_CRAFTING_TABLE), new PortableCraftingTable());
		specialItems.put(ItemManager.getItemClone(ItemType.PLAYER_GUIDE), new PlayerGuide());
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onInteract(PlayerInteractEvent e) {
		ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
		ItemStack itemOffHand = e.getPlayer().getInventory().getItemInOffHand();

		for (Entry<ItemStack, Special> entry : specialItems.entrySet()) {
			if (item != null && ItemUtils.areSimilarNoLore(entry.getKey(), item)) {
				entry.getValue().onInteract(e, item);
				return;
			} else if (itemOffHand != null && ItemUtils.areSimilarNoLore(entry.getKey(), itemOffHand)) {
				entry.getValue().onInteract(e, itemOffHand);
				return;
			}
		}
		
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerDamageEntity(EntityDamageByEntityEvent e) {
		if ((e.getDamager() != null) && ((e.getDamager() instanceof Player))) {
			Player player = (Player) e.getDamager();
			ItemStack item = player.getInventory().getItemInMainHand();

			if (item != null) {
				for (Map.Entry<ItemStack, Special> entry : specialItems.entrySet()) {
					if (ItemUtils.areSimilarNoLore((ItemStack) entry.getKey(), item)) {
						((Special) entry.getValue()).onPlayerDamageEntity(e, player, item);
						return;
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onItemConsume(PlayerItemConsumeEvent e) {
		ItemStack item = e.getItem();

		if (item != null) {
			for (Map.Entry<ItemStack, Special> entry : specialItems.entrySet()) {
				if (ItemUtils.areSimilarNoLore((ItemStack) entry.getKey(), item)) {
					((Special) entry.getValue()).onItemConsume(e, item);
					return;
				}
			}
		}
	}
}
