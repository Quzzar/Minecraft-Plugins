package com.quzzar.server.souls.limbo;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.quzzar.server.souls.Souls;
import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;
import com.quzzar.server.souls.limbo.creation.CreationManager;

public class LimboListener implements Listener {
	
	// Prevent sending commands in limbo
	@EventHandler
	public void onCommandSend(PlayerCommandPreprocessEvent e) {
		if(e.isCancelled()) {
			return;
		}
		
		PlayerCharacter pChar = CharacterManager.getCharacter(e.getPlayer());
		if(pChar.getData().getSoul().inLimbo()) {
			e.setCancelled(true);
		}
		
	}
	
	// Prevent players from taking damage in limbo
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e){
		if(e.isCancelled()) {
			return;
		}
		if(e.getEntity() instanceof Player) {
			PlayerCharacter pChar = CharacterManager.getCharacter((Player) e.getEntity());
			if(pChar.getData().getSoul().inLimbo()) {
				e.setCancelled(true);
			}
		}
	}
	
	// Prevent dropping items in limbo
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e){
		PlayerCharacter pChar = CharacterManager.getCharacter(e.getPlayer());
		if(pChar.getData().getSoul().inLimbo()) {
			e.setCancelled(true);
		}
	}
	
	// Prevent picking up items in limbo
	@EventHandler
	public void onItemPickUp(EntityPickupItemEvent e){
		if(e.getEntity() instanceof Player) {
			PlayerCharacter pChar = CharacterManager.getCharacter((Player) e.getEntity());
			if(pChar.getData().getSoul().inLimbo()) {
				e.setCancelled(true);
			}
		}
	}
	
	// Prevent inventory clicking in limbo
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		
		if(!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		
		PlayerCharacter pChar = CharacterManager.getCharacter((Player) e.getWhoClicked());
		if(!pChar.getData().getSoul().inLimbo()) {
			return;
		}
		
		e.setCancelled(true);
		
		handleItemInteract((Player) e.getWhoClicked(), e.getCurrentItem());
		
	}
	
	// Prevent interaction in limbo
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		PlayerCharacter pChar = CharacterManager.getCharacter(e.getPlayer());
		if(!pChar.getData().getSoul().inLimbo()) {
			return;
		}
		
		if(e.getItem()!=null && e.getItem().getType() == Material.FISHING_ROD) {
			// Don't cancel
		} else {
			e.setCancelled(true);
		}
		
		handleItemInteract(e.getPlayer(), e.getItem());
		
	}
	
	
	private static ArrayList<UUID> creatingConfirm = new ArrayList<UUID>();
	
	public static void runClock() {
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Souls.instance, new Runnable() {
			
			public void run() {
				
				creatingConfirm.clear();
				
			}
		
		}, 200, 200); // Every 10 seconds
		
	}
	
	private void handleItemInteract(Player player, ItemStack item) {
		if(item == null) {return;}
		
		if(item.equals(LimboItemCreator.getCreateCharacter())) {
			// Do character creation
			if(creatingConfirm.contains(player.getUniqueId())) {
				CreationManager.create(player);
			} else {
				player.sendMessage(ChatColor.BLUE+"Are you sure this is what you want to be your character?");
				player.sendMessage(ChatColor.GREEN+"You won't be able to change it again!");
				player.sendMessage(ChatColor.BLUE+""+ChatColor.ITALIC+"(click again to confirm)");
				creatingConfirm.add(player.getUniqueId());
			}
		} else if (item.equals(LimboItemCreator.getSetSkin())) {
			// Do show skin select
			CreationManager.setSkin(player);
		}else if (item.equals(LimboItemCreator.getSetName())) {
			// Do show name set
			CreationManager.setName(player);
		} else if(item.equals(LimboItemCreator.getSouls(player))) {
			// Do purchase souls
			CreationManager.sendPurchaseButton(player);
		} else if(item.equals(LimboItemCreator.getAboutPaper())) {
			// Do display info
			player.sendMessage(ChatColor.GREEN+"You must use a soul to create a new character.");
			player.sendMessage(ChatColor.GREEN+"After dying, your soul will begin to recharge back its essence. It may take a while!");
			player.sendMessage(ChatColor.GREEN+"In the meantime, feel free to explore or work on designing your new character!");
			player.sendMessage(ChatColor.GREEN+"You can pick and choose your next name and skin!");
		}
		
	}
	
	
	
}
