package com.quzzar.server.worldutils;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockExpEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemMendEvent;

import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.InvDataTag;
import com.quzzar.server.worldutils.enchantments.enchanting.CustomEnchanting;
import com.quzzar.server.worldutils.enchantments.repairing.CustomRepairing;
import com.quzzar.server.worldutils.enchantments.warding.CustomWarding;

public class NoExpListener implements Listener {
	
	// DISABLES ENCHANTING TABLES AND ANVILS
	@EventHandler
	public void onEnchantItem(EnchantItemEvent e) {
		e.setCancelled(true);
	}
	@EventHandler
	public void onPrepareAnvil(PrepareAnvilEvent e) {
		// Prevent using anvils unless it's for AnvilGUI
		if(!CraftMeta.invHasData(e.getInventory(), InvDataTag.ANVIL_GUI)) {
			e.setResult(null);
		}
	}
	
	// BLOCKS DROP/YIELD NO EXP
	@EventHandler
	public void onBlockExp(BlockExpEvent e) {
		e.setExpToDrop(0);
	}
	
	// EXP BOTTLES SPAWN NO EXP
	@EventHandler
	public void onExpBottleBreak(ExpBottleEvent e) {
		e.setExperience(0);
	}
	
	// For now disable mend enchantment until more research is done
	@EventHandler
	public void onItemMend(PlayerItemMendEvent e) {
		e.setCancelled(true);
	}
	
	// PREVENT OPENING CERTAIN BLOCKS
	@EventHandler
	public void onBlockInteract(PlayerInteractEvent e) {
		
		if(e.getClickedBlock() == null) {
			return;
		}
		
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		
		if(Tag.ANVIL.getValues().contains(e.getClickedBlock().getType())) {
			e.setUseInteractedBlock(Result.DENY);
			
			// CHECK IF CUSTOM ENCHANTING IS HAPPENING
			CustomEnchanting.attemptToEnchant(e.getClickedBlock(), e.getItem(), e.getPlayer());
			
			return;
		}
		
		if(e.getClickedBlock().getType().equals(Material.ENCHANTING_TABLE)) {
			e.setUseInteractedBlock(Result.DENY);
			
			// CHECK IF CUSTOM ENCHANTMENT FORGING IS HAPPENING
			CustomWarding.attemptToWard(e.getClickedBlock(), e.getPlayer());
			
			return;
		}
		
		if(e.getClickedBlock().getType().equals(Material.GRINDSTONE)) {
			e.setUseInteractedBlock(Result.DENY);
			
			// CHECK IF CUSTOM REPAIRING IS HAPPENING
			CustomRepairing.attemptToRepair(e.getClickedBlock(), e.getItem(), e.getPlayer());
			
			return;
		}
		
	}
	
	

}
