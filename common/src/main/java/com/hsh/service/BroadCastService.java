package com.hsh.service;

import java.util.List;
import com.hsh.model.BroadCast;

public interface BroadCastService {

    int   saveOrUpdate(BroadCast broadcast);
	
	void  broadcastInUse(int broadcastId);
	
	void  broadcastNotInUse(int broadcastId);
	
	List<BroadCast> getIndexBroadCast();
	
	List<BroadCast> getStartBroadCast();

}
