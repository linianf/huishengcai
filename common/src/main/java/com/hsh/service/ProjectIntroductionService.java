package com.hsh.service;

import com.hsh.exception.DataValidateException;
import com.hsh.model.ProjectIntroduction;


public interface ProjectIntroductionService {

    void addProjectIntroduction(ProjectIntroduction introduction) throws DataValidateException;

}
