package quzzar.mod.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import quzzar.mod.ArmorStandData;
import quzzar.mod.LangHandler;
import quzzar.mod.Utility;

public class DebugDeleteCommand {
	
	public static String commandLayout = "/im forcedelete";
	
	public static String commandDescription(ChatColor color){
		return color+LangHandler.applyCommandDescription("Force_Delete", "Deletes any nearby machines. (Caution: This command may cause loss of machine data. Use at your own risk!)");
	}
	
	
	
	public static void run(CommandSender sender, String[] args){
		if(args.length==1 && sender instanceof Player){
			
			Player p = (Player) sender;
			
			int count = 0;
			
			for(Entity entity : p.getWorld().getNearbyEntities(p.getLocation(), 2, 1, 2)) {
				if(entity instanceof ArmorStand) {
					ArmorStand as = (ArmorStand) entity;
					
					if(!ArmorStandData.isNatural(as)) {
						as.remove();
						count++;
					}
					
				}
			}
			
			for(Block block : Utility.getNearbyBlocks(p.getLocation(), 2)) {
				if(block.getType().equals(Material.BARRIER)) {
					block.setType(Material.AIR);
					count++;
				}
			}
			
			p.sendMessage(ChatColor.GREEN+"Successfully removed '"+count+"' nearby machine parts.");
			
		}else{
			failedCommand(sender);
		}
	}
	
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
}
