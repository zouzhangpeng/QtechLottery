package com.util;

import java.awt.Color;
import java.awt.Font;
import java.util.TimerTask;

import javax.swing.*;

import com.model.LotteryInfo;
import com.view.ShowLotteryPanel;

public class Task extends TimerTask {

    @Override
    public void run() {
        ShowLotteryPanel.luckyDrawPanel.removeAll();
        if("加码奖".equals(LotteryInfo.prizeType)) {
        	LotteryInfo.luckyPeopleList = new SQLiteUtil().getRandomExtra();
        }else {
        	LotteryInfo.luckyPeopleList = new SQLiteUtil().getRandom();
        }
        JLabel[] textList = new JLabel[LotteryInfo.luckyPeopleList.size()];
        for (int i = 0, j = textList.length; i < j; i++) {
            textList[i] = new JLabel(LotteryInfo.luckyPeopleList.get(i).get("emp_no") + " "
                    + Utils.nameFormat(LotteryInfo.luckyPeopleList.get(i).get("emp_name").toString()),JLabel.CENTER);
            textList[i].setFont(new Font("微软雅黑", Font.BOLD, 40));
            textList[i].setForeground(Color.WHITE);
            textList[i].setOpaque(false);
            ShowLotteryPanel.luckyDrawPanel.add(textList[i]);
        }
        ShowLotteryPanel.luckyDrawPanel.validate();
        ShowLotteryPanel.luckyDrawPanel.repaint();
    }

}
