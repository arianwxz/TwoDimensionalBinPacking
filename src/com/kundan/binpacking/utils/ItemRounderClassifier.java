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
		DecimalFormat df = new DecimalFormat("#.#");
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

	public List<Item> prepareContainers(List<Item> roundedItems) {
		List<Item> tempItems = new ArrayList<Item>();
		
		double delta = 0.5;
		doClassification(roundedItems, delta, Math.pow(delta, 4));
		final List<List<Item>> ContainerH = new ArrayList<List<Item>>();
		final List<List<Item>> ContainerW = new ArrayList<List<Item>>();
		
		for(int i=0;i<10;i++){ //linear grouping of 10 size
			ContainerH.add(new ArrayList<Item>());
			ContainerW.add(new ArrayList<Item>());
		}
		
		
		for(Item item : mLongItems){
			ContainerH.get((int)(item.mHieght*10)-1).add(item);
		}
		
		for(Item item : mWideItems){
			ContainerH.get((int)(item.mWidth*10)-1).add(item);
		}
		
		//adding all big items to final list
		for(Item item : mBigItems){
			try {
				tempItems.add(item.clone());
			} catch (final CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		
		//adding all small items to the final list
		for(Item item : mSmallItems){
			try {
				tempItems.add(item.clone());
			} catch (final CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		
		//container to BIG item conversion
		for(List<Item> items : ContainerH){
			if(items.size() == 1){
				try {
					tempItems.add(items.get(0).clone());
				} catch (final CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}else if(items.size()>1){
				Item tmpItem = new Item();
				
				for(Item item : items){
					tmpItem.index = item.index;
					tmpItem.mHieght = item.mHieght;
					tmpItem.mWidth += item.mWidth;
				}
				
				tempItems.add(tmpItem);
			}
		}
		
		
		for(List<Item> items : ContainerH){
			if(items.size() == 1){
				try {
					tempItems.add(items.get(0).clone());
				} catch (final CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}else if(items.size()>1){
				Item tmpItem = new Item();
				
				for(Item item : items){
					tmpItem.index = item.index;
					tmpItem.mHieght += item.mHieght;
					tmpItem.mWidth = item.mWidth;
				}
				
				tempItems.add(tmpItem);
			}
		}
		System.out.println("Contianer"+tempItems.size());
		return tempItems;
		
	}

	public List<Item> roundedItems(List<Item> mItems) {
		return mItems;
	}
	
	

}
