package com.hsh.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsh.dao.PushLogDao;
import com.hsh.exception.DataValidateException;
import com.hsh.model.PushLog;

@Repository(value = "pushLogDao")
public class PushLogDaoImpl implements PushLogDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPushLog(PushLog pushLog) throws DataValidateException {

        try {
            sessionFactory.getCurrentSession().save(pushLog);
        } catch (Exception ex) {
            throw new DataValidateException("数据库异常");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List <PushLog> pagePushLogByMessageId(Map <String, Object> params) throws DataValidateException {

        try {
            String hql = " from PushLog where messageId=:messageId";
            if (params.get("minId") != null) {
                hql = hql + " and id <:minId ";
            }
            hql = hql + " order by id desc";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            query = query.setLong("messageId", (long) params.get("messageId"));
            if (params.get("minId") != null) {
                query = query.setLong("minId", (long) params.get("minId"));
            }
            if (params.get("pageSize") != null) {
                return (List <PushLog>) query.setMaxResults((int) params.get("pageSize")).list();
            }
            return query.list();
        } catch (Exception e) {
            throw new DataValidateException("数据库异常");
        }
    }

}
