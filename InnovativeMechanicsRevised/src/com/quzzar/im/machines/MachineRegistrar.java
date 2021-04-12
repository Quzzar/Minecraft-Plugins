package com.quzzar.im.machines;

import java.util.Hashtable;

import com.quzzar.im.machines.types.AutoCraftingTable;
import com.quzzar.im.machines.types.Healer;
import com.quzzar.im.machines.types.IndustrialFurnace;
import com.quzzar.im.machines.types.Macerator;
import com.quzzar.im.machines.types.Miner;
import com.quzzar.im.machines.types.Pipe;
import com.quzzar.im.machines.types.Quarry;
import com.quzzar.im.textures.TextureCollection;
import com.quzzar.im.textures.TextureDatabase;

public class MachineRegistrar {
	
	public static Hashtable<MachineType, Machine> machines = new Hashtable<MachineType, Machine>();
	
	public static void register(){
		
		machines.clear();
		
		machines.put(MachineType.AUTO_CRAFTING_TABLE,
				new AutoCraftingTable(MachineType.AUTO_CRAFTING_TABLE, TextureDatabase.AUTO_CRAFTING_TABLE_item,
						TextureCollection.AUTO_CRAFTING_TABLE, TextureCollection.AUTO_CRAFTING_TABLE_ON));
		machines.put(MachineType.HEALER,
				new Healer(MachineType.HEALER, TextureDatabase.HEALER_item,
						TextureCollection.HEALER, TextureCollection.HEALER));
		machines.put(MachineType.MACERATOR,
				new Macerator(MachineType.MACERATOR, TextureDatabase.MACERATOR_item,
						TextureCollection.MACERATOR, TextureCollection.MACERATOR_ON));
		machines.put(MachineType.INDUSTRIAL_FURNACE,
				new IndustrialFurnace(MachineType.INDUSTRIAL_FURNACE, TextureDatabase.INDUSTRIAL_FURNACE_item,
						TextureCollection.INDUSTRIAL_FURNACE, TextureCollection.INDUSTRIAL_FURNACE_ON));
		machines.put(MachineType.MINER,
				new Miner(MachineType.MINER, TextureDatabase.MINER_item,
						TextureCollection.MINER, TextureCollection.MINER_ON));
		machines.put(MachineType.QUARRY,
				new Quarry(MachineType.QUARRY, TextureDatabase.QUARRY_item,
						TextureCollection.QUARRY, TextureCollection.QUARRY_ON));
		machines.put(MachineType.PIPE,
				new Pipe(MachineType.PIPE, TextureDatabase.PIPE_item,
						TextureCollection.PIPE, TextureCollection.PIPE));
		
	}
	
	
	
	public static Machine getMachine(MachineType type) {
		return machines.get(type);
	}
	
	
}
