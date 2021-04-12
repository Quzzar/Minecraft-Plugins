package quzzar.mod.mhu.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import quzzar.mod.HeadUnit;
import quzzar.mod.Main;
import quzzar.mod.MultiHeadUnit;
import quzzar.mod.Utility;
import quzzar.mod.Textures.TextureCollection;
import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.Utilities.MHU_Utilities;
import quzzar.mod.autocrafttable.CraftingInstance;
import quzzar.mod.autocrafttable.ItemStackData;
import quzzar.mod.blocks.PoweredMHUs;
import quzzar.mod.documents.CheckConfig;
import quzzar.mod.documents.ConfigManager;
import quzzar.mod.inventories.mechInv.MechInvAS;
import quzzar.mod.inventories.mechInv.MechInvASManager;
import quzzar.mod.mhu.LargeMachineUnit;
import quzzar.mod.mhu.MHURegistrar;
import quzzar.mod.mhu.ArmorStandGeneration.ASDirection;
import quzzar.mod.mhu.ArmorStandGeneration.ASPlacement;
import quzzar.mod.mhu.ArmorStandGeneration.ArmorStandDesign;
import quzzar.mod.sound.SoundDatabase;
import quzzar.mod.sound.SoundManager;

public class Auto_Crafting_Table extends LargeMachineUnit {
	
	
	private String name = this.getClass().getSimpleName();
	
	public Auto_Crafting_Table(BlockType machineType, TextureDatabase itemTexture, TextureCollection texCollection,
			TextureCollection poweredTexCollection) {
		
		super(machineType, itemTexture, texCollection, poweredTexCollection);
		
		
		CheckConfig.configChecker(MHURegistrar.settingName+"."+name+".Enable_Running_Sound", true);
		
		CheckConfig.configChecker(MHURegistrar.settingName+"."+name+".Enable_Completion_Sound", true);
		
		
		ArrayList<ASPlacement> placements = ASPlacement.getMachineBaseList();
		placements.add(new ASPlacement(0, 1, -1.425, ASDirection.FORWARD));
		placements.add(new ASPlacement(0, -1, -1.425, ASDirection.OPPOSITE));
		placements.add(new ASPlacement(0, 0, -0.40, ASDirection.LEFT));
		
		setASPlacements(placements);
		
		
		setSpeed(10);
		
	}
	
