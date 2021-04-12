package quzzar.mod.thedark;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import quzzar.mod.Main;

public class DarkExcessLiquidHandler {

	
	public static void updateLiquids(Main main, List<Block> liquidBlocks){
		
		try {
			
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
				  public void run() {
					  
					  for(Block b : liquidBlocks){
							
							for(BlockFace face : BlockFace.values()){
					        	
								b.getRelative(face).setType(Material.STONE);
								b.getRelative(face).setType(Material.AIR);
					        	
					        }
							
					  }
					  
				  }
			}, 1L);
			
		}catch(NullPointerException e){
			
		}
		
	}
	
	
	
}
