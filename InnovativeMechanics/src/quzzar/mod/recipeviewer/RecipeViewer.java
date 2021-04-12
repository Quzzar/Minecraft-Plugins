package quzzar.mod.recipeviewer;

import java.util.ArrayList;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.im.versioncontrol.VersionControl;
import com.quzzar.im.versioncontrol.global.material.GMaterial;

import quzzar.mod.ItemsList;
import quzzar.mod.LangHandler;
import quzzar.mod.Utility;
import quzzar.mod.pluginhelp.HelpUtil;

public class RecipeViewer implements Listener{
	
	
	public static Inventory openingInv = Bukkit.createInventory(null, InventoryType.HOPPER, 
			ChatColor.stripColor(ItemsList.Recipe_Viewer(1).getItemMeta().getDisplayName()));
	private static ItemStack emptyPanel = VersionControl.getItemStack(GMaterial.WHITE_STAINED_GLASS_PANE, 1);
	private static ItemStack placePanel = VersionControl.getItemStack(GMaterial.LIGHT_GRAY_STAINED_GLASS_PANE, 1);
	
	public static ArrayList<Inventory> recipeViewInvs = new ArrayList<Inventory>();
	
	{
		
		ItemMeta im = emptyPanel.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+"");
	    emptyPanel.setItemMeta(im);
	    
	    ItemMeta imP = placePanel.getItemMeta();
	    imP.setDisplayName(ChatColor.GREEN+LangHandler.applyMessage("Recipe_Viewer_Place", "Place Item Here"));
	    placePanel.setItemMeta(imP);
	    
		
	    openingInv.setItem(0, emptyPanel);
	    openingInv.setItem(1, emptyPanel);
	    openingInv.setItem(2, placePanel);
	    openingInv.setItem(3, emptyPanel);
	    openingInv.setItem(4, emptyPanel);
		
	}
	
	
	
	
	
	@EventHandler
    public void onInventoryDrag(InventoryDragEvent e){
		
		if(e.getInventory()!=null) {
			
			if(e.getInventory().equals(openingInv)||recipeViewInvs.contains(e.getInventory())){
				e.setCancelled(true);
			}
			
		}
		
	}
	
	@EventHandler
    public void onInventoryClick(InventoryClickEvent e){
		
		if(e.getInventory()!=null) {
			
			if(e.getInventory().equals(openingInv)||recipeViewInvs.contains(e.getInventory())) {
				if(e.getRawSlot() < e.getInventory().getSize()){
					e.setCancelled(true);
				}
			}
			
			for(Inventory inv : recipeViewInvs){
				if(inv.equals(e.getInventory())){
					
					e.getWhoClicked().closeInventory();
					
					if(e.getCurrentItem()!=null) {
						
						Inventory invOpen = createRecipeViewInv(e.getCurrentItem());
						
						e.getWhoClicked().openInventory(invOpen);
						
					}
					
					inv.clear();
					return;
					
				}
			}
			
			if(e.getInventory().equals(openingInv) && e.getSlot()==2 && !e.getCursor().getType().equals(Material.AIR)) {
				
				Inventory inv = createRecipeViewInv(e.getCursor());
				
				ItemStack itemPut = e.getCursor().clone();
				
				e.getCursor().setAmount(0);
				
				Utility.addItemToInventory(e.getWhoClicked().getInventory(), itemPut, e.getWhoClicked().getLocation());
				
				e.getWhoClicked().openInventory(inv);
				
			}
			
		}
		
		
	}	
	
	private static Inventory createRecipeViewInv(ItemStack item) {
		
		Inventory inv;
		if(item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			inv = Bukkit.createInventory(null, InventoryType.DROPPER, ChatColor.stripColor(item.getItemMeta().getDisplayName()));
		} else {
			String name = item.getType().toString();
			name = name.replaceAll("_", " "); name = WordUtils.capitalizeFully(name);
			inv = Bukkit.createInventory(null, InventoryType.DROPPER, name);
		}
		recipeViewInvs.add(inv);
		HelpUtil.displayRecipe(inv, item);
		
		return inv;
		
	}
	
}