	@Override
	public void create(Block placedBlock, Player p) {
		
		/// Set Variables
		
		 String invName1 = "[Output] Crafted Items";
		 String invName2 = "[Input] Raw Materials";
		 String craftInvName3 = "Crafting Layout";
		 int invSize = 18;
		
		///

			ArrayList<ItemStack> itsCol = this.getTexCollection().getRawItemStackCollection(1);
			
			HeadUnit headunit = new HeadUnit(this.getItemTexture());
			
			
			BlockFace fbf = Utility.getFaceToRight(Utility.getFront((Skull)placedBlock.getState()));
	        
			Location centerLocation = placedBlock.getLocation();
			centerLocation.add(0.5, 0, 0.5);
			
			ArrayList<ArmorStand> asList = ArmorStandDesign.createStructure(centerLocation, this.getASPlacements(), itsCol, fbf);
			
	        
			MechInvASManager.createNew(invName1,invSize,asList.get(8));
		    MechInvASManager.createNew(invName2,invSize,asList.get(9));
		    
		    MechInvASManager.createNew(craftInvName3,InventoryType.DROPPER,asList.get(10));
		    
		    
	        
	        MultiHeadUnit mhu = MultiHeadUnit.createNew(centerLocation, headunit, asList, placedBlock, fbf);
	        
	        mhu.setCreatorPlayer(p);
	        
	        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
				  public void run() {
					  
					  placedBlock.setType(Main.fillerType);
					  
				  }
			}, 5L);
		
	}

	@Override
	public Object interact(MultiHeadUnit mhu, ArmorStand as) {
		
		
		MechInvAS mechinv = MechInvASManager.getMechInventory(as);
		
		if(mechinv != null && as.equals(mhu.getArmorStands().get(10))){
			
			return mechinv.getInventory();
			
		} else if(mechinv != null){
			
			SoundManager.playSound(as.getEyeLocation(), SoundDatabase.MINICHEST_OPEN);
			return mechinv.getInventory();
			
		} else {
			PoweredMHUs.toggle(mhu, as.getEyeLocation(), this.getPoweredTexCollection(), this.getTexCollection());
			return null;
		}
		
		
	}

	@Override
	public void updateTask(MultiHeadUnit mhu) {
		
		
		if(PoweredMHUs.isOn(mhu)){
			
			Location centerLocation = mhu.getCenterLocation();
			
			if(ConfigManager.getInstance().getConfig().getBoolean(MHURegistrar.settingName+"."+name+".Enable_Running_Sound")){
				SoundManager.playSound(centerLocation, SoundDatabase.MACHINE_RUNNING);
			}
			
			
			if(mhu.getVariable1()>getSpeed()-mhu.getUpgrades().getSpeed()*2){
				
				mhu.setVariable1(0);
				
				MechInvAS outputMechInv = mhu.getInventories().get(0);
				MechInvAS inputMechInv = mhu.getInventories().get(1);
				MechInvAS layoutMechInv = mhu.getInventories().get(2);
				
				Inventory layoutInv = layoutMechInv.getInventory();
				Inventory inputInv = inputMechInv.getInventory();
				
				
				boolean found = false;
				
				
				Iterator<Recipe> itr = Bukkit.recipeIterator();
				
				crafting:
				while(itr.hasNext()){
					Recipe r = itr.next();
					
					
					/////
					
					if(r instanceof ShapedRecipe){
						
						ShapedRecipe shR = (ShapedRecipe) r;
						
						ArrayList<CraftingInstance> craftInstance = new ArrayList<CraftingInstance>();
						
						for(int i=0; i<layoutInv.getSize(); i++){
							
							if(layoutInv.getItem(i)==null){
								craftInstance.add(new CraftingInstance(layoutInv.getItem(i)));
							}else{
								ItemStack iNew = layoutInv.getItem(i).clone();
								iNew.setAmount(1);
								
								/*
								if(iNew.getType().equals(Material.WOOD)){
									iNew.setDurability((short) 32767);
								} else if(iNew.getType().equals(Material.LOG)||iNew.getType().equals(Material.LOG_2)){
									iNew.setDurability((short) 0);
								} else if(iNew.getType().equals(Material.WOOD_STEP)){
									iNew.setDurability((short) 0);
								}
								*/
								
								craftInstance.add(new CraftingInstance(iNew));
							}
							
				        }
						
						reduceCraftInstance(craftInstance);
						
						
						Map<Character,ItemStack> ingredientsMap = new HashMap<Character,ItemStack>();
						
						for(CraftingInstance instance : craftInstance){
							if(instance.getItemStack()==null){
								ingredientsMap.put(intToCharacter(craftInstance.indexOf(instance)), null);
							}else{
								ingredientsMap.put(intToCharacter(craftInstance.indexOf(instance)), instance.getItemStack());
							}
						}
						
						
						/*
						if(r.getResult().isSimilar(ItemsList.Wooden_Crate_Empty(1))){
							
							
							for (Character name : shR.getIngredientMap().keySet()){
								
					            String key = name.toString();
					            
					            String value;
					            
					            int data;
					            
					            if(shR.getIngredientMap().get(name)==null){
					            	value = "null";
					            	data = 0;
					            }else {
					            	value = shR.getIngredientMap().get(name).toString();
					            	data = shR.getIngredientMap().get(name).getDurability();
					            }
					            
					            
					            Bukkit.broadcastMessage(key + " | "+ value + " | " + data);
					            
							}
							
							Bukkit.broadcastMessage("-----------");
							
							Bukkit.broadcastMessage(""+ingredientsMap.size());
							
							for (Character name : ingredientsMap.keySet()){
								
					            String key = name.toString();
					            
					            String value;
					            
					            int data;
					            
					            if(ingredientsMap.get(name)==null){
					            	value = "null";
					            	data = 0;
					            }else {
					            	value = ingredientsMap.get(name).toString();
					            	data = ingredientsMap.get(name).getDurability();
					            }
					            
					            
					            Bukkit.broadcastMessage(key + " | "+ value + " | " + data + "<Current>");
					            
							} 
							
						}
						*/
						
						
						if(shR.getIngredientMap().equals(ingredientsMap)){
							

							ArrayList<ItemStackData> itemSlots = new ArrayList<ItemStackData>();
							
							
							
							int size_count = 0;
							
							for(CraftingInstance instance : craftInstance){
								if(instance.getItemStack()!=null){
									size_count++;
								}
							}
							
							
							for(ItemStack item : inputInv.getContents()){
								if(item!=null){
									ItemStack itemNew = item.clone();
									
									/*
									if(itemNew.getType().equals(Material.WOOD)){
										itemNew.setDurability((short) 32767); 
									} else if(itemNew.getType().equals(Material.LOG)||itemNew.getType().equals(Material.LOG_2)){
										itemNew.setDurability((short) 0);
									} else if(itemNew.getType().equals(Material.WOOD_STEP)){
										itemNew.setDurability((short) 0);
									}
									*/
									
									
									int amt = 0;
									
									ArrayList<CraftingInstance> removeList = new ArrayList<CraftingInstance>();
									
									for(CraftingInstance instance : craftInstance){
										if(instance.getItemStack()!=null){
											
											if(instance.getItemStack().isSimilar(itemNew)&&itemNew.getAmount()>amt){
												
												itemSlots.add(new ItemStackData(inputInv.getItem(inputInv.first(item)), inputInv.first(item)));
												amt++;
												removeList.add(instance);
											}
											
											
										}
									}
									
									craftInstance.removeAll(removeList);
									
								}
								
							}
							
							
							if(itemSlots.size()==size_count){
								
								for(ItemStackData n : itemSlots){
									ItemStack i = n.getItemStack();
									i.setAmount(i.getAmount()-1);
									inputInv.setItem(n.getIndex(), i);
								}
								
								found = true;
								
								if(ConfigManager.getInstance().getConfig().getBoolean(MHURegistrar.settingName+"."+name+".Enable_Completion_Sound")){
									SoundManager.playSound(centerLocation, SoundDatabase.MACHINE_SUCCESS);
								}
								
								Utility.addItemToInventory(outputMechInv.getInventory(), shR.getResult(),
										outputMechInv.getArmorStand().getEyeLocation());
								
								break crafting;
								
							}
							
						}
						
						
						
					} else if(r instanceof ShapelessRecipe){
						
						ShapelessRecipe shless = (ShapelessRecipe) r;
						
						List<ItemStack> craftInstance = new ArrayList<ItemStack>();
						
						for(int i=0; i<layoutInv.getSize(); i++){
							if(layoutInv.getItem(i)!=null){
								
								ItemStack iNew = layoutInv.getItem(i).clone();
								iNew.setAmount(1);
								
								/*
								if(iNew.getType().equals(Material.WOOD)){
									iNew.setDurability((short) 32767); 
								} else if(iNew.getType().equals(Material.LOG)||iNew.getType().equals(Material.LOG_2)){
									iNew.setDurability((short) 0);
								} else if(iNew.getType().equals(Material.WOOD_STEP)){
									iNew.setDurability((short) 0);
								}
								*/
								
								craftInstance.add(iNew);
							}
				        }
						
						/*
						if(shless.getResult().getType().equals(Material.WOOD_STEP)){
							
							for(ItemStack i : shless.getIngredientList()){
								Bukkit.broadcastMessage(i+" | "+i.getDurability());
							}
							
							Bukkit.broadcastMessage("--------");
							
							for(ItemStack i : craftInstance){
								Bukkit.broadcastMessage(i+" | "+i.getDurability());
							}
							
						}
						*/
						
						
						if(listsHaveSameElements(shless.getIngredientList(), craftInstance)){
							
							
							ArrayList<ItemStackData> itemSlots = new ArrayList<ItemStackData>();
							
							
							
							int size_count = 0;
							
							for(ItemStack instance : craftInstance){
								if(instance!=null){
									size_count++;
								}
							}
							
							
							for(ItemStack item : inputInv.getContents()){
								if(item!=null){
									ItemStack itemNew = item.clone();
									
									/*
									if(itemNew.getType().equals(Material.WOOD)){
										itemNew.setDurability((short) 32767); 
									} else if(itemNew.getType().equals(Material.LOG)||itemNew.getType().equals(Material.LOG_2)){
										itemNew.setDurability((short) 0);
									} else if(itemNew.getType().equals(Material.WOOD_STEP)){
										itemNew.setDurability((short) 0);
									}
									*/
									
									
									int amt = 0;
									
									ArrayList<ItemStack> removeList = new ArrayList<ItemStack>();
									
									for(ItemStack instance : craftInstance){
										if(instance!=null){
											
											if(instance.isSimilar(itemNew)&&itemNew.getAmount()>amt){
												
												
												itemSlots.add(new ItemStackData(inputInv.getItem(inputInv.first(item)), inputInv.first(item)));
												amt++;
												removeList.add(instance);
											}
											
											
										}
									}
									
									craftInstance.removeAll(removeList);
									
								}
								
							}
							
							
							if(itemSlots.size()==size_count){
								for(ItemStackData n : itemSlots){
									ItemStack i = n.getItemStack();
									i.setAmount(i.getAmount()-1);
									inputInv.setItem(n.getIndex(), i);
								}
								
								found = true;
								
								if(ConfigManager.getInstance().getConfig().getBoolean(MHURegistrar.settingName+"."+name+".Enable_Completion_Sound")){
									SoundManager.playSound(centerLocation, SoundDatabase.MACHINE_SUCCESS);
								}
								
								Utility.addItemToInventory(outputMechInv.getInventory(), shless.getResult(),
										outputMechInv.getArmorStand().getEyeLocation());
								
								break crafting;
								
							}
							
							
						}
						
						
					}
					/////
						
				}
				
				if(!found){
					PoweredMHUs.turnOff(mhu, centerLocation, TextureCollection.AUTO_CRAFTING_TABLE);
				}
				
				
				
				
			}
			
			
			mhu.setVariable1(mhu.getVariable1()+1);
			
		}
		
		
	}

	@Override
	public void delete(MultiHeadUnit mhu) {
		
		
		MHU_Utilities.deleteInventories(mhu);
		
		SoundManager.playSound(mhu.getCenterLocation(), SoundDatabase.MACHINE_BREAK);
		
		for(ArmorStand as : mhu.getArmorStands()){
			as.remove();
		}
		
		mhu.getBlock().setType(Material.AIR);
		
		Main.MHUList.remove(mhu);
		
		
	}

	
	private static void reduceCraftInstance(ArrayList<CraftingInstance> craftInstance){
		ArrayList<CraftingInstance> removeList = new ArrayList<CraftingInstance>();
		
		checkRemoveIndex(craftInstance, removeList, 0, 1, 2);
		checkRemoveIndex(craftInstance, removeList, 3, 4, 5);
		checkRemoveIndex(craftInstance, removeList, 6, 7, 8);
		
		checkRemoveIndex(craftInstance, removeList, 0, 3, 6);
		checkRemoveIndex(craftInstance, removeList, 1, 4, 7);
		checkRemoveIndex(craftInstance, removeList, 2, 5, 8);
		
		
		craftInstance.removeAll(removeList);
		
	}
	
	private static void checkRemoveIndex(ArrayList<CraftingInstance> craftInstance, ArrayList<CraftingInstance> list, int x, int y, int z){
		if(craftInstance.get(x).getItemStack()==null&&craftInstance.get(y).getItemStack()==null&&craftInstance.get(z).getItemStack()==null){
			if(!list.contains(craftInstance.get(x))){
				list.add(craftInstance.get(x));
			}
			if(!list.contains(craftInstance.get(y))){
				list.add(craftInstance.get(y));
			}
			if(!list.contains(craftInstance.get(z))){
				list.add(craftInstance.get(z));
			}
		}
	}
	
	
	private static Character intToCharacter(int i){
		return String.valueOf((char)(i + 97)).charAt(0);
	}
	
	
	private static <E> boolean listsHaveSameElements(final List<E> l1, final List<E> l2) {
	    final Set<E> set = new HashSet<>(l1);
	    return l1.size() == l2.size() && set.containsAll(l2);
	}
	
	
	
	
}
