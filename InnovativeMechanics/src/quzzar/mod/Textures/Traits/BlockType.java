package quzzar.mod.Textures.Traits;

public enum BlockType {

	STORAGE,
	GLOBALSTORAGE,
	MACHINE,
	MHU_Large_Dirt_Chest,
	MHU_Pipe,
	MHU_Item_Filter,
	MHU_Incinerator,
	MHU_Miner (true, true),
	MHU_Quarry (true, true),
	MHU_Recycler (true, true),
	MHU_Macerator (true, true),
	MHU_Auto_Crafting_Table (true, true),
	MHU_Industrial_Furance (true, false),
	MHU_Cremator (true, false),
	MHU_Healer,
	CRATE,
	MISC;
	
	
	private Boolean powerable;
	
	private Boolean upgradable;
	
	BlockType(){
		powerable = false;
		upgradable = false;
	}
	
	BlockType(Boolean powerable, Boolean upgradable){
		this.powerable = powerable;
		this.upgradable = upgradable;
	}
	
	public Boolean isPowerable(){
		return powerable;
	}
	
	public Boolean isUpgradable(){
		return upgradable;
	}
	
}
