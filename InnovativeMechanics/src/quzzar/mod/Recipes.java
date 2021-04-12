package quzzar.mod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import com.quzzar.im.versioncontrol.VersionControl;
import com.quzzar.im.versioncontrol.global.material.GMaterial;

import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.documents.CheckConfig;
import quzzar.mod.documents.ConfigManager;
import quzzar.mod.mhu.MHURegistrar;
import quzzar.mod.pluginhelp.items.ItemHelp;
import quzzar.mod.pluginhelp.items.ItemHelpInstance;

public class Recipes implements Listener{
	
	private static ItemStack nothing = new ItemStack(Material.AIR);
	
	
	public static void BookRecipe(Main main){
		if((boolean) CheckConfig.getValue("Enable_Book_of_Innovations_Recipe", true)){
			ShapelessRecipe shless = new ShapelessRecipe(new NamespacedKey(Main.instance,"BookOfInnovationsRec"),ItemsList.Book_of_Innovations(1));
			shless.addIngredient(Material.BOOK);
			shless.addIngredient(Material.IRON_INGOT);
		    main.getServer().addRecipe(shless);
		}
	}
	
	public static void Machines(Main main) {
		
		String name = "Machines";
		
		if((boolean) CheckConfig.getValue(name+".Enable_Recycler", true)){
			
			ItemStack item = ItemsList.Recycler(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"RRecycler"),nothing);
			shR.shape("IBI", "CRC", "ICI");
			shR.setIngredient('R', Material.REDSTONE_BLOCK);
			shR.setIngredient('I', Material.IRON_BLOCK);
			shR.setIngredient('C', VersionControl.getMaterialData(GMaterial.PLAYER_HEAD_ITEM));
			shR.setIngredient('B', VersionControl.getMaterialData(GMaterial.IRON_BARS));
		    main.getServer().addRecipe(shR);
		    
		    HeadUnitShapedRecipe.addNew(shR, TextureDatabase.IRONCHEST, item);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Upgradable Machine",
		    					  "Uses empty Wooden Crates and",
		    					  "recyclable items to make filled",
		    					  "Wooden Crates of different raries",
		    					  "with random contents inside.")));
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Pipes", true)){
			
			ItemStack item = ItemsList.Pipe(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"PPipess1"),item);
			shR.shape("IGI", "IRI", "IGI");
			shR.setIngredient('G', Material.GLASS);
			shR.setIngredient('R', Material.REDSTONE);
			shR.setIngredient('I', Material.IRON_INGOT);
		    main.getServer().addRecipe(shR);
		    
		    ShapedRecipe shR2 = new ShapedRecipe(new NamespacedKey(Main.instance,"PPipess2"),item);
			shR2.shape("III", "GRG", "III");
			shR2.setIngredient('G', Material.GLASS);
			shR2.setIngredient('R', Material.REDSTONE);
			shR2.setIngredient('I', Material.IRON_INGOT);
		    main.getServer().addRecipe(shR2);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name, 		    		
		    		Arrays.asList("Used to transfer items from one ",
		    					  "container to another. To place:",
		    					  "Left Click container to pull from,",
		    					  "Right Click container to pull to,",
		    					  ChatColor.ITALIC+"with Pipe in hand.")));
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Item_Filter", true)){
			
			ItemStack item = ItemsList.Item_Filter(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"IItemFilter"),nothing);
			shR.shape(" C ", "IRI", " I ");
			shR.setIngredient('I', Material.IRON_BLOCK);
			shR.setIngredient('R', Material.REDSTONE);
			shR.setIngredient('C', VersionControl.getMaterialData(GMaterial.PLAYER_HEAD_ITEM));
		    main.getServer().addRecipe(shR);
		    
		    HeadUnitShapedRecipe.addNew(shR, TextureDatabase.DIAMONDCHEST, item);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Will sort items from top Input into",
	    					  	  "one of four containers via filters.",
	    					      "Shift+Right Click on side container",
	    					      "to open filter menu. An empty filter",
	    					      "will accept all items. If no filter will",
	    					      "accept an item, it won't be filtered.")));
		}
	    
		if((boolean) CheckConfig.getValue(name+".Enable_Healer", true)){
			
			ItemStack item = ItemsList.Healer(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"HHealer"),item);
			shR.shape("IAI", "ARA", "IAI");
			shR.setIngredient('R', Material.REDSTONE_BLOCK);
			shR.setIngredient('I', Material.IRON_BLOCK);
			shR.setIngredient('A', Material.GOLDEN_APPLE);
		    main.getServer().addRecipe(shR);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Any player or mob standing next to",
  					  	  		  "the Healer will be given bursts of",
  					  	  		  "Regen IV and Luck")));
		    		
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Cremator", true)){
			
			ItemStack item = ItemsList.Cremator(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"CCremator"),item);
			shR.shape("IFI", "FRF", "IFI");
			shR.setIngredient('I', Material.IRON_BLOCK);
			shR.setIngredient('F', Material.FLINT_AND_STEEL);
			shR.setIngredient('R', Material.REDSTONE_BLOCK);
		    main.getServer().addRecipe(shR);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Will set fire and burn any players",
		    					  "and mobs within a 2-block radius.")));
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Miner", true)){
			
			ItemStack item = ItemsList.Miner(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"MMiner"),item);
			shR.shape("iIi", "IRI", "DDD");
			shR.setIngredient('R', Material.REDSTONE_BLOCK);
			shR.setIngredient('I', Material.IRON_BLOCK);
			shR.setIngredient('i', Material.IRON_INGOT);
			shR.setIngredient('D', Material.DIAMOND_PICKAXE);
		    main.getServer().addRecipe(shR);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Upgradable Machine",
		    					  "Mines a 1x1 hole directly below",
	    					  	  "itself until it reaches Bedrock,",
	    					  	  "Lava, Obsidian, etc.",
	    					  	  "All mined minerals are left in",
	    					  	  "ore form so they may be Macerated.")));
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Quarry", true)){
			
			ItemStack item = ItemsList.Quarry(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"QQuarry"),nothing);
			shR.shape("iIi", "IRI", "MMM");
			shR.setIngredient('R', Material.REDSTONE_BLOCK);
			shR.setIngredient('I', Material.IRON_BLOCK);
			shR.setIngredient('i', Material.IRON_INGOT);
			shR.setIngredient('M', VersionControl.getMaterialData(GMaterial.PLAYER_HEAD_ITEM));
		    main.getServer().addRecipe(shR);
		    
		    HeadUnitShapedRecipe.addNew(shR, TextureDatabase.MINER_item, item);
		    
		    int radius = ConfigManager.getInstance().getConfig().getInt(MHURegistrar.settingName+".Quarry.Mining_Radius")*2+1;
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Upgradable Machine",
		    					  "Mines a "+radius+"x"+radius+" pit directly below",
  					  	  		  "itself until it reaches Bedrock,",
  					  	  		  "Lava, Obsidian, etc.",
  					  	  		  "All mined minerals are left in",
		    				 	  "ore form so they may be Macerated.")));
		    
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Macerator", true)){
			
			ItemStack item = ItemsList.Macerator(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"MMacerator"),nothing);
			shR.shape("IFI", "RGR", "IFI");
			shR.setIngredient('R', Material.REDSTONE);
			shR.setIngredient('I', Material.IRON_BLOCK);
			shR.setIngredient('G', VersionControl.getMaterialData(GMaterial.PLAYER_HEAD_ITEM));
			shR.setIngredient('F', Material.FLINT);
		    main.getServer().addRecipe(shR);
		    
		    HeadUnitShapedRecipe.addNew(shR, TextureDatabase.GRINDING_UNIT, item);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Upgradable Machine",
		    					  "Crushes minerals in ore form",
				  	  		  	  "(Ex. Iron Ore, Coal Ore, etc)",
				  	  		  	  "into chunks. Ore chunks can be",
				  	  		  	  "smelted in an Instrial Furnace",
		    					  "to produce twice the product.")));
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Industrial_Furnace", true)){
			
			ItemStack item = ItemsList.Industrial_Furnace(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"IIndustrialFurnace"),item);
			shR.shape(" I ", "IRI", "BFB");
			shR.setIngredient('R', Material.REDSTONE);
			shR.setIngredient('B', Material.IRON_BLOCK);
			shR.setIngredient('I', Material.IRON_INGOT);
			shR.setIngredient('F', Material.FURNACE);
		    main.getServer().addRecipe(shR);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Smelts all ores and chunks",
		    					  "inside burning chamber. Can",
		    					  "smelt ore chunks and produce",
		    					  "twice the usual product.",
		    					  "(Ex. 1 Iron Chunk = 2 Iron)")));
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Auto_Crafting_Table", true)){
			
			ItemStack item = ItemsList.Auto_Crafting_Table(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"AAutoCraftTable"),nothing);
			shR.shape("IWI", "RGR", "IRI");
			shR.setIngredient('R', Material.REDSTONE);
			shR.setIngredient('I', Material.IRON_BLOCK);
			shR.setIngredient('G', VersionControl.getMaterialData(GMaterial.PLAYER_HEAD_ITEM));
			shR.setIngredient('W', VersionControl.getMaterialData(GMaterial.CRAFTING_TABLE));
		    main.getServer().addRecipe(shR);
		    
		    HeadUnitShapedRecipe.addNew(shR, TextureDatabase.ASSEMBLING_COMPONENT, item);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Upgradable Machine",
		    					  "Place the desired recipe in",
	    					  	  "the Recipe Layout (top table).",
	    					  	  "Will auto-craft recipe if",
	    					  	  "required ingedients are",
		    					  "available (in Input).")));
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Incinerator", true)){
			
			ItemStack item = ItemsList.Incinerator(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"IIncinerator"),item);
			shR.shape("III", "ILI", "III");
			shR.setIngredient('I', Material.IRON_INGOT);
			shR.setIngredient('L', Material.LAVA_BUCKET);
		    main.getServer().addRecipe(shR);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Removes all items placed inside.",
		  	  		  	  	  	  "Can have a Pipe connected to it.")));
		    
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Placer", true)){
			
			ItemStack item = ItemsList.Placer(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"PPlacer"),item);
			shR.shape("IPI", "CRC", "CCC");
			shR.setIngredient('C', Material.STONE);
			shR.setIngredient('I', Material.IRON_INGOT);
			shR.setIngredient('P', VersionControl.getMaterialData(GMaterial.PISTON));
			shR.setIngredient('R', Material.REDSTONE);
		    main.getServer().addRecipe(shR);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Small Machine, Redstone Powered",
  					  	  		  "When powered via Redstone,",
  					  	  		  "will place the first block in",
  					  	  		  "an adjacent chest.")));
		}
	    
		if((boolean) CheckConfig.getValue(name+".Enable_Breaker", true)){
			
			ItemStack item = ItemsList.Breaker(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"BBreaker"),item);
			shR.shape("IPI", "CRC", "CCC");
			shR.setIngredient('C', Material.COBBLESTONE);
			shR.setIngredient('I', Material.IRON_INGOT);
			shR.setIngredient('P', Material.DIAMOND_PICKAXE);
			shR.setIngredient('R', Material.REDSTONE);
		    main.getServer().addRecipe(shR);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Small Machine, Redstone Powered",
				  	  		  	  "When powered via Redstone,",
				  	  		  	  "will break the block in front",
		    					  "of it and place it in an",
		    					  "adjacent chest.",
		    					  "If no chest is available,",
		    					  "will drop on the ground.")));
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Cobblestone_Generator", true)){
			
			ItemStack item = ItemsList.Cobblestone_Generator(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"CCobblestoneGenerator"),item);
			shR.shape("III", "WRL", "III");
			shR.setIngredient('W', Material.WATER_BUCKET);
			shR.setIngredient('I', Material.IRON_INGOT);
			shR.setIngredient('L', Material.LAVA_BUCKET);
			shR.setIngredient('R', Material.REDSTONE);
		    main.getServer().addRecipe(shR);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Small Machine, Redstone Powered",
			  	  		  	  	  "When powered via Redstone,",
			  	  		  	  	  "will slowly begin producing",
			  	  		  	  	  "Cobblestone and place it in",
			  	  		  	  	  "an adjacent chest.",
			  	  		  	  	  "If no chest is available,",
		    					  "will drop on the ground.")));
		}
	    
	    
    }
	
	public static void MiniChests(Main main){
		
		String name = "Mini_Chests";
		
		String settingsName = "Mini_Chests_Settings";
		
		if((boolean) CheckConfig.getValue(name+".Enable_MiniChest", true)){
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"MiniChest"),ItemsList.MiniChest(1));
			shR.shape("SSS", "S S", "SSS");
			shR.setIngredient('S', VersionControl.getMaterialData(GMaterial.OAK_SLAB));
		    main.getServer().addRecipe(shR);
		    
		    ShapedRecipe shR2 = new ShapedRecipe(new NamespacedKey(Main.instance,"MiniChest2s"),ItemsList.MiniChest(16));
			shR2.shape("SSS", "S S", "SSS");
			shR2.setIngredient('S', Material.CHEST);
		    main.getServer().addRecipe(shR2);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(ItemsList.MiniChest(1), name,
		    		Arrays.asList("Small Wooden Chest",
		  	  		  		"Has 18 storage slots.")));
		}
		
		
		if((boolean) CheckConfig.getValue(name+".Enable_DirtChest", true)){
			
			ItemStack item = ItemsList.DirtChest(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"DirtChest"),item);
			shR.shape("SSS", "S S", "SSS");
			shR.setIngredient('S', Material.DIRT);
		    main.getServer().addRecipe(shR);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Small Dirt Chest",
		  	  		  		"Has 5 storage slots.")));
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_IronChest", true)){
			
			ItemStack item = ItemsList.IronChest(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"IronChest"),item);
			shR.shape("SSS", "SPS", "SSS");
			shR.setIngredient('S', Material.IRON_INGOT);
			shR.setIngredient('P', Material.CHEST);
		    main.getServer().addRecipe(shR);
			
		    ShapedRecipe shR2 = new ShapedRecipe(new NamespacedKey(Main.instance,"IronChest2"),nothing);
			shR2.shape("SSS", "SMS", "SSS");
			shR2.setIngredient('S', Material.IRON_INGOT);
			shR2.setIngredient('M', VersionControl.getMaterialData(GMaterial.PLAYER_HEAD_ITEM));
		    main.getServer().addRecipe(shR2);
		    HeadUnitShapedRecipe.addNew(shR2, TextureDatabase.MINICHEST, item);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Small Iron Chest",
		  	  		  		"Has 36 storage slots.")));
			
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_GoldChest", true)){
			
			ItemStack item = ItemsList.GoldChest(1);
			
			if(!(boolean) CheckConfig.getValue(settingsName+".Use_Progressional_Recipes", true)){
				ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"GoldChest"),item);
				shR.shape("SSS", "SPS", "SSS");
				shR.setIngredient('S', Material.GOLD_INGOT);
				shR.setIngredient('P', Material.CHEST);
			    main.getServer().addRecipe(shR);
			}else{
			    ShapedRecipe shR2 = new ShapedRecipe(new NamespacedKey(Main.instance,"GoldChest2"),nothing);
				shR2.shape("SSS", "SMS", "SSS");
				shR2.setIngredient('S', Material.GOLD_INGOT);
				shR2.setIngredient('M', VersionControl.getMaterialData(GMaterial.PLAYER_HEAD_ITEM));
			    main.getServer().addRecipe(shR2);
			    HeadUnitShapedRecipe.addNew(shR2, TextureDatabase.IRONCHEST, item);
			}
			
			ItemHelp.addInstance(new ItemHelpInstance(item, name,
					Arrays.asList("Small Gold Chest",
		  	  		  		"Has 45 storage slots.")));
			
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_QuartzChest", true)){
			
			ItemStack item = ItemsList.QuartzChest(1);
			
			if(!(boolean) CheckConfig.getValue(settingsName+".Use_Progressional_Recipes", true)){
				ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"QuartzChest"),item);
				shR.shape("SSS", "SPS", "SSS");
				shR.setIngredient('S', Material.QUARTZ);
				shR.setIngredient('P', Material.CHEST);
			    main.getServer().addRecipe(shR);
			}else{
				ShapedRecipe shR2 = new ShapedRecipe(new NamespacedKey(Main.instance,"QuartzChest2"),nothing);
				shR2.shape("SSS", "SMS", "SSS");
				shR2.setIngredient('S', Material.QUARTZ);
				shR2.setIngredient('M', VersionControl.getMaterialData(GMaterial.PLAYER_HEAD_ITEM));
			    main.getServer().addRecipe(shR2);
			    HeadUnitShapedRecipe.addNew(shR2, TextureDatabase.IRONCHEST, item);
			}
			
			ItemHelp.addInstance(new ItemHelpInstance(item, name,
					Arrays.asList("Small Quartz Chest",
		  	  		  		"Has 45 storage slots.")));
			
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_EmeraldChest", true)){
			
			ItemStack item = ItemsList.EmeraldChest(1);
			
			if(!(boolean) CheckConfig.getValue(settingsName+".Use_Progressional_Recipes", true)){
				ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"EmerChest"),item);
				shR.shape("SSS", "SPS", "SSS");
				shR.setIngredient('S', Material.EMERALD);
				shR.setIngredient('P', Material.CHEST);
			    main.getServer().addRecipe(shR);
			}else{
				ShapedRecipe shR2 = new ShapedRecipe(new NamespacedKey(Main.instance,"EmerChest2"),nothing);
				shR2.shape("SSS", "SMS", "SSS");
				shR2.setIngredient('S', Material.EMERALD);
				shR2.setIngredient('M', VersionControl.getMaterialData(GMaterial.PLAYER_HEAD_ITEM));
			    main.getServer().addRecipe(shR2);
			    HeadUnitShapedRecipe.addNew(shR2, TextureDatabase.QUARTZCHEST, item);
			    
			    HeadUnitShapedRecipe.addNew(shR2, TextureDatabase.GOLDCHEST, item);
			}
			
			ItemHelp.addInstance(new ItemHelpInstance(item, name,
					Arrays.asList("Small Emerald Chest",
		  	  		  		"Has 54 storage slots.")));
			
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_DiamondChest", true)){
			
			ItemStack item = ItemsList.DiamondChest(1);
			
			if(!(boolean) CheckConfig.getValue(settingsName+".Use_Progressional_Recipes", true)){
				ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"DiaChest"),item);
				shR.shape("SSS", "SPS", "SSS");
				shR.setIngredient('S', Material.DIAMOND);
				shR.setIngredient('P', Material.CHEST);
			    main.getServer().addRecipe(shR);
			}else{
			    ShapedRecipe shR2 = new ShapedRecipe(new NamespacedKey(Main.instance,"DiaChest2"),nothing);
				shR2.shape("SSS", "SMS", "SSS");
				shR2.setIngredient('S', Material.DIAMOND);
				shR2.setIngredient('M', VersionControl.getMaterialData(GMaterial.PLAYER_HEAD_ITEM));
			    main.getServer().addRecipe(shR2);
			    HeadUnitShapedRecipe.addNew(shR2, TextureDatabase.QUARTZCHEST, item);
			    
			    HeadUnitShapedRecipe.addNew(shR2, TextureDatabase.GOLDCHEST, item);
			}
			
			ItemHelp.addInstance(new ItemHelpInstance(item, name,
					Arrays.asList("Small Diamond Chest",
		  	  		  		"Has 54 storage slots.")));
			
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_InterdimensionalChest", true)){
			
			ItemStack item = ItemsList.InterDChest(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"InterDimChest"),item);
			shR.shape("CDC", "DED", "CDC");
			shR.setIngredient('C', Material.OBSIDIAN);
			shR.setIngredient('E', Material.ENDER_CHEST);
			shR.setIngredient('D', Material.DIAMOND_BLOCK);
		    main.getServer().addRecipe(shR);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Small Interdimensional Chest",
		  	  		  			  "Has 99 storage slots.",
		  	  		  			  "Functions the same as an",
		  	  		  			  "Ender Chest.",
		  	  		  			  "(Personal Player Storage)")));
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_LargeDirtChest", true)){
			
			ItemStack item = ItemsList.LDirtChest(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"LargeDirtChest"),nothing);
			shR.shape("SSS", "S S", "SSS");
			shR.setIngredient('S', VersionControl.getMaterialData(GMaterial.PLAYER_HEAD_ITEM));
		    main.getServer().addRecipe(shR);
		    
		    HeadUnitShapedRecipe.addNew(shR, TextureDatabase.DIRTCHEST, item);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Large Dirt Chest",
		  	  		  		"Has 36 storage slots.",
		  	  		  		"May excrete dirt.")));
		}
		
		
	}
	
	
	public static void BlockCompression(Main main){
		
		String name = "Block_Compression";
		
		if((boolean) CheckConfig.getValue(name+".Enable_Dirt_Compression", true)){
			
			ItemStack item = ItemsList.Compressed_Dirt(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"CompDirt"),item);
			shR.shape("BBB", "BBB", "BBB");
			shR.setIngredient('B', Material.DIRT);
		    main.getServer().addRecipe(shR);
		    
		    
		    HeadUnitShapedRecipe.addNew(HURecipeLayouts.shapedFull, TextureDatabase.COMPRESSED_DIRT, new ItemStack(Material.DIRT,27));
		    
		    HeadUnitShapelessRecipe.addNew(HURecipeLayouts.shlessSingle, TextureDatabase.COMPRESSED_DIRT, new ItemStack(Material.DIRT,9));
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name, Arrays.asList("Equivalent to 9 Dirt")));
		    
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Cobble_Compression", true)){
			
			ItemStack item1 = ItemsList.Compressed_Cobble(1);
			
			ItemStack item2 = ItemsList.Extremely_Compressed_Cobble(1);
			
			ItemStack item3 = ItemsList.Insanely_Compressed_Cobble(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"CompCobble"),item1);
			shR.shape("BBB", "BBB", "BBB");
			shR.setIngredient('B', Material.COBBLESTONE);
		    main.getServer().addRecipe(shR);
		    
		    HeadUnitShapelessRecipe.addNew(HURecipeLayouts.shlessSingle, TextureDatabase.COMPRESSED_COBBLE, new ItemStack(Material.COBBLESTONE,9));
		    
		    
		    HeadUnitShapedRecipe.addNew(HURecipeLayouts.shapedFull, TextureDatabase.COMPRESSED_COBBLE, ItemsList.Extremely_Compressed_Cobble(1));
		    
		    HeadUnitShapelessRecipe.addNew(HURecipeLayouts.shlessSingle, TextureDatabase.EXTREMELY_COMPRESSED_COBBLE, ItemsList.Compressed_Cobble(9));
		    
		    
		    HeadUnitShapedRecipe.addNew(HURecipeLayouts.shapedFull, TextureDatabase.EXTREMELY_COMPRESSED_COBBLE, ItemsList.Insanely_Compressed_Cobble(1));
		    
		    HeadUnitShapelessRecipe.addNew(HURecipeLayouts.shlessSingle, TextureDatabase.INSANELY_COMPRESSED_COBBLE, ItemsList.Extremely_Compressed_Cobble(9));
		    
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item1, name, Arrays.asList("Equivalent to 9 Cobblestone")));
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item2, name, Arrays.asList("Equivalent to 81 Cobblestone")));
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item3, name, Arrays.asList("Equivalent to 729 Cobblestone")));
		    
		}
		
		//
		
		if((boolean) CheckConfig.getValue(name+".Enable_Diorite_Compression", true)){
			
			ItemStack item = ItemsList.Compressed_Diorite(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"CompDiorite"),item);
			shR.shape("BBB", "BBB", "BBB");
			shR.setIngredient('B', VersionControl.getMaterialData(GMaterial.DIORITE));
		    main.getServer().addRecipe(shR);
		    
		    
		    HeadUnitShapelessRecipe.addNew(HURecipeLayouts.shlessSingle, TextureDatabase.COMPRESSED_DIORITE, new ItemStack(Material.STONE,9,(short) 3));
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name, Arrays.asList("Equivalent to 9 Diorite")));
		    
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Granite_Compression", true)){
			
			ItemStack item = ItemsList.Compressed_Granite(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"CompGranite"),item);
			shR.shape("BBB", "BBB", "BBB");
			shR.setIngredient('B', VersionControl.getMaterialData(GMaterial.GRANITE));
		    main.getServer().addRecipe(shR);
		    
		    
		    HeadUnitShapelessRecipe.addNew(HURecipeLayouts.shlessSingle, TextureDatabase.COMPRESSED_GRANITE, new ItemStack(Material.STONE,9,(short) 1));
		    
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name, Arrays.asList("Equivalent to 9 Granite")));
		    
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Andesite_Compression", true)){
			
			ItemStack item = ItemsList.Compressed_Andesite(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"CompAndesite"),item);
			shR.shape("BBB", "BBB", "BBB");
			shR.setIngredient('B', VersionControl.getMaterialData(GMaterial.ANDESITE));
		    main.getServer().addRecipe(shR);
		    
		    
		    HeadUnitShapelessRecipe.addNew(HURecipeLayouts.shlessSingle, TextureDatabase.COMPRESSED_ANDESITE, new ItemStack(Material.STONE,9,(short) 5));
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name, Arrays.asList("Equivalent to 9 Andesite")));
		    
		}
		
		//
		
		if((boolean) CheckConfig.getValue(name+".Enable_Netherrack_Compression", true)){
			
			ItemStack item = ItemsList.Compressed_Netherrack(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"CompNetherrack"),item);
			shR.shape("BBB", "BBB", "BBB");
			shR.setIngredient('B', Material.NETHERRACK);
		    main.getServer().addRecipe(shR);
		    
		    
		    HeadUnitShapelessRecipe.addNew(HURecipeLayouts.shlessSingle, TextureDatabase.COMPRESSED_NETHERRACK, new ItemStack(Material.NETHERRACK,9));
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name, Arrays.asList("Equivalent to 9 Netherrack")));
		    
		}
		
		
		if((boolean) CheckConfig.getValue(name+".Enable_The_Dark_Gateway", false)){
			
			ItemStack item = ItemsList.The_Dark_Gateway(1);
			
			HeadUnitShapedRecipe.addNew(HURecipeLayouts.shapedFull, TextureDatabase.INSANELY_COMPRESSED_COBBLE, item);
			
			ItemHelp.addInstance(new ItemHelpInstance(item, name, 
					Arrays.asList("A portal to a mysterious world.",
								  "Right click on the Gateway to enter.")));
			
		}
		
		
	}
	
	
	public static void Utility(Main main) {
		
		String name = "Utility";
		
		if((boolean) CheckConfig.getValue(name+".Enable_Wrench", true)){
			
			ItemStack item = ItemsList.Wrench(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"WWrenchIM"),item);
			shR.shape(" I ", "I I", " S ");
			shR.setIngredient('S', Material.STICK);
			shR.setIngredient('I', Material.IRON_INGOT);
		    main.getServer().addRecipe(shR);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Right click on a large machine",
							      "to break it instantly!")));
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Portable_Crafting_Table", true)){
			
			ItemStack item = ItemsList.Portable_Crafting_Table(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"PPCraftTable"),item);
			
			shR.shape(" I ", "IWI", " I ");
			shR.setIngredient('W', VersionControl.getMaterialData(GMaterial.CRAFTING_TABLE));
			shR.setIngredient('I', Material.IRON_INGOT);
		    main.getServer().addRecipe(shR);
			
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Right click with Portable",
						      "Crafting Table in hand to open.",
						      "Also works if in off-hand.")));
			
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Aetherium_Cube", true)){
			
			ItemStack item = ItemsList.Aetherium_Cube(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"AAetheriumCube"),item);
			
			shR.shape("IOI", "QFQ", "IOI");
			shR.setIngredient('O', Material.OBSIDIAN);
			shR.setIngredient('F', Material.FEATHER);
			shR.setIngredient('Q', Material.QUARTZ);
			shR.setIngredient('I', Material.IRON_INGOT);
		    main.getServer().addRecipe(shR);
			
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Can be placed anywhere!",
						      "(Does not need to be placed",
						      "against another block)")));
			
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Recipe_Viewer", true)){
			
			ItemStack item = ItemsList.Recipe_Viewer(1);
			
			ShapelessRecipe shless = new ShapelessRecipe(new NamespacedKey(Main.instance,"RRecipeViewer"),item);
			
			shless.addIngredient(VersionControl.getMaterialData(GMaterial.CRAFTING_TABLE));
			shless.addIngredient(Material.IRON_INGOT);
			shless.addIngredient(Material.GLASS);
		    main.getServer().addRecipe(shless);
			
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Right click in hand to open.",
						      "Place item in slot to discover",
						      "the item's recipe!")));
			
		}
		
	}
	
	
	public static void Miscellaneous(Main main){
		
		/*
		// For Slab Recipe
		ShapedRecipe shRSlab = new ShapedRecipe(new NamespacedKey(Main.instance,"SSlabWoodLess"),new ItemStack(Material.WOOD_STEP, 6));
		shRSlab.shape("W", "W", "W");
		shRSlab.setIngredient('W', Material.WOOD, 32767);
	    main.getServer().addRecipe(shRSlab);
	    //
	    */
	    
	    String name = "Miscellaneous";
	    
	    
		if((boolean) CheckConfig.getValue(name+".Enable_Assembling_Component", true)){
			
			ItemStack item = ItemsList.Assembling_Component(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"AAsComponent"),item);
			shR.shape("IPI", "IRI", "IPI");
			shR.setIngredient('R', Material.REDSTONE);
			shR.setIngredient('I', Material.IRON_INGOT);
			shR.setIngredient('P', VersionControl.getMaterialData(GMaterial.PISTON));
		    main.getServer().addRecipe(shR);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Used to craft Automatic",
						      	  "Crafting Table.")));
		    
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Grinding_Unit", true)){
			
			ItemStack item = ItemsList.Grinding_Unit(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"GGUnitt"),item);
			shR.shape("IFI", "FRF", "IFI");
			shR.setIngredient('R', Material.REDSTONE);
			shR.setIngredient('I', Material.IRON_INGOT);
			shR.setIngredient('F', Material.FLINT);
		    main.getServer().addRecipe(shR);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Used to craft Recycler.")));
		}
	    
		
		if((boolean) CheckConfig.getValue(name+".Enable_Wooden_Crates", true)){
			
			ItemStack item = ItemsList.Wooden_Crate_Empty(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"WoodenCrateEmpty"),item);
			
			if((boolean) CheckConfig.getValue("Empty_Crate.Hard_Recipe", false)){
				
				shR.shape("IPI", "P P", "IPI");
				shR.setIngredient('P', VersionControl.getMaterialData(GMaterial.OAK_SLAB));
				shR.setIngredient('I', Material.IRON_INGOT);
			    main.getServer().addRecipe(shR);
				
			}else{
				
				shR.shape("IPI", "P P", "IPI");
				shR.setIngredient('P', VersionControl.getMaterialData(GMaterial.OAK_SLAB));
				shR.setIngredient('I', Material.IRON_NUGGET);
			    main.getServer().addRecipe(shR);
				
			}
			
			ItemHelp.addInstance(new ItemHelpInstance(item, name,
					Arrays.asList("Empty. Used as input in Recycler",
								  "to be filled with items.",
								  "Recycler creates Wooden Crate",
								  "(Unknown)'s.")));
			
		}
		
		
		if((boolean) CheckConfig.getValue(name+".Enable_Speed_Upgrade", true)){
			
			ItemStack item = ItemsList.Speed_Upgrade(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"SSpeedUpgrade"),item);
			shR.shape("IrI", "rRr", "III");
			shR.setIngredient('R', VersionControl.getMaterialData(GMaterial.REDSTONE_TORCH));
			shR.setIngredient('r', Material.REDSTONE);
			shR.setIngredient('I', Material.GOLD_INGOT);
		    main.getServer().addRecipe(shR);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Machine Upgrade. Shift+Right Click",
							  	  "on upgradable machines to open",
		    					  "their Upgrade Panel.",
		    					  "Increases machine's processing",
		    					  "speed. Does not stack.")));
		    
		}
		
		if((boolean) CheckConfig.getValue(name+".Enable_Storage_Upgrade", true)){
			
			ItemStack item = ItemsList.Storage_Upgrade(1);
			
			ShapedRecipe shR = new ShapedRecipe(new NamespacedKey(Main.instance,"SStorageUpgrade"),item);
			shR.shape("III", "rCr", "III");
			shR.setIngredient('C', Material.CHEST);
			shR.setIngredient('r', Material.REDSTONE);
			shR.setIngredient('I', Material.IRON_INGOT);
		    main.getServer().addRecipe(shR);
		    
		    ItemHelp.addInstance(new ItemHelpInstance(item, name,
		    		Arrays.asList("Machine Upgrade. Shift+Right Click",
						  	  	  "on upgradable machines to open",
						  	  	  "their Upgrade Panel.",
						  	  	  "Increases machine's storage",
		    					  "size. (Will empty all existing",
		    					  "contents when size is changed)",
		    					  "Does not stack.")));
		    
		}
		
		
		///
		
		ItemHelp.addInstance(new ItemHelpInstance(ItemsList.Coal_Chunks(1), name));
		ItemHelp.addInstance(new ItemHelpInstance(ItemsList.Iron_Chunks(1), name));
		ItemHelp.addInstance(new ItemHelpInstance(ItemsList.Redstone_Chunks(1), name));
		ItemHelp.addInstance(new ItemHelpInstance(ItemsList.Lapis_Chunks(1), name));
		ItemHelp.addInstance(new ItemHelpInstance(ItemsList.Gold_Chunks(1), name));
		ItemHelp.addInstance(new ItemHelpInstance(ItemsList.Emerald_Chunks(1), name));
		ItemHelp.addInstance(new ItemHelpInstance(ItemsList.Diamond_Chunks(1), name));
		
		ItemHelp.addInstance(new ItemHelpInstance(ItemsList.Wooden_Crate_Common(1), name,
				Arrays.asList("Produced as product from Recycler",
				  	  	  	  "Right click in hand to break open.",
				  	  	  	  "Contains random items inside.",
				  	  	  	  "Has different raries.",
				  	  	  	  "After opening, may leave empty",
				  	  	  	  "Wooden Crate behind.")));
		
	}
	
	
	
	@EventHandler
    public void onPlayerCraft(PrepareItemCraftEvent event) {
		
		if(event.getRecipe()!=null){
			
			
			if(event.getRecipe() instanceof ShapedRecipe){
				
				for(HeadUnitShapedRecipe HUr: HeadUnitShapedRecipe.list){
					
					
					if(HUr.getRecipe().getKey().equals(((ShapedRecipe)event.getRecipe()).getKey())){
						
						boolean passing = false;
						
						List<ItemStack> currentstack = Arrays.asList(clean(event.getInventory().getMatrix()));
						
						checker:
						for(ItemStack is : currentstack){
							if(VersionControl.areSameMaterial(is, GMaterial.PLAYER_HEAD_ITEM)){
								
								if(Utility.checkMechanical(is)){
									if(Utility.getTexture(is).equals(HUr.getTexture())){
										
										
										passing = true;
										
										
									}else{
										
										passing = false;
										break checker;
									}
								}else{
									passing = false;
									break checker;
								}
								
								
							}
						}
						
						
						if(passing){
							event.getInventory().setResult(HUr.getResult());
						}
						
						
					}
				}
				
			}else if (event.getRecipe() instanceof ShapelessRecipe){
				
				for(HeadUnitShapelessRecipe HUr : HeadUnitShapelessRecipe.list){
					
					
					
					if(HUr.getRecipe().getKey().equals(((ShapelessRecipe)event.getRecipe()).getKey())){
						
						boolean passing = false;
						
						List<ItemStack> currentstack = Arrays.asList(clean(event.getInventory().getMatrix()));
						
						checker:
						for(ItemStack is : currentstack){
							if(VersionControl.areSameMaterial(is, GMaterial.PLAYER_HEAD_ITEM)){
								
								if(Utility.checkMechanical(is)){
									if(Utility.getTexture(is).equals(HUr.getTexture())){
										
										passing = true;
										
									}else{
										passing = false;
										break checker;
									}
								}else{
									passing = false;
									break checker;
								}
								
								
							}
						}
						
						
						if(passing){
							event.getInventory().setResult(HUr.getResult());
						}
						
						
					}
				}
				
			}
			
			
			
		}
	}
	
	
	public static ItemStack[] clean(final ItemStack[] v) {
	    List<ItemStack> list = new ArrayList<ItemStack>(Arrays.asList(v));
	    list.removeAll(Collections.singleton(null));
	    return list.toArray(new ItemStack[list.size()]);
	}
	
	
	
	
	
}
