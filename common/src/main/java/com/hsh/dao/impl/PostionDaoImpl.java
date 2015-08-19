package com.hsh.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsh.dao.PostionDao;
import com.hsh.model.Postion;

@Repository(value = "postionDao")
public class PostionDaoImpl implements PostionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Postion getPostionById(long postionId) {

        String hql = "from Postion where id =:postionId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setLong("postionId", postionId);
        return query.list().size() > 0 ? (Postion) query.list().get(0) : null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List <Postion> ListAllPostion(int pageSize, int minId) {

        StringBuffer hql = new StringBuffer("from Postion");
        if (minId > 0) {
            hql.append(" and id <:minId ");
        }
        hql.append("order by id desc");
        Query query = sessionFactory.getCurrentSession().createQuery(hql.toString()).setMaxResults(pageSize);
        return query.list();
    }
}
