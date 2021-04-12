package com.quzzar.server.moreitems.wands;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract interface Wand
{
  public abstract ItemStack getWand();
  
  public abstract void rightClick(Player paramPlayer, ItemStack paramItemStack);
  
  public abstract void shiftRightClick(Player paramPlayer, ItemStack paramItemStack);
  
  public abstract void leftClick(Player paramPlayer, ItemStack paramItemStack);
  
  public abstract void shiftLeftClick(Player paramPlayer, ItemStack paramItemStack);
}
