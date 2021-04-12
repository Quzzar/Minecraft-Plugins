package quzzar.mod.hu;

import java.util.ArrayList;

import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.hu.types.Breaker;
import quzzar.mod.hu.types.Cobblestone_Generator;
import quzzar.mod.hu.types.Placer;

public class HURegistrar {
	
	public static ArrayList<SmallMachineUnit> typesList = new ArrayList<SmallMachineUnit>();
	
	public static void register(){
		
		typesList.clear();
		
		registerHUType(new Placer(BlockType.MACHINE, TextureDatabase.PLACER));
		registerHUType(new Breaker(BlockType.MACHINE, TextureDatabase.BREAKER));
		registerHUType(new Cobblestone_Generator(BlockType.MACHINE, TextureDatabase.COBBLESTONE_GENERATOR));
		
	}
	
	
	
	
	private static void registerHUType(SmallMachineUnit machine){
		
		typesList.add(machine);
		
	}
	
	
}
