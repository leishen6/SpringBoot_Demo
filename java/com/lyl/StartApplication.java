package com.lyl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Title: Application
 * @Description:  springboot 的启动类
 * @date: 2019年8月23日 下午4:38:42
 */
@SpringBootApplication // 启动类注解，是一个复合注解
@MapperScan("com.lyl.dao") // 扫描MapperDao的
public class StartApplication {

	public static void main(String[] args) {
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(StartApplication.class, args);
		System.out.println("程序启动成功......");
	}
	
}
