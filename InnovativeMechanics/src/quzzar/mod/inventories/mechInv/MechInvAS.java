package quzzar.mod.inventories.mechInv;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class MechInvAS {

	private Inventory inv;
	private String name;
	private ArmorStand as;
	
	private int originalInvSize;
	
	public MechInvAS(String name, int invSize, ArmorStand as){
		this.inv = Bukkit.createInventory(null, invSize, name);
		this.name = name;
		this.as = as;
		this.originalInvSize = invSize;
	}
	
	public MechInvAS(String name, InventoryType type, ArmorStand as){
		this.inv = Bukkit.createInventory(null, type, name);
		this.name = name;
		this.as = as;
		this.originalInvSize = -1;
	}
	
	public MechInvAS(Inventory inv, ArmorStand as){
		this.inv = inv;
		this.name = inv.getName();
		this.as = as;
		this.originalInvSize = inv.getSize();
	}
	
	
	public Inventory getInventory(){
		return inv;
	}
	
	public int getOriginalInvSize(){
		return originalInvSize;
	}
	
	public void resetInventory(Inventory inv){
		this.inv = inv;
	}
	
	public String getName(){
		return name;
	}
	
	public ArmorStand getArmorStand(){
		return as;
	}
	
	public void delete(){
		inv.clear();
		inv.setMaxStackSize(1);
		MechInvASManager.listAS.remove(this);
	}
	
	
}
