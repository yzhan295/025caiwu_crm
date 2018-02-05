package com.ifinance.controller.follow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ifinance.base.BaseController;
import com.ifinance.base.BaseService;
import com.ifinance.model.Customer;
import com.ifinance.model.Follow;
import com.ifinance.model.User;
import com.ifinance.tool.DateUtil;
import com.jfinal.plugin.activerecord.Page;

public class FollowService extends BaseService {

	public static final FollowService service = new FollowService();
	
	/**
	 * 获取历史跟单记录
	 * @param bc
	 * @return
	 */
	public String getFollowHistoryList(BaseController bc) {
		int customerId = bc.getParaToInt("customerId", -1);
		JSONObject object = new JSONObject();
		
		List<Follow> followList = Customer.dao.getFollowHistoryList(customerId);
		
		JSONArray array = new JSONArray();
		for (int i = 0; i < followList.size(); i++) {
			Follow follow = followList.get(i);
			JSONObject objectItem = new JSONObject();
			objectItem.put("username", User.dao.findById(follow.getUserId()).getName());
			objectItem.put("followDesc", follow.getFollowDesc());
			objectItem.put("followTime",  DateUtil.dateToString(follow.getFollowTime(), DateUtil.FORMAT_YYMMDDHHMMSS));
			
			array.add(objectItem);
		}
		object.put("item", array);
		
		return respJsonSuccess(object);
	}
	
	/**
	 * 获取客户列表
	 * @param bc
	 * @return
	 */
	public String listCustomers(BaseController bc) {
		User user = (User)bc.getSession().getAttribute("user");
		List<User> allUsersIncludeMyself = new ArrayList<User>();
		// 添加当前用户自身
		allUsersIncludeMyself.add(user);
		// 添加我的下属员工
		listSubUsersById(user.getId(), allUsersIncludeMyself);
		
		int pageNumber = bc.getParaToInt("page", 1);
		int type = bc.getParaToInt("type", -1);
		
		Page<Customer> page = Customer.dao.paginate(allUsersIncludeMyself, pageNumber, type);
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
			objectItem.put("name", customer.getName());
			objectItem.put("mobile", customer.getMobile());
			objectItem.put("source", customer.getSource());
			objectItem.put("phonestate", customer.getPhoneState());
			
			objectItem.put("wechatState", customer.getWechatState());
			objectItem.put("smsState", customer.getSmsState());
			objectItem.put("customerType", customer.getCustomerType());
			objectItem.put("userId", customer.getUserId());
			objectItem.put("updateTime", DateUtil.dateToString(customer.getUpdateTime(), DateUtil.FORMAT_YYMMDDHHMMSS));
			objectItem.put("createTime", DateUtil.dateToString(customer.getCreateTime(), DateUtil.FORMAT_YYMMDDHHMMSS));
			Follow followDetail = Customer.dao.getLastOneFollow(customer.getId());
			
			objectItem.put("followsaler", User.dao.findById(customer.getUserId()).getName());
			objectItem.put("followcount", Follow.dao.getFollowCount(customer.getId()));
			if (null != followDetail) {
				
				objectItem.put("followtime", DateUtil.dateToString(followDetail.getFollowTime(), DateUtil.FORMAT_YYMMDDHHMMSS));
				objectItem.put("followdesc", followDetail.getFollowDesc());
			}
			
			array.add(objectItem);
		}
		object.put("item", array);

		return respJsonSuccess(object);
	}
	
	/**
	 * 更新客户表
	 * @param bc
	 * @return
	 */
	public String updateCustomer(BaseController bc) {
		int customerId = bc.getParaToInt("customerId", -1);
		int source = bc.getParaToInt("source", -1);
		int phonestate = bc.getParaToInt("phone", -1);
		int wechatState = bc.getParaToInt("wechat", -1);
		int smsState = bc.getParaToInt("sms", -1);
		int customerType = bc.getParaToInt("customerType", -1);
		
		Customer customer = Customer.dao.findById(customerId);
		if(source != -1) {
			customer.setSource(source);
		}
		
		if(phonestate != -1) {
			customer.setPhoneState(phonestate);
		}
		
		if(wechatState != -1) {
			customer.setWechatState(wechatState);
		}
		
		if(smsState != -1) {
			customer.setSmsState(smsState);
		}
		
		if(customerType != -1) {
			customer.setCustomerType(customerType);
		}
		
		customer.update();
		
		return respJsonSuccess("更新成功！");
	}	
	
	/**
	 * 新建跟单详细
	 * @param bc
	 * @return
	 */
	public String createFollowDetail(BaseController bc) {
		User user = (User)bc.getSession().getAttribute("user");
		int customerId = bc.getParaToInt("customerId", -1);
		String followDesc = bc.getPara("followDesc", "");
		
		Follow follow = new Follow();
		follow.setUserId(user.getId());
		follow.setCustomerId(customerId);
		follow.setFollowDesc(followDesc);
		follow.setFollowTime(new Date());
		
		follow.save();
		
		return respJsonSuccess("保存成功！");
	}
	
}