package com.hsh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hsh.exception.DataValidateException;
import com.hsh.service.ProjectService;
import com.hsh.vo.AjaxResult;

@Controller
@RequestMapping("/project")
public class ProjectController extends ABaseController {

    @Autowired
    private ProjectService projectService;

    /**
     * 跳转列表页面
     * 
     * @return
     */
    public ModelAndView toListPage() {

        return null;
    }

    public AjaxResult listProject(@RequestParam(value = "", required = false) int pageSize, @RequestParam(value = "minId", required = false) int minId) {

        try {
            return AjaxResult.success(projectService.listPageProject(pageSize, minId));
        } catch (DataValidateException e) {
            return AjaxResult.failed(e.getMessage());
        }
    }

    public AjaxResult getProjectById(@RequestParam("id") long id) {

        try {
            return AjaxResult.success(projectService.getProjectById(id));
        } catch (DataValidateException e) {
            return AjaxResult.failed(e.getMessage());
        }
    }

}
