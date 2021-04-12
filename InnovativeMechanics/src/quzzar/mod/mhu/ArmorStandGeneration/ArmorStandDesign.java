package quzzar.mod.mhu.ArmorStandGeneration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import quzzar.mod.ArmorStandData;
import quzzar.mod.Utility;
import quzzar.mod.Utilities.MHU_Utilities;

public class ArmorStandDesign {
	
	
	private static double getAngle(BlockFace face){
		
		
		int num = Math.abs(face.compareTo(BlockFace.NORTH));
		
		if(num==0) {
			return 0;
		}else if (num==1) {
			return Math.PI*0.5;
		}else if (num==2) {
			return Math.PI;
		}else if (num==3) {
			return Math.PI*1.5;
		}else {
			return 0;
		}
	}
	
	private static ASPlacement rotatePlacement(ASPlacement place, BlockFace front) {
		
		double angle = getAngle(front);
		
		double newFirstVal = place.getFirstVal()*Math.cos(angle) - place.getSecondVal()*Math.sin(angle);
		double newSecondVal = place.getSecondVal()*Math.cos(angle) + place.getFirstVal()*Math.sin(angle);
		
		return new ASPlacement(newFirstVal, newSecondVal, place.getHeightVal(), place.getDirection(), place.isSmall());
	}
	
	
	
	public static ArrayList<ArmorStand> createStructure(Location centerLoc, ArrayList<ASPlacement> placements,
				ArrayList<ItemStack> itemCollection, BlockFace front) {
		
		
		ArrayList<ArmorStand> armorStands = new ArrayList<ArmorStand>();
		
		
		for(ASPlacement place : placements) {
			
			Location loc = centerLoc.clone();
			
			ASPlacement rotatedPlace = rotatePlacement(place, front);
			
			loc.add(rotatedPlace.getFirstVal(), rotatedPlace.getHeightVal(), rotatedPlace.getSecondVal());
			
			ItemStack item;
			
			if(itemCollection.size()>placements.indexOf(place)) {
				item = itemCollection.get(placements.indexOf(place));
			}else {
				item = itemCollection.get(itemCollection.size()-1);
			}
			
			ArmorStand as;
			
			if(place.isSmall()) {
				as = Utility.createSmallArmorStand(loc, item);
			}else {
				as = Utility.createArmorStand(loc, item);
			}
			
			if(place.getDirection().equals(ASDirection.FORWARD)) {
				as.setHeadPose(new EulerAngle(0, Utility.getEulerDegree(front), 0));
			}else if(place.getDirection().equals(ASDirection.OPPOSITE)) {
				as.setHeadPose(new EulerAngle(0, Utility.getEulerDegree(front.getOppositeFace()), 0));
			}else if(place.getDirection().equals(ASDirection.LEFT)) {
				as.setHeadPose(new EulerAngle(0, Utility.getEulerDegree(Utility.getFaceToLeft(front)), 0));
			}else if(place.getDirection().equals(ASDirection.RIGHT)) {
				as.setHeadPose(new EulerAngle(0, Utility.getEulerDegree(Utility.getFaceToRight(front)), 0));
			}
			
			armorStands.add(as);
			
		}
		
		return armorStands;
	}
	
