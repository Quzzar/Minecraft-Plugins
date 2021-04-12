package com.quzzar.im.machines;

import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.EntityDataTag;
import com.quzzar.craftmeta.tags.InvDataTag;
import com.quzzar.craftmeta.tags.ItemDataTag;
import com.quzzar.im.structures.Structure;
import com.quzzar.im.structures.StructureManager;
import com.quzzar.im.structures.loading.inv.InvTag;
import com.quzzar.im.utils.FaceUtils;

public class MachineListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent e) {
		
		if(e.isCancelled()) {
			return;
		}
		
		if(CraftMeta.itemHasData(e.getItemInHand(), ItemDataTag.MACHINE)) {
			
			e.setCancelled(true);
			
			MachineType type = (MachineType) CraftMeta.itemGet(e.getItemInHand(), ItemDataTag.MACHINE);
			Machine machine = MachineRegistrar.getMachine(type);
			
			if(type == MachineType.PIPE) {return;}
			
			machine.create(e.getBlockPlaced(), FaceUtils.getFacing(e.getPlayer()), e.getPlayer());
			
			e.getItemInHand().subtract();
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onArmorStandInteract(PlayerArmorStandManipulateEvent e){
		if(CraftMeta.entityHasData(e.getRightClicked(), EntityDataTag.MACHINE_STRUCTURE)) {
			e.setCancelled(true);
		}
	}
	
	// Save StructInv on close
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e){
		if(CraftMeta.invHasData(e.getInventory(), InvDataTag.MACHINE_STRUCTURE)) {
			UUID structUUID = (UUID) CraftMeta.invGet(e.getInventory(), InvDataTag.MACHINE_STRUCTURE);
			InvTag invTag = (InvTag) CraftMeta.invGet(e.getInventory(), InvDataTag.MACHINE_INV_TAG);
			Structure structure = StructureManager.getStructure(structUUID);
			structure.getStructInventory(invTag).setInventory(e.getInventory());
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onArmorStandInteract(PlayerInteractAtEntityEvent e){
		
		if(e.isCancelled()) {
			return;
		}
		
		if(CraftMeta.entityHasData(e.getRightClicked(), EntityDataTag.MACHINE_STRUCTURE)) {
			
			e.setCancelled(true);
			
			UUID structUUID = (UUID) CraftMeta.entityGet(e.getRightClicked(), EntityDataTag.MACHINE_STRUCTURE);
			Structure structure = StructureManager.getStructure(structUUID);
			if(structure == null) {return;}
			structure.getMachine().interact(structure, (ArmorStand) e.getRightClicked(), e.getPlayer());
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamage(EntityDamageByEntityEvent e){
		
		if(e.isCancelled()) {
			return;
		}
		
		if(!(e.getDamager() instanceof Player)){
			return;
		}
			
		Player p = (Player) e.getDamager();
		
		if(CraftMeta.entityHasData(e.getEntity(), EntityDataTag.MACHINE_STRUCTURE)) {
			
			e.setCancelled(true);
			
			if(p.getGameMode() == GameMode.CREATIVE) {
				
				UUID structUUID = (UUID) CraftMeta.entityGet(e.getEntity(), EntityDataTag.MACHINE_STRUCTURE);
				Structure structure = StructureManager.getStructure(structUUID);
				if(structure == null) {return;}
				
				BreakMachine.playerBreak(structure, e.getEntity().getLocation().add(0, 1, 0), true, p);
				
				
			}
			
		}
		
	}
	
}
