package com.quzzar.server.dominions.zones.commands.neutralzone;

import java.util.LinkedHashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.quzzar.server.dominions.centeredtext.CenterText;


public class NeutralZoneHelp
{
  private static LinkedHashMap<String, String> helpMenu = new LinkedHashMap<String, String>();
  
  private static ChatColor border = ChatColor.DARK_GRAY;

  
  public static void setHelpMenu() {
    helpMenu.put(NeutralZoneClaim.commandLayout, "Claims a chunk as a Neutral Zone.");
    helpMenu.put(NeutralZoneUnclaim.commandLayout, "Unclaims a Neutral Zone chunk.");
    helpMenu.put(NeutralZoneUnclaimAll.commandLayout, "Unclaims all Neutral Zone chunks.");
  }



  
  public static void run(CommandSender sender, String[] args) { displayHelpMenu(sender, helpMenu); }




  
  private static void displayHelpMenu(CommandSender sender, LinkedHashMap<String, String> helpMenu) {
    CenterText.sendCenteredMessage(sender, 
        border + "§m[---------------" + 
        border + "[ §7§lNeutral Zone §2Help " + 
        border + "]§m---------------]");
    
    displayCommands(sender, helpMenu);
    
    CenterText.sendCenteredMessage(sender, 
        border + "§m[------------------------------------------------]");
  }



  
  private static void displayCommands(CommandSender sender, LinkedHashMap<String, String> helpMenu) {
    for (String comLayout : helpMenu.keySet()) {
      CenterText.sendCenteredMessage(sender, ChatColor.BLUE + "" + ChatColor.BOLD + comLayout);
      CenterText.sendCenteredMessage(sender, ChatColor.GREEN + (String)helpMenu.get(comLayout));
    } 
  }
}