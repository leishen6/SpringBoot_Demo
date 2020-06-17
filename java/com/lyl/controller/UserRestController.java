package com.lyl.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyl.bean.User;
import com.lyl.service.UserService;
import com.lyl.utils.RedisTemplateUtils;
import com.lyl.utils.RequestParameter;
import com.lyl.utils.Response;
import com.lyl.utils.ResponseCode;
import io.swagger.annotations.ApiOperation;

/**
 * @Title: UserRestController
 * @Description:  在Controller中没有必要根据删除、修改等特意的去使用deleteMapping、putMapping等，
 *               因为这些都是需要有请求参数的，所以可以直接只使用postMapping即可
 * @date: 2019年8月23日 下午4:30:56
 */

@RestController
@RequestMapping(value = "/v1/api")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UserRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// redis模糊查询key时匹配的键
	private String prex = "User_";
	
	@Autowired
	RedisTemplateUtils redisU;

	@Autowired
	private UserService userService;
    
	
	// @ApiOperation这个注解是使用swagger生成rest接口的在线文档
	@ApiOperation(value="新增用户", notes="新增用户数据")
	@PostMapping("/user/addUser")
	public Response<Boolean> addUser(@RequestBody RequestParameter<User> requestParameter) {
		logger.info("request: "+ requestParameter.toString());
		logger.info("开始新增...");
		
		// 插入新数据，需要将redis中与 User 相关的缓存数据清空
		logger.info("清空redis中与 "+prex+"相关的缓存数据...");
		redisU.deleteByPrex(prex+"*");
		
		boolean flag = userService.addUser(requestParameter.getData());
		Response response = null;
		if (flag) {
			response = new Response(flag, ResponseCode.SUCCESS.code(), ResponseCode.SUCCESS.message());
		}
		else {
			response = new Response(flag, ResponseCode.ERROR.code(), ResponseCode.ERROR.message());
		}
		logger.info("response: "+response.toString());
		return response;
	}
    
	
	@ApiOperation(value="根据id更新用户", notes="更新用户数据")
	@PostMapping("/user/updateUser")
	public Response<Boolean> updateUser(@RequestBody RequestParameter<User> requestParameter) {
		logger.info("request: "+ requestParameter.toString());
		logger.info("开始更新...");
		
		// 插入新数据，需要将redis中与 User 相关的缓存数据清空
		logger.info("清空redis中与 "+prex+"相关的缓存数据...");
		redisU.deleteByPrex(prex+"*");
		
		boolean flag = userService.updateUser(requestParameter.getData());
		Response response = null;
		if (flag) {
			response = new Response(flag, ResponseCode.SUCCESS.code(), ResponseCode.SUCCESS.message());
		}
		else {
			response = new Response(flag, ResponseCode.ERROR.code(), ResponseCode.ERROR.message());
		}
		logger.info("response: "+response.toString());
		return response;
	}

	
	@ApiOperation(value="根据用户id删除用户", notes="根据用户id删除用户数据")
	@PostMapping("/user/deleteUserById")
	public Response<Boolean> delete(@RequestBody RequestParameter<Integer> requestParameter) {
		logger.info("request: "+ requestParameter.toString());
		logger.info("开始删除... " + requestParameter.getData());
		
		// 插入新数据，需要将redis中与 User 相关的缓存数据清空
		logger.info("清空redis中与 "+prex+"相关的缓存数据...");
		redisU.deleteByPrex(prex+"*");
		
		boolean flag = userService.deleteUser(requestParameter.getData());
		Response response = null;
		if (flag) {
			response = new Response(flag, ResponseCode.SUCCESS.code(), ResponseCode.SUCCESS.message());
		}
		else {
			response = new Response(flag, ResponseCode.ERROR.code(), ResponseCode.ERROR.message());
		}
		logger.info("response: "+response.toString());
		return response;
	}
	
	

	@ApiOperation(value="根据名字查询用户", notes="根据名字查询用户数据")
	@PostMapping("/user/findUserByName")
	public Response<User> findByUserId(@RequestBody RequestParameter<String> requestParameter) {
		logger.info("request: "+ requestParameter.toString());
		logger.info("开始根据名字查询数据...");
		
		User user;
		
		user = redisU.get("User_"+requestParameter.getData(), User.class);
		
		if (user == null) {
			user = userService.findUserByName(requestParameter.getData());
			
			logger.info("将数据放到redis缓存中......");
			redisU.set("User_"+requestParameter.getData(), user);
		}
		
		Response response = null;
		if (user != null) {
			response = new Response(user, ResponseCode.SUCCESS.code(), ResponseCode.SUCCESS.message());
		}
		else {
			response = new Response("", ResponseCode.ERROR.code(), ResponseCode.ERROR.message());
		}
		logger.info("response: "+response.toString());
		return response;
	}
	
	
	
	@ApiOperation(value="查询全部用户", notes="查询全部用户数据")
	@GetMapping("/user/findUserAll")
	public Response<List<User>> findAll() {
		logger.info("开始查询所有数据...");

		List<User> findAll;
		//redis缓存中查询
		findAll = redisU.get("User_findUserAll", List.class);
		
		if (!(findAll != null && findAll.size()>0)) {
			findAll = userService.findAll();
			
			logger.info("将数据放到redis缓存中......");
			redisU.set("User_findUserAll", findAll);
		}
		
		Response response = null;
		if (findAll.size() != 0) {
			response = new Response(findAll, ResponseCode.SUCCESS.code(), ResponseCode.SUCCESS.message());
		}
		else {
			response = new Response(findAll, ResponseCode.ERROR.code(), ResponseCode.ERROR.message());
		}
		logger.info("response: "+response.toString());
		return response;
	}
	
	
	
	
	@ApiOperation(value="分页查询全部用户", notes="分页查询全部用户数据")
	@PostMapping("/user/findUserAllByPage")
	public Response<PageInfo> findAllByPage(@RequestBody RequestParameter<User> requestParameter) {
		logger.info("request: "+ requestParameter.toString());
		logger.info("开始分页查询数据...");
		
		PageHelper.startPage(requestParameter.getData().getPageinfo().getPageNum(), requestParameter.getData().getPageinfo().getPageSize());
		List<User> findAll = userService.findAll();
		PageInfo<User> pageinfo = new PageInfo<>(findAll);
		
		Response response = null;
		if (findAll.size() != 0) {
			response = new Response(pageinfo, ResponseCode.SUCCESS.code(), ResponseCode.SUCCESS.message());
		}
		else {
			response = new Response(pageinfo, ResponseCode.ERROR.code(), ResponseCode.ERROR.message());
		}
		logger.info("response: "+response.toString());
		return response;
	}

}