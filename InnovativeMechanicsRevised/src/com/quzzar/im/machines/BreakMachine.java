package com.quzzar.im.machines;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.quzzar.im.structures.Structure;

public class BreakMachine {
	
	
	public static void forceBreak(Structure structure, Location loc, boolean drop){
		
		
		ItemStack i = structure.getMachine().getItem();
		
		if(i!=null) {
			
			structure.getMachine().delete(structure);
			if(drop) {
				loc.getWorld().dropItemNaturally(loc, i);
			}
			
		}
		
	}
	
	public static void playerBreak(Structure structure, Location loc, boolean drop, Player p){
		
		BlockBreakEvent eventBreak = new BlockBreakEvent(loc.getBlock(), p);
		Bukkit.getServer().getPluginManager().callEvent(eventBreak);
		
		if (!eventBreak.isCancelled()){
			forceBreak(structure, loc, drop);
		}
		
	}
	
	
	
}
