package com.quzzar.im;

import org.bukkit.plugin.java.JavaPlugin;

import com.quzzar.im.machines.MachineClocks;
import com.quzzar.im.machines.MachineListener;
import com.quzzar.im.machines.MachineRegistrar;
import com.quzzar.im.machines.types.pipe.PipeListener;
import com.quzzar.im.structures.StructureManager;

public class InnovativeMechanics extends JavaPlugin{

	public static InnovativeMechanics instance;
	
	@Override
	public void onEnable(){
		
		instance = this;
		
		if (!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
    	}
		
		getServer().getPluginManager().registerEvents(new ChunkListener(), this);
		getServer().getPluginManager().registerEvents(new MachineListener(), this);
		getServer().getPluginManager().registerEvents(new PipeListener(), this);
		
		StructureManager.load();
		MachineRegistrar.register();
		
		MachineClocks.runClocks();
		
	}
	
	@Override
	public void onDisable(){
		
		StructureManager.despawnAll();
		StructureManager.save();
		
	}
	
}
