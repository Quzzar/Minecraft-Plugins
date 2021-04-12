package com.quzzar.server.moreitems.wands;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.quzzar.server.moreitems.ItemUtils;
import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;
import com.quzzar.server.moreitems.spells.GreaterFireballs;

public class WandOfGreaterFireballs implements Wand
{
  public WandOfGreaterFireballs() {}
  
  public ItemStack getWand()
  {
    return ItemManager.getItemClone(ItemType.WAND_OF_GREATER_FIREBALLS);
  }
  

  public void rightClick(Player player, ItemStack wand)
  {
    GreaterFireballs.castSpell(player);
    
    if (ItemUtils.percentChance(2.0D)) {
      ItemUtils.breakItem(player, wand);
    }
  }
  
  public void shiftRightClick(Player player, ItemStack wand) {}
  
  public void leftClick(Player player, ItemStack wand) {}
  
  public void shiftLeftClick(Player player, ItemStack wand) {}
}
