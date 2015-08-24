package com.hsh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsh.dao.InvestmentPlanDao;
import com.hsh.exception.DataValidateException;
import com.hsh.model.InvestmentPlan;
import com.hsh.service.InvestmentPlanService;

@Service("investmentPlanService")
public class InvestmentPlanServiceImpl implements InvestmentPlanService {

    @Autowired
    private InvestmentPlanDao investmentPlanDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addInvestmentPlanList(List <InvestmentPlan> planList) throws DataValidateException {

        try {
            for (InvestmentPlan plan : planList) {
                investmentPlanDao.addInvestmentPlan(plan);
            }
        } catch (Exception e) {
            throw new DataValidateException("数据库异常");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInvestmentPlanList(List <InvestmentPlan> planList) throws DataValidateException {

        try {
            investmentPlanDao.deleteListByProjectId(planList.get(0).getProjectId());

        } catch (Exception e) {

        }

    }
}
