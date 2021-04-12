package com.quzzar.server.dominions.zones.commands.safezone;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SafezoneCommand implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if (sender instanceof Player && sender.hasPermission("dominions.commands.safezone")) {

			Player player = (Player) sender;

			if (args.length >= 1) {

				if (args[0].equalsIgnoreCase("claim")) {
					SafezoneClaim.run(player, args);
				} else if (args[0].equalsIgnoreCase("claimregion")){
					SafezoneClaimRegion.run(player, args);
				} else if (args[0].equalsIgnoreCase("unclaim")) {
					SafezoneUnclaim.run(player, args);
				} else if (args[0].equalsIgnoreCase("unclaimall")) {
					SafezoneUnclaimAll.run(player, args);
				} else if (args[0].equalsIgnoreCase("help")) {
					SafezoneHelp.run(player, args);
				} else {
					SafezoneHelp.run(player, args);
				}

			} else {
				SafezoneHelp.run(player, args);
			}
		}

		return true;
	}
}