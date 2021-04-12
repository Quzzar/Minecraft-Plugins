package quzzar.mod.pluginhelp;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemViewer {
	
	private Inventory inv;
	
	private Inventory previous;
	
	private ItemStack item;
	
	private ItemStack backItem;
	private ItemStack recipeItem;
	private ItemStack displayItem;
	
	private ItemStack infoItem;
	
	private RecipePage recView;
	
	public ItemViewer(ItemStack item, List<String> list, Inventory previous){
		
		this.item = item;
		
		this.inv = Bukkit.createInventory(null, InventoryType.HOPPER, ChatColor.stripColor(item.getItemMeta().getDisplayName()));
		
		this.previous = previous;
		
		this.backItem = HelpUtil.createBackItem(previous);
		this.recipeItem = HelpUtil.createRecipeClickItem(item.clone());
		this.displayItem = HelpUtil.createItemViewerDisplayItem(item.clone());
		
		this.infoItem = HelpUtil.createInfoItem(list);
		
		
		inv.setItem(0, backItem);
		
		inv.setItem(2, displayItem);
		
		if(list!=null) {
			inv.setItem(3, infoItem);
		}
		
		inv.setItem(4, recipeItem);
		
		
		recView = new RecipePage(item, inv);
		
		PluginHelpManager.allItems.add(this);
	}
	
	
	public ItemStack getItem(){
		return item;
	}
	
	public ItemStack getBackItem(){
		return backItem;
	}
	public ItemStack getRecipeItem(){
		return recipeItem;
	}
	public ItemStack getDisplayItem(){
		return displayItem;
	}
	
	public ItemStack getInfoItem(){
		return infoItem;
	}
	
	public Inventory getInventory(){
		return inv;
	}
	
	public Inventory getPreviousInventory(){
		return previous;
	}
	
	public RecipePage getRecipeViewer(){
		return recView;
	}
	
}
