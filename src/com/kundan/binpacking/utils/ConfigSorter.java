package com.kundan.binpacking.utils;

import java.util.Comparator;

import com.kundan.binpacking.configuration.Config;

public class ConfigSorter implements Comparator<Config> {

	@Override
	public int compare(Config o1, Config o2) {
		Config one = (Config)o1;
		Config two = (Config)o2;
		if(one.mIdentifier.size() == two.mIdentifier.size()){
			return 0;
		}else if(one.mIdentifier.size() < two.mIdentifier.size()){
			return -1;
		}else{
			return +1;
		}
	}

}
