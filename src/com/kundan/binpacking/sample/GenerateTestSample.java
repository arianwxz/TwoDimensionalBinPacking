package com.kundan.binpacking.sample;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * class to generate sample items.
 * 
 * @author my-pc
 *
 */
public class GenerateTestSample {
	public static void main(String args[]) {
		File file = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			file = new File("D:\\items.txt");
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			Random rand = new Random();
			for (int i = 0; i < 30; i++) {
				bos.write(new String(rand.nextDouble() + " "
						+ rand.nextDouble()).getBytes());
				bos.write("\n".getBytes());
			}
			bos.write("#".getBytes());
			bos.flush();
		} catch (final Exception e) {
			e.printStackTrace();
		}finally{
			if(bos != null){
				try {
					bos.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
			if(fos != null){
				try {
					fos.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
