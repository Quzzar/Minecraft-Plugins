package com.quzzar.server.moreitems;

import java.util.SplittableRandom;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {
	public ItemUtils() {
	}

	private static SplittableRandom splitRand = new SplittableRandom();

	public static void breakItem(Player player, ItemStack item) {
		item.setAmount(item.getAmount() - 1);
		player.getWorld().playSound(player.getLocation(), org.bukkit.Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
	}

	public static boolean percentChance(double percent) {
		percent /= 100.0D;
		return splitRand.nextDouble() < percent;
	}

	public static boolean isInventoryFull(Inventory inv, ItemStack item) {
		boolean full = false;
		if (inv.firstEmpty() == -1) {
			for (ItemStack i : inv.getContents()) {
				if (i != null) {
					if ((i.isSimilar(item)) && (i.getAmount() < i.getMaxStackSize())) {
						return false;
					}
					full = true;
				}
			}
		}

		return full;
	}

	public static boolean addItemToInventory(Inventory inv, ItemStack i, Location loc) {
		if (isInventoryFull(inv, i)) {
			loc.getWorld().dropItem(loc, i);
			return false;
		}
		inv.addItem(new ItemStack[] { i });
		return true;
	}

	public static boolean areSimilarNoLore(ItemStack item1, ItemStack item2) {

		if ((item1.hasItemMeta()) && (item1.getItemMeta().hasLore())) {
			item1 = item1.clone();
			ItemMeta im = item1.getItemMeta();
			im.setLore(null);
			item1.setItemMeta(im);
		}

		if ((item2.hasItemMeta()) && (item2.getItemMeta().hasLore())) {
			item2 = item2.clone();
			ItemMeta im = item2.getItemMeta();
			im.setLore(null);
			item2.setItemMeta(im);
		}

		return item1.isSimilar(item2);
	}
	
	public static boolean areSimilarNoDamage(ItemStack item1, ItemStack item2) {
		if(item1.hasItemMeta() && item2.hasItemMeta()
				&& item1.getItemMeta() instanceof Damageable
				&& item2.getItemMeta() instanceof Damageable) {
			
			Damageable dMeta1 = (Damageable) item1.getItemMeta();
			
			ItemStack tempItem2 = item2.clone();
			Damageable dMeta2 = (Damageable) tempItem2.getItemMeta();
			dMeta2.setDamage(dMeta1.getDamage());
			tempItem2.setItemMeta((ItemMeta) dMeta2);
			
			return item1.isSimilar(tempItem2);
		} else {
			return item1.isSimilar(item2);
		}
	}
	
}
