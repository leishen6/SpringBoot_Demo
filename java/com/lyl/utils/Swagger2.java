package com.lyl.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Sets;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Title: Swagger2
 * @Description:  Swagger2配置类
 *              实现Swagger除了需要在controller中的方法使用@ApiOperation注解外，还需要定义一个Swagger2配置类
 * @date: 2019年8月23日 下午5:22:02
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).protocols(Sets.newHashSet("http")) // 协议，http或https
				.apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.lyl.controller")) // 一定要写对，会在这个路径下扫描controller定义
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("REST接口定义").version("1.0").description("用于测试RESTful API").build();
	}

}
