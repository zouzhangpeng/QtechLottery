package com.util;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.SwingWorker;

import com.model.Constant;
import com.model.LotteryInfo;

public class WorkUtil extends SwingWorker<Object, Object> {

	@Override
	protected Object doInBackground() throws Exception {
		new SQLiteUtil().updateLuckyEmp();
		new SQLiteUtil().updateLuckyPrize();
		String path = PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, "path");
		String filePath = path + "//2017年晚会" + LotteryInfo.prizeType + LotteryInfo.prizeName + "中奖名单.pdf";
		File file = new File(filePath);
		try {
			List<Map<String, Object>> list = new SQLiteUtil().queryLuckyEmpInfo(LotteryInfo.prizeType,
					LotteryInfo.prizeName);
			new PdfWriteUtil(file).generatePDF(list, LotteryInfo.prizeType);
		} catch (Exception e1) {
			DialogUtil.showDialog(null, "保存文件失败！", Constant.ERROR_MESSAGE_DIALOG_TYPE);
			e1.printStackTrace();
		}
		return null;
	}

}
