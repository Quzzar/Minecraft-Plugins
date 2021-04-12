package com.quzzar.server.moreitems.items.special;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import com.quzzar.server.moreitems.heads.HeadUtil;
import com.quzzar.server.moreitems.heads.TextureDatabase;

public class PortableCraftingTable implements Special
{
  public PortableCraftingTable() {}
  
  public void onInteract(PlayerInteractEvent e, ItemStack item)
  {
    if ((e.getAction().equals(Action.RIGHT_CLICK_AIR)) || (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
    {
      Player player = e.getPlayer();
      
      if ((HeadUtil.checkMechanical(item)) && 
        (HeadUtil.getTexture(item).equals(TextureDatabase.CRAFTING_TABLE))) {
        player.openWorkbench(player.getLocation(), true);
        return;
      }
    }
  }
  
  public void onPlayerDamageEntity(EntityDamageByEntityEvent e, Player player, ItemStack item) {}
  
  public void onItemConsume(PlayerItemConsumeEvent e, ItemStack item) {}
}
