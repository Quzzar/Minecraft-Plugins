package quzzar.mod;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.EulerAngle;

import com.mojang.authlib.GameProfile;
import com.quzzar.im.versioncontrol.VersionControl;
import com.quzzar.im.versioncontrol.VersionType;
import com.quzzar.im.versioncontrol.global.material.GMaterial;

import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.blocks.HeadUnitBlock;
import quzzar.mod.inventories.mechInv.MechInvManager;
import quzzar.mod.maps.TextureItemStackMapManager;

public class Utility {
	
	
	public static boolean checkMechanical(Block b){
		
		if(b!=null) {
			if(VersionControl.getVersion().equals(VersionType.V_1_13)) {
				
				if(VersionControl.areSameMaterial(b, GMaterial.PLAYER_HEAD_BLOCK)
						|| VersionControl.areSameMaterial(b, GMaterial.PLAYER_HEAD_BLOCK_WALL)){
			        if (b.getState() instanceof Skull){
			            if(((Skull) b.getState()).hasOwner()){
			            	if(TextureDatabase.getTexture(getUUID(b)) != null){
			            		return true;
			            	}
			            }
			        }
				}
				
			} else {
				if(VersionControl.areSameMaterialOnly(b, GMaterial.PLAYER_HEAD_BLOCK)){
			        if (b.getState() instanceof Skull){
			            if(((Skull) b.getState()).hasOwner()){
			            	if(TextureDatabase.getTexture(getUUID(b)) != null){
			            		return true;
			            	}
			            }
			        }
				}
				
			}
		}
		return false;
	}
	
	
	public static boolean checkMechanical(ItemStack i){
		
		if(i!=null) {
			if(VersionControl.areSameMaterial(i, GMaterial.PLAYER_HEAD_ITEM)){
				if(i.hasItemMeta()){
					if(TextureDatabase.getTexture(getUUID(i)) != null){
	            		return true;
	            	}
				}
			}
		}
		return false;
	}
	
	
	public static boolean isNextToMechanical(Block b){
		
		List<BlockFace> blockFaces = Arrays.asList(BlockFace.NORTH,BlockFace.EAST,BlockFace.SOUTH,BlockFace.WEST);
		
		for(BlockFace bf : blockFaces){
			if(checkMechanical(b.getRelative(bf))){
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isNearToLargeMachine(Block b){
		
		List<BlockFace> blockFaces = Arrays.asList(BlockFace.NORTH,BlockFace.EAST,BlockFace.SOUTH,BlockFace.WEST,
				BlockFace.DOWN, BlockFace.NORTH_EAST, BlockFace.NORTH_WEST, BlockFace.SELF, BlockFace.SOUTH_EAST,
				BlockFace.SOUTH_WEST, BlockFace.SOUTH_WEST, BlockFace.UP);
		
		for(BlockFace bf : blockFaces){
			if(b.getRelative(bf).getType().equals(Main.fillerType)){
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<Block> getAdjacentMechanicals(Block b){
		
		ArrayList<Block> blocks = new ArrayList<Block>();
		
		List<BlockFace> blockFaces = Arrays.asList(BlockFace.NORTH,BlockFace.EAST,BlockFace.SOUTH,BlockFace.WEST);
		
		for(BlockFace bf : blockFaces){
			if(checkMechanical(b.getRelative(bf))){
				blocks.add(b.getRelative(bf));
			}
		}
		
		if(blocks.isEmpty()){
			blocks = null;
		}
		
		return blocks;
	}
	
	public static ArrayList<Block> getAdjacentChests(Block b){
		
		ArrayList<Block> blocks = new ArrayList<Block>();
		
		List<BlockFace> blockFaces = Arrays.asList(BlockFace.NORTH,BlockFace.EAST,BlockFace.SOUTH,BlockFace.WEST,BlockFace.UP);
		
		for(BlockFace bf : blockFaces){
			if(b.getRelative(bf).getType().equals(Material.CHEST)||b.getRelative(bf).getType().equals(Material.TRAPPED_CHEST)){
				blocks.add(b.getRelative(bf));
			}
		}
		
		if(blocks.isEmpty()){
			blocks = null;
		}
		
		return blocks;
	}
	
	public static Inventory getCustomChestInv(Block b){
		
		List<BlockFace> blockFaces = Arrays.asList(BlockFace.NORTH,BlockFace.EAST,BlockFace.SOUTH,BlockFace.WEST,BlockFace.UP);
		
		for(BlockFace bf : blockFaces){
			
			if(checkMechanical(b.getRelative(bf))){
				for(HeadUnitBlock hub : Main.HUBList){
					if(hub.getBlock().equals(b.getRelative(bf))){
						HeadUnit headunit = hub.getHeadUnit();
						if(headunit.getType().equals(BlockType.STORAGE)){
							return MechInvManager.getMechInventories(headunit).get(0).getInventory();
						}
					}
				}
				
				
			}
		}
		
		return null;
	}
	
	
	public static BlockFace getFront(Skull b){
		if(VersionControl.getVersion().equals(VersionType.V_1_13)) {
			return b.getRotation().getOppositeFace();
		} else {
			return b.getRotation();
		}
	}
	
	public static void snapToGrid(Block b){
		
		if(VersionControl.getVersion().equals(VersionType.V_1_13)) {
			if(VersionControl.areSameMaterial(b, GMaterial.PLAYER_HEAD_BLOCK_WALL)) {
				return;
			}
		}
		
		Skull skull = (Skull) b.getState();
		
		switch(skull.getRotation()){
			case WEST_NORTH_WEST: skull.setRotation(BlockFace.WEST); break;
			case WEST_SOUTH_WEST: skull.setRotation(BlockFace.WEST); break;
			case EAST_NORTH_EAST: skull.setRotation(BlockFace.EAST); break;
			case EAST_SOUTH_EAST: skull.setRotation(BlockFace.EAST); break;
			case NORTH_NORTH_EAST: skull.setRotation(BlockFace.NORTH); break;
			case NORTH_NORTH_WEST: skull.setRotation(BlockFace.NORTH); break;
			case SOUTH_SOUTH_EAST: skull.setRotation(BlockFace.SOUTH); break;
			case SOUTH_SOUTH_WEST: skull.setRotation(BlockFace.SOUTH); break;
			case NORTH_EAST: skull.setRotation(BlockFace.NORTH); break;
			case NORTH_WEST: skull.setRotation(BlockFace.NORTH); break;
			case SOUTH_EAST: skull.setRotation(BlockFace.SOUTH); break;
			case SOUTH_WEST: skull.setRotation(BlockFace.SOUTH); break;
			default: break;
		}
		
		skull.update();
		
	}
	
	public static BlockFace getFaceToRight(BlockFace face) {
		switch(face){
			case NORTH: return BlockFace.EAST;
			case EAST: return BlockFace.SOUTH;
			case SOUTH: return BlockFace.WEST;
			case WEST: return BlockFace.NORTH;
			default: return BlockFace.NORTH;
		}
	}
	
	public static BlockFace getFaceToLeft(BlockFace face) {
		switch(face){
			case NORTH: return BlockFace.WEST;
			case WEST: return BlockFace.SOUTH;
			case SOUTH: return BlockFace.EAST;
			case EAST: return BlockFace.NORTH;
			default: return BlockFace.NORTH;
		}
	}
	
	
	public static UUID getUUID(Block b){
		return ((Skull) b.getState()).getOwningPlayer().getUniqueId();
	}
	
	public static UUID getUUID(ItemStack i){
		SkullMeta headMeta = (SkullMeta) i.getItemMeta();
		
		Field profileField = null;
	    try {
	        profileField = headMeta.getClass().getDeclaredField("profile");
	        profileField.setAccessible(true);
	        
	        GameProfile gp = (GameProfile) profileField.get(headMeta);
	        
	        return gp.getId();
	        
	    } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
	        e1.printStackTrace();
	    }
	    
	    return null;
	}
	
	public static HeadUnit makeHeadUnit(Block b){
		return new HeadUnit(getUUID(b));
	}
	
	public static HeadUnit makeHeadUnit(ItemStack i){
		return new HeadUnit(getUUID(i));
	}
	
	
	public static TextureDatabase getTexture(Block b){
		return TextureDatabase.getTexture(getUUID(b));
	}
	
	public static TextureDatabase getTexture(ItemStack i){
		return TextureDatabase.getTexture(getUUID(i));
	}
	
	public static HeadUnitBlock getHeadUnitBlock(Block b){
		for(HeadUnitBlock hub : Main.HUBList) {
			if(hub.getBlock().equals(b)) {
				return hub;
			}
		}
		return null;
	}
	
	
	
	public static double getEulerDegree(BlockFace bf) {
        
		if(bf.equals(BlockFace.NORTH)){
			return 0;
		}else if (bf.equals(BlockFace.SOUTH)){
			return Math.PI;
		}else if (bf.equals(BlockFace.EAST)){
			return Math.PI*0.5;
		}else if (bf.equals(BlockFace.WEST)){
			return Math.PI*1.5;
		}else{
			return 0;
		}
         
    }
	
	
	public static boolean inventoryFull(Inventory inv, ItemStack item){
		
		boolean full = false;
		
		if(inv.firstEmpty()==-1){
			for(ItemStack i : inv.getContents()){
				if(i!=null){
					if(i.isSimilar(item)&&i.getAmount()<i.getMaxStackSize()){
						return false;
					}else{
						full = true;
					}
				}
			}
		}
		
		return full;
	}
	
	
	public static void tellConsole(String message){
		Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN+"["+ChatColor.DARK_AQUA+"Innovative Mechanics"+ChatColor.DARK_GREEN+"]"+ChatColor.GREEN+" "+message);
	}
	
	public static void tellSender(CommandSender sender, String message){
		sender.sendMessage(ChatColor.DARK_GREEN+"["+ChatColor.DARK_AQUA+"Innovative Mechanics"+ChatColor.DARK_GREEN+"]"+ChatColor.GREEN+" "+message);
	}
	
	
	public static ArmorStand createArmorStand(Location l, ItemStack i){
		ArmorStand as = l.getWorld().spawn(l, ArmorStand.class);
        as.setHelmet(i);
        as.setVisible(false);
        as.setGravity(false);
        as.setCollidable(false);
        as.setInvulnerable(true);
        return as;
	}
	
	public static ArmorStand createSmallArmorStand(Location l, ItemStack i){
		ArmorStand as = l.getWorld().spawn(l, ArmorStand.class);
        as.setHelmet(i);
        as.setVisible(false);
        as.setGravity(false);
        as.setCollidable(false);
        as.setInvulnerable(true);
        as.setSmall(true);
        return as;
	}
	
	public static ArmorStand createArmorStandHoldingItem(Location l, ItemStack i, EulerAngle angle){
		ArmorStand as = l.getWorld().spawn(l, ArmorStand.class);
        as.setItemInHand(i);
        as.setRightArmPose(angle);
        as.setVisible(false);
        as.setGravity(false);
        as.setCollidable(false);
        as.setInvulnerable(true);
        return as;
	}
	
	public static boolean addItemToInventory(Inventory inv, ItemStack i, Location loc){
		if(inventoryFull(inv, i)){
			loc.getWorld().dropItem(loc, i);
			return false;
		}else{
			inv.addItem(i);
			return true;
		}
	}
	
	
	public static String getSafeItemName(ItemStack i){
		String itemName;
		if(TextureItemStackMapManager.getTexture(i)!=null){
			itemName = TextureItemStackMapManager.getTexture(i).name();
		}else{
			itemName = i.getItemMeta().getDisplayName();
		}
		return ChatColor.stripColor(itemName);
	}
	
    public static List<Block> getNearbyBlocks(Location location, int radius) {
        List<Block> blocks = new ArrayList<Block>();
        for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for(int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                   blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }
	
}
