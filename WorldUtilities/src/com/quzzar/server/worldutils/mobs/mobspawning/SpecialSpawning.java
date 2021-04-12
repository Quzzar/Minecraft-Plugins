package com.quzzar.server.worldutils.mobs.mobspawning;

import java.util.SplittableRandom;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.entity.Slime;

public class SpecialSpawning {

	private static SplittableRandom splitRand = new SplittableRandom();
	
	public static boolean determineSpecialSpawn(Location loc, Spawn spawn) {
		if(spawn.getTag().equals("")) {return false;}
		if(spawn.getTag().equals("Empty")) {return true;}
		
		if(spawn.getTag().equals("Aether_Phantom")) {
			spawnAetherPhantom(loc, spawn);
			return true;
		}
		
		if(spawn.getTag().equals("Enderman_Legion")) {
			spawnEndermanLegion(loc, spawn);
			return true;
		}
		
		if(spawn.getTag().equals("Vex_Legion")) {
			spawnVexLegion(loc, spawn);
			return true;
		}
		
		if(spawn.getTag().equals("Trap_Skeleton_Horse")) {
			spawnTrapSkeletonHorse(loc, spawn);
			return true;
		}
		
		if(spawn.getTag().equals("Silverfish_Swarm")) {
			spawnSilverfishSwarm(loc, spawn);
			return true;
		}
		
		if(spawn.getTag().equals("Phantom_Rider")) {
			spawnPhantomRider(loc, spawn);
			return true;
		}
		
		if(spawn.getTag().equals("Zombie_Legion")) {
			spawnZombieLegion(loc, spawn);
			return true;
		}
		
		if(spawn.getTag().equals("Skeleton_Legion")) {
			spawnSkeletonLegion(loc, spawn);
			return true;
		}
		
		if(spawn.getTag().equals("Illusioners")) {
			spawnIllusioners(loc, spawn);
			return true;
		}
		
		if(spawn.getTag().equals("Pilliger_Legion_2")) {
			spawnPilligerLegionTwo(loc, spawn);
			return true;
		}
		
		if(spawn.getTag().equals("Pilliger_Legion")) {
			spawnPilligerLegion(loc, spawn);
			return true;
		}
		
		if(spawn.getTag().equals("Unit_Phantom")) {
			spawnUnitPhantom(loc, spawn);
			return true;
		}
		
		if(spawn.getTag().equals("Mega_Slime")) {
			spawnMegaSlime(loc, spawn);
			return true;
		}
		
		return false;
		
	}
	
	private static void spawnEndermanLegion(Location loc, Spawn spawn) {
		
		int size = splitRand.nextInt(2, 9);
		int spawnRadius = 2;
		
		for(int i = 0; i <= size; i++) {
			
			Location nearbyLoc = loc.add(
					splitRand.nextDouble(-1*spawnRadius, spawnRadius), 0, 
					splitRand.nextDouble(-1*spawnRadius, spawnRadius));
			
			loc.getWorld().spawnEntity(nearbyLoc, EntityType.ENDERMAN);
		}
		
		
	}
	
	private static void spawnVexLegion(Location loc, Spawn spawn) {
		
		int size = splitRand.nextInt(2, 9);
		int spawnRadius = 2;
		
		for(int i = 0; i <= size; i++) {
			
			Location nearbyLoc = loc.add(
					splitRand.nextDouble(-1*spawnRadius, spawnRadius), 0, 
					splitRand.nextDouble(-1*spawnRadius, spawnRadius));
			
			loc.getWorld().spawnEntity(nearbyLoc, EntityType.VEX);
		}
		
		
	}
	
	private static void spawnAetherPhantom(Location loc, Spawn spawn) {
		
		int size = splitRand.nextInt(1, 5);
		
		Location newLoc = loc.clone();
		newLoc.setY(200);
		
		if(newLoc.getBlock().getLightLevel() <= spawn.getMaxLightLevel()) {
			Phantom phantom = (Phantom) loc.getWorld().spawnEntity(newLoc, EntityType.PHANTOM);
			phantom.setSize(size);
		}
		
	}
	
	private static void spawnTrapSkeletonHorse(Location loc, Spawn spawn) {
		
		SkeletonHorse horse = (SkeletonHorse) loc.getWorld().spawnEntity(loc, EntityType.SKELETON_HORSE);
		horse.setTrap(true);
		
	}
	
	private static void spawnSilverfishSwarm(Location loc, Spawn spawn) {
		
		int size = splitRand.nextInt(2, 5);
		int spawnRadius = 2;
		
		for(int i = 0; i <= size; i++) {
			
			Location nearbyLoc = loc.add(
					splitRand.nextDouble(-1*spawnRadius, spawnRadius), 0, 
					splitRand.nextDouble(-1*spawnRadius, spawnRadius));
			
			loc.getWorld().spawnEntity(nearbyLoc, EntityType.SILVERFISH);
		}
		
	}
	
	private static void spawnPhantomRider(Location loc, Spawn spawn) {
		
		Location nearbyLoc = loc.clone();
		nearbyLoc.add(0, 10, 0);
		
		Phantom phantom = (Phantom) nearbyLoc.getWorld().spawnEntity(nearbyLoc, EntityType.PHANTOM);
		phantom.setSize(12);
		LivingEntity rider = (LivingEntity) nearbyLoc.getWorld().spawnEntity(nearbyLoc, EntityType.EVOKER);
		
		phantom.addPassenger(rider);
		
	}
	
