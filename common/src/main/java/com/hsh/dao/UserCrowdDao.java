package com.hsh.dao;

import java.util.List;

import com.hsh.model.QuanGetRecord;
import com.hsh.model.UserCrowd;


public interface UserCrowdDao {

	int saveOrUpdate(UserCrowd userCrowd);
	
	long saveOrUpdate(QuanGetRecord quanGetRecord);
	
	List<UserCrowd> getInCrowdList(int userId);
	
	List<UserCrowd> getEndCrowdList(int userId,int pageSize,int lastMinUserCrowdId);
	
	List<UserCrowd>  getUserCrowdByCrowdId(int crowdId, int pageSize, int lastMinUserCrowdId);

	List<UserCrowd> getInCrowdList(int pageSize, int lastMinUserCrowdId);
}
