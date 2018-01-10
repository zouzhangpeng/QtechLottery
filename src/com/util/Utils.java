package com.util;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Utils {

    /**
     * 获取图片
     *
     * @param path
     * @return
     */
    public static ImageIcon getPicture(String path) {
        ImageIcon image = null;
        File file = new File(path);
        if (!file.exists()) {
            showWarningMessage("资源图片加载失败，请重新安装", "Warning");
        } else {
            image = new ImageIcon(path);
        }
        return image;
    }

    /**
     * 警告窗口
     *
     * @param tips
     */
    public static void showWarningMessage(String tips, String type) {
        JOptionPane.showMessageDialog(null, tips, type, JOptionPane.WARNING_MESSAGE);
    }


    /**
     * 数组转字符串
     *
     * @param arr
     */
    public static String arrayToString(String[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0, j = arr.length; i < j; i++) {
            sb.append(arr[i] + ",");
        }
        String str = sb.toString();
        str = str.substring(0, str.length() - 1);
        return str;
    }

    public static String nameFormat(String string) {
        StringBuffer sbf = new StringBuffer(string);
        if (string.length() == 2) {
            sbf.insert(1, "   ");
        } else {
            return string;
        }
        return sbf.toString();
    }

    public static String departmentFormat(String string) {
        StringBuffer sbf = new StringBuffer(string);
        if (string.length() == 3) {
            sbf.insert(0, "  ");
            sbf.insert(5, "  ");
        } else {
            return string;
        }
        return sbf.toString();
    }

    /**
     * 根据*截取字符串
     *
     * @param str
     * @return
     */
    public static String[] removeStr(String str) {
        String[] strs = str.split("\\*");
        return strs;
    }

}
