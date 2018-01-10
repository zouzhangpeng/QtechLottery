package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowLotteryPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // 提示信息
    public static JPanel showLuckyDrawTipPanel;
    public static JLabel showLuckyDrawTipLabel;
    public static JPanel luckyDrawContainerPanel;
    public static JPanel luckyDrawPanel;

    public ShowLotteryPanel() {
        initComponent();
        initUI();
    }

    private void initComponent() {
        showLuckyDrawTipPanel = new JPanel();
        showLuckyDrawTipPanel.setOpaque(false);
        showLuckyDrawTipPanel.setLayout(new FlowLayout());

        showLuckyDrawTipLabel = new JLabel("好紧张哦，期待中！", JLabel.CENTER);
        showLuckyDrawTipLabel.setFont(new Font("方正少儿简体", Font.BOLD, 50));
        showLuckyDrawTipLabel.setForeground(Color.ORANGE);

        luckyDrawContainerPanel = new JPanel();
        luckyDrawContainerPanel.setOpaque(false);
        luckyDrawContainerPanel.setLayout(new BorderLayout());

        luckyDrawPanel = new JPanel();
        luckyDrawPanel.setOpaque(false);
        luckyDrawPanel.setLayout(new FlowLayout(1,40,10));

    }

    private void initUI() {
        setOpaque(false);
        setLayout(new BorderLayout());
        add(showLuckyDrawTipPanel, BorderLayout.NORTH);
        add(luckyDrawContainerPanel, BorderLayout.CENTER);
        showLuckyDrawTipPanel.add(showLuckyDrawTipLabel);
        luckyDrawContainerPanel.add(luckyDrawPanel, BorderLayout.CENTER);
    }

}
