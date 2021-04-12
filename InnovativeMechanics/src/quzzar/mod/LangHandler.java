package quzzar.mod;

import org.bukkit.configuration.file.FileConfiguration;

import quzzar.mod.documents.ConfigManager;

public class LangHandler {
	
	private static FileConfiguration bookFile = ConfigManager.getInstance().getBookFile();
	private static FileConfiguration itemNamesFile = ConfigManager.getInstance().getItemNamesFile();
	private static FileConfiguration messagesFile = ConfigManager.getInstance().getMessagesFile();
	
	
	public static String applyItemName(String engName) {
		
		String name;
		
		if(itemNamesFile.get("Items."+engName+".Name")==null){
			itemNamesFile.set("Items."+engName+".Name", engName);
			ConfigManager.getInstance().saveItemNamesFile();
			
			name = engName;
			
		}else {
			
			name = itemNamesFile.getString("Items."+engName+".Name");
			
		}
		
		return name;
		
	}
	
	public static String applyCategoryTitle(String engTitle) {
		
		String title;
		
		if(bookFile.get("Pages."+engTitle+".Title")==null){
			bookFile.set("Pages."+engTitle+".Title", engTitle);
			ConfigManager.getInstance().saveBookFile();
			
			title = engTitle;
			
		}else {
			
			title = bookFile.getString("Pages."+engTitle+".Title");
			
		}
		
		return title;
		
	}
	
	public static String applyCommandDescription(String name, String engDescr) {
		
		String descr;
		
		if(messagesFile.get("Commands."+name+".Description")==null){
			messagesFile.set("Commands."+name+".Description", engDescr);
			ConfigManager.getInstance().saveMessagesFile();
			
			descr = engDescr;
			
		}else {
			
			descr = messagesFile.getString("Commands."+name+".Description");
			
		}
		
		return descr;
		
	}
	
	public static String applyMessage(String name, String engMessage) {
		
		String message;
		
		if(messagesFile.get("Messages."+name)==null){
			messagesFile.set("Messages."+name, engMessage);
			ConfigManager.getInstance().saveMessagesFile();
			
			message = engMessage;
			
		}else {
			
			message = messagesFile.getString("Messages."+name);
			
		}
		
		return message;
		
	}
	
}
