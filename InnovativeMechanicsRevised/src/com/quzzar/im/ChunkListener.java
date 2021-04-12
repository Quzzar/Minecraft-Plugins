package com.quzzar.im;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import com.quzzar.im.structures.StructureManager;

public class ChunkListener implements Listener {

	@EventHandler
	public void onChunkLoad(ChunkLoadEvent e) {
		StructureManager.loadInChunk(e.getChunk());
	}
	
	@EventHandler
	public void onChunkUnload(ChunkUnloadEvent e) {
		StructureManager.unloadInChunk(e.getChunk());
	}
	
}
