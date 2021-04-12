package quzzar.mod.maps;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

import quzzar.mod.Textures.TextureDatabase;

public class TextureItemStackMapManager {

	
	public static ArrayList<TextureItemStackMap> list = new ArrayList<TextureItemStackMap>();
	
	public static void addNew(TextureDatabase tex, ItemStack i){
		if(!containsTexture(tex)){
			list.add(new TextureItemStackMap(tex, i));
		}
	}
	
	public static void remove(TextureItemStackMap unit){
		list.remove(unit);
	}
	
	
	public static ItemStack getItemStack(TextureDatabase tex, int amt){
		for(TextureItemStackMap unit : list){
			if(unit.getTexture().equals(tex)){
				ItemStack i = unit.getItemStack();
				i.setAmount(amt);
				return i;
			}
		}
		return null;
	}
	
	public static TextureDatabase getTexture(ItemStack i){
		for(TextureItemStackMap unit : list){
			if(unit.getItemStack().isSimilar(i)){
				return unit.getTexture();
			}
		}
		return null;
	}
	
	
	public static boolean containsTexture(TextureDatabase tex){
		for(TextureItemStackMap unit : list){
			if(unit.getTexture().equals(tex)){
				return true;
			}
		}
		return false;
	}
	
	
}
