package com.util;

import com.model.Constant;
import com.model.LotteryInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQLite工具类
 * 
 * @author zhangpeng.zhou
 *
 */
public class SQLiteUtil {
	public String driver = null;
	public String url = null;
	private Connection con = null;// 连接对象
	private PreparedStatement pstmt = null;// 语句对象
	private ResultSet rs = null;// 结果集对象

	/**
	 * 查询奖项
	 *
	 * @return
	 */
	public String[] queryPrizeTypes() {
		String sql = "select distinct prize_type from prize where prize_quantity <> 0 order by id desc";
		List<Map<String, Object>> list = queryData(sql);
		String[] prizeTypes = new String[list.size() + 2];
		prizeTypes[0] = "==请选择==";
		prizeTypes[1] = "加码奖";
		for (int i = 0, j = list.size(); i < j; i++) {
			prizeTypes[i + 2] = (String) list.get(i).get("prize_type");
		}
		return prizeTypes;
	}

	/**
	 * 根据奖项查询奖品信息
	 *
	 * @param prizeType
	 */
	public String[] queryPrizes(String prizeType) {
		String sql = "select prize_name,prize_quantity from prize where prize_type ='" + prizeType
				+ "' and prize_quantity <> 0";
		List<Map<String, Object>> list = queryData(sql);
		String[] prizes = new String[list.size() + 1];
		prizes[0] = "==请选择==";
		for (int i = 0, j = list.size(); i < j; i++) {
			prizes[i + 1] = list.get(i).get("prize_name").toString();
		}
		return prizes;
	}

	/**
	 * 根据类型奖品信息
	 * 
	 * @param prizeType
	 * @param prizeName
	 * @param empType
	 * @return
	 */
	public List<Map<String, Object>> getLotteryPrizeInfoByType(String prizeType, String prizeName, String empType) {
		String sql = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (prizeName.equals("") || prizeName == null) {
			sql = "select prize_type, prize_name," + empType + " from prize where prize_type = '" + prizeType + "'";
			list = queryData(sql);
		} else {
			sql = "select prize_type,prize_name," + empType + " from prize where prize_name = '" + prizeName + "'";
			list = queryData(sql);
		}
		return list;
	}

	/**
	 * 随机抽取幸运员工
	 *
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> getRandom() {
		List<Map<String, Object>> empInfoList = new ArrayList<Map<String, Object>>();
		String sqlA = "select emp_no,emp_name,emp_first_department,emp_secondary_department,emp_three_department from employee where emp_type = 'A' and prize_name is null ORDER BY RANDOM() LIMIT "
				+ LotteryInfo.aEmpQuantity;
		String sqlB = "select emp_no,emp_name,emp_first_department,emp_secondary_department,emp_three_department from employee where emp_type = 'B' and prize_name is null ORDER BY RANDOM() LIMIT "
				+ LotteryInfo.bEmpQuantity;
		String sqlC = "select emp_no,emp_name,emp_first_department,emp_secondary_department,emp_three_department from employee where emp_type = 'C' and prize_name is null ORDER BY RANDOM() LIMIT "
				+ LotteryInfo.cEmpQuantity;
		String sqlD = "select emp_no,emp_name,emp_first_department,emp_secondary_department,emp_three_department from employee where emp_type = 'D' and prize_name is null ORDER BY RANDOM() LIMIT "
				+ LotteryInfo.dEmpQuantity;
		LotteryInfo.aempList = queryData(sqlA);
		LotteryInfo.bempList = queryData(sqlB);
		LotteryInfo.cempList = queryData(sqlC);
		LotteryInfo.dempList = queryData(sqlD);
		empInfoList.addAll(LotteryInfo.aempList);
		empInfoList.addAll(LotteryInfo.bempList);
		empInfoList.addAll(LotteryInfo.cempList);
		empInfoList.addAll(LotteryInfo.dempList);
		return empInfoList;
	}
	
	/**
	 * 加码奖
	 * @return
	 */
	public List<Map<String, Object>> getRandomExtra() {
		String sql = "select emp_no,emp_name,emp_first_department,emp_secondary_department,emp_three_department from employee where emp_type = 'A' and prize_name is null ORDER BY RANDOM() LIMIT "
				+ LotteryInfo.lotteryQuantity;
		List<Map<String, Object>> empInfoList = queryData(sql);
		return empInfoList;
	}

	public int queryQuantityByType(String prizeType) {
		String sql = "select sum(prize_quantity) quantity from prize where prize_type = '" + prizeType + "'";
		List<Map<String, Object>> list = queryData(sql);
		return (int) list.get(0).get("quantity");
	}

