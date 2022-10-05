package com.goodee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Spring MVC프로젝트에 관련된 설정을 하는 클래스
@Configuration
// Controller 어노테이션이 세팅되어 있는 클래스를 등록하는 어노테이션
@EnableWebMvc
// 스캔할 패키지 지정
@ComponentScan("com.goodee.controller")
public class ServletAppContext implements WebMvcConfigurer{
	
	// Controller의 메서드가 반환하는 jsp의 이름 앞뒤에 경로와 확장자를 붙여주도록 설정한다.
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	// 정적 파일의 경로 세팅
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/resource/**").addResourceLocations("/resources/");  //내부경로 참조하고 싶을때
		registry.addResourceHandler("/upload/**").addResourceLocations("file:///D:/sample/"); //외부파일경로 참조하고 싶을 때
	}
	
	
	// 파일 업로드 세팅
	private final int MAX_SIZE = 10 * 1024 * 1024;
	
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		
		// 디폴트 인코딩 타입 설정
		multipartResolver.setDefaultEncoding("UTF-8");
		// 전체 올릴 수 있는 파일들의 총 용량 최대치
		multipartResolver.setMaxUploadSize(MAX_SIZE*10);
		// 파일 한개 당 올릴 수 있는 용량 최대치
		multipartResolver.setMaxUploadSizePerFile(MAX_SIZE);
		
		return multipartResolver;
	}

}








