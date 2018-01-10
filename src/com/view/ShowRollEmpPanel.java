package com.view;

import java.awt.*;
import javax.swing.*;

public class ShowRollEmpPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // 提示信息
    public static JPanel northPanel;
    public static JPanel showRollPeopleTipPanel;
    public static JLabel showRollPeopleTipLabel;
    public static JPanel centerPanel;
    public static JPanel tableHeaderPanel;
    public static JPanel tableBodyPanel;

    public ShowRollEmpPanel() {
        initComponent();
        initPanelSet();
        initUI();
    }

    private void initComponent() {
        northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setLayout(new BorderLayout());

        showRollPeopleTipPanel = new JPanel();
        showRollPeopleTipPanel.setOpaque(false);
        showRollPeopleTipPanel.setLayout(new FlowLayout());

        showRollPeopleTipLabel = new JLabel("恭喜以下同仁中奖！", JLabel.CENTER);
        showRollPeopleTipLabel.setFont(new Font("方正少儿简体", Font.BOLD, 50));
        showRollPeopleTipLabel.setForeground(Color.ORANGE);

        centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BorderLayout());

        tableHeaderPanel = new JPanel();
        tableHeaderPanel.setOpaque(false);
        tableHeaderPanel.setLayout(null);
        tableHeaderPanel.setPreferredSize(new Dimension(0,50));

        tableBodyPanel = new JPanel();
        tableBodyPanel.setOpaque(false);
        tableBodyPanel.setLayout(null);

    }

    private void initPanelSet() {
        setOpaque(false);
        setLayout(new BorderLayout());
    }

    private void initUI() {
        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        northPanel.add(showRollPeopleTipPanel, BorderLayout.CENTER);

        showRollPeopleTipPanel.add(showRollPeopleTipLabel);

        centerPanel.add(tableHeaderPanel, BorderLayout.NORTH);
        centerPanel.add(tableBodyPanel, BorderLayout.CENTER);
    }


}
