package quzzar.mod.hu.types;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Skull;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.quzzar.im.versioncontrol.VersionControl;
import com.quzzar.im.versioncontrol.global.material.GMaterial;

import quzzar.mod.Utility;
import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.blocks.ActivatedMechBlock;
import quzzar.mod.blocks.HeadUnitBlock;
import quzzar.mod.hu.SmallMachineUnit;

public class Breaker extends SmallMachineUnit{

	public Breaker(BlockType blockType, TextureDatabase itemTexture) {
		super(blockType, itemTexture);
	}

	@Override
	public void appliedPower(ActivatedMechBlock aMB) {
		
		Block b = aMB.getBlock();
		
		Block bf = b.getRelative(Utility.getFront((Skull)b.getState()));
		if(!bf.getType().equals(Material.BEDROCK)&&!bf.getType().equals(Material.OBSIDIAN)&&!bf.getType().equals(Material.AIR)
				&&!VersionControl.areSameMaterial(bf, GMaterial.PLAYER_HEAD_BLOCK)){
			
			Inventory inv = Utility.getCustomChestInv(b);
			ArrayList<Block> blocks = Utility.getAdjacentChests(b);
			
			for(ItemStack i : bf.getDrops()){
				
				if(inv!=null&&!Utility.inventoryFull(inv, i)){
					
					inv.addItem(i);
					
				} else if(blocks!=null){
					for(Block cb : blocks){
						Chest chest = (Chest) cb.getState();
						
						chest.getBlockInventory().addItem(i);
						
						break;
					}
				}else{
					b.getWorld().dropItem(bf.getLocation(), i);
				}
				
			}
			
			bf.getWorld().playSound(bf.getLocation(), Sound.BLOCK_STONE_BREAK, 2, 2);
			bf.setType(Material.AIR);
		}
		
	}

	@Override
	public void updateTask(HeadUnitBlock hub) {
		
	}

}
