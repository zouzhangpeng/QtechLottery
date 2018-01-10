package com.util;

import com.model.Constant;

import javax.swing.*;
/**
 * 对话框
 * @author zhangpeng.zhou
 *
 */
public class DialogUtil {
	/**
	 * 弹窗
	 *
	 * @param parentFrame
	 *            父窗口
	 * @param message
	 *            提示信息
	 * @param dialogType
	 *            弹窗类型
	 */
	public static void showDialog(JFrame parentFrame, String message, String dialogType) {
		switch (dialogType) {
		case Constant.ERROR_MESSAGE_DIALOG_TYPE:
			JOptionPane.showMessageDialog(parentFrame, message, Constant.ERROR_MESSAGE_DIALOG_TYPE,
					JOptionPane.ERROR_MESSAGE);
			break;
		case Constant.WARNING_MESSAGE_DIALOG_TYPE:
			JOptionPane.showMessageDialog(parentFrame, message, Constant.WARNING_MESSAGE_DIALOG_TYPE,
					JOptionPane.WARNING_MESSAGE);
			break;
		default:
			JOptionPane.showMessageDialog(parentFrame, message, Constant.INFORMATION_MESSAGE_DIALOG_TYPE,
					JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}
}
