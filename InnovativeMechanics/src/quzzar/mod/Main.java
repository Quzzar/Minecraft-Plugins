package quzzar.mod;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.quzzar.im.versioncontrol.VersionControl;

import quzzar.mod.blocks.HeadUnitBlock;
import quzzar.mod.commands.ImCommand;
import quzzar.mod.dataProcessing.DataProcessingManager;
import quzzar.mod.dataProcessing.HUBDataProcessing;
import quzzar.mod.dataProcessing.MHUDataProcessing;
import quzzar.mod.dataProcessing.PipeDataProcessing;
import quzzar.mod.dataProcessing.ProcessingManager;
import quzzar.mod.documents.CheckConfig;
import quzzar.mod.documents.ConfigManager;
import quzzar.mod.hu.HURegistrar;
import quzzar.mod.inventories.mechInv.MechGlobalInv;
import quzzar.mod.inventories.mechInv.MechInvASManager;
import quzzar.mod.inventories.mechInv.MechInvManager;
import quzzar.mod.mhu.MHURegistrar;
import quzzar.mod.pipes.HUPipe;
import quzzar.mod.pluginhelp.HelpListener;
import quzzar.mod.pluginhelp.PluginHelpManager;
import quzzar.mod.recipeviewer.RecipeViewer;
import quzzar.mod.thedark.DarkListener;
import quzzar.mod.thedark.DarkManager;

public class Main extends JavaPlugin implements CommandExecutor{
	
	private static ConfigManager configManager;
	
	public static ArrayList<MultiHeadUnit> MHUList = new ArrayList<MultiHeadUnit>();
	public static ArrayList<HeadUnitBlock> HUBList = new ArrayList<HeadUnitBlock>();
	public static ArrayList<HUPipe> PipeList = new ArrayList<HUPipe>();
	
	public static ArrayList<Player> loggedOffPlayers = new ArrayList<Player>();
	
	public static Main instance;
	
	public static String pluginSignature = ChatColor.GRAY+"Innovative Mechanics";
	public static String pluginSignatureMessage = ChatColor.ITALIC+"Innovative Mechanics";
	public static Material fillerType = Material.BARRIER;
	
	
	public static MechInvManager machInvInstance;
	public static MechInvASManager machInvASInstance;
	
	public static MHUDataProcessing mhuDataProcInstance;
	public static HUBDataProcessing hubDataProcInstance;
	public static PipeDataProcessing pipeDataProcInstance;
	
	@Override
	public void onEnable(){
		
		instance = this;
		
		VersionControl.initialize();
		
		configManager = ConfigManager.getInstance();
		
		configSetup(instance);
		
		
		MechGlobalInv.loadInventories();
		WoodenCrate.loadMaps();
		
		machInvInstance = new MechInvManager();
		machInvASInstance = new MechInvASManager();
		
		mhuDataProcInstance = new MHUDataProcessing();
		hubDataProcInstance = new HUBDataProcessing();
		pipeDataProcInstance = new PipeDataProcessing();
		
		
		
		ProcessingManager.fillUUIDInvMap();
		
		DataProcessingManager.loadData();
		
		
		InteralClock.RunTask();
		
		//
		
		instance.getCommand("im").setExecutor(new ImCommand());
		instance.getCommand("innovativemechanics").setExecutor(new ImCommand());
		
		
		getServer().getPluginManager().registerEvents(new MainListener(), instance);
		getServer().getPluginManager().registerEvents(new Recipes(), instance);
		getServer().getPluginManager().registerEvents(new RecipeViewer(), instance);
		
		if(configManager.getConfig().getBoolean("Enable_The_Dark")){
			
			getServer().getPluginManager().registerEvents(new DarkListener(), instance);
			
			DarkManager.initialize(instance);
			
		}
		
		
		
		
		recipeSetup();
		
		
		PluginHelpManager.initialize();
		
		getServer().getPluginManager().registerEvents(new HelpListener(), instance);
		
		
		processVersion();
		
		
		if(CheckConfig.generatingDefaults){
			Utility.tellConsole(ChatColor.YELLOW+"Missing config element(s), generating defaults!");
		}
		
		Utility.tellConsole(LangHandler.applyMessage("Successful_Startup", "Successfully Loaded and Ready!"));
		
	}
	
	
	
	
	
	@Override
	public void onDisable(){
		
		DataProcessingManager.saveWorldsData();
		
		machInvInstance.saveInventories();
		machInvASInstance.saveInventories();
		
		
		// To prevent item steal
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			p.closeInventory();
		}
		
		
		try{
			Utility.tellConsole(LangHandler.applyMessage("Disabling", "Disabling!"));
		} catch(NoClassDefFoundError e){
			
		}
		
	}
	
	public static void configSetup(Plugin plugin){
		
		configManager.setup(plugin);
		
		new CheckConfig();
		
		MHURegistrar.register();
		HURegistrar.register();
		
	}
	
	public void recipeSetup(){
		HURecipeLayouts.loadLayouts(this);
		
		
		Recipes.BookRecipe(this);
		
		if((boolean) CheckConfig.getValue("Enable_Machines", true)){
			Recipes.Machines(this);
		}
		
		if((boolean) CheckConfig.getValue("Enable_Mini_Chests", true)){
			Recipes.MiniChests(this);
		}
		
		if((boolean) CheckConfig.getValue("Enable_Block_Compression", true)){
			Recipes.BlockCompression(this);
		}
		
		if((boolean) CheckConfig.getValue("Enable_Utility", true)){
			Recipes.Utility(this);
		}
		
		if((boolean) CheckConfig.getValue("Enable_Miscellaneous", true)){
			Recipes.Miscellaneous(this);
		}
	}
	
	
	private static void processVersion(){
		if(!configManager.getConfig().getString("Version").equalsIgnoreCase(getVersion())){
			
			boolean remakeConfig = false;
			
			if(remakeConfig) {
				
				Utility.tellConsole(ChatColor.YELLOW+LangHandler.applyMessage("Old_Version_Files", "Currently using old version files!"));
				Utility.tellConsole(ChatColor.LIGHT_PURPLE+LangHandler.applyMessage("Remaking_Config", "Remaking config and putting in default values!"));
				
				File configFile = new File(Main.instance.getDataFolder(), "config.yml");
				configFile.delete();
				
				configSetup(Main.instance);
				
			}
			
		}
	}
	
	
	public static String getVersion(){
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		Plugin p = pm.getPlugin("InnovativeMechanics");
		if (p != null) {
		    PluginDescriptionFile pdf = p.getDescription();
		    return pdf.getVersion();
		}
		return "";
	}
	
	
}