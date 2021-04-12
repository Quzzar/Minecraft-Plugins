package com.quzzar.im.structures.loading.asbuilding;

import java.util.ArrayList;

public class ASPlacement {
	
	private double firstVal;
	private double secondVal;
	private double heightVal;
	private ASDirection direction;
	private boolean small = false;
	
	public ASPlacement(double firstVal, double secondVal, double heightVal, ASDirection direction) {
		this.firstVal = firstVal;
		this.secondVal = secondVal;
		this.heightVal = heightVal;
		this.direction = direction;
	}
	
	public ASPlacement(double firstVal, double secondVal, double heightVal, ASDirection direction, boolean small) {
		this.firstVal = firstVal;
		this.secondVal = secondVal;
		this.heightVal = heightVal;
		this.direction = direction;
		this.small = small;
	}
	
	public double getFirstVal() {
		return firstVal;
	}
	
	public double getSecondVal() {
		return secondVal;
	}
	
	public double getHeightVal() {
		return heightVal;
	}
	
	public ASDirection getDirection() {
		return direction;
	}
	
	public boolean isSmall() {
		return small;
	}
	
	
	public static ArrayList<ASPlacement> getMachineBaseList(){
		
		double val = 0.315;
		
		double topVal = -0.795;
		double botVal = -1.425;
		
		ArrayList<ASPlacement> list  = new ArrayList<ASPlacement>();
		
		list.add(new ASPlacement(-val, val, topVal, ASDirection.FORWARD));
		list.add(new ASPlacement(val, val, topVal, ASDirection.FORWARD));
		list.add(new ASPlacement(-val, val, botVal, ASDirection.FORWARD));
		list.add(new ASPlacement(val, val, botVal, ASDirection.FORWARD));
		list.add(new ASPlacement(-val, -val, topVal, ASDirection.FORWARD));
		list.add(new ASPlacement(val, -val, topVal, ASDirection.FORWARD));
		list.add(new ASPlacement(-val, -val, botVal, ASDirection.FORWARD));
		list.add(new ASPlacement(val, -val, botVal, ASDirection.FORWARD));
		
		return list;
		
	}
	
	public static ArrayList<ASPlacement> getSmallMachineBaseList(){
		
		double val = 0.2;
		
		double topVal = -0.05;
		double botVal = -0.49;
		
		ArrayList<ASPlacement> list  = new ArrayList<ASPlacement>();
		
		list.add(new ASPlacement(-val, val, topVal, ASDirection.FORWARD, true));
		list.add(new ASPlacement(val, val, topVal, ASDirection.FORWARD, true));
		list.add(new ASPlacement(-val, val, botVal, ASDirection.FORWARD, true));
		list.add(new ASPlacement(val, val, botVal, ASDirection.FORWARD, true));
		list.add(new ASPlacement(-val, -val, topVal, ASDirection.FORWARD, true));
		list.add(new ASPlacement(val, -val, topVal, ASDirection.FORWARD, true));
		list.add(new ASPlacement(-val, -val, botVal, ASDirection.FORWARD, true));
		list.add(new ASPlacement(val, -val, botVal, ASDirection.FORWARD, true));
		
		return list;
		
	}
	
}
