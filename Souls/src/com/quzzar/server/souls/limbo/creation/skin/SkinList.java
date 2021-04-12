package com.quzzar.server.souls.limbo.creation.skin;

import java.util.Arrays;
import java.util.List;

public class SkinList {
	
	private static List<String> skinList = Arrays.asList();
	
	public static List<String> getSkinList(){
		return skinList;
	}
	
	public static String getSkin(int index) {
		return skinList.get(index);
	}
	
}
