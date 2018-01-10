package com.util;

import java.io.*;
import java.util.Properties;

/**
 * 读写properties文件
 */
public class PropertiesUtil {

	/**
	 * 根据key值读取properties文件
	 *
	 * @param key
	 * @return
	 */
	public static String getValueByKey(String propertiesPath, String key) {
		Properties pps = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(propertiesPath));
			pps.load(in);
			String value = pps.getProperty(key);
			return value;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 写入properties文件
	 * 
	 * @param pKey
	 * @param pValue
	 */
	public static void writeProperties(String propertiesPath, String pKey, String pValue) {
		Properties pps = new Properties();
		try {
			InputStream in = new FileInputStream(propertiesPath);
			pps.load(in);
			OutputStream out = new FileOutputStream(propertiesPath);
			pps.setProperty(pKey, pValue);
			pps.store(out, "Update " + pKey + " name");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}