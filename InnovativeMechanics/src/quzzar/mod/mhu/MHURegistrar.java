package quzzar.mod.mhu;

import java.util.ArrayList;

import quzzar.mod.Textures.TextureCollection;
import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.mhu.types.Auto_Crafting_Table;
import quzzar.mod.mhu.types.Cremator;
import quzzar.mod.mhu.types.Healer;
import quzzar.mod.mhu.types.Incinerator;
import quzzar.mod.mhu.types.Industrial_Furnace;
import quzzar.mod.mhu.types.Item_Filter;
import quzzar.mod.mhu.types.Large_Dirt_Chest;
import quzzar.mod.mhu.types.Macerator;
import quzzar.mod.mhu.types.Miner;
import quzzar.mod.mhu.types.Pipe_Unit;
import quzzar.mod.mhu.types.Quarry;
import quzzar.mod.mhu.types.Recycler;

public class MHURegistrar {
	
	public static ArrayList<LargeMachineUnit> typesList = new ArrayList<LargeMachineUnit>();
	
	public static String settingName = "Machine_Settings";
	
	public static void register(){
		
		typesList.clear();
		
		registerMHUType(new Large_Dirt_Chest(BlockType.MHU_Large_Dirt_Chest,TextureDatabase.L_DIRT_CHEST_item, TextureCollection.LARGE_DIRT_CHEST, TextureCollection.LARGE_DIRT_CHEST));
		registerMHUType(new Item_Filter(BlockType.MHU_Item_Filter,TextureDatabase.ITEM_FILTER_item, TextureCollection.ITEM_FILTER, TextureCollection.ITEM_FILTER));
		registerMHUType(new Recycler(BlockType.MHU_Recycler,TextureDatabase.RECYCLER_item, TextureCollection.RECYCLER, TextureCollection.RECYCLER_ON));
		registerMHUType(new Healer(BlockType.MHU_Healer,TextureDatabase.HEALER_item, TextureCollection.HEALER, TextureCollection.HEALER));
		registerMHUType(new Macerator(BlockType.MHU_Macerator,TextureDatabase.MACERATOR_item, TextureCollection.MACERATOR, TextureCollection.MACERATOR_ON));
		registerMHUType(new Miner(BlockType.MHU_Miner,TextureDatabase.MINER_item, TextureCollection.MINER, TextureCollection.MINER_ON));
		registerMHUType(new Quarry(BlockType.MHU_Quarry,TextureDatabase.QUARRY_item, TextureCollection.QUARRY, TextureCollection.QUARRY_ON));
		registerMHUType(new Auto_Crafting_Table(BlockType.MHU_Auto_Crafting_Table,TextureDatabase.AUTO_CRAFTING_TABLE_item, TextureCollection.AUTO_CRAFTING_TABLE, TextureCollection.AUTO_CRAFTING_TABLE_ON));
		registerMHUType(new Pipe_Unit(BlockType.MHU_Pipe,TextureDatabase.PIPE_item, TextureCollection.PIPE, TextureCollection.PIPE));
		registerMHUType(new Industrial_Furnace(BlockType.MHU_Industrial_Furance,TextureDatabase.INDUSTRIAL_FURNACE_item, TextureCollection.INDUSTRIAL_FURNACE, TextureCollection.INDUSTRIAL_FURNACE_ON));
		registerMHUType(new Incinerator(BlockType.MHU_Incinerator,TextureDatabase.INCINERATOR_item, TextureCollection.INCINERATOR, TextureCollection.INCINERATOR));
		registerMHUType(new Cremator(BlockType.MHU_Cremator,TextureDatabase.CREMATOR_item, TextureCollection.CREMATOR, TextureCollection.CREMATOR_ON));
		
	}
	
	
	
	
	private static void registerMHUType(LargeMachineUnit machine){
		
		typesList.add(machine);
		
	}
	
	
}
