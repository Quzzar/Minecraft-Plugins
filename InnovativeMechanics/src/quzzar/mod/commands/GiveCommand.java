package quzzar.mod.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import quzzar.mod.LangHandler;
import quzzar.mod.Utility;
import quzzar.mod.pluginhelp.items.ItemHelp;
import quzzar.mod.pluginhelp.items.ItemHelpInstance;

public class GiveCommand {

	public static String commandLayout = "/im give <player> <itemName> [amount]";
	
	public static String commandDescription(ChatColor color){
		return color+LangHandler.applyCommandDescription("Give", "Gives Innovative Mechanics items to players.");
	}
	
	
	public static void run(CommandSender sender, String[] args){
		if(args.length==3){
			
			Player p = Bukkit.getPlayer(args[1]);
			if(p != null){
				String texName = givePlayer(p, args[2], 1);
				if(texName!=null){
					Utility.tellSender(sender, p.getName()+" was given 1 "+ texName + "!");
				}else{
					Utility.tellSender(sender, ChatColor.RED+"Item '"+args[2]+"' could not be found!");
				}
			}else{
				Utility.tellSender(sender, ChatColor.RED+"Player '"+args[1]+"' could not be found!");
			}
			
		}else if(args.length==4){
			
			int amt = -1;
			try {
				amt = Integer.parseInt(args[3]);
			} catch(Exception e) {
				Utility.tellSender(sender, ChatColor.RED+"'"+args[3]+"' is not an amount!");
				return;
			}
			
			Player p = Bukkit.getPlayer(args[1]);
			if(p != null&&amt != -1){
				String texName = givePlayer(p, args[2], Math.abs(amt));
				if(texName!=null){
					Utility.tellSender(sender, p.getName()+" was given "+amt+" "+ texName + "(s)!");
				}else{
					Utility.tellSender(sender, ChatColor.RED+"Item '"+args[2]+"' could not be found!");
				}
			}else{
				Utility.tellSender(sender, ChatColor.RED+"Player '"+args[1]+"' could not be found!");
			}
			
		}else{
			failedCommand(sender);
		}
	}
	
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
	
	
	private static String givePlayer(Player p, String itemName, int amt){
		for(ItemHelpInstance instance : ItemHelp.getItemsList()){
			ItemStack i = instance.getItemStack();
			
			String texName = Utility.getSafeItemName(i);
			
			if(texName.equalsIgnoreCase(itemName)){
				i.setAmount(amt);
				Utility.addItemToInventory(p.getInventory(), i, p.getLocation());
				return texName;
			}
		}
		return null;
	}
	
	
	
}
