package com.quzzar.server.dominions.commands.tabcompleter;

import java.util.HashMap;
import java.util.List;

import com.quzzar.server.dominions.settings.SettingType;

public class CompleteCommand {

	private String cmdText;
	private SettingType typeToUse;
	private boolean leaderOnly;
	
	private HashMap<Integer, List<String>> extraArgs;
	
	public CompleteCommand(String cmdText, SettingType typeToUse) {
		this.cmdText = cmdText;
		this.typeToUse = typeToUse;
		this.leaderOnly = false;
		this.extraArgs = new HashMap<Integer, List<String>>();
	}

	public CompleteCommand(String cmdText, boolean leaderOnly) {
		this.cmdText = cmdText;
		this.typeToUse = SettingType.NULL;
		this.leaderOnly = leaderOnly;
		this.extraArgs = new HashMap<Integer, List<String>>();
	}
	
	public CompleteCommand extraArgs(int argNum, List<String> options) {
		extraArgs.put(argNum, options);
		return this;
	}
	
	public CompleteCommand extraArgs(int argNum, ExtraType extraType) {
		extraArgs.put(argNum, extraType.toArgsList());
		return this;
	}
	
	public String getCmdText() {
		return cmdText;
	}

	public SettingType getTypeToUse() {
		return typeToUse;
	}
	
	public boolean isLeaderOnly() {
		return leaderOnly;
	}
	
	public boolean hasExtraArgs(){
		return !extraArgs.isEmpty();
	}
	
	public HashMap<Integer, List<String>> getExtraArgs(){
		return extraArgs;
	}
	
}
