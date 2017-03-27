package com.kundan.binpacking.core;

import java.util.ArrayList;

public class Bin {
	public double mHieght = 1;
	public double mWidth = 1;
	public ArrayList<Shelf> mShelves = new ArrayList<Shelf>();

	@Override
	public String toString() {
		return "{" + mWidth + "," + mHieght + " :S "+ mShelves+ "}";
	}

}
