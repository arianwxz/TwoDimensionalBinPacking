package com.kundan.binpacking.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.kundan.binpacking.core.Bin;

public class Config {
	
	public List<Integer> mIdentifier = new ArrayList<Integer>();
	
	public Bin mBin = new Bin();
	
	@Override
	public String toString() {
		String str = "";
		Collections.sort(mIdentifier);
		for(int i:mIdentifier){
			str = str + i + ".";
		}
		return str;
	}
	
	@Override
	public Config clone() throws CloneNotSupportedException {
		Config obj = new Config();
		obj.mBin = mBin.clone();
		obj.mIdentifier = new ArrayList<Integer>(mIdentifier);
		return obj;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean flag = toString().equals(((Config)obj).toString());
		return flag;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
}
