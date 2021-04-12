package quzzar.mod.autocrafttable;

import org.bukkit.inventory.ItemStack;

public class ItemStackData {

	private ItemStack i;
	private int index;
	
	public ItemStackData(ItemStack i, int index){
		this.i = i;
		this.index = index;
	}
	
	
	public ItemStack getItemStack(){
		return i;
	}
	
	
	public int getIndex(){
		return index;
	}
	
}
