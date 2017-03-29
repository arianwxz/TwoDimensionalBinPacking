package com.kundan.binpacking.core;

import java.util.ArrayList;
import java.util.List;

import com.kundan.binpacking.utils.Utils;

public class Bin {
	public double mHieght = 1;
	public double mWidth = 1;
	public List<Shelf> mShelves = new ArrayList<Shelf>();
	
	public boolean addItem(Item item){
		boolean flag = Utils.canFitInBin(this, item);

		return flag;
	}

	@Override
	public String toString() {
		return "{" + mWidth + "," + mHieght + " :S "+ mShelves+ "}";
	}
	
	@Override
	public Bin clone() throws CloneNotSupportedException {
		Bin bin = new Bin();
		bin.mShelves = Utils.cloneShelfList(mShelves);
		return bin;
	}

}
