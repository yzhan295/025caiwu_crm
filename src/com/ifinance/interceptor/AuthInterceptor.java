package com.ifinance.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.ifinance.base.BaseController;
import com.ifinance.model.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 接口安全性检查
 * @author 刘兴超
 */
public class AuthInterceptor implements Interceptor {



	public void intercept(Invocation invoc) {
		BaseController contro = (BaseController) invoc.getController();
		HttpServletRequest request = contro.getRequest();
		String uri = invoc.getActionKey(); // 默认就是ActionKey
		User user = (User)contro.getSessionAttr("user");
		if(null == user && !uri.contains("login"))
		{
			if (isAjax(request)) {
				JSONObject json = new JSONObject();
				json.put("result", 0);
				json.put("data", "请登录系统!");
				invoc.getController().renderJson(json.toJSONString());
				return;
			} else {
				contro.redirect("/loginPage");
				return;
			}
		}

		try {
			invoc.invoke();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	public boolean isAjax(HttpServletRequest request) {
		return request.getHeader("X-Requested-With")!=null&&request.getHeader("X-Requested-With").equals("XMLHttpRequest");
	}
}
