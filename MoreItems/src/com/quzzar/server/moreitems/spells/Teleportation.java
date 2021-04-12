package com.quzzar.server.moreitems.spells;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.server.dominions.misc.Teleporting;

public class Teleportation {
	public static String spellName = "Teleportation";

	public Teleportation() {
	}

	public static void bind(Player player, ItemStack itemBeingCastWith) {
		player.playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1.0F, 1.4F);
		convertToBinded(player.getDisplayName(), itemBeingCastWith);
		player.sendMessage(ChatColor.GREEN+"The wand has been bound towards you.");
	}

	public static void castSpell(Player player, Player teleportingTo) {
		Teleporting.teleport(player, teleportingTo.getLocation());
	}

	private static ItemStack convertToBinded(String playerName, ItemStack item) {
		ItemMeta im = item.getItemMeta();

		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GREEN + ChatColor.stripColor(playerName));
		im.setLore(lore);

		item.setItemMeta(im);

		return item;
	}
}
