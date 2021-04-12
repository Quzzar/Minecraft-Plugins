package quzzar.mod.pluginhelp.items;

import java.util.List;

import org.bukkit.inventory.ItemStack;

import quzzar.mod.LangHandler;

public class ItemHelpInstance {

	private ItemStack item;
	private List<String> info;
	private String category;
	
	public ItemHelpInstance(ItemStack item, String category){
		this.item = item;
		this.category = LangHandler.applyCategoryTitle(category.replaceAll("_", " "));
	}
	
	public ItemHelpInstance(ItemStack item, String category, List<String> info){
		this.item = item;
		this.category = LangHandler.applyCategoryTitle(category.replaceAll("_", " "));
		this.info = info;
	}
	
	public ItemStack getItemStack() {
		return item;
	}
	
	public void setInfo(List<String> info) {
		this.info = info;
	}
	
	public List<String> getInfo(){
		return info;
	}
	
	public String getCategory() {
		return category;
	}
	
}
