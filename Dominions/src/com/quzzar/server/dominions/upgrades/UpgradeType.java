package com.quzzar.server.dominions.upgrades;

public enum UpgradeType {

	PROT_T1(01),
	PROT_T2(02),
	PROT_T3(03),
	COURAGE_T1(10),
	COURAGE_T2(11),
	INTIMIDATION_T1(20),
	INTIMIDATION_T2(21),
	PERSONAL_SPACE(30),
	PADLOCKS(40),
	GIFT_FROM_GODS(50),
	POISON_GAS(60),
	GE_TRADER(1499),
	SYNDICATE(1500),
	COLONIZATION(1501),
	IMPERIALISM(1502);
	
	
	private int id;
	
	private UpgradeType(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
}
