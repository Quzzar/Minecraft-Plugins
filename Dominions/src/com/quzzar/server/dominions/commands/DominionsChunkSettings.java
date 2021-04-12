package com.quzzar.server.dominions.commands;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.Utility;
import com.quzzar.server.dominions.settings.SettingType;
import com.quzzar.server.dominions.settings.chunk.ChunkSettingsUtils;
import com.quzzar.server.dominions.settings.chunk.IntroChunkSettings;

public class DominionsChunkSettings {
	
	public static String commandLayout = "/d chunksettings";
	
	public static void run(CommandSender sender, String[] args){
		
		if(args.length==1){
			
			if(sender instanceof Player) {
				
				Player player = (Player) sender;
				
				Dominion dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
				
				if(dominion!=null) {
					
					// Does the player have the rank to view/edit chunk settings? Check settings.
					if(DominionUtils.hasPermission(dominion, player.getUniqueId(), SettingType.CHUNK_SETTINGS)) {
						
						Chunk chunk = player.getLocation().getChunk();
						
						if(!ChunkSettingsUtils.confirmChunkIsValid(chunk, dominion)) {
							sender.sendMessage(ChatColor.RED+"You can only use this command in a chunk that your dominion owns!");
							return;
						}
						
						IntroChunkSettings.open(player, player.getChunk(), dominion);
						
					} else {
						sender.sendMessage(ChatColor.RED+"You don't have the rank to use this command!");
					}
					
				} else {
					sender.sendMessage(ChatColor.RED+"You have no Dominion!");
				}
				
			} else {
				sender.sendMessage(ChatColor.RED+"Only players can use this command!");
			}
			
		} else {
			failedCommand(sender);
		}
		
	}
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
}
