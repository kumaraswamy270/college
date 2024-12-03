package com.student.detail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	// View resolver for JSPs
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/college/"); // The folder where JSPs are stored
		resolver.setSuffix(".jsp"); // The file extension for JSP files
		return resolver;
	}

	// Static resource mappings
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Serve static resources from /static directory in classpath
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
	}
}
