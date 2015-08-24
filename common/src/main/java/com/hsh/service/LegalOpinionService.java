package com.hsh.service;

import com.hsh.exception.DataValidateException;
import com.hsh.model.LegalOpinion;

/**
 * 法律意见书
 * 
 * @author lengxiangwu
 * 
 */
public interface LegalOpinionService {

    /**
     * 添加法律意见书
     * 
     * @param legalOpinion
     * @throws DataValidateException
     */
    void addLegalOpinion(LegalOpinion legalOpinion) throws DataValidateException;

    /**
     * 修改法律意见书
     * 
     * @param legalOpinion
     * @throws DataValidateException
     */
    void updateLegalOpinion(LegalOpinion legalOpinion) throws DataValidateException;

    /**
     * 根据意见书ID删除法律意见书
     * 
     * @param legalOpinion
     * @throws DataValidateException
     */
    void deleteLegalOpinion(long id) throws DataValidateException;

    /**
     * 根据项目ID删除法律意见书
     * 
     * @param projectId
     * @throws DataValidateException
     */
    void deleteLegalOpinionByProjectId(long projectId) throws DataValidateException;
}
