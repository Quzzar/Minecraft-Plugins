package quzzar.mod;

import java.util.Arrays;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.quzzar.im.versioncontrol.VersionControl;
import com.quzzar.im.versioncontrol.global.material.GMaterial;

import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.documents.ConfigManager;

public class SimulateStrength {
	
	private static ConfigManager configManager = ConfigManager.getInstance();
	
	private static List<Material> wood = Arrays.asList(VersionControl.getMaterial(GMaterial.WOODEN_PICKAXE),
			Material.STONE_PICKAXE,Material.IRON_PICKAXE,VersionControl.getMaterial(GMaterial.GOLDEN_PICKAXE),Material.DIAMOND_PICKAXE);
	private static List<Material> stone = Arrays.asList(Material.STONE_PICKAXE,Material.IRON_PICKAXE,
			VersionControl.getMaterial(GMaterial.GOLDEN_PICKAXE),Material.DIAMOND_PICKAXE);
	private static List<Material> iron = Arrays.asList(Material.IRON_PICKAXE,
			VersionControl.getMaterial(GMaterial.GOLDEN_PICKAXE),Material.DIAMOND_PICKAXE);
	private static List<Material> gold = Arrays.asList(VersionControl.getMaterial(GMaterial.GOLDEN_PICKAXE),Material.DIAMOND_PICKAXE);
	private static List<Material> diamond = Arrays.asList(Material.DIAMOND_PICKAXE);
	
	
	private static List<Material> tools = Arrays.asList(
			VersionControl.getMaterial(GMaterial.WOODEN_PICKAXE),Material.STONE_PICKAXE,Material.IRON_PICKAXE,
			VersionControl.getMaterial(GMaterial.GOLDEN_PICKAXE),Material.DIAMOND_PICKAXE,
			VersionControl.getMaterial(GMaterial.WOODEN_AXE),Material.STONE_AXE,Material.IRON_AXE,
			VersionControl.getMaterial(GMaterial.GOLDEN_AXE),Material.DIAMOND_AXE,
			VersionControl.getMaterial(GMaterial.WOODEN_SHOVEL),VersionControl.getMaterial(GMaterial.STONE_SHOVEL),
			VersionControl.getMaterial(GMaterial.IRON_SHOVEL),VersionControl.getMaterial(GMaterial.GOLDEN_SHOVEL),
			VersionControl.getMaterial(GMaterial.DIAMOND_SHOVEL),
			VersionControl.getMaterial(GMaterial.WOODEN_HOE),Material.STONE_HOE,Material.IRON_HOE,
			VersionControl.getMaterial(GMaterial.GOLDEN_HOE),Material.DIAMOND_HOE,
			VersionControl.getMaterial(GMaterial.WOODEN_SWORD),Material.STONE_SWORD,Material.IRON_SWORD,
			VersionControl.getMaterial(GMaterial.GOLDEN_SWORD),Material.DIAMOND_SWORD);
	
	public static boolean attemptBreak(Player p, TextureDatabase tex, ItemStack i){
		
		if(configManager.getConfig().getBoolean("Blocks.Simulate_Block_Hardness")){
			
			if(p.getGameMode().equals(GameMode.CREATIVE)){
				return true;
			}
			
			switch(tex.getStrength()){
				case INSTANT: {
					return true;
				}
				case NONE: {
					return true;
				}
				case WOOD: {
				if(wood.contains(i.getType())){
					return true;
				}
				} break;
				case STONE: {
					if(stone.contains(i.getType())){
						return true;
					}
				} break;
				case IRON: {
					if(iron.contains(i.getType())){
						return true;
					}
				} break;
				case GOLD: {
					if(gold.contains(i.getType())){
						return true;
					}
				} break;
				case DIAMOND: {
					if(diamond.contains(i.getType())){
						return true;
					}
				} break;
				default: {
					return false;
				}
			}
			return false;
		}else{
			return true;
		}
	}
	
	
	//
	
	public static String startBreak(Player p, TextureDatabase tex, ItemStack i){
		
		if(configManager.getConfig().getBoolean("Blocks.Simulate_Block_Hardness")){
			
			if(p.getGameMode().equals(GameMode.CREATIVE)){
				return "break";
			}else{
				applyItemUseage(i);
			}
			
			switch(tex.getStrength()){
				case INSTANT: {
					return "break";
				}
				case NONE: {
					return "nothing";
				}
				case WOOD: {
					if(!wood.contains(i.getType())){
						return "delay";
					}
				} break;
				case STONE: {
					if(!stone.contains(i.getType())){
						return "delay";
					}
				} break;
				case IRON: {
					if(!iron.contains(i.getType())){
						return "delay";
					}
				} break;
				case GOLD: {
					if(!gold.contains(i.getType())){
						return "delay";
					}
				} break;
				case DIAMOND: {
					if(!diamond.contains(i.getType())){
						return "delay";
					}
				} break;
				default: {
					return "don't break";
				}
			}
			return "nothing";
		}else{
			return "break";
		}
		
	}
	
	
	private static void applyItemUseage(ItemStack i){
		if(tools.contains(i.getType())){
			i.setDurability((short) (i.getDurability()+1));
			if(i.getDurability()<=0){
				i.setAmount(i.getAmount()-1);
			}
		}
	}
	
}
