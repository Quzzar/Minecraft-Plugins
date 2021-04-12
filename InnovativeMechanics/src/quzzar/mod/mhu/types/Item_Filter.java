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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import quzzar.mod.ArmorStandData;
import quzzar.mod.HeadUnit;
import quzzar.mod.Main;
import quzzar.mod.MultiHeadUnit;
import quzzar.mod.Utility;
import quzzar.mod.Textures.TextureCollection;
import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.Utilities.MHU_Utilities;
import quzzar.mod.inventories.mechInv.MechInvAS;
import quzzar.mod.inventories.mechInv.MechInvASManager;
import quzzar.mod.mhu.LargeMachineUnit;
import quzzar.mod.mhu.ArmorStandGeneration.ASDirection;
import quzzar.mod.mhu.ArmorStandGeneration.ASPlacement;
import quzzar.mod.mhu.ArmorStandGeneration.ArmorStandDesign;
import quzzar.mod.sound.SoundDatabase;
import quzzar.mod.sound.SoundManager;

public class Item_Filter extends LargeMachineUnit{

	public Item_Filter(BlockType machineType, TextureDatabase itemTexture, TextureCollection texCollection,
			TextureCollection poweredTexCollection) {
		super(machineType, itemTexture, texCollection, poweredTexCollection);
		
		
		ArrayList<ASPlacement> placements = ASPlacement.getSmallMachineBaseList();
		placements.add(new ASPlacement(0, 0, -0.4, ASDirection.FORWARD));
		placements.add(new ASPlacement(0, 0.6, -1.1, ASDirection.FORWARD));
		placements.add(new ASPlacement(0.6, 0, -1.1, ASDirection.FORWARD));
		placements.add(new ASPlacement(0, -0.6, -1.1, ASDirection.FORWARD));
		placements.add(new ASPlacement(-0.6, 0, -1.1, ASDirection.FORWARD));
		
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
		
        
		MechInvASManager.createNew(ChatColor.BOLD+"[Input]"+ChatColor.RESET+" Items to be Filtered", 54, asList.get(8));
		
	    MechInvASManager.createNew(ChatColor.DARK_BLUE+"[Output #1]",18,asList.get(9));
	    MechInvASManager.createNew(ChatColor.DARK_RED+"[Output #2]",18,asList.get(10));
	    MechInvASManager.createNew(ChatColor.DARK_GREEN+"[Output #3]",18,asList.get(11));
	    MechInvASManager.createNew(ChatColor.DARK_PURPLE+"[Output #4]",18,asList.get(12));
	    
	    MechInvASManager.createNew("" + ChatColor.DARK_BLUE+ChatColor.BOLD+"[Filter]"+ChatColor.RESET+ChatColor.DARK_BLUE+" Output #1", 9, asList.get(4));
	    MechInvASManager.createNew("" + ChatColor.DARK_RED+ChatColor.BOLD+"[Filter]"+ChatColor.RESET+ChatColor.DARK_RED+" Output #2", 9, asList.get(5));
	    MechInvASManager.createNew("" + ChatColor.DARK_GREEN+ChatColor.BOLD+"[Filter]"+ChatColor.RESET+ChatColor.DARK_GREEN+" Output #3", 9, asList.get(6));
	    MechInvASManager.createNew("" + ChatColor.DARK_PURPLE+ChatColor.BOLD+"[Filter]"+ChatColor.RESET+ChatColor.DARK_PURPLE+" Output #4", 9, asList.get(7));
	    
        
        MultiHeadUnit mhu = MultiHeadUnit.createNew(centerLocation, headunit, asList, placedBlock, fbf);
        
	    ArmorStandData.setDisabled(asList.get(4));
	    ArmorStandData.setDisabled(asList.get(5));
	    ArmorStandData.setDisabled(asList.get(6));
	    ArmorStandData.setDisabled(asList.get(7));
	    
	    // Maybe make filter inventories maxStackSize 1
        
        mhu.setCreatorPlayer(p);
		
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			  public void run() {
				  
				  placedBlock.setType(Material.AIR);
				  
			  }
		}, 5L);
        
	}

	@Override
	public Object interact(MultiHeadUnit mhu, ArmorStand as) {
		
		MechInvAS mechinv = MechInvASManager.getMechInventory(as);
		
		if(mechinv != null && as.equals(mhu.getArmorStands().get(8))){
			
			SoundManager.playSound(as.getEyeLocation(), SoundDatabase.MINICHEST_OPEN);
			return mechinv.getInventory();
			
		} else if(mechinv != null){
			
			return mechinv.getInventory();
			
		}
		return null;
		
	}
	
	public void interactShift(MultiHeadUnit mhu, ArmorStand as, Player p) {
		
		Inventory inv = getFilterInventory(mhu, as);
		
		if(inv!=null) {
			p.openInventory(inv);
		}
		
	}

	@Override
	public void updateTask(MultiHeadUnit mhu) {
		
		// temp code to fix bad filters
		if(mhu.getInventories().size()==0) {
			SoundManager.playSound(mhu.getCenterLocation(), SoundDatabase.MACHINE_BREAK);
			
			for(ArmorStand as : mhu.getArmorStands()){
				as.remove();
			}
			
			return;
		}
		//
		
		MechInvAS inputMechInv = mhu.getInventories().get(4);
		
		ArrayList<ArmorStand> outputAS = new ArrayList<ArmorStand>(Arrays.asList(mhu.getArmorStands().get(9),
				mhu.getArmorStands().get(10), mhu.getArmorStands().get(11), mhu.getArmorStands().get(12)));
		
		
		ArrayList<Inventory> emptyFilters = new ArrayList<Inventory>();
		ArrayList<Inventory> filledFilters = new ArrayList<Inventory>();
		
		
		for(ArmorStand as : outputAS) {
			Inventory inv = getFilterInventory(mhu, as);
			if(inv!=null) {
				
				boolean items = false;
				
				check:
				for(ItemStack i : inv.getContents()) {
					if(i!=null) {
						items = true;
						break check;
					}
				}
				
				if(items) {
					filledFilters.add(inv);
				}else {
					emptyFilters.add(inv);
				}
				
			}
		}
		
		
		placeItem:
		for(ItemStack i : inputMechInv.getInventory().getStorageContents()) {
			if(i!=null) {
				
				for(Inventory inv : filledFilters) {
					
					if(inv.containsAtLeast(i, 1)) {
						
						
						Inventory mainInv = getMainInventory(mhu, inv);
						if(mainInv!=null) {
							
							if(!Utility.inventoryFull(mainInv, i)){
								
								
								mainInv.addItem(i);
								i.setAmount(0);
								
								break placeItem;
							}
							
						}
						
					}
				}
				
				for(Inventory inv : emptyFilters) {
					
					Inventory mainInv = getMainInventory(mhu, inv);
					if(mainInv!=null) {
						
						if(!Utility.inventoryFull(mainInv, i)){
							
							mainInv.addItem(i);
							i.setAmount(0);
							
							break placeItem;
						}
						
					}
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
		
		Main.MHUList.remove(mhu);
		
	}

	
	private static Inventory getFilterInventory(MultiHeadUnit mhu, ArmorStand as) {
		if(as.getUniqueId().equals(mhu.getArmorStands().get(9).getUniqueId())) {
			return mhu.getInventories().get(0).getInventory();
		}else if(as.getUniqueId().equals(mhu.getArmorStands().get(10).getUniqueId())) {
			return mhu.getInventories().get(1).getInventory();
		}else if(as.getUniqueId().equals(mhu.getArmorStands().get(11).getUniqueId())) {
			return mhu.getInventories().get(2).getInventory();
		}else if(as.getUniqueId().equals(mhu.getArmorStands().get(12).getUniqueId())) {
			return mhu.getInventories().get(3).getInventory();
		}
		return null;
	}
	
	private static Inventory getMainInventory(MultiHeadUnit mhu, Inventory inv) {
		if(ChatColor.stripColor(inv.getTitle()).equalsIgnoreCase("[Filter] Output #1")) {
			return mhu.getInventories().get(5).getInventory();
		}else if(ChatColor.stripColor(inv.getTitle()).equalsIgnoreCase("[Filter] Output #2")) {
			return mhu.getInventories().get(6).getInventory();
		}else if(ChatColor.stripColor(inv.getTitle()).equalsIgnoreCase("[Filter] Output #3")) {
			return mhu.getInventories().get(7).getInventory();
		}else if(ChatColor.stripColor(inv.getTitle()).equalsIgnoreCase("[Filter] Output #4")) {
			return mhu.getInventories().get(8).getInventory();
		}
		return null;
	}
	
	
}
