package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.Constant;
import com.util.PropertiesUtil;

public class ShowImagePanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // 奖品信息
    public static JPanel prizeInfoPanel;
    public static JPanel prizePanel;
    public static JPanel prizeQuantityPanel;
    public static JLabel prizeLabel;
    public static JLabel prizeQuantityLabel;
    // 横向播放动画
    public static JPanel showImagePanel;
    public static JPanel imageContainerPanel;
    public static int prizeInfoFont;

    public ShowImagePanel() {
    	initData();
        initComponent();
        initUI();
    }

    public void initData() {
    	prizeInfoFont = Integer
				.parseInt(PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.PRIZE_INFO_FONT));
	}

	/**
     * 初始化组件
     */
    public void initComponent() {
        showImagePanel = this;
        // 奖品信息
        prizeInfoPanel = new JPanel();
        prizeInfoPanel.setOpaque(false);
        prizeInfoPanel.setLayout(new BorderLayout());

        prizePanel = new JPanel();
        prizePanel.setOpaque(false);
        prizePanel.setLayout(new FlowLayout());

        prizeQuantityPanel = new JPanel();
        prizeQuantityPanel.setOpaque(false);
        prizeQuantityPanel.setLayout(new FlowLayout());

        prizeLabel = new JLabel(" ", JLabel.CENTER);
        prizeLabel.setFont(new Font("微软雅黑", Font.BOLD, prizeInfoFont));
        prizeLabel.setForeground(Color.YELLOW);

        prizeQuantityLabel = new JLabel(" ", JLabel.CENTER);
        prizeQuantityLabel.setFont(new Font("微软雅黑", Font.BOLD, prizeInfoFont));
        prizeQuantityLabel.setForeground(Color.YELLOW);

        // 横向播放动画
        imageContainerPanel = new JPanel();
        imageContainerPanel.setOpaque(false);
        imageContainerPanel.setLayout(new BorderLayout());

    }

    /**
     * 初始化应用界面
     */
    public void initUI() {
        setOpaque(false);
        setLayout(new BorderLayout());
        add(prizeInfoPanel, BorderLayout.NORTH);
        add(imageContainerPanel, BorderLayout.CENTER);

        prizeInfoPanel.add(prizePanel, BorderLayout.NORTH);
        prizeInfoPanel.add(prizeQuantityPanel, BorderLayout.CENTER);

        prizePanel.add(prizeLabel);
        prizeInfoPanel.add(prizeQuantityLabel);

    }
}
