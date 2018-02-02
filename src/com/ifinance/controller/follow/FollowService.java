package com.ifinance.controller.follow;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ifinance.base.BaseController;
import com.ifinance.base.BaseService;
import com.ifinance.model.Customer;
import com.ifinance.tool.DateUtil;
import com.jfinal.plugin.activerecord.Page;

public class FollowService extends BaseService {

	public static final FollowService service = new FollowService();
	

	public String list(BaseController bc) {

		Page<Customer> page = Customer.dao.paginate(1, 10);
		List<Customer> list = page.getList();
		JSONObject object = new JSONObject();
		object.put("pageNumber", page.getPageNumber());
		object.put("pageSize", page.getPageSize());
		object.put("totalPage", page.getTotalPage());
		object.put("totalRow", page.getTotalRow());
		
		JSONArray array = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			JSONObject objectItem = new JSONObject();
			Customer customer = list.get(i);
			objectItem.put("id", customer.getId());
			objectItem.put("mobile", customer.getMobile());
			objectItem.put("source", customer.getSource());
			objectItem.put("wechatState", customer.getWechatState());
			objectItem.put("smsState", customer.getSmsState());
			objectItem.put("customerType", customer.getCustomerType());
			objectItem.put("userId", customer.getUserId());
			objectItem.put("updateTime", DateUtil.dateToString(customer.getUpdateTime(), DateUtil.FORMAT_YYMMDDHHMMSS));
			objectItem.put("createTime", DateUtil.dateToString(customer.getCreateTime(), DateUtil.FORMAT_YYMMDDHHMMSS));
			objectItem.put("follow", Customer.dao.getFollow(customer.getId()));
			array.add(objectItem);
		}
		object.put("item", array);

		return respJsonSuccess(array);
	}
	
}