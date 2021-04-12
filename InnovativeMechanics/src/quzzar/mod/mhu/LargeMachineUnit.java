package quzzar.mod.mhu;

import java.util.ArrayList;

import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import quzzar.mod.MultiHeadUnit;
import quzzar.mod.Textures.TextureCollection;
import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.mhu.ArmorStandGeneration.ASPlacement;

public abstract class LargeMachineUnit{
	
	private BlockType machineType;
	
	private TextureDatabase itemTexture;
	
	private TextureCollection texCollection;
	private TextureCollection poweredTexCollection;
	
	private int speed;
	
	private ArrayList<ASPlacement> placements = new ArrayList<ASPlacement>();
	
	public LargeMachineUnit(BlockType machineType, TextureDatabase itemTexture, TextureCollection texCollection, TextureCollection poweredTexCollection){
		this.machineType = machineType;
		this.itemTexture = itemTexture;
		this.texCollection = texCollection;
		this.poweredTexCollection = poweredTexCollection;
	}
	
	public abstract void create(Block placedBlock, Player p);
	
	public abstract Object interact(MultiHeadUnit mhu, ArmorStand as);
	
	public abstract void updateTask(MultiHeadUnit mhu);
	
	public abstract void delete(MultiHeadUnit mhu);
	
	
	public ArrayList<ASPlacement> getASPlacements() {
		return placements;
	}
	
	public void setASPlacements(ArrayList<ASPlacement> placements) {
		this.placements = placements;
	}
	
	public BlockType getMachineType() {
		return machineType;
	}

	public TextureDatabase getItemTexture() {
		return itemTexture;
	}
	
	public TextureCollection getTexCollection() {
		return texCollection;
	}

	public TextureCollection getPoweredTexCollection() {
		return poweredTexCollection;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	
	
}
