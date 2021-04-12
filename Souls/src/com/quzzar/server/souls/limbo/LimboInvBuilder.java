package com.quzzar.server.souls.limbo;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import com.mojang.authlib.GameProfile;
import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.InvDataTag;
import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;
import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;
import com.quzzar.server.souls.character.packets.skin.SkinManager;

public class LimboInvBuilder {

	private static ArrayList<Inventory> skinSelectionInvs;
	
	static {
		
		skinSelectionInvs = createSkinSelectionInvs();
		
	}
	
	private static ArrayList<Inventory> createSkinSelectionInvs(){
		
		final int SKIN_INV_SIZE = 54;
		final String SKIN_INV_TITLE = "Character Skins - Page: ";
		
		ArrayList<Inventory> skinInvs = new ArrayList<Inventory>();
		
		int skinCount = 0;
		int totalSkinCount = 0;
		
		int pageCount = 1;
		int pageMaxCount = (int) Math.ceil(SkinManager.getAllSkins().size()/(SKIN_INV_SIZE-9)) + 1;
		
		Inventory inv = Bukkit.createInventory(null, SKIN_INV_SIZE, SKIN_INV_TITLE+pageCount+"/"+pageMaxCount);
		CraftMeta.invSet(inv, InvDataTag.CHARACTER_CREATION_SKIN_SELECT, pageCount-1);
		
		for(GameProfile profile : SkinManager.getAllSkins()) {
			
			if(skinCount < SKIN_INV_SIZE-9) {
				
				ItemStack head = SkinManager.getHead(profile);
				ItemMeta im = head.getItemMeta();
				im.setDisplayName(ChatColor.GOLD+"Skin #"+totalSkinCount);
				head.setItemMeta(im);
				
				inv.setItem(skinCount, head);
				
				
				skinCount++; totalSkinCount++;
				
			} else {
				
				finalizePage(inv, pageCount, pageMaxCount);
				skinInvs.add(inv);
				pageCount++;
				inv = Bukkit.createInventory(null, SKIN_INV_SIZE, SKIN_INV_TITLE+pageCount+"/"+pageMaxCount);
				CraftMeta.invSet(inv, InvDataTag.CHARACTER_CREATION_SKIN_SELECT, pageCount-1);
				skinCount = 0;
				
			}
			
		}
		
		finalizePage(inv, pageCount, pageMaxCount);
		skinInvs.add(inv);
		
		return skinInvs;
		
	}
	
	private static void finalizePage(Inventory inv, int pageCount, int pageMaxCount) {
		
		if(pageCount != 1) {
			inv.setItem(45, LimboItemCreator.getSkinPreviousPage());
		}
		
		inv.setItem(49, LimboItemCreator.getSkinSelectInfo());
		
		if(pageCount != pageMaxCount) {
			inv.setItem(53, LimboItemCreator.getSkinNextPage());
		}
		
		fillInvWithItem(inv, LimboItemCreator.getInvBorder());
		
	}
	
	private static void fillInvWithItem(Inventory inv, ItemStack item) {
		for(int i=0; i<inv.getSize(); i++){
			
			if(inv.getItem(i)==null) {
				inv.setItem(i, item);
			}
			
		}
	}
	
	///////////
	
	public static ArrayList<Inventory> getSkinSelectionInvs(){
		return skinSelectionInvs;
	}
	
	public static void drawLimboInv(Player player) {
		
		PlayerCharacter pChar = CharacterManager.getCharacter(player);
		PlayerInventory inv = player.getInventory();
		inv.clear();
		
		inv.setItem(0, LimboItemCreator.getAboutPaper());
		inv.setItem(1, ItemManager.getItemClone(ItemType.GRAPPLING_HOOK_INFINITE));
		inv.setItem(3, LimboItemCreator.getSetSkin());
		if(pChar.canCreateCharacter()) {
			inv.setItem(4, LimboItemCreator.getCreateCharacter());
		} else {
			inv.setItem(4, LimboItemCreator.getSoulRecharging());
		}
		inv.setItem(5, LimboItemCreator.getSetName());
		inv.setItem(8, LimboItemCreator.getSouls(player));
		
	}
	
	public static void setStartingInv(Player player) {
		
		PlayerInventory inv = player.getInventory();
		inv.clear();
		
		inv.setItem(0, ItemManager.getItemClone(ItemType.ANDESITE_SWORD));
		inv.setItem(1, ItemManager.getItemClone(ItemType.ANDESITE_PICKAXE));
		inv.setItem(2, ItemManager.getItemClone(ItemType.ANDESITE_AXE));
		inv.setItem(3, new ItemStack(Material.COOKED_MUTTON, 16));
		
		inv.setItem(5, ItemManager.getItemClone(ItemType.PLAYER_GUIDE));
		
		inv.setItem(7, new ItemStack(Material.NAUTILUS_SHELL));
		inv.setItem(8, ItemManager.getItemClone(ItemType.PORTABLE_CRAFTING_TABLE));
		
		inv.setItem(EquipmentSlot.HEAD, new ItemStack(Material.LEATHER_HELMET));
		inv.setItem(EquipmentSlot.CHEST, new ItemStack(Material.LEATHER_CHESTPLATE));
		inv.setItem(EquipmentSlot.LEGS, new ItemStack(Material.CHAINMAIL_LEGGINGS));
		inv.setItem(EquipmentSlot.FEET, new ItemStack(Material.LEATHER_BOOTS));
		
	}
	
}
