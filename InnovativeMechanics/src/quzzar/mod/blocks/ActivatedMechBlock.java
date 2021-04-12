package quzzar.mod.blocks;

import java.util.ArrayList;

import org.bukkit.block.Block;

import quzzar.mod.HeadUnit;
import quzzar.mod.Utility;

public class ActivatedMechBlock {
	
	private HeadUnitBlock hub;
	
	private int oldCurrent;
	
	public static ArrayList<ActivatedMechBlock> list = new ArrayList<ActivatedMechBlock>();
	
	ActivatedMechBlock(HeadUnitBlock hub, int oldCurrent){
		
		this.hub = hub;
		
		this.oldCurrent = oldCurrent;
		
	}
	
	public Block getBlock(){
		return hub.getBlock();
	}
	
	public HeadUnit getHeadUnit(){
		return hub.getHeadUnit();
	}
	
	public int getOldCurrent(){
		return oldCurrent;
	}
	
	public static void createNew(Block b, int oldCurrent){
		
		HeadUnitBlock hub = Utility.getHeadUnitBlock(b);
		
		if(hub!=null) {
			list.add(new ActivatedMechBlock(hub, oldCurrent));
		}
		
	}
	
}
