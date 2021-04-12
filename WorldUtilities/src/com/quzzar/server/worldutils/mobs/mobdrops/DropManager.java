package com.quzzar.server.worldutils.mobs.mobdrops;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.SplittableRandom;

import org.bukkit.Material;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;
import com.quzzar.server.worldutils.mobs.mobdrops.drop.Drop;
import com.quzzar.server.worldutils.mobs.mobdrops.drop.ItemDrop;
import com.quzzar.server.worldutils.mobs.mobdrops.drop.MoneyDrop;

public class DropManager {

	private static List<DamageCause> preventedDropsTypes = Arrays.asList(
			DamageCause.FALL, DamageCause.DROWNING, DamageCause.CONTACT, DamageCause.SUFFOCATION, DamageCause.HOT_FLOOR);
	
	private static List<EntityType> noncontrolledEntities = Arrays.asList(
			EntityType.COW, EntityType.PIG, EntityType.SHEEP, EntityType.CHICKEN, EntityType.COD, EntityType.DOLPHIN,
			EntityType.MUSHROOM_COW, EntityType.PANDA, EntityType.PARROT, EntityType.POLAR_BEAR, EntityType.PUFFERFISH,
			EntityType.RABBIT, EntityType.SALMON, EntityType.TROPICAL_FISH, EntityType.TURTLE);
	
	private static final int CHECK_RADIUS_XZ = 3;
	private static final int CHECK_RADIUS_Y = 5;
	private static final int AVERAGE_COUNT = 3;
	
	private static SplittableRandom splitRand = new SplittableRandom();
	
	// When a mob dies...
	public static void entityDead(EntityDeathEvent e) {
		
		// If entity is in non-controlled entities list, then don't do anything - just return
		if(noncontrolledEntities.contains(e.getEntityType())) {
			return;
		}
		
		// Clear all mob drops
		e.getDrops().clear();
		
		if(e.getEntity() == null) {
			return;
		}
		
		// If deathType is in list, prevent getting drops
		if(preventedDropsTypes.contains(e.getEntity().getLastDamageCause().getCause())) {
			return;
		}
		
		
		double chanceReducer = 1;
		Collection<LivingEntity> nearbyEntities = e.getEntity().getWorld().getNearbyLivingEntities(
				e.getEntity().getLocation(), CHECK_RADIUS_XZ, CHECK_RADIUS_Y);
		if(AVERAGE_COUNT < nearbyEntities.size()) {
			chanceReducer = 1.0/(nearbyEntities.size()-AVERAGE_COUNT);
			//Bukkit.broadcastMessage(ChatColor.BLUE+"DEBUG: Drops rates reduced by "+(100 - chanceReducer*100)+"%");
		}
		
		if(e.getEntityType() == null) {
			return;
		}
		
		// Non-adult animals don't drop
		if(e.getEntity() instanceof Ageable) {
			Ageable ageable = (Ageable) e.getEntity();
			if(!ageable.isAdult()) {
				return;
			}
		}
		
		LinkedList<Drop> entityDrops = DropMap.getDropMap().get(e.getEntityType());
		
		if(entityDrops == null) {
			return;
		}
		
		// Determine drops from dropMap
		for(Drop drop : entityDrops) {
			if(drop.getChance()*chanceReducer > splitRand.nextDouble(100)) {
				if(drop instanceof ItemDrop) {
					ItemDrop itemDrop = (ItemDrop) drop;
					ItemStack item = itemDrop.getDrop().clone();
					item.setAmount(splitRand.nextInt(itemDrop.getMin(), itemDrop.getMax()+1));
					e.getDrops().add(applyRandomDurability(item));
				} else if(drop instanceof MoneyDrop && e.getEntity().getKiller() != null) {
					MoneyDrop moneyDrop = (MoneyDrop) drop;
					
					PlayerCharacter pChar = CharacterManager.getCharacter(e.getEntity().getKiller());
					pChar.giveMoney(round(splitRand.nextDouble(moneyDrop.getMin(), moneyDrop.getMax())));
					
				}
			}
		}
		
		// If it has an inventory, drop inventory
		if(e.getEntity() instanceof ChestedHorse) {
			ChestedHorse chestedHorse = (ChestedHorse) e.getEntity();
			for(ItemStack item : chestedHorse.getInventory().getStorageContents()) {
				if(item != null && !item.getType().equals(Material.AIR)) {
					e.getDrops().add(applyRandomDurability(item));
					//Bukkit.broadcastMessage("- Dropping from Inventory "+item.getI18NDisplayName());
				}
			}
		}
		
		// Add to drops, whatever it has equipped
		for(ItemStack equippedItem : e.getEntity().getEquipment().getArmorContents()) {
			if(equippedItem != null && !equippedItem.getType().equals(Material.AIR)) {
				e.getDrops().add(applyRandomDurability(equippedItem));
				//Bukkit.broadcastMessage("- Dropping from Equipment "+equippedItem.getI18NDisplayName());
			}
		}
		ItemStack itemMainHand = e.getEntity().getEquipment().getItemInMainHand();
		if(itemMainHand != null && !itemMainHand.getType().equals(Material.AIR)) {
			e.getDrops().add(applyRandomDurability(itemMainHand));
			//Bukkit.broadcastMessage("- Dropping from MainHand "+itemMainHand.getI18NDisplayName());
		}
		ItemStack itemOffHand = e.getEntity().getEquipment().getItemInOffHand();
		if(itemOffHand != null && !itemOffHand.getType().equals(Material.AIR)) {
			e.getDrops().add(applyRandomDurability(itemOffHand));
			//Bukkit.broadcastMessage("- Dropping from OffHand "+itemOffHand.getI18NDisplayName());
		}
		
	}
	
	
	private static ItemStack applyRandomDurability(ItemStack item) {
		if(item.getType().getMaxDurability() > 0) {
			Damageable damage = (Damageable) item.getItemMeta();
			damage.setDamage(splitRand.nextInt(1, item.getType().getMaxDurability()));
			item.setItemMeta((ItemMeta) damage);
		}
		return item;
	}
	
	private static double round(double d) {
		return Math.round(d * 100.0) / 100.0;
	}
	
}
