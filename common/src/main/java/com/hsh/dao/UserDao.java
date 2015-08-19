package com.hsh.dao;

import java.util.List;

import com.hsh.model.HSCUser;


public interface UserDao {

	HSCUser  getUserById(int userId);
	
	int saveOrUpdate(HSCUser user);
	
	HSCUser  getUserById(String mobile);
	
	List<HSCUser> getUsersRecommend(int userId);

	int getTotalUser();
	
}
