package com.quzzar.server.dominions.zones.commands.neutralzone;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.DominionsMain;


public class NeutralZoneUnclaimAll
{
  public static String commandLayout = "/neutralzone unclaimall";

  
  public static void run(Player player, String[] args) {
    DominionsMain.getZoneManager().removeAllNeutralChunks();
    player.sendMessage(ChatColor.GREEN + "All Neutral Zone chunks unclaimed.");
  }
}
