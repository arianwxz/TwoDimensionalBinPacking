package com.kundan.binpacking.core;

import java.util.ArrayList;

import com.kundan.binpacking.utils.Utils;

/**
 * Main class to run the algorithm.
 * This algorithm computes the minimum bins required to fit rectangles in unit size bin.
 * @author my-pc
 *
 */
public class BinPackingAlgorithm {
	
	public static ArrayList<Item> mItems;
	public static ArrayList<Bin> mBins;

	/**
	 * main method.
	 * @param args
	 */
	public static void init(){
		System.out.println("Start of Algorithm");
		// get all the items
		// get the bin size
		// normalize the bin to unit, and normalize the items too with same ratio.
		// if any item exceed bin size scale it to the unit size.
		mItems = Utils.getSampleItems();
		System.out.println("Given Items.");
		System.out.println(mItems);
		
		mBins = Utils.getNFDHBinPacking(mItems);
		
		// call the method which will return the packing.
		System.out.println("End of Algorithm");
	}
	
	

	
}
