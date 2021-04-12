package com.quzzar.server.souls.death;

import java.util.SplittableRandom;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DetermineInvKeep {

	private static SplittableRandom splitRand = new SplittableRandom();
	
	public static void thirdLoss(Player p) {
		
		for(ItemStack i : p.getInventory().getContents()) {
			if(splitRand.nextInt(3)==0 && i != null) {
				p.getWorld().dropItem(p.getLocation(), i);
				i.setAmount(0);
			}
		}
		
	}
	
	public static void totalLoss(Player p) {
		
		for(ItemStack i : p.getInventory().getContents()) {
			if(i != null) {
				p.getWorld().dropItem(p.getLocation(), i);
				i.setAmount(0);
			}
		}
		
	}
	
}
