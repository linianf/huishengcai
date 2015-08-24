package com.hsh.dao;

import com.hsh.model.LegalOpinion;

public interface LegalOpinionDao {

    void addLegalOpinion(LegalOpinion legalOpinion) throws Exception;

    void updateLegalOpinion(LegalOpinion legalOpinion) throws Exception;

    void deleteLegalOpinionById(long id) throws Exception;

    void deleteLegalOpinionByProjectId(long projectId) throws Exception;
}
