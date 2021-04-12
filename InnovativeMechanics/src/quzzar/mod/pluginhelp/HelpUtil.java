package quzzar.mod.pluginhelp;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.im.versioncontrol.VersionControl;
import com.quzzar.im.versioncontrol.global.material.GMaterial;

import quzzar.mod.HeadUnitShapedRecipe;
import quzzar.mod.HeadUnitShapelessRecipe;
import quzzar.mod.maps.TextureItemStackMapManager;

public class HelpUtil {
	
	public static ItemViewer getItemViewer(ItemStack item){
		for(ItemViewer itemView : PluginHelpManager.allItems){
			if(itemView.getItem().isSimilar(item)){
				return itemView;
			}
		}
		return null;
	}
	
	public static RecipePage getRecipeViewer(ItemStack item){
		for(RecipePage recView : PluginHelpManager.allRecipes){
			if(recView.getItem().isSimilar(item)){
				return recView;
			}
		}
		return null;
	}
	
	
	public static ItemStack createRecipeClickItem(ItemStack item){
		
		ItemStack i = VersionControl.getItemStack(GMaterial.CRAFTING_TABLE, 1);
		
		ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BLUE+"Recipe");
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GRAY+"<"+ChatColor.stripColor(item.getItemMeta().getDisplayName())+">");
	    im.setLore(lore);
	    i.setItemMeta(im);
		
	    return i;
		
	}
	
	public static ItemStack createItemViewerDisplayItem(ItemStack item){
		
		ItemMeta im = item.getItemMeta();
	    im.setLore(null);
	    item.setItemMeta(im);
		
	    return item;
		
	}
	
	public static ItemStack createHelpPageDisplayItem(ItemStack item, String name){
		
		ItemStack newItem = item.clone();
		
		ItemMeta im = newItem.getItemMeta();
		im.setDisplayName(name);
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GRAY+"<Page>");
	    im.setLore(lore);
	    newItem.setItemMeta(im);
		
	    return newItem;
		
	}
	
	public static ItemStack createInfoItem(List<String> list){
		
		ItemStack i = new ItemStack(Material.NAME_TAG);
		
		ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.GOLD+""+ChatColor.ITALIC+"Info.");
	    
	    if(list != null) {
	    	
		    List<String> newList = new ArrayList<String>();
		    for(String line : list) {
		    	newList.add(ChatColor.GRAY+line);
		    }
		    im.setLore(newList);
	    	
	    }
	    
	    i.setItemMeta(im);
		
	    return i;
		
	}
	
	public static ItemStack createBackItem(Inventory previous){
		
		ItemStack i = VersionControl.getItemStack(GMaterial.WOOD_DOOR, 1);
		
		ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.RED+"<- Back");
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GRAY+"<"+ChatColor.stripColor(previous.getTitle())+">");
	    im.setLore(lore);
	    i.setItemMeta(im);
		
	    return i;
		
	}
	
	public static void displayInventory(HumanEntity he, Inventory inv){
		he.closeInventory();
		he.openInventory(inv);
	}
	
	
	public static void displayRecipe(Inventory inv, ItemStack item) {
		
		inv.clear();
		
		for(Recipe r : Bukkit.getRecipesFor(item)){
			if(r instanceof ShapedRecipe){
				
				ShapedRecipe shR = (ShapedRecipe) r;
				
				if(shR.getResult().isSimilar(item)){
					
					int strCount = 0;
					for(String str : shR.getShape()){
						int charCount = 0;
						for(char c : str.toCharArray()){
							
							int index = charCount+strCount*3;
							
							if(shR.getIngredientMap().get(c)!=null){
								
								ItemStack i = shR.getIngredientMap().get(c).clone();
								
								//adjustWoodItems(i);
								
								inv.setItem(index, i);
							}else{
								inv.setItem(index, VersionControl.getItemStack(GMaterial.LIGHT_GRAY_STAINED_GLASS_PANE, 1));
							}
							
							charCount++;
						}
						strCount++;
					}
					
					for(int n=0; n<inv.getContents().length; n++){
						ItemStack i = inv.getContents()[n];
						if(i!=null){
							if(VersionControl.areSameMaterial(i, GMaterial.WHITE_STAINED_GLASS_PANE)){
								i.setAmount(0);
							}
						}
					}
					
					return;
					
				}
				
				
			}else if(r instanceof ShapelessRecipe){
					
				ShapelessRecipe shless = (ShapelessRecipe) r;
				
				if(shless.getResult().isSimilar(item)){
					
					for(ItemStack i : shless.getIngredientList()){
						
						place:
						for(int n=0; n<inv.getContents().length; n++){
							if(inv.getContents()[n]==null){
								
								ItemStack iNew = i.clone();
								
								//adjustWoodItems(iNew);
								
								inv.setItem(n, iNew);
								break place;
							}
						}
						
					}
					
					return;
					
				}
				
			}
		}
		
		for(HeadUnitShapedRecipe HUr: HeadUnitShapedRecipe.list){
			
			
			if(HUr.getResult().isSimilar(item)){
				
				int strCount = 0;
				for(String str : HUr.getRecipe().getShape()){
					int charCount = 0;
					for(char c : str.toCharArray()){
						
						int index = charCount+strCount*3;
						
						if(HUr.getRecipe().getIngredientMap().get(c)!=null){
							inv.setItem(index, HUr.getRecipe().getIngredientMap().get(c));
						}else{
							inv.setItem(index, VersionControl.getItemStack(GMaterial.LIGHT_GRAY_STAINED_GLASS_PANE, 1));
						}
						
						charCount++;
					}
					strCount++;
				}
				
				
				for(int n=0; n<inv.getContents().length; n++){
					ItemStack i = inv.getContents()[n];
					if(i!=null){
						if(VersionControl.areSameMaterial(i, GMaterial.WHITE_STAINED_GLASS_PANE)){
							i.setAmount(0);
						} else if(VersionControl.areSameMaterial(i, GMaterial.PLAYER_HEAD_ITEM)){
							
							inv.setItem(n, TextureItemStackMapManager.getItemStack(HUr.getTexture(), 1));
						}
					}
				}
				
				return;
				
			}
			
		}
		
		
		for(HeadUnitShapelessRecipe HUrless: HeadUnitShapelessRecipe.list){
			
			
			if(HUrless.getResult().isSimilar(item)){
				
				for(ItemStack i : HUrless.getRecipe().getIngredientList()){
					
					place:
					for(int n=0; n<inv.getContents().length; n++){
						if(inv.getContents()[n]==null){
							
							inv.setItem(n, i);
							break place;
						}
					}
					
				}
				
				
				for(int n=0; n<inv.getContents().length; n++){
					ItemStack i = inv.getContents()[n];
					if(i!=null){
						if(VersionControl.areSameMaterial(i, GMaterial.PLAYER_HEAD_ITEM)){
							
							inv.setItem(n, TextureItemStackMapManager.getItemStack(HUrless.getTexture(), 1));
						}
					}
				}
				
				return;
				
			}
			
		}
		
	}
	
	/*
	private static void adjustWoodItems(ItemStack i) {
		
		if(i.getType().equals(Material.LOG)){
			i.setDurability((short) 0);
		} else if(i.getType().equals(Material.LOG)||i.getType().equals(Material.LOG_2)){
			i.setDurability((short) 0);
		} else if(i.getType().equals(Material.WOOD_STEP)){
			i.setDurability((short) 0);
		}
		
	}
	*/
	
}
