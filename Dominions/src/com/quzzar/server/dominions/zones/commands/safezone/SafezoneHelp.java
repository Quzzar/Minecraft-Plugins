package com.quzzar.server.dominions.zones.commands.safezone;

import java.util.LinkedHashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.quzzar.server.dominions.centeredtext.CenterText;


public class SafezoneHelp
{
  private static LinkedHashMap<String, String> helpMenu = new LinkedHashMap<String, String>();
  
  private static ChatColor border = ChatColor.DARK_GRAY;

  
  public static void setHelpMenu() {
    helpMenu.put(SafezoneClaim.commandLayout, "Claims a chunk as a SafeZone.");
    helpMenu.put(SafezoneUnclaim.commandLayout, "Unclaims a SafeZone chunk.");
    helpMenu.put(SafezoneUnclaimAll.commandLayout, "Unclaims all SafeZone chunks.");
  }



  
  public static void run(CommandSender sender, String[] args) { displayHelpMenu(sender, helpMenu); }




  
  private static void displayHelpMenu(CommandSender sender, LinkedHashMap<String, String> helpMenu) {
    CenterText.sendCenteredMessage(sender, 
        border + "§m[---------------" + 
        border + "[ §a§lSafeZone §2Help " + 
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