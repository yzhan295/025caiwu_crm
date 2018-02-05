package com.ifinance.controller.user;

import com.ifinance.base.BaseController;

public class UserController extends BaseController {
	
	/**
	 * 登录接口
	 */
	public void login()
	{
		String respone = UserService.service.login(this);
		renderJson(respone);
	}
	
	/**
	 * 列出我的所有下属
	 */
	public void listSubUsers() {
		String respone = UserService.service.listSubUsers(this);
		renderJson(respone);
	}
	
	/**
	 * 首页统计数据
	 */
	public void statistics() {
		String respone = UserService.service.statistics(this);
		renderJson(respone);
	}
	
	/**
	 *  退出接口
	 */
	public void quit()
	{
		String respone = UserService.service.quit(this);
		renderJson(respone);
	}
}