package com.action;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.model.Constant;
import com.util.ConversionUtil;
import com.util.DialogUtil;
import com.util.PropertiesUtil;
import com.view.BasicSetFrame;
import com.view.LotterySetFrame;
import com.view.ScreenChooseDialog;
import com.view.ShowLotteryFrame;

public class MainFrameAction implements ActionListener {
	private JButton startLuckyDrawBtn, basicSetBtn, luckyDrawSetBtn;
	public ShowLotteryFrame showLotteryFrame;
	public int flag = 1;

	public MainFrameAction(JButton startLuckyDrawBtn, JButton basicSetBtn, JButton luckyDrawSetBtn, JButton exitBtn) {
		this.startLuckyDrawBtn = startLuckyDrawBtn;
		this.basicSetBtn = basicSetBtn;
		this.luckyDrawSetBtn = luckyDrawSetBtn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startLuckyDrawBtn) {
			flag = 0;
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice[] gd = ge.getScreenDevices();
			if (gd.length <= 1) {
				PropertiesUtil.writeProperties(Constant.CONFIG_PROPERTIES_FILE_PATH, "screenWidth",
						String.valueOf(gd[0].getDisplayMode().getWidth()));
				PropertiesUtil.writeProperties(Constant.CONFIG_PROPERTIES_FILE_PATH, "screenHeight",
						String.valueOf(gd[0].getDisplayMode().getHeight()));
				PropertiesUtil.writeProperties(Constant.CONFIG_PROPERTIES_FILE_PATH, "x",
						String.valueOf(gd[0].getDefaultConfiguration().getBounds().x));
				showLotteryFrame = new ShowLotteryFrame();
				showLotteryFrame.setVisible(true);
			} else {
				String[] screens = new String[gd.length];
				for (int i = 0, j = gd.length; i < j; i++) {
					screens[i] = ConversionUtil.numToChinese(i);
				}
				new ScreenChooseDialog(showLotteryFrame, screens).setVisible(true);
			}
		} else if (e.getSource() == basicSetBtn) {
			if (flag == 1) {
				DialogUtil.showDialog(null, "请先打开抽奖界面", Constant.WARNING_MESSAGE_DIALOG_TYPE);
			} else {
				new BasicSetFrame().setVisible(true);
			}
		} else if (e.getSource() == luckyDrawSetBtn) {
			if (flag == 1) {
				DialogUtil.showDialog(null, "请先打开抽奖界面", Constant.WARNING_MESSAGE_DIALOG_TYPE);
			} else {
				new LotterySetFrame().setVisible(true);
			}
		} else {
			System.exit(0);
		}
	}

}
