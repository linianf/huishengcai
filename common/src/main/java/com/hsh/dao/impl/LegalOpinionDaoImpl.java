package com.hsh.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsh.dao.LegalOpinionDao;
import com.hsh.model.LegalOpinion;

@Repository("legalOpinionDao")
public class LegalOpinionDaoImpl implements LegalOpinionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addLegalOpinion(LegalOpinion legalOpinion) throws Exception {

        sessionFactory.getCurrentSession().save(legalOpinion);
    }

    @Override
    public void updateLegalOpinion(LegalOpinion legalOpinion) throws Exception {

        sessionFactory.getCurrentSession().update(legalOpinion);
    }

    @Override
    public void deleteLegalOpinionById(long id) throws Exception {

        sessionFactory.getCurrentSession().createSQLQuery("delete LegalOpinion where id=:id").setLong("id", id).executeUpdate();
    }

    @Override
    public void deleteLegalOpinionByProjectId(long projectId) throws Exception {

        sessionFactory.getCurrentSession().createSQLQuery("delete LegalOpinion where projectId=:projectId").setLong("projectId", projectId).executeUpdate();
    }

}
