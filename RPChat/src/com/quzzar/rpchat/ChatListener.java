package com.quzzar.rpchat;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.quzzar.rpchat.radio.Radio;
import com.quzzar.rpchat.radio.RadioUtilities;

public class ChatListener implements Listener {
	
	@EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
		
		e.setCancelled(true);
		
		Player p = e.getPlayer();
		ArrayList<Radio> radios = RadioUtilities.getRadios(p);
		
        if(radios != null) {
        	// Send message over radio
        	
        	for(Player rp : Bukkit.getOnlinePlayers()) {
        		
        		ArrayList<Radio> rpRadios = RadioUtilities.getRadios(rp);
        		if(rpRadios!=null) {
        			
        			ArrayList<Radio> commonRadios = new ArrayList<Radio>(radios);
        			commonRadios.retainAll(rpRadios);
        			
        			if(!commonRadios.isEmpty()) {
        				
        				StringBuilder strBuilder = new StringBuilder();
        				
        				boolean first = true;
        				for(Radio commonR : commonRadios) {
        					if(first) {
        						strBuilder.append(commonR.getChannelPrefix());
        						first = false;
        					} else {
        						strBuilder.append(" §8& "+commonR.getChannelPrefix());
        					}
        				}
        				
        				Utility.sendPlayerMessage(strBuilder.toString(), p, rp, e.getMessage());
        				
        			}
        			
        		}
        		
        	}
        	
        } else {
        	// Send message over local chat
        	
        	RPChat.getLocalChatManager().getNormalProcessor()
        		.sendLocalMessage(p, e.getRecipients(), e.getMessage());
        	
        }
		
    }
	
}
