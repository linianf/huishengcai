package com.hsh.dao;

import java.util.List;

import com.hsh.model.BroadCast;

public interface BroadCastDao {

	int   saveOrUpdate(BroadCast broadcast);
	
	void  broadcastInUse(int broadcastId);
	
	void  broadcastNotInUse(int broadcastId);
	
	List<BroadCast> getIndexBroadCast();
	
	List<BroadCast> getStartBroadCast();
}
