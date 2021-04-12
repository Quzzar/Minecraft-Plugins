package quzzar.mod;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import com.quzzar.im.versioncontrol.VersionControl;
import com.quzzar.im.versioncontrol.global.material.GMaterial;

public class HURecipeLayouts {

	
	private static ItemStack nothing = new ItemStack(Material.AIR);
	
	
	public static ShapelessRecipe shlessSingle;
	public static ShapelessRecipe shlessThree;
	
	public static ShapedRecipe shapedFull;
	
	
	public static void loadLayouts(Main main){
		
		generalLayouts(main);
		
	}
	
	
	public static void generalLayouts(Main main){
		
		shlessSingle = new ShapelessRecipe(new NamespacedKey(Main.instance,"Layout_ShapelessSingle"),nothing);
		shlessSingle.addIngredient(VersionControl.getMaterial(GMaterial.PLAYER_HEAD_ITEM));
	    main.getServer().addRecipe(shlessSingle);
	    
	    shlessThree = new ShapelessRecipe(new NamespacedKey(Main.instance,"Layout_ShapelessThree"),nothing);
	    shlessThree.addIngredient(VersionControl.getMaterial(GMaterial.PLAYER_HEAD_ITEM));
	    main.getServer().addRecipe(shlessThree);
		
	    
	    shapedFull = new ShapedRecipe(new NamespacedKey(Main.instance,"Layout_ShapedFull"),nothing);
	    shapedFull.shape("SSS", "SSS", "SSS");
	    shapedFull.setIngredient('S', VersionControl.getMaterial(GMaterial.PLAYER_HEAD_ITEM));
	    main.getServer().addRecipe(shapedFull);
		
	}
	
	
}
