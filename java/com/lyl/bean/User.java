package com.lyl.bean;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * @Title: User
 * @Description:
 * @date: 2019年8月23日 下午4:21:11
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 编号 */
	private int id;
	/** 姓名 */
	private String name;
	/** 年龄 */
	private int age;

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
