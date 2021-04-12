package quzzar.mod.furnace;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.quzzar.im.versioncontrol.VersionControl;
import com.quzzar.im.versioncontrol.global.material.GMaterial;

import quzzar.mod.ItemsList;

public enum Smeltables {
	
	IRON_ORE(new ItemStack(Material.IRON_ORE), new ItemStack(Material.IRON_INGOT)),
	GOLD_ORE(new ItemStack(Material.GOLD_ORE), new ItemStack(Material.GOLD_INGOT)),
	DIAMOND_ORE(new ItemStack(Material.DIAMOND_ORE), new ItemStack(Material.DIAMOND)),
	COAL_ORE(new ItemStack(Material.COAL_ORE), new ItemStack(Material.COAL)),
	EMERALD_ORE(new ItemStack(Material.EMERALD_ORE), new ItemStack(Material.EMERALD)),
	QUARTZ_ORE(new ItemStack(VersionControl.getMaterial(GMaterial.QUARTZ_ORE)), new ItemStack(Material.QUARTZ)),
	REDSTONE_ORE(new ItemStack(Material.REDSTONE_ORE), new ItemStack(Material.REDSTONE,4)),
	LAPIS_ORE(new ItemStack(Material.LAPIS_ORE), VersionControl.getItemStack(GMaterial.LAPIS_LAZULI, 6)),
	SAND(new ItemStack(Material.SAND), new ItemStack(Material.GLASS)),
	
	COAL_CHUNKS(ItemsList.Coal_Chunks(1), new ItemStack(Material.COAL, 2)),
	IRON_CHUNKS(ItemsList.Iron_Chunks(1), new ItemStack(Material.IRON_INGOT, 2)),
	GOLD_CHUNKS(ItemsList.Gold_Chunks(1), new ItemStack(Material.GOLD_INGOT, 2)),
	LAPIS_CHUNKS(ItemsList.Lapis_Chunks(1), VersionControl.getItemStack(GMaterial.LAPIS_LAZULI, 12)),
	REDSTONE_CHUNKS(ItemsList.Redstone_Chunks(1), new ItemStack(Material.REDSTONE, 8)),
	EMERALD_CHUNKS(ItemsList.Emerald_Chunks(1), new ItemStack(Material.EMERALD, 2)),
	DIAMOND_CHUNKS(ItemsList.Diamond_Chunks(1), new ItemStack(Material.DIAMOND, 2));
	
	
	private ItemStack item;
	private ItemStack product;
	private int index = -1;
	
	Smeltables(ItemStack item, ItemStack product){
		this.item = item;
		this.product = product;
	}
	
	public ItemStack getInput(){
		return item;
	}
	
	public ItemStack getProduct(){
		return product;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
}
