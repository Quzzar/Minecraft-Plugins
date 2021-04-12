package quzzar.mod.mhu.types;

import java.util.ArrayList;
import java.util.Arrays;

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
import quzzar.mod.blocks.PoweredMHUs;
import quzzar.mod.documents.CheckConfig;
import quzzar.mod.documents.ConfigManager;
import quzzar.mod.furnace.Burnables;
import quzzar.mod.furnace.Smeltables;
import quzzar.mod.inventories.mechInv.MechInvAS;
import quzzar.mod.inventories.mechInv.MechInvASManager;
import quzzar.mod.mhu.LargeMachineUnit;
import quzzar.mod.mhu.MHURegistrar;
import quzzar.mod.mhu.ArmorStandGeneration.ASDirection;
import quzzar.mod.mhu.ArmorStandGeneration.ASPlacement;
import quzzar.mod.mhu.ArmorStandGeneration.ArmorStandDesign;
import quzzar.mod.sound.SoundDatabase;
import quzzar.mod.sound.SoundManager;

public class Industrial_Furnace extends LargeMachineUnit{
	
	private String name = this.getClass().getSimpleName();
	
	public Industrial_Furnace(BlockType machineType, TextureDatabase itemTexture, TextureCollection texCollection,
			TextureCollection poweredTexCollection) {
		
		super(machineType, itemTexture, texCollection, poweredTexCollection);
		
		CheckConfig.configChecker(MHURegistrar.settingName+"."+name+".Enable_Burning_Sound", true);
		
		
		ArrayList<ASPlacement> placements = ASPlacement.getMachineBaseList();
		placements.add(new ASPlacement(0, 1, -1.425, ASDirection.FORWARD));
		
		setASPlacements(placements);
		
	}

	@Override
	public void create(Block placedBlock, Player p) {
		
		/// Set Variables
		
		 String invName1 = "[Input] Ores and Fuel";
		 String invName2 = "[Output] Refined Materials";
		 int invSize = 18;
		
		///
		
			ArrayList<ItemStack> itsCol = this.getTexCollection().getRawItemStackCollection(1);
			
			HeadUnit headunit = new HeadUnit(this.getItemTexture());
			
			
			BlockFace fbf = Utility.getFront((Skull)placedBlock.getState()).getOppositeFace();
	        
			Location centerLocation = placedBlock.getLocation();
			centerLocation.add(0.5, 0, 0.5);
			
			ArrayList<ArmorStand> asList = ArmorStandDesign.createStructure(centerLocation, this.getASPlacements(), itsCol, fbf);
			
	        
			MechInvASManager.createNew(invName1,invSize,asList.get(0));
		    MechInvASManager.createNew(invName2,invSize,asList.get(8));
	        
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
		
		ArmorStand asNew = Industrial_Furnace.getStorageArmorStand(mhu, as);
		
		MechInvAS mechinv = MechInvASManager.getMechInventory(asNew);
		
		if(mechinv != null){
			
			return mechinv.getInventory();
			
		}
		return null;
		
	}
	
	@Override
	public void updateTask(MultiHeadUnit mhu) {
		
		MechInvAS inputMechInv = mhu.getInventories().get(0);
		MechInvAS outputMechInv = mhu.getInventories().get(1);
		
		
		ArrayList<ItemStack> inputContents = new ArrayList<>(Arrays.asList(inputMechInv.getInventory().getContents()));
		
		if(ConfigManager.getInstance().getConfig().getBoolean(MHURegistrar.settingName+"."+name+".Enable_Burning_Sound")){
			if(PoweredMHUs.isOn(mhu)){
				SoundManager.playSound(mhu.getCenterLocation(), SoundDatabase.FURNACE_BURNING);
			}
		}
		
		if(mhu.getVariable1()<=0){
			if(PoweredMHUs.isOn(mhu)){
				PoweredMHUs.turnOffSoundless(mhu, this.getTexCollection());
			}
		}else{
			mhu.setVariable1(mhu.getVariable1()-1);
		}
		
		
		
		if(mhu.getVariable3()>=12){
			
			mhu.setVariable3(0);
			
			if(PoweredMHUs.isOn(mhu)){
				
				Smeltables smelt = null;
				
				finding:
				for(ItemStack item : inputContents){
					if(item!=null){
						
						if(smelt != null){
							break finding;
						}else{
							
							for(Smeltables smeltP : Smeltables.values()){
								if(smeltP.getInput().isSimilar(item)){
									smelt = smeltP;
									smelt.setIndex(inputContents.indexOf(item));
								}
							}
							
						}
						
					}
				}
				
				if(smelt != null){
					
					inputContents.get(smelt.getIndex()).setAmount(inputContents.get(smelt.getIndex()).getAmount()-1);
					
					ItemStack iNew = smelt.getProduct();
					
					Utility.addItemToInventory(outputMechInv.getInventory(), iNew, mhu.getCenterLocation());
					
					SoundManager.playSound(mhu.getCenterLocation(), SoundDatabase.FURNACE_COMPLETE);
					
				}
				
			}
			
		}else{
			mhu.setVariable3(mhu.getVariable3()+1);
		}
		
		
		
		
		if(!PoweredMHUs.isOn(mhu)){
			
			Burnables burn = null;
			Smeltables smelt = null;
			
			finding:
			for(ItemStack item : inputContents){
				if(item!=null){
					
					if(burn != null && smelt != null){
						break finding;
					}else{
						if(burn == null){
							for(Burnables burnP : Burnables.values()){
								if(burnP.getMaterial().equals(item.getType())){
									burn = burnP;
									burn.setIndex(inputContents.indexOf(item));
								}
							}
						}
						
						if(smelt == null){
							for(Smeltables smeltP : Smeltables.values()){
								if(smeltP.getInput().isSimilar(item)){
									smelt = smeltP;
								}
							}
						}
						
					}
					
				}
			}
			
			if(burn != null && smelt != null){
				
				
				if(inputContents.get(burn.getIndex()).getType().equals(Material.LAVA_BUCKET)){
					inputContents.get(burn.getIndex()).setAmount(inputContents.get(burn.getIndex()).getAmount()-1);
					
					ItemStack bucket = new ItemStack(Material.BUCKET);
					
					Utility.addItemToInventory(inputMechInv.getInventory(), bucket, mhu.getCenterLocation());
					
				}else{
					inputContents.get(burn.getIndex()).setAmount(inputContents.get(burn.getIndex()).getAmount()-1);
				}
				
				mhu.setVariable1(burn.getDuration());
				
				if(!PoweredMHUs.isOn(mhu)){
					PoweredMHUs.turnOnSoundless(mhu, this.getPoweredTexCollection());
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
	
	
	public static ArmorStand getStorageArmorStand(MultiHeadUnit mhu, ArmorStand as){
		
		if(as.getUniqueId().equals(mhu.getArmorStands().get(8).getUniqueId())) {
			return mhu.getArmorStands().get(8);
		}else {
			return mhu.getArmorStands().get(0);
		}
		
		/*
		Location cursorLoc = p.getEyeLocation().toVector().add(p.getLocation().getDirection().multiply(2)).toLocation(p.getWorld(), p.getLocation().getYaw(), p.getLocation().getPitch());
		Location adjustedCenterLoc = mhu.getCenterLocation().clone(); adjustedCenterLoc.setY(adjustedCenterLoc.getY()+0.72);
		
		if(cursorLoc.getY()>=adjustedCenterLoc.getY()){
			return mhu.getArmorStands().get(0);
		}else{
			return mhu.getArmorStands().get(2);
		}
		*/
		
	}
	
	
}
