package com.hsh.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsh.controller.util.WebResult;
import com.hsh.exception.DataValidateException;
import com.hsh.model.Project;
import com.hsh.model.ProjectIntroduction;
import com.hsh.service.ProjectService;
import com.hsh.util.HSHUtil;
import com.hsh.vo.AjaxResult;

@Controller
@RequestMapping("/project")
public class ProjectController extends ABaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    /**
     * 跳转列表页面
     * 
     * @return
     */
    @RequestMapping("toListPage")
    public ModelAndView toListPage() {

        return null;
    }

    @RequestMapping("toAddPage")
    public ModelAndView toAddPage() {

        return null;
    }

    // TODO:lengxw
    public ModelAndView addProject(@ModelAttribute("project") Project project, MultipartHttpServletRequest request) {

        Map <String, Object> map = new HashMap <String, Object>();
        map.put("project", project);
        MultipartFile file = request.getFile("file");
        if (file.getSize() == 0) {
            return WebResult.failed("", "图片不能为空", map);
        }

        try {
            validateImg(file);
            project.setUserId(getCurrentUserId());
            project.setCreateTime(HSHUtil.now());
            projectService.addProject(project, file);

        } catch (DataValidateException e) {
            LOGGER.error(e.getMessage());
            return WebResult.failed("pro.add.page", "数据库异常", map);
        }

        return null;

    }

    public ModelAndView toAddProjectIntroduction(Project project) {
        
        return null;
    }
    
    public ModelAndView toAddProjectInvestmentPlan(Project project,ProjectIntroduction introduction){
        return null;
    }
    
    

    @RequestMapping("listProject")
    @ResponseBody
    public AjaxResult listProject(@RequestParam(value = "", required = false) int pageSize, @RequestParam(value = "minId", required = false) int minId) {

        try {
            return AjaxResult.success(projectService.listPageProject(pageSize, minId));
        } catch (DataValidateException e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed(e.getMessage());
        }
    }

    public AjaxResult getProjectById(@RequestParam("id") long id) {

        try {
            return AjaxResult.success(projectService.getProjectById(id));
        } catch (DataValidateException e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed(e.getMessage());
        }
    }

    private String[] imgSuffixs = new String[] { "jpg", "jpeg", "png", "bmp", "gif" };

    private String validateImg(MultipartFile imgFile) throws DataValidateException {

        if (imgFile == null) {
            throw new DataValidateException("请上传图片");
        }
        String fileName = imgFile.getOriginalFilename();
        long size = imgFile.getSize();
        if (size > 1024 * 1024) {
            throw new DataValidateException("图片不能超过1M");
        }
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (!Arrays.asList(imgSuffixs).contains(suffix)) {
            throw new DataValidateException("图片格式只能是jpg、jpeg、png、bmp、gif！");
        }
        return suffix;
    }

}
