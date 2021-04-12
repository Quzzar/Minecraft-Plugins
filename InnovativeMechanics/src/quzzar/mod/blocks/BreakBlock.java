package quzzar.mod.blocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import quzzar.mod.HeadUnit;
import quzzar.mod.MultiHeadUnit;
import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.maps.TextureItemStackMapManager;

public class BreakBlock {

	public static void headBreak(Block b, TextureDatabase tex){
		
		ItemStack i  = TextureItemStackMapManager.getItemStack(tex,1);
		
		if(i!=null) {
			
			b.getWorld().dropItemNaturally(b.getLocation(), i);
			HeadUnit.delete(b);
			
		}
		
	}
	
	
	public static void MHUForceBreak(MultiHeadUnit mhu, Location loc, boolean drop){
		
		
		ItemStack i = TextureItemStackMapManager.getItemStack(mhu.getItemHU().getTexture(),1);
		
		if(i!=null) {
			
			if(drop){
				loc.getWorld().dropItemNaturally(loc, i);
			}
			mhu.delete();
			
		}
		
	}
	
	public static void MHUPlayerBreak(MultiHeadUnit mhu, Location loc, boolean drop, Player p){
		
		
		BlockBreakEvent eventBreak = new BlockBreakEvent(mhu.getBlock(), p);
		Bukkit.getServer().getPluginManager().callEvent(eventBreak);
		
		if (!eventBreak.isCancelled()){
			MHUForceBreak(mhu, loc, drop);
		}else {
			mhu.setDamage(0);
		}
		
	}
	
	
	
}
