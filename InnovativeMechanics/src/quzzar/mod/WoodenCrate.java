package quzzar.mod;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.quzzar.im.versioncontrol.VersionControl;
import com.quzzar.im.versioncontrol.global.material.GMaterial;
import com.quzzar.im.versioncontrol.global.sound.GSound;

import quzzar.mod.Textures.TextureDatabase;

public class WoodenCrate {

	
	public static HashMap<Material,Integer> valueMapCommon = new HashMap<Material,Integer>();
	
	public static HashMap<Material,Integer> valueMapUncommon = new HashMap<Material,Integer>();
	
	public static HashMap<Material,Integer> valueMapRare = new HashMap<Material,Integer>();
	
	public static HashMap<Material,Integer> valueMapAll = new HashMap<Material,Integer>();
	
	private static Random rand = new Random();
	
	public static void loadMaps(){
		
		
		valueMapRare.put(Material.DIAMOND_BLOCK, 1);
		
		valueMapRare.put(Material.GOLDEN_APPLE, 1);
		
		valueMapRare.put(Material.EMERALD, 18);
		
		valueMapRare.put(Material.DIAMOND, 15);
		
		valueMapRare.put(Material.IRON_BLOCK, 14);
		
		valueMapRare.put(Material.REDSTONE_BLOCK, 15);
		
		valueMapRare.put(Material.GOLDEN_CARROT, 10);
		
		valueMapRare.put(Material.GOLD_BLOCK, 14);
		
		valueMapRare.put(Material.COAL_BLOCK, 20);
		
		valueMapRare.put(Material.EMERALD_BLOCK, 4);
		
		valueMapRare.put(Material.GRASS, 12);
		
		valueMapRare.put(Material.CHAINMAIL_BOOTS, 15);
		
		valueMapRare.put(Material.CHAINMAIL_CHESTPLATE, 8);
		
		valueMapRare.put(Material.CHAINMAIL_HELMET, 13);
		
		valueMapRare.put(Material.CHAINMAIL_LEGGINGS, 10);
		
		valueMapRare.put(Material.DIAMOND_SWORD, 8);
		
		valueMapRare.put(VersionControl.getMaterial(GMaterial.DIAMOND_SHOVEL), 10);
		
		valueMapRare.put(Material.DIAMOND_AXE, 8);
		
		valueMapRare.put(Material.DIAMOND_PICKAXE, 8);
		
		valueMapRare.put(Material.DIAMOND_HELMET, 5);
		
		valueMapRare.put(Material.DIAMOND_LEGGINGS, 2);
		
		valueMapRare.put(Material.DIAMOND_CHESTPLATE, 3);
		
		valueMapRare.put(Material.ANVIL, 45);
		
		valueMapRare.put(Material.DIAMOND_ORE, 75);
		
		valueMapRare.put(Material.DIAMOND_BOOTS, 5);
		
		valueMapRare.put(Material.FLINT_AND_STEEL, 80);
		
		valueMapRare.put(Material.BEETROOT_SEEDS, 80);
		
		valueMapRare.put(Material.MELON_SEEDS, 80);
		
		valueMapRare.put(Material.PUMPKIN_SEEDS, 80);
		
		valueMapRare.put(Material.IRON_PICKAXE, 50);
		
		valueMapRare.put(Material.IRON_CHESTPLATE, 45);
		
		valueMapRare.put(Material.IRON_BOOTS, 45);
		
		valueMapRare.put(Material.IRON_SWORD, 55);
		
		valueMapRare.put(Material.IRON_LEGGINGS, 45);
		
		valueMapRare.put(Material.IRON_HELMET, 45);
		
		valueMapRare.put(Material.IRON_AXE, 55);
		
		
		
		valueMapUncommon.put(VersionControl.getMaterial(GMaterial.PLAYER_HEAD_ITEM), 1);
		
		valueMapUncommon.put(Material.DIAMOND_ORE, 9);
		
		valueMapUncommon.put(Material.EMERALD_ORE, 9);
		
		valueMapUncommon.put(Material.REDSTONE, 35);
		
		valueMapUncommon.put(Material.TORCH, 80);
		
		valueMapUncommon.put(Material.TIPPED_ARROW, 50);
		
		valueMapUncommon.put(Material.STONE, 80);
		
		valueMapUncommon.put(Material.QUARTZ, 35);
		
		valueMapUncommon.put(Material.STONE_SWORD, 80);
		
		valueMapUncommon.put(Material.STONE_PICKAXE, 75);
		
		valueMapUncommon.put(Material.COAL_BLOCK, 10);
		
		valueMapUncommon.put(Material.CHAINMAIL_BOOTS, 5);
		
		valueMapUncommon.put(Material.CHAINMAIL_CHESTPLATE, 2);
		
		valueMapUncommon.put(Material.CHAINMAIL_HELMET, 4);
		
		valueMapUncommon.put(Material.CHAINMAIL_LEGGINGS, 3);
		
		valueMapUncommon.put(Material.DIAMOND_SWORD, 1);
		
		valueMapUncommon.put(Material.BLAZE_POWDER, 20);
		
		valueMapUncommon.put(Material.FLINT_AND_STEEL, 30);
		
		valueMapUncommon.put(Material.BEETROOT_SEEDS, 23);
		
		valueMapUncommon.put(Material.MELON_SEEDS, 23);
		
		valueMapUncommon.put(Material.PUMPKIN_SEEDS, 23);
		
		valueMapUncommon.put(Material.IRON_PICKAXE, 15);
		
		valueMapUncommon.put(Material.IRON_HOE, 15);
		
		valueMapUncommon.put(VersionControl.getMaterial(GMaterial.IRON_SHOVEL), 15);
		
		valueMapUncommon.put(Material.IRON_CHESTPLATE, 10);
		
		valueMapUncommon.put(Material.IRON_BOOTS, 15);
		
		valueMapUncommon.put(Material.IRON_SWORD, 17);
		
		valueMapUncommon.put(Material.BOW, 25);
		
		valueMapUncommon.put(Material.IRON_LEGGINGS, 12);
		
		valueMapUncommon.put(Material.IRON_HELMET, 16);
		
		valueMapUncommon.put(Material.IRON_AXE, 55);
		
		valueMapUncommon.put(Material.APPLE, 90);
		
		valueMapUncommon.put(Material.GOLD_INGOT, 70);
		
		valueMapUncommon.put(Material.IRON_INGOT, 90);
		
		valueMapUncommon.put(Material.COOKED_BEEF, 95);
		
		valueMapUncommon.put(Material.FISHING_ROD, 80);
		
		valueMapUncommon.put(Material.COOKED_CHICKEN, 90);
		
		valueMapUncommon.put(Material.COOKED_MUTTON, 90);
		
		valueMapUncommon.put(Material.COOKED_RABBIT, 90);
		
		
		
		valueMapCommon.put(Material.BEETROOT_SEEDS, 10);
		
		valueMapCommon.put(Material.MELON_SEEDS, 9);
		
		valueMapCommon.put(Material.PUMPKIN_SEEDS, 9);
		
		valueMapCommon.put(Material.COAL_BLOCK, 10);
		
		valueMapCommon.put(VersionControl.getMaterial(GMaterial.WOOD), 90);
		
		valueMapCommon.put(Material.COAL_ORE, 35);
		
		valueMapCommon.put(Material.GOLD_ORE, 15);
		
		valueMapCommon.put(Material.IRON_ORE, 30);
		
		valueMapCommon.put(Material.LAPIS_ORE, 15);
		
		valueMapCommon.put(Material.REDSTONE_ORE, 15);
		
		valueMapCommon.put(VersionControl.getMaterial(GMaterial.LOG_2), 30);
		
		valueMapCommon.put(VersionControl.getMaterial(GMaterial.WOODEN_AXE), 20);
		
		valueMapCommon.put(VersionControl.getMaterial(GMaterial.WOODEN_SWORD), 20);
		
		valueMapCommon.put(VersionControl.getMaterial(GMaterial.WOODEN_HOE), 20);
		
		valueMapCommon.put(VersionControl.getMaterial(GMaterial.WOODEN_PICKAXE), 20);
		
		valueMapCommon.put(VersionControl.getMaterial(GMaterial.WOODEN_SHOVEL), 20);
		
		valueMapCommon.put(Material.STONE_AXE, 15);
		
		valueMapCommon.put(Material.STONE_HOE, 15);
		
		valueMapCommon.put(Material.STONE_PICKAXE, 15);
		
		valueMapCommon.put(VersionControl.getMaterial(GMaterial.STONE_SHOVEL), 15);
		
		valueMapCommon.put(Material.STONE_SWORD, 15);
		
		valueMapCommon.put(Material.COAL, 92);
		
		valueMapCommon.put(Material.BONE, 60);
		
		valueMapCommon.put(Material.ARROW, 80);
		
		valueMapCommon.put(Material.COBBLESTONE, 95);
		
		valueMapCommon.put(Material.APPLE, 90);
		
		valueMapCommon.put(Material.WHEAT, 95);
		
		valueMapCommon.put(Material.SUGAR, 80);
		
		valueMapCommon.put(Material.SUGAR_CANE, 95);
		
		valueMapCommon.put(Material.COOKED_BEEF, 95);
		
		valueMapCommon.put(Material.EGG, 95);
		
		valueMapCommon.put(Material.DIRT, 95);
		
		valueMapCommon.put(Material.SAND, 70);
		
		valueMapCommon.put(Material.GRAVEL, 70);
		
		valueMapCommon.put(Material.FLINT, 45);
		
		valueMapCommon.put(Material.SANDSTONE, 70);
		
		valueMapAll.putAll(valueMapCommon);
		valueMapAll.putAll(valueMapUncommon);
		valueMapAll.putAll(valueMapRare);
	}
	
	
	
