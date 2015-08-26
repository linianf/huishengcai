package com.hsh.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsh.dao.RateConfigDao;
import com.hsh.model.RateConfig;

@Repository("rateConfigDao")
public class RateConfigDaoImpl implements RateConfigDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List <RateConfig> listRateConfig(Map <String, Object> params) throws HibernateException {

        StringBuffer sb = new StringBuffer("from RateConfig c where 1=1");

        if (params.get("amount") != null) {
            sb.append("c.maxValue <=:amount and c.minValue >=:amount");
        }
        if (params.get("id") != null) {
            sb.append("c.id =:id");
        }
        Query query = sessionFactory.getCurrentSession().createQuery(sb.toString());

        if (params.get("amount") != null) {
            query.setInteger("amount", Integer.parseInt(params.get("amount").toString()));
        }
        if (params.get("id") != null) {
            query.setInteger("id", Integer.parseInt(params.get("id").toString()));
        }
        return query.list();
    }

    @Override
    public void addRateConfig(RateConfig rateConfig) throws HibernateException {

        sessionFactory.getCurrentSession().save(rateConfig);
    }

    @Override
    public void updateRateConfig(RateConfig rateConfig) throws HibernateException {

        sessionFactory.getCurrentSession().update(rateConfig);
    }

    @Override
    public void deleteRateConfig(RateConfig rateConfig) throws HibernateException {

        sessionFactory.getCurrentSession().delete(rateConfig);
    }

}
