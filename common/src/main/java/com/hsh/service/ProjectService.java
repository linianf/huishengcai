package com.hsh.service;

import java.util.List;

import com.hsh.exception.DataValidateException;
import com.hsh.model.InvestmentPlan;
import com.hsh.model.LegalOpinion;
import com.hsh.model.Project;
import com.hsh.model.ProjectIntroduction;

public interface ProjectService {

    /**
     * 添加项目
     * 
     * @param project 项目详情
     * @param introduction 项目介绍
     * @param planList 投资计划列表
     * @param legalOpinion 法律意见书
     * @return 项目ID
     * @throws DataValidateException
     */
    long addProject(Project project, ProjectIntroduction introduction, List <InvestmentPlan> planList, LegalOpinion legalOpinion) throws DataValidateException;

    /**
     * 修改项目详情
     * 
     * @param project
     * @throws DataValidateException
     */
    void updateProject(Project project) throws DataValidateException;

    /**
     * 分页查询项目列表
     * 
     * @param pageSize
     * @param minId
     * @return
     * @throws DataValidateException
     */
    List <Project> listPageProject(Integer pageSize, Integer minId) throws DataValidateException;

    /**
     * 根据项目状态查询项目列表
     * 
     * @param pageSize
     * @param minId
     * @param state
     * @return
     * @throws DataValidateException
     */
    List <Project> listPageProject(Integer pageSize, Integer minId, int state) throws DataValidateException;

    /**
     * 根据项目ID查询项目详情
     * 
     * @param id
     * @return
     * @throws DataValidateException
     */
    Project getProjectById(long id) throws DataValidateException;

}
