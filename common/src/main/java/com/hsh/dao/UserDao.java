package com.hsh.dao;

import java.util.List;

import com.hsh.model.HSCUser;
import com.hsh.model.User;


public interface UserDao {

	HSCUser  getUserById(int userId);
	
	int saveOrUpdate(HSCUser user);
	
	HSCUser  getUserById(String mobile);
	
	List<HSCUser> getUsersRecommend(int userId);

	int getTotalUser();

	/**
	 * 根据用户名查询后台用户
	 * @param username
	 * @return
	 */
    User getUserByUsername(String username);
	
}
