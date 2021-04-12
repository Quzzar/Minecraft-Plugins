package com.quzzar.ge.core.buylist.buyinvmapping;

import java.util.List;

import com.quzzar.ge.core.InvType;
import com.quzzar.ge.core.buylist.SimpleItem;

public class BuyAreaInstance {

	private List<SimpleItem> contents;
	private String title;
	private InvType parentInv;
	
	public BuyAreaInstance(List<SimpleItem> contents, String title, InvType parentInv) {
		this.contents = contents;
		this.title = title;
		this.parentInv = parentInv;
	}
	
	public List<SimpleItem> getContents() {
		return contents;
	}
	
	public String getTitle() {
		return title;
	}
	
	public InvType getBackInv() {
		return parentInv;
	}
	
}
