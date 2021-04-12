package com.quzzar.rpchat.rankprefix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.bukkit.entity.Player;

import com.quzzar.rpchat.RPChat;

public class RankPrefixManager {

	private ArrayList<RankPrefix> rankprefixes;
	
	public RankPrefixManager() {
		
		rankprefixes = new ArrayList<RankPrefix>();
		
		Set<String> rSet = RPChat.getMainConfig().getConfigurationSection("RankPrefix").getKeys(false);
		
		List<String> ranks = new ArrayList<String>(rSet);
		Collections.sort(ranks, Collections.reverseOrder());
		
		for(String rankName : ranks) {
			
			String prefix = RPChat.getMainConfig().getString("RankPrefix."+rankName+".Prefix");
			
			rankprefixes.add(new RankPrefix(rankName, prefix));
			
		}
		
	}
	
	public String getRankPrefix(Player player) {
		for(RankPrefix rankPrefix : rankprefixes) {
			if(player.hasPermission(rankPrefix.getPermission())) {
				return rankPrefix.getPrefix();
			}
		}
		return "";
	}
	
}
