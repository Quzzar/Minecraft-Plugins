package quzzar.mod.pluginhelp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import quzzar.mod.ItemsList;
import quzzar.mod.Utility;

public class HelpListener implements Listener{
	
	@EventHandler
    public void onInventoryDrag(InventoryDragEvent e){
		
		if(e.getInventory()!=null) {
			
			if(PluginHelpManager.helpInventories.contains(e.getInventory())){
				e.setCancelled(true);
			}
			
		}
		
	}
	
	@EventHandler
    public void onInventoryClick(InventoryClickEvent e){
		
		if(e.getInventory()!=null) {
			
			if(PluginHelpManager.helpInventories.contains(e.getInventory())){
				e.setCancelled(true);
			}
			
			if(e.getCurrentItem()!=null){
				
				if(PluginHelpManager.getHomeInventory().equals(e.getInventory())){
					for(HelpPage page : PluginHelpManager.allPages){
						if(e.getCurrentItem().isSimilar(page.getDisplayItem())){
							HelpUtil.displayInventory(e.getWhoClicked(), page.getInventory());
							return;
						}
					}
				}
				
				for(HelpPage page : PluginHelpManager.allPages){
					if(page.getInventory().equals(e.getInventory())){
						
						for(ItemViewer iViewer : page.itemList){
							if(e.getCurrentItem().isSimilar(iViewer.getDisplayItem())){
								HelpUtil.displayInventory(e.getWhoClicked(), iViewer.getInventory());
								return;
							}else if(e.getCurrentItem().isSimilar(page.getBackItem())){
								HelpUtil.displayInventory(e.getWhoClicked(), page.getPreviousInventory());
								return;
							}
						}
						
					}
				}
				
				for(ItemViewer iViewer : PluginHelpManager.allItems){
					if(iViewer.getInventory().equals(e.getInventory())){
						
						if(e.getCurrentItem().isSimilar(iViewer.getBackItem())){
							HelpUtil.displayInventory(e.getWhoClicked(), iViewer.getPreviousInventory());
						}else if (e.getCurrentItem().isSimilar(iViewer.getDisplayItem())){
							if(e.getWhoClicked().hasPermission("innomech.commands.give")){
								
								String itemName = Utility.getSafeItemName(iViewer.getItem());
								
								
								if(e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
									Bukkit.getServer().dispatchCommand(e.getWhoClicked(), "im give "+e.getWhoClicked().getName()+" "+itemName+
											" "+iViewer.getItem().getMaxStackSize());
								}else {
									Bukkit.getServer().dispatchCommand(e.getWhoClicked(), "im give "+e.getWhoClicked().getName()+" "+itemName);
								}
								
								return;
							}
						}else if (e.getCurrentItem().isSimilar(iViewer.getRecipeItem())){
							HelpUtil.displayInventory(e.getWhoClicked(), iViewer.getRecipeViewer().getInventory());
							return;
						}
						
					}
				}
				
			}
			
			for(RecipePage recViewer : PluginHelpManager.allRecipes){
				if(recViewer.getInventory().equals(e.getInventory())){
					
					HelpUtil.displayInventory(e.getWhoClicked(), recViewer.getPreviousInventory());
					return;
					
				}
			}
			
		}
		
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		
		Player p = e.getPlayer();
		
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR)||e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			
			ItemStack i = p.getInventory().getItemInMainHand();
			
			boolean opened = false;
			
			if(i != null){
				if(i.hasItemMeta()){
					
					if(i.getItemMeta().hasLore()){
						
						if(i.getItemMeta().getLore().equals(ItemsList.Book_of_Innovations(1).getItemMeta().getLore())){
							
							p.openInventory(PluginHelpManager.getHomeInventory());
							e.setCancelled(true);
							opened = true;
							
						}
						
					}
					
				}
			}
			if(!opened){
				ItemStack i_o = p.getInventory().getItemInOffHand();
				if(i_o != null){
					if(i_o.hasItemMeta()){
						
						if(i_o.getItemMeta().hasLore()){
							
							if(i_o.getItemMeta().getLore().equals(ItemsList.Book_of_Innovations(1).getItemMeta().getLore())){
								
								p.openInventory(PluginHelpManager.getHomeInventory());
								e.setCancelled(true);
								
							}
							
						}
						
					}
				}
			}
			
		}
	}
	
	
	
	
}
