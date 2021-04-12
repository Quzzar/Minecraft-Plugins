package quzzar.mod.hu;

import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.blocks.ActivatedMechBlock;
import quzzar.mod.blocks.HeadUnitBlock;

public abstract class SmallMachineUnit{
	
	private BlockType blockType;
	
	private TextureDatabase itemTexture;
	
	
	public SmallMachineUnit(BlockType blockType, TextureDatabase itemTexture){
		this.blockType = blockType;
		this.itemTexture = itemTexture;
	}
	
	public abstract void appliedPower(ActivatedMechBlock aMB);
	
	public abstract void updateTask(HeadUnitBlock hub);
	
	
	public BlockType getBlockType() {
		return blockType;
	}

	public TextureDatabase getItemTexture() {
		return itemTexture;
	}
	
	
	
	
	
	
}
