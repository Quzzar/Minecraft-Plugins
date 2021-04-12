package quzzar.mod;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Skull;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import quzzar.mod.blocks.ActivatedMechBlock;
import quzzar.mod.blocks.BreakBlock;
import quzzar.mod.blocks.HeadUnitBlock;
import quzzar.mod.blocks.PoweredHUs;
import quzzar.mod.commands.SaveCommand;
import quzzar.mod.documents.ConfigManager;
import quzzar.mod.hu.HURegistrar;
import quzzar.mod.hu.SmallMachineUnit;
import quzzar.mod.inventories.mechInv.MechInvAS;
import quzzar.mod.inventories.mechInv.MechInvManager;
import quzzar.mod.mhu.LargeMachineUnit;
import quzzar.mod.mhu.MHURegistrar;
import quzzar.mod.pipes.HUPipe;

public class InteralClock {
	
	public static void RunTask(){
		
		int clockSpeed = ConfigManager.getInstance().getConfig().getInt("Plugin_ClockSpeed");
		
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			
			public void run() {
				
				SaveCommand.save();
				
				if(ConfigManager.getInstance().getConfig().getBoolean("Display_Autosave_Message")) {
					Utility.tellConsole(ChatColor.GOLD+
							ConfigManager.getInstance().getConfig().getString("Autosave_Message"));
				}
				
			}
		
		}, ConfigManager.getInstance().getConfig().getInt("Plugin_Autosave_Every_X_Ticks"),
				ConfigManager.getInstance().getConfig().getInt("Plugin_Autosave_Every_X_Ticks"));
		
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			
			public void run() {
				
				for(MultiHeadUnit mhu : Main.MHUList){
					if(mhu.getDamage()>=10){
						mhu.setDamage(mhu.getDamage()-10);
					}
				}
				
			}
		
		}, 400, 400);// 400 L == 20 sec, 20 ticks == 1 sec
		
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			
			public void run() {			
				
				
				
				for(MultiHeadUnit mhu : Main.MHUList){
					
					// Machines > Update Upgrades
					if(mhu.getItemHU().getType().isUpgradable()) {
						updateUpgrades(mhu);
					}
					//
					
					// MHU Machines > Call Update Task
					machine:
					for(LargeMachineUnit machine : MHURegistrar.typesList){
						
						if(mhu.getItemHU().getType().equals(machine.getMachineType())){
							
							machine.updateTask(mhu);
							break machine;
							
						}
					}
					//
					
					
				}
				
				
				
				
				for(HeadUnitBlock hub : Main.HUBList){
					
					// HU Machines > Call Update Task
					machine:
					for(SmallMachineUnit machine : HURegistrar.typesList){
						
						if(hub.getHeadUnit().getTexture().equals(machine.getItemTexture())){
							
							machine.updateTask(hub);
							break machine;
							
						}
					}
					//
					
				}
				
				
				
				// Pipes > Transfer Items
				Iterator<HUPipe> iter = Main.PipeList.iterator();
				
				pipeSearch:
				while (iter.hasNext()) {
					try {
						HUPipe pipe = iter.next();
						
						if(pipe.getEndInventory().getMaxStackSize()==1) {
							
							BreakBlock.MHUForceBreak(pipe.getCompletedMHU(), pipe.getCompletedMHU().getCenterLocation(), true);
						}else{
							transfer:
							for(ItemStack i : pipe.getBeginInventory().getStorageContents()){
								if(i!=null) {
									if(!Utility.inventoryFull(pipe.getEndInventory(), i)) {
										pipe.getEndInventory().addItem(i);
										i.setAmount(0);
										break transfer;
									}
								}
							}
						}
					}catch(ConcurrentModificationException e) {
						break pipeSearch;
					}
				}
				//
				
				
				
				
			}
			
		}, 20, 20);// 20 L == 1 sec, 20 ticks == 1 sec
		
		
		
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			
			public void run() {
				
				ArrayList<HeadUnitBlock> removeList = new ArrayList<HeadUnitBlock>();
				for(HeadUnitBlock hu : Main.HUBList){
					try{
						((Skull) hu.getBlock().getState()).update();
					} catch (Exception e){
						removeList.add(hu);
					}
				}
				Main.HUBList.removeAll(removeList);
				
				////
				
				for(ActivatedMechBlock aMB : ActivatedMechBlock.list){
					
					PoweredHUs.saveCurrent(aMB.getHeadUnit(), aMB.getOldCurrent());
					
					if(PoweredHUs.isOn(aMB.getHeadUnit())) {
						
						machine:
						for(SmallMachineUnit machine : HURegistrar.typesList){
							
							if(aMB.getHeadUnit().getTexture().equals(machine.getItemTexture())){
								
								machine.appliedPower(aMB);
								break machine;
								
							}
								
						}
						
					}
					
				}
				
				ActivatedMechBlock.list.clear();
				
				
			}
			
		}, clockSpeed, clockSpeed);// 2 L == 1/10 sec, 20 ticks == 1 sec
		
	}
	
	
	private static void updateUpgrades(MultiHeadUnit mhu) {
		
		if(mhu.getUpgrades()!=null) {
			Inventory upgradesInv = MechInvManager.getMechInventories(mhu.getItemHU()).get(0).getInventory();
			upgradesInv.setMaxStackSize(1);
			
			
			int speed = 0;
			int storage = 0;
			
			for(ItemStack item : upgradesInv.getContents()) {
				if (item!=null) {
					if(item.isSimilar(ItemsList.Speed_Upgrade(1))) {
						speed = speed +
								ConfigManager.getInstance().getConfig().getInt("Machine_Upgrades.Speed.Added_Improvement_Multiplier");
					}else if (item.isSimilar(ItemsList.Storage_Upgrade(1))) {
						storage = storage + 
								ConfigManager.getInstance().getConfig().getInt("Machine_Upgrades.Storage.Added_Storage_Slots*9");
					}
				}
			}
			
			if (mhu.getUpgrades().getStorage() != storage && mhu.getUpgrades().getStorage() != -1) {
				
				for(MechInvAS mechInv : mhu.getInventories()) {
					
					int newSize = mechInv.getOriginalInvSize()+storage*9;
					
					if(newSize>54) {
						newSize = 54;
					}
					
					if(mechInv.getInventory().getType().equals(InventoryType.CHEST)) {
						
						for(ItemStack item : mechInv.getInventory().getContents()) {
							if (item!=null) {
								mechInv.getArmorStand().getWorld().dropItem(mechInv.getArmorStand().getEyeLocation(), item);
								item.setAmount(0);
							}
						}
						
						Inventory newInv = Bukkit.createInventory(mechInv.getInventory().getHolder(), newSize, mechInv.getInventory().getName());
						
						mechInv.resetInventory(newInv);
						
					}
					
				}
				
				
			}
			
			
			
			mhu.getUpgrades().setStorage(storage);
			mhu.getUpgrades().setSpeed(speed);
			
		} else {
			Utility.tellConsole(ChatColor.RED+"Failed to locate upgrades for upgradable machine!");
		}
		
	}
	
	
	
}
