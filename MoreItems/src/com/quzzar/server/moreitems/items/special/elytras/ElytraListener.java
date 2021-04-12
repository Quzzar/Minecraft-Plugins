package com.quzzar.server.moreitems.items.special.elytras;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.destroystokyo.paper.ParticleBuilder;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.ItemDataTag;
import com.quzzar.server.moreitems.ItemUtils;
import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;

public class ElytraListener {

	private static final int BOOST_DAMAGE = 1;
	
	public static void onPlayerSneak(PlayerToggleSneakEvent e) {
		
		Player p = e.getPlayer();
		
		if(p.getInventory().getChestplate() == null 
				|| !p.isGliding()) {
			return;
		}
		
		ItemStack chestplateItem = p.getInventory().getChestplate();
		
		if(CraftMeta.itemHasData(chestplateItem, ItemDataTag.SPECIAL_ELYTRA)) {
			
			// Apply sound effect
			p.getLocation().getWorld().playSound(
					p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1.3f, 0.8f);
			
			// Apply particle effect
			ParticleBuilder pBuilder = new ParticleBuilder(Particle.EXPLOSION_LARGE);
        	pBuilder.location(p.getLocation()).count(1).spawn();
			
			// Apply boost
			Vector newVelocity = p.getVelocity().add(p.getEyeLocation().getDirection().multiply(0.4));
			if(newVelocity.length() > 580) {
				p.setVelocity(newVelocity.normalize().multiply(580));
			} else {
				p.setVelocity(newVelocity);
			}
			
			// Apply damage
			Damageable damage = (Damageable) chestplateItem.getItemMeta();
			damage.setDamage(damage.getDamage()+BOOST_DAMAGE);
			
			if(damage.getDamage() > Material.ELYTRA.getMaxDurability()) {
				ItemUtils.breakItem(p, chestplateItem);
			}
			
			chestplateItem.setItemMeta((ItemMeta) damage);
			return;
		}
		
	}
	
	public static void onPlayerJump(PlayerJumpEvent e) {
		
		Player p = e.getPlayer();
		
		// When player jumps and is sneaking, do boost upwards with advanced elytra
		if(p.getInventory().getChestplate() == null || !p.isSneaking()) {
			return;
		}
		
		ItemStack chestplateItem = p.getInventory().getChestplate();
		ItemStack advancedElytra = ItemManager.getItemClone(ItemType.ADVANCED_ELYTRA);
		
		if(ItemUtils.areSimilarNoDamage(chestplateItem, advancedElytra)) {
			
			// Apply sound effect
			p.getLocation().getWorld().playSound(
					p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1.3f, 0.6f);
			
			// Apply particle effect
			ParticleBuilder pBuilder = new ParticleBuilder(Particle.EXPLOSION_LARGE);
        	pBuilder.location(p.getLocation()).count(3).spawn();
			
			// Apply boost upwards
			p.setVelocity(new Vector(0, 22, 0));
			
			// Apply damage
			Damageable damage = (Damageable) chestplateItem.getItemMeta();
			damage.setDamage(damage.getDamage()+BOOST_DAMAGE);
			
			if(damage.getDamage() > Material.ELYTRA.getMaxDurability()) {
				ItemUtils.breakItem(p, chestplateItem);
			}
			
			chestplateItem.setItemMeta((ItemMeta) damage);
			
			p.setGliding(true);
			
			return;
			
		}
		
	}
	
}
