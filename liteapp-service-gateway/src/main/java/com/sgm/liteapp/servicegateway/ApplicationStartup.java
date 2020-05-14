package com.sgm.liteapp.servicegateway;

import java.io.IOException;
import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Configuration
@ComponentScan(basePackages = { "com.sgm.liteapp" })
public class ApplicationStartup {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
		SpringApplication.run(ApplicationStartup.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		RestTemplate rest = restTemplateBuilder.setConnectTimeout(15000).setReadTimeout(15000).build();
		// 修改默认的异常处理器，否则当返回非200时走不到ResponseEntity就抛异常了
		rest.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {

			}
		});
		return rest;
	}

	@Bean
	@LoadBalanced
	public LoadBalancedRestTemplate loadBalancedRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		LoadBalancedRestTemplate rest = restTemplateBuilder.setConnectTimeout(15000).setReadTimeout(15000)
				.build(LoadBalancedRestTemplate.class);
		// 修改默认的异常处理器，否则当返回非200时走不到ResponseEntity就抛异常了
		rest.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {

			}
		});
		return rest;
	}
}
