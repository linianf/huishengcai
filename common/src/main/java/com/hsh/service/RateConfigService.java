package com.hsh.service;

import java.util.List;
import java.util.Map;

import com.hsh.exception.DataValidateException;
import com.hsh.model.RateConfig;

/**
 * 钱包余额日利率配置service
 * 
 * @author lengxiangwu
 * 
 */
public interface RateConfigService {

    List <RateConfig> listRateConfig(Map <String, Object> params) throws DataValidateException;

    void addRateConfig(RateConfig rateConfig) throws DataValidateException;

    void updateRateConfig(RateConfig rateConfig) throws DataValidateException;

    void deleteRateConfig(RateConfig rateConfig) throws DataValidateException;

}
