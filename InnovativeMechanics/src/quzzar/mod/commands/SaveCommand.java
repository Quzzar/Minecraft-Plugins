package quzzar.mod.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import quzzar.mod.LangHandler;
import quzzar.mod.Utility;
import quzzar.mod.dataProcessing.DataProcessingManager;
import quzzar.mod.inventories.mechInv.MechInvASManager;
import quzzar.mod.inventories.mechInv.MechInvManager;

public class SaveCommand {

	public static String commandLayout = "/im save";
	
	public static String commandDescription(ChatColor color){
		return color+LangHandler.applyCommandDescription("Save", "Saves all plugin data (Recommended before stopping server).");
	}
	
	private static MechInvManager machInvInstance;
	private static MechInvASManager machInvASInstance;
	
	
	
	public static void run(CommandSender sender, String[] args){
		if(args.length==1){
			
			save();
			
			sender.sendMessage(ChatColor.GREEN+LangHandler.applyMessage("Successfully_Save", "Plugin data successfully saved."));
			
		}else{
			failedCommand(sender);
		}
	}
	
	
	public static void failedCommand(CommandSender sender){
		Utility.tellSender(sender, ChatColor.RED+commandLayout);
	}
	
	public static void save() {
		
		machInvInstance = new MechInvManager();
		machInvASInstance = new MechInvASManager();
		
		DataProcessingManager.saveWorldsData();
		
		machInvInstance.saveInventories();
		machInvASInstance.saveInventories();
		
	}
	
}
