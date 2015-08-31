package com.hsh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hsh.exception.DataValidateException;
import com.hsh.model.RateConfig;
import com.hsh.service.RateConfigService;
import com.hsh.util.HSHUtil;
import com.hsh.vo.AjaxResult;

@Controller
@RequestMapping("/rateConfig")
public class RateConfigController extends ABaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateConfigController.class);
    @Autowired
    private RateConfigService rateConfigService;

    @RequestMapping("/index")
    public ModelAndView toRateConfig() {

        return null;
    }

    @RequestMapping("/listRateConfig")
    public AjaxResult listRateConfig() {

        try {
            return AjaxResult.success(rateConfigService.listRateConfig(null));
        } catch (DataValidateException e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed("系统异常");
        }
    }

    @RequestMapping("/getRateConfigById")
    public AjaxResult getRateConfigById(@RequestParam(value = "id") String id) {

        try {
            Map <String, Object> params = new HashMap <String, Object>();
            params.put("id", Integer.parseInt(id));
            List <RateConfig> list = rateConfigService.listRateConfig(params);
            if (list == null || list.size() == 0) {
                throw new DataValidateException("选择有误，请重新选择");
            }
            return AjaxResult.success(list.get(0));
        } catch (NumberFormatException e) {
            return AjaxResult.failed("参数有误");
        } catch (DataValidateException e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed("系统异常");
        }
    }

    @RequestMapping("/add")
    public AjaxResult addRateConfig(@ModelAttribute(value = "rateConfig") RateConfig rateConfig) {

        try {
            rateConfigService.addRateConfig(rateConfig);
            fillUserIdAndCreateTime(rateConfig, getCurrentUserId());
            return AjaxResult.success("新增成功");
        } catch (DataValidateException e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed("系统异常");
        }
    }

    @RequestMapping("/update")
    public AjaxResult updateRateConfig(@ModelAttribute(value = "rateConfig") RateConfig rateConfig) {

        try {

            if (rateConfig.getId() <= 0) {
                throw new DataValidateException("请重新选择");
            }
            fillUserIdAndCreateTime(rateConfig, getCurrentUserId());
            rateConfigService.updateRateConfig(rateConfig);
            return AjaxResult.success("修改成功");
        } catch (DataValidateException e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed("系统异常");
        }
    }

    @RequestMapping("/delete")
    public AjaxResult deleteRateConfig(@RequestParam("id") int id) {

        return null;
    }

    private void fillUserIdAndCreateTime(RateConfig rateConfig, int userId) {

        rateConfig.setUserId(userId);
        rateConfig.setCreateTime(HSHUtil.now());
    }

}
