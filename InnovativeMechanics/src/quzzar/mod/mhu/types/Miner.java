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

import com.quzzar.im.versioncontrol.VersionControl;
import com.quzzar.im.versioncontrol.global.material.GMaterial;

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
import quzzar.mod.mhu.LargeMachineUnit;
import quzzar.mod.mhu.MHURegistrar;
import quzzar.mod.mhu.ArmorStandGeneration.ASDirection;
import quzzar.mod.mhu.ArmorStandGeneration.ASPlacement;
import quzzar.mod.mhu.ArmorStandGeneration.ArmorStandDesign;
import quzzar.mod.sound.SoundDatabase;
import quzzar.mod.sound.SoundManager;

public class Miner extends LargeMachineUnit{
	
	
	private static ArrayList<Material> minerBlackListedMaterials = new ArrayList<Material>(Arrays.asList(Material.OBSIDIAN,Material.IRON_BLOCK,Material.DIAMOND_BLOCK,
			Material.BEDROCK,Material.BARRIER,Material.LAVA,Material.LAPIS_BLOCK,Material.SLIME_BLOCK,Material.REDSTONE_BLOCK,
			VersionControl.getMaterial(GMaterial.BED_BLOCK),VersionControl.getMaterial(GMaterial.PLAYER_HEAD_BLOCK), Material.WATER));
	
	
	private String name = this.getClass().getSimpleName();
	
	public Miner(BlockType machineType, TextureDatabase itemTexture, TextureCollection texCollection,
			TextureCollection poweredTexCollection) {
		
		super(machineType, itemTexture, texCollection, poweredTexCollection);
		
		CheckConfig.configChecker(MHURegistrar.settingName+"."+name+".Enable_Running_Sound", true);
		
		CheckConfig.configChecker(MHURegistrar.settingName+"."+name+".Enable_Completion_Sound", true);
		
		CheckConfig.configChecker(MHURegistrar.settingName+"."+name+".Keep_Chunks_Loaded", false);
		
		
		ArrayList<ASPlacement> placements = ASPlacement.getMachineBaseList();
		placements.add(new ASPlacement(0, 1, -1.425, ASDirection.FORWARD));
		
		setASPlacements(placements);
		
		
		setSpeed(25);
		
	}

	@Override
	public void create(Block placedBlock, Player p) {
		
		
		/// Set Variables
		
		 String invName = "[Output] Mined Blocks";
		 int invSize = 18;
		
		///
		
		
		 	ArrayList<ItemStack> itsCol = this.getTexCollection().getRawItemStackCollection(1);
			
			HeadUnit headunit = new HeadUnit(this.getItemTexture());
			
			
			BlockFace fbf = Utility.getFront((Skull)placedBlock.getState()).getOppositeFace();
	        
			Location centerLocation = placedBlock.getLocation();
			centerLocation.add(0.5, 0, 0.5);
			
			ArrayList<ArmorStand> asList = ArmorStandDesign.createStructure(centerLocation, this.getASPlacements(), itsCol, fbf);
			
	        
	        MechInvASManager.createNew(invName,invSize,asList.get(8));
	        
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
			
		}else{
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
			
			if(mhu.getVariable1()>getSpeed()-mhu.getUpgrades().getSpeed()*5) {
				
				Block miningBlock = getBlockBelowLoc(centerLocation.clone()).getBlock();
				mhu.setVariable1(0);
				
				if(!minerBlackListedMaterials.contains(miningBlock.getType())){
					
					MechInvAS outputMechInv = mhu.getInventories().get(0);
					
					boolean mineOre = false;
					
					for(OreList ore : OreList.values()){
						if(ore.getOreToChunkMap().getMaterial().equals(miningBlock.getType())){
							mineOre = true;
						}
					}
					
					if(ConfigManager.getInstance().getConfig().getBoolean(MHURegistrar.settingName+"."+name+".Enable_Completion_Sound")){
						SoundManager.playSound(centerLocation, SoundDatabase.MACHINE_SUCCESS);
					}
					
					if(mineOre){
						
						ItemStack i = new ItemStack(miningBlock.getType(),1);
						
						Utility.addItemToInventory(outputMechInv.getInventory(), i, outputMechInv.getArmorStand().getEyeLocation());
						
					}else{
						
						for(ItemStack i : miningBlock.getDrops()){
							Utility.addItemToInventory(outputMechInv.getInventory(), i, outputMechInv.getArmorStand().getEyeLocation());
						}
						
					}
					
					
					miningBlock.setType(Material.AIR);
					SoundManager.playSound(miningBlock.getLocation(), SoundDatabase.BLOCK_BREAK);
					
					
					
				}else{
					PoweredMHUs.turnOff(mhu, centerLocation, TextureCollection.MINER);
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
	
	
	private static Location getBlockBelowLoc(Location loc) {
	    Location locBelow = loc.subtract(0, 1, 0);
	    if(locBelow.getBlock().getType().equals(Material.AIR)||locBelow.getBlock().getType().equals(Material.BARRIER)) {
	        locBelow = getBlockBelowLoc(locBelow);
	    }
	    return locBelow;
	}
	
}
