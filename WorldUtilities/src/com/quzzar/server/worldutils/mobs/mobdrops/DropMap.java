package com.quzzar.server.worldutils.mobs.mobdrops;

import java.util.HashMap;
import java.util.LinkedList;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import com.quzzar.server.moreitems.items.ItemManager;
import com.quzzar.server.moreitems.items.ItemType;
import com.quzzar.server.worldutils.mobs.mobdrops.drop.Drop;
import com.quzzar.server.worldutils.mobs.mobdrops.drop.ItemDrop;
import com.quzzar.server.worldutils.mobs.mobdrops.drop.MoneyDrop;

public class DropMap {

	private static HashMap<EntityType,LinkedList<Drop>> dropMap = 
				new HashMap<EntityType,LinkedList<Drop>>();
	
	static {
		
		LinkedList<Drop> wolfDrops = new LinkedList<Drop>();
		wolfDrops.add(new ItemDrop(100, new ItemStack(Material.MUTTON), 0, 3));
		
		dropMap.put(EntityType.WOLF, wolfDrops);
		
		///
		
		LinkedList<Drop> catDrops = new LinkedList<Drop>();
		catDrops.add(new ItemDrop(20, new ItemStack(Material.SALMON), 1, 1));
		
		dropMap.put(EntityType.CAT, catDrops);
		dropMap.put(EntityType.OCELOT, catDrops);
		
		///
		
		LinkedList<Drop> foxDrops = new LinkedList<Drop>();
		foxDrops.add(new ItemDrop(100, new ItemStack(Material.MUTTON), 0, 2));
		foxDrops.add(new ItemDrop(20, new ItemStack(Material.SWEET_BERRIES), 1, 5));
		
		dropMap.put(EntityType.FOX, foxDrops);
		
		///
		
		LinkedList<Drop> endermanDrops = new LinkedList<Drop>();
		endermanDrops.add(new ItemDrop(50, new ItemStack(Material.ENDER_PEARL), 1, 1));
		endermanDrops.add(new ItemDrop(5, new ItemStack(Material.CHORUS_FRUIT), 1, 1));
		endermanDrops.add(new MoneyDrop(100, 8, 23));
		
		dropMap.put(EntityType.ENDERMAN, endermanDrops);
		
		///
		
		LinkedList<Drop> horseDrops = new LinkedList<Drop>();
		horseDrops.add(new ItemDrop(100, new ItemStack(Material.BEEF), 1, 4));
		horseDrops.add(new ItemDrop(100, new ItemStack(Material.LEATHER), 0, 2));
		horseDrops.add(new ItemDrop(5, new ItemStack(Material.SADDLE), 1, 1));
		
		dropMap.put(EntityType.HORSE, horseDrops);
		dropMap.put(EntityType.DONKEY, horseDrops);
		dropMap.put(EntityType.MULE, horseDrops);
		
		///
		
		LinkedList<Drop> llamaDrops = new LinkedList<Drop>();
		llamaDrops.add(new ItemDrop(100, new ItemStack(Material.MUTTON), 1, 4));
		llamaDrops.add(new ItemDrop(100, new ItemStack(Material.WHITE_WOOL), 0, 2));
		
		dropMap.put(EntityType.LLAMA, llamaDrops);
		dropMap.put(EntityType.TRADER_LLAMA, llamaDrops);
		
		///
		
		LinkedList<Drop> squidDrops = new LinkedList<Drop>();
		squidDrops.add(new ItemDrop(100, new ItemStack(Material.COD), 0, 2));
		squidDrops.add(new ItemDrop(80, new ItemStack(Material.SALMON), 0, 2));
		squidDrops.add(new ItemDrop(3, new ItemStack(Material.PUFFERFISH), 1, 1));
		squidDrops.add(new ItemDrop(0.5, new ItemStack(Material.FISHING_ROD), 1, 1));
		
		dropMap.put(EntityType.SQUID, squidDrops);
		
		///
		
		LinkedList<Drop> batDrops = new LinkedList<Drop>();
		batDrops.add(new ItemDrop(12, new ItemStack(Material.IRON_NUGGET), 1, 2));
		batDrops.add(new ItemDrop(4, new ItemStack(Material.GOLD_NUGGET), 1, 2));
		
		dropMap.put(EntityType.BAT, batDrops);
		
		///
		
		LinkedList<Drop> blazeDrops = new LinkedList<Drop>();
		blazeDrops.add(new ItemDrop(30, new ItemStack(Material.BLAZE_ROD), 1, 2));
		blazeDrops.add(new ItemDrop(100, new ItemStack(Material.BLAZE_POWDER), 1, 3));
		blazeDrops.add(new MoneyDrop(100, 0, 15));
		
		dropMap.put(EntityType.BLAZE, blazeDrops);
		
		///
		
		LinkedList<Drop> pandaDrops = new LinkedList<Drop>();
		pandaDrops.add(new ItemDrop(60, new ItemStack(Material.BAMBOO), 1, 4));
		pandaDrops.add(new ItemDrop(100, new ItemStack(Material.BEEF), 0, 5));
		
		dropMap.put(EntityType.PANDA, pandaDrops);
		
		///
		
		LinkedList<Drop> dolphinDrops = new LinkedList<Drop>();
		dolphinDrops.add(new ItemDrop(1.5, new ItemStack(Material.HEART_OF_THE_SEA), 1, 1));
		dolphinDrops.add(new ItemDrop(35, new ItemStack(Material.COD), 1, 2));
		dolphinDrops.add(new ItemDrop(30, new ItemStack(Material.KELP), 2, 3));
		
		dropMap.put(EntityType.DOLPHIN, dolphinDrops);
		
		///
		
		LinkedList<Drop> endermiteDrops = new LinkedList<Drop>();
		endermiteDrops.add(new ItemDrop(10, new ItemStack(Material.ENDER_PEARL), 1, 1));
		endermiteDrops.add(new ItemDrop(2, new ItemStack(Material.CHORUS_FRUIT), 1, 1));
		
		dropMap.put(EntityType.ENDERMITE, endermiteDrops);
		
		///
		
		LinkedList<Drop> caveSpiderDrops = new LinkedList<Drop>();
		caveSpiderDrops.add(new ItemDrop(80, new ItemStack(Material.STRING), 1, 2));
		caveSpiderDrops.add(new ItemDrop(100, new ItemStack(Material.SPIDER_EYE), 0, 3));
		caveSpiderDrops.add(new MoneyDrop(80, 0, 8));
		
		dropMap.put(EntityType.CAVE_SPIDER, caveSpiderDrops);
		
		///
		
		LinkedList<Drop> spiderDrops = new LinkedList<Drop>();
		spiderDrops.add(new ItemDrop(100, new ItemStack(Material.STRING), 1, 8));
		spiderDrops.add(new ItemDrop(70, new ItemStack(Material.SPIDER_EYE), 0, 5));
		
		dropMap.put(EntityType.SPIDER, spiderDrops);
		
		///
		
		LinkedList<Drop> zPigmanDrops = new LinkedList<Drop>();
		zPigmanDrops.add(new ItemDrop(20, new ItemStack(Material.GOLD_INGOT), 1, 2));
		zPigmanDrops.add(new ItemDrop(10, new ItemStack(Material.GOLDEN_CARROT), 1, 1));
		zPigmanDrops.add(new ItemDrop(50, new ItemStack(Material.COOKED_PORKCHOP), 1, 1));
		zPigmanDrops.add(new MoneyDrop(25, 8, 12));
		
		dropMap.put(EntityType.PIG_ZOMBIE, zPigmanDrops);
		
		///
		
		LinkedList<Drop> creeperDrops = new LinkedList<Drop>();
		creeperDrops.add(new ItemDrop(100, new ItemStack(Material.GUNPOWDER), 0, 3));
		creeperDrops.add(new ItemDrop(1, ItemManager.getItemClone(ItemType.EXPLOSIVES_T1), 1, 1));
		creeperDrops.add(new ItemDrop(0.05, ItemManager.getItemClone(ItemType.EXPLOSIVES_T2), 1, 1));
		creeperDrops.add(new MoneyDrop(100, 0, 12));
		
		dropMap.put(EntityType.CREEPER, creeperDrops);
		
		///
		
		LinkedList<Drop> drownedDrops = new LinkedList<Drop>();
		drownedDrops.add(new ItemDrop(60, new ItemStack(Material.ROTTEN_FLESH), 1, 2));
		drownedDrops.add(new ItemDrop(50, new ItemStack(Material.KELP), 1, 1));
		drownedDrops.add(new MoneyDrop(100, 0, 5));
		
		dropMap.put(EntityType.DROWNED, drownedDrops);
		
		///
		
		LinkedList<Drop> ghastDrops = new LinkedList<Drop>();
		ghastDrops.add(new ItemDrop(60, new ItemStack(Material.GHAST_TEAR), 1, 1));
		ghastDrops.add(new ItemDrop(50, new ItemStack(Material.GUNPOWDER), 1, 2));
		ghastDrops.add(new MoneyDrop(40, 18, 30));
		
		dropMap.put(EntityType.GHAST, ghastDrops);
		
		///
		
		LinkedList<Drop> guardianDrops = new LinkedList<Drop>();
		guardianDrops.add(new ItemDrop(60, new ItemStack(Material.PRISMARINE_SHARD), 1, 3));
		guardianDrops.add(new ItemDrop(10, new ItemStack(Material.PRISMARINE_CRYSTALS), 1, 1));
		guardianDrops.add(new ItemDrop(80, new ItemStack(Material.KELP), 1, 2));
		guardianDrops.add(new ItemDrop(8, new ItemStack(Material.HEART_OF_THE_SEA), 1, 1));
		
		dropMap.put(EntityType.GUARDIAN, guardianDrops);
		
		///
		
		LinkedList<Drop> elderGuardianDrops = new LinkedList<Drop>();
		elderGuardianDrops.add(new ItemDrop(100, new ItemStack(Material.PRISMARINE_SHARD), 3, 5));
		elderGuardianDrops.add(new ItemDrop(90, new ItemStack(Material.PRISMARINE_CRYSTALS), 1, 3));
		elderGuardianDrops.add(new ItemDrop(100, new ItemStack(Material.KELP), 3, 13));
		elderGuardianDrops.add(new ItemDrop(70, new ItemStack(Material.HEART_OF_THE_SEA), 1, 1));
		elderGuardianDrops.add(new ItemDrop(15, new ItemStack(Material.TRIDENT), 1, 1));
		
		dropMap.put(EntityType.ELDER_GUARDIAN, elderGuardianDrops);
		
		///
		
		LinkedList<Drop> huskDrops = new LinkedList<Drop>();
		huskDrops.add(new ItemDrop(50, new ItemStack(Material.ROTTEN_FLESH), 1, 2));
		huskDrops.add(new MoneyDrop(10, 1, 4));
		
		dropMap.put(EntityType.HUSK, huskDrops);
		
		///
		
		LinkedList<Drop> magmaCubeDrops = new LinkedList<Drop>();
		magmaCubeDrops.add(new ItemDrop(25, new ItemStack(Material.MAGMA_CREAM), 1, 2));
		magmaCubeDrops.add(new ItemDrop(5, new ItemStack(Material.FIRE_CHARGE), 1, 1));
		
		dropMap.put(EntityType.MAGMA_CUBE, magmaCubeDrops);
		
		///
		
		LinkedList<Drop> phantomDrops = new LinkedList<Drop>();
		phantomDrops.add(new ItemDrop(25, new ItemStack(Material.PHANTOM_MEMBRANE), 1, 1));
		phantomDrops.add(new ItemDrop(10, new ItemStack(Material.DIAMOND), 0, 2));
		
		dropMap.put(EntityType.PHANTOM, phantomDrops);
		
		///
		
		LinkedList<Drop> pillagerDrops = new LinkedList<Drop>();
		//pillagerDrops.add(new ItemDrop(100, new ItemStack(Material.CROSSBOW), 1, 1));
		pillagerDrops.add(new ItemDrop(100, new ItemStack(Material.ARROW), 0, 8));
		pillagerDrops.add(new ItemDrop(8, ItemManager.getItemClone(ItemType.UNREAD_RECIPE), 1, 1));
		pillagerDrops.add(new MoneyDrop(100, 8, 24));
		
		dropMap.put(EntityType.PILLAGER, pillagerDrops);
		
		///
		
		LinkedList<Drop> ravagerDrops = new LinkedList<Drop>();
		ravagerDrops.add(new ItemDrop(100, new ItemStack(Material.MUTTON), 5, 9));
		ravagerDrops.add(new ItemDrop(100, new ItemStack(Material.LEATHER), 2, 6));
		ravagerDrops.add(new ItemDrop(100, new ItemStack(Material.SADDLE), 1, 1));
		ravagerDrops.add(new ItemDrop(4, ItemManager.getItemClone(ItemType.UNREAD_RECIPE), 1, 1));
		ravagerDrops.add(new MoneyDrop(100, 23, 45));
		
		dropMap.put(EntityType.RAVAGER, ravagerDrops);
		
		///
		
		LinkedList<Drop> witherSkeletonDrops = new LinkedList<Drop>();
		witherSkeletonDrops.add(new ItemDrop(4, new ItemStack(Material.WITHER_SKELETON_SKULL), 1, 1));
		witherSkeletonDrops.add(new ItemDrop(100, new ItemStack(Material.BONE), 1, 3));
		//witherSkeletonDrops.add(new ItemDrop(100, new ItemStack(Material.STONE_SWORD), 1, 1));
		witherSkeletonDrops.add(new MoneyDrop(100, 8, 14));
		
		dropMap.put(EntityType.WITHER_SKELETON, witherSkeletonDrops);
		
		///
		
		LinkedList<Drop> skeletonDrops = new LinkedList<Drop>();
		skeletonDrops.add(new ItemDrop(4, new ItemStack(Material.SKELETON_SKULL), 1, 1));
		skeletonDrops.add(new ItemDrop(100, new ItemStack(Material.BONE), 2, 4));
		skeletonDrops.add(new ItemDrop(100, new ItemStack(Material.ARROW), 0, 13));
		//skeletonDrops.add(new ItemDrop(100, new ItemStack(Material.BOW), 1, 1));
		skeletonDrops.add(new MoneyDrop(100, 2, 8));
		
		dropMap.put(EntityType.SKELETON, skeletonDrops);
		
		///
		
		LinkedList<Drop> slimeDrops = new LinkedList<Drop>();
		slimeDrops.add(new ItemDrop(100, new ItemStack(Material.SLIME_BALL), 0, 2));
		slimeDrops.add(new MoneyDrop(100, 0, 1));
		
		dropMap.put(EntityType.SLIME, slimeDrops);
		
		/// TO-DO VVV

		LinkedList<Drop> vindicatorDrops = new LinkedList<Drop>();
		vindicatorDrops.add(new ItemDrop(8, ItemManager.getItemClone(ItemType.SECURITY_BATON), 1, 1));
		vindicatorDrops.add(new ItemDrop(5, ItemManager.getItemClone(ItemType.UNREAD_RECIPE), 1, 1));
		//vindicatorDrops.add(new ItemDrop(100, new ItemStack(Material.IRON_AXE), 1, 1));
		vindicatorDrops.add(new MoneyDrop(100, 14, 32));
		
		dropMap.put(EntityType.VINDICATOR, vindicatorDrops);
		
		///
		
		LinkedList<Drop> strayDrops = new LinkedList<Drop>();
		strayDrops.add(new ItemDrop(3, ItemManager.getItemClone(ItemType.UNREAD_RECIPE), 1, 1));
		strayDrops.add(new ItemDrop(100, new ItemStack(Material.BONE), 2, 4));
		strayDrops.add(new ItemDrop(100, new ItemStack(Material.ARROW), 0, 10));
		strayDrops.add(new ItemDrop(20, new ItemStack(Material.COBWEB), 1, 1));
		strayDrops.add(new MoneyDrop(100, 3, 10));
		
		dropMap.put(EntityType.STRAY, strayDrops);
		
		///
		
		LinkedList<Drop> illusionerDrops = new LinkedList<Drop>();
		illusionerDrops.add(new ItemDrop(50, new ItemStack(Material.LAPIS_LAZULI), 2, 9));
		illusionerDrops.add(new ItemDrop(50, new ItemStack(Material.PRISMARINE_SHARD), 1, 3));
		illusionerDrops.add(new ItemDrop(50, new ItemStack(Material.PRISMARINE_CRYSTALS), 1, 3));
		illusionerDrops.add(new ItemDrop(22, ItemManager.getItemClone(ItemType.BOTTLE_OF_APEIRON), 1, 1));
		illusionerDrops.add(new ItemDrop(3, ItemManager.getItemClone(ItemType.WAND_OF_TELEPORTATION), 1, 1));
		illusionerDrops.add(new ItemDrop(3, ItemManager.getItemClone(ItemType.WAND_OF_POLYMORPH), 1, 1));
		illusionerDrops.add(new ItemDrop(56, ItemManager.getItemClone(ItemType.UNREAD_RECIPE), 1, 1));
		illusionerDrops.add(new MoneyDrop(100, 10, 30));
		
		dropMap.put(EntityType.ILLUSIONER, illusionerDrops);
		
		///
		
		LinkedList<Drop> evokerDrops = new LinkedList<Drop>();
		evokerDrops.add(new ItemDrop(50, new ItemStack(Material.LAPIS_LAZULI), 2, 9));
		evokerDrops.add(new ItemDrop(50, new ItemStack(Material.PRISMARINE_SHARD), 1, 3));
		evokerDrops.add(new ItemDrop(50, new ItemStack(Material.PRISMARINE_CRYSTALS), 1, 3));
		evokerDrops.add(new ItemDrop(22, ItemManager.getItemClone(ItemType.BOTTLE_OF_APEIRON), 1, 1));
		evokerDrops.add(new ItemDrop(3, ItemManager.getItemClone(ItemType.WAND_OF_TOXIC_FUMES), 1, 1));
		evokerDrops.add(new ItemDrop(3, ItemManager.getItemClone(ItemType.WAND_OF_FIREBALLS), 1, 1));
		evokerDrops.add(new ItemDrop(56, ItemManager.getItemClone(ItemType.UNREAD_RECIPE), 1, 1));
		evokerDrops.add(new MoneyDrop(100, 10, 30));
		
		dropMap.put(EntityType.EVOKER, evokerDrops);
		
		///
		
		LinkedList<Drop> shulkerDrops = new LinkedList<Drop>();
		shulkerDrops.add(new ItemDrop(1, new ItemStack(Material.SHULKER_SHELL), 1, 1));
		shulkerDrops.add(new MoneyDrop(100, 20, 60));
		
		dropMap.put(EntityType.SHULKER, shulkerDrops);
		
		///
		
		LinkedList<Drop> zombieDrops = new LinkedList<Drop>();
		zombieDrops.add(new ItemDrop(100, new ItemStack(Material.ROTTEN_FLESH), 1, 2));
		zombieDrops.add(new MoneyDrop(100, 0, 4));
		
		dropMap.put(EntityType.ZOMBIE, zombieDrops);
		dropMap.put(EntityType.ZOMBIE_VILLAGER, zombieDrops);
		
		///
		
		LinkedList<Drop> ironGolemDrops = new LinkedList<Drop>();
		ironGolemDrops.add(new ItemDrop(100, new ItemStack(Material.IRON_INGOT), 3, 14));
		ironGolemDrops.add(new ItemDrop(100, new ItemStack(Material.IRON_NUGGET), 0, 32));
		ironGolemDrops.add(new ItemDrop(35, new ItemStack(Material.POPPY), 1, 1));
		
		dropMap.put(EntityType.IRON_GOLEM, ironGolemDrops);
		
		///
		
		LinkedList<Drop> snowGolemDrops = new LinkedList<Drop>();
		snowGolemDrops.add(new ItemDrop(100, new ItemStack(Material.SNOWBALL), 8, 26));
		snowGolemDrops.add(new ItemDrop(100, new ItemStack(Material.STICK), 2, 2));
		snowGolemDrops.add(new ItemDrop(100, new ItemStack(Material.CARVED_PUMPKIN), 1, 1));
		
		dropMap.put(EntityType.SNOWMAN, snowGolemDrops);
		
		///
		
		LinkedList<Drop> witherDrops = new LinkedList<Drop>();
		witherDrops.add(new ItemDrop(100, new ItemStack(Material.NETHER_STAR), 1, 1));
		witherDrops.add(new MoneyDrop(100, 120, 180));
		
		dropMap.put(EntityType.WITHER, witherDrops);
		
		///
		
		
		
	}
	
	public static HashMap<EntityType,LinkedList<Drop>> getDropMap(){
		return dropMap;
	}
	
}
