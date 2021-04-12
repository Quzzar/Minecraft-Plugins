package quzzar.mod;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import quzzar.mod.ListenerSubsets.ChestListener;
import quzzar.mod.ListenerSubsets.CrateListener;
import quzzar.mod.Textures.TextureCollection;
import quzzar.mod.Textures.TextureDatabase;
import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.Utilities.HU_Utilities;
import quzzar.mod.Utilities.MHU_Utilities;
import quzzar.mod.blocks.ActivatedMechBlock;
import quzzar.mod.blocks.BreakBlock;
import quzzar.mod.blocks.HeadUnitBlock;
import quzzar.mod.commands.SaveCommand;
import quzzar.mod.documents.CheckConfig;
import quzzar.mod.documents.ConfigManager;
import quzzar.mod.inventories.mechInv.MechInvManager;
import quzzar.mod.mhu.LargeMachineUnit;
import quzzar.mod.mhu.MHURegistrar;
import quzzar.mod.mhu.types.Industrial_Furnace;
import quzzar.mod.mhu.types.Item_Filter;
import quzzar.mod.pipes.PipeDesignerManager;
import quzzar.mod.recipeviewer.RecipeViewer;
import quzzar.mod.sound.SoundDatabase;
import quzzar.mod.sound.SoundManager;
import quzzar.mod.thedark.DarkListener;

public class MainListener implements Listener{
	
	
	
