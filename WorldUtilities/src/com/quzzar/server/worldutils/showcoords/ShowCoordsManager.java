package com.quzzar.server.worldutils.showcoords;

import java.util.HashSet;
import java.util.Set;
import org.bukkit.entity.Player;

import com.connorlinfoot.bountifulapi.BountifulAPI;

public class ShowCoordsManager {

	private static Set<Player> showings = new HashSet<Player>();
	
	public static Set<Player> getShowings() {
		return showings;
	}
	
	public static boolean isShowing(Player player) {
		return showings.contains(player);
	}
	
	public static void setShowing(Player player, boolean showing) {
		if(showing) {
			showings.add(player);
		} else {
			showings.remove(player);
			BountifulAPI.sendActionBar(player, "");
		}
	}
	
	public static void toggleShowing(Player player) {
		setShowing(player, !isShowing(player));
	}
	
}
