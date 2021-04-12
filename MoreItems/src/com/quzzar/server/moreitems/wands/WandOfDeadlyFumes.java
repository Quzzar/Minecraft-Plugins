package com.quzzar.server.moreitems.wands;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.quzzar.server.moreitems.ItemUtils;
import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;
import com.quzzar.server.moreitems.spells.DeadlyFumes;

public class WandOfDeadlyFumes implements Wand
{
  public WandOfDeadlyFumes() {}
  
  public ItemStack getWand()
  {
    return ItemManager.getItemClone(ItemType.WAND_OF_DEADLY_FUMES);
  }
  

  public void rightClick(Player player, ItemStack wand)
  {
    DeadlyFumes.castSpell(player);
    
    if (ItemUtils.percentChance(6.5D)) {
      ItemUtils.breakItem(player, wand);
    }
  }
  
  public void shiftRightClick(Player player, ItemStack wand) {}
  
  public void leftClick(Player player, ItemStack wand) {}
  
  public void shiftLeftClick(Player player, ItemStack wand) {}
}
