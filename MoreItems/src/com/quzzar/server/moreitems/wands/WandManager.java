package com.quzzar.server.moreitems.wands;

import java.util.ArrayList;

public class WandManager
{
  private static ArrayList<Wand> wands = new ArrayList<Wand>();
  
  public WandManager() {}
  
  public static void initialize() {
	  
	wands.add(new WandOfTeleportation());
    wands.add(new WandOfLesserFireballs());
    wands.add(new WandOfFireballs());
    wands.add(new WandOfGreaterFireballs());
    wands.add(new WandOfGrandFireballs());
    wands.add(new WandOfPolymorph());
    wands.add(new WandOfToxicFumes());
    wands.add(new WandOfDeadlyFumes());
    wands.add(new WandOfLightningBolts());
    wands.add(new WandOfGreaterLightningBolts());
    wands.add(new WandOfFeasting());
    wands.add(new WandOfHealing());
    
  }
  
  public static ArrayList<Wand> getWands()
  {
    return wands;
  }
}
