package quzzar.mod.blocks;

import org.bukkit.block.Block;

import quzzar.mod.HeadUnit;

public class HeadUnitBlock {
	
	private HeadUnit headunit;
	private Block block;
	
	public HeadUnitBlock(HeadUnit headunit, Block block){
		this.headunit = headunit;
		this.block = block;
	}
	
	
	public Block getBlock(){
		return block;
	}
	
	public HeadUnit getHeadUnit(){
		return headunit;
	}
	
	
}
