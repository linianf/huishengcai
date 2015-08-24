package com.hsh.service;

import java.util.List;

import com.hsh.exception.DataValidateException;
import com.hsh.model.InvestmentPlan;

public interface InvestmentPlanService {

    void addInvestmentPlanList(List <InvestmentPlan> planList) throws DataValidateException;

    void updateInvestmentPlanList(List <InvestmentPlan> planList) throws DataValidateException;

}