	public static ArrayList<ArmorStand> confirmASStructure(Location centerLoc, ArrayList<ASPlacement> placements, BlockFace front, UUID uuid, int repeatCount){
		
		ArrayList<ArmorStand> armorStands = new ArrayList<ArmorStand>();
		
		for(ASPlacement place : placements) {
			
			Location loc = centerLoc.clone();
			
			ASPlacement rotatedPlace = rotatePlacement(place, front);
			
			loc.add(rotatedPlace.getFirstVal(), rotatedPlace.getHeightVal(), rotatedPlace.getSecondVal());
			
			search:
			for(Entity entity : getEntitiesAroundPoint(loc, repeatCount+1)) {
				
				if(entity!=null) {
					
					if(entity instanceof ArmorStand) {
						
						ArmorStand as = (ArmorStand) entity;
						
						if(!armorStands.contains(as)) {
							
							if(MHU_Utilities.getSerializedID(uuid)==ArmorStandData.getID(as)) {
								
								armorStands.add(as);
								break search;
								
							}
							
						}
						
					} else if(entity.getType().equals(EntityType.ARMOR_STAND)) {
						
						Utility.tellConsole(ChatColor.RED+"Found entity of type ArmorStand but not instanceof");
						
					}
					
				}
				
			}
			
		}
		
		
		if(armorStands.size()==placements.size()) {
			return armorStands;
		} else {
			
			if(repeatCount<5) {
				
				Utility.tellConsole(ChatColor.YELLOW+"Could not locate Machine Structure, trying again...");
				
				repeatCount++;
				
				try {
					
					return confirmASStructure(centerLoc, placements, front, uuid, repeatCount);
					
				} catch (NullPointerException e) {
					
					return null;
					
				}
				
			}else {
				
				Utility.tellConsole(ChatColor.RED+"Failed to locate Machine Structure ["+armorStands.size()+"/"+placements.size()+"]! ("
						+ centerLoc.getWorld().getName()+", "+centerLoc.getX()+", "+centerLoc.getY()+", "+centerLoc.getZ()+") ("
						+ front.name() +") <"+ uuid + "> "+ " First Placement: ["+placements.get(0).getFirstVal()+", "+
						placements.get(0).getSecondVal() + ", "+ placements.get(0).getHeightVal() + ", " + placements.get(0).getDirection().name()+ "]");
				
				
				for(ArmorStand as : armorStands){
					as.remove();
				}
				
				
				///
				int count = 0;
				
				Utility.tellConsole(ChatColor.RED+"In an attempt to fix corrupted machine, removing nearby entities.");
				
				for(Entity entity : getEntitiesAroundPoint(centerLoc, 1)) {
					
					Utility.tellConsole(ChatColor.RED+"Removing entity of type '"+entity.getType()+"'.");
					
					entity.remove();
					count++;
					
					/*
					if(entity instanceof ArmorStand) {
						ArmorStand as = (ArmorStand) entity;
						
						if(!ArmorStandData.isNatural(as)) {
							as.remove();
							count++;
						}
						
					}
					*/
				}
				
				for(Block block : Utility.getNearbyBlocks(centerLoc, 1)) {
					if(block.getType().equals(Material.BARRIER)) {
						block.setType(Material.AIR);
						count++;
					}
				}
				
				Utility.tellConsole(ChatColor.RED+"Successfully removed '"+count+"' nearby broken machine parts.");
				///
				
				
				return null;
				
			}
			
		}
	}
	
	public static ArrayList<ArmorStand> confirmPipeStructure(Location centerLoc, UUID uuid, int var1, int repeatCount){
		
		ArrayList<ArmorStand> armorStands = new ArrayList<ArmorStand>();
		
		for(Entity entity : getEntitiesAroundPoint(centerLoc, repeatCount+10)) {
			
			if(entity instanceof ArmorStand) {
				
				ArmorStand as = (ArmorStand) entity;
				
				if(MHU_Utilities.getSerializedID(uuid)==ArmorStandData.getID(as)) {
					
					armorStands.add(as);
					
				}
				
			}
			
		}
		
		if(armorStands.size()==var1) {
			return armorStands;
		}else {
			
			if(repeatCount<5) {
				
				Utility.tellConsole(ChatColor.YELLOW+"Could not locate Pipe Structure, trying again...");
				
				repeatCount++;
				
				return confirmPipeStructure(centerLoc, uuid, var1, repeatCount);
				
			}else {
				
				Utility.tellConsole(ChatColor.RED+"Failed to locate Pipe Structure ["+armorStands.size()+"/"+var1+"]!");
				
				for(ArmorStand as : armorStands){
					as.remove();
				}
				
				return null;
				
			}
			
		}
	}
	
	
	
	public static List<Entity> getEntitiesAroundPoint(Location location, double radius) {
	    List<Entity> entities = new ArrayList<Entity>();
	    World world = location.getWorld();
	    
	    if(world!=null) {

		    // To find chunks we use chunk coordinates (not block coordinates!)
		    int smallX = (int) Math.floor((location.getX() - radius) / 16.0D);
		    int bigX = (int) Math.floor((location.getX() + radius) / 16.0D);
		    int smallZ = (int) Math.floor((location.getZ() - radius) / 16.0D);
		    int bigZ = (int) Math.floor((location.getZ() + radius) / 16.0D);

		    for (int x = smallX; x <= bigX; x++) {
		        for (int z = smallZ; z <= bigZ; z++) {
		        	world.loadChunk(x, z, true);
		            if (world.isChunkLoaded(x, z)) {
		            	entities.addAll(Arrays.asList(world.getChunkAt(x, z).getEntities())); // Add all entities from this chunk to the list
		            } else {
		            	Utility.tellConsole(ChatColor.RED+"Chunk failed to load!");
		            }
		        }
		    }

		    // Remove the entities that are within the box above but not actually in the sphere we defined with the radius and location
		    // This code below could probably be replaced in Java 8 with a stream -> filter
		    Iterator<Entity> entityIterator = entities.iterator(); // Create an iterator so we can loop through the list while removing entries
		    while (entityIterator.hasNext()) {
		        if (entityIterator.next().getLocation().distanceSquared(location) > radius * radius) { // If the entity is outside of the sphere...
		            entityIterator.remove(); // Remove it
		        }
		    }
	    }
	    
	    return entities;
	    
	}
	
}
