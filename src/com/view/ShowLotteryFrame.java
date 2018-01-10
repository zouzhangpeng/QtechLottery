package com.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import com.model.Constant;
import com.util.PropertiesUtil;
import com.util.Utils;

public class ShowLotteryFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	// 界面设置
	private int screenWidth;// 屏幕宽度
	private int screenHeight;// 屏幕高度
	private int northHeight;// 上方边距
	private int marginWidth;// 边距宽度
	private int southHeight;// 下方边距
	private int x;// x坐标
	// 字体样式
	public static int mainTitleFontSize;//主标题字体大小
	public static int childTitleFontSize;//子标题字体大小

	public static JFrame showLuckDrawFrame;
	public static JPanel contentPanel;
	public static CardLayout cardLayout;
	// 标题
	public static JPanel titlePanel;
	public static JPanel mainTitlePanel;
	public static JPanel childTitlePanel;
	public static JLabel mainTitleLabel;// 主标题
	public static JLabel childTitleLabel;// 子标题
	// 主要内容
	public static JPanel mianContentPanel;
	public static JPanel westPanel;
	public static JPanel eastPanel;
	public static JPanel southPanel;
	public static JPanel emptyPanel;

	public ShowLotteryFrame() {
		//初始化数据
		initData();
		//初始化组件
		initComponent();
		//初始化界面
		initFrame();
		//初始化应用界面
		initUI();
	}

	/**
	 * 初始化数据
	 */
	public void initData() {
		// 获取主标题字体大小
		mainTitleFontSize = Integer.parseInt(
				PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.MAIN_TITLE_FONT_SIZE));
		// 获取子标题字体大小
		childTitleFontSize = Integer.parseInt(
				PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.CHILD_TITLE_FONT_SIZE));
		// 获取屏幕宽度
		screenWidth = Integer
				.parseInt(PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.SCREEN_WIDTH));
		// 获取屏幕高度
		screenHeight = Integer
				.parseInt(PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.SCREEN_HEIGHT));
		// 获取抽奖界面x坐标
		x = Integer.parseInt(PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.COORDINATE_X));
		// 获取界面上方高度
		northHeight = Integer
				.parseInt(PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.NORTH_HEIGHT));
		// 获取界面左右宽度
		marginWidth = Integer
				.parseInt(PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.MARGIN_WIDTH));
		// 获取界面下方高度
		southHeight = Integer
				.parseInt(PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.SOUTH_HEIGHT));
	}

	/**
	 * 初始化组件
	 */
	private void initComponent() {
		showLuckDrawFrame = this;
		contentPanel = new JPanel() {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				Image img = Utils.getPicture(Constant.BACKGROUND_FILE_PATH).getImage();
				g.drawImage(img, 0, 0, screenWidth, screenHeight, null);
			}
		};
		contentPanel.setLayout(new BorderLayout());

		cardLayout = new CardLayout();

		titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(screenWidth, northHeight));
		titlePanel.setOpaque(false);
		titlePanel.setLayout(new BorderLayout());

		mainTitlePanel = new JPanel();
		mainTitlePanel.setOpaque(false);
		mainTitlePanel.setLayout(new FlowLayout());

		mainTitleLabel = new JLabel(
				PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.MAIN_TITLE_TEXT),
				JLabel.CENTER);
		mainTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, mainTitleFontSize));
		mainTitleLabel.setForeground(Color.WHITE);

		childTitlePanel = new JPanel();
		childTitlePanel.setOpaque(false);
		childTitlePanel.setLayout(new FlowLayout());

		childTitleLabel = new JLabel(
				PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.CHILD_TITLE_TEXT),
				JLabel.CENTER);
		childTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, childTitleFontSize));
		childTitleLabel.setForeground(Color.ORANGE);

		mianContentPanel = new JPanel();
		mianContentPanel.setOpaque(false);
		mianContentPanel.setLayout(cardLayout);

		westPanel = new JPanel();
		westPanel.setOpaque(false);
		westPanel.setPreferredSize(new Dimension(marginWidth, 0));

		eastPanel = new JPanel();
		eastPanel.setOpaque(false);
		eastPanel.setPreferredSize(new Dimension(marginWidth, 0));

		southPanel = new JPanel();
		southPanel.setOpaque(false);
		southPanel.setPreferredSize(new Dimension(0, southHeight));

		emptyPanel = new JPanel();
		emptyPanel.setOpaque(false);
	}

	/**
	 * 初始化窗口
	 */
	private void initFrame() {
		setSize(screenWidth, screenHeight);
		setLocation(x, 0);
		setContentPane(contentPanel);
		setIconImage(new ImageIcon(Constant.LOGO_FILE_PATH).getImage());
		// 去除标题栏
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	}

	/**
	 * 初始化应用界面
	 */
	private void initUI() {
		contentPanel.add(titlePanel, BorderLayout.NORTH);
		contentPanel.add(mianContentPanel, BorderLayout.CENTER);
		contentPanel.add(westPanel, BorderLayout.WEST);
		contentPanel.add(eastPanel, BorderLayout.EAST);
		contentPanel.add(southPanel, BorderLayout.SOUTH);

		titlePanel.add(mainTitlePanel, BorderLayout.NORTH);
		titlePanel.add(childTitlePanel, BorderLayout.CENTER);

		mainTitlePanel.add(mainTitleLabel);
		childTitlePanel.add(childTitleLabel);

		mianContentPanel.add(emptyPanel, "emptyPanel");
		mianContentPanel.add(new ShowImagePanel(), "showImagePanel");
		mianContentPanel.add(new ShowLotteryPanel(), "showLuckyDrawPanel");
		mianContentPanel.add(new ShowRollEmpPanel(), "showRollPeoplePanel");
	}

}
