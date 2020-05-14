package com.sgm.liteapp.admin;

import java.util.TimeZone;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@SpringBootApplication
@ComponentScan(basePackages = { "com.sgm.liteapp" })
@MapperScan("com.sgm.liteapp.admin.model.mapper")
public class ApplicationStartup extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
		SpringApplication.run(ApplicationStartup.class, args);
	}

}
