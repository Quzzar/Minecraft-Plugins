package com.quzzar.server.worldutils.enchantments.enchanting;

import java.util.Collection;
import java.util.Map;
import java.util.SplittableRandom;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import com.destroystokyo.paper.ParticleBuilder;
import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.ItemDataTag;
import com.quzzar.server.dominions.Utility;

public class CustomEnchanting {

	private static SplittableRandom splitRand = new SplittableRandom();
	
	private static String howToUseMessage = ChatColor.GREEN
			+"To use, place an enchanted book on top of the anvil and then right click the anvil with the item you wish to enchant.";
	
	public static void attemptToEnchant(Block clickedBlock, ItemStack itemInHand, Player player) {
		
		if(itemInHand == null) {
			player.sendMessage(howToUseMessage);
			return;
		}
		
		Block aboveBlock = clickedBlock.getRelative(BlockFace.UP);
		World world = aboveBlock.getLocation().getWorld();
		
		Collection<Item> itemEntities = world.getNearbyEntitiesByType(Item.class, aboveBlock.getLocation().add(0.5, 0, 0.5), 0.5);
		
		boolean enchantedItem = false;
		
		for(Item itemEntity : itemEntities) {
			
			if (itemEntity.getItemStack().getType().equals(Material.ENCHANTED_BOOK)) {
				
				ItemStack itemOnBlock = itemEntity.getItemStack();
				
				if(itemOnBlock.getItemMeta() instanceof EnchantmentStorageMeta) {
		            EnchantmentStorageMeta enchantMeta = (EnchantmentStorageMeta) itemOnBlock.getItemMeta();
		            
		            if(canBeEnchanted(itemInHand, enchantMeta.getStoredEnchants())){
		            	
		            	enchantedItem = true;
		            	
		            	ItemStack newEnchantedItem = itemInHand.clone();
		            	Location smithingLocation = itemEntity.getLocation().clone().add(0, 0.4, 0);
		            	
		            	itemEntity.remove();
		            	itemInHand.subtract();
		            	
		            	newEnchantedItem.setAmount(1);
		            	newEnchantedItem.addUnsafeEnchantments(enchantMeta.getStoredEnchants());
		            	Utility.addItemToInventory(player.getInventory(), newEnchantedItem, player.getLocation());
		            	
		            	// Do success effect and sound
		            	world.playSound(aboveBlock.getLocation(), Sound.BLOCK_ANVIL_USE, 1f, 1f);
		            	
		            	ParticleBuilder pBuilder = new ParticleBuilder(Particle.REDSTONE);
		            	pBuilder.location(smithingLocation).count(10).color(215, 83, 213).receivers(10).source(player).spawn();
		            	
		            	// Chance to damage anvil after use
		            	chanceToDamage(clickedBlock);
		            	
		            	return;
		            }
		            
		        }
				
			}
			
		}
		
		if(!enchantedItem) {
			// Play Failure sound
        	player.playSound(aboveBlock.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.8f, 1.3f);
		}
		
	}
	
	private static void chanceToDamage(Block anvilBlock) {
		if(splitRand.nextDouble() < 0.1) { // 10% chance to become damaged
			anvilBlock.getWorld().playSound(anvilBlock.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1f, 1f);
			if(anvilBlock.getType().equals(Material.ANVIL)) {
				anvilBlock.setType(Material.CHIPPED_ANVIL);
				return;
			}
			if(anvilBlock.getType().equals(Material.CHIPPED_ANVIL)) {
				anvilBlock.setType(Material.DAMAGED_ANVIL);
				return;
			}
			if(anvilBlock.getType().equals(Material.DAMAGED_ANVIL)) {
				anvilBlock.setType(Material.AIR);
				return;
			}
		}
	}
	
	
	private static boolean canBeEnchanted(ItemStack item, Map<Enchantment, Integer> enchants) {
		if(CraftMeta.itemHasData(item, ItemDataTag.NOT_ENCHANTABLE)) {return false;}
			
		for(Enchantment enchantment : enchants.keySet()) {
			if(!enchantment.canEnchantItem(item) || item.containsEnchantment(enchantment)) {
				return false;
			}
		}
		return true;
	}
	
	
}
