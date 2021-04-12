package quzzar.mod;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.inventory.InventoryType;
import quzzar.mod.inventories.mechInv.MechInvAS;
import quzzar.mod.inventories.mechInv.MechInvASManager;
import quzzar.mod.inventories.mechInv.MechInvManager;
import quzzar.mod.mhu.LargeMachineUnit;
import quzzar.mod.mhu.MHURegistrar;

public class MultiHeadUnit {

	private final ArrayList<ArmorStand> asList;
	private final Block b;
	private final Location centerLoc;
	private BlockFace face;
	private HeadUnit mechUnit;
	private int damage;
	private int variable1;
	private String variable2;
	private int variable3;
	
	private ArrayList<MechInvAS> inventories = new ArrayList<MechInvAS>();
	
	private MachineUpgrades upgrades = null;
	
	private OfflinePlayer creatorPlayer = null;
	
	public MultiHeadUnit(Location centerLoc, HeadUnit mechUnit, ArrayList<ArmorStand> asList, Block b, BlockFace face){
		this.asList = asList;
		this.b = b;
		this.face = face;
		this.centerLoc = centerLoc;
		this.mechUnit = mechUnit;
		this.damage = 0;
		this.variable1 = -1;
		this.variable2 = "off";
		this.variable3 = -1;
		
		setInventories();
		
		for(ArmorStand as : asList) {
			ArmorStandData.serializeArmorStand(this, as);
		}
	}
	
	
	public MultiHeadUnit(Location centerLoc, HeadUnit mechUnit, ArrayList<ArmorStand> asList, Block b, BlockFace face, int var1, String var2, int var3){
		this.asList = asList;
		this.b = b;
		this.face = face;
		this.centerLoc = centerLoc;
		this.mechUnit = mechUnit;
		this.damage = 0;
		this.variable1 = var1;
		this.variable2 = var2;
		this.variable3 = var3;
		
		setInventories();
		
		for(ArmorStand as : asList) {
			ArmorStandData.serializeArmorStand(this, as);
		}
	}
	
	public ArrayList<ArmorStand> getArmorStands(){
		return asList;
	}
	
	public Block getBlock(){
		return b;
	}
	
	public BlockFace getBlockFace(){
		return face;
	}
	
	public Location getCenterLocation(){
		return centerLoc.clone();
	}
	
	public HeadUnit getItemHU(){
		return mechUnit;
	}
	
	public LargeMachineUnit getMachineType(){
		
		for(LargeMachineUnit machine : MHURegistrar.typesList){
			if (machine.getMachineType().equals(this.getItemHU().getType())){
				
				return machine;
				
			}
		}
		
		return null;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public void setDamage(int damage){
		this.damage = damage;
	}
	
	public int getVariable1(){
		return variable1;
	}
	
	public void setVariable1(int variable){
		this.variable1 = variable;
	}
	
	public String getVariable2(){
		return variable2;
	}
	
	public void setVariable2(String variable){
		this.variable2 = variable;
	}
	
	public int getVariable3(){
		return variable3;
	}
	
	public void setVariable3(int variable){
		this.variable3 = variable;
	}
	
	
	public OfflinePlayer getCreatorPlayer() {
		return creatorPlayer;
	}

	public void setCreatorPlayer(OfflinePlayer creatorPlayer) {
		this.creatorPlayer = creatorPlayer;
	}
	
	public MachineUpgrades getUpgrades() {
		return upgrades;
	}
	
	public void setUpgrades(MachineUpgrades upgrades) {
		this.upgrades = upgrades;
	}
	
	public ArrayList<MechInvAS> getInventories() {
		return inventories;
	}

	public void setInventories() {
		
		for(ArmorStand as : getArmorStands()) {
			
			MechInvAS mechInv = MechInvASManager.getMechInventory(as);
			
			if(mechInv!=null) {
				getInventories().add(mechInv);
			}
			
		}
	}
	
	
	public void delete(){
		
		for(LargeMachineUnit machine : MHURegistrar.typesList){
			
			if (machine.getMachineType().equals(this.getItemHU().getType())){
				
				machine.delete(this);
				return;
				
			}
			
		}
		
	}
	
	
	
	public static MultiHeadUnit createNew(Location centerLoc, HeadUnit headUnit, ArrayList<ArmorStand> asList, Block b, BlockFace face){
		
		MultiHeadUnit mhu = new MultiHeadUnit(centerLoc, headUnit, asList, b, face);
		
		if(headUnit.getType().isUpgradable()) {
			MechInvManager.createNew("Upgrades Panel",InventoryType.HOPPER,headUnit);
			
			mhu.setUpgrades(new MachineUpgrades());
		}
		
		Main.MHUList.add(mhu);
		
		return mhu;
	}
	
	public static MultiHeadUnit createNewLoad(Location centerLoc, HeadUnit headUnit, ArrayList<ArmorStand> asList,  Block b, BlockFace face, int var1, String var2, int var3){
		
		MultiHeadUnit mhu = new MultiHeadUnit(centerLoc, headUnit, asList, b, face, var1, var2, var3);
		
		if(headUnit.getType().isUpgradable()) {
			
			mhu.setUpgrades(new MachineUpgrades());
			
		}
		
		Main.MHUList.add(mhu);
		
		return mhu;
	}
	
	
	
	
	
}
