package quzzar.mod.ListenerSubsets;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import quzzar.mod.Utility;
import quzzar.mod.WoodenCrate;
import quzzar.mod.Textures.TextureDatabase;

public class CrateListener {

	public static void openCrate(ItemStack i, Player p){
		
		
		if(Utility.getTexture(i).equals(TextureDatabase.WOOD_CRATE_COMMON)){
			
			WoodenCrate.open(i, TextureDatabase.WOOD_CRATE_COMMON,p);
			
		} else if(Utility.getTexture(i).equals(TextureDatabase.WOOD_CRATE_UNCOMMON)){
			
			WoodenCrate.open(i, TextureDatabase.WOOD_CRATE_UNCOMMON,p);
			
		} else if(Utility.getTexture(i).equals(TextureDatabase.WOOD_CRATE_RARE)){
			
			WoodenCrate.open(i, TextureDatabase.WOOD_CRATE_RARE,p);
			
		}
		
		
	}
	
	
}
