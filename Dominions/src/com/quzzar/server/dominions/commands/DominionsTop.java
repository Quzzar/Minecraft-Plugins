package com.quzzar.server.dominions.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionManager;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.misc.DiplomacyState;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class DominionsTop {
	
	public static String commandLayout = "/d top";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==1){
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
				

				/////// Get list of Dominions, sort it by wealth.
				
				ArrayList<Dominion> domList = new ArrayList<Dominion>();
				
				domList.addAll(DominionManager.getDominions());
				
				Collections.sort(domList, new Comparator<Dominion>() {
			        @Override
			        public int compare(Dominion d1, Dominion d2) {
			            return Integer.compare(d1.getCalculatedWealth(), d2.getCalculatedWealth());
			        }
			    });
				// Get it in order of greatest to least
				Collections.reverse(domList);
				
				/////////
				
				sender.sendMessage("§8§m-------§8[ §5§lTop Dominions §8]§m-------");
				
				for(int i=0; i<10; i++) {
					int num = i+1;
					if(domList.size()>i) {
						
						DiplomacyState state = DiplomacyState.Neutral;
						
						Dominion otherDom = domList.get(i);
						
						if(dominion!=null) {
							state = dominion.getDominionDiplomacy(otherDom);
						}
						
						TextComponent message = new TextComponent(
								"§6§l"+num+"§8. "+state.getPrimaryColor()+otherDom.getName());
						message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/d show " + otherDom.getName()));
						message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
								TextComponent.fromLegacyText("§5$"+otherDom.getCalculatedWealth())));
						
						sender.spigot().sendMessage(message);
						
					} else {
						// If not enough Dominions, just leave blank
						sender.sendMessage("§6§l"+num+"§8. ");
					}
				}
				
				sender.sendMessage("§8§m------------------------------");
				
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
	
}
