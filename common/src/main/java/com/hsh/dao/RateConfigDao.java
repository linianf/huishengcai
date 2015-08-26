package com.hsh.dao;

import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import com.hsh.model.RateConfig;

public interface RateConfigDao {

    List <RateConfig> listRateConfig(Map <String, Object> params) throws HibernateException;

    void addRateConfig(RateConfig rateConfig) throws HibernateException;

    void updateRateConfig(RateConfig rateConfig) throws HibernateException;

    void deleteRateConfig(RateConfig rateConfig) throws HibernateException;

}
