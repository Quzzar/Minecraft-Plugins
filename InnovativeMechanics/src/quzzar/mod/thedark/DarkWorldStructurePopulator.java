package quzzar.mod.thedark;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import quzzar.mod.thedark.structures.ChestStructure;

public class DarkWorldStructurePopulator extends BlockPopulator{
	
	
	int chestStructure = 10000;
	
	
    @Override
    public void populate(final World world, final Random random, final Chunk source) {
    	CaveGen caveRandom = new CaveGen(source);
    	
    	ChestStructure cs = new ChestStructure();
    	
    	Random rand = new Random();
    	
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
            	
                for (int y = DarkManager.caveMax; y >= DarkManager.caveMin; y--) {
                	
                	Block b = source.getBlock(x, y, z);
                	
                	if (caveRandom.isCave(x, y, z)&&!caveRandom.isCave(x, y-1, z)) {
                        
                		int r = rand.nextInt(chestStructure) + 1;
                		if(r==1){
                			cs.createStructure(b.getLocation());
                		}
                        
                    }
                }
            }
        }
         
    }
	
    
    
    
    
}