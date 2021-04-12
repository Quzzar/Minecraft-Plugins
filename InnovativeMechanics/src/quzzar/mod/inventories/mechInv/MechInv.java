package quzzar.mod.inventories.mechInv;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import quzzar.mod.HeadUnit;

public class MechInv {

	private Inventory inv;
	private String name;
	private HeadUnit headunit;
	
	public MechInv(String name, int invSize, HeadUnit headunit){
		this.inv = Bukkit.createInventory(null, invSize, name);
		this.name = name;
		this.headunit = headunit;
	}
	
	public MechInv(String name, InventoryType type, HeadUnit headunit){
		this.inv = Bukkit.createInventory(null, type, name);
		this.name = name;
		this.headunit = headunit;
	}
	
	public MechInv(Inventory inv, HeadUnit headunit){
		this.inv = inv;
		this.name = inv.getName();
		this.headunit = headunit;
	}
	
	
	public Inventory getInventory(){
		return inv;
	}
	
	public String getName(){
		return name;
	}
	
	public HeadUnit getHeadUnit(){
		return headunit;
	}
	
	public void delete(){
		inv.clear();
		inv.setMaxStackSize(1);
		MechInvManager.mapInvs.remove(this.getHeadUnit().getID());
	}
	
	
}
