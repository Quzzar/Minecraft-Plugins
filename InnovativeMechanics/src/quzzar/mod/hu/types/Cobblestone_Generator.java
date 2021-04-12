package quzzar.mod.hu.types;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import quzzar.mod.Utility;
import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.blocks.ActivatedMechBlock;
import quzzar.mod.blocks.HeadUnitBlock;
import quzzar.mod.blocks.PoweredHUs;
import quzzar.mod.hu.SmallMachineUnit;

public class Cobblestone_Generator extends SmallMachineUnit{

	public Cobblestone_Generator(BlockType blockType, TextureDatabase itemTexture) {
		super(blockType, itemTexture);
	}

	@Override
	public void appliedPower(ActivatedMechBlock aMB) {
		
	}
	
	
	@Override
	public void updateTask(HeadUnitBlock hub) {
		
		if(PoweredHUs.isOn(hub.getHeadUnit())) {
			
			if(hub.getHeadUnit().getVarTemp()>8){// Configurable?
				
				hub.getHeadUnit().setVarTemp(0);
				
				Block b = hub.getBlock();
				
				ItemStack i = new ItemStack(Material.COBBLESTONE);
				
				Inventory inv = Utility.getCustomChestInv(b);
				ArrayList<Block> blocks = Utility.getAdjacentChests(b);
				
				if(inv!=null&&!Utility.inventoryFull(inv, i)){
					
					inv.addItem(i);
					
				} else if(blocks!=null){
					for(Block cb : blocks){
						Chest chest = (Chest) cb.getState();
						
						chest.getBlockInventory().addItem(i);
						
						break;
					}
				}else{
					b.getWorld().dropItem(b.getLocation(), i);
				}
				
				b.getWorld().playSound(b.getLocation(), Sound.BLOCK_STONE_PLACE, 0.8f, 1.2f);
				
			}
			
			hub.getHeadUnit().setVarTemp(hub.getHeadUnit().getVarTemp()+1);
			
		}
		
	}

}
