package com.lyl;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

/**
 *@Title: PageHelperConfig 
 * @Description:      使用PageHelper分页插件的一个配置类，除了使用配置类外，还可以直接在配置文件中进行配置。
 * @date: 2019年8月24日 下午1:18:01
 */
@Configuration
public class PageHelperConfig {
	
	@Bean
    public PageHelper pageHelper() {
        System.out.println("MyBatisConfiguration.pageHelper()分页插件成功注册到容器中......");
        
        PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		// 配置mysql数据库的方言
		properties.setProperty("dialect", "mysql");
		pageHelper.setProperties(properties);
		return pageHelper;
    }

}
