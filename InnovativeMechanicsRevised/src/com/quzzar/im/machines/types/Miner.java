package com.quzzar.im.machines.types;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import com.quzzar.im.InnovativeMechanics;
import com.quzzar.im.machines.Machine;
import com.quzzar.im.machines.MachineType;
import com.quzzar.im.machines.types.macerator.OreList;
import com.quzzar.im.sound.SoundDatabase;
import com.quzzar.im.sound.SoundManager;
import com.quzzar.im.structures.Structure;
import com.quzzar.im.structures.StructureManager;
import com.quzzar.im.structures.loading.asbuilding.ASDirection;
import com.quzzar.im.structures.loading.asbuilding.ASPlacement;
import com.quzzar.im.structures.loading.inv.InvTag;
import com.quzzar.im.textures.TextureCollection;
import com.quzzar.im.textures.TextureDatabase;
import com.quzzar.im.utils.ItemUtils;

public class Miner extends Machine {
	
	private static ArrayList<Material> minerBlackListedMaterials = new ArrayList<Material>(Arrays.asList(
			Material.IRON_BLOCK,Material.DIAMOND_BLOCK, Material.BEDROCK,Material.BARRIER,Material.LAVA,
			Material.LAPIS_BLOCK,Material.SLIME_BLOCK,Material.REDSTONE_BLOCK,
			Material.PLAYER_HEAD, Material.WATER, Material.OBSIDIAN));
	
	public Miner(MachineType machineType, TextureDatabase itemTexture, TextureCollection texCollection,
			TextureCollection poweredTexCollection) {
		super(machineType, itemTexture, texCollection, poweredTexCollection);
	}

	@Override
	protected ItemStack initialize(ItemStack rawItem) {

		ArrayList<ASPlacement> placements = ASPlacement.getMachineBaseList();
		placements.add(new ASPlacement(0, 1, -1.425, ASDirection.FORWARD));

		setASPlacements(placements);
		
		setSpeed(16);
		
		addToIndexInvTagMap(8, InvTag.OUTPUT);

		///

		ItemMeta im = rawItem.getItemMeta();

		im.setDisplayName(ChatColor.GOLD + "Miner");

		rawItem.setItemMeta(im);

		ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(InnovativeMechanics.instance, "Miner"), rawItem);
		shR.shape(new String[] { "III", "IRI", "III" });
		shR.setIngredient('I', Material.IRON_BLOCK);
		shR.setIngredient('R', Material.DIAMOND_PICKAXE);
		InnovativeMechanics.instance.getServer().addRecipe(shR);

		return rawItem;

	}

	@Override
	public void create(Block placedBlock, BlockFace direction, Player p) {

		/// Set Variables
		
		String invName = "[Output] Mined Blocks";
		int invSize = 18;
		
		///

		Location centerLocation = placedBlock.getLocation();
		centerLocation.add(0.5, 0, 0.5);

		Structure structure = StructureManager.createStructure(centerLocation, direction, p,
				MachineType.MINER);
		
		structure.addStructInventory(InvTag.OUTPUT, Bukkit.createInventory(null, invSize, invName), invName);

	}

	@Override
	public void interact(Structure structure, ArmorStand as, Player p) {

		InvTag tag = structure.getInvTag(as);
		Inventory inv = structure.getInventory(tag);

		if (inv != null) {
			
			SoundManager.playSound(as.getEyeLocation(), SoundDatabase.MINICHEST_OPEN);
			p.openInventory(inv);
			
		} else {
			
			if(structure.getMachineType().isPowerable()) {
				structure.toggleRunning();
			}
			
		}
		
	}

	@Override
	public void updateTask(Structure structure) {
		
		if(!structure.isRunning()) {
			return;
		}
		
		Location centerLocation = structure.getCenterLocation().toLocation();
		
		SoundManager.playSound(centerLocation, SoundDatabase.MACHINE_RUNNING);
		
		boolean success = attemptToMine(structure, centerLocation);
		if(!success) {
			structure.setRunning(false);
		}
		
	}

	@Override
	public void delete(Structure structure) {

		StructureManager.removeStructure(structure);
		SoundManager.playSound(structure.getCenterLocation().toLocation(), SoundDatabase.MACHINE_BREAK);

	}
	
	private boolean attemptToMine(Structure structure, Location centerLocation) {
		
		Location miningLoc = getBlockBelowLoc(centerLocation.clone());
		if(miningLoc == null) {return false;}
		
		Block miningBlock = miningLoc.getBlock();
		
		if(!minerBlackListedMaterials.contains(miningBlock.getType())){
			
			boolean mineOre = false;
			
			for(OreList ore : OreList.values()){
				if(ore.getOreToChunkMap().getMaterial().equals(miningBlock.getType())){
					mineOre = true;
				}
			}
			
			SoundManager.playSound(centerLocation, SoundDatabase.MACHINE_SUCCESS);
			
			Inventory outputInv = structure.getInventory(InvTag.OUTPUT);
			
			if(mineOre){
				
				ItemStack i = new ItemStack(miningBlock.getType(),1);
				ItemUtils.addItemToInventory(outputInv, i, centerLocation);
				
			}else{
				
				for(ItemStack i : miningBlock.getDrops()){
					ItemUtils.addItemToInventory(outputInv, i, centerLocation);
				}
				
			}
			
			miningBlock.setType(Material.AIR);
			SoundManager.playSound(miningBlock.getLocation(), SoundDatabase.BLOCK_BREAK);
			
			return true;
			
		} else {
			return false;
		}
		
	}
	
	private static Location getBlockBelowLoc(Location loc) {
		if(loc == null || loc.getY() < 1) {
	    	return null;
	    }
		Location locBelow = loc.subtract(0, 1, 0);
	    if(locBelow.getBlock().getType().equals(Material.AIR) || locBelow.getBlock().getType().equals(Material.BARRIER)) {
	        locBelow = getBlockBelowLoc(locBelow);
	    }
	    return locBelow;
	}
	
}
