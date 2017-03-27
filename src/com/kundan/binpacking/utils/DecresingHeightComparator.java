package com.kundan.binpacking.utils;

import java.util.Comparator;

import com.kundan.binpacking.core.Item;

public class DecresingHeightComparator implements Comparator<Object> {

	@Override
	public int compare(Object arg0, Object arg1) {
		Item one = (Item)arg0;
		Item two = (Item)arg1;
		if(one.mHieght == two.mHieght){
			return 0;
		}else if(one.mHieght > two.mHieght){
			return -1;
		}else{
			return +1;
		}
	}

}
