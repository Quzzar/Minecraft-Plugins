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
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import quzzar.mod.HeadUnit;
import quzzar.mod.Main;
import quzzar.mod.MultiHeadUnit;
import quzzar.mod.Utility;
import quzzar.mod.Textures.TextureCollection;
import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.blocks.PoweredMHUs;
import quzzar.mod.documents.CheckConfig;
import quzzar.mod.documents.ConfigManager;
import quzzar.mod.mhu.LargeMachineUnit;
import quzzar.mod.mhu.MHURegistrar;
import quzzar.mod.mhu.ArmorStandGeneration.ASPlacement;
import quzzar.mod.mhu.ArmorStandGeneration.ArmorStandDesign;
import quzzar.mod.sound.SoundDatabase;
import quzzar.mod.sound.SoundManager;

public class Cremator extends LargeMachineUnit {
	
	
	private String name = this.getClass().getSimpleName();
	
	public Cremator(BlockType machineType, TextureDatabase itemTexture, TextureCollection texCollection,
			TextureCollection poweredTexCollection) {
		super(machineType, itemTexture, texCollection, poweredTexCollection);
		
		CheckConfig.configChecker(MHURegistrar.settingName+"."+name+".Effect_Distance", 2);
		CheckConfig.configChecker(MHURegistrar.settingName+"."+name+".Effect_Intensity", 25);
		
		
		ArrayList<ASPlacement> placements = ASPlacement.getMachineBaseList();
		
		setASPlacements(placements);
		
	}

	@Override
	public void create(Block placedBlock, Player p) {
		
		
		ArrayList<ItemStack> itsCol = this.getTexCollection().getRawItemStackCollection(1);
		
		HeadUnit headunit = new HeadUnit(this.getItemTexture());
		
		
		BlockFace fbf = Utility.getFront((Skull)placedBlock.getState()).getOppositeFace();
        
		Location centerLocation = placedBlock.getLocation();
		centerLocation.add(0.5, 0, 0.5);
		
		ArrayList<ArmorStand> asList = ArmorStandDesign.createStructure(centerLocation, this.getASPlacements(), itsCol, fbf);
        
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
		
		
		PoweredMHUs.toggle(mhu, as.getEyeLocation(), this.getPoweredTexCollection(), this.getTexCollection());
		return null;
		
		
	}

	@Override
	public void updateTask(MultiHeadUnit mhu) {
		
		Location centerLoc = mhu.getCenterLocation();
		
		if(PoweredMHUs.isOn(mhu)) {
			
			int effect_distance = ConfigManager.getInstance().getConfig().getInt(MHURegistrar.settingName+"."+name+".Effect_Distance");
			int effect_intensity = ConfigManager.getInstance().getConfig().getInt(MHURegistrar.settingName+"."+name+".Effect_Intensity");
			
			
			for (Entity entity : centerLoc.getWorld().getNearbyEntities(centerLoc, effect_distance, effect_distance, effect_distance)){
				if(entity instanceof LivingEntity){
					entity.setFireTicks(200);
				}
			}
			
			
			Random rand = new Random();
			
			for(Block b : Utility.getNearbyBlocks(centerLoc, effect_distance)) {
				
				int r = rand.nextInt(effect_intensity);
				
				if(b.getType().equals(Material.AIR) && b.getRelative(BlockFace.DOWN).getType().isSolid() && r == 0) {
					
					b.setType(Material.FIRE);
					
				}
			}
		} else {
			
			if (centerLoc.getBlock().getRelative(BlockFace.UP).getType().equals(Material.FIRE)) {
				centerLoc.getBlock().getRelative(BlockFace.UP).setType(Material.AIR);
			}
			
		}
		
	}

	@Override
	public void delete(MultiHeadUnit mhu) {
		
		
		SoundManager.playSound(mhu.getCenterLocation(), SoundDatabase.MACHINE_BREAK);
		
		for(ArmorStand as : mhu.getArmorStands()){
			as.remove();
		}
		
		mhu.getBlock().setType(Material.AIR);
		
		Main.MHUList.remove(mhu);
		
		
	}

}
