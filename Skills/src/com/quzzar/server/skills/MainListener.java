package com.quzzar.server.skills;

import java.util.SplittableRandom;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class MainListener implements Listener {
	
	public static void runClock() {
		
		SplittableRandom splitRand = new SplittableRandom();
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Skills.instance, new Runnable() {
			
			public void run() {
				
				for(UUID uuid : Skills.getSkillManager().getSkillTables().keySet()) {
					
					int randIndex = splitRand.nextInt(5);
					
					Skills.getSkillManager().adjustSkills(uuid, randIndex, 14);
					
				}
				
			}
		
		}, 36000, 36000); // Every 30 minutes
		
	}
	
	
	@EventHandler
    public void onPlayerCraft(CraftItemEvent e) {
		
		if(e.getRecipe()!=null && !e.getViewers().isEmpty() && e.getInventory().getResult() != null){
			
			ItemStack result = SkillEnchantments.determineEnchantments(
					e.getViewers().get(0).getUniqueId(), e.getInventory().getResult());
			e.getInventory().setResult(result);
			
		}
		
	}
	
	
	 @EventHandler
	 public void onEntityDeath(EntityDeathEvent e) {
		 
		 if(e.getEntity().getKiller() != null) {
			 
			 if(e.getEntity() instanceof Player) {
				 Skills.getSkillManager().adjustSkills(
						 e.getEntity().getKiller().getUniqueId(), 3, 6);
			 } else if (e.getEntity() instanceof Monster) {
				 Skills.getSkillManager().adjustSkills(
						 e.getEntity().getKiller().getUniqueId(), 3, 3);
			 } else if (e.getEntity() instanceof Animals) {
				 Skills.getSkillManager().adjustSkills(
						 e.getEntity().getKiller().getUniqueId(), 3, 1);
			 }
			 
		 }
		 
	 }
	 
	 
	 @SuppressWarnings("deprecation")
	@EventHandler
	 public void onBlockBreak(BlockBreakEvent e) {
		 
		 Material bMaterial = e.getBlock().getType();
		 BlockData bData = e.getBlock().getBlockData();
		 
		// Checking if the broken block is a dirt-type block
		 if(e.getBlock().getType().equals(Material.DIRT)) {
			 Skills.getSkillManager().adjustSkills(
					 e.getPlayer().getUniqueId(), 4, 1);
			 return;
		 } else if(e.getBlock().getType().equals(Material.GRASS)) {
			 Skills.getSkillManager().adjustSkills(
					 e.getPlayer().getUniqueId(), 4, 1);
			 return;
		 } else if(e.getBlock().getType().equals(Material.SAND)) {
			 Skills.getSkillManager().adjustSkills(
					 e.getPlayer().getUniqueId(), 4, 1);
			 return;
		 } else if(e.getBlock().getType().equals(Material.GRAVEL)) {
			 Skills.getSkillManager().adjustSkills(
					 e.getPlayer().getUniqueId(), 4, 1);
			 return;
		 } else if(e.getBlock().getType().equals(Material.CLAY)) {
			 Skills.getSkillManager().adjustSkills(
					 e.getPlayer().getUniqueId(), 4, 2);
			 return;
		 }
		 
		 // Checking if the broken block is a log
		 if(Tag.LOGS.isTagged(e.getBlock().getType())) {
			 Skills.getSkillManager().adjustSkills(
					 e.getPlayer().getUniqueId(), 0, 2);
			 return;
		 }
		 
		 // Checking if the broken block is an ore
		 if(e.getBlock().getType().equals(Material.COAL_ORE)) {
			 Skills.getSkillManager().adjustSkills(
					 e.getPlayer().getUniqueId(), 2, 1);
			 return;
		 } else if(e.getBlock().getType().equals(Material.IRON_ORE)) {
			 Skills.getSkillManager().adjustSkills(
					 e.getPlayer().getUniqueId(), 2, 2);
			 return;
		 } else if(e.getBlock().getType().equals(Material.GOLD_ORE)) {
			 Skills.getSkillManager().adjustSkills(
					 e.getPlayer().getUniqueId(), 2, 3);
			 return;
		 } else if(e.getBlock().getType().equals(Material.LAPIS_ORE)) {
			 Skills.getSkillManager().adjustSkills(
					 e.getPlayer().getUniqueId(), 2, 4);
			 return;
		 } else if(e.getBlock().getType().equals(Material.REDSTONE_ORE)) {
			 Skills.getSkillManager().adjustSkills(
					 e.getPlayer().getUniqueId(), 2, 3);
			 return;
		 } else if(e.getBlock().getType().equals(Material.DIAMOND_ORE)) {
			 Skills.getSkillManager().adjustSkills(
					 e.getPlayer().getUniqueId(), 2, 5);
			 return;
		 } else if(e.getBlock().getType().equals(Material.EMERALD_ORE)) {
			 Skills.getSkillManager().adjustSkills(
					 e.getPlayer().getUniqueId(), 2, 7);
			 return;
		 } else if(e.getBlock().getType().equals(Material.LEGACY_QUARTZ_ORE)) {
			 Skills.getSkillManager().adjustSkills(
					 e.getPlayer().getUniqueId(), 2, 2);
			 return;
		 }
		 
	     if(bData instanceof Ageable){
	     Ageable age = (Ageable) bData;
	     
	     	// Make sure crop is fairly mature
		     if(age.getAge() > age.getMaximumAge()*0.75){
		    	 
		    	 if(bMaterial.equals(Material.LEGACY_CROPS)) { // 207 3
					 Skills.getSkillManager().adjustSkills(
							 e.getPlayer().getUniqueId(), 1, 6);
					 return;
				 } 
		    	 
		    	 // Include support for melons, beets, nether warts, carrots, potatoes, etc
		    	 /*else if(bMaterial.equals(Material.LEGACY_MELON_BLOCK)) { // 142 7
					 Skills.getSkillManager().adjustSkills(
							 e.getPlayer().getUniqueId(), 1, 4);
					 return;
				 } else if(bMaterial.equals(Material.LEGACY_NETHER_WARTS)) { // 142 7
					 Skills.getSkillManager().adjustSkills(
							 e.getPlayer().getUniqueId(), 1, 4);
					 return;
				 } else if(bMaterial.equals(Material.LEGACY_CROPS)) { // 103 0
					 Skills.getSkillManager().adjustSkills(
							 e.getPlayer().getUniqueId(), 1, 3);
					 return;
				 } else if(bMaterial.equals(Material.LEGACY_CROPS)) { // 59 7
					 Skills.getSkillManager().adjustSkills(
							 e.getPlayer().getUniqueId(), 1, 5);
					 return;
				 }*/
		    	 
	         }
	     }
		 
	 }
	 
}
