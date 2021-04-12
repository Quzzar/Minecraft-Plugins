package quzzar.mod.pluginhelp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RecipePage {
	
	private Inventory inv;
	
	private Inventory previous;
	
	private ItemStack item;
	
	public RecipePage(ItemStack item, Inventory previous){
		
		this.item = item.clone();
		
		inv = Bukkit.createInventory(null, InventoryType.DROPPER, ChatColor.stripColor(item.getItemMeta().getDisplayName()));
		
		this.previous = previous;
		
		setRecipe();
		
		PluginHelpManager.allRecipes.add(this);
	}
	
	public Inventory getInventory(){
		return inv;
	}
	
	public Inventory getPreviousInventory(){
		return previous;
	}
	
	public ItemStack getItem(){
		return item;
	}
	
	
	private void setRecipe(){
		
		HelpUtil.displayRecipe(inv, item);
		
	}
	
	
	
	
}
