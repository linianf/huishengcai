package com.hsh.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsh.dao.BankDao;
import com.hsh.model.Bank;
import com.hsh.model.SubBank;

@Repository("bankDao")
public class BankDaoImpl implements BankDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int saveOrUpdateBank(Bank bank) {
		sessionFactory.getCurrentSession().saveOrUpdate(bank);
		return bank.getId();
	}

	@Override
	public int saveOrUpdateSubBank(SubBank subBank) {
		sessionFactory.getCurrentSession().saveOrUpdate(subBank);
		return subBank.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubBank> getSubBankListByCityAndBank(int bankId, int cityId) {
		String hql = "from SubBank where bankId=:bankId and cityId=:cityId";
		List<SubBank> list = (List<SubBank>)sessionFactory.getCurrentSession().createQuery(hql)
		              .setInteger("bankId", bankId).setInteger("cityId", cityId).list(); 
		return list;
	}

}