	private static void spawnZombieLegion(Location loc, Spawn spawn) {
		
		int size1 = splitRand.nextInt(1, 3);
		int size2 = splitRand.nextInt(0, 2);
		int spawnRadius = 5;
		
		for(int i = 0; i <= size1; i++) {
			
			Location nearbyLoc = loc.clone();
			nearbyLoc.add(
					splitRand.nextDouble(-1*spawnRadius, spawnRadius), 0, 
					splitRand.nextDouble(-1*spawnRadius, spawnRadius));
			
			loc.getWorld().spawnEntity(nearbyLoc, EntityType.ZOMBIE_VILLAGER);
		}
		for(int i = 0; i <= size2; i++) {
			
			Location nearbyLoc = loc.clone();
			nearbyLoc.add(
					splitRand.nextDouble(-1*spawnRadius, spawnRadius), 0, 
					splitRand.nextDouble(-1*spawnRadius, spawnRadius));
			
			loc.getWorld().spawnEntity(nearbyLoc, EntityType.ZOMBIE);
		}
		
		loc.getWorld().spawnEntity(loc, EntityType.WITCH);
		
	}
	
	private static void spawnSkeletonLegion(Location loc, Spawn spawn) {
		
		int size1 = splitRand.nextInt(1, 3);
		int size2 = splitRand.nextInt(0, 2);
		int spawnRadius = 5;
		
		for(int i = 0; i <= size1; i++) {
			
			Location nearbyLoc = loc.clone();
			nearbyLoc.add(
					splitRand.nextDouble(-1*spawnRadius, spawnRadius), 0, 
					splitRand.nextDouble(-1*spawnRadius, spawnRadius));
			
			nearbyLoc.getWorld().spawnEntity(nearbyLoc, EntityType.SKELETON);
			
		}
		
		for(int i = 0; i <= size2; i++) {
			
			Location nearbyLoc = loc.clone();
			nearbyLoc.add(
					splitRand.nextDouble(-1*spawnRadius, spawnRadius), 0, 
					splitRand.nextDouble(-1*spawnRadius, spawnRadius));
			
			LivingEntity sHorse = (LivingEntity) nearbyLoc.getWorld().spawnEntity(nearbyLoc, EntityType.SKELETON_HORSE);
			LivingEntity sRider = (LivingEntity) nearbyLoc.getWorld().spawnEntity(nearbyLoc, EntityType.STRAY);
			
			sHorse.addPassenger(sRider);
			
		}
		
	}
	
	private static void spawnIllusioners(Location loc, Spawn spawn) {
		
		int size1 = splitRand.nextInt(2, 4);
		int spawnRadius = 5;
		
		for(int i = 0; i <= size1; i++) {
			
			Location nearbyLoc = loc.clone();
			nearbyLoc.add(
					splitRand.nextDouble(-1*spawnRadius, spawnRadius), 3, 
					splitRand.nextDouble(-1*spawnRadius, spawnRadius));
			
			Phantom phantom = (Phantom) nearbyLoc.getWorld().spawnEntity(nearbyLoc, EntityType.PHANTOM);
			phantom.setSize(1);
			
		}
		
		loc.getWorld().spawnEntity(loc, EntityType.ILLUSIONER);
		
	}
	
	private static void spawnPilligerLegion(Location loc, Spawn spawn) {
		
		int size1 = splitRand.nextInt(0, 3);
		int spawnRadius = 5;
		
		for(int i = 0; i <= size1; i++) {
			
			Location nearbyLoc = loc.clone();
			nearbyLoc.add(
					splitRand.nextDouble(-1*spawnRadius, spawnRadius), 0, 
					splitRand.nextDouble(-1*spawnRadius, spawnRadius));
			
			nearbyLoc.getWorld().spawnEntity(nearbyLoc, EntityType.VINDICATOR);
			
		}
		
		LivingEntity ravager = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.RAVAGER);
		LivingEntity pRider = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.EVOKER);
		ravager.addPassenger(pRider);
		
	}
	
	private static void spawnPilligerLegionTwo(Location loc, Spawn spawn) {
		
		int size1 = splitRand.nextInt(1, 2);
		int size2 = splitRand.nextInt(0, 2);
		int spawnRadius = 5;
		
		for(int i = 0; i <= size1; i++) {
			
			Location nearbyLoc = loc.clone();
			nearbyLoc.add(
					splitRand.nextDouble(-1*spawnRadius, spawnRadius), 0, 
					splitRand.nextDouble(-1*spawnRadius, spawnRadius));
			
			nearbyLoc.getWorld().spawnEntity(nearbyLoc, EntityType.VINDICATOR);
			
		}
		
		for(int i = 0; i <= size2; i++) {
			
			Location nearbyLoc = loc.clone();
			nearbyLoc.add(
					splitRand.nextDouble(-1*spawnRadius, spawnRadius), 0, 
					splitRand.nextDouble(-1*spawnRadius, spawnRadius));
			
			nearbyLoc.getWorld().spawnEntity(nearbyLoc, EntityType.PILLAGER);
			
		}
		
		LivingEntity ravager = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.RAVAGER);
		LivingEntity pRider = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.PILLAGER);
		ravager.addPassenger(pRider);
		
	}
	
	private static void spawnUnitPhantom(Location loc, Spawn spawn) {
		
		int size = splitRand.nextInt(25, 60);
		
		Location newLoc = loc.clone();
		newLoc.setY(160);
		
		Phantom unitPhantom = (Phantom) loc.getWorld().spawnEntity(newLoc, EntityType.PHANTOM);
		
		unitPhantom.setSize(size);
		
	}
	
	private static void spawnMegaSlime(Location loc, Spawn spawn) {
		
		int size = splitRand.nextInt(15, 30);
		
		Slime megaSlime = (Slime) loc.getWorld().spawnEntity(loc, EntityType.SLIME);
		
		megaSlime.setWander(true);
		megaSlime.setSize(size);
		
	}
	
}
