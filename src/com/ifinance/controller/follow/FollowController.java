package com.ifinance.controller.follow;

import com.ifinance.base.BaseController;
import com.ifinance.model.Customer;
import com.ifinance.model.Follow;

public class FollowController extends BaseController {
	
	/**
	 * 客户跟单列表
	 */
	public void listCustomer() {
		String respone = FollowService.service.list(this);
		renderJson(respone);
	} 
	
	/**
	 * 创建Customer
	 */
	public void createCustomer(Customer customer) {
		
	}
	
	/**
	 * 更新Customer
	 */
	public void updateCustomer(Customer customer) {
		
	}
	
	/**
	 * 更新跟单详细
	 */
	public void createFollowDetail(Follow follow) {
		
	}
	
	/**
	 * 客户跟单详细列表
	 */
	public void listFollowDetail() {
		String respone = FollowService.service.list(this);
		renderJson(respone);
	} 
}