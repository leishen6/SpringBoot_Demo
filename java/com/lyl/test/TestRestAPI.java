package com.lyl.test;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyl.bean.User;
import com.lyl.utils.RequestParameter;


/**
 *@Title: TestRestAPI 
 * @Description: 
 * @date: 2019年8月23日 下午8:27:34
 */
public class TestRestAPI {
	
	@Test
	public  void findAll(){
		RestTemplate restTemplate = new RestTemplate();

		String url = "http://localhost:8083/api/user/findUserAll";
		
		//无参数的查询使用 getForObject()
		String result = restTemplate.getForObject(url, String.class);

		JSONObject resultJsonObject = JSON.parseObject(result);
		System.out.println(resultJsonObject.getString("date"));
	}
	
	@Test
	public  void addUser(){
		RestTemplate restTemplate = new RestTemplate();

		String url = "http://localhost:8083/api/user/addUser";
		
		//请求参数
		RequestParameter<User> re = new RequestParameter<>();
		User user = new User();
		user.setAge(36);
		user.setName("诫子书巅峰");
		re.setData(user);
		
		String result = restTemplate.postForObject(url, re, String.class);
		
		JSONObject resultJsonObject = JSON.parseObject(result);
		System.out.println(resultJsonObject.getString("date"));
	}
	
	@Test
	public  void deleteUserByid(){
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "http://localhost:8083/api/user/deleteUserById";
		//请求参数
		RequestParameter<Integer> re = new RequestParameter<>();
		int id = 16;
		re.setData(id);
		
        String result = restTemplate.postForObject(url, re, String.class);
		
		JSONObject resultJsonObject = JSON.parseObject(result);
		System.out.println(resultJsonObject.getString("date"));
	}
	
	@Test
	public  void updateUser(){
		RestTemplate restTemplate = new RestTemplate();

		String url = "http://localhost:8083/api/user/updateUser";
		
		//请求参数
		RequestParameter<User> re = new RequestParameter<>();
		User user = new User();
		user.setId(23);
		user.setAge(36);
		user.setName("分即可");
		re.setData(user);
		
		String result = restTemplate.postForObject(url, re, String.class);
		
		JSONObject resultJsonObject = JSON.parseObject(result);
		System.out.println(resultJsonObject.getString("date"));
	}
	
	
	@Test
	public  void findUserByName(){
		RestTemplate restTemplate = new RestTemplate();

		String url = "http://localhost:8083/api/user/findUserByName";
		
		//请求参数
		RequestParameter<String> re = new RequestParameter<>();
		re.setData("图色");
		
		String result = restTemplate.postForObject(url, re, String.class);
		
		JSONObject resultJsonObject = JSON.parseObject(result);
		System.out.println(resultJsonObject.getString("date"));
	}

}
