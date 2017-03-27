package com.kundan.binpacking.core;

public class Item {
	public double mHieght;
	public double mWidth;
	public int index;
	
	@Override
	public String toString() {
		return "<"+index+": "+mWidth+","+mHieght+">";
	}
}
