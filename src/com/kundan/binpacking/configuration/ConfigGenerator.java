package com.kundan.binpacking.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kundan.binpacking.core.Item;
import com.kundan.binpacking.utils.DecresingHeightComparator;
import com.kundan.binpacking.utils.Utils;

public class ConfigGenerator {
	public List<Config> fetchAllConfigs(List<Item> pItems) throws CloneNotSupportedException{
		Collections.sort(pItems, new DecresingHeightComparator());
		List<Config> configs = new ArrayList<Config>();
		for(Item item : pItems){ //single Item config created.
			Config config = new Config();
			config.mBin.addItem(item);
			config.mIdentifier.add(item.index);
			configs.add(config);
		}
		
		List<Config> temp = new ArrayList<Config>();
		temp.addAll(Utils.cloneList(configs));
		
		List<Config> temp2 = new ArrayList<Config>();
		int iterations = 0;
		while(temp.size() > 0){
			for(Item item : pItems){
				for(Config config : temp){
					if(!config.mIdentifier.contains(item.index)){
						Config tempConfig = config.clone();
						boolean flag = tempConfig.mBin.addItem(item);
						if(flag){
							tempConfig.mIdentifier.add(item.index);
							temp2.add(tempConfig);
						}
					}
				}
			}
			configs.addAll(temp2);
			Set<Config> hs = new HashSet<Config>();
			hs.addAll(configs);
			configs.clear();
			configs.addAll(hs);
			temp.clear();
			temp.addAll(Utils.cloneList(temp2));
			temp2.clear();
			
			Set<Config> hs1 = new HashSet<Config>();
			hs1.addAll(temp);
			temp.clear();
			temp.addAll(hs1);
			System.out.println(++iterations + " "+ temp.size()+" "+temp);
			//break;
		}
		
		System.out.println(configs.size());
		System.out.println(configs);
		return configs;
	}
	
}
