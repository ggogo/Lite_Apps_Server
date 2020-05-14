package com.sgm.liteapp.cloudapi;

import java.util.TimeZone;

import org.apache.commons.lang.ArrayUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sgm.liteapp.cloudapi.constants.ApiDoc;
import com.sgm.liteapp.cloudapi.webapp.security.SecuredAccessInterceptor;

@SuppressWarnings("deprecation")
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.sgm.liteapp" })
@MapperScan("com.sgm.liteapp.cloudapi.dao.mapper")
public class ApplicationStartup extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
		SpringApplication.run(ApplicationStartup.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String[] whiteList = (String[]) ArrayUtils.addAll(ApiDoc.UI_PATHS, new String[] { "/healthz" });
		registry.addInterceptor(new SecuredAccessInterceptor()).addPathPatterns("/**").excludePathPatterns(whiteList);
	}

}
