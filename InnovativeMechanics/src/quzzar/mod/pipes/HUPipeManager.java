package quzzar.mod.pipes;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import quzzar.mod.Main;
import quzzar.mod.MultiHeadUnit;
import quzzar.mod.Utility;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.Utilities.MHU_Utilities;
import quzzar.mod.blocks.HeadUnitBlock;
import quzzar.mod.inventories.mechInv.MechInvManager;
import quzzar.mod.mhu.LargeMachineUnit;
import quzzar.mod.mhu.MHURegistrar;

public class HUPipeManager {
	
	public static HUPipe getHUPipe(MultiHeadUnit mhu){
		for(HUPipe pipe : Main.PipeList){
			if(pipe.getCompletedMHU()!=null){
				if(pipe.getCompletedMHU().getItemHU().getID().equals(mhu.getItemHU().getID())){
					return pipe;
				}
			}
		}
		return null;
	}
	
	public static HUPipe getHUPipe(Location loc){
		for(HUPipe pipe : Main.PipeList){
			if(pipe.getBeginInventory().equals(getInventoryConnection(loc))) {
				return pipe;
			}else if(pipe.getEndInventory().equals(getInventoryConnection(loc))) {
				return pipe;
			}
		}
		return null;
	}
	
	
	public static Inventory getInventoryConnection(Location loc) {
		Block b = loc.getBlock();
		if(b.getState() instanceof InventoryHolder) {
			InventoryHolder invHolder = (InventoryHolder) b.getState();
			return invHolder.getInventory();
		}
		if(Utility.checkMechanical(b)){
			for(HeadUnitBlock hub : Main.HUBList){
				if(hub.getBlock().equals(b)){
					if(hub.getHeadUnit().getType().equals(BlockType.STORAGE)){
						return MechInvManager.getMechInventories(hub.getHeadUnit()).get(0).getInventory();
					}
				}
			}
		}
		for(Entity entity : loc.getWorld().getNearbyEntities(loc, 0.2, 0.2, 0.2)) {
			if(entity instanceof ArmorStand){
				ArmorStand as = (ArmorStand) entity;
				if(Utility.checkMechanical(as.getHelmet())){
					MultiHeadUnit mhu = MHU_Utilities.getMHU(as);
					if(mhu!=null){
						for(LargeMachineUnit machine : MHURegistrar.typesList){
							
							if(mhu.getItemHU().getType().equals(machine.getMachineType())){
								
								Object returnedObj = machine.interact(mhu, as);
								
								if(returnedObj!=null) {
									if(returnedObj instanceof Inventory) {
										return (Inventory) returnedObj;
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	
	
}
