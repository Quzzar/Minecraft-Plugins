package com.quzzar.server.dominions.commands.tabcompleter;

import java.util.Arrays;
import java.util.List;

public enum ExtraType {

	NULL,
	DOMINIONS,
	DOM_MEMBERS,
	DOM_INVITES;
	
	public List<String> toArgsList(){
		return Arrays.asList(this.toString());
	}
	
}
