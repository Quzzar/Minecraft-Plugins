package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.DominionsMain;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.misc.DiplomacyState;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class DominionsMap {
	
	public static String commandLayout = "/d map";
	
	private static int mapRadius = 5;
	
	private static char blockChar = '\u2587';
	private static char markerChar = '\u2587'; // 9FB1 // 2B24 /// 
	
	private static char endPoint = '\u2B24';
	
	private static TextComponent noneComponent = new TextComponent(ChatColor.WHITE+(""+blockChar));
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==1){
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
				
				drawMap(player, dominion);
				
			} else {
				sender.sendMessage(ChatColor.RED+"Only players can use this command!");
			}
			
		}else{
			failedCommand(sender);
		}
		
	}
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
	private static void drawMap(Player player, Dominion dominion) {
		
		Chunk baseChunk = player.getLocation().getChunk();
		
		player.sendMessage("§8"+endPoint+"§m-----§8[ §5§lMap §8]§m-----§8"+endPoint);
		
		for(int z = -mapRadius; z<=mapRadius; z++) {
			
			TextComponent mapLayer = new TextComponent("  ");
			
			for(int x = -mapRadius; x<=mapRadius; x++) {
				
				// Player's chunk
				if(x==0 && z==0) {
					mapLayer.addExtra(getMarkerComponent());
				} else {
					
					Chunk xzChunk = baseChunk.getWorld()
							.getChunkAt(baseChunk.getX()+x, baseChunk.getZ()+z);
					
					Dominion domAtXZ = DominionUtils.getDominionByChunk(xzChunk);
					
					if(domAtXZ!=null) {
						
						if(dominion!=null) {
							
							// Get diplomacy color
							ChatColor color = dominion.getDominionDiplomacy(domAtXZ).getPrimaryColor();
							mapLayer.addExtra(getTextComponent(color, domAtXZ));
							
						} else {
							// If the player has no dominion, make neutral
							mapLayer.addExtra(getTextComponent(DiplomacyState.Neutral.getPrimaryColor(), domAtXZ));
						}
						
					} else if (DominionsMain.getZoneManager().isSafeClaimed(xzChunk)){
						mapLayer.addExtra(getSafeComponent());
					} else if (DominionsMain.getZoneManager().isNeutralClaimed(xzChunk)){
						mapLayer.addExtra(getNeutralComponent());
					} else {
						// If not dominion at chunk, make none
						mapLayer.addExtra(noneComponent);
					}
					
				}
				
			}
			
			player.spigot().sendMessage(mapLayer);
			
		}
		
	}
	
	private static TextComponent getTextComponent(ChatColor color, Dominion domAtXZ) {
		
		TextComponent message = new TextComponent(color+(""+blockChar));
		message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/d show " + domAtXZ.getName()));
		message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				TextComponent.fromLegacyText(color+domAtXZ.getName()+"\n"+
				ChatColor.GREEN+"\u00A7o"+domAtXZ.getDescription())));
		
		return message;
	}
	
	private static TextComponent getMarkerComponent() {
		
		TextComponent marker = new TextComponent(ChatColor.BLACK+(""+markerChar));
		marker.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				TextComponent.fromLegacyText(ChatColor.GOLD+""+ChatColor.BOLD+"You")));

		return marker;
	}
	
	private static TextComponent getSafeComponent() {
		
		TextComponent marker = new TextComponent(ChatColor.GREEN+(""+blockChar));
		marker.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				TextComponent.fromLegacyText(ChatColor.GREEN+""+ChatColor.BOLD+"SafeZone")));

		return marker;
	}
	
	private static TextComponent getNeutralComponent() {
		
		TextComponent marker = new TextComponent(ChatColor.DARK_GRAY+(""+blockChar));
		marker.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				TextComponent.fromLegacyText(ChatColor.DARK_GRAY+""+ChatColor.BOLD+"Neutral Zone")));

		return marker;
	}
	
}
