package com.view;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.action.LotterySetFrameAction;
import com.model.Constant;
import com.model.LotteryInfo;
import com.util.DialogUtil;
import com.util.SQLiteUtil;

public class LotterySetFrame extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPanel;
	public static JLabel prizeTypeLabel, prizeLabel, quantityLabel;
	public static JComboBox<String> prizeTypeComboBox, prizeComboBox;
	public static JButton showPictureBtn, startBtn, endBtn, esureBtn, testBtn, closelotteryBtn;
	public static JTextField prizeTypeText, prizeText, quantityText;
	public static String[] prizeTypes, prizes;
	public static JFrame lotterySetFrame;

	public LotterySetFrame() {
		initData();
		initComponent();
		initFrame();
		initUI();
		initListener();
	}

	/**
	 * 初始化组件数据
	 */
	private void initData() {
		prizeTypes = new SQLiteUtil().queryPrizeTypes();
		prizes = new SQLiteUtil().queryPrizes(null);
	}

	/**
	 * 初始化组件
	 */
	private void initComponent() {
		lotterySetFrame = this;
		contentPanel = new JPanel();
		contentPanel.setLayout(null);

		prizeTypeLabel = new JLabel("当前奖项  ", JLabel.CENTER);
		prizeTypeText = new JTextField();
		prizeTypeText.setEditable(false);
		prizeTypeComboBox = new JComboBox<String>(prizeTypes);

		prizeLabel = new JLabel("当前奖品  ", JLabel.CENTER);
		prizeText = new JTextField();
		prizeText.setEditable(false);
		prizeComboBox = new JComboBox<String>(prizes);

		quantityLabel = new JLabel("数量", JLabel.CENTER);
		quantityText = new JTextField();
		quantityText.setEditable(false);

		esureBtn = new JButton("确定");
		esureBtn.setEnabled(false);
		esureBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		showPictureBtn = new JButton("展示奖品");
		showPictureBtn.setEnabled(false);
		showPictureBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		startBtn = new JButton("开始抽奖");
		startBtn.setEnabled(false);
		startBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		endBtn = new JButton("结束抽奖");
		endBtn.setEnabled(false);
		endBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));

		closelotteryBtn = new JButton("关闭抽奖界面");
		closelotteryBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
	}

	/**
	 * 初始化窗口
	 */
	private void initFrame() {
		setContentPane(contentPanel);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		// 设置尺寸
		setSize(new Dimension(600, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * 初始化应用界面
	 */
	private void initUI() {
		prizeTypeLabel.setBounds(60, 60, 60, 30);
		contentPanel.add(prizeTypeLabel);

		prizeTypeText.setBounds(130, 60, 200, 30);
		contentPanel.add(prizeTypeText);

		prizeTypeComboBox.setBounds(340, 60, 200, 30);
		contentPanel.add(prizeTypeComboBox);

		prizeLabel.setBounds(60, 100, 60, 30);
		contentPanel.add(prizeLabel);

		prizeText.setBounds(130, 100, 200, 30);
		contentPanel.add(prizeText);

		prizeComboBox.setBounds(340, 100, 200, 30);
		contentPanel.add(prizeComboBox);

		quantityLabel.setBounds(60, 140, 60, 30);
		contentPanel.add(quantityLabel);

		quantityText.setBounds(130, 140, 200, 30);
		contentPanel.add(quantityText);

		esureBtn.setBounds(260, 180, 80, 30);
		contentPanel.add(esureBtn);

		showPictureBtn.setBounds(260, 220, 80, 30);
		contentPanel.add(showPictureBtn);

		startBtn.setBounds(260, 260, 80, 30);
		contentPanel.add(startBtn);

		endBtn.setBounds(260, 300, 80, 30);
		contentPanel.add(endBtn);

		closelotteryBtn.setBounds(480, 340, 100, 30);
		contentPanel.add(closelotteryBtn);

	}

	/**
	 * 初始化监听事件
	 */
	private void initListener() {
		LotterySetFrameAction showFrameAction = new LotterySetFrameAction();
		prizeTypeComboBox.addActionListener(showFrameAction);
		prizeComboBox.addActionListener(showFrameAction);
		esureBtn.addActionListener(showFrameAction);
		showPictureBtn.addActionListener(showFrameAction);
		startBtn.addActionListener(showFrameAction);
		endBtn.addActionListener(showFrameAction);
		closelotteryBtn.addActionListener(showFrameAction);
		// 关闭事件
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				if (LotteryInfo.lotteryStatus == 1) {
					DialogUtil.showDialog(null, "抽奖过程中无法退出！", Constant.WARNING_MESSAGE_DIALOG_TYPE);
					return;
				}
			}
		});
	}

	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			if (LotteryInfo.lotteryStatus == 1) {
				return; // 直接返回，阻止默认动作，阻止窗口关闭
			}
		}
		super.processWindowEvent(e); // 该语句会执行窗口事件的默认动作(如：隐藏)
	}
}
