package com.hsh.dao;

import java.util.List;

import com.hsh.exception.DataValidateException;
import com.hsh.model.SetUpInfo;

public interface SetUpInfoDao {

    void addSetUpInfo(SetUpInfo setUpInfo) throws DataValidateException;

    void updateSetUpInfo(SetUpInfo setUpInfo) throws DataValidateException;

    SetUpInfo getSetUpInfoByKey(String key) throws DataValidateException;

    List <SetUpInfo> listSetUpInfo() throws DataValidateException;

}
