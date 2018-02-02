package com.ifinance.controller;

import com.ifinance.base.BaseController;

public class IndexController extends BaseController {
	
	public void index() {
		render("index.html");
	}
	
	public void loginPage() {
		render("login.html");
	}
	
}