package com.hsh.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsh.dao.BroadCastDao;
import com.hsh.model.BroadCast;

@Repository("broadCastDao")
public class BroadCastDaoImpl implements BroadCastDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int saveOrUpdate(BroadCast broadcast) {
		sessionFactory.getCurrentSession().saveOrUpdate(broadcast);
		return broadcast.getId();
	}

	@Override
	public void broadcastInUse(int broadcastId) {
		String hql = " update BroadCast set  status=1  where status=0 and broadcastId=:broadcastId";
	    sessionFactory.getCurrentSession().createQuery(hql).setInteger("broadcastId", broadcastId)
		              .executeUpdate();
	}

	@Override
	public void broadcastNotInUse(int broadcastId) {
		String hql = " update BroadCast set  status=0 where status=1 and broadcastId=:broadcastId";
	    sessionFactory.getCurrentSession().createQuery(hql).setInteger("broadcastId", broadcastId)
		              .executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BroadCast> getIndexBroadCast() {
		String hql = " from BroadCast where broadType=2 and status=1";
	    return (List<BroadCast>)sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BroadCast> getStartBroadCast() {
		String hql = " from BroadCast where broadType=1 and status=1";
	    return (List<BroadCast>)sessionFactory.getCurrentSession().createQuery(hql).list();
	}

}
