package com.kundan.binpacking.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.kundan.binpacking.configuration.Config;
import com.kundan.binpacking.configuration.ConfigGenerator;
import com.kundan.binpacking.configuration.LinearProgramHelper;
import com.kundan.binpacking.utils.ConfigSorter;
import com.kundan.binpacking.utils.ItemRounderClassifier;
import com.kundan.binpacking.utils.Utils;

/**
 * Main class to run the algorithm.
 * This algorithm computes the minimum bins required to fit rectangles in unit size bin.
 * @author my-pc
 *
 */
public class BinPackingAlgorithm {
	
	public static List<Item> mItems;
	public static List<Bin> mBins;

	/**
	 * main method.
	 * @param args
	 * @throws CloneNotSupportedException 
	 */
	public static void init() throws CloneNotSupportedException{
		System.out.println("Start of Algorithm");
		// get all the items
		// get the bin size
		// normalize the bin to unit, and normalize the items too with same ratio.
		// if any item exceed bin size scale it to the unit size.
		mItems = Utils.getSampleItems();
		System.out.println("Given Items.");
		System.out.println(mItems);
		
		ConfigGenerator generator = new ConfigGenerator();
		
		double accuracyE = 0.5; 
		double delta = 0.5;
		
		ItemRounderClassifier rounderClassifier = new ItemRounderClassifier();
		List<Item> roundedItems = rounderClassifier.doRounding(mItems, 0.1);
		rounderClassifier.doClassification(roundedItems, delta, Math.pow(delta, 4));
		
		/*System.out.println("BIG:"+rounderClassifier.mBigItems);
		System.out.println("SMALL:"+rounderClassifier.mSmallItems);
		System.out.println("WIDE:"+rounderClassifier.mWideItems);
		System.out.println("LONG:"+rounderClassifier.mLongItems);
		System.out.println("MEDIUM:"+rounderClassifier.mMediomItems);*/
		
		List<Item> IDash = rounderClassifier.prepareContainers(roundedItems);
		
		List<Config> configs = generator.fetchAllConfigs(IDash);
		
		LinearProgramHelper solver = new LinearProgramHelper();
		//YTD: TODO
		double value = solver.solve1(IDash, configs);
		
		List<Item> rolledBack  = rounderClassifier.roundedItems(IDash);
		
		//getting the bins fitted with NFDH
		mBins = Utils.getNFDHBinPacking(mItems);
		/*mBins.addAll(Utils.getNFDHBinPacking(rounderClassifier.mMediomItems));
		mBins.addAll(Utils.getNFDHBinPacking(rounderClassifier.mSmallItems));
		mBins.addAll(Utils.getNFDHBinPacking(rounderClassifier.mWideItems));
		mBins.addAll(Utils.getNFDHBinPacking(rounderClassifier.mLongItems));*/
		//getting all configs possible.
		//Collections.sort(configs, new ConfigSorter());
		//mBins.addAll(Utils.getAllConfigBinPacking(configs));
		
		// call the method which will return the packing.
		System.out.println("End of Algorithm");
	}
	
	

	
}
