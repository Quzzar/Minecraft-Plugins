package quzzar.mod.mhu.types;

import java.util.ArrayList;

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
import quzzar.mod.Ores.OreList;
import quzzar.mod.Textures.TextureCollection;
import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.Utilities.MHU_Utilities;
import quzzar.mod.blocks.PoweredMHUs;
import quzzar.mod.documents.CheckConfig;
import quzzar.mod.documents.ConfigManager;
import quzzar.mod.inventories.mechInv.MechInvAS;
import quzzar.mod.inventories.mechInv.MechInvASManager;
import quzzar.mod.maps.TextureItemStackMapManager;
import quzzar.mod.mhu.LargeMachineUnit;
import quzzar.mod.mhu.MHURegistrar;
import quzzar.mod.mhu.ArmorStandGeneration.ASDirection;
import quzzar.mod.mhu.ArmorStandGeneration.ASPlacement;
import quzzar.mod.mhu.ArmorStandGeneration.ArmorStandDesign;
import quzzar.mod.sound.SoundDatabase;
import quzzar.mod.sound.SoundManager;

public class Macerator extends LargeMachineUnit{
	
	private String name = this.getClass().getSimpleName();
	
	public Macerator(BlockType machineType, TextureDatabase itemTexture, TextureCollection texCollection,
			TextureCollection poweredTexCollection) {
		
		super(machineType, itemTexture, texCollection, poweredTexCollection);
		
		CheckConfig.configChecker(MHURegistrar.settingName+"."+name+".Enable_Running_Sound", true);
		
		CheckConfig.configChecker(MHURegistrar.settingName+"."+name+".Enable_Completion_Sound", true);
		
		
		ArrayList<ASPlacement> placements = ASPlacement.getMachineBaseList();
		placements.add(new ASPlacement(-1, 0, -1.425, ASDirection.RIGHT));
		placements.add(new ASPlacement(1, 0, -1.425, ASDirection.LEFT));
		
		setASPlacements(placements);
		
		
		setSpeed(16);
		
	}
	
	@Override
	public void create(Block placedBlock, Player p) {
		
		
		/// Set Variables
		
		 String invName1 = "[Input] Ores to Process";
		 String invName2 = "[Output] Processed Ores";
		 int invSize = 18;
		
		///
		
		 	ArrayList<ItemStack> itsCol = this.getTexCollection().getRawItemStackCollection(1);
			
			HeadUnit headunit = new HeadUnit(this.getItemTexture());
			
			
			BlockFace fbf = Utility.getFront((Skull)placedBlock.getState()).getOppositeFace();
	        
			Location centerLocation = placedBlock.getLocation();
			centerLocation.add(0.5, 0, 0.5);
			
			ArrayList<ArmorStand> asList = ArmorStandDesign.createStructure(centerLocation, this.getASPlacements(), itsCol, fbf);
			
	        
			MechInvASManager.createNew(invName1,invSize,asList.get(8));
		    MechInvASManager.createNew(invName2,invSize,asList.get(9));
	        
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
		
		MechInvAS mechinv = MechInvASManager.getMechInventory(as);
		
		if(mechinv != null){
			
			SoundManager.playSound(as.getEyeLocation(), SoundDatabase.MINICHEST_OPEN);
			return mechinv.getInventory();
			
		} else {
			PoweredMHUs.toggle(mhu, as.getEyeLocation(), this.getPoweredTexCollection(), this.getTexCollection());
			return null;
		}
		
		
	}

	@Override
	public void updateTask(MultiHeadUnit mhu) {
		
		
		if(PoweredMHUs.isOn(mhu)){
			
			Location centerLocation = mhu.getCenterLocation();
			
			if(ConfigManager.getInstance().getConfig().getBoolean(MHURegistrar.settingName+"."+name+".Enable_Running_Sound")){
				SoundManager.playSound(centerLocation, SoundDatabase.MACHINE_RUNNING);
			}
			
			
			if(mhu.getVariable1()>getSpeed()-mhu.getUpgrades().getSpeed()*2){
				
				mhu.setVariable1(0);
				
				
				MechInvAS inputMechInv = mhu.getInventories().get(0);
				MechInvAS outputMechInv = mhu.getInventories().get(1);
				
				
				boolean found = false;
				
				checking:
				for(ItemStack i : inputMechInv.getInventory().getContents()){
					
					if(i!=null){
						
						for(OreList ore : OreList.values()){
							
							if (i.getType().equals(ore.getOreToChunkMap().getMaterial())){
								
								found = true;
								
								if(ConfigManager.getInstance().getConfig().getBoolean(MHURegistrar.settingName+"."+name+".Enable_Completion_Sound")){
									SoundManager.playSound(centerLocation, SoundDatabase.MACHINE_SUCCESS);
								}
								
								ItemStack iNew = TextureItemStackMapManager.getItemStack(ore.getOreToChunkMap().getTexture(), 1);
								
								Utility.addItemToInventory(outputMechInv.getInventory(), iNew, outputMechInv.getArmorStand().getEyeLocation());
								
								i.setAmount(i.getAmount()-1);
								
								break checking;
							}
							
						}
						
					}
					
				}
				
				if(!found){
					PoweredMHUs.turnOff(mhu, centerLocation, TextureCollection.MACERATOR);
				}
				
				
			}
			
			mhu.setVariable1(mhu.getVariable1()+1);
			
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
