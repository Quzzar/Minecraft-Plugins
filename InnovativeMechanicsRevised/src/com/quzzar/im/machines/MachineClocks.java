package com.quzzar.im.machines;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.quzzar.im.InnovativeMechanics;
import com.quzzar.im.structures.Structure;
import com.quzzar.im.structures.StructureManager;
import com.quzzar.im.utils.ChatUtils;

public class MachineClocks {

	public static void runClocks() {
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(InnovativeMechanics.instance, new Runnable() {
			
			public void run() {
				
				ChatUtils.tellConsole(ChatColor.GOLD+"Auto saving...");
				StructureManager.save();
				
			}
		
		}, 72000, 72000); // 1 hour
		
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(InnovativeMechanics.instance, new Runnable() {
			
			public void run() {
				
				for(Set<Structure> structures : StructureManager.getStructureMap().values()) {
					for(Structure structure : structures) {
						if(structure.isLoaded()) {
							
							structure.setPowerCooldown(false);
							structure.getMachine().updateTask(structure);
							
						}
					}
				}
				
			}
		
		}, 60, 60); // 3 seconds
		
		
	}
	
}
