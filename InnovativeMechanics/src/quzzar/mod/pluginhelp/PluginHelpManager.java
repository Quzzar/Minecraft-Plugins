package quzzar.mod.pluginhelp;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

import quzzar.mod.pluginhelp.items.ItemHelp;
import quzzar.mod.pluginhelp.items.ItemHelpInstance;

public class PluginHelpManager {
	
	public static ArrayList<HelpPage> allPages = new ArrayList<HelpPage>();
	public static ArrayList<ItemViewer> allItems = new ArrayList<ItemViewer>();
	public static ArrayList<RecipePage> allRecipes = new ArrayList<RecipePage>();
	
	public static ArrayList<Inventory> helpInventories = new ArrayList<Inventory>();
	
	private static Inventory homeInv;
	
	public static void initialize(){
		
		homeInv = Bukkit.createInventory(null, 9, ChatColor.BLACK+"I"+ChatColor.ITALIC+"nnovative "+ChatColor.BLACK+"M"+ChatColor.ITALIC+"echanics "+ChatColor.BOLD+""+ChatColor.ITALIC+"[Index]");
		
		for(ItemHelpInstance instance : ItemHelp.getItemsList()){
			
			
			boolean found = false;
			for(HelpPage page : allPages){
				if(ChatColor.stripColor(page.getInventory().getTitle()).equals(instance.getCategory())){
					found = true;
					page.addItemViewer(instance.getItemStack(), instance.getInfo());
				}
			}
			
			if(!found){
				HelpPage page = new HelpPage(HelpUtil.createHelpPageDisplayItem(instance.getItemStack(), ChatColor.BLUE+instance.getCategory()), instance.getCategory(), 18);
				page.addItemViewer(instance.getItemStack(), instance.getInfo());
			}
			
		}
		
		
		for(HelpPage page : allPages){
			helpInventories.add(page.getInventory());
			
			homeInv.addItem(page.getDisplayItem());
			
		}
		
		////
		
		helpInventories.add(homeInv);
		
		for(ItemViewer itemV : allItems){
			helpInventories.add(itemV.getInventory());
		}
		
		for(RecipePage recipeV : allRecipes){
			helpInventories.add(recipeV.getInventory());
		}
		
		
		
	}
	
	
	public static Inventory getHomeInventory(){
		return homeInv;
	}
	
}
