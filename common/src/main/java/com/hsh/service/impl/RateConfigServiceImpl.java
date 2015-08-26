package com.hsh.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsh.dao.RateConfigDao;
import com.hsh.exception.DataValidateException;
import com.hsh.model.RateConfig;
import com.hsh.service.RateConfigService;

@Service(value = "rateConfigService")
public class RateConfigServiceImpl implements RateConfigService {

    @Autowired
    private RateConfigDao rateConfigdao;

    @Override
    public List <RateConfig> listRateConfig(Map <String, Object> params) throws DataValidateException {

        try {
            return rateConfigdao.listRateConfig(params);
        } catch (HibernateException e) {
            throw new DataValidateException("数据库异常");
        }
    }

    @Override
    public void addRateConfig(RateConfig rateConfig) throws DataValidateException {

        try {
            rateConfigdao.addRateConfig(rateConfig);
        } catch (HibernateException e) {
            throw new DataValidateException("数据库异常");
        }
    }

    @Override
    public void updateRateConfig(RateConfig rateConfig) throws DataValidateException {

        try {
            rateConfigdao.updateRateConfig(rateConfig);
        } catch (HibernateException e) {
            throw new DataValidateException("数据库异常");
        }
    }

    @Override
    public void deleteRateConfig(RateConfig rateConfig) throws DataValidateException {

        try {
            rateConfigdao.deleteRateConfig(rateConfig);
        } catch (HibernateException e) {
            throw new DataValidateException("数据库异常");
        }
    }

}
