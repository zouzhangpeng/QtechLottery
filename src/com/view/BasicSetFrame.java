package com.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.model.Constant;
import com.util.DialogUtil;
import com.util.PropertiesUtil;

public class BasicSetFrame extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int screenWidth;
	private int prizeInfoFont;
	private JPanel contentPanel;
	private Box controlBox;
	private JSlider prizeInfoFontSlider;
	private JTextField prizeInfoFontText;
	private JButton prizeInfoFontBtn;

	public BasicSetFrame() {
		initData();
		initComponent();
		initFrame();
		initUI();
		addListener();
	}

	public void initData() {
		prizeInfoFont = Integer
				.parseInt(PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.PRIZE_INFO_FONT));
		screenWidth = Integer
				.parseInt(PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, "screenWidth"));
	}

	public void initFrame() {
		setContentPane(contentPanel);
		setSize(600, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void initComponent() {
		contentPanel = new JPanel();
		contentPanel.setLayout(null);

		controlBox = new Box(BoxLayout.Y_AXIS);

		prizeInfoFontSlider = new JSlider();
		prizeInfoFontSlider.setMaximum(400);
		prizeInfoFontSlider.setMinimum(5);
		prizeInfoFontSlider.setValue(prizeInfoFont);

		prizeInfoFontText = new JTextField();
		prizeInfoFontText.setText(String.valueOf(prizeInfoFont));

		prizeInfoFontBtn = new JButton("确定");
	}

	public void initUI() {
		addBox(prizeInfoFontSlider, "奖品信息字体", prizeInfoFontText, prizeInfoFontBtn);
		controlBox.setBounds(100, 100, 400, 50);
		contentPanel.add(controlBox);
	}

	public void addBox(JSlider slider, String description, JTextField text, JButton btn) {
		Box box = new Box(BoxLayout.X_AXIS);
		box.add(new JLabel(description + "："));
		box.add(slider);
		box.add(text);
		box.add(btn);
		controlBox.add(box);
	}

	public void addListener() {
//		//更新主标题字体大小
//		mainTitleFontSlider.addChangeListener(new ChangeListener() {
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				JSlider source = (JSlider) e.getSource();
//				mainTitleFontText.setText(String.valueOf(source.getValue()));
//				ShowLotteryFrame.mainTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, (int) source.getValue()));
//				ShowLotteryFrame.contentPanel.repaint();
//			}
//		});

//		//更新子标题字体大小
//		childTitleFontSlider.addChangeListener(new ChangeListener() {
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				JSlider source = (JSlider) e.getSource();
//				childTitleFontText.setText(String.valueOf(source.getValue()));
//				ShowLotteryFrame.childTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, (int) source.getValue()));
//				ShowLotteryFrame.contentPanel.repaint();
//			}
//		});

		//更新奖品信息字体大小
		prizeInfoFontSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				prizeInfoFontText.setText(String.valueOf(source.getValue()));
				ShowImagePanel.prizeLabel.setFont(new Font("微软雅黑", Font.BOLD, (int) source.getValue()));
				ShowImagePanel.prizeQuantityLabel.setFont(new Font("微软雅黑", Font.BOLD, (int) source.getValue()));
				ShowLotteryFrame.contentPanel.repaint();
			}
		});

//		//更新界面上方高度
//		northHeightSlider.addChangeListener(new ChangeListener() {
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				JSlider source = (JSlider) e.getSource();
//				northHeightText.setText(String.valueOf(source.getValue()));
//				ShowLotteryFrame.titlePanel.setPreferredSize(new Dimension(screenWidth, (int) source.getValue()));
//				ShowLotteryFrame.contentPanel.repaint();
//			}
//		});

//		mainTitleFontBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				PropertiesUtil.writeProperties(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.MAIN_TITLE_FONT_SIZE,
//						mainTitleFontText.getText());
//				DialogUtil.showDialog(null, Constant.UPDATE_SUCCESS_DIALOG_MESSAGE,
//						Constant.INFORMATION_MESSAGE_DIALOG_TYPE);
//			}
//		});
//
//		childTitleFontBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				PropertiesUtil.writeProperties(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.CHILD_TITLE_FONT_SIZE,
//						childTitleFontText.getText());
//				DialogUtil.showDialog(null, Constant.UPDATE_SUCCESS_DIALOG_MESSAGE,
//						Constant.INFORMATION_MESSAGE_DIALOG_TYPE);
//			}
//		});

		prizeInfoFontBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PropertiesUtil.writeProperties(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.PRIZE_INFO_FONT,
						prizeInfoFontText.getText());
				DialogUtil.showDialog(null, Constant.UPDATE_SUCCESS_DIALOG_MESSAGE,
						Constant.INFORMATION_MESSAGE_DIALOG_TYPE);
			}
		});

//		northHeightBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				PropertiesUtil.writeProperties(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.NORTH_HEIGHT,
//						northHeightText.getText());
//				DialogUtil.showDialog(null, Constant.UPDATE_SUCCESS_DIALOG_MESSAGE,
//						Constant.INFORMATION_MESSAGE_DIALOG_TYPE);
//			}
//		});
	}
}
