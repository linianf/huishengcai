package com.hsh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsh.dao.BroadCastDao;
import com.hsh.model.BroadCast;
import com.hsh.service.BroadCastService;

@Service("broadCastService")
@Transactional(readOnly=true)
public class BroadCastServiceImpl  implements BroadCastService{

	@Autowired
	private  BroadCastDao broadCastDao;

	@Override
	@Transactional(readOnly=false)
	public int saveOrUpdate(BroadCast broadcast) {
		return broadCastDao.saveOrUpdate(broadcast);
	}

	@Override
	@Transactional(readOnly=false)
	public void broadcastInUse(int broadcastId) {
		broadCastDao.broadcastInUse(broadcastId);
	}

	@Override
	@Transactional(readOnly=false)
	public void broadcastNotInUse(int broadcastId) {
		broadCastDao.broadcastNotInUse(broadcastId);
	}

	@Override
	public List<BroadCast> getIndexBroadCast() {
		return broadCastDao.getIndexBroadCast();
	}

	@Override
	public List<BroadCast> getStartBroadCast() {
		return broadCastDao.getStartBroadCast();
	}
	
	
}
