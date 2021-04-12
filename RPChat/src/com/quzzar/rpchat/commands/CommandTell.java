package com.quzzar.rpchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.quzzar.rpchat.Utility;

public class CommandTell implements CommandExecutor {
	
	private static final String permission = "rpchat.tell";
	
	public static final String commandLayout = "/tell <player> <message>";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(player.hasPermission(permission)) {
				
				if(args.length>1) {
					
					Player receivingPlayer = Bukkit.getPlayer(args[0]);
					
					if(receivingPlayer == null) {
						player.sendMessage(ChatColor.RED+"Player by the name \""+args[0]+"\" could not be found!");
						return true;
					}
					
					if(receivingPlayer.equals(player)) {
						player.sendMessage(ChatColor.RED+"Stop talking to yourself!");
						return true;
					}
					
					StringBuilder message = new StringBuilder();
					for(int i=1; i<args.length; i++){
						message.append(args[i]);
						message.append(" ");
					}
					
					player.sendMessage("[To "+receivingPlayer.getDisplayName()+"] - "+ChatColor.GRAY+""+ChatColor.ITALIC+message.toString());
					receivingPlayer.sendMessage("[From "+player.getDisplayName()+"] - "+ChatColor.GRAY+""+ChatColor.ITALIC+message.toString());
					
				} else {
					player.sendMessage(ChatColor.RED+commandLayout);
				}
				
			} else {
				Utility.needsPermission(player);
			}
			
		}
		
		return true;
	}
	
}
