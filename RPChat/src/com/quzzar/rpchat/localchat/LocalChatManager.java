package com.quzzar.rpchat.localchat;

import com.quzzar.rpchat.RPChat;

public class LocalChatManager {

	public static boolean randomForMissing;
	
	private ChatProcessor normalProcessor;
	private ChatProcessor shoutProcessor;
	private ChatProcessor whisperProcessor;
	
	public LocalChatManager() {
		
		randomForMissing = RPChat.getMainConfig().getBoolean("UseMagicConcealment");
		
		normalProcessor = new ChatProcessor(RPChat.getMainConfig().getString("NormalPrefix"),
				RPChat.getMainConfig().getDouble("NormalChatRadius"), RPChat.getMainConfig().getDouble("NormalConcealRange"));
		whisperProcessor = new ChatProcessor(RPChat.getMainConfig().getString("WhisperPrefix"),
				RPChat.getMainConfig().getDouble("WhisperChatRadius"), RPChat.getMainConfig().getDouble("WhisperConcealRange"));
		shoutProcessor = new ChatProcessor(RPChat.getMainConfig().getString("ShoutPrefix"),
				RPChat.getMainConfig().getDouble("ShoutChatRadius"), RPChat.getMainConfig().getDouble("ShoutConcealRange"));
		
	}

	public ChatProcessor getShoutProcessor() {
		return shoutProcessor;
	}
	
	public ChatProcessor getNormalProcessor() {
		return normalProcessor;
	}
	
	public ChatProcessor getWhisperProcessor() {
		return whisperProcessor;
	}
	
}
