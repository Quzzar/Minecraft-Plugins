package com.quzzar.im.versioncontrol;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import com.quzzar.im.versioncontrol.global.material.GMaterial;
import com.quzzar.im.versioncontrol.global.sound.GSound;

@SuppressWarnings("deprecation")
public class VersionControl {

	private static VersionHandler vHandler;
	
	public static void initialize() {
		
		if (Bukkit.getVersion().contains("1.12")) {
			vHandler = new VersionHandler_1_12(VersionType.V_1_12);
		}
		
	}
	
	public static VersionType getVersion() {
		return vHandler.getVersion();
	}
	
	
	
	public static ItemStack getItemStack(GMaterial material, int amount) {
		MaterialData mData = vHandler.getMaterialData(material);
		return new ItemStack(mData.getItemType(), amount, mData.getData());
	}
	
	public static MaterialData getMaterialData(GMaterial material) {
		return vHandler.getMaterialData(material);
	}
	
	public static Material getMaterial(GMaterial material) {
		return vHandler.getMaterialData(material).getItemType();
	}
	
	public static boolean areSameMaterial(ItemStack itemstack, GMaterial material) {
		MaterialData mData = vHandler.getMaterialData(material);
		return itemstack.getType().equals(mData.getItemType())
				&& itemstack.getData().getData()==mData.getData();
	}
	
	public static boolean areSameMaterial(Block block, GMaterial material) {
		MaterialData mData = vHandler.getMaterialData(material);
		return block.getType().equals(mData.getItemType())
				&& block.getData()==mData.getData();
	}
	
	public static boolean areSameMaterialOnly(ItemStack itemstack, GMaterial material) {
		MaterialData mData = vHandler.getMaterialData(material);
		return itemstack.getType().equals(mData.getItemType());
	}
	
	public static boolean areSameMaterialOnly(Block block, GMaterial material) {
		MaterialData mData = vHandler.getMaterialData(material);
		return block.getType().equals(mData.getItemType());
	}
	
	public static Sound getSound(GSound sound) {
		return vHandler.getSound(sound);
	}
	
}