	@EventHandler
	public void onChunkUnload(ChunkUnloadEvent e){
		for(MultiHeadUnit mhu : Main.MHUList){
			if(e.getChunk().equals(mhu.getCenterLocation().getChunk())){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent e){
		
		if(Utility.checkMechanical(e.getBlockAgainst())){
			BlockType bt = Utility.getTexture(e.getBlockAgainst()).getType();
			if(bt.equals(BlockType.STORAGE)||bt.equals(BlockType.GLOBALSTORAGE)){
				e.setCancelled(true);
			}
		}
		
		
		if(e.canBuild()&&!e.isCancelled()){
			
			Block b = e.getBlockPlaced();
			
			ItemStack iMain = e.getPlayer().getInventory().getItemInMainHand();
			ItemStack iSide = e.getPlayer().getInventory().getItemInOffHand();
			
			if(isWrench(iMain)||isWrench(iSide)){
				e.setCancelled(true);
				return;
			}
			
			
			if(Utility.checkMechanical(b)){
				
				HeadUnit headunit = Utility.makeHeadUnit(b);
				
				if(!Utility.isNearToLargeMachine(b)) {
					
					for(LargeMachineUnit machine : MHURegistrar.typesList){
						
						if (machine.getItemTexture().equals(headunit.getTexture())){
							
							if(Main.MHUList.size()>=ConfigManager.getInstance().getConfig().getInt("Max_Large_Machine_Limit")) {
								
								Utility.tellSender(e.getPlayer(), "Can't place! The max large machine limit has been reached!");
								e.setCancelled(true);
								
							} else {
								
								machine.create(b, e.getPlayer());
								
								SaveCommand.save();
								
							}
							
							return;
							
						}
						
					}
					
					switch(headunit.getTexture()){
						case MINICHEST: {
							
							HU_Utilities.createHB_Storage(b, headunit, "Mini Chest", 18);
							
						} break;
						case DIRTCHEST: {
							
							HU_Utilities.createHB_Storage_By_Type(b, headunit, "Dirt Chest (Mini)", InventoryType.HOPPER);
							
						} break;
						case IRONCHEST: {
							
							HU_Utilities.createHB_Storage(b, headunit, "Iron Chest (Mini)", 36);
							
						} break;
						case GOLDCHEST: {
							
							HU_Utilities.createHB_Storage(b, headunit, "Gold Chest (Mini)", 45);
							
						} break;
						case QUARTZCHEST: {
							
							HU_Utilities.createHB_Storage(b, headunit, "Quartz Chest (Mini)", 45);
							
						} break;
						case EMERALDCHEST: {
							
							HU_Utilities.createHB_Storage(b, headunit, "Emerald Chest (Mini)", 54);
							
						} break;
						case DIAMONDCHEST: {
							
							HU_Utilities.createHB_Storage(b, headunit, "Diamond Chest (Mini)", 54);
							
						} break;
						case InterDCHEST: {
							
							HU_Utilities.createHB_Global_Storage(b, headunit);
							
						} break;
						default: {
							HU_Utilities.createHB_Blank(b, headunit);
						} break;
					}
					
				} else {
					e.setCancelled(true);
				}
				
			}
		}
		
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent e){
		
		if(!e.isCancelled()){
			if(Utility.checkMechanical(e.getBlock())){
				
				if(e.getPlayer().hasPermission("innomech.machines.place.pipe")){
					ItemStack iMain = e.getPlayer().getInventory().getItemInMainHand();
					if(Utility.checkMechanical(iMain)){
						if(Utility.getTexture(iMain).equals(TextureDatabase.PIPE_item)){
							e.setCancelled(true);
							return;
						}
					}
				}
				
				///
				
				e.setDropItems(false);
				
				TextureDatabase tex = Utility.getTexture(e.getBlock());
				
				if(SimulateStrength.attemptBreak(e.getPlayer(), tex, e.getPlayer().getInventory().getItemInMainHand())){
					BreakBlock.headBreak(e.getBlock(), tex);
				}else if (e.getPlayer().hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
					e.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
				}
				
			}else if (e.getBlock().getState() instanceof InventoryHolder) {
				// So pipes connected will fall off
				InventoryHolder invHolder = (InventoryHolder) e.getBlock().getState();
				invHolder.getInventory().setMaxStackSize(1);
			}
			
		}
		
	}
	
	
	@EventHandler(priority = EventPriority.LOW)
	public void onArmorStandInteract(PlayerArmorStandManipulateEvent e){
		if(!ArmorStandData.isNatural(e.getRightClicked())){
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onArmorStandInteract(PlayerInteractAtEntityEvent e){
		
		if(!e.isCancelled()){
			
			if(e.getRightClicked() instanceof ArmorStand){
				ArmorStand as = (ArmorStand) e.getRightClicked();
				if(Utility.checkMechanical(as.getHelmet())&&!ArmorStandData.isNatural(as)){
					
					MultiHeadUnit mhu = MHU_Utilities.getMHU(as);
					
					if(mhu!=null){
						
						
						ItemStack iMain = e.getPlayer().getInventory().getItemInMainHand();
						ItemStack iSide = e.getPlayer().getInventory().getItemInOffHand();
						
						
						if(e.getPlayer().hasPermission("innomech.use.wrench")){
							
							if(isWrench(iMain)||isWrench(iSide)){
								BreakBlock.MHUPlayerBreak(mhu, as.getEyeLocation(), true, e.getPlayer());
								e.setCancelled(true);
								return;
							}
							
						}
						
						
						
						if(ArmorStandData.isEnabled(as)&&e.getPlayer().hasPermission("innomech.machines.place.pipe")){
							
							if(Utility.checkMechanical(iMain)){
								if(Utility.getTexture(iMain).equals(TextureDatabase.PIPE_item)){
									
									if(Utility.getTexture(as.getHelmet()).getType().equals(BlockType.STORAGE)){
										
										PipeDesignerManager.attemptComplete(as.getEyeLocation(), e.getPlayer());
										
										e.setCancelled(true);
										return;
										
									} else if (mhu.getItemHU().getType().equals(BlockType.MHU_Industrial_Furance)){
										
										PipeDesignerManager.attemptComplete(Industrial_Furnace.getStorageArmorStand(mhu, as).getEyeLocation(), e.getPlayer());
										
										e.setCancelled(true);
										return;
										
									}
									
								}
							}
							
						}
						
						
						if(ArmorStandData.isEnabled(as)&&e.getPlayer().hasPermission("innomech.machines.interact")){
							
							for(LargeMachineUnit machine : MHURegistrar.typesList){
								
								if(mhu.getItemHU().getType().equals(machine.getMachineType())){
									
									// Item Filter > Filter Menus
									if(machine.getTexCollection().equals(TextureCollection.ITEM_FILTER)) {
										if(e.getPlayer().isSneaking()) {
											((Item_Filter) machine).interactShift(mhu, as, e.getPlayer());
											return;
										}
									}
									//
									
									// Machines > Interact (If Player NOT Sneaking)
									if(!e.getPlayer().isSneaking()) {
										
										Object returnedObj = machine.interact(mhu, as);
										
										if(returnedObj!=null) {
											if(returnedObj instanceof Inventory) {
												e.getPlayer().openInventory((Inventory) returnedObj);
												return;
											}
										}
										
									}
									//
									
									// Machines > Upgrade Panel
									if(machine.getMachineType().isUpgradable()) {
										if(e.getPlayer().isSneaking()) {
											e.getPlayer().openInventory(MechInvManager.getMechInventories(mhu.getItemHU()).get(0).getInventory());
											return;
										}
									}
									//
									
								}
								
							}
						}
						
					}
				}
			}
			
		}
		
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteract(PlayerInteractEvent e){
		
		Player p = e.getPlayer();
		
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR)){
			
			ItemStack i = p.getInventory().getItemInMainHand();
			
			
			boolean opened = false;
			
			if(i != null){
				if(Utility.checkMechanical(i)){
					if (Utility.getTexture(i).getType().equals(BlockType.CRATE)){
						CrateListener.openCrate(i, p);
					} else if(Utility.getTexture(i).equals(TextureDatabase.CRAFTING_TABLE)&&p.hasPermission("innomech.use.portable_crafting_table")){
						p.openWorkbench(p.getLocation(), true);
						opened = true;
					} else if(Utility.getTexture(i).equals(TextureDatabase.RECIPE_VIEWER)&&p.hasPermission("innomech.use.recipe_viewer")){
						p.openInventory(RecipeViewer.openingInv);
						opened = true;
					} else if(Utility.getTexture(i).equals(TextureDatabase.AETHERIUM_CUBE)){
						AetheriumCube.placeAetheriumCube(p, i);
					}
					
				}
			}
			if(!opened){
				ItemStack i_o = p.getInventory().getItemInOffHand();
				if(i_o != null){
					if(Utility.checkMechanical(i_o)){
						if(Utility.getTexture(i_o).equals(TextureDatabase.CRAFTING_TABLE)&&p.hasPermission("innomech.use.portable_crafting_table")){
							p.openWorkbench(p.getLocation(), true);
						} else if(Utility.getTexture(i).equals(TextureDatabase.RECIPE_VIEWER)&&p.hasPermission("innomech.use.recipe_viewer")){
							p.openInventory(RecipeViewer.openingInv);
						}
					}
				}
			}
			
			
		}
		
		
		if(!e.isCancelled()){
			
			
			if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
				Block b = e.getClickedBlock();
				
				
				if(Utility.checkMechanical(b)){
					
					for(HeadUnitBlock hub : Main.HUBList){
						if(hub.getBlock().equals(b)){
							
							if(p.hasPermission("innomech.use.portable_crafting_table")){
								if(hub.getHeadUnit().getTexture().equals(TextureDatabase.CRAFTING_TABLE)){
									p.openWorkbench(p.getLocation(), true);
								}
							}
							
							if(p.hasPermission("innomech.use.recipe_viewer")){
								if(hub.getHeadUnit().getTexture().equals(TextureDatabase.RECIPE_VIEWER)){
									p.openInventory(RecipeViewer.openingInv);
								}
							}
							
							if(p.hasPermission("innomech.use.minichests")){
								
								ChestListener.openChest(hub.getHeadUnit(), p, b);
								
							}
							
							if(p.hasPermission("innomech.use.thedarkgateway")){
								
								DarkListener.traverseGate(hub.getHeadUnit(), p);
								
							}
							
							
						}
					}
				}else if (b.getState() instanceof InventoryHolder){
					if(p.hasPermission("innomech.machines.place.pipe")){
						ItemStack iMain = p.getInventory().getItemInMainHand();
						if(Utility.checkMechanical(iMain)){
							if(Utility.getTexture(iMain).equals(TextureDatabase.PIPE_item)){
								
								Location bLoc = b.getLocation().clone();
								bLoc.add(0.5, 0.5, 0.5);
									
								PipeDesignerManager.attemptComplete(bLoc, p);
								
								e.setCancelled(true);
								return;
							}
						}
					}
				}
				
			}
			
			
			if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
				Block b = e.getClickedBlock();
				
				
				if(Utility.checkMechanical(b)) {
					
					if(p.hasPermission("innomech.machines.place.pipe")){
						ItemStack iMain = p.getInventory().getItemInMainHand();
						if(Utility.checkMechanical(iMain)){
							if(Utility.getTexture(iMain).equals(TextureDatabase.PIPE_item)){
								for(HeadUnitBlock hub : Main.HUBList){
									if(hub.getBlock().equals(b)){
										if(hub.getHeadUnit().getType().equals(BlockType.STORAGE)){
											
											Location bLoc = b.getLocation().clone();
											bLoc.add(0.5, 0.5, 0.5);
											
											PipeDesignerManager.attemptStart(bLoc, p);
											
											e.setCancelled(true);
											return;
										}
									}
								}
							}
						}
					}
					
					if (p.hasPermission("innomech.machines.break")){
						
						TextureDatabase tex = Utility.getTexture(b);
						
						switch(SimulateStrength.startBreak(e.getPlayer(), tex, e.getPlayer().getInventory().getItemInMainHand())){
							case "break": {
							} break;
							case "nothing": {
							} break;
							case "delay": {
								e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 1));
							} break;
							case "don't break": {
								e.setCancelled(true);
							} break;
							default: {
								e.setCancelled(true);
							} break;
						}
						
					}
					
				}else if (b.getState() instanceof InventoryHolder){
					if(p.hasPermission("innomech.machines.place.pipe")){
						ItemStack iMain = p.getInventory().getItemInMainHand();
						if(Utility.checkMechanical(iMain)){
							if(Utility.getTexture(iMain).equals(TextureDatabase.PIPE_item)){
								
								Location bLoc = b.getLocation().clone();
								bLoc.add(0.5, 0.5, 0.5);
								
								PipeDesignerManager.attemptStart(bLoc, p);
								
								e.setCancelled(true);
								return;
							}
						}
					}
				}
				
			}
			
			
		}
	}
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamage(EntityDamageByEntityEvent e){
		
		
		if(!e.isCancelled()){
		
			if(e.getEntity() instanceof ArmorStand){
				ArmorStand as = (ArmorStand) e.getEntity();
				
				if(Utility.checkMechanical(as.getHelmet())){
					if(MHU_Utilities.getMHU(as)!=null){
						MultiHeadUnit mhu = MHU_Utilities.getMHU(as);
						
						
						
						if(e.getDamager() instanceof Player){
							
							Player p = (Player) e.getDamager();
							
							ItemStack i = p.getInventory().getItemInMainHand();
							
							if(ArmorStandData.isEnabled(as)&&p.hasPermission("innomech.machines.place.pipe")){
								if(Utility.checkMechanical(i)){
									if(Utility.getTexture(i).equals(TextureDatabase.PIPE_item)){
										
										if(Utility.getTexture(as.getHelmet()).getType().equals(BlockType.STORAGE)){
											
											PipeDesignerManager.attemptStart(as.getEyeLocation(), p);
											
											e.setCancelled(true);
											return;
											
										} else if (mhu.getItemHU().getType().equals(BlockType.MHU_Industrial_Furance)){
											
											PipeDesignerManager.attemptStart(Industrial_Furnace.getStorageArmorStand(mhu, as).getEyeLocation(), p);
											
											e.setCancelled(true);
											return;
											
										}
										
									}
								}
							}
							
							if(p.hasPermission("innomech.machines.break")){
								
								TextureDatabase tex = mhu.getItemHU().getTexture();
								
								mhu.setDamage((int) (Math.round(e.getDamage()*2)+mhu.getDamage()));
								
								SoundManager.playSound(mhu.getCenterLocation(), SoundDatabase.MACHINE_HIT);
								
								switch(SimulateStrength.startBreak(p, tex, p.getInventory().getItemInMainHand())){
									case "break": {
										BreakBlock.MHUPlayerBreak(mhu, as.getEyeLocation(), true, p);
									} break;
									case "nothing": {
										mhu.setDamage((int) (Math.round(e.getDamage()*10)+mhu.getDamage()));
										if(mhu.getDamage()>=100){
											BreakBlock.MHUPlayerBreak(mhu, as.getEyeLocation(), true, p);
										}
									} break;
									case "delay": {
										if(mhu.getDamage()>=100){
											BreakBlock.MHUPlayerBreak(mhu, as.getEyeLocation(), false, p);
										}
									} break;
									case "don't break": {
										mhu.setDamage(0);
									} break;
									default: {
										mhu.setDamage(0);
									} break;
								}
								
							}
							
							
						}
						
						
					}
				}
			}
		
		}
	}
	
	
	@EventHandler(priority = EventPriority.LOW)
	public void onBlockPowered(BlockRedstoneEvent e){
		
		if(Utility.isNextToMechanical(e.getBlock())){
			for(Block b : Utility.getAdjacentMechanicals(e.getBlock())){
				ActivatedMechBlock.createNew(b, e.getOldCurrent());
			}
		}
		
	}
	
	
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent e){
		
		if((boolean) CheckConfig.getValue("Player.Give_Book_On_First_Join", true)){
			
			Player p = e.getPlayer();
			if(!p.hasPlayedBefore()) {
				Utility.addItemToInventory(p.getInventory(), ItemsList.Book_of_Innovations(1), p.getLocation());
			}
			
		}
		
	}
	
	
	@EventHandler
	public void onLogOffEvent(PlayerQuitEvent e){
		if(!Main.loggedOffPlayers.contains(e.getPlayer())) {
			Main.loggedOffPlayers.add(e.getPlayer());
		}
	}
	
	
	public static boolean isWrench(ItemStack i){
		
		ItemStack iWrench = ItemsList.Wrench(1);
		
		if(i!=null){
			if(i.isSimilar(iWrench)){
				return true;
			}
		}
		return false;
	}
	
	
}
