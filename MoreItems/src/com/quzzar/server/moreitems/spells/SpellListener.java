package com.quzzar.server.moreitems.spells;

import java.util.SplittableRandom;

import org.bukkit.Location;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class SpellListener implements org.bukkit.event.Listener {

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		if (e.getEntity().hasMetadata(GrandFireballs.spellName)) {
			if (e.getHitEntity() != null) {
				e.getHitEntity().setFireTicks(500);
			}
			if (e.getHitBlock() != null) {
				e.getHitBlock().getWorld().createExplosion(e.getHitBlock().getX(), e.getHitBlock().getY(),
						e.getHitBlock().getZ(), 3.0F, true, false);
			}
			return;
		}

		if (e.getEntity().hasMetadata(GreaterFireballs.spellName)) {
			if (e.getHitEntity() != null) {
				e.getHitEntity().setFireTicks(200);
			}
			if (e.getHitBlock() != null) {
				e.getHitBlock().getWorld().createExplosion(e.getHitBlock().getX(), e.getHitBlock().getY(),
						e.getHitBlock().getZ(), 2.0F, true, false);
			}
			return;
		}

		if (e.getEntity().hasMetadata(Fireballs.spellName)) {
			if (e.getHitEntity() != null) {
				e.getHitEntity().setFireTicks(80);
			}
			return;
		}

		if (e.getEntity().hasMetadata(LesserFireballs.spellName)) {
			if (e.getHitEntity() != null) {
				e.getHitEntity().setFireTicks(20);
			}
			return;
		}

		if (e.getEntity().hasMetadata(ToxicFumes.spellName)) {
			if ((e.getHitEntity() != null) && ((e.getHitEntity() instanceof LivingEntity))) {
				((LivingEntity) e.getHitEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 1));
			}
			return;
		}

		if (e.getEntity().hasMetadata(DeadlyFumes.spellName)) {
			if ((e.getHitEntity() != null) && ((e.getHitEntity() instanceof LivingEntity))) {
				((LivingEntity) e.getHitEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 2));
			}
			if (e.getHitBlock() != null) {
				e.getHitBlock().getWorld().createExplosion(e.getHitBlock().getX(), e.getHitBlock().getY(),
						e.getHitBlock().getZ(), 2.0F, false, false);
			}
			return;
		}

		if (e.getEntity().hasMetadata(Polymorph.spellName)) {
			if ((e.getHitEntity() != null) && ((e.getHitEntity() instanceof Player))) {
				Player hitPlayer = (Player) e.getHitEntity();
				hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 1));
				MobDisguise mobDisguise = new MobDisguise(
						Polymorph.polymorphTypes.get(new SplittableRandom().nextInt(Polymorph.polymorphTypes.size())));
				DisguiseAPI.disguiseEntity(hitPlayer, mobDisguise);
			}
			return;
		}
		
		if (e.getEntity().hasMetadata(Feasting.spellName)) {
			
			Location droppingLoc = getHitLocation(e);
			for(ItemStack foodItem : Feasting.getRandomFoods()) {
				droppingLoc.getWorld().dropItem(droppingLoc, foodItem);
			}
			
			return;
		}
		
		if (e.getEntity().hasMetadata(Healing.spellName)) {
			
			Location hitLoc = getHitLocation(e);
			AreaEffectCloud aoeCloud = (AreaEffectCloud) hitLoc.getWorld().spawnEntity(hitLoc, EntityType.AREA_EFFECT_CLOUD);
			aoeCloud.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 1, 1, false, true, false), true);
			
			return;
		}
		
	}
	
	@EventHandler
	public void onEntityDamagedByEntity(EntityDamageByEntityEvent e) {
		if(e.getDamager().hasMetadata(Feasting.spellName)) {
			e.setCancelled(true);
		}
		if(e.getDamager().hasMetadata(Healing.spellName)) {
			e.setCancelled(true);
		}
	}
	
	private Location getHitLocation(ProjectileHitEvent e) {
		if(e.getHitBlock() != null) {
			return e.getHitBlock().getRelative(e.getHitBlockFace()).getLocation();
		} else if (e.getHitEntity() != null) {
			return e.getHitEntity().getLocation();
		} else {
			return null;
		}
	}
}
