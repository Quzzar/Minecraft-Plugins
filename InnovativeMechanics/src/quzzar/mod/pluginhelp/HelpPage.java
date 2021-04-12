package quzzar.mod.pluginhelp;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HelpPage {
	
	private ItemStack displayItem;
	
	private ItemStack backItem;
	
	private Inventory inv;
	
	private Inventory previous;
	
	public ArrayList<ItemViewer> itemList = new ArrayList<ItemViewer>();
	
	
	public HelpPage(ItemStack displayItem, String title, int size){
		
		inv = Bukkit.createInventory(null, size, title);
		
		this.displayItem = displayItem.clone();
		
		this.previous = PluginHelpManager.getHomeInventory();
		
		this.backItem = HelpUtil.createBackItem(previous);
		
		refresh();
		
		PluginHelpManager.allPages.add(this);
	}
	
	public void addItemViewer(ItemViewer iv){
		itemList.add(iv);
		refresh();
	}
	
	public void addItemViewer(ItemStack itemstack, List<String> list){
		itemList.add(new ItemViewer(itemstack, list, this.getInventory()));
		refresh();
	}
	
	public void refresh(){
		inv.clear();
		
		inv.addItem(backItem);
		
		for(ItemViewer iViewer : itemList){
			inv.addItem(iViewer.getDisplayItem());
		}
		
	}
	
	public Inventory getInventory(){
		return inv;
	}
	
	public ItemStack getDisplayItem(){
		return displayItem;
	}
	
	public ItemStack getBackItem(){
		return backItem;
	}
	
	public Inventory getPreviousInventory(){
		return previous;
	}
	
	
	
}
