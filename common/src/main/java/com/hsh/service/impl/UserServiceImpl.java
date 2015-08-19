package com.hsh.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsh.dao.UserDao;
import com.hsh.model.HSCUser;
import com.hsh.service.UserService;

/**
 * 
 * @author linianf
 *
 */
@Service("userService")
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public HSCUser getUserById(int userId) {
		return userDao.getUserById(userId);
	}

	@Override
	@Transactional(readOnly=false)
	public int saveOrUpdate(HSCUser user) {
		return userDao.saveOrUpdate(user);
	}

	@Override
	public HSCUser getUserById(String mobile) {
		return userDao.getUserById(mobile);
	}

	@Override
	public List<HSCUser> getUsersRecommend(int userId) {
		return userDao.getUsersRecommend(userId);
	}

	@Override
	@Transactional(readOnly=false)
	public int userRegister(String mobile, String pwd) {
		HSCUser user = new HSCUser();
		user.setLoginPass(pwd);
		user.setMobile(mobile);
		user.setRegDate(new Date());
		user.setChannel(1);
		int userId = userDao.saveOrUpdate(user);
		user.setReferCode(String.valueOf(userId));
		return userId;
	}

	@Override
	public int  getTotalUser(){
		return userDao.getTotalUser();
	}
}