	/**
	 * 更新中奖员工信息
	 */
	public void updateLuckyEmp() {
		String[] sqls = new String[LotteryInfo.showPeopleList.size()];
		for (int i = 0, j = LotteryInfo.showPeopleList.size(); i < j; i++) {
			sqls[i] = "update employee set prize_name = '" + LotteryInfo.showPeopleList.get(i).get("prize_name")
					+ "',prize_type = '" + LotteryInfo.showPeopleList.get(i).get("prize_type") + "' where emp_no ='"
					+ LotteryInfo.showPeopleList.get(i).get("emp_no") + "';";
		}
		Statement ste = null;
		try {
			getConnection();
			con.setAutoCommit(false);
			ste = con.createStatement();
			for (String sql : sqls) {
				if (sql != null) {
					ste.addBatch(sql);
				}
			}
			ste.executeBatch();
			ste.close();
			con.commit();// 提交
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();// 回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (ste != null) {
				try {
					ste.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 更新被抽取奖品信息
	 */
	public void updateLuckyPrize() {
		String[] sqls = new String[LotteryInfo.aPrizeInfoList.size()];
		for (int i = 0, j = LotteryInfo.aPrizeInfoList.size(); i < j; i++) {
			sqls[i] = "update prize set prize_quantity = 0,prize_aemp = 0,prize_bemp = 0,prize_cemp = 0,prize_demp = 0 where prize_name = '"
					+ LotteryInfo.aPrizeInfoList.get(i).get("prize_name") + "';";
		}
		Statement ste = null;
		try {
			getConnection();
			con.setAutoCommit(false);
			ste = con.createStatement();
			for (String sql : sqls) {
				if (sql != null) {
					ste.addBatch(sql);
				}
			}
			ste.executeBatch();
			ste.close();
			con.commit();// 提交
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();// 回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (ste != null) {
				try {
					ste.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 根据奖项奖品查询中奖员工信息
	 * 
	 * @param prizeType
	 * @param prizeName
	 * @return
	 */
	public List<Map<String, Object>> queryLuckyEmpInfo(String prizeType, String prizeName) {
		String sql = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (prizeName.equals("") || prizeName == null) {
			sql = "select emp_no,emp_name,emp_first_department,emp_secondary_department,emp_three_department, prize_name from employee where prize_type = '"
					+ prizeType + "' order by prize_name";
			list = queryData(sql);
		} else {
			sql = "select emp_no,emp_name,emp_first_department,emp_secondary_department,emp_three_department,prize_name from employee where prize_name = '"
					+ prizeName + "' order by prize_name";
			list = queryData(sql);
		}
		return list;
	}

	/**
	 * 查询语句
	 *
	 * @param sql
	 *            查询sql语句
	 * @return
	 */
	public List<Map<String, Object>> queryData(String sql) {
		List<Map<String, Object>> al = new ArrayList<Map<String, Object>>();
		try {
			getConnection();// 获得连接对象
			pstmt = con.prepareStatement(sql);// 获得预设语句对象
			// 执行查询
			ResultSet rs = pstmt.executeQuery();
			// 获得结果集元数据（元数据就是描述数据的数据，比如把表的列类型列名等作为数据）
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获得列的总数
			int columnCount = rsmd.getColumnCount();
			// 遍历结果集
			while (rs.next()) {
				Map<String, Object> hm = new HashMap<String, Object>();
				for (int i = 0; i < columnCount; i++) {
					// 根据列索引取得每一列的列名,索引从1开始
					String columnName = rsmd.getColumnName(i + 1);
					// 根据列名获得列值
					Object columnValue = rs.getObject(columnName);
					// 将列名作为key，列值作为值，放入 hm中，每个 hm相当于一条记录
					hm.put(columnName, columnValue);
				}
				// 将每个 hm添加到al中, al相当于是整个表，每个 hm是里面的一条记录
				al.add(hm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DialogUtil.showDialog(null, Constant.SQL_ERROR_DIALOG_MESSAGE, Constant.ERROR_MESSAGE_DIALOG_TYPE);
		} finally {
			closeConnection(rs, pstmt, con);
		}
		return al;
	}

	/**
	 * 更新数据
	 *
	 * @param sql
	 *            更新sql语句
	 * @return
	 */
	public void updateData(String sql) {
		try {
			getConnection();// 获得连接对象
			pstmt = con.prepareStatement(sql);// 获得预设语句对象
			pstmt.executeUpdate(); // 执行更新
		} catch (SQLException e) {
			e.printStackTrace();
			DialogUtil.showDialog(null, Constant.SQL_ERROR_DIALOG_MESSAGE, Constant.ERROR_MESSAGE_DIALOG_TYPE);
		} finally {
			closeConnection(rs, pstmt, con);
		}
	}

	/**
	 * 获取数据库连接
	 *
	 * @return
	 */
	public Connection getConnection() {
		driver = Constant.SQLITE_DRIVER;
		url = Constant.SQLITE_URL;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url);
		} catch (ClassNotFoundException | SQLException e) {
			DialogUtil.showDialog(null, Constant.SQL_ERROR_DIALOG_MESSAGE, Constant.ERROR_MESSAGE_DIALOG_TYPE);
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 关闭数据库连接
	 *
	 * @throws SQLException
	 */

	public void closeConnection(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			DialogUtil.showDialog(null, Constant.SQL_ERROR_DIALOG_MESSAGE, Constant.ERROR_MESSAGE_DIALOG_TYPE);
			e.printStackTrace();
		}
	}
}
