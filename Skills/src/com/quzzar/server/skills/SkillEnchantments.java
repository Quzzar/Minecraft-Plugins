package com.quzzar.server.skills;

import java.util.List;
import java.util.SplittableRandom;
import java.util.UUID;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.server.skills.arrays.EnchantmentArrays;
import com.quzzar.server.skills.arrays.MaterialArrays;

public class SkillEnchantments {
	
	private static SplittableRandom splitRand = new SplittableRandom();
	
	
	public static ItemStack determineEnchantments(UUID uuid, ItemStack item) {
		
		if(MaterialArrays.axes.contains(item.getType())) {
			
			int level = Skills.getSkillManager().getSkillSet(uuid).get(0);
			
			chooseRandom(EnchantmentArrays.axeAll, level, item);
			
		} else if(MaterialArrays.hoes.contains(item.getType())) {
			
			int level = Skills.getSkillManager().getSkillSet(uuid).get(1);
			
			chooseRandom(EnchantmentArrays.hoeAll, level, item);
			
		} else if(MaterialArrays.pickaxes.contains(item.getType())) {
			
			int level = Skills.getSkillManager().getSkillSet(uuid).get(2);
			
			chooseRandom(EnchantmentArrays.pickaxeAll, level, item);
			
		} else if(MaterialArrays.swords.contains(item.getType())) {
			
			int level = Skills.getSkillManager().getSkillSet(uuid).get(3);
			
			chooseRandom(EnchantmentArrays.swordAll, level, item);
			
		} else if(MaterialArrays.shovels.contains(item.getType())) {
			
			int level = Skills.getSkillManager().getSkillSet(uuid).get(4);
			
			chooseRandom(EnchantmentArrays.shovelAll, level, item);
			
		}
		
		return item;
		
	}
	
	
	
	private static ItemStack chooseRandom(List<SkillEnchantmentElement> enchantList,
			int level, ItemStack item) {
		
		// Pick enchantment at random from list
		SkillEnchantmentElement enchant = enchantList.get(
				splitRand.nextInt(enchantList.size()));
		
		double odds = level/(SkillSet.defaultAmt*enchant.getOdds());
		
		//Bukkit.broadcastMessage("DEBUG: "+odds+" E:"+enchant.getOdds());
		
		if(odds>1.0) {
			if(splitRand.nextDouble()<(odds-1.0)) {
				
				ItemMeta im = item.getItemMeta();
			    
			    im.addEnchant(enchant.getEnchantment(), enchant.getTier(), true);
			    
			    item.setItemMeta(im);
				
			}
		}
		
		return item;
		
	}
	

}
