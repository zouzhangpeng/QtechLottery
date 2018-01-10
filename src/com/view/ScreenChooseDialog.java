package com.view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.action.ScreenChooseDialogListener;

public class ScreenChooseDialog extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame showLotteryFrame;
	private String[] screens;
	public static JFrame screenChooseDialog;
	public static JPanel contentPane;
	public static JLabel tipLabel;
	public static JComboBox<String> screenChooseComboBox;
	public static JButton btn;

	public ScreenChooseDialog(JFrame showLotteryFrame, String[] screens) {
		this.showLotteryFrame = showLotteryFrame;
		this.screens = screens;
		initComponent();
		initFrame();
		initUI();
		initListener();
	}

	private void initComponent() {
		screenChooseDialog = this;
		contentPane = new JPanel();
		tipLabel = new JLabel("请选择投影屏幕！", JLabel.CENTER);
		screenChooseComboBox = new JComboBox<>(screens);
		btn = new JButton("确定");
	}

	private void initFrame() {
		setSize(new Dimension(400, 200));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void initUI() {
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tipLabel.setBounds(100, 10, 200, 50);
		screenChooseComboBox.setBounds(100, 60, 200, 30);
		btn.setBounds(170, 110, 60, 50);
		contentPane.add(tipLabel);
		contentPane.add(screenChooseComboBox);
		contentPane.add(btn);
	}

	public void initListener() {
		ScreenChooseDialogListener screenChooseDialogListener = new ScreenChooseDialogListener(screenChooseComboBox,
				showLotteryFrame,screenChooseDialog);
		btn.addActionListener(screenChooseDialogListener);
	}
}
