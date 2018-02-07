package com.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;


import com.action.MainFrameAction;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

public class MainFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPanel;
    private JButton startLuckyDrawBtn, luckyDrawSetBtn, exitBtn;

    public MainFrame() {
        initComponent();
        initFrame();
        initUI();
        initListener();
    }

    /**
     * 初始化窗口
     */
    private void initFrame() {
        setContentPane(contentPanel);
        setSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
    }

    /**
     * 初始化组件
     */
    private void initComponent() {
        startLuckyDrawBtn = new JButton("开始抽奖");
        startLuckyDrawBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
        startLuckyDrawBtn.setFont(new Font("微软雅黑", Font.BOLD, 40));
        luckyDrawSetBtn = new JButton("抽奖设置");
        luckyDrawSetBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
        luckyDrawSetBtn.setFont(new Font("微软雅黑", Font.BOLD, 40));
        exitBtn = new JButton("退    出");
        exitBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
        exitBtn.setFont(new Font("微软雅黑", Font.BOLD, 40));
        contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new GridLayout(3, 1));
    }

    /**
     * 构建应用界面
     */
    private void initUI() {
        // 窗口横向布局
        contentPanel.add(startLuckyDrawBtn);
        contentPanel.add(luckyDrawSetBtn);
        contentPanel.add(exitBtn);
    }

    /**
     * 初始化监听事件
     */
    private void initListener() {
        MainFrameAction mainFrameAction = new MainFrameAction(startLuckyDrawBtn, luckyDrawSetBtn,
                exitBtn);
        startLuckyDrawBtn.addActionListener(mainFrameAction);
        luckyDrawSetBtn.addActionListener(mainFrameAction);
        exitBtn.addActionListener(mainFrameAction);
    }
}
