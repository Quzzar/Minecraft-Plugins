package quzzar.mod.documents;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
 
public class ConfigManager {
 
        private static ConfigManager instance = new ConfigManager();
       
        public static ConfigManager getInstance() {
                return instance;
        }
        
        private Plugin p;
       
        private FileConfiguration config;
        private File cfile;
        
        private FileConfiguration textureData;
        private File tfile;
        
        private FileConfiguration inventoryData;
        private File ifile;
        
        
        private FileConfiguration itemNames;
        private File iNamesfile;
        
        private FileConfiguration book;
        private File bookfile;
        
        private FileConfiguration messages;
        private File messagesfile;
        
        
        public PluginDescriptionFile getDesc() {
            return p.getDescription();
        }
       
        public void setup(Plugin p) {
        	
        	if (!p.getDataFolder().exists()) {
                p.getDataFolder().mkdir();
        	}
        	
        	 cfile = new File(p.getDataFolder(), "config.yml");
             if (!cfile.exists()) {
                 try {
                         cfile.createNewFile();
                 }
                 catch (IOException e) {
                         Bukkit.getServer().getLogger().severe("Could not create config.yml!");
                 }
             }
             config = YamlConfiguration.loadConfiguration(cfile);
             
             
             
             tfile = new File(p.getDataFolder() + File.separator + "Data", "textureData.yml");
             
             if (!tfile.exists()) {
                     try {
                             tfile.createNewFile();
                     }
                     catch (IOException e) {
                             Bukkit.getServer().getLogger().severe("Could not create Data/textureData.yml!");
                     }
             }
             textureData = YamlConfiguration.loadConfiguration(tfile);
             
             
             
             ifile = new File(p.getDataFolder() + File.separator + "Data", "inventoryData.yml");
             
             if (!ifile.exists()) {
                     try {
                             ifile.createNewFile();
                     }
                     catch (IOException e) {
                             Bukkit.getServer().getLogger().severe("Could not create Data/inventoryData.yml!");
                     }
             }
             inventoryData = YamlConfiguration.loadConfiguration(ifile);
             
             
             
             iNamesfile = new File(p.getDataFolder() + File.separator + "lang", "ItemNames.yml");
             
             if (!iNamesfile.exists()) {
                     try {
                    	 iNamesfile.createNewFile();
                     }
                     catch (IOException e) {
                             Bukkit.getServer().getLogger().severe("Could not create lang/ItemNames.yml!");
                     }
             }
             itemNames = YamlConfiguration.loadConfiguration(iNamesfile);
             
             
             
             bookfile = new File(p.getDataFolder() + File.separator + "lang", "Book.yml");
             
             if (!bookfile.exists()) {
                     try {
                    	 bookfile.createNewFile();
                     }
                     catch (IOException e) {
                             Bukkit.getServer().getLogger().severe("Could not create lang/Book.yml!");
                     }
             }
             book = YamlConfiguration.loadConfiguration(bookfile);
             
             
             messagesfile = new File(p.getDataFolder() + File.separator + "lang", "Messages.yml");
             
             if (!messagesfile.exists()) {
                     try {
                    	 messagesfile.createNewFile();
                     }
                     catch (IOException e) {
                             Bukkit.getServer().getLogger().severe("Could not create lang/Messages.yml!");
                     }
             }
             messages = YamlConfiguration.loadConfiguration(messagesfile);
        	
        }
        /////////////////////////////////////////////////////
        
        public FileConfiguration getConfig() {
                return config;
        }
       
        public void saveConfig() {
                try {
                        config.save(cfile);
                }
                catch (IOException e) {
                        Bukkit.getServer().getLogger().severe("Could not save config.yml!");
                }
        }
       
        public void reloadConfig() {
        	config = YamlConfiguration.loadConfiguration(cfile);
        }
        
        ///
        
        public FileConfiguration getTextureData() {
                return textureData;
        }
       
        public void saveTextureData() {
                try {
                	textureData.save(tfile);
                }
                catch (IOException e) {
                        Bukkit.getServer().getLogger().severe("Could not save Data/textureData.yml!");
                }
        }
       
        public void reloadTextureData() {
        	textureData = YamlConfiguration.loadConfiguration(tfile);
        }
        
        ///
        
        public FileConfiguration getInventoryData() {
                return inventoryData;
        }
       
        public void saveInventoryData() {
                try {
                	inventoryData.save(ifile);
                }
                catch (IOException e) {
                        Bukkit.getServer().getLogger().severe("Could not save Data/inventoryData.yml!");
                }
        }
       
        public void reloadInventoryData() {
        	inventoryData = YamlConfiguration.loadConfiguration(ifile);
        }
        
        ///
        
        public FileConfiguration getBookFile() {
                return book;
        }
       
        public void saveBookFile() {
                try {
                	book.save(bookfile);
                }
                catch (IOException e) {
                        Bukkit.getServer().getLogger().severe("Could not save lang/Book.yml!");
                }
        }
       
        public void reloadBookFile() {
        	book = YamlConfiguration.loadConfiguration(bookfile);
        }
        
        ///
        
        public FileConfiguration getItemNamesFile() {
                return itemNames;
        }
       
        public void saveItemNamesFile() {
                try {
                	itemNames.save(iNamesfile);
                }
                catch (IOException e) {
                        Bukkit.getServer().getLogger().severe("Could not save lang/ItemNames.yml!");
                }
        }
       
        public void reloadItemNamesFile() {
        	itemNames = YamlConfiguration.loadConfiguration(iNamesfile);
        }

        ///
        
        public FileConfiguration getMessagesFile() {
                return messages;
        }
       
        public void saveMessagesFile() {
                try {
                	messages.save(messagesfile);
                }
                catch (IOException e) {
                        Bukkit.getServer().getLogger().severe("Could not save lang/Messages.yml!");
                }
        }
        
        public void reloadMessagesFile() {
        	messages = YamlConfiguration.loadConfiguration(messagesfile);
        }
        
}