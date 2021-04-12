package quzzar.mod;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import quzzar.mod.Textures.TextureDatabase;

public class HeadUnitShapelessRecipe {

	private ShapelessRecipe shR;
	private TextureDatabase tex;
	private ItemStack result;
	
	public static ArrayList<HeadUnitShapelessRecipe> list = new ArrayList<HeadUnitShapelessRecipe>();
	
	public HeadUnitShapelessRecipe(ShapelessRecipe shR, TextureDatabase tex, ItemStack result){
		this.shR = shR;
		this.tex = tex;
		this.result = result;
	}
	
	
	public ShapelessRecipe getRecipe(){
		return shR;
	}
	
	public ItemStack getResult(){
		return result;
	}
	
	public TextureDatabase getTexture(){
		return tex;
	}
	
	public void remove(){
		list.remove(this);
	}
	
	
	public static void addNew(ShapelessRecipe shR, TextureDatabase tex, ItemStack result){
		list.add(new HeadUnitShapelessRecipe(shR, tex, result));
	}
	
}
