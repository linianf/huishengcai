package com.hsh.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsh.dao.FtpDao;
import com.hsh.model.FtpContent;

@Repository("ftpDao")
public class FtpDaoImpl implements FtpDao{

	@Autowired
	private  SessionFactory sessionFactory;

	@Override
	public FtpContent getContentById(int id) {
		String hql = " from FtpContent where id =:id";
		return (FtpContent)sessionFactory.getCurrentSession().createQuery(hql)
		       .setInteger("id", id).uniqueResult();
	}

	@Override
	public int saveOrUpdate(FtpContent ftpContent) {
		sessionFactory.getCurrentSession().saveOrUpdate(ftpContent);
		return ftpContent.getId();
	}

	@Override
	public boolean isExistMD5(String md5) {
		String hql = " from FtpContent where md5=:md5 ";
		Object obj = sessionFactory.getCurrentSession()
				.createQuery(hql).setString("md5", md5).uniqueResult();
		return obj!=null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FtpContent> getContentById() {
		String hql = " from FtpContent ";
		List<FtpContent> list = (List<FtpContent>)sessionFactory.getCurrentSession()
				.createQuery(hql).list();
		return list;
	}
}
