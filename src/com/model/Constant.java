package com.model;

public class Constant {
	// 屏幕宽度
	public final static String SCREEN_WIDTH = "screenWidth";
	// 屏幕高度
	public final static String SCREEN_HEIGHT = "screenHeight";
	// 抽奖界面x坐标
	public final static String COORDINATE_X = "x";
	// 抽奖界面边距长度
	public final static String MARGIN_WIDTH = "marginWidth";
	// 抽奖界面上方高度
	public final static String NORTH_HEIGHT = "northHeight";
	// 抽奖界面下方高度
	public final static String SOUTH_HEIGHT = "southHeight";
	// 主活动标题
	public final static String MAIN_TITLE_TEXT = "mainTitleText";
	// 主标题字体大小
	public final static String MAIN_TITLE_FONT_SIZE = "mainTitleFontSize";
	// 子活动标题
	public final static String CHILD_TITLE_TEXT = "childTitleText";
	// 子标题字体大小
	public final static String CHILD_TITLE_FONT_SIZE = "childTitleFontSize";
	// 奖品信息字体大小
	public final static String PRIZE_INFO_FONT = "prizeInfoFont";
	// 基础设置properties文件
	public final static String CONFIG_PROPERTIES_FILE_PATH = "config.properties";
	// 展示图片路径
	public final static String SHOW_IMAGE_PATH = "Image/ShowImage";
	// 展示图片宽度
	public final static String SHOW_IMAGE_WIDTH = "showImageWidth";
	// 展示图片高度
	public final static String SHOW_IMAGE_HEIGHT = "showImageHeight";
	// 抽奖中提示
	public final static String LUCKY_DRAW_TIP = "luckyDrawTip";
	// 背景图片目录
	public final static String BACKGROUND_FILE_PATH = "Image/Background/background.jpg";
	//logo图片目录
	public final static String LOGO_FILE_PATH = "Image/Logo/logo.ico";

	/**
	 * SQLite信息
	 */
	public final static String SQLITE_DRIVER = "org.sqlite.JDBC";// SQLite连接驱动
	public final static String SQLITE_URL = "jdbc:sqlite:qtechlottry.db";// SQLite连接地址

	/**
	 * 弹窗类型
	 */
	public final static String ERROR_MESSAGE_DIALOG_TYPE = "错误";// 错误弹窗
	public final static String INFORMATION_MESSAGE_DIALOG_TYPE = "信息";// 信息弹窗
	public final static String WARNING_MESSAGE_DIALOG_TYPE = "警告"; // 警告弹窗

	/**
	 * 弹窗信息
	 */
	public final static String LIMIT_PRIZE_TYPE_DIALOG_MESSAGE = "超出软件奖项上限！";// 超出软件奖项上限！
	public final static String SQL_ERROR_DIALOG_MESSAGE = "数据库连接错误！";// 数据库连接错误！
	public final static String LOTTERY_DIALOG_MESSAGE = "请先打开抽奖界面";
	public final static String UPDATE_SUCCESS_DIALOG_MESSAGE = "更新成功！";
}
