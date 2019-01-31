package com.topspin.boot;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.topspin.boot.controller"))
				.paths(regex("/*.*"))
				.build()
				.apiInfo(metaInfo());
				
	}
	
	private ApiInfo metaInfo() {
		
		ApiInfo apiInfo = new ApiInfo(
			"Topspin API Rest",
			"API Rest do aplicativo Topspin",
			"1.0",
			"Terms of Service",
			"Topspin",
			"Apache License Service 2.0",
			"https://www.apache.org/licesen.html"
		);
		
		return apiInfo;
	}

}
