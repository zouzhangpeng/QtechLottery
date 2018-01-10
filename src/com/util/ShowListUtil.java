package com.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.LotteryInfo;
/**
 * 把不同类型的奖品分给不同类型的员工
 * @author zhangpeng.zhou
 *
 */
public class ShowListUtil {

	public void showListUtil(List<Map<String, Object>> list1, List<Map<String, Object>> list2, String type) {
		int count = 0;
		for (int m = 0, n = list1.size(); m < n; m++) {
			int j = (int) list1.get(m).get(type);
			if (j != 0) {
				for (int i = 0; i < j; i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("emp_no", list2.get(count).get("emp_no"));
					map.put("emp_name", list2.get(count).get("emp_name"));
					map.put("emp_first_department", list2.get(count).get("emp_first_department"));
					map.put("emp_secondary_department", list2.get(count).get("emp_secondary_department"));
					map.put("emp_three_department", list2.get(count).get("emp_three_department"));
					map.put("prize_type", list1.get(m).get("prize_type"));
					map.put("prize_name", list1.get(m).get("prize_name"));
					LotteryInfo.showPeopleList.add(map);
					count++;
				}
			}
		}
	}
}
