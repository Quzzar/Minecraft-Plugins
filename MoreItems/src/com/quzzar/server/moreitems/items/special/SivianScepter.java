package com.quzzar.server.moreitems.items.special;

import java.util.SplittableRandom;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import com.quzzar.server.moreitems.ItemUtils;

public class SivianScepter implements Special {
	
	private static SplittableRandom splitRand = new SplittableRandom();

	public void onInteract(PlayerInteractEvent e, ItemStack item) {
		
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Block b = e.getClickedBlock();

			if (b.getType().isSolid()) {
				BlockBreakEvent eventBreak = new BlockBreakEvent(b, e.getPlayer());
				org.bukkit.Bukkit.getServer().getPluginManager().callEvent(eventBreak);

				if (!eventBreak.isCancelled()) {
					Location centerBlockLoc = b.getLocation().clone();
					centerBlockLoc.setX(centerBlockLoc.getX() + 0.5D);
					centerBlockLoc.setZ(centerBlockLoc.getZ() + 0.5D);

					FallingBlock fallingblock = b.getWorld().spawnFallingBlock(centerBlockLoc, b.getBlockData());
					fallingblock.setDropItem(false);

					b.setType(Material.AIR);

					b.getWorld().playSound(b.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1.0F, 1.0F);

					if (splitRand.nextInt(100) == 1) {
						ItemUtils.breakItem(e.getPlayer(), item);
					}
				}
			}
		} else if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if (splitRand.nextInt(200) == 1) {
				ItemUtils.breakItem(e.getPlayer(), item);
			}
		}
		
	}

	public void onPlayerDamageEntity(EntityDamageByEntityEvent e, Player player, ItemStack item) {
	}

	public void onItemConsume(PlayerItemConsumeEvent e, ItemStack item) {
	}
}
