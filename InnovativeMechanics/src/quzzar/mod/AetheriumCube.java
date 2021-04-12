package quzzar.mod;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.quzzar.im.versioncontrol.VersionControl;
import com.quzzar.im.versioncontrol.global.material.GMaterial;

import quzzar.mod.Textures.TextureDatabase;

public class AetheriumCube {
	
	
	public static void placeAetheriumCube(Player p, ItemStack i) {
		
		if(p.getLocation().getPitch()<70) {
			
			Block b = p.getEyeLocation().add(p.getLocation().getDirection().multiply(2)).getBlock();
			
			BlockBreakEvent eventBreak = new BlockBreakEvent(b, p);
			Bukkit.getServer().getPluginManager().callEvent(eventBreak);
			
			if (!eventBreak.isCancelled()){
				
				b.setType(VersionControl.getMaterial(GMaterial.PLAYER_HEAD_BLOCK));
				Skull skull = (Skull) b.getState();
				skull.setOwningPlayer(Bukkit.getOfflinePlayer(TextureDatabase.AETHERIUM_CUBE.getUUID()));
				skull.update();
				
				if(!p.getGameMode().equals(GameMode.CREATIVE)) {
					i.setAmount(i.getAmount()-1);
				}
				
				
			}
			
		}
		
	}
	
	
	
}
