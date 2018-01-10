package com.action;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.model.Constant;
import com.model.LotteryInfo;
import com.util.AnimationUtil;
import com.util.ImageLabelUtil;
import com.util.PropertiesUtil;
import com.util.SQLiteUtil;
import com.util.ShowListUtil;
import com.util.TableModelUtil;
import com.util.TableUtil;
import com.util.Task;
import com.util.WorkUtil;
import com.view.LotterySetFrame;
import com.view.ShowImagePanel;
import com.view.ShowLotteryFrame;
import com.view.ShowRollEmpPanel;

public class LotterySetFrameAction implements ActionListener {
	private ScheduledExecutorService service;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == LotterySetFrame.prizeTypeComboBox) {
			// 选择奖项
			String[] prizes = new SQLiteUtil()
					.queryPrizes(LotterySetFrame.prizeTypeComboBox.getSelectedItem().toString());
			LotterySetFrame.prizeComboBox.removeAllItems();
			for (int i = 0, j = prizes.length; i < j; i++) {
				LotterySetFrame.prizeComboBox.addItem(prizes[i]);
			}
			LotterySetFrame.esureBtn.setEnabled(true);
		} else if (e.getSource() == LotterySetFrame.esureBtn) {
			// 确定按钮
			// 奖项名称
			LotteryInfo.prizeType = LotterySetFrame.prizeTypeComboBox.getSelectedItem().toString();
			// 奖品名称
			String prizeComboBoxString = LotterySetFrame.prizeComboBox.getSelectedItem().toString();
			LotteryInfo.prizeName = (prizeComboBoxString.equals("==请选择==") ? "" : prizeComboBoxString);
			LotteryInfo.aPrizeInfoList = new SQLiteUtil().getLotteryPrizeInfoByType(LotteryInfo.prizeType,
					LotteryInfo.prizeName, "prize_aemp");
			LotteryInfo.bPrizeInfoList = new SQLiteUtil().getLotteryPrizeInfoByType(LotteryInfo.prizeType,
					LotteryInfo.prizeName, "prize_bemp");
			LotteryInfo.cPrizeInfoList = new SQLiteUtil().getLotteryPrizeInfoByType(LotteryInfo.prizeType,
					LotteryInfo.prizeName, "prize_cemp");
			LotteryInfo.dPrizeInfoList = new SQLiteUtil().getLotteryPrizeInfoByType(LotteryInfo.prizeType,
					LotteryInfo.prizeName, "prize_demp");
			LotteryInfo.aEmpQuantity = 0;
			for (int i = 0, j = LotteryInfo.aPrizeInfoList.size(); i < j; i++) {
				LotteryInfo.aEmpQuantity += (int) LotteryInfo.aPrizeInfoList.get(i).get("prize_aemp");
			}
			LotteryInfo.bEmpQuantity = 0;
			for (int i = 0, j = LotteryInfo.bPrizeInfoList.size(); i < j; i++) {
				LotteryInfo.bEmpQuantity += (int) LotteryInfo.bPrizeInfoList.get(i).get("prize_bemp");
			}
			LotteryInfo.cEmpQuantity = 0;
			for (int i = 0, j = LotteryInfo.cPrizeInfoList.size(); i < j; i++) {
				LotteryInfo.cEmpQuantity += (int) LotteryInfo.cPrizeInfoList.get(i).get("prize_cemp");
			}
			LotteryInfo.dEmpQuantity = 0;
			for (int i = 0, j = LotteryInfo.dPrizeInfoList.size(); i < j; i++) {
				LotteryInfo.dEmpQuantity += (int) LotteryInfo.dPrizeInfoList.get(i).get("prize_demp");
			}
			LotteryInfo.lotteryQuantity = LotteryInfo.aEmpQuantity + LotteryInfo.bEmpQuantity + LotteryInfo.cEmpQuantity
					+ LotteryInfo.dEmpQuantity;
			LotterySetFrame.prizeTypeText.setText(LotteryInfo.prizeType);
			LotterySetFrame.prizeText.setText(LotteryInfo.prizeName);
			LotterySetFrame.quantityText.setText(String.valueOf(LotteryInfo.lotteryQuantity));
			LotterySetFrame.showPictureBtn.setEnabled(true);
		} else if (e.getSource() == LotterySetFrame.showPictureBtn) {
			// 展示图片
			ShowImagePanel.imageContainerPanel.removeAll();
			// 跳转到图片展示panel
			ShowImagePanel.prizeLabel.setText(LotteryInfo.prizeType + " " + LotteryInfo.prizeName);
			ShowImagePanel.prizeQuantityLabel.setText(LotteryInfo.lotteryQuantity + "名");
			JPanel animationHContainerPanel = new JPanel();
			animationHContainerPanel.setOpaque(false);
			animationHContainerPanel.setLayout(null);
			ShowImagePanel.imageContainerPanel.add(animationHContainerPanel, BorderLayout.CENTER);
			List<JLabel> imageLabelList = ImageLabelUtil.getImageLabelList(Constant.SHOW_IMAGE_PATH,
					LotteryInfo.prizeType, LotteryInfo.prizeName);
			JPanel imagePanel = new JPanel();
			imagePanel.setOpaque(false);
			imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS));
			for (int i = 0, j = imageLabelList.size(); i < j; i++) {
				imagePanel.add(imageLabelList.get(i));
			}
			animationHContainerPanel.add(imagePanel);
			ShowLotteryFrame.cardLayout.show(ShowLotteryFrame.mianContentPanel, "emptyPanel");
			ShowLotteryFrame.cardLayout.show(ShowLotteryFrame.mianContentPanel, "showImagePanel");
			int parentWidth = ShowImagePanel.imageContainerPanel.getWidth();
			int parentHeight = ShowImagePanel.imageContainerPanel.getHeight();
			int imageWidth = Integer.parseInt(
					PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.SHOW_IMAGE_WIDTH));
			int imageHeight = Integer.parseInt(
					PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.SHOW_IMAGE_HEIGHT));
			int imagePanelWidth = imageWidth * imageLabelList.size();
			if (imagePanelWidth <= parentWidth) {
				imagePanel.setBounds((parentWidth - imagePanelWidth) / 2, (parentHeight - imageHeight) / 2,
						imagePanelWidth, imageHeight);
				animationHContainerPanel.add(imagePanel);
			} else {
				imagePanel.setBounds(-imagePanelWidth, (parentHeight - imageHeight) / 2, imagePanelWidth, imageHeight);
				animationHContainerPanel.add(imagePanel);
				List<JLabel> cycleImageLabelList = ImageLabelUtil.getImageLabelList(Constant.SHOW_IMAGE_PATH,
						LotteryInfo.prizeType, LotteryInfo.prizeName);
				JPanel cycleImagePanel = new JPanel();
				cycleImagePanel.setOpaque(false);
				cycleImagePanel.setLayout(new BoxLayout(cycleImagePanel, BoxLayout.X_AXIS));
				for (int i = 0, j = cycleImageLabelList.size(); i < j; i++) {
					cycleImagePanel.add(cycleImageLabelList.get(i));
				}
				cycleImagePanel.setBounds(-imagePanelWidth * 2, (parentHeight - imageHeight) / 2, imagePanelWidth,
						imageHeight);
				animationHContainerPanel.add(cycleImagePanel);
				AnimationUtil.horizontalRoll(imagePanel, cycleImagePanel, parentWidth, imagePanelWidth, imageHeight,
						(parentHeight - imageHeight) / 2);
			}
			LotterySetFrame.showPictureBtn.setEnabled(false);
			LotterySetFrame.esureBtn.setEnabled(false);
			LotterySetFrame.startBtn.setEnabled(true);
			LotterySetFrame.closelotteryBtn.setEnabled(false);
			LotteryInfo.lotteryStatus = 1;
		} else if (e.getSource() == LotterySetFrame.startBtn) {
			int frequency = Integer
					.parseInt(PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, "frequency"));
			service = Executors.newScheduledThreadPool(1);
			service.scheduleAtFixedRate(new Task(), 0, frequency, TimeUnit.MILLISECONDS);
			ShowLotteryFrame.cardLayout.show(ShowLotteryFrame.mianContentPanel, "emptyPanel");
			ShowLotteryFrame.cardLayout.show(ShowLotteryFrame.mianContentPanel, "showLuckyDrawPanel");
			LotterySetFrame.startBtn.setEnabled(false);
			LotterySetFrame.endBtn.setEnabled(true);
		} else if (e.getSource() == LotterySetFrame.endBtn) {
			service.shutdown();
			long startTime = System.currentTimeMillis();
			LotteryInfo.showPeopleList = new ArrayList<Map<String, Object>>();
			new ShowListUtil().showListUtil(LotteryInfo.aPrizeInfoList, LotteryInfo.aempList, "prize_aemp");
			new ShowListUtil().showListUtil(LotteryInfo.bPrizeInfoList, LotteryInfo.bempList, "prize_bemp");
			new ShowListUtil().showListUtil(LotteryInfo.cPrizeInfoList, LotteryInfo.cempList, "prize_cemp");
			new ShowListUtil().showListUtil(LotteryInfo.dPrizeInfoList, LotteryInfo.dempList, "prize_demp");
			ShowLotteryFrame.cardLayout.show(ShowLotteryFrame.mianContentPanel, "emptyPanel");
			ShowLotteryFrame.cardLayout.show(ShowLotteryFrame.mianContentPanel, "showRollPeoplePanel");
			ShowRollEmpPanel.tableHeaderPanel.removeAll();
			ShowRollEmpPanel.tableBodyPanel.removeAll();
			// 获取中奖员工信息
			List<String[]> luckyEmps = new ArrayList<String[]>();
			for (int i = 0, j = LotteryInfo.showPeopleList.size(); i < j; i++) {
				String[] empInfos = new String[] { (String) LotteryInfo.showPeopleList.get(i).get("emp_no"),
						(String) LotteryInfo.showPeopleList.get(i).get("emp_name"),
						(String) LotteryInfo.showPeopleList.get(i).get("prize_type"),
						(String) LotteryInfo.showPeopleList.get(i).get("prize_name") };
				luckyEmps.add(empInfos);
			}
			String[] tableTitle = new String[] { "工号", "姓名", "奖项", "奖品" };
			// 表头
			JTable luckyEmpTable = new TableUtil().getTable(new TableModelUtil(luckyEmps, tableTitle),
					ShowRollEmpPanel.centerPanel);
			int tableH = Integer
					.parseInt(PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, "tableHeight"));
			int tableHeight = luckyEmps.size() * tableH;
			int parentWidth = ShowRollEmpPanel.tableBodyPanel.getWidth();
			int parentHeight = ShowRollEmpPanel.tableBodyPanel.getHeight();
			luckyEmpTable.getTableHeader().setBounds(0, 0, parentWidth, tableH);
			ShowRollEmpPanel.tableHeaderPanel.add(luckyEmpTable.getTableHeader());
			if (tableHeight <= parentHeight - 30) {
				luckyEmpTable.setBounds(0, 20, parentWidth, tableHeight);
				ShowRollEmpPanel.tableBodyPanel.add(luckyEmpTable);
			} else {
				luckyEmpTable.setBounds(0, -tableHeight, parentWidth, tableHeight);
				ShowRollEmpPanel.tableBodyPanel.add(luckyEmpTable);
				JTable cloneTable = new TableUtil().getTable(new TableModelUtil(luckyEmps, tableTitle),
						ShowRollEmpPanel.centerPanel);
				cloneTable.setBounds(0, -tableHeight * 2, parentWidth, tableHeight);
				ShowRollEmpPanel.tableBodyPanel.add(cloneTable);
				AnimationUtil.verticalRoll(luckyEmpTable, cloneTable, parentHeight, parentWidth, tableHeight);
			}
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new WorkUtil().execute();
				}
			});
			LotterySetFrame.endBtn.setEnabled(false);
			LotterySetFrame.esureBtn.setEnabled(true);
			LotterySetFrame.closelotteryBtn.setEnabled(true);
			LotteryInfo.lotteryStatus = 0;
			long endTime = System.currentTimeMillis();
			System.out.println(endTime-startTime);
		} else if (e.getSource() == LotterySetFrame.closelotteryBtn) {
			ShowLotteryFrame.showLuckDrawFrame.dispose();
			LotterySetFrame.lotterySetFrame.dispose();
		}
	}

}
