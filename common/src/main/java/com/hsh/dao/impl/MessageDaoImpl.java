package com.hsh.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsh.dao.MessageDao;
import com.hsh.exception.DataValidateException;
import com.hsh.model.Message;

@Repository(value = "messageDao")
public class MessageDaoImpl implements MessageDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addMessage(Message message) throws DataValidateException {

        try {
            sessionFactory.getCurrentSession().save(message);
        } catch (Exception ex) {
            throw new DataValidateException("数据库异常");
        }
    }

    @Override
    public void updateMessage(Message message) throws DataValidateException {

        try {
            sessionFactory.getCurrentSession().update(message);
        } catch (Exception ex) {
            throw new DataValidateException("数据库异常");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List <Message> listMessage(Map <String, Object> params) throws DataValidateException {

        try {
            String hql = " from Message where 1=1";
            if (params.get("minId") != null) {
                hql = hql + " and id <:minId ";
            }
            hql = hql + " order by id desc";
            Query query = sessionFactory.getCurrentSession().createQuery(hql);
            if (params.get("minId") != null) {
                query = query.setLong("minId", (long) params.get("minId"));
            }
            if (params.get("pageSize") != null) {
                return (List <Message>) query.setMaxResults((int) params.get("pageSize")).list();
            }
            return query.list();
        } catch (Exception e) {
            throw new DataValidateException("数据库异常");
        }
    }
}
