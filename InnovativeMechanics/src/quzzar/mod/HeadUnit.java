package quzzar.mod;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.quzzar.im.versioncontrol.VersionControl;
import com.quzzar.im.versioncontrol.global.material.GMaterial;

import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.Textures.Traits.HUStrength;
import quzzar.mod.blocks.HeadUnitBlock;
import quzzar.mod.inventories.mechInv.MechInv;
import quzzar.mod.inventories.mechInv.MechInvManager;

public class HeadUnit {

	private TextureDatabase tex;
	
	private UUID id;
	
	private int var = -1;
	
	private int var_temp = 0;
	
	public static List<UUID> listIDs = new ArrayList<UUID>();
	
	public HeadUnit(TextureDatabase tex){
		this.tex = tex;
		this.getNewID();
	}
	
	public HeadUnit(UUID uuid){
		this.tex = TextureDatabase.getTexture(uuid);
		this.getNewID();
	}
	
	public HeadUnit(String name){
		this.tex = TextureDatabase.getTexture(name);
		this.getNewID();
	}
	
	
	// From Load //
	public HeadUnit(String name, UUID setID){
		this.tex = TextureDatabase.getTexture(name);
		this.id = setID;
	}
	
	
	public UUID getID(){
		return id;
	}
	
	public HeadUnit clone(){
		HeadUnit hu = new HeadUnit(this.getTexture());
		hu.setVar(this.getVar());
		hu.id = this.getID();
		return hu;
	}
	
	public int getVar(){
		return var;
	}
	
	public void setVar(int var){
		this.var = var;
	}
	
	
	public int getVarTemp(){
		return this.var_temp;
	}
	
	public void setVarTemp(int var_temp){
		this.var_temp = var_temp;
	}
	
	public TextureDatabase getTexture(){
		return tex;
	}
	
	public BlockType getType(){
		return tex.getType();
	}
	
	public HUStrength getStrength(){
		return tex.getStrength();
	}
	
	public ItemStack getRawItemStack(int amt){
		
		ItemStack head = VersionControl.getItemStack(GMaterial.PLAYER_HEAD_ITEM, amt);
		
	    if(tex.getURL().isEmpty())return head;
	    
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
	    return head;
	}
	
	public Material getMaterial(){
		return getRawItemStack(1).getType();
	}
	
	
	public static void delete(Block b){
		HeadUnitBlock hubRemove = null;
		for(HeadUnitBlock hub : Main.HUBList){
			if (hub.getBlock().equals(b)){
				try{
					listIDs.remove(hub.getHeadUnit().getID());
					hubRemove = hub;
					if(hub.getHeadUnit().getType().equals(BlockType.STORAGE)){
						MechInv mechinv = MechInvManager.getMechInventories(hub.getHeadUnit()).get(0);
						for(ItemStack i : mechinv.getInventory().getStorageContents()){
							if(i!=null){
								mechinv.getInventory().remove(i);
								b.getWorld().dropItem(b.getLocation(), i);
							}
						}
						mechinv.delete();
					}
				}catch (IndexOutOfBoundsException e){
					//
				}
				
			}
		}
		if(hubRemove!=null){
			Main.HUBList.remove(hubRemove);
		}
		
	}
	
	public void addToHeadUnitBlockList(Block b){
		Main.HUBList.add(new HeadUnitBlock(this, b));
	}
	
	
	
	
	public void getNewID(){
		
		UUID id;
		
		do{
			id=UUID.randomUUID();
		}while(listIDs.contains(id));
		
		this.id = id;
		listIDs.add(id);
	}
	
	
}
