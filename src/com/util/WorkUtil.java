package com.util;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.SwingWorker;

import com.model.Constant;
import com.model.LotteryInfo;
import com.view.LotterySetFrame;

public class WorkUtil extends SwingWorker<Object, Object> {

	@Override
	protected Object doInBackground() throws Exception {
		new SQLiteUtil().updateLuckyEmp();
		if (!"加码奖".equals(LotteryInfo.prizeType)) {
			new SQLiteUtil().updateLuckyPrize();
		}
		String path = PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, "path");
		String filePath = path + "//2017年晚会" + LotteryInfo.prizeType +"中奖名单.pdf";
		File file = new File(filePath);
		try {
			if (!"加码奖".equals(LotteryInfo.prizeType)) {
				new PdfWriteUtil(file).generatePDF(LotteryInfo.showPeopleList, LotteryInfo.prizeType);
			} else {
				List<Map<String, Object>> list = new SQLiteUtil().queryLuckyEmpInfo(LotteryInfo.prizeType,LotteryInfo.prizeTurn);
				new PdfWriteUtil(file).generatePDF(list, LotteryInfo.prizeType);
			}
		} catch (Exception e1) {
			DialogUtil.showDialog(null, "保存文件失败！", Constant.ERROR_MESSAGE_DIALOG_TYPE);
			e1.printStackTrace();
		}
		if (LotteryInfo.prizeCount == 2 || "".equals(LotteryInfo.prizeTurn)) {
			LotterySetFrame.prizeTypeComboBox.removeItemAt(LotteryInfo.prizeTypeIndex);
		} else {
			LotterySetFrame.prizeComboBox.removeItemAt(LotteryInfo.prizeIndex);
		}
		return null;
	}

}
