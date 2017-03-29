package com.kundan.binpacking.core;

import java.util.ArrayList;
import java.util.List;

import com.kundan.binpacking.utils.Utils;

public class Shelf {
	public List<Item> mAssignedItems = new ArrayList<Item>();
	public double mShelfHieght;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return mShelfHieght+" > "+mAssignedItems;
	}
	
	@Override
	public Shelf clone() throws CloneNotSupportedException {
		Shelf shelf = new Shelf();
		shelf.mShelfHieght = mShelfHieght;
		shelf.mAssignedItems = Utils.cloneItemList(mAssignedItems);
		return shelf;
	}
}
