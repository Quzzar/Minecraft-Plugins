package com.quzzar.server.souls.limbo;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;

public class LimboItemCreator {

	private static ItemStack createCharacter, soulRecharging, setName, setSkin, aboutPaper,
		invBorder, skinSelectInfo, skinPreviousPage, skinNextPage;
	
	static {
		
		setName = createSetName();
		setSkin = createSetSkin();
		createCharacter = createCreateCharacter();
		soulRecharging = createSoulRecharging();
		aboutPaper = createAboutPaper();
		invBorder = createInvBorder();
		skinSelectInfo = createSkinSelectInfo();
		skinPreviousPage = createSkinPreviousPage();
		skinNextPage = createSkinNextPage();
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Private - Create Items /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	
	private static ItemStack createCreateCharacter(){
		
		ItemStack i = new ItemStack(Material.END_CRYSTAL);
		
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"Complete");
		i.setItemMeta(im);
		
		return i;
	}
	
	private static ItemStack createSoulRecharging(){
		
		ItemStack i = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.DARK_AQUA+""+ChatColor.ITALIC+"Soul Recharging...");
		i.setItemMeta(im);
		
		return i;
	}
		
	private static ItemStack createSetSkin(){
			
			ItemStack i = new ItemStack(Material.PLAYER_HEAD);
			
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(ChatColor.YELLOW+"Set Skin");
			i.setItemMeta(im);
			
			return i;
		}
	
	private static ItemStack createSetName(){
		
		ItemStack i = new ItemStack(Material.NAME_TAG);
		
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.YELLOW+"Set Name");
		i.setItemMeta(im);
		
		return i;
	}
	
	private static ItemStack createAboutPaper(){
		
		ItemStack i = new ItemStack(Material.PAPER);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"About.");
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.BLUE+""+ChatColor.ITALIC+"You must use a soul to create a new");
	    lore.add(ChatColor.BLUE+""+ChatColor.ITALIC+"character. After dying, your soul will");
	    lore.add(ChatColor.BLUE+""+ChatColor.ITALIC+"begin to recharge back its essence.");
	    lore.add(ChatColor.BLUE+""+ChatColor.ITALIC+"It may take a while! In the meantime,");
	    lore.add(ChatColor.BLUE+""+ChatColor.ITALIC+"feel free to explore or work on");
	    lore.add(ChatColor.BLUE+""+ChatColor.ITALIC+"designing your new character! You can");
	    lore.add(ChatColor.BLUE+""+ChatColor.ITALIC+"pick and choose your next name and skin!");
	    im.setLore(lore);
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createInvBorder(){
		
		ItemStack i = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+"");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createSkinSelectInfo(){
		
		ItemStack i = new ItemStack(Material.PAPER);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.GOLD+"Info.");
	    List<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"Click on a skin to apply it to");
	    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"your character and try it out!");
	    im.setLore(lore);
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createSkinPreviousPage(){
		
		ItemStack i = new ItemStack(Material.SPRUCE_BUTTON);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.GREEN+"<- Previous");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	private static ItemStack createSkinNextPage(){
		
		ItemStack i = new ItemStack(Material.SPRUCE_BUTTON);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.GREEN+"Next ->");
	    i.setItemMeta(im);
		
	    return i;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// Public - Get Items /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////
	
	public static ItemStack getCreateCharacter() {
		return createCharacter;
	}
	
	public static ItemStack getSoulRecharging() {
		return soulRecharging;
	}
	
	public static ItemStack getSetSkin() {
		return setSkin;
	}
	
	public static ItemStack getSetName() {
		return setName;
	}
	
	public static ItemStack getAboutPaper() {
		return aboutPaper;
	}
	
	public static ItemStack getInvBorder() {
		return invBorder;
	}
	
	public static ItemStack getSkinSelectInfo() {
		return skinSelectInfo;
	}
	
	public static ItemStack getSkinPreviousPage() {
		return skinPreviousPage;
	}
	
	public static ItemStack getSkinNextPage() {
		return skinNextPage;
	}
	
	public static ItemStack getSouls(Player player) {
		
		PlayerCharacter pChar = CharacterManager.getCharacter(player);
		
		ItemStack i = new ItemStack(Material.HEART_OF_THE_SEA);
		
	    ItemMeta im = i.getItemMeta();
	    
	    im.addEnchant(Enchantment.OXYGEN, 1, false);
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
	    
		if(pChar.getSoulCount() > 0) {
			im.setDisplayName(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"Soul(s): "
					+ChatColor.GOLD+""+ChatColor.BOLD+pChar.getSoulCount());
		    List<String> lore = new ArrayList<String>();
		    lore.add(ChatColor.GREEN+""+ChatColor.ITALIC+"Click to buy additional Souls");
		    im.setLore(lore);
		} else {
			im.setDisplayName(ChatColor.BLUE+""+ChatColor.BOLD+"Hour(s) Left: "
					+pChar.getData().getSoul().getRechargeHours());
		    List<String> lore = new ArrayList<String>();
		    lore.add(ChatColor.GREEN+"After dying, your soul needs");
		    lore.add(ChatColor.GREEN+"time to recharge its essence.");
		    lore.add("");
		    lore.add(ChatColor.GREEN+"If you can't wait, you may also");
		    lore.add(ChatColor.GREEN+"click to buy a new soul.");
		    im.setLore(lore);
		}
		
	    i.setItemMeta(im);
	    
	    return i;
		
	}
	
}
