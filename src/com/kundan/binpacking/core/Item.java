package com.kundan.binpacking.core;

public class Item {
	public double mHieght;
	public double mWidth;
	public int index;
	
	@Override
	public String toString() {
		return "<"+index+": "+mWidth+","+mHieght+">";
	}
	
	@Override
	public Item clone() throws CloneNotSupportedException {
		Item item = new Item();
		item.mHieght = mHieght;
		item.mWidth = mWidth;
		item.index = index;
		return item;
	}
}
