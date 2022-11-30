package com.kurly;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

    	/*
    	 * html에서 이미지 불러올 때
    	 * 상품의 이미지 파일은 로컬 저장소 C:/Repository/images에 넣고 불러올 것.
    	 **** 예시="/images/sample.png"
    	 * 용량이 적은 아이콘류의 이미지 파일은 프로젝트 내부 src/main/resources/static/icons에 넣고 불러올 것: 
    	 **** 예시="/icons/cart.png"
    	 * */
    	registry.addResourceHandler("/images/**")
				.addResourceLocations("file:///c:/Repository/images/");
    	registry.addResourceHandler("/**")
    			.addResourceLocations("classpath:/static/");
    }

}
