package com.hsh.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsh.dao.InvestmentPlanDao;
import com.hsh.model.InvestmentPlan;

@Repository("investmentPlanDao")
public class InvestmentPlanDaoImpl implements InvestmentPlanDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addInvestmentPlan(InvestmentPlan plan) throws Exception {

        sessionFactory.getCurrentSession().save(plan);
    }

    @Override
    public void deleteListByProjectId(long projectId) throws Exception {

        StringBuffer sb = new StringBuffer("delete InveInvestmentPlan as plan where plan.projectId=:projectId");

        sessionFactory.getCurrentSession().createQuery(sb.toString()).setLong("projectId", projectId).executeUpdate();
    }

}
