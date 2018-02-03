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
				// allSubUsers.addAll(User.dao.getUsersByPid(subUsers.get(i).getId()));
				listSubUsersById(subUsers.get(i).getId(), allSubUsers);
			}
		}
	}
	
	/**
	 * 统计数据
	 */
	public void statistics() {
		// 今日意向客户
		
		// 本月意向客户
		
		// 意向客户总数
	}
	
	public String quit(BaseController bc)
	{
		bc.getSession().removeAttribute("user");
		JSONObject object = new JSONObject();
		return respJsonSuccess(object);
	}
	
}