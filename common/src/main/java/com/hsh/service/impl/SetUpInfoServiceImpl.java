package com.hsh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsh.dao.SetUpInfoDao;
import com.hsh.exception.DataValidateException;
import com.hsh.model.SetUpInfo;
import com.hsh.service.SetUpInfoService;

@Service(value = "setUpInfoService")
public class SetUpInfoServiceImpl implements SetUpInfoService {

    @Autowired
    private SetUpInfoDao setUpInfoDao;

    @Override
    public void addSetUpInfo(SetUpInfo setUpInfo) throws DataValidateException {

        setUpInfoDao.addSetUpInfo(setUpInfo);
    }

    @Override
    public void updateSetUpInfo(SetUpInfo setUpInfo) throws DataValidateException {

        setUpInfoDao.updateSetUpInfo(setUpInfo);
    }

    @Override
    public SetUpInfo getSetUpInfoById(int id) throws DataValidateException {

        return setUpInfoDao.getSetUpInfoById(id);
    }

    @Override
    public List <SetUpInfo> listSetUpInfo() throws DataValidateException {

        return setUpInfoDao.listSetUpInfo();
    }

}
