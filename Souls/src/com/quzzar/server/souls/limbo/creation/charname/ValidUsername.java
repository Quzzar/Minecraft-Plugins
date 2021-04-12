package com.quzzar.server.souls.limbo.creation.charname;

import java.util.Arrays;
import java.util.List;

public class ValidUsername {
	
	public static List<String> blackListNames = Arrays.asList(
			"fuck", " the ", "shit", " ass", " dick", " ho ", " big ", "sexy", "milf", "bitch",
			"igger", "damn", " cunt", "slut", "jesus", "twat", " hell", "christ ", " crap", "admin",
			" notch", "character", " name", "quzzar", "trevek", "small", " lol ", "haha ", "ahah ",
			"nazi", "hitler", "mega ", "douche", "tiddies");
	
	public static boolean isValidName(String name) {
		
		if(name.length() < 3 || name.length() > 12) {
			return false;
		}
		
		if(!name.matches("^[a-zA-Z_]+$")) {
			return false;
		}
		
		double upperC = 0;
		for(char c : name.toCharArray()) {
			if(Character.isUpperCase(c)) {
				upperC++;
			}
		}
		
		if(upperC > 2) {
			return false;
		}
		
		if(!isClean(name)) {
			return false;
		}
		
		if(NameRecordManager.containsName(name)) {
			return false;
		}
		
		return true;
		
	}
	
	private static boolean isClean(String name) {
		String tempName = name;
		tempName = " "+tempName+" ";
		tempName = tempName.toLowerCase();
		for(String word : blackListNames) {
			if(tempName.contains(word)) {
				return false;
			}
		}
		return true;
	}
	
	public static String lettersOnly(String name) {
		return name.replaceAll("[^a-zA-Z]", "");
	}
	

}
