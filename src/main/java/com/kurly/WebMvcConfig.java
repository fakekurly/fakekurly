package com.kurly;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//정적 자원 상대 경로 설정
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

    	registry.addResourceHandler("/profile/**")
				.addResourceLocations("file:///C:/Repository/profile/");
    	registry.addResourceHandler("/**")
    			.addResourceLocations("classpath:/static/");
    }

}
