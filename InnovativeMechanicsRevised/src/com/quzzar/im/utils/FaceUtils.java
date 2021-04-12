package com.quzzar.im.utils;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;

public class FaceUtils {

	public static double getEulerDegree(BlockFace bf) {

		if (bf.equals(BlockFace.NORTH)) {
			return 0;
		} else if (bf.equals(BlockFace.SOUTH)) {
			return Math.PI;
		} else if (bf.equals(BlockFace.EAST)) {
			return Math.PI * 0.5;
		} else if (bf.equals(BlockFace.WEST)) {
			return Math.PI * 1.5;
		} else {
			return 0;
		}

	}

	public static BlockFace getFaceToRight(BlockFace face) {
		switch (face) {
		case NORTH:
			return BlockFace.EAST;
		case EAST:
			return BlockFace.SOUTH;
		case SOUTH:
			return BlockFace.WEST;
		case WEST:
			return BlockFace.NORTH;
		default:
			return BlockFace.NORTH;
		}
	}

	public static BlockFace getFaceToLeft(BlockFace face) {
		switch (face) {
		case NORTH:
			return BlockFace.WEST;
		case WEST:
			return BlockFace.SOUTH;
		case SOUTH:
			return BlockFace.EAST;
		case EAST:
			return BlockFace.NORTH;
		default:
			return BlockFace.NORTH;
		}
	}
	
	public static double getAngle(BlockFace face) {

		int num = Math.abs(face.compareTo(BlockFace.NORTH));

		if (num == 0) {
			return 0;
		} else if (num == 1) {
			return Math.PI * 0.5;
		} else if (num == 2) {
			return Math.PI;
		} else if (num == 3) {
			return Math.PI * 1.5;
		} else {
			return 0;
		}
	}
	
	public static BlockFace getFacing(Block b){
		if(b.getBlockData() instanceof Directional) {
			return ((Directional) b.getBlockData()).getFacing();
		} else {
			return BlockFace.NORTH;
		}
	}
	
	public static BlockFace getFacing(Player p){
		float yaw = p.getLocation().getYaw();
	    if (yaw < 0) {
	        yaw += 360;
	    }
	    if (yaw >= 315 || yaw < 45) {
	        return BlockFace.SOUTH;
	    } else if (yaw < 135) {
	        return BlockFace.WEST;
	    } else if (yaw < 225) {
	        return BlockFace.NORTH;
	    } else if (yaw < 315) {
	        return BlockFace.EAST;
	    }
	    return BlockFace.NORTH;
	}
	
}
