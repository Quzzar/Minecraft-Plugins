package com.quzzar.im.structures.loading.asbuilding;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.EntityDataTag;
import com.quzzar.im.machines.Machine;
import com.quzzar.im.machines.types.Pipe;
import com.quzzar.im.structures.Structure;
import com.quzzar.im.structures.data.StructDataTag;
import com.quzzar.im.utils.FaceUtils;

public class Loading {

	public static ArrayList<ArmorStand> createStructure(Structure structure) {

		Machine machine = structure.getMachine();
		ArrayList<ASPlacement> placements = machine.getASPlacements();
		ArrayList<ItemStack> itemCollection = (structure.isRunning()) ? machine.getPoweredTexCollection()
				: machine.getTexCollection();
		BlockFace front = structure.getFacingDirection();

		ArrayList<ArmorStand> armorStands = new ArrayList<ArmorStand>();

		int index = 0;
		for (ASPlacement place : placements) {

			Location loc = structure.getCenterLocation().toLocation().clone();

			ASPlacement rotatedPlace = rotatePlacement(place, front);

			loc.add(rotatedPlace.getFirstVal(), rotatedPlace.getHeightVal(), rotatedPlace.getSecondVal());

			ItemStack item;
			if (itemCollection.size() > placements.indexOf(place)) {
				item = itemCollection.get(placements.indexOf(place));
			} else {
				item = itemCollection.get(itemCollection.size() - 1);
			}

			ArmorStand as;

			if (place.isSmall()) {
				as = createSmallArmorStand(loc, item);
			} else {
				as = createArmorStand(loc, item);
			}

			CraftMeta.entitySet(as, EntityDataTag.MACHINE_STRUCTURE, structure.getID());
			if (machine.getIndexInvTagMap().get(index) != null) {
				CraftMeta.entitySet(as, EntityDataTag.MACHINE_INV_TAG, machine.getIndexInvTagMap().get(index));
			}

			if (place.getDirection().equals(ASDirection.FORWARD)) {
				as.setHeadPose(new EulerAngle(0, FaceUtils.getEulerDegree(front), 0));
			} else if (place.getDirection().equals(ASDirection.OPPOSITE)) {
				as.setHeadPose(new EulerAngle(0, FaceUtils.getEulerDegree(front.getOppositeFace()), 0));
			} else if (place.getDirection().equals(ASDirection.LEFT)) {
				as.setHeadPose(new EulerAngle(0, FaceUtils.getEulerDegree(FaceUtils.getFaceToLeft(front)), 0));
			} else if (place.getDirection().equals(ASDirection.RIGHT)) {
				as.setHeadPose(new EulerAngle(0, FaceUtils.getEulerDegree(FaceUtils.getFaceToRight(front)), 0));
			}

			armorStands.add(as);
			index++;
		}

		return armorStands;
	}

	public static ArrayList<ArmorStand> createPipe(Structure structure) {

		Pipe pipe = (Pipe) structure.getMachine();
		ItemStack firstPipeItem = pipe.firstPipe;
		ItemStack pipeItem = pipe.getItem();

		World world = structure.getCenterLocation().toLocation().getWorld();

		Location startingLoc = new Location(world, structure.getMetaInt(StructDataTag.STARTING_LOC_X),
				structure.getMetaInt(StructDataTag.STARTING_LOC_Y), structure.getMetaInt(StructDataTag.STARTING_LOC_Z));
		startingLoc.add(0.5, 0, 0.5);

		Location endingLoc = new Location(world, structure.getMetaInt(StructDataTag.ENDING_LOC_X),
				structure.getMetaInt(StructDataTag.ENDING_LOC_Y), structure.getMetaInt(StructDataTag.ENDING_LOC_Z));
		endingLoc.add(0.5, 0, 0.5);

		Vector vec = startingLoc.toVector().subtract(endingLoc.toVector());

		ArrayList<ArmorStand> armorStands = new ArrayList<ArmorStand>();

		Vector v = vec.clone().multiply(0.19 * (1 / vec.length())); // Approx distance between each small Armor Stand

		for (double n = 0; n < vec.length() * 10; n++) {

			if (v.clone().length() * n * 2.1 >= vec.length()) { // When to cut off loop (before reaching max). Gives a
																// fairly good approximation.
				return armorStands; // 2.6
			}

			ArmorStand as;
			if (n == 0) {
				as = createSmallArmorStand(endingLoc.add(v.clone().add(v.clone())), firstPipeItem);
			} else {
				as = createSmallArmorStand(endingLoc.add(v.clone().add(v.clone())), pipeItem);
			}
			
			as.setHeadPose(convertVectorToEulerAngle(vec));
			
			CraftMeta.entitySet(as, EntityDataTag.MACHINE_STRUCTURE, structure.getID());
			
			armorStands.add(as);

		}

		return armorStands;

	}

	public static void updateHeads(ArrayList<ArmorStand> armorStands, Structure structure) {

		Machine machine = structure.getMachine();
		ArrayList<ItemStack> itemCollection = (structure.isRunning()) ? machine.getPoweredTexCollection()
				: machine.getTexCollection();

		for (int i = 0; i < armorStands.size(); i++) {

			ItemStack item;
			if (itemCollection.size() > i) {
				item = itemCollection.get(i);
			} else {
				item = itemCollection.get(itemCollection.size() - 1);
			}

			ArmorStand as = armorStands.get(i);
			as.setHelmet(item);

		}

	}

	private static ArmorStand createArmorStand(Location l, ItemStack i) {
		ArmorStand as = (ArmorStand) l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
		as.setHelmet(i);
		as.setVisible(false);
		as.setGravity(false);
		as.setCollidable(false);
		as.setInvulnerable(true);
		return as;
	}

	private static ArmorStand createSmallArmorStand(Location l, ItemStack i) {
		ArmorStand as = (ArmorStand) l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
		as.setHelmet(i);
		as.setVisible(false);
		as.setGravity(false);
		as.setCollidable(false);
		as.setInvulnerable(true);
		as.setSmall(true);
		return as;
	}

	private static ASPlacement rotatePlacement(ASPlacement place, BlockFace front) {

		double angle = FaceUtils.getAngle(front);

		double newFirstVal = place.getFirstVal() * Math.cos(angle) - place.getSecondVal() * Math.sin(angle);
		double newSecondVal = place.getSecondVal() * Math.cos(angle) + place.getFirstVal() * Math.sin(angle);

		return new ASPlacement(newFirstVal, newSecondVal, place.getHeightVal(), place.getDirection(), place.isSmall());
	}

	private static EulerAngle convertVectorToEulerAngle(Vector vec) {
		
		double x = vec.getX();
		double y = vec.getY();
		double z = vec.getZ();
		
		double xz = Math.sqrt(x*x + z*z);
		
		double eulX;
		if(x < 0) {
			if(y == 0) {
				eulX = Math.PI*0.5;
			} else {
				eulX = Math.atan(xz/y)+Math.PI;
			}
		} else {
			eulX = Math.atan(y/xz)+Math.PI*0.5;
		}
		
		double eulY;
		if(x == 0) {
			if(z > 0) {
				eulY = Math.PI;
			} else {
				eulY = 0;
			}
		} else {
			eulY = Math.atan(z/x)+Math.PI*0.5;
		}
		
		return new EulerAngle(eulX, eulY, 0);
		
	}

}
