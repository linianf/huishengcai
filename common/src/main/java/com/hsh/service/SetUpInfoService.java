package com.hsh.service;

import java.util.List;

import com.hsh.exception.DataValidateException;
import com.hsh.model.SetUpInfo;

public interface SetUpInfoService {

    /**
     * 
     * @param setUpInfo
     * @throws DataValidateException
     */
    void addSetUpInfo(SetUpInfo setUpInfo) throws DataValidateException;

    /**
     * 
     * @param setUpInfo
     * @throws DataValidateException
     */
    void updateSetUpInfo(SetUpInfo setUpInfo) throws DataValidateException;

    /**
     * 
     * @param key
     * @return
     * @throws DataValidateException
     */
    SetUpInfo getSetUpInfoById(int id) throws DataValidateException;

    List <SetUpInfo> listSetUpInfo() throws DataValidateException;

}
