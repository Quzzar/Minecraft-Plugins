package com.quzzar.server.dominions.zones.commands.safezone;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionManager;
import com.quzzar.server.dominions.DominionsMain;
import com.quzzar.server.dominions.zones.ZoneListener;

public class SafezoneClaim {
	public static String commandLayout = "/safezone claim";

	public static void run(Player player, String[] args) {
		Chunk chunk = player.getLocation().getChunk();

		int claimType = determineClaim(chunk);

		if (claimType == 0 || claimType == 1) {

			DominionsMain.getZoneManager().addSafeChunk(chunk);
			player.sendMessage(ChatColor.GREEN + "SafeZone claimed at [" + chunk.getX()*16 + ", " + chunk.getZ()*16 + "]");
			ZoneListener.displayZone(player, chunk, null);
			
		} else if (claimType == 2) {
			player.sendMessage(ChatColor.RED + "This chunk is already a SafeZone!");
		} else if (claimType == 3) {
			
			DominionsMain.getZoneManager().removeNeutralChunk(chunk);
			DominionsMain.getZoneManager().addSafeChunk(chunk);
			player.sendMessage(ChatColor.GREEN + "SafeZone claimed from Neutral Zone at [" + chunk.getX()*16 + ", " + chunk.getZ()*16 + "]");
			ZoneListener.displayZone(player, chunk, null);
			
		}
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