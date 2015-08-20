package com.hsh.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsh.dao.SetUpInfoDao;
import com.hsh.exception.DataValidateException;
import com.hsh.model.SetUpInfo;

@SuppressWarnings("unchecked")
@Repository(value = "setUpInfoDao")
public class SetUpInfoDaoImpl implements SetUpInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addSetUpInfo(SetUpInfo setUpInfo) throws DataValidateException {

        try {
            sessionFactory.getCurrentSession().save(setUpInfo);
        } catch (Exception e) {
            throw new DataValidateException("数据库异常");
        }
    }

    @Override
    public void updateSetUpInfo(SetUpInfo setUpInfo) throws DataValidateException {

        try {
            sessionFactory.getCurrentSession().update(setUpInfo);
        } catch (Exception e) {
            throw new DataValidateException("数据库异常");
        }
    }

    @Override
    public SetUpInfo getSetUpInfoById(int id) throws DataValidateException {

        try {
            StringBuffer hql = new StringBuffer("from SetUpInfo where id =: id");
            Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
            List <SetUpInfo> list = query.setInteger("id", id).list();
            return (list != null && list.size() > 0) ? list.get(0) : null;
        } catch (Exception e) {
            throw new DataValidateException("数据库异常");
        }
    }

    @Override
    public List <SetUpInfo> listSetUpInfo() throws DataValidateException {

        return sessionFactory.getCurrentSession().createQuery("from SetUpInfo").list();
    }

}
