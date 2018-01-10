package com.util;

import javax.swing.*;

/**
 * 滑动动画
 * 
 * @author zhangpeng.zhou
 *
 */
public class AnimationUtil {

	/**
	 * panel横向滑动
	 *
	 * @param childPanel
	 * @param cyclePanel
	 * @param parentPanelWidth
	 * @param width
	 * @param height
	 * @param y
	 */
	public static void horizontalRoll(JPanel childPanel, JPanel cyclePanel, int parentPanelWidth, int width, int height,
			int y) {
		new Thread() {
			@Override
			public void run() {
				int x1 = -width;
				int x2 = -width * 2;
				while (true) {
					childPanel.setBounds(x1, y, width, height);
					cyclePanel.setBounds(x2, y, width, height);
					if (x1 == parentPanelWidth) {
						x1 = x1 - width * 2;
					}
					if (x2 == parentPanelWidth) {
						x2 = x2 - width * 2;
					}
					try {
						Thread.sleep(6);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					x1++;
					x2++;
				}
			}
		}.start();
	}

	public static void verticalRoll(JComponent component, JComponent cycleComponent, int parentHeight, int width,
			int height) {
		new Thread() {
			@Override
			public void run() {
				int y1 = -height;
				int y2 = -height * 2;
				while (true) {
					component.setBounds(0, y1, width, height);
					cycleComponent.setBounds(0, y2, width, height);
					if (y1 == parentHeight) {
						y1 = y1 - height * 2;
					}
					if (y2 == parentHeight) {
						y2 = y2 - height * 2;
					}
					try {
						Thread.sleep(8);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					y1++;
					y2++;
				}
			}
		}.start();
	}
}
