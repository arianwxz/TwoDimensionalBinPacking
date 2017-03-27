package com.kundan.binpacking.core;

import java.util.ArrayList;

public class Shelf {
	public ArrayList<Item> mAssignedItems = new ArrayList<Item>();
	public double mShelfHieght;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return mShelfHieght+" > "+mAssignedItems;
	}
}
