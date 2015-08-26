package com.hsh.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
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

    @SuppressWarnings("unchecked")
    @Override
    public List <Message> getMessageListByParams(Map <String, Object> params) throws DataValidateException{
        try{
            StringBuffer sb = new StringBuffer("from Message where 1=1");
            
            if(params.get("startTime")!=null){
                sb.append("and pushTime >=:startTime ");
            }
            if(params.get("endTime")!=null){
                sb.append("and pushTime <=:endTime ");
            }
            if(params.get("state")!=null){
                sb.append("and state =:state ");
            }
            if(params.get("type")!=null){
                sb.append("and type =:type ");
            }
            sb.append("order by id");
            Query query = sessionFactory.getCurrentSession().createSQLQuery(sb.toString());
            
            if(params.get("startTime")!=null){
                query.setDate("startTime", (Date) params.get("startTime"));
            }
            if(params.get("endTime")!=null){
                query.setDate("endTime", (Date) params.get("endTime"));
            }
            if(params.get("state")!=null){
                query.setInteger("state", (int) params.get("state"));
            }
            if(params.get("type")!=null){
                query.setInteger("type", (int) params.get("type"));
            }
            return query.list();
        }catch(NumberFormatException e){
            throw new DataValidateException("参数异常");
        }catch(HibernateException e){
            throw new DataValidateException("数据库异常");
        }
    }
}
