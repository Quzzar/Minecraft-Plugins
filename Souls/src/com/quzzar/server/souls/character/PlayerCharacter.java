package com.quzzar.server.souls.character;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.connorlinfoot.bountifulapi.BountifulAPI;
import com.quzzar.server.souls.Souls;
import com.quzzar.server.souls.Utility;
import com.quzzar.server.souls.character.data.CharData;
import com.quzzar.server.souls.character.souls.SoulManager;

public class PlayerCharacter {

	private Player player;
	private CharData cData;
	
	public PlayerCharacter(CharData cData, Player player) {
		this.cData = cData;
		this.player = player;
	}
	
	public void kill() {
		CharacterManager.getAllData().remove(cData.getUUID());
		CharacterManager.addCharData(player);
		cData = null;
	}
	
	public boolean isDead() {
		return cData.getSoul().inLimbo();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public String getName() {
		return cData.getName();
	}
	
	public UUID getSkin() {
		return cData.getSkin();
	}
	
	public CharData getData() {
		return cData;
	}
	
	public int getSoulCount() {
		return SoulManager.getSoulAmount(cData.getUUID());
	}
	
	public boolean canCreateCharacter() {
		return getSoulCount() > 0 || cData.getSoul().getRechargeHours() <= 0;
	}
	
	public boolean subtractEssence() {
		return cData.getEssence()
			.subtractEssence(cData.getFame().getValue());
	}
	
	public boolean subtractEssence(double multiplier) {
		return cData.getEssence()
				.subtractEssence(cData.getFame().getValue(), multiplier);
	}
	
	public void increaseInfamy(double amt) {
		
		cData.getFame().adjustInfamy(amt);
		
		player.setGlowing(true);
		
		player.playSound(player.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 1, 0.8f);
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Souls.instance, new Runnable() {
			
			public void run() {
				
				player.setGlowing(false);
				
			}
		
		}, 40); // Delay 2 seconds
		
	}
	
	public void giveMoney(double amount) {
		cData.setMoney(cData.getMoney()+amount);
		BountifulAPI.sendActionBar(player, ChatColor.DARK_GREEN+"+ "+ChatColor.GREEN+"$"+amount, 30);
	}
	
	public boolean takeMoney(double amount) {
		boolean success = !(cData.getMoney() - amount < 0);
		if(success) {
			cData.setMoney(cData.getMoney()-amount);
			BountifulAPI.sendActionBar(player, ChatColor.DARK_RED+"- "+ChatColor.RED+"$"+amount, 30);
		}
		return success;
	}
	
	public void setMoney(double amount) {
		cData.setMoney(amount);
		BountifulAPI.sendActionBar(player, ChatColor.GOLD+"$"+amount, 30);
	}
	
	public double getMoney() {
		return cData.getMoney();
	}
	
	public void refresh() {
		
		if(cData.getSoul().inLimbo()) {
			
			player.setExp(0);
			
			player.setLevel(0);
			
		} else {
			
			try {
				player.setExp((float)cData.getEssence().getEssence());
			} catch (Exception e) {
				Utility.tellConsole(ChatColor.RED+"Can't set exp to "+cData.getEssence()+"!");
			}
			
			double fameVal = cData.getFame().getValue();
			fameVal = Math.round(fameVal);
			player.setLevel((int) fameVal);
			
		}
		
	}
	
	
	
}
