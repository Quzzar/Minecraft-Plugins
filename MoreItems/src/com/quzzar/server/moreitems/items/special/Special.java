package com.quzzar.server.moreitems.items.special;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public abstract interface Special
{
  public abstract void onInteract(PlayerInteractEvent paramPlayerInteractEvent, ItemStack paramItemStack);
  
  public abstract void onPlayerDamageEntity(EntityDamageByEntityEvent paramEntityDamageByEntityEvent, Player paramPlayer, ItemStack paramItemStack);
  
  public abstract void onItemConsume(PlayerItemConsumeEvent paramPlayerItemConsumeEvent, ItemStack paramItemStack);
}
