package com.ifinance.controller.user;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ifinance.base.BaseController;
import com.ifinance.base.BaseService;
import com.ifinance.model.User;

public class UserService  extends BaseService {

	public static final UserService service = new UserService();

	public String login(BaseController bc) {
		
		String mobile = bc.getPara("mobile","");
		String password = bc.getPara("password","");
		
		if("".equals(mobile)||"".equals(password))
		{
			String json = respJsonError("登录参数不能为空！");
			return json;
		}
		
		User user = User.dao.getUserByMobile(mobile);
		if(null == user)
		{
			String json = respJsonError("账号不存在！");
			return json;
		}else
		{
			if(!password.equals(user.getPassword()))
			{
				String json = respJsonError("密码不正确!");
				return json;
			}
		}
		bc.getSession().setAttribute("user", user);
		
		JSONObject object = new JSONObject();
		object.put("user", user);
		
		return respJsonSuccess(object);
	}
	
	
	public String listSubUsers(BaseController bc) {
		User user = (User)bc.getSession().getAttribute("user");
		JSONObject object = new JSONObject();
		
		int id = user.getId();
		List<User> allSubUsers = new ArrayList<User>();
		
		listSubUsersById(id, allSubUsers);
		object.put("item", allSubUsers);
		
		return respJsonSuccess(object);
	}
	
	public void listSubUsersById(int id, List<User> allSubUsers) {
		List<User> subUsers = User.dao.getUsersByPid(id);
		
		if (null != subUsers) {
			allSubUsers.addAll(subUsers);
			for(int i=0; i<subUsers.size(); i++) {
				listSubUsersById(subUsers.get(i).getId(), allSubUsers);
			}
		}
	}
	
	/**
	 * 统计数据
	 */
	public String statistics(BaseController bc) {
		User user = (User)bc.getSession().getAttribute("user");
		List<User> allUsersIncludeMyself = new ArrayList<User>();
		// 添加当前用户自身
		allUsersIncludeMyself.add(user);
		// 添加我的下属员工
		listSubUsersById(user.getId(), allUsersIncludeMyself);
		
		// 今日意向客户
		int todayIntentCount = User.dao.getTodayIntentCount(allUsersIncludeMyself);
		
		// 本月意向客户
		int monthIntentCount = User.dao.getMonthIntentCount(allUsersIncludeMyself);
		
		// 意向客户总数
		int totalIntentCount = User.dao.getTotalIntentCount(allUsersIncludeMyself);
		
		// 本周销售业绩
		int weekSale = User.dao.getWeekSaleCount(allUsersIncludeMyself);
		
		// 本月销售业绩
		int monthSale = User.dao.getMonthSaleCount(allUsersIncludeMyself);
		
		JSONObject object = new JSONObject();
		object.put("today_intent", todayIntentCount);
		object.put("month_intent", monthIntentCount);
		object.put("total_intent", totalIntentCount);
		
		object.put("week_sale", weekSale);
		object.put("month_sale", monthSale);
		object.put("target_sale", 0);
		
		return respJsonSuccess(object);
	}
	
	public String quit(BaseController bc)
	{
		bc.getSession().removeAttribute("user");
		JSONObject object = new JSONObject();
		return respJsonSuccess(object);
	}
	
}