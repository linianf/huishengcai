package com.hsh.dao;

import com.hsh.model.InvestmentPlan;

public interface InvestmentPlanDao {

    void addInvestmentPlan(InvestmentPlan plan) throws Exception;

    void deleteListByProjectId(long projectId) throws Exception;

}
