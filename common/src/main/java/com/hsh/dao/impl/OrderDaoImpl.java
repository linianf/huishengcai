package com.hsh.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsh.dao.OrderDao;
import com.hsh.model.Recharge;
import com.hsh.model.Transfer;

/**
 * 
 * @author linianf
 *
 */
@Repository("orderDao")
public class OrderDaoImpl implements OrderDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public long saveOrUpdateRecharge(Recharge recharge) {
		sessionFactory.getCurrentSession().saveOrUpdate(recharge);
		return recharge.getId();
	}

	@Override
	public long saveOrUpdateRecharge(Transfer transfer) {
		sessionFactory.getCurrentSession().saveOrUpdate(transfer);
		return transfer.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transfer> getTransferList(String fromDate,String toDate,int lastMinId, int pageSize) {
		
		String hql = " from Transfer where status=0 and createTime>:fromDate and createTime<:toDate";
		if(lastMinId!=0){
			hql = hql + " and id <:lastMinId ";
		}
		hql = hql + " order by id desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query = query.setString("fromDate", fromDate);
		query = query.setString("toDate", toDate);
		if(lastMinId!=0){
			query = query.setInteger("lastMinId", lastMinId);
		}
		return (List<Transfer>)query.setMaxResults(pageSize).list();
	}

	@Override
	public Transfer getTransferById(long id) {
		String hql = " from Transfer where id=:id";
		return (Transfer)sessionFactory.getCurrentSession().createQuery(hql).setLong("id", id).uniqueResult();
	}

	@Override
	public Recharge getRechargeById(long id) {
		String hql = " from Recharge where id=:id";
		return (Recharge)sessionFactory.getCurrentSession().createQuery(hql).setLong("id", id).uniqueResult();
	}

}
