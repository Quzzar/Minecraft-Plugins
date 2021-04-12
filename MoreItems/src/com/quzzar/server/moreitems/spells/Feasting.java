package com.quzzar.server.moreitems.spells;

import java.util.ArrayList;
import java.util.SplittableRandom;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import com.quzzar.ge.core.buylist.ItemTag;
import com.quzzar.server.moreitems.MoreItems;

public class Feasting {
	
	private static final int foodMin = 1, foodMax = 2;
	private static SplittableRandom splitRand = new SplittableRandom();
	
	public static String spellName = "Feasting";

	public static void castSpell(Player player) {
		player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1.0F, 1.7F);
		ShulkerBullet proj = (ShulkerBullet) player.getWorld().spawn(player.getEyeLocation(), ShulkerBullet.class);
		proj.setShooter(player);
		proj.setVelocity(player.getLocation().getDirection().multiply(1.5D));
		proj.setMetadata(spellName, new FixedMetadataValue(MoreItems.getInstance(), Boolean.valueOf(true)));
	}
	
	public static ArrayList<ItemStack> getRandomFoods() {
		ArrayList<ItemStack> foodList = new ArrayList<ItemStack>();
		int amt = splitRand.nextInt(foodMin, foodMax+1);
		for(int i = 0; i < amt; i++) {
			foodList.add(ItemTag.PREPARED_FOODS.getList()
					.get(splitRand.nextInt(ItemTag.PREPARED_FOODS.getList().size())).toItemStack());
		}
		return foodList;
	}
	
}
