package quzzar.mod.autocrafttable;

import org.bukkit.inventory.ItemStack;

public class CraftingInstance {

	private ItemStack i;
	
	public CraftingInstance(ItemStack i){
		this.i = i;
	}

	public ItemStack getItemStack() {
		return i;
	}
	
	
}
