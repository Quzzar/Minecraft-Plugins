package quzzar.mod;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import quzzar.mod.Textures.TextureDatabase;

public class HeadUnitShapedRecipe {

	
	private ShapedRecipe shR;
	private TextureDatabase tex;
	private ItemStack result;
	
	public static ArrayList<HeadUnitShapedRecipe> list = new ArrayList<HeadUnitShapedRecipe>();
	
	public HeadUnitShapedRecipe(ShapedRecipe shR, TextureDatabase tex, ItemStack result){
		this.shR = shR;
		this.tex = tex;
		this.result = result;
	}
	
	
	public ShapedRecipe getRecipe(){
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
	
	
	public static void addNew(ShapedRecipe shR, TextureDatabase tex, ItemStack result){
		list.add(new HeadUnitShapedRecipe(shR, tex, result));
	}
	
	
}
