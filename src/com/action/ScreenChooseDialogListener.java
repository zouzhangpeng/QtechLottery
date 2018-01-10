package com.action;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.model.Constant;
import com.util.ConversionUtil;
import com.util.PropertiesUtil;
import com.view.ShowLotteryFrame;

public class ScreenChooseDialogListener implements ActionListener {
	private JComboBox<String> screenChooseComboBox;
	private JFrame showLotteryFrame;
	private JFrame screenChooseDialog;

	public ScreenChooseDialogListener(JComboBox<String> screenChooseComboBox, JFrame showLotteryFrame,JFrame screenChooseDialog) {
		this.screenChooseComboBox = screenChooseComboBox;
		this.showLotteryFrame = showLotteryFrame;
		this.screenChooseDialog = screenChooseDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 获取被选中设备序号
		int selectIndex = ConversionUtil.chineseToNum(screenChooseComboBox.getSelectedItem().toString());
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gd = ge.getScreenDevices();
		PropertiesUtil.writeProperties(Constant.CONFIG_PROPERTIES_FILE_PATH, "screenWidth",
				String.valueOf(gd[selectIndex].getDisplayMode().getWidth()));
		PropertiesUtil.writeProperties(Constant.CONFIG_PROPERTIES_FILE_PATH, "screenHeight",
				String.valueOf(gd[selectIndex].getDisplayMode().getHeight()));
		PropertiesUtil.writeProperties(Constant.CONFIG_PROPERTIES_FILE_PATH, "x",
				String.valueOf(gd[selectIndex].getDefaultConfiguration().getBounds().x));
		showLotteryFrame = new ShowLotteryFrame();
		showLotteryFrame.setVisible(true);
		screenChooseDialog.dispose();
	}

}
