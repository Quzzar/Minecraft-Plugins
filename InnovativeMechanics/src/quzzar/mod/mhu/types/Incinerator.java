package quzzar.mod.mhu.types;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
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

public class Incinerator extends LargeMachineUnit{

	private String name = this.getClass().getSimpleName();
	
	public Incinerator(BlockType machineType, TextureDatabase itemTexture, TextureCollection texCollection,
			TextureCollection poweredTexCollection) {
		
		super(machineType, itemTexture, texCollection, poweredTexCollection);
		
		CheckConfig.configChecker(MHURegistrar.settingName+"."+name+".Enable_Burning", true);
		
		
		ArrayList<ASPlacement> placements = ASPlacement.getMachineBaseList();
		
		setASPlacements(placements);
		
	}

	@Override
	public void create(Block placedBlock, Player p) {
		
		
		/// Set Variables
		
		 String invName = "Incinerator";
		
		///
		
		
			ArrayList<ItemStack> itsCol = this.getTexCollection().getRawItemStackCollection(1);
			
			HeadUnit headunit = new HeadUnit(this.getItemTexture());
			
			
			BlockFace fbf = Utility.getFront((Skull)placedBlock.getState()).getOppositeFace();
	        
			Location centerLocation = placedBlock.getLocation();
			centerLocation.add(0.5, 0, 0.5);
			
			ArrayList<ArmorStand> asList = ArmorStandDesign.createStructure(centerLocation, this.getASPlacements(), itsCol, fbf);
			
	        
			MechInvASManager.createNew(invName,InventoryType.DROPPER,asList.get(2));
	        
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
			
			return mechinv.getInventory();
			
		}
		return null;
		
		
	}

	@Override
	public void updateTask(MultiHeadUnit mhu) {
		
		MechInvAS mechInv = mhu.getInventories().get(0);
		
		mechInv.getInventory().clear();
		
		if(ConfigManager.getInstance().getConfig().getBoolean(MHURegistrar.settingName+"."+name+".Enable_Burning")){
			SoundManager.playSound(mhu.getCenterLocation(), SoundDatabase.QUIET_BURNING);
			mhu.getCenterLocation().getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, mhu.getCenterLocation(), 1);
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
