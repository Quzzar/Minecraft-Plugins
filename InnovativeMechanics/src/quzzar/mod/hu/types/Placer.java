package quzzar.mod.hu.types;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Skull;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import quzzar.mod.Utility;
import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.blocks.ActivatedMechBlock;
import quzzar.mod.blocks.HeadUnitBlock;
import quzzar.mod.hu.SmallMachineUnit;

public class Placer extends SmallMachineUnit{

	public Placer(BlockType blockType, TextureDatabase itemTexture) {
		super(blockType, itemTexture);
	}

	@Override
	public void appliedPower(ActivatedMechBlock aMB) {
		
		Block b = aMB.getBlock();
		
		Block bf = b.getRelative(Utility.getFront((Skull)b.getState()));
		if(bf.getType().equals(Material.AIR)){
			
			Inventory inv = Utility.getCustomChestInv(b);
			ArrayList<Block> blocks = Utility.getAdjacentChests(b);
			
			if(inv!=null){
				
				for(ItemStack i : inv.getContents()){
					if(i!=null){
						if(i.getType().isBlock()){
							
							bf.setType(i.getType());
							
							b.getWorld().playSound(b.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 5, 2);
							
							i.setAmount(i.getAmount()-1);
							
							return;
						}
					}
				}
				
			}
			
			if(blocks!=null){
				for(Block cb : blocks){
					Chest chest = (Chest) cb.getState();
					
					for(ItemStack i : chest.getBlockInventory().getContents()){
						if(i!=null){
							if(i.getType().isBlock()){
								
								bf.setType(i.getType());
								
								b.getWorld().playSound(b.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 5, 2);
								
								i.setAmount(i.getAmount()-1);
								
								return;
							}
						}
					}
					
				}
			}
			
		}
		
	}
	
	@Override
	public void updateTask(HeadUnitBlock hub) {
		
	}

}
