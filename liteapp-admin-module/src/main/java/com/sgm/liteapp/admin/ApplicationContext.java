package com.sgm.liteapp.admin;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.sgm.liteapp.commons.cms.CMSService;
import com.sgm.liteapp.commons.cms.CMSServiceImpl;
import com.sgm.liteapp.commons.csapi.CSAPIService;
import com.sgm.liteapp.commons.csapi.CSAPIServiceImpl;
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
	public RestTemplate restTemplate() {
		RestTemplate rest = new RestTemplate();
		// 修改默认的异常处理器，否则当返回非200时走不到ResponseEntity就抛异常了
		rest.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {

			}
		});
		// 返回内容使用UTF-8编码，避免ResponseEntity.getBody内容乱码
		rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		return rest;
	}

	@Bean
	public CMSService cmsService() {
		return new CMSServiceImpl();
	}

	@Bean
	public CSAPIService csapiService() {
		return new CSAPIServiceImpl();
	}

}
