package com.quzzar.server.dominions.settings.chunk;

import org.bukkit.Chunk;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionUtils;

public class ChunkSettingsUtils {

	public static boolean confirmChunkIsValid(Chunk chunk, Dominion dominion) {
		
		Dominion chunkDom = DominionUtils.getDominionByChunk(chunk);
		
		if(chunkDom == null || !dominion.equals(chunkDom)) {
			return false;
		}
		
		return true;
		
	}
	
}
