package com.hsh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hsh.exception.DataValidateException;
import com.hsh.model.SetUpInfo;
import com.hsh.service.SetUpInfoService;
import com.hsh.vo.AjaxResult;

@Controller
@RequestMapping("/setUp")
public class SetUpInfoController extends ABaseController {

    @Autowired
    private SetUpInfoService setUpInfoService;

    @RequestMapping("/index")
    public ModelAndView toSetUpIndex() {
 
        return null;
    }

    @RequestMapping("/listSetUpInfo")
    public AjaxResult listPageSetUpInfo() {

        try {
            return AjaxResult.success(setUpInfoService.listSetUpInfo());
        } catch (DataValidateException e) {
            return AjaxResult.failed(e.getMessage());
        } catch (Exception e) {
            return AjaxResult.failed("程序异常");
        }
    }

    @RequestMapping("/getSetUpInfoById")
    public AjaxResult getSetUpInfoById(@RequestParam(value = "id", required = false) String id) {

        try {
            return AjaxResult.success(setUpInfoService.getSetUpInfoById(Integer.parseInt(id)));
        } catch (NumberFormatException e) {
            return AjaxResult.failed("参数有误");
        } catch (DataValidateException e) {
            return AjaxResult.failed(e.getMessage());
        } catch (Exception e) {
            return AjaxResult.failed("程序异常");
        }
    }

    @RequestMapping("/add")
    public AjaxResult addSetUpInfo(@ModelAttribute(value = "setUpInfo") SetUpInfo setUpInfo) {

        try {
            checkSetUpInfo(setUpInfo);
            setUpInfo.setUserId(getCurrentUserId());
            setUpInfoService.addSetUpInfo(setUpInfo);
            return AjaxResult.success("新增成功");
        } catch (DataValidateException e) {
            return AjaxResult.failed(e.getMessage());
        } catch (Exception e) {
            return AjaxResult.failed("程序异常");
        }
    }

    @RequestMapping("/update")
    public AjaxResult updateSetUpInfo(@ModelAttribute(value = "setUpInfo") SetUpInfo setUpInfo) {

        try {
            checkSetUpInfo(setUpInfo);
            setUpInfo.setUserId(getCurrentUserId());
            setUpInfoService.updateSetUpInfo(setUpInfo);
            return AjaxResult.success("修改成功");
        } catch (DataValidateException e) {
            return AjaxResult.failed(e.getMessage());
        } catch (Exception e) {
            return AjaxResult.failed("程序异常");
        }
    }

    private void checkSetUpInfo(SetUpInfo setUpInfo) throws DataValidateException {

    }

}
