package com.hsh.service;

import java.util.List;

import com.hsh.model.HSCUser;
import com.hsh.model.User;

public interface UserService {

    HSCUser  getUserById(int userId);
	
	int saveOrUpdate(HSCUser user);
	
	int userRegister(String mobile,String pwd);
	
	HSCUser  getUserById(String mobile);
	
	List<HSCUser> getUsersRecommend(int userId);
	
	int  getTotalUser();

    User getUserByUsername(String username);
}
