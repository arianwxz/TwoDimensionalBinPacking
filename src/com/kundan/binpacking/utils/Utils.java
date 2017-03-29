package com.kundan.binpacking.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import com.kundan.binpacking.configuration.Config;
import com.kundan.binpacking.core.Bin;
import com.kundan.binpacking.core.Item;
import com.kundan.binpacking.core.Shelf;

/**
 * Utils class for various util methods used accross classes.
 * @author kundan.singh
 *
 */
public class Utils {

	public static ArrayList<Item> getSampleItems() {
		File file = null;
		FileReader fis = null;
		BufferedReader bis = null;
		final ArrayList<Item> result = new ArrayList<Item>();
		try {
			file = new File("D:\\items.txt");
			fis = new FileReader(file);
			bis = new BufferedReader(fis);
			String str = bis.readLine();
			int i=0;
			while (!str.equals("#")) {
				StringTokenizer stoken = new StringTokenizer(str);
				Item item = new Item();
				item.index = i;
				item.mWidth = Double.parseDouble(stoken.nextToken());
				item.mHieght = Double.parseDouble(stoken.nextToken());
				result.add(item);
				str = bis.readLine();
				i++;
			}
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static List<Bin> getNFDHBinPacking(List<Item> pItems) {
		List<Bin> resultBins = new ArrayList<Bin>();
		Bin bin = null;
		Collections.sort(pItems, new DecresingHeightComparator());
		for (Item item : pItems) {
			boolean isAccomodated = false;
			for(Bin existingBin : resultBins){
				if(canFitInBin(existingBin, item)){
					isAccomodated = true;
					break;
				}
			}
			if(!isAccomodated){
				bin = new Bin();
				Shelf shelf = new Shelf();
				shelf.mAssignedItems.add(item);
				shelf.mShelfHieght = item.mHieght;
				bin.mShelves.add(shelf);
				resultBins.add(bin);
			}
		}
		System.out.println(resultBins.size());
		return resultBins;
	}

	public static boolean canFitInBin(Bin bin, Item item) {
		boolean result = false;
		if(bin != null){
			for(Shelf shelf : bin.mShelves){
				if(canFitInShelf(shelf, item)){
					result = true;
					shelf.mAssignedItems.add(item);
					if(shelf.mShelfHieght < item.mHieght){
						shelf.mShelfHieght = item.mHieght;
					}
					break;
				}
			}
			if(!result){
				double totalHeight = 0;
				for(Shelf shelf : bin.mShelves){
					totalHeight+=shelf.mShelfHieght;
				}
				if(totalHeight+item.mHieght <= 1){
					Shelf newShelf = new Shelf();
					newShelf.mAssignedItems.add(item);
					newShelf.mShelfHieght = item.mHieght;
					bin.mShelves.add(newShelf);
					result = true;
				}
				
			}
		}
		return result;
	}

	private static boolean canFitInShelf(Shelf shelf, Item item) {
		if(shelf.mShelfHieght >= item.mHieght){
			double totalWidth = 0;
			for(Item sItem:shelf.mAssignedItems){
				totalWidth += sItem.mWidth;
			}
			if(totalWidth+item.mWidth <= 1){
				return true;
			}
		}
		return false;
	}
	
	public static List<Config> cloneList(List<Config> list) throws CloneNotSupportedException {
	    List<Config> clone = new ArrayList<Config>(list.size());
	    for (Config item : list) clone.add(item.clone());
	    return clone;
	}
	
	public static List<Shelf> cloneShelfList(List<Shelf> list) throws CloneNotSupportedException {
	    List<Shelf> clone = new ArrayList<Shelf>(list.size());
	    for (Shelf item : list) clone.add(item.clone());
	    return clone;
	}

	public static List<Item> cloneItemList(List<Item> list) throws CloneNotSupportedException {
		List<Item> clone = new ArrayList<Item>(list.size());
	    for (Item item : list) clone.add(item.clone());
	    return clone;
	}

	public static List<Bin> getAllConfigBinPacking(List<Config> configs) {
		List<Bin> bins = new ArrayList<Bin>();
		for(Config config : configs){
			bins.add(config.mBin);
		}
		return bins;
	}

}
