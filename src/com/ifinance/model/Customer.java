package com.ifinance.model;

import java.util.List;

import com.ifinance.model.base.BaseCustomer;
import com.jfinal.plugin.activerecord.Page;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Customer extends BaseCustomer<Customer> {
	public static final Customer dao = new Customer().dao();
	
	public Follow getLastOneFollow(int customerId) {
	       return Follow.dao.findFirst("select * from follow where customer_id = " + customerId + " order by follow_time desc limit 0, 1");
	}
	
	public List<Follow> getFollowHistoryList(int customerId) {
	       return Follow.dao.find("select * from follow where customer_id = " + customerId + " order by follow_time desc");
	}
	
	public Page<Customer> paginate(List<User> allUsers, int pageNumber, int type) {
		StringBuffer sqlInCondition = new StringBuffer();
		for(int i=0; i<allUsers.size(); i++) {
			sqlInCondition.append(allUsers.get(i).getId());
			// 最后一条数据不需要加","
			if (i < (allUsers.size() - 1)) {
				sqlInCondition.append(",");
			}
		}
		
		String sqlType = "";
		if (type != -1) {
			sqlType = " customer_type = " + type + " and ";
		}
		
		return paginate(pageNumber, 10, "select *", "from customer where " + sqlType + "user_id in ("+sqlInCondition.toString()+") order by update_time");
	}
}
