package quzzar.mod.mhu.types;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
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

public class Quarry extends LargeMachineUnit{
	
	
	private static ArrayList<Material> minerBlackListedMaterials = new ArrayList<Material>(Arrays.asList(Material.BEDROCK,Material.BARRIER,
			Material.LAVA,VersionControl.getMaterial(GMaterial.PLAYER_HEAD_BLOCK)));
	
	
	private String name = this.getClass().getSimpleName();
	
	
	public Quarry(BlockType machineType, TextureDatabase itemTexture, TextureCollection texCollection,
			TextureCollection poweredTexCollection) {
		
		super(machineType, itemTexture, texCollection, poweredTexCollection);
		
		CheckConfig.configChecker(MHURegistrar.settingName+"."+name+".Enable_Running_Sound", true);
		
		CheckConfig.configChecker(MHURegistrar.settingName+"."+name+".Enable_Completion_Sound", true);
		
		CheckConfig.configChecker(MHURegistrar.settingName+"."+name+".Keep_Chunks_Loaded", true);
		
		CheckConfig.configChecker(MHURegistrar.settingName+"."+name+".Mining_Radius", 4);
		
		
		ArrayList<ASPlacement> placements = ASPlacement.getMachineBaseList();
		placements.add(new ASPlacement(1, 0, -1.425, ASDirection.LEFT));
		
		setASPlacements(placements);
		
		
		setSpeed(20);
		
	}
	
	@Override
	public void create(Block placedBlock, Player p) {
		
		
		/// Set Variables
		
		 String invName = "[Output] Mined Blocks";
		 int invSize = 36;
		
		///
		
		
		ArrayList<ItemStack> itsCol = this.getTexCollection().getRawItemStackCollection(1);
		
		HeadUnit headunit = new HeadUnit(this.getItemTexture());
		
		
		BlockFace fbf = Utility.getFaceToLeft(Utility.getFront((Skull)placedBlock.getState()));
        
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
			
			if(mhu.getVariable1()>getSpeed()-mhu.getUpgrades().getSpeed()*5){
				
				mhu.setVariable1(0);
				
				Location planeLoc = centerLocation.clone();
				
				int yLevel;
				
				if(mhu.getVariable3() == -1) {
					yLevel = planeLoc.getBlockY();
				} else {
					yLevel = mhu.getVariable3();
				}
				
				
				boolean blockFound = false;
				
				int radius = ConfigManager.getInstance().getConfig().getInt(MHURegistrar.settingName+"."+name+".Mining_Radius");
				
				processing:
				for(int x = planeLoc.getBlockX() - radius; x <= planeLoc.getBlockX() + radius; x++){
					
					for(int z = planeLoc.getBlockZ() - radius; z <= planeLoc.getBlockZ() + radius; z++){
						
						Block b = planeLoc.getWorld().getBlockAt(x, yLevel, z);
						
						if(!b.getType().equals(Material.AIR) && !b.getType().equals(Material.WATER) 
								&& !VersionControl.areSameMaterial(b, GMaterial.WATER) && !b.equals(centerLocation.getBlock())) {
							blockFound = true;
							
							
							if(!minerBlackListedMaterials.contains(b.getType())){
								
								if(mhu.getCreatorPlayer()!=null) {
									
									if(mhu.getCreatorPlayer().isOnline()) {
										
										Player player = Bukkit.getPlayer(mhu.getCreatorPlayer().getUniqueId());
										
										if(player!=null) {
											
											BlockBreakEvent eventBreak = new BlockBreakEvent(b, player);
											Bukkit.getServer().getPluginManager().callEvent(eventBreak);
											
											
											if (!eventBreak.isCancelled()){
												
												////
												
												mineBlock(mhu, b, centerLocation);
												
												break processing;
												
												////
												
											} else {
												
												PoweredMHUs.turnOff(mhu, centerLocation, TextureCollection.QUARRY);
												
											}
											
										}else {
											Utility.tellConsole(ChatColor.RED+"Quarry couldn't find player! Please break and re-place me!"
													+ " [Coords: "+centerLocation.getWorld().getName()+", " 
													+ centerLocation.getBlockX()+", " 
													+ centerLocation.getBlockY()+", "
													+ centerLocation.getBlockZ()+"]");
											PoweredMHUs.turnOff(mhu, centerLocation, TextureCollection.QUARRY);
										}
										
										
									} else {
										
										Player player = null;
										
										for(Player p : Main.loggedOffPlayers) {
											if(p.getUniqueId().equals(mhu.getCreatorPlayer().getUniqueId())) {
												player = p;
											}
										}
										
										if(player!=null) {
											
											BlockBreakEvent eventBreak = new BlockBreakEvent(b, player);
											Bukkit.getServer().getPluginManager().callEvent(eventBreak);
											

											
											if (!eventBreak.isCancelled()){
												
												////
												
												mineBlock(mhu, b, centerLocation);
												
												break processing;
												
												////
												
											} else {
												
												PoweredMHUs.turnOff(mhu, centerLocation, TextureCollection.QUARRY);
												
											}
											
										} else {
											
											PoweredMHUs.turnOff(mhu, centerLocation, TextureCollection.QUARRY);
											
										}
										
									}
									
								} else {
									
									PoweredMHUs.turnOff(mhu, centerLocation, TextureCollection.QUARRY);
									
								}
								
							} else {
								
								PoweredMHUs.turnOff(mhu, centerLocation, TextureCollection.QUARRY);
								
							}
							
							
						}
						
					}
					
				}
				
				if(!blockFound) {
					
					mhu.setVariable3(yLevel-1);
					
				}
				
				
			}
			
			mhu.setVariable1(mhu.getVariable1()+1);
			
		}
		
		
	}
	
	private void mineBlock(MultiHeadUnit mhu, Block b, Location centerLocation) {
		
		MechInvAS outputMechInv = mhu.getInventories().get(0);
		
		boolean mineOre = false;
		
		for(OreList ore : OreList.values()){
			if(ore.getOreToChunkMap().getMaterial().equals(b.getType())){
				mineOre = true;
			}
		}
		
		if(ConfigManager.getInstance().getConfig().getBoolean(MHURegistrar.settingName+"."+name+".Enable_Completion_Sound")){
			SoundManager.playSound(centerLocation, SoundDatabase.MACHINE_SUCCESS);
		}
		
		if(mineOre){
			
			ItemStack i = new ItemStack(b.getType(),1);
			
			Utility.addItemToInventory(outputMechInv.getInventory(), i,
					outputMechInv.getArmorStand().getEyeLocation());
			
		}else{
			
			for(ItemStack i : b.getDrops()){
				Utility.addItemToInventory(outputMechInv.getInventory(), i,
						outputMechInv.getArmorStand().getEyeLocation());
			}
			
		}
		
		
		b.setType(Material.AIR);
		SoundManager.playSound(b.getLocation(), SoundDatabase.BLOCK_BREAK);
		
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
