package com.quzzar.rpchat.radio;

import java.io.Serializable;

public class Radio implements Serializable{

	private static final long serialVersionUID = -5793266729109328034L;

	private String serializedMatrix;
	private String channelPrefix;
	
	public Radio(String serializedMatrix) {
		
		this.serializedMatrix = serializedMatrix;
		this.channelPrefix = RadioUtilities.getRandomChannelPrefix();
		
	}

	public String getSerializedMatrix() {
		return serializedMatrix;
	}

	public String getChannelPrefix() {
		return channelPrefix;
	}
	
}