	public static void open(ItemStack i, TextureDatabase tex, Player p){
		
		if(tex.equals(TextureDatabase.WOOD_CRATE_COMMON)){
			
			Material m = getOpenedMaterialCommon();
			if(m!=null){
				Utility.addItemToInventory(p.getInventory(), new ItemStack(m,1), p.getLocation());
			}
			
		} else if(tex.equals(TextureDatabase.WOOD_CRATE_UNCOMMON)){
			
			Material m = getOpenedMaterialUncommon();
			if(m!=null){
				Utility.addItemToInventory(p.getInventory(), new ItemStack(m,1), p.getLocation());
			}
			
		} else if(tex.equals(TextureDatabase.WOOD_CRATE_RARE)){
			
			Material m = getOpenedMaterialRare();
			if(m!=null){
				Utility.addItemToInventory(p.getInventory(), new ItemStack(m,1), p.getLocation());
			}
			
		}
		
		p.getWorld().playSound(p.getLocation(), VersionControl.getSound(GSound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR), 0.2f, 0.75f);
		
		i.setAmount(i.getAmount()-1);
		
		int r = rand.nextInt(2) + 1;
		if(r==1){
			Utility.addItemToInventory(p.getInventory(), ItemsList.Wooden_Crate_Empty(1), p.getLocation());
		}
		
	}
	
	
	public static ItemStack make(Material m){
		
		if(valueMapRare.containsKey(m)){
			
			int r = rand.nextInt(4) + 1;
			if(r==1){
				return ItemsList.Wooden_Crate_Rare(1);
			}
		}else if(valueMapUncommon.containsKey(m)){
			
			int r = rand.nextInt(2) + 1;
			if(r==1){
				return ItemsList.Wooden_Crate_Uncommon(1);
			}
		}
		
		return ItemsList.Wooden_Crate_Common(1);
	}
	
	
	
	
	private static Material getOpenedMaterialCommon(){
		Material materialWon = null;
		
		for(Material m : valueMapCommon.keySet()){
			int chance = openChance(valueMapCommon.get(m));
			int r = rand.nextInt(chance) + 1;
			if(r==1){
				materialWon = m;
			}
		}
		
		return materialWon;
	}
	
	private static Material getOpenedMaterialUncommon(){
		Material materialWon = null;
		
		for(Material m : valueMapUncommon.keySet()){
			int chance = openChance(valueMapUncommon.get(m));
			int r = rand.nextInt(chance) + 1;
			if(r==1){
				materialWon = m;
			}
		}
		
		return materialWon;
	}
	
	private static Material getOpenedMaterialRare(){
		Material materialWon = null;
		
		for(Material m : valueMapRare.keySet()){
			int chance = openChance(valueMapRare.get(m));
			int r = rand.nextInt(chance) + 1;
			if(r==1){
				materialWon = m;
			}
		}
		
		return materialWon;
	}
	
	
	private static int openChance(int chance){
		return Math.abs(chance-99);
	}
	
}
