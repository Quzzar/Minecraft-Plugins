package quzzar.mod.pluginhelp.items;

import java.util.ArrayList;
import java.util.List;

public class ItemHelp {

	private static List<ItemHelpInstance> itemsList = new ArrayList<ItemHelpInstance>();
	
	public static void addInstance(ItemHelpInstance helpIn){
		itemsList.add(helpIn);
	}
	
	public static List<ItemHelpInstance> getItemsList(){
		return itemsList;
	}
	
}
