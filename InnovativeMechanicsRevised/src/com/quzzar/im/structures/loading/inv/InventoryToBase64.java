package com.quzzar.im.structures.loading.inv;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.InvDataTag;
import com.quzzar.im.structures.Structure;
import com.quzzar.im.utils.ChatUtils;

public class InventoryToBase64 {
	
	
    public static String inventoryToBase64(Inventory inventory) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(inventory.getSize());

            for (int i = 0; i < inventory.getSize(); i++) {
                dataOutput.writeObject(inventory.getItem(i));
            }
            
            dataOutput.close();
            
            return Base64Coder.encodeLines(outputStream.toByteArray());
            
        } catch (Exception e) {
            ChatUtils.tellConsole(ChatColor.RED+"Could not convert inventory to base64.");
            return null;
        }
    }
    
    public static Inventory inventoryFromBase64(StructInv inv, Structure structure, InvTag tag) {
        try {
        	
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(inv.getEncodedItems()));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            
            Inventory inventory;
            if(inv.getInvType().equals(InventoryType.CHEST)){
            	inventory = Bukkit.createInventory(null, dataInput.readInt(), inv.getInvTitle());
            }else{
            	dataInput.readInt();
            	inventory = Bukkit.createInventory(null, inv.getInvType(), inv.getInvTitle());
            }
            
            
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, (ItemStack) dataInput.readObject());
            }
            
            dataInput.close();     
            
            CraftMeta.invSet(inventory, InvDataTag.MACHINE_STRUCTURE, structure.getID());
            CraftMeta.invSet(inventory, InvDataTag.MACHINE_INV_TAG, tag);
            
            return inventory;
            
        } catch (Exception e) {
            ChatUtils.tellConsole(ChatColor.RED+"Could not decode inventory.");
            return null;
        }
    }
    
}
