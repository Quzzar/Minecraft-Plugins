package com.quzzar.server.worldutils.enchantments.repairing;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomRepairing {
	
	private static String howToUseMessage = ChatColor.GREEN
			+"To use, right click the grindstone with a damaged item and materials will be consumed for your inventory to repair it.";
	
	private static ArrayList<RepairCost> costs = new ArrayList<RepairCost>();
	
	static {
		
		costs.add(new RepairCost(Material.WOODEN_AXE, Material.OAK_PLANKS, 0.35, null, ""));
		costs.add(new RepairCost(Material.WOODEN_HOE, Material.OAK_PLANKS, 0.5, null, ""));
		costs.add(new RepairCost(Material.WOODEN_PICKAXE, Material.OAK_PLANKS, 0.35, null, ""));
		costs.add(new RepairCost(Material.WOODEN_SHOVEL, Material.OAK_PLANKS, 0.9, null, ""));
		costs.add(new RepairCost(Material.WOODEN_SWORD, Material.OAK_PLANKS, 0.5, null, ""));
		
		costs.add(new RepairCost(Material.STONE_AXE, Material.COBBLESTONE, 0.35, null, ""));
		costs.add(new RepairCost(Material.STONE_HOE, Material.COBBLESTONE, 0.5, null, ""));
		costs.add(new RepairCost(Material.STONE_PICKAXE, Material.COBBLESTONE, 0.35, null, ""));
		costs.add(new RepairCost(Material.STONE_SHOVEL, Material.COBBLESTONE, 0.9, null, ""));
		costs.add(new RepairCost(Material.STONE_SWORD, Material.COBBLESTONE, 0.5, null, ""));
		
		costs.add(new RepairCost(Material.STONE_AXE, Material.GRANITE, 0.35, "Granite", ""));
		costs.add(new RepairCost(Material.STONE_HOE, Material.GRANITE, 0.5, "Granite", ""));
		costs.add(new RepairCost(Material.STONE_PICKAXE, Material.GRANITE, 0.35, "Granite", ""));
		costs.add(new RepairCost(Material.STONE_SHOVEL, Material.GRANITE, 0.9, "Granite", ""));
		costs.add(new RepairCost(Material.STONE_SWORD, Material.GRANITE, 0.5, "Granite", ""));
		
		costs.add(new RepairCost(Material.STONE_AXE, Material.ANDESITE, 0.35, "Andesite", ""));
		costs.add(new RepairCost(Material.STONE_HOE, Material.ANDESITE, 0.5, "Andesite", ""));
		costs.add(new RepairCost(Material.STONE_PICKAXE, Material.ANDESITE, 0.35, "Andesite", ""));
		costs.add(new RepairCost(Material.STONE_SHOVEL, Material.ANDESITE, 0.9, "Andesite", ""));
		costs.add(new RepairCost(Material.STONE_SWORD, Material.ANDESITE, 0.5, "Andesite", ""));
		
		costs.add(new RepairCost(Material.STONE_AXE, Material.DIORITE, 0.35, "Diorite", ""));
		costs.add(new RepairCost(Material.STONE_HOE, Material.DIORITE, 0.5, "Diorite", ""));
		costs.add(new RepairCost(Material.STONE_PICKAXE, Material.DIORITE, 0.35, "Diorite", ""));
		costs.add(new RepairCost(Material.STONE_SHOVEL, Material.DIORITE, 0.9, "Diorite", ""));
		costs.add(new RepairCost(Material.STONE_SWORD, Material.DIORITE, 0.5, "Diorite", ""));
		
		costs.add(new RepairCost(Material.GOLDEN_AXE, Material.GOLD_INGOT, 0.35));
		costs.add(new RepairCost(Material.GOLDEN_HOE, Material.GOLD_INGOT, 0.5));
		costs.add(new RepairCost(Material.GOLDEN_PICKAXE, Material.GOLD_INGOT, 0.35));
		costs.add(new RepairCost(Material.GOLDEN_SHOVEL, Material.GOLD_INGOT, 0.9));
		costs.add(new RepairCost(Material.GOLDEN_SWORD, Material.GOLD_INGOT, 0.5));
		
		costs.add(new RepairCost(Material.IRON_AXE, Material.IRON_INGOT, 0.35));
		costs.add(new RepairCost(Material.IRON_HOE, Material.IRON_INGOT, 0.5));
		costs.add(new RepairCost(Material.IRON_PICKAXE, Material.IRON_INGOT, 0.35));
		costs.add(new RepairCost(Material.IRON_SHOVEL, Material.IRON_INGOT, 0.9));
		costs.add(new RepairCost(Material.IRON_SWORD, Material.IRON_INGOT, 0.5));
		
		costs.add(new RepairCost(Material.DIAMOND_AXE, Material.DIAMOND, 0.35));
		costs.add(new RepairCost(Material.DIAMOND_HOE, Material.DIAMOND, 0.5));
		costs.add(new RepairCost(Material.DIAMOND_PICKAXE, Material.DIAMOND, 0.35));
		costs.add(new RepairCost(Material.DIAMOND_SHOVEL, Material.DIAMOND, 0.9));
		costs.add(new RepairCost(Material.DIAMOND_SWORD, Material.DIAMOND, 0.5));
		
		// 
		
		costs.add(new RepairCost(Material.LEATHER_BOOTS, Material.LEATHER, 0.25, null, ""));
		costs.add(new RepairCost(Material.LEATHER_CHESTPLATE, Material.LEATHER, 0.125, null, ""));
		costs.add(new RepairCost(Material.LEATHER_HELMET, Material.LEATHER, 0.2, null, ""));
		costs.add(new RepairCost(Material.LEATHER_LEGGINGS, Material.LEATHER, 0.143, null, ""));
		
		costs.add(new RepairCost(Material.CHAINMAIL_BOOTS, Material.IRON_INGOT, 0.25 * 1.3));
		costs.add(new RepairCost(Material.CHAINMAIL_CHESTPLATE, Material.IRON_INGOT, 0.125 * 1.3));
		costs.add(new RepairCost(Material.CHAINMAIL_HELMET, Material.IRON_INGOT, 0.2 * 1.3));
		costs.add(new RepairCost(Material.CHAINMAIL_LEGGINGS, Material.IRON_INGOT, 0.143 * 1.3));
		
		costs.add(new RepairCost(Material.IRON_BOOTS, Material.IRON_INGOT, 0.25));
		costs.add(new RepairCost(Material.IRON_CHESTPLATE, Material.IRON_INGOT, 0.125));
		costs.add(new RepairCost(Material.IRON_HELMET, Material.IRON_INGOT, 0.2));
		costs.add(new RepairCost(Material.IRON_LEGGINGS, Material.IRON_INGOT, 0.143));
		
		costs.add(new RepairCost(Material.GOLDEN_BOOTS, Material.GOLD_INGOT, 0.25));
		costs.add(new RepairCost(Material.GOLDEN_CHESTPLATE, Material.GOLD_INGOT, 0.125));
		costs.add(new RepairCost(Material.GOLDEN_HELMET, Material.GOLD_INGOT, 0.2));
		costs.add(new RepairCost(Material.GOLDEN_LEGGINGS, Material.GOLD_INGOT, 0.143));
		
		costs.add(new RepairCost(Material.DIAMOND_BOOTS, Material.DIAMOND, 0.25));
		costs.add(new RepairCost(Material.DIAMOND_CHESTPLATE, Material.DIAMOND, 0.125));
		costs.add(new RepairCost(Material.DIAMOND_HELMET, Material.DIAMOND, 0.2));
		costs.add(new RepairCost(Material.DIAMOND_LEGGINGS, Material.DIAMOND, 0.143));
		
		//
		
		costs.add(new RepairCost(Material.BOW, Material.STRING, 0.2, null, ""));
		costs.add(new RepairCost(Material.CROSSBOW, Material.STRING, 0.15, null, ""));
		
		costs.add(new RepairCost(Material.TRIDENT, Material.DIAMOND, 0.2));
		
		costs.add(new RepairCost(Material.SHIELD, Material.IRON_INGOT, 0.6));
		
		costs.add(new RepairCost(Material.TURTLE_HELMET, Material.SCUTE, 0.22));
		
		costs.add(new RepairCost(Material.ELYTRA, Material.EMERALD, 0.021));
		costs.add(new RepairCost(Material.ELYTRA, Material.EMERALD, 0.010, "Powered Elytra", ""));
		costs.add(new RepairCost(Material.ELYTRA, Material.DIAMOND, 0.08, "Advanced Elytra", ""));
		
		costs.add(new RepairCost(Material.CARROT_ON_A_STICK, Material.CARROT, 0.8));
		
		costs.add(new RepairCost(Material.FISHING_ROD, Material.STRING, 0.3, null, ""));
		
		costs.add(new RepairCost(Material.FISHING_ROD, Material.EMERALD, 0.15, "Grappling Hook", ""));
		
		costs.add(new RepairCost(Material.SHEARS, Material.IRON_INGOT, 0.5));
		
		costs.add(new RepairCost(Material.FLINT_AND_STEEL, Material.FLINT, 0.4, null, ""));
		
	}
	
	public static void attemptToRepair(Block clickedBlock, ItemStack itemInHand, Player player) {
		
		if(itemInHand == null || !itemInHand.hasItemMeta() 
				|| !(itemInHand.getItemMeta() instanceof Damageable) 
				|| !((Damageable) itemInHand.getItemMeta()).hasDamage()) {
			player.sendMessage(howToUseMessage);
			return;
		}
		
		RepairCost cost = getMaterialCost(itemInHand);
		
		if(cost == null) {
			return;
		}
		
		if(payCost(player, cost)) {
			
			int maxDurability = (int) itemInHand.getType().getMaxDurability();
			
			int getRepairAmt = (int) (maxDurability*cost.getPercentPerItem()*adjustedForEnchantments(itemInHand));
			if(getRepairAmt < 1) {getRepairAmt = 1;}
			
			Damageable dMeta = (Damageable) itemInHand.getItemMeta();
			
			if(dMeta.getDamage()-getRepairAmt < 0) {
				dMeta.setDamage(0);
			} else {
				dMeta.setDamage(dMeta.getDamage()-getRepairAmt);
			}
			
			itemInHand.setItemMeta((ItemMeta) dMeta);
			
			// Play success sound!
			clickedBlock.getWorld().playSound(clickedBlock.getLocation(), Sound.BLOCK_GRINDSTONE_USE, 1f, 1f);
			return;
			
		} else {
			
			String itemName = (new ItemStack(cost.getMaterialCost())).getI18NDisplayName();
			player.sendMessage(ChatColor.GREEN+"It looks like you could use some "+itemName+cost.getPluralAdder()+" to repair this.");
			return;
			
		}
		
		
	}
	
	private static boolean payCost(Player player, RepairCost cost) {
		
		for(ItemStack item : player.getInventory().getStorageContents()) {
			if(item != null && !item.hasItemMeta() && item.getType().equals(cost.getMaterialCost())) {
				item.setAmount(item.getAmount()-1);
				return true;
			}
		}
		return false;
	}
	
	private static double adjustedForEnchantments(ItemStack itemInHand) {
		
		int totalEnchants = 0;
		for(Entry<Enchantment, Integer> entry : itemInHand.getItemMeta().getEnchants().entrySet()) {
			totalEnchants += entry.getValue();
		}
		
		return 1.0/(totalEnchants+1.0);
		
	}
	
	private static RepairCost getMaterialCost(ItemStack itemInHand) {
		
		for(RepairCost cost : costs) {
			
			if(cost.hasNameIncludes()) {
				
				if(cost.getItemType().equals(itemInHand.getType()) && itemInHand.getItemMeta().hasDisplayName()) {
					
					String itemName = ChatColor.stripColor(itemInHand.getItemMeta().getDisplayName().toUpperCase());
					if(itemName.contains(cost.getNameIncludes().toUpperCase())) {
						return cost;
					}
					
				}
				
			} else {
				
				if(cost.getItemType().equals(itemInHand.getType()) && !itemInHand.getItemMeta().hasDisplayName()) {
					return cost;
				}
				
			}
			
		}
		return null;
	}

}
