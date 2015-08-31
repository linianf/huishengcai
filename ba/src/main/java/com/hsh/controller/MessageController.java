package com.hsh.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hsh.exception.DataValidateException;
import com.hsh.model.Message;
import com.hsh.service.MessageService;
import com.hsh.vo.AjaxResult;

@Controller
@RequestMapping("/message")
public class MessageController extends ABaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/toListPage", method = RequestMethod.POST)
    public ModelAndView toListMessage() {

        return null;
    }

    @RequestMapping(value = "/listPageMessage", method = RequestMethod.POST)
    public AjaxResult listPageMessage(@RequestParam(value = "pageSize", required = false) String pageSize, @RequestParam(value = "minId", required = false) String minId) {

        try {
            Map <String, Object> params = new HashMap <String, Object>();
            // pageSize 每页显示条数 minId 最小id
            int iPageSize = StringUtils.isNotBlank(pageSize) ? 10 : Integer.parseInt(pageSize);
            int iMinId = StringUtils.isNotBlank(minId) ? 0 : Integer.parseInt(minId);
            params.put("pageSize", iPageSize);
            params.put("minId", iMinId);
            return AjaxResult.success(messageService.listMessage(params));
        } catch (NumberFormatException e) {
            return AjaxResult.failed("参数异常");
        } catch (DataValidateException e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed("系统异常");
        }
    }

    @RequestMapping(value = "/getMessageById", method = RequestMethod.POST)
    public AjaxResult getMessageById(@RequestParam(value = "id") String id) {

        try {
            return AjaxResult.success(messageService.getMessageById(Long.parseLong(id)));
        } catch (NumberFormatException e) {
            return AjaxResult.failed("选择有误，请重新选择");
        } catch (DataValidateException e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed("系统异常");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public AjaxResult updateMessage(@ModelAttribute(value = "message") Message message) {

        try {
            checkMessageCondition(message);
            message.setUserId(getCurrentUserId());
            messageService.updateMessage(message);
            return AjaxResult.success("修改推送信息成功");
        } catch (DataValidateException e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed("系统异常");
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxResult addMessage(@ModelAttribute(value = "message") Message message) {

        try {
            checkMessageCondition(message);
            message.setUserId(getCurrentUserId());
            messageService.addMessage(message);
            return AjaxResult.success("新增推送信息成功");
        } catch (DataValidateException e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed(e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return AjaxResult.failed("系统异常");
        }
    }

    private void checkMessageCondition(Message message) throws DataValidateException {

        // TODO:LENGXW add checkCondition detail
    }

}
