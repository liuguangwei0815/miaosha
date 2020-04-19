package com.im.miaosha.datasource;
/**
 * druid 配置.
*/

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfiguration {

	/*
	 * 注册过滤器
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<WebStatFilter> setDruidFilter() {
		FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setFilter(new WebStatFilter());
		Map<String, String> initParameters = new HashMap<>();
		initParameters.put("exclusions", "*.js,*.html,*.gif,*.jpg,*.png,*.css,*.ico,*.jsp,/druid/*,/download/*");
		filterRegistrationBean.setInitParameters(initParameters);
		return filterRegistrationBean;
	}

	/**
	 * 注册一个StatViewServlet
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<StatViewServlet> setDruidServlet() {
		ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>();
		servletRegistrationBean.setServlet(new StatViewServlet());
		servletRegistrationBean.addUrlMappings("/druid/*");

		Map<String, String> initParameters = new HashMap<>();
		initParameters.put("resetEnable", "false"); // 禁用HTML页面上的“Rest All”功能
		initParameters.put("allow", ""); // ip白名单（没有配置或者为空，则允许所有访问）
		initParameters.put("loginUsername", "maxwell"); // ++监控页面登录用户名
		initParameters.put("loginPassword", "pkusoft"); // ++监控页面登录用户密码
		initParameters.put("deny", ""); // ip黑名单
		// 如果某个ip同时存在，deny优先于allow
		servletRegistrationBean.setInitParameters(initParameters);
		return servletRegistrationBean;
	}
}
