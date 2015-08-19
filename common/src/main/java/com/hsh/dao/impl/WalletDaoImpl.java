package com.hsh.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsh.dao.WalletDao;
import com.hsh.model.RateConfig;
import com.hsh.model.Wallet;
import com.hsh.model.WalletFlow;
import com.hsh.util.HSHUtil;

@Repository("walletDao")
public class WalletDaoImpl implements WalletDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int saveOrUpdate(Wallet wallet) {
		sessionFactory.getCurrentSession().saveOrUpdate(wallet);
		return wallet.getId();
	}

	@Override
	public int saveOrUpdate(WalletFlow walletFlow) {
		sessionFactory.getCurrentSession().saveOrUpdate(walletFlow);
		return walletFlow.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WalletFlow> getWalletFlowByUserId(int userId, int lastMinFlowId,
			int pageSize) {
		String hql = " from WalletFlow where userId=:userId ";
		if(lastMinFlowId!=0){
			hql = hql + " and id <:lastMinFlowId ";
		}
		hql = hql + " order by id desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query = query.setInteger("userId", userId);
		if(lastMinFlowId!=0){
			query = query.setInteger("lastMinFlowId", lastMinFlowId);
		}
		return (List<WalletFlow>)query.setMaxResults(pageSize).list();
	}

	@Override
	public int getUserWeekGain(int userId) {
		String hql = "select sum(amount) from WalletFlow where userId=:userId and inOut=1 and operateDate>:operateDate";
		String firstDayOfWeek = HSHUtil.calacDaysFromThisWeek(new Date());
		Number num = (Number)sessionFactory.getCurrentSession().createQuery(hql).setInteger("userId", userId)
		              .setString("operateDate", firstDayOfWeek).iterate().next();
		return num.intValue();
	}

	@Override
	public int getUserMonthGain(int userId) {
		String hql = "select sum(amount) from WalletFlow where userId=:userId and inOut=1 and operateDate>:operateDate";
		String firstDayOfWeek = HSHUtil.calacDaysFromThisMonth(new Date());
		Number num = (Number)sessionFactory.getCurrentSession().createQuery(hql).setInteger("userId", userId)
		              .setString("operateDate", firstDayOfWeek).iterate().next();
		return num.intValue();
	}

	@Override
	public int getUserDayGain(int userId) {
		String hql = "select sum(amount) from WalletFlow where userId=:userId and inOut=1 and operateDate>:operateDate";
		String firstDayOfWeek = HSHUtil.calacDaysFromToday(new Date());
		Number num = (Number)sessionFactory.getCurrentSession().createQuery(hql).setInteger("userId", userId)
		              .setString("operateDate", firstDayOfWeek).iterate().next();
		return num.intValue();
	}

	@Override
	public int getUserTotalGain(int userId) {
		String hql = "select sum(amount) from WalletFlow where userId=:userId and inOut=1";
		Number num = (Number)sessionFactory.getCurrentSession().createQuery(hql).setInteger("userId", userId)
		              .iterate().next();
		return num.intValue();
	}

	@Override
	public void addUserWallet(int userId, int amount) {
		String hql = " update Wallet set balance=balance + :amount where userId=:userId";
		sessionFactory.getCurrentSession().createQuery(hql).setInteger("amount", amount)
		              .setInteger("userId", userId).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Wallet> getWalletList(int lastMinId, int pageSize) {
		String hql = " from Wallet ";
		if(lastMinId!=0){
			hql = hql + " and id <:lastMinId ";
		}
		hql = hql + " order by id desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(lastMinId!=0){
			query = query.setInteger("lastMinId", lastMinId);
		}
		return (List<Wallet>)query.setMaxResults(pageSize).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RateConfig> getRateConfig() {
		String hql = " from RateConfig";
		return (List<RateConfig>)sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	public Wallet getWalletByUserId(int userId) {
		String hql = "from Wallet where userId=:userId";
		return (Wallet)sessionFactory.getCurrentSession().createQuery(hql)
				.setInteger("userId", userId).uniqueResult();
	}

}
