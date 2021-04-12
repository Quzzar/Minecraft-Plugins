package com.quzzar.server.moreitems.wands;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.quzzar.server.moreitems.ItemUtils;
import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;
import com.quzzar.server.moreitems.spells.Feasting;

public class WandOfFeasting implements Wand {

	public ItemStack getWand() {
		return ItemManager.getItemClone(ItemType.WAND_OF_FEASTING);
	}

	public void rightClick(Player player, ItemStack wand) {
		Feasting.castSpell(player);

		if (ItemUtils.percentChance(0.7)) {
			ItemUtils.breakItem(player, wand);
		}
	}

	public void shiftRightClick(Player player, ItemStack wand) {
	}

	public void leftClick(Player player, ItemStack wand) {
	}

	public void shiftLeftClick(Player player, ItemStack wand) {
	}
}
