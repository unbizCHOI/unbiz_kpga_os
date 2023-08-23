package com.unbiz.api.comm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * packageName    : com.unbiz.coda.comm.config
 * fileName       : SwaggerConfig
 * author         : UNBIZ
 * date           : 2022-09-16
 * description    : Swagger 설정
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-16        UNBIZ              최초 생성
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("unbiz kpga os ")
				.description("유엔비즈 KPGA 운영 관리 시스템")
				.version("0.0.1")
				.build();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(this.apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.unbiz.api"))
				.paths(PathSelectors.any())
				.build()
				.useDefaultResponseMessages(false);
	}

}
