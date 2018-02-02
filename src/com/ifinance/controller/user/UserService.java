package com.ifinance.controller.user;

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
	
	public String quit(BaseController bc)
	{
		bc.getSession().removeAttribute("user");
		JSONObject object = new JSONObject();
		return respJsonSuccess(object);
	}
	
}