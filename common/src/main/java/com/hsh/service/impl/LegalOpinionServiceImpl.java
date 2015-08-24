package com.hsh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsh.dao.LegalOpinionDao;
import com.hsh.exception.DataValidateException;
import com.hsh.model.LegalOpinion;
import com.hsh.service.LegalOpinionService;

@Service("legalOpinionService")
public class LegalOpinionServiceImpl implements LegalOpinionService {

    @Autowired
    private LegalOpinionDao legalOpinionDao;

    @Override
    public void addLegalOpinion(LegalOpinion legalOpinion) throws DataValidateException {

        try {
            legalOpinionDao.addLegalOpinion(legalOpinion);
        } catch (Exception e) {
            throw new DataValidateException("数据库异常");
        }
    }

    @Override
    public void updateLegalOpinion(LegalOpinion legalOpinion) throws DataValidateException {

        try {
            legalOpinionDao.updateLegalOpinion(legalOpinion);
        } catch (Exception e) {
            throw new DataValidateException("数据库异常");
        }
    }

    @Override
    public void deleteLegalOpinion(long id) throws DataValidateException {

        try {
            legalOpinionDao.deleteLegalOpinionById(id);
        } catch (Exception e) {
            throw new DataValidateException("数据库异常");
        }
    }

    @Override
    public void deleteLegalOpinionByProjectId(long projectId) throws DataValidateException {

        try {
            legalOpinionDao.deleteLegalOpinionByProjectId(projectId);
        } catch (Exception e) {
            throw new DataValidateException("数据库异常");
        }
    }

}
