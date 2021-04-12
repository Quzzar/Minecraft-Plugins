package com.quzzar.im.structures;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.quzzar.craftmeta.CraftMeta;
import com.quzzar.craftmeta.tags.EntityDataTag;
import com.quzzar.im.machines.Machine;
import com.quzzar.im.machines.MachineRegistrar;
import com.quzzar.im.machines.MachineType;
import com.quzzar.im.structures.data.StructDataTag;
import com.quzzar.im.structures.loading.SimpleLocation;
import com.quzzar.im.structures.loading.asbuilding.ASManager;
import com.quzzar.im.structures.loading.inv.InvManager;
import com.quzzar.im.structures.loading.inv.InvTag;
import com.quzzar.im.structures.loading.inv.StructInv;

public class Structure implements Serializable {
	
	private static final long serialVersionUID = -1681805154342207892L;
	
	private final SimpleLocation centerLoc;
	private BlockFace facingDirection;
	
	private Hashtable<InvTag, StructInv> inventories;
	
	private Hashtable<StructDataTag, Integer> metaInts;
	private Hashtable<StructDataTag, String> metaStrs;
	
	private UUID creatorPlayerUUID;
	private UUID uuid;
	
	private MachineType machineType;
	
	private boolean running;
	private boolean powerCooldown;
	
	public Structure(Location centerLoc, BlockFace facingDirection, MachineType machineType, Player creatorPlayer){
		
		this.centerLoc = new SimpleLocation(centerLoc);
		this.facingDirection = facingDirection;
		
		this.machineType = machineType;
		
		this.inventories = new Hashtable<InvTag, StructInv>();
		this.creatorPlayerUUID = creatorPlayer.getUniqueId();
		
		this.metaInts = new Hashtable<StructDataTag, Integer>();
		this.metaStrs = new Hashtable<StructDataTag, String>();
		
		this.uuid = UUID.randomUUID();
		
		this.running = false;
		
	}
	
	public SimpleLocation getCenterLocation() {
		return centerLoc;
	}
	
	///
	
	public InvTag getInvTag(ArmorStand armorStand) {
		if(CraftMeta.entityHasData(armorStand, EntityDataTag.MACHINE_INV_TAG)) {
			return (InvTag) CraftMeta.entityGet(armorStand, EntityDataTag.MACHINE_INV_TAG);
		}
		return null;
	}
	
	public StructInv addStructInventory(InvTag tag, Inventory inv, String invTitle) {
		StructInv structInv = new StructInv(inv, invTitle);
		inventories.put(tag, structInv);
		return structInv;
	}
	
	public StructInv getStructInventory(InvTag tag) {
		if(tag == null) {return null;}
		return inventories.get(tag);
	}
	
	public void removeStructInventory(InvTag tag) {
		inventories.remove(tag);
	}
	
	///
	
	public Inventory getInventory(InvTag tag) {
		return InvManager.getInventory(this, tag);
	}
	
	public void saveInventories() {
		InvManager.saveInventories(this);
	}
	
	///
	
	public UUID getCreatorPlayerUUID() {
		return creatorPlayerUUID;
	}
	
	public BlockFace getFacingDirection() {
		return facingDirection;
	}
	
	public MachineType getMachineType() {
		return machineType;
	}
	
	public Machine getMachine() {
		return MachineRegistrar.getMachine(machineType);
	}
	
	public UUID getID() {
		return uuid;
	}
	
	public void load() {
		ASManager.spawnArmorStands(this);
	}
	
	public void unload() {
		ASManager.despawnArmorStands(this);
	}
	
	public void refresh() {
		if(isLoaded()) {
			ASManager.refreshArmorStands(this);
		}
	}
	
	public boolean isLoaded() {
		return ASManager.isSpawned(this);
	}
	
	public boolean hasMetaInt(StructDataTag tag) {
		return metaInts.containsKey(tag);
	}
	
	public void setMetaInt(StructDataTag tag, int value) {
		metaInts.put(tag, value);
	}
	
	public int getMetaInt(StructDataTag tag) {
		return metaInts.get(tag);
	}
	
	public boolean hasMetaStr(StructDataTag tag) {
		return metaStrs.containsKey(tag);
	}
	
	public void setMetaStr(StructDataTag tag, String value) {
		metaStrs.put(tag, value);
	}
	
	public String getMetaStr(StructDataTag tag) {
		return metaStrs.get(tag);
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void setRunning(boolean toRunning) {
		
		if(!machineType.isPowerable()) {this.running = false; return;}
		
		if(hasPowerCooldown()) {
			return;
		} else {
			if(toRunning) {
				setPowerCooldown(true);
			}
		}
		
		if(this.running != toRunning) {
			this.running = toRunning;
			refresh();
		} else {
			this.running = toRunning;
		}
		
	}
	
	public void toggleRunning() {
		setRunning(!isRunning());
	}
	
	
	public boolean hasPowerCooldown() {
		return powerCooldown;
	}
	
	public void setPowerCooldown(boolean powerCooldown) {
		this.powerCooldown = powerCooldown;
	}
	
}
