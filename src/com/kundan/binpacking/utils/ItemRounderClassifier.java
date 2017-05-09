package com.kundan.binpacking.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.kundan.binpacking.core.Item;

public class ItemRounderClassifier {
	public List<Item> mBigItems = new ArrayList<Item>();
	public List<Item> mSmallItems = new ArrayList<Item>();
	public List<Item> mMediomItems = new ArrayList<Item>();
	public List<Item> mWideItems = new ArrayList<Item>();
	public List<Item> mLongItems = new ArrayList<Item>();

	public void doClassification(List<Item> oItems, double fe, double ge) {
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

	/**
	 * performing linear grouping.
	 * @param mItems
	 * @param d 
	 * @return
	 */
	public List<Item> doRounding(final List<Item> mItems, final double d) {
		double factor = d;
		double temp = 9*(factor/10);
		List<Item> rounded = new ArrayList<Item>();
		Item rItem;
		DecimalFormat df = new DecimalFormat("#.##");
		for(Item item : mItems){
			try {
				rItem = item.clone();
				rItem.mHieght+=temp;
				rItem.mWidth+=temp;
				rItem.mHieght = Double.parseDouble(df.format(rItem.mHieght));
				rItem.mWidth = Double.parseDouble(df.format(rItem.mWidth));
				rounded.add(rItem);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		return rounded;
	}

	public void prepareContainers(List<Item> roundedItems) {
		// TODO Auto-generated method stub
		
	}

	public List<Item> roundedItems(List<Item> mItems) {
		return mItems;
	}

}
