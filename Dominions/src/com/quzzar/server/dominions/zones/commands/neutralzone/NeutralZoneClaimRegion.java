package com.quzzar.server.dominions.zones.commands.neutralzone;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionManager;
import com.quzzar.server.dominions.DominionsMain;

public class NeutralZoneClaimRegion {
	public static String commandLayout = "/neutralzone claimregion x1 z1 x2 z2";

	public static void run(Player player, String[] args) {
		
		if(args.length != 5) {
			player.sendMessage(ChatColor.RED+commandLayout);
			return;
		}
		
		if(!player.hasPermission("dominions.claimregion")) {
			player.sendMessage(ChatColor.RED + "You don't have permission to use this comamnd!");
			return;
		}
		
		int x1 = Integer.parseInt(args[1])/16;
		int z1 = Integer.parseInt(args[2])/16;
		int x2 = Integer.parseInt(args[3])/16;
		int z2 = Integer.parseInt(args[4])/16;
		
		if(x2 < x1 || z2 < z1) {
			player.sendMessage(ChatColor.RED + "x1 and z1 must be less than x2 and z2!");
			return;
		}
		
		int count = 0;
		for(int x = x1; x < x2; x++) {
		    for(int z = z1; z < z2; z++) {
		        Chunk chunk = player.getWorld().getChunkAt(x, z);
		        
		        int claimType = determineClaim(chunk);

				if (claimType == 0 || claimType == 1) {
					DominionsMain.getZoneManager().addNeutralChunk(chunk);
					count++;
				}
				
				if(claimType == 2) {
					DominionsMain.getZoneManager().removeSafeChunk(chunk);
					DominionsMain.getZoneManager().addNeutralChunk(chunk);
					count++;
				}
		        
		    }
		}

		player.sendMessage(ChatColor.GREEN + "Claimed "+count+" chunks as Neutral Zone.");
		
	}

	private static int determineClaim(Chunk chunk) {
		if (DominionsMain.getZoneManager().isSafeClaimed(chunk)) {
			return 2;
		}
		if (DominionsMain.getZoneManager().isNeutralClaimed(chunk)) {
			return 3;
		}

		for (Dominion dominion : DominionManager.getDominions()) {
			if (dominion.hasClaimed(chunk)) {
				dominion.removeChunk(chunk);
				return 1;
			}
		}

		return 0;
	}
}
