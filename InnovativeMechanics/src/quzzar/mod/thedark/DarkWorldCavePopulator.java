package quzzar.mod.thedark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import quzzar.mod.Main;

public class DarkWorldCavePopulator extends BlockPopulator{
	
    @Override
    public void populate(final World world, final Random random, final Chunk source) {
    	CaveGen caveRandom = new CaveGen(source);
    	
    	List<Block> liquidBlocks = new ArrayList<Block>();
    	
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
            	
                for (int y = DarkManager.caveMax; y >= DarkManager.caveMin; y--) {
                	
                	Block b = source.getBlock(x, y, z);
                	
                	if (caveRandom.isCave(x, y, z)) {
                        
                		if(b.getType().equals(Material.LAVA) || b.getType().equals(Material.WATER)){
                			liquidBlocks.add(b);
                    	}
                		
                        b.setType(Material.AIR);
                        
                        
                    }
                }
            }
        }
        
        
        DarkExcessLiquidHandler.updateLiquids(Main.instance, liquidBlocks);
         
    }
	
    
    
    
    
}