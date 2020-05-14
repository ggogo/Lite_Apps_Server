package com.sgm.liteapp.commons;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.sgm.liteapp.commons.utils.CacheUtils;

@Configuration
@PropertySource(value = "classpath:liteapp-commons-config.properties")
public class CommonsConfigurationLoader {

	@Bean
	public CacheUtils initCacheUtil() {
		return new CacheUtils();
	}
}
