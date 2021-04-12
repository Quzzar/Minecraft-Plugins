package com.quzzar.server.dominions.zones.commands.safezone;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.DominionsMain;


public class SafezoneUnclaimAll
{
  public static String commandLayout = "/safezone unclaimall";

  
  public static void run(Player player, String[] args) {
    DominionsMain.getZoneManager().removeAllSafeChunks();
    player.sendMessage(ChatColor.GREEN + "All SafeZone chunks unclaimed.");
  }
}
