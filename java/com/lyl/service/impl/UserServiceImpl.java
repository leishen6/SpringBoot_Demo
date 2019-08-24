package com.lyl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyl.bean.User;
import com.lyl.dao.UserDao;
import com.lyl.service.UserService;

/**
 * @Title: UserServiceImpl
 * @Description:
 * @date: 2019年8月23日 下午4:29:01
 */

@Service //表示此是服务层类 
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)//添加上事务
	public boolean addUser(User user) {
		boolean flag = false;
		try {
			userDao.addUser(user);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)//添加上事务
	public boolean updateUser(User user) {
		boolean flag = false;
		try {
			userDao.updateUser(user);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)//添加上事务
	public boolean deleteUser(int id) {
		boolean flag = false;
		try {
			userDao.deleteUser(id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	
	@Override
	public User findUserByName(String userName) {
		return userDao.findByName(userName);
	}

	
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

}
