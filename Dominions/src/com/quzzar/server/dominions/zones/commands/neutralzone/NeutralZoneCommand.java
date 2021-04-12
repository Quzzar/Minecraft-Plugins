package com.quzzar.server.dominions.zones.commands.neutralzone;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class NeutralZoneCommand
implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if (sender instanceof Player && sender.hasPermission("dominions.commands.neutralzone")) {

			Player player = (Player)sender;

			if (args.length >= 1) {

				if (args[0].equalsIgnoreCase("claim")){
					NeutralZoneClaim.run(player, args);
				} else if (args[0].equalsIgnoreCase("claimregion")){
					NeutralZoneClaimRegion.run(player, args);
				} else if (args[0].equalsIgnoreCase("unclaim")){
					NeutralZoneUnclaim.run(player, args);
				} else if (args[0].equalsIgnoreCase("unclaimall")){
					NeutralZoneUnclaimAll.run(player, args);
				} else if (args[0].equalsIgnoreCase("help")){
					NeutralZoneHelp.run(player, args);
				} else{
					NeutralZoneHelp.run(player, args);
				}


			} else {
				NeutralZoneHelp.run(player, args);
			} 
		} 



		return true;
	}
}
