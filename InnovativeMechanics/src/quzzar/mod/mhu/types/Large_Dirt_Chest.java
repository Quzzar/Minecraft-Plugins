package quzzar.mod.mhu.types;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import quzzar.mod.HeadUnit;
import quzzar.mod.Main;
import quzzar.mod.MultiHeadUnit;
import quzzar.mod.Utility;
import quzzar.mod.Textures.TextureCollection;
import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.Utilities.MHU_Utilities;
import quzzar.mod.documents.CheckConfig;
import quzzar.mod.documents.ConfigManager;
import quzzar.mod.inventories.mechInv.MechInvAS;
import quzzar.mod.inventories.mechInv.MechInvASManager;
import quzzar.mod.mhu.LargeMachineUnit;
import quzzar.mod.mhu.MHURegistrar;
import quzzar.mod.mhu.ArmorStandGeneration.ASPlacement;
import quzzar.mod.mhu.ArmorStandGeneration.ArmorStandDesign;
import quzzar.mod.sound.SoundDatabase;
import quzzar.mod.sound.SoundManager;

public class Large_Dirt_Chest extends LargeMachineUnit{
	
	
	private String name = this.getClass().getSimpleName();
	
	public Large_Dirt_Chest(BlockType machineType, TextureDatabase itemTexture, TextureCollection texCollection,
			TextureCollection poweredTexCollection) {
		
		super(machineType, itemTexture, texCollection, poweredTexCollection);
		
		CheckConfig.configChecker(MHURegistrar.settingName+"."+name+".Generate_Dirt", true);
		
		
		ArrayList<ASPlacement> placements = ASPlacement.getMachineBaseList();
		
		setASPlacements(placements);
		
	}

	@Override
	public void create(Block placedBlock, Player p) {
		
		
		/// Set Variables
		
		 String invName = "Dirt Chest (Large)";
		 int invSize = 36;
		
		///
		
		
		 	ArrayList<ItemStack> itsCol = this.getTexCollection().getRawItemStackCollection(1);
			
			HeadUnit headunit = new HeadUnit(this.getItemTexture());
			
			
			BlockFace fbf = Utility.getFront((Skull)placedBlock.getState()).getOppositeFace();
	        
			Location centerLocation = placedBlock.getLocation();
			centerLocation.add(0.5, 0, 0.5);
			
			ArrayList<ArmorStand> asList = ArmorStandDesign.createStructure(centerLocation, this.getASPlacements(), itsCol, fbf);
			
	        
	        MechInvASManager.createNew(invName,invSize,asList.get(2));
	        
	        MultiHeadUnit mhu = MultiHeadUnit.createNew(centerLocation, headunit, asList, placedBlock, fbf);
	        
	        mhu.setCreatorPlayer(p);
	        
	        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
				  public void run() {
					  
					  placedBlock.setType(Main.fillerType);
					  
				  }
			}, 5L);
		
		
	}

	@Override
	public Object interact(MultiHeadUnit mhu, ArmorStand as) {
		
		MechInvAS mechinv = MechInvASManager.getMechInventory(mhu.getArmorStands().get(2));
		
		if(mechinv != null){
			
			SoundManager.playSound(as.getEyeLocation(), SoundDatabase.MINICHEST_OPEN);
			return mechinv.getInventory();
			
		}
		return null;
		
	}
	
	@Override
	public void updateTask(MultiHeadUnit mhu) {
		
		if(ConfigManager.getInstance().getConfig().getBoolean(MHURegistrar.settingName+"."+name+".Generate_Dirt")){
			
			Random rand = new Random();
			
			if(rand.nextInt(80)==1) {
				
				MechInvAS mechinv = MechInvASManager.getMechInventory(mhu.getArmorStands().get(2));
				
				if(mechinv!=null) {
					Utility.addItemToInventory(mechinv.getInventory(), new ItemStack(Material.DIRT), mhu.getCenterLocation());
				}
				
			}
			
		}
		
	}

	@Override
	public void delete(MultiHeadUnit mhu) {
		
		MHU_Utilities.deleteInventories(mhu);
		
		SoundManager.playSound(mhu.getCenterLocation(), SoundDatabase.MACHINE_BREAK);
		
		for(ArmorStand as : mhu.getArmorStands()){
			as.remove();
		}
		
		mhu.getBlock().setType(Material.AIR);
		
		Main.MHUList.remove(mhu);
		
		
	}
	
}
