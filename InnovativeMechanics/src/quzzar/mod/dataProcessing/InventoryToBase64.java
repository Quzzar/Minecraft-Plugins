package quzzar.mod.dataProcessing;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class InventoryToBase64 {
	
	
    public String inventoryToBase64(Inventory inventory, int index, UUID id) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(inventory.getSize());

            for (int i = 0; i < inventory.getSize(); i++) {
                dataOutput.writeObject(inventory.getItem(i));
            }
            
            dataOutput.close();
            
            String str = Base64Coder.encodeLines(outputStream.toByteArray());
            
            str = str + "!" + inventory.getType().name() + "!" + inventory.getTitle() + "!" + id + "!" + index;
            
            return str;
            
            //Converts the inventory and its contents to base64, This also saves item meta-data and inventory type
        } catch (Exception e) {
            throw new IllegalStateException("Could not convert inventory to base64.", e);
        }
    }
    
    public InvUUIDUnit inventoryFromBase64(String data) throws IOException {
        try {
        	
        	String[] str = data.split("!");
        	
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(str[0]));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            
            Inventory inventory;
            if(InventoryType.valueOf(str[1]).equals(InventoryType.CHEST)){
            	inventory = Bukkit.createInventory(null, dataInput.readInt(), str[2]);
            }else{
            	dataInput.readInt();
            	inventory = Bukkit.createInventory(null, InventoryType.valueOf(str[1]), str[2]);
            }
            
            
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, (ItemStack) dataInput.readObject());
            }
            
            dataInput.close();
            
            
			int index = 0;
			try {
				index = Integer.parseInt(str[4]);
			} catch(NullPointerException e) {
				e.printStackTrace();
	            throw new IOException("Could not decode inventory.", e);
			}
            
            
            
            
            
            return new InvUUIDUnit(inventory,index,UUID.fromString(str[3]));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException("Could not decode inventory.", e);
        }
    }
    
    
    
    
    
    public String inventoryToBase64NoMap(Inventory inventory) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(inventory.getSize());

            for (int i = 0; i < inventory.getSize(); i++) {
                dataOutput.writeObject(inventory.getItem(i));
            }
            
            dataOutput.close();
            
            String str = Base64Coder.encodeLines(outputStream.toByteArray());
            
            str = str + "!" + inventory.getType().name() + "!" + inventory.getTitle();
            
            return str;
            
            //Converts the inventory and its contents to base64, This also saves item meta-data and inventory type
        } catch (Exception e) {
            throw new IllegalStateException("Could not convert inventory to base64.", e);
        }
    }
    
    public Inventory inventoryFromBase64NoMap(String data) throws IOException {
        try {
        	
        	String[] str = data.split("!");
        	
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(str[0]));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            
            Inventory inventory;
            if(InventoryType.valueOf(str[1]).equals(InventoryType.CHEST)){
            	inventory = Bukkit.createInventory(null, dataInput.readInt(), str[2]);
            }else{
            	inventory = Bukkit.createInventory(null, InventoryType.valueOf(str[1]), str[2]);
            }
            
            
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, (ItemStack) dataInput.readObject());
            }

            dataInput.close();
            
            
            return inventory;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException("Could not decode inventory.", e);
        }
    }
    
    
}
