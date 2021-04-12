package com.quzzar.im.machines.types.pipe;

import java.util.UUID;

import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.EntityDataTag;
import com.quzzar.craftmeta.tags.ItemDataTag;
import com.quzzar.im.machines.MachineRegistrar;
import com.quzzar.im.machines.MachineType;
import com.quzzar.im.machines.types.Pipe;
import com.quzzar.im.structures.Structure;
import com.quzzar.im.structures.StructureManager;
import com.quzzar.im.structures.loading.inv.InvTag;

public class PipeListener implements Listener {

	@EventHandler
	public void onArmorStandInteract(PlayerInteractAtEntityEvent e){
		
		if(e.isCancelled()) {
			return;
		}
		
		ItemStack itemInHand = e.getPlayer().getInventory().getItemInMainHand();
		
		if(e.getRightClicked() == null || itemInHand == null) {
			return;
		}
		
		if(CraftMeta.itemHasData(itemInHand, ItemDataTag.MACHINE)) {
			
			MachineType type = (MachineType) CraftMeta.itemGet(itemInHand, ItemDataTag.MACHINE);
			
			if(type == MachineType.PIPE) {
				
				if(CraftMeta.entityHasData(e.getRightClicked(), EntityDataTag.MACHINE_STRUCTURE)) {
					
					e.setCancelled(true);
					
					UUID structUUID = (UUID) CraftMeta.entityGet(e.getRightClicked(), EntityDataTag.MACHINE_STRUCTURE);
					Structure structure = StructureManager.getStructure(structUUID);
					if(structure == null) {return;}
					
					ArmorStand as = (ArmorStand) e.getRightClicked();
					InvTag tag = structure.getInvTag(as);
					Inventory inv = structure.getInventory(tag);

					if (inv != null) {
						
						InvLocData data = new InvLocData(structure.getID().toString(), tag.name(), as.getEyeLocation());
						InvLocData existingData = PipePlacingManager.addEnd(e.getPlayer(), data);
						
						if(existingData != null) {
							
							Pipe pipe = (Pipe) MachineRegistrar.getMachine(MachineType.PIPE);
							boolean success = pipe.create(existingData, data, e.getPlayer());
							
							if(success) {
								
								PipePlacingManager.remove(e.getPlayer());
								itemInHand.subtract();
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		
		if(e.getClickedBlock() == null || e.getItem() == null) {
			return;
		}
		
		if(CraftMeta.itemHasData(e.getItem(), ItemDataTag.MACHINE)) {
			
			MachineType type = (MachineType) CraftMeta.itemGet(e.getItem(), ItemDataTag.MACHINE);
			
			if(type == MachineType.PIPE) {
				
				if(e.getClickedBlock().getState() instanceof BlockInventoryHolder) {
					
					InvLocData data = new InvLocData(Pipe.BLOCK_INV_ID, Pipe.BLOCK_INV_ID, e.getClickedBlock().getLocation());
					InvLocData existingData = PipePlacingManager.addEnd(e.getPlayer(), data);
					
					e.setCancelled(true);
					
					if(existingData != null) {
						
						Pipe pipe = (Pipe) MachineRegistrar.getMachine(MachineType.PIPE);
						boolean success = pipe.create(existingData, data, e.getPlayer());
						
						if(success) {
							
							PipePlacingManager.remove(e.getPlayer());
							e.getItem().subtract();
							
						}
						
					}
					
				}
				
			} else {
				PipePlacingManager.remove(e.getPlayer());
				return;
			}
			
		}
		
	}
	
	@EventHandler
	public void onLogout(PlayerQuitEvent e){
		
		PipePlacingManager.remove(e.getPlayer());
		
	}
	
}
