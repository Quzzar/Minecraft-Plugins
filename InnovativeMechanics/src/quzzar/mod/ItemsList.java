package quzzar.mod;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.maps.TextureItemStackMapManager;

public class ItemsList {
	
	
	
	
	public static ItemStack Book_of_Innovations(int amt){
		
		ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
		
		BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();
		bookMeta.setTitle(ChatColor.LIGHT_PURPLE+LangHandler.applyItemName("Book of Innovations"));
		bookMeta.setAuthor("Quzzar");
		ArrayList<String> lore = new ArrayList<String>();
	    lore.add(ChatColor.GRAY+"Inn. Mech. Book");
	    bookMeta.setLore(lore);
		
		List<String> pages = new ArrayList<String>();
		pages.add("Hello, welcome to the Book of Innovations!"); // Page 1
		pages.add("Hope you enjoy the plugin!"); // Page 2
		
		bookMeta.setPages(pages);
		
		writtenBook.setItemMeta(bookMeta);
		
	    return writtenBook;
	}
	
	public static ItemStack Wrench(int amt){
		
		ItemStack i = new ItemStack(Material.TRIPWIRE_HOOK, amt);
		
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Wrench"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
		
	    return i;
	}
	
	public static ItemStack None(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.NONE);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+"Nothing");
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Aetherium_Cube(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.AETHERIUM_CUBE);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.AQUA+LangHandler.applyItemName("Aetherium Cube"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Storage_Upgrade(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.STORAGE_UPGRADE);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Storage Upgrade"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Speed_Upgrade(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.SPEED_UPGRADE);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Speed Upgrade"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Portable_Crafting_Table(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.CRAFTING_TABLE);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Portable Crafting Table"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Recipe_Viewer(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.RECIPE_VIEWER);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Recipe Viewer"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Placer(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.PLACER);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Placer"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Breaker(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.BREAKER);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Breaker"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Cobblestone_Generator(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.COBBLESTONE_GENERATOR);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Cobblestone Generator"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Healer(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.HEALER_item);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.RED+LangHandler.applyItemName("Healer"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Cremator(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.CREMATOR_item);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Cremator"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Miner(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.MINER_item);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Miner"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Quarry(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.QUARRY_item);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.BLUE+LangHandler.applyItemName("Quarry"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Recycler(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.RECYCLER_item);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Recycler"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	
	public static ItemStack Industrial_Furnace(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.INDUSTRIAL_FURNACE_item);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Industrial Furnace"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Incinerator(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.INCINERATOR_item);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Incinerator"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	
	public static ItemStack Macerator(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.MACERATOR_item);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Macerator"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Auto_Crafting_Table(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.AUTO_CRAFTING_TABLE_item);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Automated Crafting Table"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Grinding_Unit(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.GRINDING_UNIT);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Grinding Unit"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack MiniChest(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.MINICHEST);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Mini Chest"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	
	public static ItemStack DirtChest(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.DIRTCHEST);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Dirt Chest"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack IronChest(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.IRONCHEST);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Iron Chest"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack GoldChest(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.GOLDCHEST);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Gold Chest"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack QuartzChest(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.QUARTZCHEST);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Quartz Chest"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack EmeraldChest(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.EMERALDCHEST);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Emerald Chest"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack DiamondChest(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.DIAMONDCHEST);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Diamond Chest"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack InterDChest(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.InterDCHEST);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.DARK_AQUA+LangHandler.applyItemName("Interdimensional Chest"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack LDirtChest(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.L_DIRT_CHEST_item);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.GOLD+LangHandler.applyItemName("Large Dirt Chest"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Compressed_Dirt(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.COMPRESSED_DIRT);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Compressed Dirt"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Compressed_Cobble(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.COMPRESSED_COBBLE);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Compressed Cobblestone"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Extremely_Compressed_Cobble(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.EXTREMELY_COMPRESSED_COBBLE);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Extremely Compressed Cobblestone"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Insanely_Compressed_Cobble(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.INSANELY_COMPRESSED_COBBLE);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Insanely Compressed Cobblestone"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Compressed_Granite(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.COMPRESSED_GRANITE);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Compressed Granite"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Compressed_Andesite(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.COMPRESSED_ANDESITE);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Compressed Andesite"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Compressed_Diorite(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.COMPRESSED_DIORITE);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Compressed Diorite"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Compressed_Netherrack(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.COMPRESSED_NETHERRACK);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Compressed Netherrack"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Wooden_Crate_Empty(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.WOOD_CRATE_EMPTY);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Wooden Crate (Empty)"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Wooden_Crate_Common(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.WOOD_CRATE_COMMON);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Wooden Crate (Unknown)"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Wooden_Crate_Uncommon(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.WOOD_CRATE_UNCOMMON);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Wooden Crate (Unknown)"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature+" ");
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Wooden_Crate_Rare(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.WOOD_CRATE_RARE);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Wooden Crate (Unknown)"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature+"  ");
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Assembling_Component(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.ASSEMBLING_COMPONENT);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Assembling Component"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Pipe(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.PIPE_item);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Pipe"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack PIPE_BLOCK(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.PIPE);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+"PIPE");
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack PIPE_BLOCK_END(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.PIPE_END);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+"PIPE END");
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Item_Filter(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.ITEM_FILTER_item);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Item Filter"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Coal_Chunks(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.COAL_CHUNKS);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Coal Chunks"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Iron_Chunks(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.IRON_CHUNKS);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Iron Chunks"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Redstone_Chunks(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.REDSTONE_CHUNKS);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Redstone Chunks"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Gold_Chunks(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.GOLD_CHUNKS);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Gold Chunks"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Lapis_Chunks(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.LAPIS_CHUNKS);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Lapis Lazuli Chunks"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Emerald_Chunks(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.EMERALD_CHUNKS);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Emerald Chunks"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack Diamond_Chunks(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.DIAMOND_CHUNKS);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.WHITE+LangHandler.applyItemName("Diamond Chunks"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	public static ItemStack The_Dark_Gateway(int amt){
		HeadUnit headunit = new HeadUnit(TextureDatabase.THE_DARK_GATEWAY);
		
		ItemStack i = headunit.getRawItemStack(amt);
	    ItemMeta im = i.getItemMeta();
	    im.setDisplayName(ChatColor.DARK_GRAY+LangHandler.applyItemName("The Dark Gateway"));
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(Main.pluginSignature);
	    im.setLore(lore);
	    i.setItemMeta(im);
	    
	    TextureItemStackMapManager.addNew(headunit.getTexture(), i);
	    return i;
	}
	
	
}
