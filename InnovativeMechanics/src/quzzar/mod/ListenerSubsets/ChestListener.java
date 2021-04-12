package quzzar.mod.ListenerSubsets;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.quzzar.im.versioncontrol.VersionControl;
import com.quzzar.im.versioncontrol.global.sound.GSound;

import quzzar.mod.HeadUnit;
import quzzar.mod.Utility;
import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.inventories.mechInv.MechGlobalInv;
import quzzar.mod.inventories.mechInv.MechInvManager;
import quzzar.mod.pipes.PipeDesignerManager;

public class ChestListener {

	
	public static void openChest(HeadUnit hu, Player p, Block b){
		
		
		if(hu.getType().equals(BlockType.STORAGE)){
			
			
			if(p.hasPermission("innomech.machines.place.pipe")){
				ItemStack iMain = p.getInventory().getItemInMainHand();
				if(Utility.checkMechanical(iMain)){
					if(Utility.getTexture(iMain).equals(TextureDatabase.PIPE_item)){
						
						Location bLoc = b.getLocation().clone();
						bLoc.add(0.5, 0.5, 0.5);
							
						PipeDesignerManager.attemptComplete(bLoc, p);
						
						return;
					}
				}
			}
			
			b.getWorld().playSound(b.getLocation(), Sound.BLOCK_CHEST_OPEN, 0.3f, 1);
			p.openInventory(MechInvManager.getMechInventories(hu).get(0).getInventory());
		}else if(hu.getType().equals(BlockType.GLOBALSTORAGE)){
			
			if(!MechGlobalInv.listIDs.contains(p.getUniqueId()+"")){
				MechGlobalInv.createNew("Interdimensional Chest", 99, p.getUniqueId());
			}
			
			b.getWorld().playSound(b.getLocation(), VersionControl.getSound(GSound.BLOCK_ENDER_CHEST_OPEN), 0.5f, 1);
			p.openInventory(MechGlobalInv.getMechGlobalInv(p.getUniqueId()).getInventory());
		}
		
		
	}
	
	
}
