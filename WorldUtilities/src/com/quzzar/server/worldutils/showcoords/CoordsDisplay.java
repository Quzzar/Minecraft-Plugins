package com.quzzar.server.worldutils.showcoords;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.connorlinfoot.bountifulapi.BountifulAPI;
import com.quzzar.server.worldlayers.WorldLayers;
import com.quzzar.server.worldutils.WorldUtilities;

public class CoordsDisplay {
	
	public static void displayClock() {
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(WorldUtilities.getInstance(), new Runnable() {
			
			public void run() {
				
				for(Player p : ShowCoordsManager.getShowings()) {
					if(p.isOnline()) {
						BountifulAPI.sendActionBar(p, buildCoords(p));
					}
				}
				
			}
		
		}, 10, 10); // Every 1/2 second
		
	}
	
	private static String day_north = ChatColor.DARK_GREEN+""+ChatColor.BOLD+"N";
	private static String day_south = ChatColor.DARK_RED+""+ChatColor.BOLD+"S";
	private static String day_east = ChatColor.GOLD+""+ChatColor.BOLD+"E";
	private static String day_west = ChatColor.BLUE+""+ChatColor.BOLD+"W";
	private static String day_coordColors = ChatColor.BLACK+""+ChatColor.BOLD+"";
	
	private static String night_north = ChatColor.GREEN+""+ChatColor.BOLD+"N";
	private static String night_south = ChatColor.RED+""+ChatColor.BOLD+"S";
	private static String night_east = ChatColor.YELLOW+""+ChatColor.BOLD+"E";
	private static String night_west = ChatColor.BLUE+""+ChatColor.BOLD+"W";
	private static String night_coordColors = ChatColor.DARK_AQUA+""+ChatColor.BOLD+"";
	
	
	private static String firstPar = ChatColor.DARK_GRAY+" (";
	private static String lastPar = ChatColor.DARK_GRAY+") ";
	private static String comma = ChatColor.DARK_GRAY+", ";
	
	private static String buildCoords(Player p) {
		
		int x = p.getLocation().getBlockX();
		int y = getY(p.getLocation());
		int z = p.getLocation().getBlockZ();
		
		StringBuilder str = new StringBuilder();
		
		if(p.getLocation().getBlock().getLightLevel() > 7) {
			
			String direction = getDirectionDay(p);
			
			str.append(direction);
			str.append(firstPar);
			str.append(day_coordColors+x);
			str.append(comma);
			str.append(day_coordColors+y);
			str.append(comma);
			str.append(day_coordColors+z);
			str.append(lastPar);
			str.append(direction);
			
		} else {
			
			String direction = getDirectionNight(p);
			
			str.append(direction);
			str.append(firstPar);
			str.append(night_coordColors+x);
			str.append(comma);
			str.append(night_coordColors+y);
			str.append(comma);
			str.append(night_coordColors+z);
			str.append(lastPar);
			str.append(direction);
			
		}
		
		return str.toString();
		
	}
	
	private static String getDirectionDay(Player p) {
	    float yaw = p.getLocation().getYaw();
	    if (yaw < 0) {
	        yaw += 360;
	    }
	    if (yaw >= 315 || yaw < 45) {
	        return day_south;
	    } else if (yaw < 135) {
	        return day_west;
	    } else if (yaw < 225) {
	        return day_north;
	    } else if (yaw < 315) {
	        return day_east;
	    }
	    return day_north;
    }
	
	private static String getDirectionNight(Player p) {
	    float yaw = p.getLocation().getYaw();
	    if (yaw < 0) {
	        yaw += 360;
	    }
	    if (yaw >= 315 || yaw < 45) {
	        return night_south;
	    } else if (yaw < 135) {
	        return night_west;
	    } else if (yaw < 225) {
	        return night_north;
	    } else if (yaw < 315) {
	        return night_east;
	    }
	    return night_north;
    }
	
	private static int getY(Location loc) {
		
		if(loc.getWorld().equals(WorldUtilities.getNetherWorld())) {
			return loc.getBlockY()-WorldLayers.NETHER_TP;
		} else if(loc.getWorld().equals(WorldUtilities.getMainWorld())) {
			return loc.getBlockY();
		} else if(loc.getWorld().equals(WorldUtilities.getAetherWorld())) {
			return WorldLayers.OVERWORLD_TP_TOP+loc.getBlockY();
		} else if(loc.getWorld().equals(WorldUtilities.getEndWorld())) {
			return WorldLayers.OVERWORLD_TP_TOP+WorldLayers.AETHER_TP_TOP+loc.getBlockY();
		}
		
		return -1;
		
	}
	
}
