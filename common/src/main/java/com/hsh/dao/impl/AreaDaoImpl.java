package com.hsh.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsh.dao.AreaDao;
import com.hsh.model.City;
import com.hsh.model.Province;

@Repository("areaDao")
public class AreaDaoImpl implements AreaDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int saveOrUpdateProvince(Province province) {
		sessionFactory.getCurrentSession().saveOrUpdate(province);
		return province.getId();
	}

	@Override
	public int saveOrUpdateCity(City city) {
		sessionFactory.getCurrentSession().saveOrUpdate(city);
		return city.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> getCityListByProvince(int provinceId) {
		String hql = " from City where provinceId=:provinceId ";
		List<City> list = (List<City>)sessionFactory.getCurrentSession().createQuery(hql)
		              .setInteger("provinceId", provinceId).list(); 
		return list;
	}

	
}
