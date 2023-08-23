package com.unbiz.api.comm.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.Filter;
import java.util.Arrays;

/**
 * packageName    : com.unbiz.coda.comm.config
 * fileName       : WebMvcConfig
 * author         : UNBIZ
 * date           : 2022-09-16
 * description    : Web Mvc 설정
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-16        UNBIZ              최초 생성
 */
@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer  {

	@Bean
	public FilterRegistrationBean filterBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean((Filter) new InitFilter());
		registrationBean.setOrder(Integer.MIN_VALUE); //필터 여러개 적용 시 순번
		registrationBean.addUrlPatterns("/*"); //전체 URL 포함
		return registrationBean;
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/swagger-ui.html");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new Interceptor())
				.order(1)
				.addPathPatterns(Arrays.asList("/adm/**.do","/adm/sub/**.do"))
				.excludePathPatterns(Arrays.asList("/adm/sub/login.do","/adm/sub/logout.do"));
	}
}
