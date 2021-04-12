package com.quzzar.server.moreitems.wands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.quzzar.server.moreitems.ItemUtils;
import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;
import com.quzzar.server.moreitems.spells.Teleportation;

public class WandOfTeleportation implements Wand {
	public WandOfTeleportation() {
	}

	public ItemStack getWand() {
		return ItemManager.getItemClone(ItemType.WAND_OF_TELEPORTATION);
	}

	public void rightClick(Player player, ItemStack wand) {
		if ((wand.hasItemMeta()) && (wand.getItemMeta().hasLore())) {
			String otherPlayerName = ChatColor.stripColor((String) wand.getItemMeta().getLore().get(0));
			Player otherPlayer = Bukkit.getPlayerExact(otherPlayerName);

			if (otherPlayer != null) {
				Teleportation.castSpell(player, otherPlayer);

				if (ItemUtils.percentChance(1.4)) {
					ItemUtils.breakItem(player, wand);
				}
			} else {
				player.sendMessage(ChatColor.GREEN+"The wand can't seem to locate its target.");
			}
		}
	}

	public void shiftRightClick(Player player, ItemStack wand) {
		Teleportation.bind(player, wand);
	}

	public void leftClick(Player player, ItemStack wand) {
	}

	public void shiftLeftClick(Player player, ItemStack wand) {
	}
}
