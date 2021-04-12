package quzzar.mod.pipes;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import quzzar.mod.Main;
import quzzar.mod.Utility;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.commands.SaveCommand;
import quzzar.mod.documents.ConfigManager;
import quzzar.mod.mhu.LargeMachineUnit;
import quzzar.mod.mhu.MHURegistrar;
import quzzar.mod.mhu.types.Pipe_Unit;

public class PipeDesignerManager {
	
	private static ArrayList<PipeDesigner> list = new ArrayList<PipeDesigner>();
	
	public static ArrayList<PipeDesigner> getList(){
		return list;
	}
	
	
	private static void addToList(Player p){
		PipeDesigner pipeDes = getPipeDesigner(p);
		
		if(pipeDes==null){
			list.add(new PipeDesigner(p));
		}
	}
	
	public static void removeFromList(PipeDesigner pd){
		list.remove(pd);
	}
	
	public static void attemptStart(Location beginLoc, Player p){
		
		
		addToList(p);
		
		PipeDesigner pipeDes = getPipeDesigner(p);
		
		if(pipeDes!=null){
			
			BlockBreakEvent eventBreak = new BlockBreakEvent(beginLoc.getBlock().getRelative(BlockFace.DOWN), p);
			Bukkit.getServer().getPluginManager().callEvent(eventBreak);
			
			
			if (!eventBreak.isCancelled()){
				
				pipeDes.setBeginLocation(beginLoc);
			}
			
		}
	}
	
	public static void attemptComplete(Location endLoc, Player p){
		
		for(LargeMachineUnit machine : MHURegistrar.typesList){
			
			if (machine.getMachineType().equals(BlockType.MHU_Pipe)){
				
				if(Main.PipeList.size()>=ConfigManager.getInstance().getConfig().getInt("Max_Pipe_Limit")) {
					
					Utility.tellSender(p, "Can't place! The max pipe limit has been reached!");
					
				} else {
					
					((Pipe_Unit) machine).createViaArmorStand(endLoc, p);
					
					SaveCommand.save();
					
				}
				
				return;
				
			}
		}
		
	}
	
	
	public static PipeDesigner getPipeDesigner(Player p){
		for(PipeDesigner pipeDes : list){
			if(pipeDes.getPlayer().getUniqueId().equals(p.getUniqueId())){
				return pipeDes;
			}
		}
		return null;
	}
	
	
}
