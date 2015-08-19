package com.hsh.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsh.dao.CrowdDao;
import com.hsh.model.Crowd;
import com.hsh.model.CrowdItem;
import com.hsh.model.CrowdPic;

@Repository("crowdDao")
public class CrowdDaoImpl implements CrowdDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int saveOrUpdateCrowd(Crowd crowd) {
		sessionFactory.getCurrentSession().saveOrUpdate(crowd);
		return crowd.getId();
	}

	@Override
	public int saveOrUpdateItem(CrowdItem item) {
		sessionFactory.getCurrentSession().saveOrUpdate(item);
		return item.getId();
	}

	@Override
	public int saveOrUpdatePic(CrowdPic crowdPic) {
		sessionFactory.getCurrentSession().saveOrUpdate(crowdPic);
		return crowdPic.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrowdItem> getItemsByCrowdId(int crowdId) {
		String hql = "from CrowdItem where crowdId=:crowdId";
		List<CrowdItem> list = (List<CrowdItem>)sessionFactory.getCurrentSession().createQuery(hql)
		              .setInteger("crowdId", crowdId).list(); 
		return list;
	}

	@Override
	public Crowd getCrowdById(int crowdId) {
		String hql = "from Crowd where id=:crowdId";
		return (Crowd)sessionFactory.getCurrentSession().createQuery(hql)
	              .setInteger("crowdId", crowdId).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrowdPic> getPicListByCrowdId(int crowdId) {
		String hql = "from CrowdPic where crowdId=:crowdId";
		List<CrowdPic> list = (List<CrowdPic>)sessionFactory.getCurrentSession().createQuery(hql)
		              .setInteger("crowdId", crowdId).list(); 
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Crowd> getAllCrowdDue() {
		String hql = " from Crowd where endDate<:endDate and status=1";
		return (List<Crowd>)sessionFactory.getCurrentSession().createQuery(hql)
				.setDate("endDate", new Date()).list();
	}

	@Override
	public int getTotalNumForCrowd(int crowdId) {
		String hql = " select sum(amount) from UserCrowd where crowdId=:crowdId";
		Number num = (Number)sessionFactory.getCurrentSession().createQuery(hql).setInteger("crowdId",crowdId);
		return num.intValue();
	}
	
	public Crowd  getHotRecommend(){
		String hql = " from Crowd where crowdStartDate<:startDate and endDate>:endDate order by orderValue desc";
		Date now = new Date();
		return (Crowd)sessionFactory.getCurrentSession().createQuery(hql).setDate("startDate", now)
				.setDate("endDate", now).setMaxResults(1).uniqueResult();
	}
	
	public Crowd  getPreRecommend(){
		String hql = " from Crowd where crowdStartDate>:startDate order by orderValue desc";
		Date now = new Date();
		return (Crowd)sessionFactory.getCurrentSession().createQuery(hql).setDate("startDate", now)
				.setDate("endDate", now).setMaxResults(1).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Crowd>  getHotRecommendList(int lastMinId, int pageSize){
		String hql = " from Crowd where crowdStartDate<:startDate and endDate>:endDate ";
		if(lastMinId!=0){
			hql = hql + " and orderValue <:lastMinId ";
		}
		hql = hql + " order by orderValue desc ";
		Date now = new Date();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query = query.setDate("startDate", now).setDate("endDate", now);
		if(lastMinId!=0){
			query = query.setInteger("lastMinId", lastMinId);
		}
		return (List<Crowd>)query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Crowd>  getPreRecommendList(){
		String hql = " from Crowd where crowdStartDate<:startDate and endDate>:endDate order by orderValue desc";
		Date now = new Date();
		return (List<Crowd>)sessionFactory.getCurrentSession().createQuery(hql).setDate("startDate", now)
				.setDate("endDate", now).list();
	}
}
