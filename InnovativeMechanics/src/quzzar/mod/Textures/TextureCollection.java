package quzzar.mod.Textures;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.quzzar.im.versioncontrol.VersionControl;
import com.quzzar.im.versioncontrol.global.material.GMaterial;

public enum TextureCollection {
	
	PIPE (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.PIPE))),
	
	LARGE_DIRT_CHEST (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.L_DIRT_CHEST_FTL,TextureDatabase.L_DIRT_CHEST_FTR,TextureDatabase.L_DIRT_CHEST_FBL,
			TextureDatabase.L_DIRT_CHEST_FBR,TextureDatabase.L_DIRT_CHEST_BTL,TextureDatabase.L_DIRT_CHEST_BTR,TextureDatabase.L_DIRT_CHEST_BBL,TextureDatabase.L_DIRT_CHEST_BBR))),
	
	HEALER (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.HEALER_FTL,TextureDatabase.HEALER_FTR,TextureDatabase.HEALER_FBL,TextureDatabase.HEALER_FBR,
			TextureDatabase.HEALER_BTL,TextureDatabase.HEALER_BTR,TextureDatabase.HEALER_BBL,TextureDatabase.HEALER_BBR))),
	
	MINER (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.MINER_FTL,TextureDatabase.MINER_FTR,TextureDatabase.MINER_FBL,TextureDatabase.MINER_FBR,
			TextureDatabase.MINER_BTL,TextureDatabase.MINER_BTR,TextureDatabase.MINER_BBL,TextureDatabase.MINER_BBR,TextureDatabase.MINICHEST))),
	MINER_ON (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.MINER_ON_FTL,TextureDatabase.MINER_ON_FTR,TextureDatabase.MINER_ON_FBL,TextureDatabase.MINER_ON_FBR,
			TextureDatabase.MINER_BTL,TextureDatabase.MINER_BTR,TextureDatabase.MINER_BBL,TextureDatabase.MINER_BBR,TextureDatabase.MINICHEST))),
	
	MACERATOR (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.MACERATOR_FTL,TextureDatabase.MACERATOR_FTR,TextureDatabase.MACERATOR_FBL,TextureDatabase.MACERATOR_FBR,
			TextureDatabase.MACERATOR_BTL,TextureDatabase.MACERATOR_BTR,TextureDatabase.MACERATOR_BBL,TextureDatabase.MACERATOR_BBR,TextureDatabase.MINICHEST,
			TextureDatabase.MINICHEST))),
	MACERATOR_ON (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.MACERATOR_ON_FTL,TextureDatabase.MACERATOR_ON_FTR,TextureDatabase.MACERATOR_ON_FBL,TextureDatabase.MACERATOR_ON_FBR,
			TextureDatabase.MACERATOR_BTL,TextureDatabase.MACERATOR_ON_BTR,TextureDatabase.MACERATOR_BBL,TextureDatabase.MACERATOR_ON_BBR,TextureDatabase.MINICHEST,
			TextureDatabase.MINICHEST))),
	
	RECYCLER (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.RECYCLER_FTL,TextureDatabase.RECYCLER_FTR,TextureDatabase.RECYCLER_FBL,TextureDatabase.RECYCLER_FBR,
			TextureDatabase.RECYCLER_BTL,TextureDatabase.RECYCLER_BTR,TextureDatabase.RECYCLER_BBL,TextureDatabase.RECYCLER_BBR,TextureDatabase.IRONCHEST,
			TextureDatabase.IRONCHEST,TextureDatabase.IRONCHEST))),
	RECYCLER_ON (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.RECYCLER_ON_FTL,TextureDatabase.RECYCLER_ON_FTR,TextureDatabase.RECYCLER_ON_FBL,TextureDatabase.RECYCLER_ON_FBR,
			TextureDatabase.RECYCLER_ON_BTL,TextureDatabase.RECYCLER_ON_BTR,TextureDatabase.RECYCLER_BBL,TextureDatabase.RECYCLER_BBR,TextureDatabase.IRONCHEST,
			TextureDatabase.IRONCHEST,TextureDatabase.IRONCHEST))),
	
	AUTO_CRAFTING_TABLE (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.AUTO_CRAFTING_TABLE_FTL,TextureDatabase.AUTO_CRAFTING_TABLE_FTR,TextureDatabase.AUTO_CRAFTING_TABLE_FBL,
			TextureDatabase.AUTO_CRAFTING_TABLE_FBR,TextureDatabase.AUTO_CRAFTING_TABLE_BTL,TextureDatabase.AUTO_CRAFTING_TABLE_BTR,TextureDatabase.AUTO_CRAFTING_TABLE_BBL,
			TextureDatabase.AUTO_CRAFTING_TABLE_BBR,TextureDatabase.MINICHEST,TextureDatabase.MINICHEST,TextureDatabase.CRAFTING_TABLE))),
	AUTO_CRAFTING_TABLE_ON (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.AUTO_CRAFTING_TABLE_ON_FTL,TextureDatabase.AUTO_CRAFTING_TABLE_ON_FTR,TextureDatabase.AUTO_CRAFTING_TABLE_ON_FBL,
			TextureDatabase.AUTO_CRAFTING_TABLE_ON_FBR,TextureDatabase.AUTO_CRAFTING_TABLE_ON_BTL,TextureDatabase.AUTO_CRAFTING_TABLE_ON_BTR,TextureDatabase.AUTO_CRAFTING_TABLE_ON_BBL,
			TextureDatabase.AUTO_CRAFTING_TABLE_ON_BBR,TextureDatabase.MINICHEST,TextureDatabase.MINICHEST,TextureDatabase.CRAFTING_TABLE))),
	
	INDUSTRIAL_FURNACE (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.INDUSTRIAL_FURNACE_FTL,TextureDatabase.INDUSTRIAL_FURNACE_FTR,TextureDatabase.INDUSTRIAL_FURNACE_FBL,
			TextureDatabase.INDUSTRIAL_FURNACE_FBR,TextureDatabase.INDUSTRIAL_FURNACE_BTL,TextureDatabase.INDUSTRIAL_FURNACE_BTR,TextureDatabase.INDUSTRIAL_FURNACE_BBL,
			TextureDatabase.INDUSTRIAL_FURNACE_BBR,TextureDatabase.MINICHEST))),
	INDUSTRIAL_FURNACE_ON (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.INDUSTRIAL_FURNACE_FTL,TextureDatabase.INDUSTRIAL_FURNACE_FTR,TextureDatabase.INDUSTRIAL_FURNACE_ON_FBL,
			TextureDatabase.INDUSTRIAL_FURNACE_ON_FBR,TextureDatabase.INDUSTRIAL_FURNACE_BTL,TextureDatabase.INDUSTRIAL_FURNACE_BTR,TextureDatabase.INDUSTRIAL_FURNACE_BBL,
			TextureDatabase.INDUSTRIAL_FURNACE_BBR,TextureDatabase.MINICHEST))),
	
	ITEM_FILTER (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.ITEM_FILTER_BASE,TextureDatabase.ITEM_FILTER_BASE,TextureDatabase.ITEM_FILTER_BASE,TextureDatabase.ITEM_FILTER_BASE,
			TextureDatabase.ITEM_FILTER_BASE,TextureDatabase.ITEM_FILTER_BASE,TextureDatabase.ITEM_FILTER_BASE,TextureDatabase.ITEM_FILTER_BASE,
			TextureDatabase.DIAMONDCHEST,TextureDatabase.ITEM_FILTER_END,TextureDatabase.ITEM_FILTER_END,TextureDatabase.ITEM_FILTER_END,TextureDatabase.ITEM_FILTER_END))),
	
	INCINERATOR (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.INCINERATOR_BASE))),
	
	QUARRY (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.QUARRY_FTL,TextureDatabase.QUARRY_FTR,TextureDatabase.QUARRY_FBL,TextureDatabase.QUARRY_FBR,
			TextureDatabase.QUARRY_BTL,TextureDatabase.QUARRY_BTR,TextureDatabase.QUARRY_BBL,TextureDatabase.QUARRY_BBR,TextureDatabase.IRONCHEST))),
	QUARRY_ON (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.QUARRY_ON_FTL,TextureDatabase.QUARRY_ON_FTR,TextureDatabase.QUARRY_ON_FBL,TextureDatabase.QUARRY_ON_FBR,
			TextureDatabase.QUARRY_ON_BTL,TextureDatabase.QUARRY_ON_BTR,TextureDatabase.QUARRY_ON_BBL,TextureDatabase.QUARRY_ON_BBR,TextureDatabase.IRONCHEST))),
	
	CREMATOR (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.CREMATOR,TextureDatabase.CREMATOR,TextureDatabase.CREMATOR,TextureDatabase.CREMATOR,TextureDatabase.CREMATOR,
			TextureDatabase.CREMATOR,TextureDatabase.CREMATOR,TextureDatabase.CREMATOR))),
			
	CREMATOR_ON (new ArrayList<TextureDatabase>(Arrays.asList(TextureDatabase.CREMATOR_ON,TextureDatabase.CREMATOR_ON,TextureDatabase.CREMATOR_ON,TextureDatabase.CREMATOR_ON,
			TextureDatabase.CREMATOR_ON,TextureDatabase.CREMATOR_ON,TextureDatabase.CREMATOR_ON,TextureDatabase.CREMATOR_ON)));
	
	
	private ArrayList<TextureDatabase> texCo;
	
	TextureCollection(ArrayList<TextureDatabase> texCo){
		this.texCo = texCo;
		
	}
	
	public ArrayList<TextureDatabase> getCollection(){
		return texCo;
	}
	
	
	public boolean containsUUID(UUID uuid){
		for(TextureDatabase tex : texCo){
			if(tex.getUUID().equals(uuid)){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<UUID> getUUIDs(){
		ArrayList<UUID> uuids = new ArrayList<UUID>();
		for(TextureDatabase tex : texCo){
			uuids.add(tex.getUUID());
		}
		return uuids;
	}
	
	public ArrayList<ItemStack> getRawItemStackCollection(int amt){
		
		ArrayList<ItemStack> rawItemStackCollection = new ArrayList<ItemStack>();
		
		for(TextureDatabase tex : texCo){
			
			ItemStack head = VersionControl.getItemStack(GMaterial.PLAYER_HEAD_ITEM, amt);
		    if(tex.getURL().isEmpty())break;
		    
		    SkullMeta headMeta = (SkullMeta) head.getItemMeta();
		    GameProfile profile = new GameProfile(tex.getUUID(), null);
		    byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", tex.getURL()).getBytes());
		    profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
		    Field profileField = null;
		    try {
		        profileField = headMeta.getClass().getDeclaredField("profile");
		        profileField.setAccessible(true);
		        profileField.set(headMeta, profile);
		        
		    } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
		        e1.printStackTrace();
		    }
		    head.setItemMeta(headMeta);
		    
		    rawItemStackCollection.add(head);
		}
		
		return rawItemStackCollection;
		
	}
	
}
