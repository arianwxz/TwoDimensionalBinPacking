package com.kundan.binpacking.applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.kundan.binpacking.core.Bin;
import com.kundan.binpacking.core.BinPackingAlgorithm;
import com.kundan.binpacking.core.Item;
import com.kundan.binpacking.core.Shelf;

/**
 * 
 * @author my-pc
 *
 */
public class LoadImageApplet extends Applet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() {
		BinPackingAlgorithm.init();
		super.init();
	}

	@Override
	public void paint(Graphics g) {
		int binPointerx = 0;
		int binPointery = 0;
		int count = 0;
		for (Bin bin : BinPackingAlgorithm.mBins) {
			int startX = binPointerx * 100;
			int startY = binPointery * 100;
			int shelfCurrentY = startY;
			System.out.println(bin);
			Random rand = new Random();
			int colorR = rand.nextInt(256);
			int colorG = rand.nextInt(256);
			int colorB = rand.nextInt(256);
			g.setColor(new Color(colorR, colorG, colorB));
			g.drawRect(startX, startY, 100, 100);
			for (Shelf shelf : bin.mShelves) {
				int shelfCurrentX = startX;
				g.setColor(new Color(colorR, colorG, rand.nextInt(256)));
				for (Item item : shelf.mAssignedItems) {
					g.drawRect(shelfCurrentX, shelfCurrentY,
							(int) (item.mWidth * 100),
							(int) (item.mHieght * 100));
					count++;
					g.drawString(item.index + "", shelfCurrentX, shelfCurrentY+10);
					shelfCurrentX += (int)(100*item.mWidth);
				}
				shelfCurrentY += (int)(shelf.mShelfHieght*100);
			}
			if (binPointerx == 8) {
				binPointerx = 0;
				binPointery++;
			} else {
				binPointerx++;
			}
			System.out.println(count);
		}
	}
}
