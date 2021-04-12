package com.quzzar.server.orecreator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SplittableRandom;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OreListener implements Listener {
	
	private static SplittableRandom splitRand = new SplittableRandom();
	private static List<Material> replaceables = Arrays.asList(
			Material.STONE, Material.GRANITE, Material.DIORITE, Material.ANDESITE);
	
	private static final BlockFace[] BLOCK_SIDES = 
		{BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN};
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent e) {
		if (!e.isCancelled()) {
			
			if(e.getBlock().getType() == Material.STONE) {
				
				if(e.getBlock() == null) {
					return;
				}
				
				BlockFace currentBlockFace = getBlockFace(e.getPlayer());
				
				if(currentBlockFace == null) {
					return;
				}
				
				BlockFace awayFace = currentBlockFace.getOppositeFace();
				Block blockOnOtherSide = e.getBlock().getRelative(awayFace);
				
				if(isSurroundedByReplaceables(blockOnOtherSide)) {
					setVein(blockOnOtherSide);
				}
				
			}
			
		}
	}
	
	private void setVein(Block ogBlock) {
		
		OreLayer oreVein = OreSpawning.getOreVein(ogBlock);
		if(oreVein == null) {return;}
		final int veinSize = splitRand.nextInt(oreVein.getMaxVeinSize())+1;
		
		Set<Block> blocksInVein = new HashSet<Block>();
		blocksInVein.add(ogBlock);
		
		blocksInVein = addBlocksToVein(blocksInVein, veinSize, 0);
		for(Block b : blocksInVein) {
			b.setType(oreVein.getBlockMaterial());
		}
		//Bukkit.broadcastMessage(
				//ChatColor.BLUE+"DEBUG: "+oreVein.getBlockMaterial()+
				//": veinSize-'"+veinSize+"', oresCreated-'"+blocksInVein.size()+"' Biome:"+oreVein.getRestrictedBiome());
		
	}
	
	private Set<Block> addBlocksToVein(Set<Block> blocksInVein, int veinSize, int failedCount){
		Block rBlock = getRandomAdjacentBlock(getRandomVeinBlock(blocksInVein));
		if(blocksInVein.size() >= veinSize) {
			return blocksInVein;
		}
		if(isSurroundedByReplaceables(rBlock)) {
			blocksInVein.add(rBlock);
		}
		failedCount++;
		if(failedCount > veinSize*2) {
			return blocksInVein;
		}
		return addBlocksToVein(blocksInVein, veinSize, failedCount);
	}
	
	private Block getRandomVeinBlock(Set<Block> blocksInVein) {
		int iRand = splitRand.nextInt(blocksInVein.size());
		int i = 0;
		for(Block block : blocksInVein) {
		    if (i == iRand) {
		    	return block;
		    }
		    i++;
		}
		return null;
	}
	
	private Block getRandomAdjacentBlock(Block block) {
		BlockFace nextFace = BLOCK_SIDES[splitRand.nextInt(BLOCK_SIDES.length)];
		return block.getRelative(nextFace);
	}
	
	private boolean isSurroundedByReplaceables(Block block) {
		if(replaceables.contains(block.getType())
				&& replaceables.contains(block.getRelative(BlockFace.NORTH).getType())
				&& replaceables.contains(block.getRelative(BlockFace.SOUTH).getType())
				&& replaceables.contains(block.getRelative(BlockFace.EAST).getType())
				&& replaceables.contains(block.getRelative(BlockFace.WEST).getType())
				&& replaceables.contains(block.getRelative(BlockFace.UP).getType())
				&& replaceables.contains(block.getRelative(BlockFace.DOWN).getType())) {
			return true;
		}
		return false;
	}
	
	private BlockFace getBlockFace(Player player) {
	    List<Block> lastTwoTargetBlocks = player.getLastTwoTargetBlocks(null, 100);
	    if (lastTwoTargetBlocks.size() != 2 || !lastTwoTargetBlocks.get(1).getType().isOccluding()) return null;
	    Block targetBlock = lastTwoTargetBlocks.get(1);
	    Block adjacentBlock = lastTwoTargetBlocks.get(0);
	    return targetBlock.getFace(adjacentBlock);
	}
	
}
