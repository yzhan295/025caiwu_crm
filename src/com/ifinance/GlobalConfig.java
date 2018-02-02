package com.ifinance;

import com.ifinance.controller.IndexController;
import com.ifinance.controller.follow.FollowController;
import com.ifinance.controller.user.UserController;
import com.ifinance.interceptor.AuthInterceptor;
import com.ifinance.model._MappingKit;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.template.Engine;

public class GlobalConfig extends JFinalConfig {
	public void configConstant(Constants me) {
		PropKit.use("config_loc.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		// tomcat编码方式
		me.setEncoding("utf-8");

	}

	public void configRoute(Routes me) {
		me.add("/", IndexController.class);
		me.add("/follow", FollowController.class);
		me.add("/user", UserController.class);
	}

	public void configEngine(Engine me) {
	}

	public void configPlugin(Plugins me) {
		// 主数据库配置
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"),
				PropKit.get("password").trim());
		me.add(c3p0Plugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		// 所有配置在 MappingKit 中搞定
		_MappingKit.mapping(arp);
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		me.add(new AuthInterceptor());
		me.add(new SessionInViewInterceptor());
	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("contextPath"));
	}

	/**
	 * 系统启动完成后执行
	 */
	public void afterJFinalStart() {
		System.out.println("############The server CRM start [SUCCESS]#################");
	}

	/**
	 * 系统关闭前调用
	 */
	public void beforeJFinalStop() {
	}

	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目 运行此 main
	 * 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 8081, "/crm", 5);
	}

}
