package quzzar.mod.Utilities;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import quzzar.mod.HeadUnit;
import quzzar.mod.Main;
import quzzar.mod.MultiHeadUnit;
import quzzar.mod.Utility;
import quzzar.mod.Textures.TextureCollection;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.inventories.mechInv.MechInv;
import quzzar.mod.inventories.mechInv.MechInvAS;
import quzzar.mod.inventories.mechInv.MechInvManager;
import quzzar.mod.mhu.LargeMachineUnit;
import quzzar.mod.mhu.MHURegistrar;

public class MHU_Utilities {
	
	
	public static void changeLook(MultiHeadUnit mhu, TextureCollection texCol){
		
		ArrayList<ItemStack> itsCol = texCol.getRawItemStackCollection(1);
		
		
		for(int i=0; i<texCol.getCollection().size(); i++){
			
			if(!texCol.getCollection().get(i).getType().equals(BlockType.STORAGE)&&!texCol.getCollection().get(i).getType().equals(BlockType.GLOBALSTORAGE)){
				mhu.getArmorStands().get(i).setHelmet(itsCol.get(i));
			}
			
		}
		
	}
	
	
	public static MultiHeadUnit getMHU(ArmorStand as){
		for(MultiHeadUnit lb : Main.MHUList){
			if(lb.getArmorStands().contains(as)){
				return lb;
			}
		}
		return null;
	}
	
	public static MultiHeadUnit getMHU(Block b){
		for(MultiHeadUnit mhu : Main.MHUList){
			if(mhu.getBlock().equals(b)){
				return mhu;
			}
		}
		return null;
	}
	
	
	public static void deleteInventories(MultiHeadUnit mhu){
		
		if(mhu.getInventories()!=null) {
			
			for(MechInvAS mechInv : mhu.getInventories()){
				
				for(ItemStack i : mechInv.getInventory().getStorageContents()){
					if(i!=null){
						mechInv.getInventory().remove(i);
						mechInv.getArmorStand().getWorld().dropItem(mechInv.getArmorStand().getEyeLocation(), i);
					}
				}
				
				mechInv.delete();
				
			}
			
		}
		
		if(MechInvManager.getMechInventories(mhu.getItemHU())!=null) {
			
			for(MechInv mechInv : MechInvManager.getMechInventories(mhu.getItemHU())){
				
				for(ItemStack i : mechInv.getInventory().getStorageContents()){
					if(i!=null){
						mechInv.getInventory().remove(i);
						mhu.getCenterLocation().getWorld().dropItem(mhu.getCenterLocation(), i);
					}
				}
				
				mechInv.delete();
				
			}
			
		}
		
	}
	
	public static LargeMachineUnit getMachineType(HeadUnit headunit) {
		for(LargeMachineUnit machine : MHURegistrar.typesList){
			
			if(headunit.getType().equals(machine.getMachineType())){
				
				return machine;
			}
			
		}
		
		return null;
	}
	
	public static double getSerializedID(UUID uuid) {
		
		String strUUID = uuid.toString().replaceAll("[^\\d.]", "");
		
		strUUID = strUUID.substring(0, strUUID.length() / 4);
		
		double id = 0;
		try {
			id = Double.parseDouble(strUUID);
		} catch(NullPointerException e) {
			Utility.tellConsole(ChatColor.RED+"Failed to serialize Armor Stand [ParseInt]!");
		}
		
		return id;
		
	}
	
}
