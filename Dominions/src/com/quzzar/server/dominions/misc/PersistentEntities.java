package com.quzzar.server.dominions.misc;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import com.quzzar.server.dominions.Dominion;

public class PersistentEntities {

	public static void spawnEntities(Dominion dominion) {
		
		HashMap<EntityType, Entity> tempMap = new HashMap<EntityType, Entity>();
		
		for(Entry<EntityType,UUID> entry : dominion.getPersistentEntitiies().entrySet()) {
			Location hLoc = dominion.getHomeLocation();
			if(hLoc != null && hLoc.getWorld() != null) {
				
				Entity entity = hLoc.getWorld().spawnEntity(hLoc, entry.getKey());
				entry.setValue(entity.getUniqueId());
				
				tempMap.put(entry.getKey(), entity);
				
			}
		}
		
		// Finalize GE Trader
		LivingEntity trader = (LivingEntity) tempMap.get(EntityType.WANDERING_TRADER);
		LivingEntity trader_llama = (LivingEntity) tempMap.get(EntityType.TRADER_LLAMA);
		if(trader != null && trader_llama != null) {
			
			trader_llama.addPassenger(trader);
			
			/*
			Bukkit.getScheduler().runTaskLater(DominionsMain.instance, new Runnable() {
				@Override
				public void run() {
					trader_llama.setLeashHolder(trader);
				}
			}, 1L);*/
		}
		
	}
	
	public static void deleteEntities(Dominion dominion) {
		
		for(Entry<EntityType,UUID> entry : dominion.getPersistentEntitiies().entrySet()) {
			Entity entity = Bukkit.getEntity(entry.getValue());
			if(entity != null) {
				entity.remove();
			}
		}
		
	}
	
	
	public static void giveGETrader(Dominion dominion) {
		
		UUID traderUUID;
		UUID traderLlamaUUID;
		
		if(dominion.getHomeLocation() != null) {
			
			Location hLoc = dominion.getHomeLocation();
			
			LivingEntity traderEntity = (LivingEntity) hLoc.getWorld().spawnEntity(hLoc, EntityType.WANDERING_TRADER);
			traderUUID = traderEntity.getUniqueId();
			
			LivingEntity traderLlamaEntity = (LivingEntity) hLoc.getWorld().spawnEntity(hLoc, EntityType.TRADER_LLAMA);
			traderLlamaUUID = traderLlamaEntity.getUniqueId();
			
			traderLlamaEntity.addPassenger(traderEntity);
			
			/*
			Bukkit.getScheduler().runTaskLater(DominionsMain.instance, new Runnable() {
				@Override
				public void run() {
					traderLlamaEntity.setLeashHolder(traderEntity);
				}
			}, 1L);*/
			
		} else {
			traderUUID = UUID.randomUUID();
			traderLlamaUUID = UUID.randomUUID();
		}
		
		dominion.addPersistentEntity(EntityType.WANDERING_TRADER, traderUUID);
		dominion.addPersistentEntity(EntityType.TRADER_LLAMA, traderLlamaUUID);
		
	}
	
}
