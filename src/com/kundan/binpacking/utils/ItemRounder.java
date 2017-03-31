package com.kundan.binpacking.utils;

import java.util.ArrayList;
import java.util.List;

import com.kundan.binpacking.core.Item;

public class ItemRounder {
	public List<Item> mBigItems = new ArrayList<Item>();
	public List<Item> mSmallItems = new ArrayList<Item>();
	public List<Item> mMediomItems = new ArrayList<Item>();
	public List<Item> mWideItems = new ArrayList<Item>();
	public List<Item> mLongItems = new ArrayList<Item>();

	public void doRounding(List<Item> oItems, double fe, double ge) {
		System.out.println("Rounding based on :["+ge+" "+fe+"]");
		for (Item item : oItems) {
			if (item.mHieght >= fe) {
				if (item.mWidth >= fe) {
					mBigItems.add(item);
				} else {
					if (item.mWidth <= ge) {
						mLongItems.add(item);
					} else {
						mMediomItems.add(item);
					}
				}
			} else {
				if (item.mHieght <= ge) {
					if (item.mWidth >= fe) {
						mWideItems.add(item);
					} else {
						if (item.mWidth <= ge) {
							mSmallItems.add(item);
						} else {
							mMediomItems.add(item);
						}
					}
				} else {
					mMediomItems.add(item);
				}
			}
		}
	}

}
