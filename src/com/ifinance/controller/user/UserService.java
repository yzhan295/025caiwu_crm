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
		int role = user.getRole();
		List<User> subAllUsers = new ArrayList<User>();
		// 销售总监
		if (role == 1) {
			List<User> subLeaders = User.dao.getUsersByPid(id);
			subAllUsers.addAll(subLeaders);
			for(int i=0; i<subLeaders.size(); i++) {
				subAllUsers.addAll(User.dao.getUsersByPid(subLeaders.get(i).getId()));
			}
		// 销售主管	
		} else if (role == 2) {
			subAllUsers = User.dao.find("select * from user where p_id=" + id);
		}
		
		object.put("item", subAllUsers);
		
		return respJsonSuccess(object);
	}
	
	public String quit(BaseController bc)
	{
		bc.getSession().removeAttribute("user");
		JSONObject object = new JSONObject();
		return respJsonSuccess(object);
	}
	
}