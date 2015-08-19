package com.hsh.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsh.dao.UserCrowdDao;
import com.hsh.model.QuanGetRecord;
import com.hsh.model.UserCrowd;

@Repository("userCrowdDao")
public class UserCrowdDaoImpl implements UserCrowdDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int saveOrUpdate(UserCrowd userCrowd) {
		sessionFactory.getCurrentSession().saveOrUpdate(userCrowd);
		return userCrowd.getId();
	}

	@Override
	public long saveOrUpdate(QuanGetRecord quanGetRecord) {
		sessionFactory.getCurrentSession().saveOrUpdate(quanGetRecord);
		return quanGetRecord.getId();
	}
	
	/**
	 * 正在进行中的UserCrowd数量不会多的离谱，无需分页，一次性全部吐出
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserCrowd> getInCrowdList(int userId) {
		String hql = " from UserCrowd where status=1 and userId=:userId ";
		List<UserCrowd> list = (List<UserCrowd>)sessionFactory.getCurrentSession().createQuery(hql).setInteger("userId", userId)
		                       .list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserCrowd> getEndCrowdList(int userId, int pageSize,
			int lastMinUserCrowdId) {
		String hql = " from UserCrowd where status=0 and userId=:userId ";
		if(lastMinUserCrowdId!=0){
			hql = hql + " and id <:lastMinUserCrowdId ";
		}
		hql = hql + " order by id desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query = query.setInteger("userId", userId);
		if(lastMinUserCrowdId!=0){
			query = query.setInteger("lastMinUserCrowdId", lastMinUserCrowdId);
		}
		return (List<UserCrowd>)query.setMaxResults(pageSize).list();
	}

	/**
	 * 如果一个项目到期，则需处理所有对应的状态state为1的UserCrowd
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserCrowd> getUserCrowdByCrowdId(int crowdId,int pageSize, int lastMinUserCrowdId) {
		String hql = " from UserCrowd where status=1 and crowdId=:crowdId ";
		
		if(lastMinUserCrowdId!=0){
			hql = hql + " and id <:lastMinUserCrowdId ";
		}
		hql = hql + " order by id desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(lastMinUserCrowdId!=0){
			query = query.setInteger("lastMinUserCrowdId", lastMinUserCrowdId);
		}
		List<UserCrowd> list = (List<UserCrowd>)query.setMaxResults(pageSize).list();
        return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserCrowd> getInCrowdList(int pageSize, int lastMinUserCrowdId) {
		String hql = " from UserCrowd where status=1 ";
		if(lastMinUserCrowdId!=0){
			hql = hql + " and id <:lastMinUserCrowdId ";
		}
		hql = hql + " order by id desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(lastMinUserCrowdId!=0){
			query = query.setInteger("lastMinUserCrowdId", lastMinUserCrowdId);
		}
		return (List<UserCrowd>)query.setMaxResults(pageSize).list();
	}

}
