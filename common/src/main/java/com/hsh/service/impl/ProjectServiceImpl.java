package com.hsh.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hsh.dao.ProjectDao;
import com.hsh.exception.DataValidateException;
import com.hsh.model.Project;
import com.hsh.service.InvestmentPlanService;
import com.hsh.service.LegalOpinionService;
import com.hsh.service.ProjectIntroductionService;
import com.hsh.service.ProjectService;

@Service(value = "projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ProjectIntroductionService projectIntroductionService;

    @Autowired
    private InvestmentPlanService investmentPlanService;

    @Autowired
    private LegalOpinionService legalOpinionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public long addProject(Project project, MultipartFile file) throws DataValidateException {

        if(file.getSize() > 0){
            //保存图片
            //压缩图片
            
            
        }
        project.setImgUrl("");
        long projectId = projectDao.addProject(project);
        
        
//        introduction.setProjectId(projectId);
//        projectIntroductionService.addProjectIntroduction(introduction);
//
//        for (InvestmentPlan investmentPlan : planList) {
//            investmentPlan.setProjectId(projectId);
//        }
//
//        investmentPlanService.addInvestmentPlanList(planList);
//
//        legalOpinion.setProjectId(projectId);
//        legalOpinionService.addLegalOpinion(legalOpinion);

        return projectId;
    }
    
    @Override
    public void updateProject(Project project) throws DataValidateException {

        if (project.getId() <= 0) {
            throw new DataValidateException("选择有误，请重新选择");
        }

        projectDao.updateProject(project);
    }

    @Override
    public List <Project> listPageProject(Integer pageSize, Integer minId) throws DataValidateException {

        Map <String, Object> params = new HashMap <String, Object>();
        params.put("pageSize", pageSize);
        params.put("minId", minId);
        return projectDao.queryProject(params);
    }

    @Override
    public List <Project> listPageProject(Integer pageSize, Integer minId, int state) throws DataValidateException {

        Map <String, Object> params = new HashMap <String, Object>();
        pageSize = pageSize == null ? 10 : pageSize;
        minId = minId == null ? 0 : minId;
        params.put("pageSize", pageSize);
        params.put("minId", minId);
        params.put("state", state);
        return projectDao.queryProject(params);
    }

    @Override
    public Project getProjectById(long id) throws DataValidateException {

        return projectDao.getProjectById(id);
    }

}
