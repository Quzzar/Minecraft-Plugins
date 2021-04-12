package com.quzzar.server.dominions.zones.commands.neutralzone;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.DominionsMain;


public class NeutralZoneUnclaim
{
  public static String commandLayout = "/neutralzone unclaim";

  
  public static void run(Player player, String[] args) {
    Chunk chunk = player.getLocation().getChunk();
    
    if (DominionsMain.getZoneManager().isNeutralClaimed(chunk)) {
      
      DominionsMain.getZoneManager().removeNeutralChunk(chunk);
      player.sendMessage(ChatColor.GREEN + "Neutral Zone unclaimed.");
    } else {
      
      player.sendMessage(ChatColor.RED + "This chunk is not a Neutral Zone!");
    } 
  }
}
