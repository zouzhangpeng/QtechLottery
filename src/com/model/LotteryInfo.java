package com.model;

import java.util.List;
import java.util.Map;

/**
 * 中间变量
 * 
 * @author zhangpeng.zhou
 *
 */
public class LotteryInfo {
	/**
	 * 当前奖项
	 */
	public static String prizeType;
	/**
	 * 当前奖品
	 */
	public static String prizeName;
	/**
	 * 当前抽奖总数量
	 */
	public static int lotteryQuantity;
	/**
	 * 抽中的员工
	 */
	public static List<Map<String, Object>> luckyPeopleList;
	/**
	 * 当前抽奖展示员工
	 */
	public static List<Map<String, Object>> showPeopleList;
	/**
	 * 当前抽奖A类型奖品信息
	 */
	public static List<Map<String, Object>> aPrizeInfoList;
	/**
	 * 当前抽奖B类型奖品信息
	 */
	public static List<Map<String, Object>> bPrizeInfoList;
	/**
	 * 当前抽奖C类型奖品信息
	 */
	public static List<Map<String, Object>> cPrizeInfoList;
	/**
	 * 当前抽奖D类型奖品信息
	 */
	public static List<Map<String, Object>> dPrizeInfoList;
	/**
	 * 当前抽奖A类型员工
	 */
	public static List<Map<String, Object>> aempList;
	/**
	 * 当前抽奖B类型员工
	 */
	public static List<Map<String, Object>> bempList;
	/**
	 * 当前抽奖C类型员工
	 */
	public static List<Map<String, Object>> cempList;
	/**
	 * 当前抽奖D类型员工
	 */
	public static List<Map<String, Object>> dempList;
	/**
	 * 当前抽奖A类型奖品数量
	 */
	public static int aEmpQuantity;
	/**
	 * 当前抽奖B类型奖品数量
	 */
	public static int bEmpQuantity;
	/**
	 * 当前抽奖C类型奖品数量
	 */
	public static int cEmpQuantity;
	/**
	 * 当前抽奖D类型奖品数量
	 */
	public static int dEmpQuantity;
	/**
	 * 加码奖数量
	 */
	public static int extraPrizeQuantity;
	/**
	 * 抽奖状态
	 */
	public static int lotteryStatus = 0;
	/**
	 * 奖项下拉框选择序号
	 */
	public static int prizeTypeIndex = 0;
	/**
	 * 奖品下拉框选择序号
	 */
	public static int prizeIndex = 0;
	/**
	 * 奖项下拉框选项数
	 */
	public static int prizeCount = 0;

}
