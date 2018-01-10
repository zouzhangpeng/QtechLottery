package com.util;

public class ConversionUtil {
	/**
	 * 数字转中文
	 * @param num
	 * @return
	 */
	public static String numToChinese(int num) {
		String string = null;
		switch (num) {
		case 0:
			string = "当前主屏";
			break;
		case 1:
			string = "第一个额外设备屏幕";
			break;
		case 2:
			string = "第二个额外设备屏幕";
			break;
		case 3:
			string = "第三个额外设备屏幕";
			break;
		case 4:
			string = "第四个额外设备屏幕";
			break;
		case 5:
			string = "第五个额外设备屏幕";
			break;
		case 6:
			string = "第六个额外设备屏幕";
			break;
		case 7:
			string = "第七个额外设备屏幕";
			break;
		case 8:
			string = "第八个额外设备屏幕";
			break;
		case 9:
			string = "第九个额外设备屏幕";
			break;
		default:
			string = "第十个额外设备屏幕";
			break;
		}
		return string;
	}

	/**
	 * 中文转数字
	 * @param string
	 * @return
	 */
	public static int chineseToNum(String string) {
		int num = 0;
		switch (string) {
		case "当前主屏":
			num = 0;
			break;
		case "第一个额外设备屏幕":
			num = 1;
			break;
		case "第二个额外设备屏幕":
			num = 2;
			break;
		case "第三个额外设备屏幕":
			num = 3;
			break;
		case "第四个额外设备屏幕":
			num = 4;
			break;
		case "第五个额外设备屏幕":
			num = 5;
			break;
		case "第六个额外设备屏幕":
			num = 6;
			break;
		case "第七个额外设备屏幕":
			num = 7;
			break;
		case "第八个额外设备屏幕":
			num = 8;
			break;
		case "第九个额外设备屏幕":
			num = 9;
			break;
		default:
			num = 0;
			break;
		}
		return num;
	}
	
	   /**
     * 根据奖项和奖品获取对应图片名称
     *
     * @param prizeType 奖项
     * @param prizeName 奖品
     * @return
     */
    public static String getImageNamePath(String prizeType, String prizeName) {
        StringBuffer imagePath = new StringBuffer(prizeName);
        imagePath.append("-");
        switch (prizeType) {
            case "特等奖":
                imagePath.append(0);
                break;
            case "一等奖":
                imagePath.append(1);
                break;
            case "二等奖":
                imagePath.append(2);
                break;
            case "三等奖":
                imagePath.append(3);
                break;
            case "四等奖":
                imagePath.append(4);
                break;
            case "五等奖":
                imagePath.append(5);
                break;
            case "六等奖":
                imagePath.append(6);
                break;
            case "七等奖":
                imagePath.append(7);
                break;
            case "八等奖":
                imagePath.append(8);
                break;
            case "九等奖":
                imagePath.append(9);
                break;
            case "十等奖":
                imagePath.append(10);
                break;
        }
        imagePath.append(".jpg");
        return imagePath.toString();
    }
}
