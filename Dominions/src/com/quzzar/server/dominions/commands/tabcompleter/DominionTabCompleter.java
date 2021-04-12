package com.quzzar.server.dominions.commands.tabcompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.quzzar.server.dominions.Dominion;
import com.quzzar.server.dominions.DominionManager;
import com.quzzar.server.dominions.DominionUtils;
import com.quzzar.server.dominions.misc.PlayerRank;
import com.quzzar.server.dominions.settings.SettingType;
import com.quzzar.server.souls.character.CharacterManager;

public class DominionTabCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		
		Player player = null;
		Dominion dominion = null;
		
		if(sender instanceof Player) {
			player = (Player) sender;
			dominion = DominionUtils.getDominionByPlayer(player.getUniqueId());
		}
		
		String currentArg = args[args.length-1];
		
		if(args.length == 1) {
			
			return getPossibleCommands(currentArg, player, dominion);
			
		} else if (args.length > 1){
			
			return getExtraArgs(args[0], args.length-1, currentArg, dominion);
			
		}
		
		return null;
	}
	
	private List<String> getPossibleCommands(String currentArg, Player player, Dominion dominion){
		
		List<String> posCommands = new ArrayList<String>();
		
		for(CompleteCommand command : TabCompleteManager.getCommandList()) {
			if(command.getCmdText().toLowerCase().startsWith(currentArg.toLowerCase())) {
				
				if(command.isLeaderOnly()) {
					// Leader Only
					
					if(player != null && dominion != null) {
						
						boolean isLeader = dominion.getPlayerRank(player.getUniqueId()).equals(PlayerRank.Leader);
						if(isLeader) {
							posCommands.add(command.getCmdText());
						}
						
					}
					
				} else if (command.getTypeToUse().equals(SettingType.NULL)){
					// Anyone Can Use
					
					posCommands.add(command.getCmdText());
					
				} else {
					// Check Rank, it's rank specific
					
					if(player != null && dominion != null) {
						
						boolean hasPerms = DominionUtils.hasPermission(dominion, player.getUniqueId(), command.getTypeToUse());
						if(hasPerms) {
							posCommands.add(command.getCmdText());
						}
						
					}
					
				}
				
			}
		}
		return posCommands;
	}
	
	
	private List<String> getExtraArgs(String firstArg, int argCount, String currentArg, Dominion dominion) {
		
		CompleteCommand command = getCommand(firstArg);
		
		if(command == null || !command.hasExtraArgs()) {
			return null;
		}
		
		List<String> possibles = command.getExtraArgs().get(argCount);
		
		if(possibles == null) {
			return null;
		}
		
		if(possibles.equals(ExtraType.DOM_INVITES.toArgsList())) {
			
			possibles = new ArrayList<String>();
			
			if(dominion != null) {
				
				for(UUID pUUID : dominion.getInvites()) {
					Player invitedPlayer = Bukkit.getPlayer(pUUID);
					if(invitedPlayer != null) {
						possibles.add(invitedPlayer.getDisplayName());
					}
				}
				
			}
			
		} else if (possibles.equals(ExtraType.DOM_MEMBERS.toArgsList())) {
			
			possibles = new ArrayList<String>();
			
			if(dominion != null) {
				
				for(UUID pUUID : dominion.getMembers().keySet()) {
					String memberName = CharacterManager.getCharacterData(pUUID).getName();
					possibles.add(memberName);
				}
				
			}
			
		} else if (possibles.equals(ExtraType.DOMINIONS.toArgsList())) {
			
			possibles = new ArrayList<String>();
			
			for(Dominion dom : DominionManager.getDominions()) {
				possibles.add(dom.getName());
			}
			
		}
		
		List<String> posCommands = new ArrayList<String>();
		
		for(String p : possibles) {
			if(p.toLowerCase().startsWith(currentArg.toLowerCase())) {
				posCommands.add(p);
			}
		}
		
		return posCommands;
		
	}
	
	private CompleteCommand getCommand(String cmdText) {
		for(CompleteCommand command : TabCompleteManager.getCommandList()) {
			if(command.getCmdText().equalsIgnoreCase(cmdText)) {
				return command;
			}
		}
		return null;
	}
	

}
