package com.quzzar.server.souls.character.data;

import java.io.Serializable;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.quzzar.server.souls.Utility;
import com.quzzar.server.souls.limbo.creation.charname.ValidUsername;

public class CharData implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private UUID pUUID;
	
	private String name;
	private UUID skin;
	
	private SoulData soul;
	private FameData fame;
	private EssenceData essence;
	
	private double money;
	
	public CharData(Player p) {
		
		pUUID = p.getUniqueId();
		
		soul = new SoulData();
		fame = new FameData();
		essence = new EssenceData();
		
		name = ValidUsername.lettersOnly(p.getName());
		skin = UUID.fromString("4a527c92-708c-437e-b879-41dd3fa82812");
		
		money = 0;
		
	}

	public UUID getUUID() {
		return pUUID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public SoulData getSoul() {
		return soul;
	}

	public FameData getFame() {
		return fame;
	}

	public EssenceData getEssence() {
		return essence;
	}
	
	public double getMoney() {
		return Utility.roundToTwoDecimals(money);
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	public UUID getSkin() {
		return skin;
	}
	
	public void setSkin(UUID skinUUID) {
		this.skin = skinUUID;
	}
	
}
