package quzzar.mod.documents;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class TXTDocumentWriter {
	
	public static void createTXTDoc(Plugin p, String fileName, ArrayList<String> texts){
		File file = new File(p.getDataFolder(),fileName+".txt");
		
		if (!file.exists()) {
            try {
                    file.createNewFile();
                    try (Writer writer = new BufferedWriter(new FileWriter(file))) {
                    	String textFull = "";
                    	for(String text : texts){
                    		textFull = textFull + System.getProperty("line.separator") + text;
                    	}
                    	writer.write(textFull);
                    } catch (IOException e) {
                    	Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not write to "+fileName+".txt!");
                    }
            }
            catch (IOException e) {
                    Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create "+fileName+".txt!");
            }
        }
	}
	
	
}
