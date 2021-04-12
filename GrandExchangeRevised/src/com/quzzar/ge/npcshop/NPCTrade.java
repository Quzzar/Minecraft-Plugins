package com.quzzar.ge.npcshop;

import org.bukkit.entity.Player;

import com.quzzar.ge.core.ItemUtils;
import com.quzzar.ge.core.buylist.SimpleItem;
import com.quzzar.server.souls.character.CharacterManager;
import com.quzzar.server.souls.character.PlayerCharacter;

public class NPCTrade {

	private double defaultPrice; // Price when the shop is at its default stock
	private int defaultStock; // Every server restart, this is the default stock
	private SimpleItem item;
	private int currentStock;
	
	public NPCTrade(double defaultPrice, int defaultStock, SimpleItem item) {
		
		this.defaultPrice = defaultPrice;
		this.defaultStock = defaultStock;
		this.currentStock = defaultStock;
		
		item.setAmount(1);
		this.item = item;
		
	}
	
	public double getBuyForPrice() {
		
		final double buyingPercentage = 0.65;
		final double minimumCapPercentage = 0.2;
		final double m = 1.2; // Larger the value, larger the rate at which prices will be adjusted based off stock
		
		double defaultBuyPrice = defaultPrice*buyingPercentage;
		double price = defaultBuyPrice-(defaultBuyPrice*(m/(defaultStock+1)))*(currentStock-defaultStock);
		if(price < defaultBuyPrice*minimumCapPercentage) {price = defaultBuyPrice*minimumCapPercentage;}
		return ItemUtils.roundToTwoDecimals(price);
	}
	
	public double getSellForPrice() {
		
		final double minimumCapPercentage = 0.2;
		final double m = 0.8; // Larger the value, larger the rate at which prices will be adjusted based off stock
		
		double price = defaultPrice-(defaultPrice*(m/(defaultStock+1)))*(currentStock-defaultStock);
		if(price < defaultPrice*minimumCapPercentage) {price = defaultPrice*minimumCapPercentage;}
		return ItemUtils.roundToTwoDecimals(price);
	}

	public int getQuantity() {
		return currentStock;
	}
	
	public SimpleItem getItem() {
		return item;
	}
	
	public void buyOneFrom(Player player) {
		PlayerCharacter pChar = CharacterManager.getCharacter(player);
		pChar.giveMoney(getBuyForPrice());
		currentStock++;
	}
	
	public boolean isOutOfStock() {
		return currentStock <= 0;
	}
	
	public boolean sellOneTo(Player player) {
		if(isOutOfStock()) {
			return false;
		}
		PlayerCharacter pChar = CharacterManager.getCharacter(player);
		boolean success = pChar.takeMoney(getSellForPrice());
		if(success) {
			currentStock--;
			return true;
		} else {
			return false;
		}
	}
	
	public NPCTrade clone(double pAdj) {
		return new NPCTrade(defaultPrice+defaultPrice*pAdj, defaultStock, item);
	}
	
}
