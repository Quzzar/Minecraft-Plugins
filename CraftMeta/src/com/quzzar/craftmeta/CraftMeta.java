package com.quzzar.craftmeta;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.quzzar.craftmeta.metamap.EntityMetaManager;
import com.quzzar.craftmeta.metamap.InventoryMetaManager;
import com.quzzar.craftmeta.metamap.ItemMetaManager;
import com.quzzar.craftmeta.tags.EntityDataTag;
import com.quzzar.craftmeta.tags.InvDataTag;
import com.quzzar.craftmeta.tags.ItemDataTag;

public class CraftMeta extends JavaPlugin {
	
	private static ItemMetaManager itemMeta;
	private static InventoryMetaManager invMeta;
	private static EntityMetaManager entityMeta;
	
	@Override
	public void onEnable(){
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"Initializing CraftMeta...");
		
		itemMeta = new ItemMetaManager();
		invMeta = new InventoryMetaManager();
		entityMeta = new EntityMetaManager(this);
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"Loaded!");
	}
	
	//////
	
	public static void itemSet(ItemStack item, ItemDataTag dataTag, Object data) {
		itemMeta.setData(item, dataTag.name(), data);
	}
	
	public static void itemSet(ItemStack item, ItemDataTag dataTag) {
		itemMeta.setData(item, dataTag.name(), true);
	}
	
	public static Object itemGet(ItemStack item, ItemDataTag dataTag) {
		return itemMeta.getData(item, dataTag.name());
	}
	
	public static boolean itemHas(ItemStack item) {
		return itemMeta.hasData(item);
	}
	
	public static boolean itemHasData(ItemStack item, ItemDataTag dataTag) {
		if(!itemMeta.hasData(item)) {return false;}
		Object data = itemGet(item, dataTag);
		return data != null;
	}
	
	//////
	
	
	public static void invSet(Inventory inv, InvDataTag dataTag, Object data) {
		invMeta.setData(inv, dataTag.name(), data);
	}
	
	public static void invSet(Inventory inv, InvDataTag dataTag) {
		invMeta.setData(inv, dataTag.name(), true);
	}
	
	public static Object invGet(Inventory inv, InvDataTag dataTag) {
		return invMeta.getData(inv, dataTag.name());
	}
	
	public static boolean invHas(Inventory inv) {
		return invMeta.hasData(inv);
	}
	
	public static boolean invHasData(Inventory inv, InvDataTag dataTag) {
		if(!invMeta.hasData(inv)) {return false;}
		Object data = invGet(inv, dataTag);
		return data != null;
	}
	
	//////
	
	public static void entitySet(Entity entity, EntityDataTag dataTag, Object data) {
		entityMeta.setData(entity, dataTag.name(), data);
	}
	
	public static void entitySet(Entity entity, EntityDataTag dataTag) {
		entityMeta.setData(entity, dataTag.name(), true);
	}
	
	public static Object entityGet(Entity entity, EntityDataTag dataTag) {
		return entityMeta.getData(entity, dataTag.name());
	}
	
	public static boolean entityHas(Entity entity) {
		return entityMeta.hasData(entity);
	}
	
	public static boolean entityHasData(Entity entity, EntityDataTag dataTag) {
		if(!entityMeta.hasData(entity)) {return false;}
		Object data = entityGet(entity, dataTag);
		return data != null;
	}
	
}
