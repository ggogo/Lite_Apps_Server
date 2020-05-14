package com.sgm.liteapp.cloudapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.sgm.liteapp.commons.cms.CMSService;
import com.sgm.liteapp.commons.cms.CMSServiceImpl;
import com.sgm.liteapp.commons.utils.SpringContextUtils;

@Configuration
public class ApplicationContext {

	@Bean
	public SpringContextUtils initSpringContextUtil() {
		return new SpringContextUtils();
	}

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor page = new PaginationInterceptor();
		page.setDialectType("oracle");
		return page;
	}
	
	@Bean
	public CMSService initCMSService() {
		return new CMSServiceImpl();
	}

}
