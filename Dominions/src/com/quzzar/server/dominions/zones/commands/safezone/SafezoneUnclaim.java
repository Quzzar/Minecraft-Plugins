package com.quzzar.server.dominions.zones.commands.safezone;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.DominionsMain;


public class SafezoneUnclaim
{
  public static String commandLayout = "/safezone unclaim";

  
  public static void run(Player player, String[] args) {
    Chunk chunk = player.getLocation().getChunk();
    
    if (DominionsMain.getZoneManager().isSafeClaimed(chunk)) {
      
      DominionsMain.getZoneManager().removeSafeChunk(chunk);
      player.sendMessage(ChatColor.GREEN + "SafeZone unclaimed.");
    } else {
      
      player.sendMessage(ChatColor.RED + "This chunk is not a SafeZone!");
    } 
  }
}
