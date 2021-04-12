package com.quzzar.server.souls.limbo.creation.skin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.InvDataTag;
import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;
import com.quzzar.server.souls.character.packets.display.PlayerDisplayProfile;
import com.quzzar.server.souls.limbo.LimboInvBuilder;
import com.quzzar.server.souls.limbo.LimboItemCreator;

public class SkinSelectListener implements Listener {

	@EventHandler
    public void onInventoryClick(InventoryClickEvent e){
		
		if(e.getInventory() == null) {
			return;
		}
		
		Inventory inv = e.getInventory();
		if(!CraftMeta.invHasData(inv, InvDataTag.CHARACTER_CREATION_SKIN_SELECT)) {
			return;
		}
		
		e.setCancelled(true);
		
		if(!(e.getWhoClicked() instanceof Player) 
				|| e.getClickedInventory() != e.getInventory()) {
			return;
		}
		
		ItemStack itemClicked = e.getCurrentItem();
		Player player = (Player) e.getWhoClicked();
		
		if(itemClicked.getType() == Material.PLAYER_HEAD) {
			
			SkullMeta sMeta = (SkullMeta) itemClicked.getItemMeta();
			
			PlayerCharacter pChar = CharacterManager.getCharacter(player);
			pChar.getData().setSkin(sMeta.getPlayerProfile().getId());
			PlayerDisplayProfile.apply(pChar, true);
			
			player.closeInventory();
			player.sendMessage(ChatColor.GREEN+"Your new skin has been set.");
			
			return;
		}
		
		if(itemClicked.equals(LimboItemCreator.getSkinPreviousPage())) {
			int pageNum = (int) CraftMeta.invGet(inv, InvDataTag.CHARACTER_CREATION_SKIN_SELECT);
			player.closeInventory();
			player.openInventory(LimboInvBuilder.getSkinSelectionInvs().get(pageNum-1));
			return;
		}
		
		if(itemClicked.equals(LimboItemCreator.getSkinNextPage())) {
			int pageNum = (int) CraftMeta.invGet(inv, InvDataTag.CHARACTER_CREATION_SKIN_SELECT);
			player.closeInventory();
			player.openInventory(LimboInvBuilder.getSkinSelectionInvs().get(pageNum+1));
			return;
		}
		
	}
	
}
