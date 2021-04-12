package com.quzzar.server.moreitems.wands;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.quzzar.server.moreitems.ItemUtils;
import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;
import com.quzzar.server.moreitems.spells.Polymorph;

public class WandOfPolymorph implements Wand {
	public WandOfPolymorph() {
	}

	public ItemStack getWand() {
		return ItemManager.getItemClone(ItemType.WAND_OF_POLYMORPH);
	}

	public void rightClick(Player player, ItemStack wand) {
		Polymorph.castSpell(player);

		if (ItemUtils.percentChance(2.0)) {
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
