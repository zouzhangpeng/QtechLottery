package com.main;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.FrameBorderStyle;

import com.view.MainFrame;
/**
 * 程序启动入口
 * @author zhangpeng.zhou
 *
 */
public class Main {

	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			// 取消设置选项
			UIManager.put("RootPane.setupButtonVisible", false);
			// 取消背景透明
			BeautyEyeLNFHelper.frameBorderStyle = FrameBorderStyle.osLookAndFeelDecorated;
			BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//启动主界面
		MainFrame mainView = new MainFrame();
		mainView.setVisible(true);
	}

}
