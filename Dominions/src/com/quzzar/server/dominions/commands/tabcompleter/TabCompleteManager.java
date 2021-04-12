package com.quzzar.server.dominions.commands.tabcompleter;

import java.util.Arrays;
import java.util.List;

import com.quzzar.server.dominions.settings.SettingType;

public class TabCompleteManager {

	private static List<CompleteCommand> commandsList;
	
	public static void init() {
		
		commandsList = Arrays.asList(
				new CompleteCommand("announce", true),
				new CompleteCommand("chunksettings", SettingType.CHUNK_SETTINGS),
				new CompleteCommand("claim", SettingType.CLAIM),
				new CompleteCommand("create", false),
				new CompleteCommand("deposit", SettingType.DEPOSIT),
				new CompleteCommand("desc", SettingType.DESCRIPTION),
				new CompleteCommand("destroy", true),
				new CompleteCommand("help", false),
				new CompleteCommand("home", SettingType.HOME),
				new CompleteCommand("invite", SettingType.INVITE),
				new CompleteCommand("join", false).extraArgs(1, ExtraType.DOMINIONS),
				new CompleteCommand("kick", false).extraArgs(1, ExtraType.DOM_MEMBERS),
				new CompleteCommand("leave", false),
				new CompleteCommand("list", false),
				new CompleteCommand("log", SettingType.LOG).extraArgs(1, Arrays.asList("finance","members","diplomacy")),
				new CompleteCommand("map", false),
				new CompleteCommand("setdiplomacy", SettingType.SET_DIPLOMACY).extraArgs(1, ExtraType.DOMINIONS)
												.extraArgs(2, Arrays.asList("ally","neutral","enemy")),
				new CompleteCommand("sethome", SettingType.SET_HOME),
				new CompleteCommand("setrank", false).extraArgs(1, ExtraType.DOM_MEMBERS)
												.extraArgs(2, Arrays.asList("leader","general","veteran","member")),
				new CompleteCommand("settings", SettingType.SETTINGS),
				new CompleteCommand("show", false).extraArgs(1, ExtraType.DOMINIONS),
				new CompleteCommand("top", false),
				new CompleteCommand("unclaim", SettingType.UNCLAIM),
				new CompleteCommand("unclaimall", true),
				new CompleteCommand("uninvite", SettingType.UNINVITE).extraArgs(1, ExtraType.DOM_INVITES),
				new CompleteCommand("upgrades", SettingType.UPGRADES),
				new CompleteCommand("vault", false),
				new CompleteCommand("withdraw", SettingType.WITHDRAW));
		
	}
	
	public static List<CompleteCommand> getCommandList() {
		return commandsList;
	}
	
}
