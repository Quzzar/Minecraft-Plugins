package com.quzzar.im.machines.types;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.quzzar.im.InnovativeMechanics;
import com.quzzar.im.machines.Machine;
import com.quzzar.im.machines.MachineType;
import com.quzzar.im.sound.SoundDatabase;
import com.quzzar.im.sound.SoundManager;
import com.quzzar.im.structures.Structure;
import com.quzzar.im.structures.StructureManager;
import com.quzzar.im.structures.loading.asbuilding.ASPlacement;
import com.quzzar.im.textures.TextureCollection;
import com.quzzar.im.textures.TextureDatabase;

public class Healer extends Machine {
	
	
	public Healer(MachineType machineType, TextureDatabase itemTexture, TextureCollection texCollection,
			TextureCollection poweredTexCollection) {
		super(machineType, itemTexture, texCollection, poweredTexCollection);
	}

	@Override
	protected ItemStack initialize(ItemStack rawItem) {

		ArrayList<ASPlacement> placements = ASPlacement.getMachineBaseList();

		setASPlacements(placements);

		///

		ItemMeta im = rawItem.getItemMeta();

		im.setDisplayName(ChatColor.RED + "Healer");

		rawItem.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(InnovativeMechanics.instance, "Healer"), rawItem);
		shR.shape(new String[] { "III", "IGI", "III" });
		shR.setIngredient('I', Material.IRON_BLOCK);
		shR.setIngredient('G', Material.GLISTERING_MELON_SLICE);
		InnovativeMechanics.instance.getServer().addRecipe(shR);

		return rawItem;

	}

	@Override
	public void create(Block placedBlock, BlockFace direction, Player p) {

		Location centerLocation = placedBlock.getLocation();
		centerLocation.add(0.5, 0, 0.5);

		StructureManager.createStructure(centerLocation, direction, p,
				MachineType.HEALER);

	}

	@Override
	public void interact(Structure structure, ArmorStand as, Player p) {

	}

	@Override
	public void updateTask(Structure structure) {
		
		Location centerLocation = structure.getCenterLocation().toLocation();
		
		SoundManager.playSound(centerLocation, SoundDatabase.QUIET_BURNING);
		
		double effect_distance = 4;
		int effect_strength = 3;
		int effect_duration = 59;
		
		for (Entity entity : centerLocation.getWorld()
				.getNearbyEntities(centerLocation, effect_distance, effect_distance, effect_distance)){
			if(entity instanceof LivingEntity){
				LivingEntity livEntity = (LivingEntity) entity;
				livEntity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, effect_duration, effect_strength, 
						false, true, false));
				livEntity.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, effect_duration, 0,
						false, true, false));
			}
		}
		
		
	}

	@Override
	public void delete(Structure structure) {

		StructureManager.removeStructure(structure);
		SoundManager.playSound(structure.getCenterLocation().toLocation(), SoundDatabase.MACHINE_BREAK);

	}
	
	
}
