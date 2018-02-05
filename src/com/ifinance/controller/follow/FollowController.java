package com.ifinance.controller.follow;

import com.ifinance.base.BaseController;
import com.ifinance.model.Customer;
import com.ifinance.model.Follow;

public class FollowController extends BaseController {
	
	/**
	 * 客户跟单列表
	 */
	public void listCustomer() {
		String respone = FollowService.service.listCustomers(this);
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
	public void updateCustomer() {
		String respone = FollowService.service.updateCustomer(this);
		renderJson(respone);
	}
	
	/**
	 * 新建跟单详细
	 */
	public void createFollowDetail() {
		String respone = FollowService.service.createFollowDetail(this);
		renderJson(respone);
	}
	
	/**
	 * 客户跟单详细列表
	 */
	public void getHistoryList() {
		String respone = FollowService.service.getFollowHistoryList(this);
		renderJson(respone);
	} 
}