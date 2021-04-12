package com.quzzar.server.worldutils.enchantments.warding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.destroystokyo.paper.ParticleBuilder;

public class CustomWarding {
	
	private static String howToUseMessage = ChatColor.GREEN
			+"To use, replicate the ritual structure required and right click on the enchantment table.";
	
	public static void attemptToWard(Block clickedBlock, Player player) {
		
		TableStructureState tableState = getStructureState(clickedBlock);
		
		if(tableState.equals(TableStructureState.INCOMPLETE)) {
			player.sendMessage(howToUseMessage);
			return;
		}
		
		
		ArrayList<Item> inputEntities = getStructureInput(clickedBlock);
		
		if(inputEntities.size() <= 1) {
			return; // Just return if there is no real recipe input
		}
		
		ArrayList<ItemStack> inputItems = new ArrayList<ItemStack>();
		
		for(Item itemEntity : inputEntities) {
			inputItems.add(itemEntity.getItemStack());
		}
		
		WardingRecipe enchantRec = WardingRecipeManager.findEnchantmentRecipe(inputItems.toArray(new ItemStack[inputItems.size()]));
		
		if(enchantRec != null) {
			
			if(enchantRec.isAdvanced() && !tableState.equals(TableStructureState.ADVANCED)) {
				// Play different failure sound
				player.playSound(clickedBlock.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 1f, 0.8f);
				return;
			}
			
			boolean success = removeMaterials(clickedBlock, inputEntities);
			
			if(success) {
				
				// Drop result
				clickedBlock.getWorld().dropItem(clickedBlock.getLocation().add(0.5, 1.2, 0.5), enchantRec.getResult());
				
			} else {
				
				// Drop failed result
				clickedBlock.getWorld().dropItem(clickedBlock.getLocation().add(0.5, 1.2, 0.5), new ItemStack(Material.SLIME_BALL));
				
			}
			
			// Play success sound
			clickedBlock.getWorld().playSound(clickedBlock.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 0.8f, 0.8f);
			
			return;
			
		} else {
			// Play failure sound
			player.playSound(clickedBlock.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1f, 0.6f);
			return; // BLOCK_END_PORTAL_FRAME_FILL
		}
		
		
	}
	
	private static boolean removeMaterials(Block clickedBlock, ArrayList<Item> inputEntities) {
		
		boolean success = true;
		
		for(Item itemEntity : inputEntities) {
			
			Location itemLocation = itemEntity.getLocation().clone().add(0, 0.4, 0);
			
			ItemStack item = itemEntity.getItemStack();
			if(itemEntity.isValid()) {
				item.subtract();
				if(item.getAmount() <= 0) {
					itemEntity.remove();
				}
			} else {
				success = false;
			}
			
			ParticleBuilder pBuilder = new ParticleBuilder(Particle.REDSTONE);
        	pBuilder.location(itemLocation).count(10).color(88, 199, 199).receivers(20).spawn();
			
		}
		
		return success;
		
	}
	
	private static ArrayList<Item> getStructureInput(Block tableBlock) {
		
		ArrayList<Item> input = new ArrayList<Item>();
		
		List<Block> slabs = Arrays.asList(
				tableBlock.getRelative(0, 0, 1),
				tableBlock.getRelative(0, 0, -1),
				tableBlock.getRelative(1, 0, 0),
				tableBlock.getRelative(-1, 0, 0));
		
		for(Block slabBlock : slabs) {
			
			Collection<Item> itemEntities = slabBlock.getWorld()
					.getNearbyEntitiesByType(Item.class, slabBlock.getLocation().add(0.5, 0.5, 0.5), 0.5);
			
			if(!itemEntities.isEmpty()) {
				input.add(itemEntities.iterator().next());
			}
			
		}
		
		return input;
		
	}
	
	private static TableStructureState getStructureState(Block tableBlock) {
		
		List<Block> slabs = Arrays.asList(
				tableBlock.getRelative(0, 0, 1),
				tableBlock.getRelative(0, 0, -1),
				tableBlock.getRelative(1, 0, 0),
				tableBlock.getRelative(-1, 0, 0));
		
		List<Block> shelves = Arrays.asList(
				tableBlock.getRelative(2, 0, 2),
				tableBlock.getRelative(-2, 0, 2),
				tableBlock.getRelative(2, 0, -2),
				tableBlock.getRelative(-2, 0, -2),
				tableBlock.getRelative(2, 1, 2),
				tableBlock.getRelative(-2, 1, 2),
				tableBlock.getRelative(2, 1, -2),
				tableBlock.getRelative(-2, 1, -2));
		
		List<Block> dias = Arrays.asList(
				tableBlock.getRelative(1, -1, 1),
				tableBlock.getRelative(-1, -1, 1),
				tableBlock.getRelative(1, -1, -1),
				tableBlock.getRelative(-1, -1, -1));
		
		List<Block> diasAdvanced = Arrays.asList(
				tableBlock.getRelative(0, -1, 2),
				tableBlock.getRelative(0, -1, -2),
				tableBlock.getRelative(2, -1, 0),
				tableBlock.getRelative(-2, -1, 0));
		
		
		boolean slabsPresent = hasBlockTypePresent(slabs, Material.QUARTZ_SLAB);
		boolean shelvesPresent = hasBlockTypePresent(shelves, Material.BOOKSHELF);
		boolean diaPresent = hasBlockTypePresent(dias, Material.DIAMOND_BLOCK);
		boolean diaAdvPresent = hasBlockTypePresent(diasAdvanced, Material.DIAMOND_BLOCK);
		
		if(slabsPresent && shelvesPresent && diaPresent) {
			if(diaAdvPresent) {
				return TableStructureState.ADVANCED;
			} else {
				return TableStructureState.BASIC;
			}
		} else {
			return TableStructureState.INCOMPLETE;
		}
		
	}
	
	private static boolean hasBlockTypePresent(List<Block> blocks, Material type) {
		for(Block block : blocks) {
			if(block == null) {
				return false;
			}
			if(!block.getType().equals(type)) {
				return false;
			}
		}
		return true;
	}
	
}
