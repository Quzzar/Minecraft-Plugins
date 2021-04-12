package com.quzzar.server.skills;

import org.bukkit.enchantments.Enchantment;

public class SkillEnchantmentElement {

	private Enchantment enchantment;
	private int tier;
	
	private double odds;
	
	public SkillEnchantmentElement(Enchantment enchantment, int tier, double odds) {
		
		this.enchantment = enchantment;
		this.tier = tier;
		this.odds = odds;
		
	}

	public Enchantment getEnchantment() {
		return enchantment;
	}

	public int getTier() {
		return tier;
	}

	public double getOdds() {
		return odds;
	}
	
}
