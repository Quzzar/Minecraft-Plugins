package quzzar.mod.mhu.types;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import quzzar.mod.HeadUnit;
import quzzar.mod.ItemsList;
import quzzar.mod.Main;
import quzzar.mod.MultiHeadUnit;
import quzzar.mod.Utility;
import quzzar.mod.Textures.TextureCollection;
import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.Utilities.HU_Utilities;
import quzzar.mod.mhu.LargeMachineUnit;
import quzzar.mod.pipes.HUPipe;
import quzzar.mod.pipes.HUPipeManager;
import quzzar.mod.pipes.PipeDesigner;
import quzzar.mod.pipes.PipeDesignerManager;
import quzzar.mod.sound.SoundDatabase;
import quzzar.mod.sound.SoundManager;

public class Pipe_Unit extends LargeMachineUnit{

	public Pipe_Unit(BlockType machineType, TextureDatabase itemTexture, TextureCollection texCollection,
			TextureCollection poweredTexCollection) {
		super(machineType, itemTexture, texCollection, poweredTexCollection);
	}

	@Override
	public void create(Block placedBlock, Player p) {
		
		HU_Utilities.createHB_Blank(placedBlock, Utility.makeHeadUnit(placedBlock));
		
	}

	@Override
	public Object interact(MultiHeadUnit mhu, ArmorStand as) {
		
		// Nothing
		return null;
		
	}

	@Override
	public void updateTask(MultiHeadUnit mhu) {
		
		// Nothing
		
	}
	
	
	public void createViaArmorStand(Location endLoc, Player p){
		
		
		PipeDesigner pipeDes = PipeDesignerManager.getPipeDesigner(p);
		
		if(pipeDes!=null){
			
			BlockBreakEvent eventBreak = new BlockBreakEvent(endLoc.getBlock().getRelative(BlockFace.DOWN), p);
			Bukkit.getServer().getPluginManager().callEvent(eventBreak);
			
			if (!eventBreak.isCancelled()&&p.getInventory().getItemInMainHand().isSimilar(ItemsList.Pipe(1))){
				pipeDes.setEndLocation(endLoc);
				
				ArrayList<ArmorStand> asList = pipeDes.complete();
				
				if(asList!=null&&!asList.isEmpty()&&pipeDes.getCompletedPipe()!=null){ // If everything worked
					
					p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
					
					Main.PipeList.add(pipeDes.getCompletedPipe());
					
					HeadUnit headunit = new HeadUnit(this.getItemTexture());
					
					Location centerLoc = asList.get((int)asList.size()/2).getLocation();
					
					MultiHeadUnit mhu = MultiHeadUnit.createNew(centerLoc, headunit, asList, centerLoc.getBlock(), BlockFace.NORTH);
					
					mhu.setCreatorPlayer(p);
					
					mhu.setVariable1(asList.size());
					
					pipeDes.getCompletedPipe().setCompletedMHU(mhu);
				}
				
				
			}
			
		}
		
		
	}
	

	@Override
	public void delete(MultiHeadUnit mhu) {
		
		SoundManager.playSound(mhu.getCenterLocation(), SoundDatabase.MACHINE_BREAK);
		
		for(ArmorStand as : mhu.getArmorStands()){
			as.remove();
		}
		
		Main.MHUList.remove(mhu);
		
		HUPipe pipe = HUPipeManager.getHUPipe(mhu);
		
		Main.PipeList.remove(pipe);
		
	}

}
